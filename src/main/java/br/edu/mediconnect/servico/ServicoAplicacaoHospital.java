package br.edu.mediconnect.servico;

import br.edu.mediconnect.legado.ClienteLabX;
import br.edu.mediconnect.legado.ApiLegadaPlanoSaude;
import br.edu.mediconnect.modelo.Internacao;
import br.edu.mediconnect.modelo.Consulta;
import br.edu.mediconnect.modelo.SolicitacaoExame;
import br.edu.mediconnect.modelo.Paciente;
import br.edu.mediconnect.repositorio.RepositorioConsultasEmMemoria;
import br.edu.mediconnect.repositorio.RepositorioPacientesEmMemoria;
import java.util.ArrayList;
import java.util.List;

/**
 * Serviço legado propositalmente simples para evolução durante as aulas.
 * Há decisões de design e arquitetura que devem ser diagnosticadas pelos alunos.
 */
public class ServicoAplicacaoHospital {
    public final RepositorioPacientesEmMemoria pacientes;
    public final RepositorioConsultasEmMemoria consultas;

    private final ServicoNotificacao notificacoes = new ServicoNotificacao();
    private final ApiLegadaPlanoSaude apiPlanoSaude = new ApiLegadaPlanoSaude();
    private final ClienteLabX clienteLab = new ClienteLabX();
    private final List<Internacao> internacoes = new ArrayList<>();

    public ServicoAplicacaoHospital(
            RepositorioPacientesEmMemoria pacientes,
            RepositorioConsultasEmMemoria consultas) {
        this.pacientes = pacientes;
        this.consultas = consultas;
    }

    public boolean agendar(Consulta consulta) {
        Paciente paciente = pacientes.buscar(consulta.idPaciente);
        if (paciente == null) {
            return false;
        }

        // Problema intencional: não verifica médico, conflito de horário ou duplicidade.
        consulta.status = "SCHEDULED";
        consultas.salvar(consulta);

        notificacoes.notificar(
                "EMAIL",
                paciente.email,
                "Consulta " + consulta.id + " agendada em " + consulta.dataHora);
        return true;
    }

    public boolean solicitarExame(SolicitacaoExame exam, double custoEstimado) {
        Paciente paciente = pacientes.buscar(exam.idPaciente);
        if (paciente == null) {
            return false;
        }

        // Problema intencional: o serviço conhece formato e regras da API externa.
        String autorizacao = apiPlanoSaude.autorizarProcedimento(
                paciente.id, exam.codigoExame, custoEstimado);
        exam.autorizado = autorizacao.endsWith("OK");

        if (exam.autorizado) {
            int codigoStatus = clienteLab.enviarExame(paciente.id, exam.codigoExame);
            exam.status = codigoStatus == 200 ? "SENT_TO_LAB" : "LAB_ERROR";
        } else {
            // MANUAL é tratado como simples negativa, perdendo um estado importante.
            exam.status = "DENIED";
        }

        notificacoes.notificar(
                "WHATSAPP",
                paciente.telefone,
                "Exame " + exam.id + " = " + exam.status);
        return exam.autorizado;
    }

    public Internacao internar(String id, String idPaciente, String quarto) {
        // Problema intencional: internação é criada sem validar paciente ou ocupação do quarto.
        Internacao internacao = new Internacao(id, idPaciente, quarto);
        internacoes.add(internacao);
        return internacao;
    }

    public List<Internacao> listarInternacoes() {
        return internacoes;
    }
}
