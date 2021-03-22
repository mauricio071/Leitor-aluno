package br.com.cadastro.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import br.com.cadastro.dao.LeitorDAO;
import br.com.cadastro.model.Leitor;

public class TelaPrincipal extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JLabel lblNomeDoLeitor;
	private JLabel lblNewLabel_2;
	private JLabel lblMensagem;
	private JTextField txtCodLeitor;
	private JComboBox cmbTipoLeitor;
	private JButton btnNovo;
	private JButton btnSalvar;
	private JButton btnConsultar;
	private JButton btnAlterar;
	private JButton btnExcluir;
	private JButton btnListar;
	private JButton btnSair;
	private TextArea txtListar;
	private JTextField txtNomeLeitor;
	private Leitor leitor;
	private LeitorDAO dao;
	
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

	/**
	 * Create the frame.
	 */
	public TelaPrincipal() {
		setTitle("Manuten\u00E7\u00E3o Leitor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 778, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("C\u00F3digo do Leitor");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(29, 21, 131, 22);
		contentPane.add(lblNewLabel);
		
		lblNomeDoLeitor = new JLabel("Nome do Leitor");
		lblNomeDoLeitor.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNomeDoLeitor.setBounds(29, 74, 131, 22);
		contentPane.add(lblNomeDoLeitor);
		
		lblNewLabel_2 = new JLabel("Tipo de Leitor");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(29, 126, 131, 22);
		contentPane.add(lblNewLabel_2);
		
		lblMensagem = new JLabel("\r\n");
		lblMensagem.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(153, 180, 209), new Color(102, 153, 153), new Color(255, 255, 255), new Color(255, 255, 255)));
		lblMensagem.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMensagem.setBounds(29, 453, 704, 47);
		contentPane.add(lblMensagem);
		
		txtCodLeitor = new JTextField();
		txtCodLeitor.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtCodLeitor.setBounds(170, 11, 563, 42);
		contentPane.add(txtCodLeitor);
		txtCodLeitor.setColumns(10);
		
		cmbTipoLeitor = new JComboBox();
		cmbTipoLeitor.setModel(new DefaultComboBoxModel(new String[] {"Selecione uma op\u00E7\u00E3o", "Aluno", "Professor", "Administrativo"}));
		cmbTipoLeitor.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cmbTipoLeitor.setBounds(170, 116, 563, 42);
		contentPane.add(cmbTipoLeitor);
		
		btnNovo = new JButton("Novo\r\n");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtCodLeitor.setText(null);
				txtNomeLeitor.setText(null);
				txtListar.setText(null);
				cmbTipoLeitor.setSelectedIndex(0);
				lblMensagem.setText(null);
			}
		});
		btnNovo.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNovo.setBounds(29, 182, 89, 23);
		contentPane.add(btnNovo);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					leitor = new Leitor();
					leitor.setCodLeitor(Integer.parseInt(txtCodLeitor.getText()));
					leitor.setNomeLeitor(txtNomeLeitor.getText());
					leitor.setTipoLeitor((String) cmbTipoLeitor.getSelectedItem());
					dao = new LeitorDAO();
					dao.salvar(leitor);
					lblMensagem.setText("Salvo com Sucesso!");
				}catch(Exception e1) {
					lblMensagem.setText("Erro ao Salvar");
				}
			}
		});
		btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSalvar.setBounds(128, 182, 89, 23);
		contentPane.add(btnSalvar);
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblMensagem.setText(null);
				txtNomeLeitor.setText(null);
				cmbTipoLeitor.setSelectedIndex(0);
				try {
					dao = new LeitorDAO();
					int codigo = Integer.parseInt(txtCodLeitor.getText());
					leitor = dao.consultar(codigo);
					txtNomeLeitor.setText(leitor.getNomeLeitor());
					
					String tipo = leitor.getTipoLeitor();
					
					if(tipo.equals("Aluno")) 
						cmbTipoLeitor.setSelectedIndex(1);
					else 
						if(tipo.equals("Professor"))
						cmbTipoLeitor.setSelectedIndex(2);
					else 
						cmbTipoLeitor.setSelectedIndex(3);	
				}catch(Exception e1) {
					lblMensagem.setText("Erro ao Consultar");
				}
			}
		});
		btnConsultar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnConsultar.setBounds(227, 182, 110, 23);
		contentPane.add(btnConsultar);
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					leitor = new Leitor();
					leitor.setCodLeitor(Integer.parseInt(txtCodLeitor.getText()));
					leitor.setNomeLeitor(txtNomeLeitor.getText());
					leitor.setTipoLeitor((String) cmbTipoLeitor.getSelectedItem());
					dao = new LeitorDAO();
					dao.alterar(leitor);
					lblMensagem.setText("Alterado com Sucesso!");
				}catch(Exception e1) {
					lblMensagem.setText("Erro ao Alterar");
				}
			}
		});
		btnAlterar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAlterar.setBounds(347, 182, 89, 23);
		contentPane.add(btnAlterar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dao = new LeitorDAO();
					int codigo = Integer.parseInt(txtCodLeitor.getText()); 
					dao.excluir(codigo);
					lblMensagem.setText("Excluido com Sucesso!");
				}catch(Exception e1) {
					lblMensagem.setText("Erro ao Excluir");
				}
			}
		});
		btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnExcluir.setBounds(446, 182, 89, 23);
		contentPane.add(btnExcluir);
		
		btnListar = new JButton("Listar");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtListar.setText(null);
				try {
					List<Leitor> lista = new ArrayList<Leitor>();
					dao = new LeitorDAO();
					lista = dao.listarTodos();
					for(Leitor leitor : lista) {
						txtListar.append("Código do Leitor.... " + leitor.getCodLeitor() + "\n");
						txtListar.append("Nome do Leitor.... " + leitor.getNomeLeitor() + "\n");
						txtListar.append("Tipo de Leitor.... " + leitor.getTipoLeitor() + "\n\n");
					}
				}catch(Exception e1) {
					lblMensagem.setText("Erro ao Listar");
				}
			}
		});
		btnListar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnListar.setBounds(545, 182, 89, 23);
		contentPane.add(btnListar);
		
		btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Encerrando o programa");
				System.exit(0);
			}
		});
		btnSair.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSair.setBounds(644, 182, 89, 23);
		contentPane.add(btnSair);
		
		txtListar = new TextArea();
		txtListar.setBounds(29, 222, 704, 225);
		contentPane.add(txtListar);
		
		txtNomeLeitor = new JTextField();
		txtNomeLeitor.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtNomeLeitor.setColumns(10);
		txtNomeLeitor.setBounds(170, 64, 563, 42);
		contentPane.add(txtNomeLeitor);
	}
}
