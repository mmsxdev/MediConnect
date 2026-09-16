package br.edu.mediconnect.legado;
public class ClienteLabX { public int enviarExame(String paciente,String codigo){return paciente!=null && codigo!=null?200:400;} public String resultado(String protocol){return "RESULT|"+protocol+"|NORMAL";} }
