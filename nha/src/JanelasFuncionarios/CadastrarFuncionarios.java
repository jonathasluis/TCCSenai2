package JanelasFuncionarios;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import DAO.Funcionario;
import Imagem.MetodosImagem;
import JanelasAnimal.CadastrarAnimais;
import JanelasComtabil.NovaCompra;
import JanelasComtabil.NovaVenda;
import JanelasComtabil.Total;
import banco.Conexao;
import crud.CrudFazenda;
import crud.CrudFuncionarios;
import outraJanelas.Login;
import outraJanelas.NovaFazenda;
import outraJanelas.Pergunta;
import outraJanelas.Principal;

public class CadastrarFuncionarios {

	private JFrame frmCadastrarFuncionarios;
	public  File img;
	private JTextField tfProcurar;
	private JTable tabela;
	private JTextField tfNome;
	private JTextField tfRg;
	private JRadioButton rdbtnMasculino;
	private JFormattedTextField ftfNascimento;
	private JTextField tfEmail;
	private JTextField tfCargo;
	private JTextField tfSalario;
	private JFormattedTextField ftfTelefone;
	private JButton btnLimpar;
	private JPanel panel;
	private JLabel lblImagem;
	private JButton btnProcurar;
	private JButton btnDeletar;
	private JButton btnCancelar;
	private JFormattedTextField ftfCpf;
	private MetodosImagem mI = new MetodosImagem();
	private MaskFormatter maskaraData, maskaraTelefone,maskaraCpf,maskaraTrabalho,maskaraPis;
	private JRadioButton rdbtnFeminino;
	private JRadioButton rdbtnAtivo;
	private JRadioButton rdbtnDesligado;
	private CrudFuncionarios funcionario = new CrudFuncionarios();
	private Funcionario DAOFuncionario = new Funcionario();
	int contadorParaEditar = 0;
	private JScrollPane scrollPane;
	private JFormattedTextField ftfAdimissao;
	private JFormattedTextField ftfDemissao;
	private JFormattedTextField ftfCarteira;
	private JFormattedTextField ftfPis;
	
