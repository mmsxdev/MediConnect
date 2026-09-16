package br.edu.mediconnect.repositorio; import br.edu.mediconnect.modelo.Consulta; import java.util.*;
public class RepositorioConsultasEmMemoria { private final Map<String,Consulta> dados=new LinkedHashMap<>(); public void salvar(Consulta a){dados.put(a.id,a);} public Consulta buscar(String id){return dados.get(id);} public Collection<Consulta> listarTodos(){return dados.values();} }
