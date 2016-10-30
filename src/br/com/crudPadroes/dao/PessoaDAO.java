package br.com.crudPadroes.dao;

import java.util.List;

import br.com.crudPadroes.model.Pessoa;

public interface PessoaDAO {
	
	public void salvar (Pessoa pessoa);
	public void remover(Pessoa p);
	public List<Pessoa> listar();
	public Pessoa buscar (int id);
	public void alterar(Pessoa pessoa);
}
