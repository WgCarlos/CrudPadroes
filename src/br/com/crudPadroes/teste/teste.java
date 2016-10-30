package br.com.crudPadroes.teste;

import java.sql.Connection;
import java.util.List;

import br.com.crudPadroes.dao.PessoaDAO;
import br.com.crudPadroes.model.Pessoa;
import br.com.crudPadroes.util.ConnectionFactory;
import br.com.crudPadroes.util.DAOFactory;

public class teste {

	public static void main(String[] args) throws Exception {
		
		PessoaDAO pd = DAOFactory.criarPessoaDAO();
		List<Pessoa> pessoas = pd.listar();
		for (Pessoa pessoa : pessoas) {
			System.out.println("nome :" + pessoa.getNome());
			System.out.println("endereco :" + pessoa.getEndereco());
			System.out.println("telefone :" + pessoa.getTelefone());
			System.out.println("email :" + pessoa.getEmail());
		}
		
	}

}
