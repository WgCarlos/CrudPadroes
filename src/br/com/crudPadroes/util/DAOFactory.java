package br.com.crudPadroes.util;

import br.com.crudPadroes.dao.PessoaDAO;
import br.com.crudPadroes.dao.PessoaDAOImpl;

public class DAOFactory {
	public static PessoaDAO criarPessoaDAO() throws Exception{
		return new PessoaDAOImpl();
	}
}
