package br.edu.mediconnect;

import br.edu.mediconnect.modelo.Consulta;
import br.edu.mediconnect.modelo.SolicitacaoExame;
import br.edu.mediconnect.modelo.Paciente;
import br.edu.mediconnect.repositorio.RepositorioConsultasEmMemoria;
import br.edu.mediconnect.repositorio.RepositorioPacientesEmMemoria;
import br.edu.mediconnect.servico.ServicoAplicacaoHospital;

public class Main {
    public static void main(String[] args) {
        RepositorioPacientesEmMemoria pacientes = new RepositorioPacientesEmMemoria();
        RepositorioConsultasEmMemoria consultas = new RepositorioConsultasEmMemoria();
        ServicoAplicacaoHospital hospital =
                new ServicoAplicacaoHospital(pacientes, consultas);

        pacientes.salvar(new Paciente(
                "P1",
                "Ana",
                "PLAN-A",
                "62999999999",
                "ana@example.com"));

        Consulta consulta = new Consulta(
                "A1",
                "P1",
                "D1",
                "2026-08-20T19:00",
                "CONSULTATION");

        System.out.println(
                "Agendada: " + hospital.agendar(consulta)
                        + " status=" + consulta.status);

        SolicitacaoExame exam = new SolicitacaoExame("E1", "P1", "HEMOGRAMA");
        System.out.println(
                "Exame autorizado: " + hospital.solicitarExame(exam, 150)
                        + " status=" + exam.status);
    }
}
