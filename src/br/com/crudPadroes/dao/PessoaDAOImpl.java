package br.com.crudPadroes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.crudPadroes.model.Pessoa;
import br.com.crudPadroes.util.ConnectionFactory;

public class PessoaDAOImpl extends ConnectionFactory implements PessoaDAO {
	
private static final String TABLE = "funcionarios";
private static final String COLUMNS = "(nome, cpf, endereco, telefone, email)";

	
	@Override
	public void salvar(Pessoa pessoa) {
		Connection con = null;
		PreparedStatement ps = null;
		StringBuilder sql = new StringBuilder();
		
		sql.append("INSERT INTO ");
		sql.append(TABLE);
		sql.append(" ");
		sql.append(COLUMNS);
		sql.append(" values (?,?,?,?,?) ");
		
		try {
			con = getConnection();
			ps = con.prepareStatement(sql.toString());
			
			ps.setString(1, pessoa.getNome());
			ps.setString(2, pessoa.getCpf());
			ps.setString(3, pessoa.getEndereco());
			ps.setString(4, pessoa.getTelefone());
			ps.setString(5, pessoa.getEmail());
			
			ps.executeUpdate();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void remover(Pessoa p) {
		Connection con = null;
		PreparedStatement ps = null;
		StringBuilder sql = new StringBuilder();
		
		sql.append("DELETE FROM ");
		sql.append(TABLE);
		sql.append(" WHERE idfuncionarios = ?");
		
		try {
			con = getConnection();
			ps = con.prepareStatement(sql.toString());
			ps.setInt(1, p.getId());
			ps.executeUpdate();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Pessoa> listar() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder();
		
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		
		sql.append("SELECT * FROM ");
		sql.append(TABLE);
		
		try {
			con = getConnection();
			ps = con.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			while (rs.next()){
				Pessoa pessoa = new Pessoa();
				pessoa.setId(rs.getInt("idfuncionarios"));
				pessoa.setNome(rs.getString("nome"));
				pessoa.setCpf(rs.getString("cpf"));
				pessoa.setEndereco(rs.getString("endereco"));
				pessoa.setTelefone(rs.getString("telefone"));
				pessoa.setEmail(rs.getString("email"));
				pessoas.add(pessoa);
			}
			ps.close();
			rs.close();
			con.close();
			return pessoas;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("falha ao listar pessoas", e);
			
		}
	}

	@Override
	public Pessoa buscar(int id) {
		Connection con = null;		
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * FROM ");
		sql.append(TABLE);
		sql.append(" ");
		sql.append("WHERE idfuncionarios = ?");
		try {
			con = getConnection();
			Pessoa pessoa = new Pessoa();
			ps = con.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			
			rs.next();
			pessoa.setNome(rs.getString("nome"));
			pessoa.setEndereco(rs.getString("endereco"));
			pessoa.setTelefone(rs.getString("telefone"));
			pessoa.setEmail(rs.getString("email"));
			
			ps.close();
			rs.close();
			con.close();
			return pessoa;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("erro ao buscar funcionario", e);
		}
		
		
		
	}

	@Override
	public void alterar(Pessoa pessoa) {
		Connection con = null;
		PreparedStatement ps = null;
		StringBuilder sql = new StringBuilder();
		
		sql.append("UPDATE ");
		sql.append(TABLE);
		sql.append(" ");
		sql.append("SET nome=?, cpf=?, endereco=?, telefone=?, email=? where idfuncionarios=?");
		
		
		try {
			con = getConnection();
			ps = con.prepareStatement(sql.toString());
			ps.setString(1, pessoa.getNome());
			ps.setString(2, pessoa.getCpf());
			ps.setString(3, pessoa.getEndereco());
			ps.setString(4, pessoa.getTelefone());
			ps.setString(5, pessoa.getEmail());
			ps.setInt(6, pessoa.getId());
			ps.executeUpdate();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
