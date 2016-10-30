package br.com.crudPadroes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.crudPadroes.model.Pessoa;
import br.com.crudPadroes.util.ConnectionFactory;

public class PessoaDAOImpl implements PessoaDAO {

	Connection connection;
	public PessoaDAOImpl() throws Exception{
		connection = ConnectionFactory.getConnection();
		
	}
	
	
	
	@Override
	public void salvar(Pessoa pessoa) {
		PreparedStatement ps = null;
		
		
		
		try {
			String SQL = "INSERT INTO funcionarios (nome, endereco, telefone, email) VALUES" + "(?,?,?,?)";
			ps = connection.prepareStatement(SQL);
			
			ps.setString(1, pessoa.getNome());
			ps.setString(2, pessoa.getEndereco());
			ps.setString(3, pessoa.getTelefone());
			ps.setString(4, pessoa.getEmail());
			
			ps.executeUpdate();
			ps.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void remover(int id) {
		PreparedStatement ps = null;
		
		try {
			String SQL = "DELETE FROM funcionarios WHERE id= ?";
			ps = connection.prepareStatement(SQL);
			ps.setInt(1, id);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Pessoa> listar() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		try {
			String SQL = "SELECT * FROM funcionarios";
			ps = connection.prepareStatement(SQL);
			rs = ps.executeQuery();
			while (rs.next()){
				Pessoa pessoa = new Pessoa();
				pessoa.setId(rs.getInt("idfuncionarios"));
				pessoa.setNome(rs.getString("nome"));
				pessoa.setEndereco(rs.getString("endereco"));
				pessoa.setTelefone(rs.getString("telefone"));
				pessoa.setEmail(rs.getString("email"));
				pessoas.add(pessoa);
			}
			ps.close();
			rs.close();
			connection.close();
			return pessoas;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("falha ao listar pessoas", e);
			
		}
	}

	@Override
	public Pessoa buscar(int id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		try {
			Pessoa pessoa = new Pessoa();
			String SQL = "SELECT * FROM funcionarios WHERE id = ?";
			ps = connection.prepareStatement(SQL);
			rs = ps.executeQuery();
			
			rs.next();
			pessoa.setNome(rs.getString("nome"));
			pessoa.setEndereco(rs.getString("endereco"));
			pessoa.setTelefone(rs.getString("telefone"));
			pessoa.setEmail(rs.getString("email"));
			
			ps.close();
			rs.close();
			connection.close();
			return pessoa;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("erro ao buscar funcionario", e);
		}
		
		
		
	}

	@Override
	public void alterar(Pessoa pessoa) {
		PreparedStatement ps = null;
		
		
		try {
			String SQL = "UPDATE funcionarios SET nome=?, endereco=?, telefone=?, email= ? WHERE id=?";
			ps = connection.prepareStatement(SQL);
			ps.setString(1, pessoa.getNome());
			ps.setString(2, pessoa.getEndereco());
			ps.setString(3, pessoa.getTelefone());
			ps.setString(4, pessoa.getEmail());
			ps.setInt(5, pessoa.getId());
			ps.executeUpdate();
			ps.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