	//tabela
		static int teste = 1; 
		static int x1=1;
		private JLabel lblSemdados;
		//tabela
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastrarFuncionarios window = new CadastrarFuncionarios();
					window.frmCadastrarFuncionarios.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CadastrarFuncionarios() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		try {//inicio formatação mascara
			maskaraData = new MaskFormatter("####-##-##");
			maskaraTelefone = new MaskFormatter("(##) ####-####");
			maskaraCpf = new MaskFormatter("###.###.###-##");
			maskaraTrabalho = new MaskFormatter("######");
			maskaraPis = new MaskFormatter("###########");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//fim formatação mascara
		
		frmCadastrarFuncionarios = new JFrame();
		frmCadastrarFuncionarios.setIconImage(Toolkit.getDefaultToolkit().getImage(CadastrarFuncionarios.class.getResource("/libs/img/libs/img/32x32.png")));
		frmCadastrarFuncionarios.setTitle("Cadastrar Funcionarios");
		frmCadastrarFuncionarios.setBounds(100, 100, 1080, 720);
		frmCadastrarFuncionarios.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmCadastrarFuncionarios.setLocationRelativeTo(null);
		frmCadastrarFuncionarios.setResizable(false);
		frmCadastrarFuncionarios.getContentPane().setLayout(null);
		
		JLabel lblFuncionarios = new JLabel("Funcionarios");
		lblFuncionarios.setHorizontalAlignment(SwingConstants.CENTER);
		lblFuncionarios.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblFuncionarios.setBounds(10, 11, 1054, 25);
		frmCadastrarFuncionarios.getContentPane().add(lblFuncionarios);
		
		panel = new JPanel();
		panel.setOpaque(false);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(774, 60, 290, 182);
		frmCadastrarFuncionarios.getContentPane().add(panel);
		panel.setLayout(new CardLayout(0, 0));
		
		lblImagem = new JLabel("");
		lblImagem.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblImagem.setIcon(new ImageIcon(CadastrarFuncionarios.class.getResource("/libs/img/libs/img/128x128.png")));
		lblImagem.setToolTipText("Clique 2 vezes");
		lblImagem.addMouseListener(new MouseAdapter() {//inicio evento para escolher e abrir imagem
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getClickCount()==2) {
					img = mI.selecionaImg();
					if(img != null) {
						lblImagem.setHorizontalAlignment(SwingConstants.LEADING);
					}
					mI.abrirImagem(img, img, panel, lblImagem,null);
				}
			}// fim evento para escolher e abrir imagem
		});
		lblImagem.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblImagem, "name_12082521761208");
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		scrollPane.setBounds(10, 253, 1054, 42);
		frmCadastrarFuncionarios.getContentPane().add(scrollPane);
		
		tabela = new JTable();
		tabela.setSelectionBackground(new Color(135, 206, 250));
		tabela.setGridColor(SystemColor.activeCaption);
		tabela.setBackground(new Color(245, 245, 245));
		tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabela.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {//evento de clique na tabela
				lblImagem.setIcon(null);
				contadorParaEditar=1;
				btnLimpar.setEnabled(false);
				btnDeletar.setEnabled(true);
				pegaDadosDaTabela();
			}
		});
		tabela.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"ID", "Nome", "Nascimento", "CPF", "RG", "Sexo", "Telefone", "Email", "Cargo", "Salario", "Fazenda", "Status", "Data Admiss\u00E3o", "Data Demiss\u00E3o", "N\u00B0 Carteira", "PIS"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tabela.getColumnModel().getColumn(0).setResizable(false);
		tabela.getColumnModel().getColumn(0).setPreferredWidth(70);
		tabela.getColumnModel().getColumn(1).setResizable(false);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(100);
		tabela.getColumnModel().getColumn(2).setResizable(false);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(83);
		tabela.getColumnModel().getColumn(3).setResizable(false);
		tabela.getColumnModel().getColumn(3).setPreferredWidth(95);
		tabela.getColumnModel().getColumn(4).setResizable(false);
		tabela.getColumnModel().getColumn(4).setPreferredWidth(85);
		tabela.getColumnModel().getColumn(5).setResizable(false);
		tabela.getColumnModel().getColumn(5).setPreferredWidth(80);
		tabela.getColumnModel().getColumn(6).setResizable(false);
		tabela.getColumnModel().getColumn(6).setPreferredWidth(90);
		tabela.getColumnModel().getColumn(7).setResizable(false);
		tabela.getColumnModel().getColumn(7).setPreferredWidth(120);
		tabela.getColumnModel().getColumn(8).setResizable(false);
		tabela.getColumnModel().getColumn(8).setPreferredWidth(90);
		tabela.getColumnModel().getColumn(9).setResizable(false);
		tabela.getColumnModel().getColumn(9).setPreferredWidth(80);
		tabela.getColumnModel().getColumn(10).setResizable(false);
		tabela.getColumnModel().getColumn(10).setPreferredWidth(90);
		tabela.getColumnModel().getColumn(11).setResizable(false);
		tabela.getColumnModel().getColumn(11).setPreferredWidth(70);
		tabela.getColumnModel().getColumn(12).setResizable(false);
		tabela.getColumnModel().getColumn(13).setResizable(false);
		tabela.getColumnModel().getColumn(14).setResizable(false);
		tabela.getColumnModel().getColumn(15).setResizable(false);
		tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabela.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(tabela);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tfNome.getText().trim().equals("")) { //inicio do tratamento de informação para salvar novo funcionario
					JOptionPane.showMessageDialog(null, "insira um nome", "ALERTA!",JOptionPane.WARNING_MESSAGE);
					tfNome.requestFocus();
					return;
				}
				if(ftfNascimento.getText().contains(" ")){
					JOptionPane.showMessageDialog(null, "insira uma Data de Nascimento", "ALERTA!",JOptionPane.WARNING_MESSAGE);
					ftfNascimento.requestFocus();
					return;
				}
				if(ftfCpf.getText().contains(" ")) {
					JOptionPane.showMessageDialog(null, "insira o CPF", "ALERTA!",JOptionPane.WARNING_MESSAGE);
					ftfCpf.requestFocus();
					return;
				}
				if(tfRg.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "insira o RG", "ALERTA!",JOptionPane.WARNING_MESSAGE);
					tfRg.requestFocus();
					return;
				}
				if(tfCargo.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "insira o cargo", "ALERTA!",JOptionPane.WARNING_MESSAGE);
					tfCargo.requestFocus();
					return;
				}
				if(tfSalario.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "insira o salario", "ALERTA!",JOptionPane.WARNING_MESSAGE);
					tfSalario.requestFocus();
					return;
				}
				if(ftfAdimissao.getText().contains(" ")){
					JOptionPane.showMessageDialog(null, "insira uma Data de Admissão", "ALERTA!",JOptionPane.WARNING_MESSAGE);
					ftfAdimissao.requestFocus();
					return;
				}
				if(ftfCarteira.getText().contains(" ")){
					JOptionPane.showMessageDialog(null, "insira o numero da carteira", "ALERTA!",JOptionPane.WARNING_MESSAGE);
					ftfCarteira.requestFocus();
					return;
				}
				if(ftfPis.getText().contains(" ")){
					JOptionPane.showMessageDialog(null, "insira o numero do PIS", "ALERTA!",JOptionPane.WARNING_MESSAGE);
					ftfPis.requestFocus();
					return;
				}
				if (rdbtnDesligado.isSelected() && ftfDemissao.getText().contains(" ")) {
					JOptionPane.showMessageDialog(null, "insira a data de Demissão", "ALERTA!",JOptionPane.WARNING_MESSAGE);
					ftfDemissao.requestFocus();
					return;
				}
				//fim do tratamento de informação para salvar novo funcionario
				
				preencherDAOFuncionarioParaSalvarNovo();
				if(contadorParaEditar==0) {
					//tabela
					x1=0;
					if (scrollPane.getHeight()<=315) {
						lblSemdados.setVisible(false);
						int x = (teste*18)+scrollPane.getHeight();
						scrollPane.setBounds(10, 253, 1054, x);
					}
					//tabela
					funcionario.addFun(DAOFuncionario);
					btnLimpar.doClick();
					JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
				}
				if(contadorParaEditar==1) {
					//tabela
					x1=0;
					//tabela
					int resposta = JOptionPane.showConfirmDialog(null, "voce deseja alterar esse Funcionario? ", "alerta",
							JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
					if(resposta==JOptionPane.YES_OPTION) {
						funcionario.updFun(DAOFuncionario);
						btnCancelar.doClick();
						JOptionPane.showMessageDialog(null, "Funcionario alterado com sucesso!");
					}else {
						return;
					}
					
				}
				colocaDadosNaTabela(CrudFuncionarios.selecionaFuncionario(DAOFuncionario));
			}
		});
		btnSalvar.setBackground(new Color(245, 245, 245));
		btnSalvar.setBounds(975, 602, 89, 23);
		frmCadastrarFuncionarios.getContentPane().add(btnSalvar);
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {//evento do botao limpar
				tfNome.setText(null);
				ftfNascimento.setValue(null);
				ftfCpf.setValue(null);
				tfRg.setText(null);
				rdbtnMasculino.setSelected(true);
				ftfTelefone.setValue(null);
				tfCargo.setText(null);
				tfSalario.setText(null);
				tfEmail.setText(null);
				rdbtnAtivo.setSelected(true);
				lblImagem.setIcon(new ImageIcon(CadastrarFuncionarios.class.getResource("/img/128x128.png")));
				lblImagem.setHorizontalAlignment(SwingConstants.CENTER);
				ftfAdimissao.setValue(null);
				ftfDemissao.setValue(null);
				ftfCarteira.setValue(null);
				ftfPis.setValue(null);
				img=null;
			}
		});//fim evento botao limpar
		btnLimpar.setBackground(new Color(245, 245, 245));
		btnLimpar.setBounds(680, 602, 89, 23);
		frmCadastrarFuncionarios.getContentPane().add(btnLimpar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnCancelar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {//inicio do evento do botao cancelar
				if(contadorParaEditar==0) {
					Principal.frmPrincipal.setVisible(true);
					frmCadastrarFuncionarios.dispose();
				}
				if(contadorParaEditar==1) {
					contadorParaEditar=0;
					btnDeletar.setEnabled(false);
					btnLimpar.setEnabled(true);
					btnLimpar.doClick();
				}
			}
		});//fim do evento do botao cancelar
		btnCancelar.setBackground(new Color(245, 245, 245));
		btnCancelar.setBounds(776, 602, 89, 23);
		frmCadastrarFuncionarios.getContentPane().add(btnCancelar);
		
		btnDeletar = new JButton("Deletar");//inicio do evento do botao deletar
		btnDeletar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int resposta = JOptionPane.showConfirmDialog(null, "Deseja deletar esse dado?", "ALERTA!",
						JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
				if(resposta==JOptionPane.YES_OPTION) {
					funcionario.removeFun(DAOFuncionario);
					btnCancelar.doClick();
					colocaDadosNaTabela(CrudFuncionarios.selecionaFuncionario(DAOFuncionario));
					//tabela
					x1=0;
					if (tabela.getRowCount()<=18) {
						int x = (scrollPane.getHeight()-16);
						scrollPane.setBounds(10, 253, 1054, x);
					}
					//tabela
					if (tabela.getRowCount()== 0) {
						scrollPane.setVisible(false);
						lblSemdados.setVisible(true);
					}
				}
			}
		});//fim do evento do botao deletar
		btnDeletar.setEnabled(false);
		btnDeletar.setBackground(new Color(245, 245, 245));
		btnDeletar.setBounds(876, 602, 89, 23);
		frmCadastrarFuncionarios.getContentPane().add(btnDeletar);
		
		tfProcurar = new JTextField();
		tfProcurar.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.GRAY, Color.GRAY, Color.DARK_GRAY));
		tfProcurar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					btnProcurar.doClick();
				}
			}
		});
		tfProcurar.setColumns(10);
		tfProcurar.setBounds(10, 222, 331, 20);
		frmCadastrarFuncionarios.getContentPane().add(tfProcurar);
		
		btnProcurar = new JButton("Procurar");
		btnProcurar.setBorder(new BevelBorder(BevelBorder.RAISED, Color.WHITE, null, null, null));
		btnProcurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DAOFuncionario.setIdFazenda(Principal.fazenda.getIdFazenda());
				//variavel para delimitar o tamanho da tabela
				x1=0;
				if (!(tabela.getRowCount()==0)) {
				//tratamento para almentar a tabela
				colocaDadosNaTabela(CrudFuncionarios.procurafuncionario(tfProcurar.getText(),DAOFuncionario ));	
				int tabel = tabela.getRowCount();
				if (tabela.getRowCount()>19) {
					tabel=18;
				}
				int linha = tabel*15;
				int valor = 46+linha;
				scrollPane.setBounds(10, 253, 1054, valor);
				if (!(tfProcurar.getText()).trim().equals("")) {
					if (tabela.getRowCount()==0) {
						lblSemdados.setVisible(true);
						int x2 = 23;
						scrollPane.setBounds(10, 253, 1054, x2);
							new CrudFuncionarios();
							colocaDadosNaTabela(CrudFuncionarios.selecionaFuncionario(DAOFuncionario));
					
						
					}
				}
					
				}
			}
		});
		btnProcurar.setBackground(new Color(245, 245, 245));
		btnProcurar.setBounds(351, 222, 89, 23);
		frmCadastrarFuncionarios.getContentPane().add(btnProcurar);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNome.setBounds(10, 60, 60, 20);
		frmCadastrarFuncionarios.getContentPane().add(lblNome);
		
		JLabel lblNascimento = new JLabel("Nascimento:");
		lblNascimento.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNascimento.setBounds(10, 91, 80, 20);
		frmCadastrarFuncionarios.getContentPane().add(lblNascimento);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCpf.setBounds(10, 122, 60, 20);
		frmCadastrarFuncionarios.getContentPane().add(lblCpf);
		
		JLabel lblRg = new JLabel("RG:");
		lblRg.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblRg.setBounds(10, 153, 60, 20);
		frmCadastrarFuncionarios.getContentPane().add(lblRg);
		
		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSexo.setBounds(10, 184, 60, 20);
		frmCadastrarFuncionarios.getContentPane().add(lblSexo);
		
		tfNome = new JTextField();
		tfNome.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.GRAY, Color.GRAY, Color.DARK_GRAY));
		tfNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					ftfNascimento.requestFocus();
				}
			}
		});
		tfNome.setColumns(10);
		tfNome.setBounds(89, 60, 150, 20);
		frmCadastrarFuncionarios.getContentPane().add(tfNome);
		
		tfRg = new JTextField();
		tfRg.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.GRAY, Color.GRAY, Color.DARK_GRAY));
		tfRg.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					ftfTelefone.requestFocus();
				}
			}
		});
		tfRg.setColumns(10);
		tfRg.setBounds(89, 153, 150, 20);
		frmCadastrarFuncionarios.getContentPane().add(tfRg);
		
		ftfNascimento = new JFormattedTextField(maskaraData);
		ftfNascimento.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.GRAY, Color.GRAY, Color.DARK_GRAY));
		ftfNascimento.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					ftfCpf.requestFocus();
				}
			}
		});
		ftfNascimento.setToolTipText("aaaa/mm/dd");
		ftfNascimento.setBounds(89, 91, 150, 20);
		frmCadastrarFuncionarios.getContentPane().add(ftfNascimento);
		
		rdbtnMasculino = new JRadioButton("Masculino");
		rdbtnMasculino.setBackground(new Color(245, 245, 245));
		rdbtnMasculino.setOpaque(false);
		rdbtnMasculino.setSelected(true);
		rdbtnMasculino.setBounds(89, 184, 83, 23);
		frmCadastrarFuncionarios.getContentPane().add(rdbtnMasculino);
		
		rdbtnFeminino = new JRadioButton("Feminino");
		rdbtnFeminino.setBackground(new Color(245, 245, 245));
		rdbtnFeminino.setOpaque(false);
		rdbtnFeminino.setBounds(174, 184, 98, 23);
		frmCadastrarFuncionarios.getContentPane().add(rdbtnFeminino);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTelefone.setBounds(260, 60, 70, 20);
		frmCadastrarFuncionarios.getContentPane().add(lblTelefone);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEmail.setBounds(260, 91, 60, 20);
		frmCadastrarFuncionarios.getContentPane().add(lblEmail);
		
		JLabel lblCargo = new JLabel("Cargo:");
		lblCargo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCargo.setBounds(260, 122, 60, 20);
		frmCadastrarFuncionarios.getContentPane().add(lblCargo);
		
		JLabel lblSalario = new JLabel("Salario:");
		lblSalario.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSalario.setBounds(260, 153, 60, 20);
		frmCadastrarFuncionarios.getContentPane().add(lblSalario);
		
		JLabel lblDesligada = new JLabel("Status:");
		lblDesligada.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDesligada.setBounds(260, 184, 60, 20);
		frmCadastrarFuncionarios.getContentPane().add(lblDesligada);
		
		rdbtnAtivo = new JRadioButton("Ativo");
		rdbtnAtivo.setBackground(new Color(245, 245, 245));
		rdbtnAtivo.setOpaque(false);
		rdbtnAtivo.setSelected(true);
		rdbtnAtivo.setBounds(340, 184, 60, 23);
		frmCadastrarFuncionarios.getContentPane().add(rdbtnAtivo);
		
		rdbtnDesligado = new JRadioButton("Desligado");
		rdbtnDesligado.setBackground(new Color(245, 245, 245));
		rdbtnDesligado.setOpaque(false);
		rdbtnDesligado.setBounds(396, 184, 83, 23);
		frmCadastrarFuncionarios.getContentPane().add(rdbtnDesligado);
		
		ftfTelefone = new JFormattedTextField(maskaraTelefone);
		ftfTelefone.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.GRAY, Color.GRAY, Color.DARK_GRAY));
		ftfTelefone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					tfEmail.requestFocus();
				}
			}
		});
		ftfTelefone.setBounds(340, 60, 150, 20);
		frmCadastrarFuncionarios.getContentPane().add(ftfTelefone);
		
		tfEmail = new JTextField();
		tfEmail.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.GRAY, Color.GRAY, Color.DARK_GRAY));
		tfEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					tfCargo.requestFocus();
				}
			}
		});
		tfEmail.setColumns(10);
		tfEmail.setBounds(340, 91, 150, 20);
		frmCadastrarFuncionarios.getContentPane().add(tfEmail);
		
		tfCargo = new JTextField();
		tfCargo.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.GRAY, Color.GRAY, Color.DARK_GRAY));
		tfCargo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					tfSalario.requestFocus();
				}
			}
		});
		tfCargo.setColumns(10);
		tfCargo.setBounds(340, 122, 150, 20);
		frmCadastrarFuncionarios.getContentPane().add(tfCargo);
		
		tfSalario = new JTextField();
		tfSalario.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.GRAY, Color.GRAY, Color.BLACK));
		tfSalario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					ftfAdimissao.requestFocus();
				}
			}
		});
		tfSalario.setColumns(10);
		tfSalario.setBounds(340, 153, 150, 20);
		frmCadastrarFuncionarios.getContentPane().add(tfSalario);
		
		ButtonGroup bgSexo = new ButtonGroup();
		bgSexo.add(rdbtnMasculino);
		bgSexo.add(rdbtnFeminino);
		
		ButtonGroup bgStatus = new ButtonGroup();
		bgStatus.add(rdbtnAtivo);
		bgStatus.add(rdbtnDesligado);
		
		ftfCpf = new JFormattedTextField(maskaraCpf);
		ftfCpf.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.GRAY, Color.GRAY, Color.DARK_GRAY));
		ftfCpf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					tfRg.requestFocus();
				}
			}
		});
		ftfCpf.setBounds(89, 122, 150, 20);
		frmCadastrarFuncionarios.getContentPane().add(ftfCpf);
		
		JLabel lblAdmisso = new JLabel("Admiss\u00E3o");
		lblAdmisso.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAdmisso.setBounds(510, 60, 80, 20);
		frmCadastrarFuncionarios.getContentPane().add(lblAdmisso);
		
		JLabel lblDemisso = new JLabel("Demiss\u00E3o");
		lblDemisso.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDemisso.setBounds(510, 91, 80, 20);
		frmCadastrarFuncionarios.getContentPane().add(lblDemisso);
		
		JLabel lblNumeroDaCarteira = new JLabel("N\u00BA Carteira:");
		lblNumeroDaCarteira.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNumeroDaCarteira.setBounds(510, 122, 80, 20);
		frmCadastrarFuncionarios.getContentPane().add(lblNumeroDaCarteira);
		
		JLabel lblPis = new JLabel("PIS:");
		lblPis.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPis.setBounds(510, 153, 80, 20);
		frmCadastrarFuncionarios.getContentPane().add(lblPis);
		
		ftfAdimissao = new JFormattedTextField(maskaraData);
		ftfAdimissao.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.GRAY, Color.GRAY, Color.DARK_GRAY));
		ftfAdimissao.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					ftfDemissao.requestFocus();
				}
			}
		});
		ftfAdimissao.setBounds(600, 61, 150, 20);
		frmCadastrarFuncionarios.getContentPane().add(ftfAdimissao);
		
		ftfDemissao = new JFormattedTextField(maskaraData);
		ftfDemissao.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.GRAY, Color.GRAY, Color.DARK_GRAY));
		ftfDemissao.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					ftfCarteira.requestFocus();
				}
			}
		});
		ftfDemissao.setBounds(600, 92, 150, 20);
		frmCadastrarFuncionarios.getContentPane().add(ftfDemissao);
		
		ftfCarteira = new JFormattedTextField(maskaraTrabalho);
		ftfCarteira.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.GRAY, Color.GRAY, Color.DARK_GRAY));
		ftfCarteira.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					ftfPis.requestFocus();
				}
			}
		});
		ftfCarteira.setBounds(600, 123, 150, 20);
		frmCadastrarFuncionarios.getContentPane().add(ftfCarteira);
		
		ftfPis = new JFormattedTextField(maskaraPis);
		ftfPis.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.GRAY, Color.GRAY, Color.DARK_GRAY));
		ftfPis.setBounds(600, 154, 150, 20);
		frmCadastrarFuncionarios.getContentPane().add(ftfPis);
		
		ImageIcon icon = new ImageIcon(CadastrarFuncionarios.class.getResource("/libs/img/libs/img/t3.png"));
		icon.setImage(icon.getImage().getScaledInstance( 53, 48, 100));
		JLabel lblIcon = new JLabel("");
		lblIcon.setBounds(997, 6, 53, 48);
		lblIcon.setIcon(icon);
		frmCadastrarFuncionarios.getContentPane().add(lblIcon);
		
		lblSemdados = new JLabel("Sem dados salvos!");
		lblSemdados.setHorizontalAlignment(SwingConstants.CENTER);
		lblSemdados.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSemdados.setBounds(10, 281, 1054, 25);
		frmCadastrarFuncionarios.getContentPane().add(lblSemdados);
		
		JLabel lblFundo = new JLabel("PIS:");
		lblFundo.setIcon(new ImageIcon(CadastrarFuncionarios.class.getResource("/libs/img/libs/img/teste13.jpg")));
		lblFundo.setBounds(0, -22, 1074, 670);
		frmCadastrarFuncionarios.getContentPane().add(lblFundo);
		
		//tabela
				x1=1;
				//tabela
		menu();
		DAOFuncionario.setIdFazenda(Principal.fazenda.getIdFazenda());
		colocaDadosNaTabela(CrudFuncionarios.selecionaFuncionario(DAOFuncionario));
		
		//tabela
				//IF PARA VERIFICAR SE A TABLE ESTIVER VAZIA E DEIXAR VISIBLE.(FALSE)
				if (tabela.getRowCount()== 0) {
					scrollPane.setVisible(false);
					lblSemdados.setVisible(true);
				}
				//tabela
	}
	
	
	void preencherDAOFuncionarioParaSalvarNovo() {
		
		
		DAOFuncionario.setNome(tfNome.getText());
		DAOFuncionario.setDataDeNascimento(ftfNascimento.getText());
		DAOFuncionario.setCpf(ftfCpf.getText());
		DAOFuncionario.setRg(tfRg.getText());
		DAOFuncionario.setTelefone(ftfTelefone.getText());
		DAOFuncionario.setEmail(tfEmail.getText());
		DAOFuncionario.setCargo(tfCargo.getText());
		DAOFuncionario.setSalario(Float.parseFloat(tfSalario.getText()));
		DAOFuncionario.setIdFazenda(Principal.fazenda.getIdFazenda());
		DAOFuncionario.setImg(mI.getImagem(img, panel));
		DAOFuncionario.setAdmissao(ftfAdimissao.getText());
		DAOFuncionario.setPis(ftfPis.getText());
		DAOFuncionario.setCarteira(ftfCarteira.getText());
		
		if (rdbtnDesligado.isSelected()) {
			DAOFuncionario.setDemissao(ftfDemissao.getText());
		}
		else {
			DAOFuncionario.setDemissao(null);
		}
		
		if(rdbtnMasculino.isSelected()) {
			DAOFuncionario.setSexo("M");
		}else {
			DAOFuncionario.setSexo("F");
		}
		
		if(rdbtnAtivo.isSelected()) {
			DAOFuncionario.setStatus("A");
		}else
			DAOFuncionario.setStatus("D");
	}
	
	void colocaDadosNaTabela(ResultSet rs) {
		DAOFuncionario.setIdFazenda(Principal.fazenda.getIdFazenda());
		String sexo;
		//tabela
				scrollPane.setVisible(true);
				//tabela
		DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
		modelo.setNumRows(0);
		
		try {
			while (rs.next()) {
				ResultSet rs2 = new CrudFazenda().selecionaFazendaEspecifica(rs.getInt("idfazenda"));
				String fazenda=null;
				String status=null;
				String demissao=null;
				
				if(rs2.next())
					fazenda = rs2.getString("nome");
				
				if( rs.getString("sexo").equalsIgnoreCase("m"))
					sexo = "Masculino";
				else
					sexo="Feminino";
				
				if(rs.getString("status").equalsIgnoreCase("a")) {
					status="Ativo";
				}else {
					status="desligado";
				}
				
				if (rs.getString("datademissao") == null) {
					demissao=null;
				}else {
					demissao=rs.getString("datademissao");
				}
				
				modelo.addRow(new Object[] {rs.getInt("idfuncionarios"),rs.getString("nome_fun"),rs.getString("data_nasc"),
						rs.getString("cpf_fun"),rs.getString("rg_fun"),sexo,rs.getString("fone_fun"),rs.getString("email_fun"),
						rs.getString("cargo"),rs.getString("salario"),fazenda,status,rs.getString("dataAdimissao"),demissao,rs.getString("numeroCarteira"),rs.getString("pis")});
				//tabela
				if (x1==1) {
					if (tabela.getRowCount() >= teste & scrollPane.getHeight()<=315) {
						teste=1;
						teste=+1;
						lblSemdados.setVisible(false);
						int x = (teste*17)+scrollPane.getHeight();
						scrollPane.setBounds(10, 253, 1054, x);
					}
				}
				//tabela
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void pegaDadosDaTabela() {
		int linha = tabela.getSelectedRow();
		
		DAOFuncionario.setIdFuncionario(Integer.parseInt(tabela.getValueAt(linha, 0).toString()));
		DAOFuncionario.setNome(tabela.getValueAt(linha, 1).toString());
		DAOFuncionario.setDataDeNascimento(tabela.getValueAt(linha, 2).toString());
		DAOFuncionario.setCpf(tabela.getValueAt(linha, 3).toString());
		DAOFuncionario.setRg(tabela.getValueAt(linha, 4).toString());
		DAOFuncionario.setSexo(tabela.getValueAt(linha, 5).toString());
		DAOFuncionario.setTelefone(tabela.getValueAt(linha, 6).toString());
		DAOFuncionario.setEmail(tabela.getValueAt(linha, 7).toString());
		DAOFuncionario.setCargo(tabela.getValueAt(linha, 8).toString());
		DAOFuncionario.setSalario(Float.parseFloat(tabela.getValueAt(linha, 9).toString()));
		DAOFuncionario.setStatus(tabela.getValueAt(linha, 11).toString());
		DAOFuncionario.setAdmissao(tabela.getValueAt(linha, 12).toString());
	
		if (tabela.getValueAt(linha, 13) == null) {
			DAOFuncionario.setDemissao(null);
		}else {
			DAOFuncionario.setDemissao(tabela.getValueAt(linha, 13).toString());
		}
		
		DAOFuncionario.setCarteira(tabela.getValueAt(linha, 14).toString());
		DAOFuncionario.setPis(tabela.getValueAt(linha, 15).toString());
		
		ResultSet dados1 = null;
		String sql = "SELECT (img) FROM funcionarios WHERE idfuncionarios=?";
		
		try {
			PreparedStatement stmt = Conexao.conexao.prepareStatement(sql);
			stmt.setInt(1, Integer.parseInt(tabela.getValueAt(linha, 0).toString()));
			dados1 = stmt.executeQuery();
			stmt.execute();
			stmt.close();
				
			if(dados1.next()) {
				DAOFuncionario.setImg(dados1.getBytes("img"));
			}
				
		} catch (SQLException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		tfNome.setText(DAOFuncionario.getNome());
		ftfNascimento.setText(DAOFuncionario.getDataDeNascimento());
		ftfCpf.setText(DAOFuncionario.getCpf());
		tfRg.setText(DAOFuncionario.getRg());
		ftfTelefone.setText(DAOFuncionario.getTelefone());
		tfEmail.setText(DAOFuncionario.getEmail());
		tfCargo.setText(DAOFuncionario.getCargo());
		tfSalario.setText(String.valueOf(DAOFuncionario.getSalario()));
		ftfAdimissao.setText(DAOFuncionario.getAdmissao());
		ftfDemissao.setText(DAOFuncionario.getDemissao());
		ftfCarteira.setText(DAOFuncionario.getCarteira());
		ftfPis.setText(DAOFuncionario.getPis());
		
		if(DAOFuncionario.getSexo().equalsIgnoreCase("masculino")) {
			rdbtnMasculino.setSelected(true);
		}else if(DAOFuncionario.getSexo().equalsIgnoreCase("feminino")){
			rdbtnFeminino.setSelected(true);
		}
		
		if (DAOFuncionario.getStatus().equalsIgnoreCase("ativo")) {
			rdbtnAtivo.setSelected(true);
		}if(DAOFuncionario.getStatus().equalsIgnoreCase("desligado"))
			rdbtnDesligado.setSelected(true);
		
		if(DAOFuncionario.getImg()!= null) {
			lblImagem.setHorizontalAlignment(SwingConstants.LEADING);
			mI.abrirImagem(DAOFuncionario.getImg(), null, panel, lblImagem, DAOFuncionario.getImg());
		}else {
			lblImagem.setHorizontalAlignment(SwingConstants.CENTER);
			lblImagem.setIcon(new ImageIcon(CadastrarFuncionarios.class.getResource("/img/128x128.png")));
		}
		
			
		
	}
	
	
	
	public void menu() {	
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(105, 105, 105), new Color(105, 105, 105), new Color(105, 105, 105), new Color(105, 105, 105)));
		menuBar.setForeground(Color.GREEN);
		menuBar.setBackground(Color.DARK_GRAY);
		frmCadastrarFuncionarios.setJMenuBar(menuBar);
		
		JMenu mnInicio = new JMenu("");
		mnInicio.setBorder(new SoftBevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY));
		mnInicio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnInicio.setOpaque(true);
		mnInicio.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mnInicio.setIcon(new ImageIcon(CadastrarFuncionarios.class.getResource("/libs/img/libs/img/Icone_Inicio.png")));
		mnInicio.setForeground(Color.WHITE);
		mnInicio.setBackground(Color.DARK_GRAY);
		mnInicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Principal.frmPrincipal.setVisible(true);
				frmCadastrarFuncionarios.dispose();
			}
		});
		menuBar.add(mnInicio);
		
		JMenu mnNewMenu = new JMenu("");
		mnNewMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnNewMenu.setIcon(new ImageIcon(CadastrarFuncionarios.class.getResource("/libs/img/libs/img/Icone_GEstao.png")));
		mnNewMenu.setOpaque(true);
		mnNewMenu.setFocusPainted(true);
		mnNewMenu.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(64, 64, 64), new Color(64, 64, 64), new Color(64, 64, 64), new Color(64, 64, 64)));
		mnNewMenu.setBackground(Color.DARK_GRAY);
		mnNewMenu.setForeground(Color.WHITE);
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmCadastrarAnimais = new JMenuItem("Animais");
		
		mntmCadastrarAnimais.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmCadastrarAnimais.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(105, 105, 105), new Color(105, 105, 105), new Color(105, 105, 105), new Color(105, 105, 105)));
		mntmCadastrarAnimais.setOpaque(true);
		mntmCadastrarAnimais.setForeground(Color.WHITE);
		mntmCadastrarAnimais.setBackground(Color.DARK_GRAY);
		mntmCadastrarAnimais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastrarAnimais.main(null);
				frmCadastrarFuncionarios.dispose();
			}
		});
		mnNewMenu.add(mntmCadastrarAnimais);
		
		JMenuItem mntmCadastrarFuncionarios = new JMenuItem("Funcionarios");
		mntmCadastrarFuncionarios.setOpaque(true);
		mntmCadastrarFuncionarios.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(105, 105, 105), new Color(105, 105, 105), new Color(105, 105, 105), new Color(105, 105, 105)));
		mntmCadastrarFuncionarios.setBackground(Color.DARK_GRAY);
		mntmCadastrarFuncionarios.setForeground(Color.WHITE);
		mntmCadastrarFuncionarios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnNewMenu.add(mntmCadastrarFuncionarios);
		mntmCadastrarFuncionarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//CadastrarFuncionarios.main(null);
				//frmCadastrarFuncionarios.dispose();
			}
		});
		
		JMenu mnNewMenu_1 = new JMenu("");
		mnNewMenu_1.setBorder(new SoftBevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY));
		
		mnNewMenu_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnNewMenu_1.setIcon(new ImageIcon(CadastrarFuncionarios.class.getResource("/libs/img/libs/img/Icone_Financeiro.png")));
		mnNewMenu_1.setForeground(Color.WHITE);
		mnNewMenu_1.setBackground(Color.DARK_GRAY);
		mnNewMenu_1.setOpaque(true);
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmCompra = new JMenuItem("Compra");
		mntmCompra.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(105, 105, 105), new Color(105, 105, 105), new Color(105, 105, 105), new Color(105, 105, 105)));
		mntmCompra.setForeground(Color.WHITE);
		mntmCompra.setBackground(Color.DARK_GRAY);
		mntmCompra.setOpaque(true);
		mntmCompra.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmCompra.setEnabled(true);
		mntmCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NovaCompra.main(null);
				frmCadastrarFuncionarios.dispose();
			}
		});
		mnNewMenu_1.add(mntmCompra);
		
		JMenuItem mntmNovaVenda = new JMenuItem("Venda");
		mntmNovaVenda.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(105, 105, 105), new Color(105, 105, 105), new Color(105, 105, 105), new Color(105, 105, 105)));
		mntmNovaVenda.setForeground(Color.WHITE);
		mntmNovaVenda.setBackground(Color.DARK_GRAY);
		mntmNovaVenda.setOpaque(true);
		mntmNovaVenda.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnNewMenu_1.add(mntmNovaVenda);
		
		JMenuItem mntmTotal = new JMenuItem("Total");
		mntmTotal.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(105, 105, 105), new Color(105, 105, 105), new Color(105, 105, 105), new Color(105, 105, 105)));
		mntmTotal.setForeground(Color.WHITE);
		mntmTotal.setBackground(Color.DARK_GRAY);
		mntmTotal.setOpaque(true);
		mntmTotal.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnNewMenu_1.add(mntmTotal);
		mntmTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Total.main(null);
				frmCadastrarFuncionarios.dispose();
			}
		});
		mntmNovaVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NovaVenda.main(null);
				frmCadastrarFuncionarios.dispose();
			}
		});
		
		JMenu mnOpes = new JMenu("");
		mnOpes.setBorder(new SoftBevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY));
		mnOpes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnOpes.setIcon(new ImageIcon(CadastrarFuncionarios.class.getResource("/libs/img/libs/img/Icone_OPCAO.png")));
		mnOpes.setForeground(Color.WHITE);
		mnOpes.setBackground(Color.DARK_GRAY);
		mnOpes.setOpaque(true);
		menuBar.add(mnOpes);
		
		JMenuItem mntmNovaFazenda = new JMenuItem("Fazenda");
		mntmNovaFazenda.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(105, 105, 105), new Color(105, 105, 105), new Color(105, 105, 105), new Color(105, 105, 105)));
		mntmNovaFazenda.setBackground(Color.DARK_GRAY);
		mntmNovaFazenda.setForeground(Color.WHITE);
		mntmNovaFazenda.setOpaque(true);
		mntmNovaFazenda.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmNovaFazenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NovaFazenda.main(null);
				frmCadastrarFuncionarios.dispose();
			}
		});
		mnOpes.add(mntmNovaFazenda);
		
		JMenuItem mntmSada = new JMenuItem("Sair");
		mntmSada.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(105, 105, 105), new Color(105, 105, 105), new Color(105, 105, 105), new Color(105, 105, 105)));
		mntmSada.setBackground(Color.DARK_GRAY);
		mntmSada.setForeground(Color.WHITE);
		mntmSada.setOpaque(true);
		mntmSada.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmSada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		JMenuItem mntmMudarFazenda = new JMenuItem("Mudar Fazenda");
		mntmMudarFazenda.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(105, 105, 105), new Color(105, 105, 105), new Color(105, 105, 105), new Color(105, 105, 105)));
		mntmMudarFazenda.setBackground(Color.DARK_GRAY);
		mntmMudarFazenda.setForeground(Color.WHITE);
		mntmMudarFazenda.setOpaque(true);
		mntmMudarFazenda.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmMudarFazenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Pergunta.main(null);
				Pergunta.contador = 1;
				Pergunta.outroFrame=frmCadastrarFuncionarios;
			}
		});
		mnOpes.add(mntmMudarFazenda);
		
		JMenuItem mntmDeslogar = new JMenuItem("Deslogar");
		mntmDeslogar.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(105, 105, 105), new Color(105, 105, 105), new Color(105, 105, 105), new Color(105, 105, 105)));
		mntmDeslogar.setBackground(Color.DARK_GRAY);
		mntmDeslogar.setForeground(Color.WHITE);
		mntmDeslogar.setOpaque(true);
		mntmDeslogar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmDeslogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmCadastrarFuncionarios.dispose();
				Login.main(null);
			}
		});
		mnOpes.add(mntmDeslogar);
		

		
		mnOpes.add(mntmSada);
		frmCadastrarFuncionarios.getContentPane().setLayout(null);
	}
}