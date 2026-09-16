package br.edu.mediconnect.modelo;
public class Consulta { public String id; public String idPaciente; public String idMedico; public String dataHora; public String tipo; public String status="CREATED"; public String prioridade="NORMAL"; public Consulta(String id,String p,String d,String dt,String tipo){this.id=id;this.idPaciente=p;this.idMedico=d;this.dataHora=dt;this.tipo=tipo;} }
