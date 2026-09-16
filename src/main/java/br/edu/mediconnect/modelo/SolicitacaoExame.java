package br.edu.mediconnect.modelo;
public class SolicitacaoExame { public String id; public String idPaciente; public String codigoExame; public String status="REQUESTED"; public boolean autorizado; public SolicitacaoExame(String id,String p,String codigo){this.id=id;this.idPaciente=p;this.codigoExame=codigo;} }
