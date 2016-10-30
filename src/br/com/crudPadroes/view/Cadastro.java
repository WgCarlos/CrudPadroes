package br.com.crudPadroes.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.crudPadroes.dao.PessoaDAO;
import br.com.crudPadroes.dao.PessoaDAOImpl;
import br.com.crudPadroes.model.Pessoa;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.table.DefaultTableModel;

public class Cadastro extends JFrame {

	private JPanel contentPane;
	private JTextField textNome;
	private JTextField textEnd;
	private JTextField textTelefone;
	private JTextField textEmail;
	private JTable table;
	private JButton btnListar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cadastro frame = new Cadastro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void SalvarFuncionario(){
		Pessoa pessoas = new Pessoa();
		pessoas.setNome(textNome.getText());
		pessoas.setEndereco(textEnd.getText());
		pessoas.setTelefone(textTelefone.getText());
		pessoas.setEmail(textEmail.getText());
		
		if ((textNome.getText().isEmpty()) || (textEnd.getText().isEmpty()) || (textTelefone.getText().isEmpty())){
			JOptionPane.showMessageDialog(null, "Campos Obrigatórios!");
		}else {
			try {
				PessoaDAOImpl dao = new PessoaDAOImpl();
				dao.salvar(pessoas);
				JOptionPane.showMessageDialog(null, "Funcionario " + textNome.getText() + " inserido com sucesso");
				textNome.setText("");
				textEnd.setText("");
				textTelefone.setText("");
				textEmail.setText("");
				CarregarTabela();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void CarregarTabela(){
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.setNumRows(0);
		try {
			PessoaDAOImpl dao = new PessoaDAOImpl();
			for(Pessoa p: dao.listar()){
				modelo.addRow(new Object[]{
						p.getId(),
						p.getNome(),
						p.getEndereco(),
						p.getTelefone(),
						p.getEmail()
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void AtualizarFuncionario(){
		
		if(table.getSelectedRow() != -1){
			Pessoa p = new Pessoa();
			
			try {
				PessoaDAOImpl dao = new PessoaDAOImpl();
				
				p.setNome(textNome.getText());
				p.setEndereco(textEnd.getText());
				p.setTelefone(textTelefone.getText());
				p.setEmail(textEmail.getText());
				p.setId((int)table.getValueAt(table.getSelectedRow(), 0));
				dao.alterar(p);
				
				
				textNome.setText("");
				textEnd.setText("");
				textTelefone.setText("");
				textEmail.setText("");
				
				CarregarTabela();
			} catch (Exception e) {
			}
		}
	}
	
	public void RemoverFuncionario(){
		if(table.getSelectedRow() != -1){
			Pessoa p = new Pessoa();
			try {
				PessoaDAOImpl dao = new PessoaDAOImpl();
				
				p.setId((int)table.getValueAt(table.getSelectedRow(), 0));
				dao.remover(p);
				CarregarTabela();
			} catch (Exception e) {
			}
		}
	}
	/**
	 * Create the frame.
	 */
	public Cadastro() {
		setTitle("Cadastro de usu\u00E1rio");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 496, 548);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCadastrarFuncionario = new JLabel("Cadastrar Funcionario");
		lblCadastrarFuncionario.setBounds(126, 11, 131, 14);
		contentPane.add(lblCadastrarFuncionario);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(33, 39, 46, 14);
		contentPane.add(lblNome);
		
		JLabel lblEndereo = new JLabel("Endere\u00E7o");
		lblEndereo.setBounds(33, 73, 71, 14);
		contentPane.add(lblEndereo);
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(33, 101, 235, 14);
		contentPane.add(lblTelefone);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(33, 132, 46, 14);
		contentPane.add(lblEmail);
		
		textNome = new JTextField();
		textNome.setBounds(114, 36, 202, 20);
		contentPane.add(textNome);
		textNome.setColumns(10);
		
		textEnd = new JTextField();
		textEnd.setBounds(114, 67, 202, 20);
		contentPane.add(textEnd);
		textEnd.setColumns(10);
		
		textTelefone = new JTextField();
		textTelefone.setBounds(115, 98, 142, 20);
		contentPane.add(textTelefone);
		textTelefone.setColumns(10);
		
		textEmail = new JTextField();
		textEmail.setBounds(114, 126, 202, 20);
		contentPane.add(textEmail);
		textEmail.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				SalvarFuncionario();
			}
		});
		btnCadastrar.setBounds(33, 408, 103, 23);
		contentPane.add(btnCadastrar);
		
		btnListar = new JButton("Listar");
		btnListar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CarregarTabela();
			}
		});
		btnListar.setBounds(169, 408, 89, 23);
		contentPane.add(btnListar);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Nome", "Endere\u00E7o", "Telefone", "Email"
			}
		));
		table.setBounds(59, 218, 353, 158);
		contentPane.add(table);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				RemoverFuncionario();
			}
		});
		btnRemover.setBounds(287, 408, 89, 23);
		contentPane.add(btnRemover);
		
		JButton btnAletrar = new JButton("Aletrar");
		btnAletrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				AtualizarFuncionario();
			}
		});
		btnAletrar.setBounds(381, 408, 89, 23);
		contentPane.add(btnAletrar);
		
		
	}
}
