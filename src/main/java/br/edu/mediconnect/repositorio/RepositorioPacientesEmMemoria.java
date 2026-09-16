package br.edu.mediconnect.repositorio; import br.edu.mediconnect.modelo.Paciente; import java.util.*;
public class RepositorioPacientesEmMemoria { private final Map<String,Paciente> dados=new HashMap<>(); public void salvar(Paciente p){dados.put(p.id,p);} public Paciente buscar(String id){return dados.get(id);} }
