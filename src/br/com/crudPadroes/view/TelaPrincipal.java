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
import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;

public class TelaPrincipal extends JFrame {

	private JPanel contentPane;
	private JTextField textNome;
	private JTextField textEnd;
	private JTextField textTelefone;
	private JTextField textEmail;
	private JTable table;
	private JButton btnListar;
	private JTextField textCpf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void SalvarFuncionario(){
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(textNome.getText());
		pessoa.setCpf(textCpf.getText());
		pessoa.setEndereco(textEnd.getText());
		pessoa.setTelefone(textTelefone.getText());
		pessoa.setEmail(textEmail.getText());
		
		if ((textNome.getText().isEmpty()) || (textEnd.getText().isEmpty()) || (textTelefone.getText().isEmpty()) || (textCpf.getText().isEmpty())){
			JOptionPane.showMessageDialog(null, "Campos Obrigatórios!");
		}else {
			try {
				PessoaDAOImpl dao = new PessoaDAOImpl();
				dao.salvar(pessoa);
				JOptionPane.showMessageDialog(null, "Funcionario " + textNome.getText() + " inserido com sucesso");
				
				Limpar();
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
			for(Pessoa pessoa: dao.listar()){
				modelo.addRow(new Object[]{
						pessoa.getId(),
						pessoa.getNome(),
						pessoa.getCpf(),
						pessoa.getEndereco(),
						pessoa.getTelefone(),
						pessoa.getEmail()
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void AtualizarFuncionario(){
		
			Pessoa pessoa = new Pessoa();
			
			try {
				PessoaDAOImpl dao = new PessoaDAOImpl();
				
				pessoa.setNome(textNome.getText());
				pessoa.setCpf(textCpf.getText());
				pessoa.setEndereco(textEnd.getText());
				pessoa.setTelefone(textTelefone.getText());
				pessoa.setEmail(textEmail.getText());
				pessoa.setId((int)table.getValueAt(table.getSelectedRow(), 0));
				dao.alterar(pessoa);
				JOptionPane.showMessageDialog(null, "Funcionario alterado com sucesso");
				
				Limpar();
				CarregarTabela();
			} catch (Exception e) {
			}
	}
	
	public void RemoverFuncionario(){
			Pessoa pessoa = new Pessoa();
			try {
				PessoaDAOImpl dao = new PessoaDAOImpl();
				
				pessoa.setId((int)table.getValueAt(table.getSelectedRow(), 0));
				int op = (JOptionPane.showConfirmDialog(null, "Deseja excluir funcionario?"));
				if (op== JOptionPane.YES_OPTION){
					dao.remover(pessoa);
				}
				
				Limpar();
				CarregarTabela();
			} catch (Exception e) {
			}
	}
	
	public void Limpar(){
		textNome.setText("");
		textCpf.setText("");
		textEnd.setText("");
		textTelefone.setText("");
		textEmail.setText("");
	}
	/**
	 * Create the frame.
	 */
	public TelaPrincipal() {
		setResizable(false);
		setTitle("Gerenciamento de funcion\u00E1rio");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 496, 548);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCadastrarFuncionario = new JLabel("Cadastrar Funcionario");
		lblCadastrarFuncionario.setBounds(192, 11, 131, 14);
		contentPane.add(lblCadastrarFuncionario);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(98, 57, 46, 14);
		contentPane.add(lblNome);
		
		JLabel lblEndereo = new JLabel("Endere\u00E7o");
		lblEndereo.setBounds(98, 122, 71, 14);
		contentPane.add(lblEndereo);
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(98, 149, 71, 14);
		contentPane.add(lblTelefone);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(98, 180, 46, 14);
		contentPane.add(lblEmail);
		
		textNome = new JTextField();
		textNome.setBounds(179, 54, 202, 20);
		contentPane.add(textNome);
		textNome.setColumns(10);
		
		textEnd = new JTextField();
		textEnd.setBounds(179, 116, 202, 20);
		contentPane.add(textEnd);
		textEnd.setColumns(10);
		
		textTelefone = new JTextField();
		textTelefone.setToolTipText("");
		textTelefone.setBounds(179, 146, 202, 20);
		contentPane.add(textTelefone);
		textTelefone.setColumns(10);
		
		textEmail = new JTextField();
		textEmail.setBounds(179, 174, 202, 20);
		contentPane.add(textEmail);
		textEmail.setColumns(10);
		
		JButton btnCadastrar = new JButton("");
		btnCadastrar.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/br/com/crudPadroes/icons/save.png")));
		btnCadastrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				SalvarFuncionario();
			}
		});
		btnCadastrar.setBounds(43, 423, 40, 40);
		contentPane.add(btnCadastrar);
		
		btnListar = new JButton("");
		btnListar.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/br/com/crudPadroes/icons/list.png")));
		btnListar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CarregarTabela();
			}
		});
		btnListar.setBounds(142, 423, 40, 40);
		contentPane.add(btnListar);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int IndiceLinha = table.getSelectedRow();
				textNome.setText(table.getValueAt(IndiceLinha, 1).toString());
				textCpf.setText(table.getValueAt(IndiceLinha, 2).toString());
				textEnd.setText(table.getValueAt(IndiceLinha, 3).toString());
				textTelefone.setText(table.getValueAt(IndiceLinha, 4).toString());
				textEmail.setText(table.getValueAt(IndiceLinha, 5).toString());
						
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Nome", "Cpf", "Endere\u00E7o", "Telefone", "Email"
			}
		));
		table.setBounds(10, 218, 470, 158);
		contentPane.add(table);
		
		JButton btnRemover = new JButton("");
		btnRemover.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/br/com/crudPadroes/icons/remove.png")));
		btnRemover.setSelectedIcon(new ImageIcon(TelaPrincipal.class.getResource("/br/com/crudPadroes/icons/remove.png")));
		btnRemover.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				RemoverFuncionario();
			}
		});
		btnRemover.setBounds(385, 423, 40, 40);
		contentPane.add(btnRemover);
		
		JButton btnAletrar = new JButton("");
		btnAletrar.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/br/com/crudPadroes/icons/edit1.png")));
		btnAletrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				AtualizarFuncionario();
			}
		});
		btnAletrar.setBounds(263, 418, 45, 45);
		contentPane.add(btnAletrar);
		
		JButton btnLimpar = new JButton("");
		btnLimpar.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/br/com/crudPadroes/icons/clear.png")));
		btnLimpar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Limpar();
			}
		});
		btnLimpar.setBounds(423, 22, 40, 40);
		contentPane.add(btnLimpar);
		
		JLabel lblNewLabel = new JLabel("Cpf");
		lblNewLabel.setBounds(98, 88, 46, 14);
		contentPane.add(lblNewLabel);
		
		textCpf = new JTextField();
		textCpf.setBounds(179, 85, 202, 20);
		contentPane.add(textCpf);
		textCpf.setColumns(10);
		
		
	}
}
