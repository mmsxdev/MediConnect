package br.edu.mediconnect.legado;
public class ApiLegadaPlanoSaude { public String autorizarProcedimento(String beneficiario,String procedimento,double valor){return beneficiario+";"+procedimento+";"+(valor<5000?"OK":"MANUAL");} }
