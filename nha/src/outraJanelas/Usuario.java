package outraJanelas;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import JanelasFuncionarios.CadastrarFuncionarios;
import crud.crudUsuarios;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import JanelasAnimal.CadastrarAnimais;
import JanelasComtabil.NovaCompra;
import JanelasComtabil.NovaVenda;
import JanelasComtabil.Total;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Usuario {

	DAO.Usuario usuario = new DAO.Usuario();
	private JFrame frmUsuario;
	private JTextField tfNome;
	private JTextField tfEmail;
	private JPasswordField pfSenha;
	private JPasswordField pfSenhaNova;
	private JButton btnEditar;
	private JButton btnCancelar;
	private JButton btnLimpar;
	private JButton btnEnviar;
	private JCheckBox chckbxAlterarDados;
	int teste = 0;
	private JPasswordField pfSenhaNova2;
	private JTextArea textArea;
	String emailEnviar = "ganenviar@gmail.com";
	String senha = "ganenviar@123";
	String destino;
	String msg;
	String titulo;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Usuario window = new Usuario();
					window.frmUsuario.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Usuario() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmUsuario = new JFrame();
		frmUsuario.setIconImage(Toolkit.getDefaultToolkit().getImage(Usuario.class.getResource("/libs/img/32x32.png")));
		frmUsuario.setTitle("Cadastrar Funcionarios");
		frmUsuario.setBounds(100, 100, 1080, 720);
		frmUsuario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmUsuario.setLocationRelativeTo(null);
		frmUsuario.setResizable(false);
		frmUsuario.getContentPane().setLayout(null);
		
		
		ImageIcon icon = new ImageIcon(Usuario.class.getResource("/libs/img/usu1.png"));
		icon.setImage(icon.getImage().getScaledInstance( 53, 48, 100));
		JLabel lblIcon = new JLabel("");
		lblIcon.setBounds(997, 6, 53, 48);
		lblIcon.setIcon(icon);
		frmUsuario.getContentPane().add(lblIcon);
		
		JLabel lblUsurio = new JLabel("Usu\u00E1rio");
		lblUsurio.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsurio.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblUsurio.setBounds(10, 11, 1054, 25);
		frmUsuario.getContentPane().add(lblUsurio);
		
		JLabel label = new JLabel("Nome:");
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		label.setBounds(44, 60, 60, 20);
		frmUsuario.getContentPane().add(label);
		
		tfNome = new JTextField();
		tfNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode()==KeyEvent.VK_ENTER) {
					tfEmail.requestFocus();
				}
			}
		});
		tfNome.setEnabled(false);
		tfNome.setColumns(10);
		tfNome.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.GRAY, Color.GRAY, Color.DARK_GRAY));
		tfNome.setBounds(114, 61, 295, 20);
		frmUsuario.getContentPane().add(tfNome);
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEmail.setBounds(44, 103, 70, 20);
		frmUsuario.getContentPane().add(lblEmail);
		
		tfEmail = new JTextField();
		tfEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					pfSenha.requestFocus();
				}
			}
		});
		tfEmail.setEnabled(false);
		tfEmail.setColumns(10);
		tfEmail.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.GRAY, Color.GRAY, Color.DARK_GRAY));
		tfEmail.setBounds(114, 104, 295, 20);
		frmUsuario.getContentPane().add(tfEmail);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSenha.setBounds(631, 65, 60, 20);
		frmUsuario.getContentPane().add(lblSenha);
		
		pfSenha = new JPasswordField();
		pfSenha.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					pfSenhaNova.requestFocus();
				}
			}
		});
		pfSenha.setEnabled(false);
		pfSenha.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.GRAY, Color.GRAY, Color.DARK_GRAY));
		pfSenha.setBounds(732, 66, 295, 20);
		frmUsuario.getContentPane().add(pfSenha);
		
		JLabel lblNovaSenha = new JLabel("Nova Senha:");
		lblNovaSenha.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNovaSenha.setBounds(631, 96, 90, 20);
		frmUsuario.getContentPane().add(lblNovaSenha);
		
		pfSenhaNova = new JPasswordField();
		pfSenhaNova.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					pfSenhaNova2.requestFocus();
				}
			}
		});
		pfSenhaNova.setEditable(false);
		pfSenhaNova.setEnabled(false);
		pfSenhaNova.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.GRAY, Color.GRAY, Color.DARK_GRAY));
		pfSenhaNova.setBounds(732, 97, 295, 20);
		frmUsuario.getContentPane().add(pfSenhaNova);
		
		btnEditar = new JButton("Editar");
		btnEditar.setEnabled(false);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				if (tfNome.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Insira um nome");
					tfNome.requestFocus();
					return;
				}
				if (!(tfEmail.getText().contains("@") && tfEmail.getText().contains(".com"))) {
					JOptionPane.showMessageDialog(null, "Insira uma e-mail");
					tfEmail.requestFocus();
					return;
				}
				if (pfSenha.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Insira uma senha");
					pfSenha.requestFocus();
					return;
				}
				
				if (pfSenhaNova.isEnabled()) {
					if (pfSenhaNova.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(null, "Insira uma nova senha");
						pfSenhaNova.requestFocus();
						return;
					}
				}
				if (pfSenhaNova2.isEnabled()) {
					if (pfSenhaNova2.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(null, "Insira uma nova senha");
						pfSenhaNova2.requestFocus();
						return;
					}
				}
				if (pfSenhaNova2.isEnabled()) {
					if ((!pfSenhaNova.getText().equals(pfSenhaNova2.getText())) ) {
						JOptionPane.showMessageDialog(null, "As senhas não se correspondem!");
						pfSenhaNova.requestFocus();
						return;
					}
				}
				
				colocaDadosDAO();
				new crudUsuarios().updUsuario(usuario);
				
				JOptionPane.showMessageDialog(null, "Dados atualizados com sucesso!");
				chckbxAlterarDados.setSelected(false);
				btnCancelar.doClick();
				
			}
		});
		btnEditar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnEditar.setBackground(new Color(245, 245, 245));
		btnEditar.setBounds(862, 596, 89, 23);
		frmUsuario.getContentPane().add(btnEditar);
		
		btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				msg=textArea.getText()+"  /Entrar em contato com "+tfEmail.getText();
				titulo="GAN feedback de "+tfNome.getText();
				destino="gan.gerenciamentodeagronegocio@gmail.com";
				new EnviarEmail().enviarEmail(emailEnviar, senha, destino, titulo, msg);
				JOptionPane.showMessageDialog(null, "Seu relatório foi enviado com sucesso!"+
				" Entraremos em contato o mais breve possível, obrigado pela compreensão!");
				btnLimpar.doClick();
			}
		});
		btnEnviar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnEnviar.setBackground(new Color(245, 245, 245));
		btnEnviar.setBounds(961, 596, 89, 23);
		frmUsuario.getContentPane().add(btnEnviar);
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (btnEditar.isEnabled()) {
					tfEmail.setText(null);
					tfNome.setText(null);
					pfSenha.setText(null);
					pfSenhaNova.setText(null);
					pfSenhaNova2.setText(null);
				}else {
					textArea.setText(null);
				}
			
			}
		});
		btnLimpar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnLimpar.setBackground(new Color(245, 245, 245));
		btnLimpar.setBounds(763, 596, 89, 23);
		frmUsuario.getContentPane().add(btnLimpar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnEditar.isEnabled()) {
					btnEnviar.setEnabled(true);
					tfEmail.setEnabled(false);
					tfNome.setEnabled(false);
					pfSenha.setEnabled(false);
					pfSenhaNova.setText(null);
					pfSenhaNova.setEnabled(false);
					pfSenhaNova.setEditable(false);
					pfSenhaNova2.setText(null);
					pfSenhaNova2.setEnabled(false);
					pfSenhaNova2.setEditable(false);
					btnEditar.setEnabled(false);
					colocaDados(new crudUsuarios().visualizarUsuario(usuario));
				}else {
					frmUsuario.dispose();
					Principal.frmPrincipal.setVisible(true);
				}
				
			}
		});
		btnCancelar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnCancelar.setBackground(new Color(245, 245, 245));
		btnCancelar.setBounds(660, 596, 89, 23);
		frmUsuario.getContentPane().add(btnCancelar);
		
		chckbxAlterarDados = new JCheckBox("Alterar dados");
		chckbxAlterarDados.setOpaque(false);
		chckbxAlterarDados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					if (chckbxAlterarDados.isSelected()) {
						teste+=1;
						btnEnviar.setEnabled(false);
						tfEmail.setEnabled(true);
						tfNome.setEnabled(true);
						
						btnEditar.setEnabled(true);
						int x = JOptionPane.showConfirmDialog(null, "Deseja alterar a senha?", "ALERTA", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
						System.out.println(x);
						if (x==0) {
							pfSenha.setEnabled(true);
							pfSenhaNova.setEnabled(true);
							pfSenhaNova.setEditable(true);
							pfSenhaNova2.setEnabled(true);
							pfSenhaNova2.setEditable(true);
						}
				}else {
					btnEditar.setEnabled(false);
					btnEnviar.setEnabled(true);
					tfEmail.setEnabled(false);
					tfNome.setEnabled(false);
					pfSenha.setEnabled(false);
					pfSenhaNova.setEnabled(false);
					pfSenhaNova.setEditable(false);
					pfSenhaNova.setText(null);
					pfSenhaNova2.setEnabled(false);
					pfSenhaNova2.setEditable(false);
					pfSenhaNova2.setText(null);
				}
			}
		});
		
		
		chckbxAlterarDados.setBounds(47, 147, 126, 23);
		frmUsuario.getContentPane().add(chckbxAlterarDados);
		
		JLabel lblREsenhanova = new JLabel("Nova Senha:");
		lblREsenhanova.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblREsenhanova.setBounds(631, 129, 90, 20);
		frmUsuario.getContentPane().add(lblREsenhanova);
		
		pfSenhaNova2 = new JPasswordField();
		pfSenhaNova2.setEnabled(false);
		pfSenhaNova2.setEditable(false);
		pfSenhaNova2.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.GRAY, Color.GRAY, Color.DARK_GRAY));
		pfSenhaNova2.setBounds(732, 128, 295, 20);
		frmUsuario.getContentPane().add(pfSenhaNova2);
		
		JLabel lblRelatarErros = new JLabel("Relatar erros");
		lblRelatarErros.setHorizontalAlignment(SwingConstants.CENTER);
		lblRelatarErros.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblRelatarErros.setBounds(10, 207, 1054, 25);
		frmUsuario.getContentPane().add(lblRelatarErros);
		
		textArea = new JTextArea();
		textArea.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setBounds(47, 257, 980, 277);
		frmUsuario.getContentPane().add(textArea);
		
		
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(Usuario.class.getResource("/libs/img/teste13.jpg")));
		label_1.setBounds(0, -24, 1074, 670);
		frmUsuario.getContentPane().add(label_1);
		
		
		
		usuario.setIdUsuario(Login.idUsu);
		System.out.println(usuario.getIdUsuario());
		colocaDados(new crudUsuarios().visualizarUsuario(usuario));
		menu();
		
		
	}
	
	public void colocaDados(ResultSet resultado) {
		try {
			while (resultado.next()) {
				tfNome.setText(resultado.getString("usuario"));
				tfEmail.setText(resultado.getString("email"));
				pfSenha.setText(resultado.getString("senha"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void colocaDadosDAO() {
		usuario.setUsuario(tfNome.getText());
		usuario.setEmail(tfEmail.getText());
		if (pfSenhaNova2.isEnabled()) {
			usuario.setSenha(pfSenhaNova2.getText());
		}else {
			usuario.setSenha(pfSenha.getText());
		}
	
	}
	
	
	public void menu() {	
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(105, 105, 105), new Color(105, 105, 105), new Color(105, 105, 105), new Color(105, 105, 105)));
		menuBar.setForeground(Color.GREEN);
		menuBar.setBackground(Color.DARK_GRAY);
		frmUsuario.setJMenuBar(menuBar);
		
		JMenu mnInicio = new JMenu("");
		mnInicio.setBorder(new SoftBevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY));
		mnInicio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnInicio.setOpaque(true);
		mnInicio.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mnInicio.setIcon(new ImageIcon(Usuario.class.getResource("/libs/img/Icone_Inicio.png")));
		mnInicio.setForeground(Color.WHITE);
		mnInicio.setBackground(Color.DARK_GRAY);
		mnInicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Principal.frmPrincipal.setVisible(true);
				frmUsuario.dispose();
			}
		});
		menuBar.add(mnInicio);
		
		JMenu mnNewMenu = new JMenu("");
		mnNewMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnNewMenu.setIcon(new ImageIcon(Usuario.class.getResource("/libs/img/Icone_GEstao.png")));
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
				frmUsuario.dispose();
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
				CadastrarFuncionarios.main(null);
				frmUsuario.dispose();
			}
		});
		
		JMenu mnNewMenu_1 = new JMenu("");
		mnNewMenu_1.setBorder(new SoftBevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY));
		
		mnNewMenu_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnNewMenu_1.setIcon(new ImageIcon(Usuario.class.getResource("/libs/img/Icone_Financeiro.png")));
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
				frmUsuario.dispose();
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
				frmUsuario.dispose();
			}
		});
		mntmNovaVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NovaVenda.main(null);
				frmUsuario.dispose();
			}
		});
		
		JMenu mnOpes = new JMenu("");
		mnOpes.setBorder(new SoftBevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY));
		mnOpes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnOpes.setIcon(new ImageIcon(Usuario.class.getResource("/libs/img/Icone_OPCAO.png")));
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
				frmUsuario.dispose();
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
				Pergunta.outroFrame=frmUsuario;
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
				frmUsuario.dispose();
				Login.main(null);
			}
		});
		mnOpes.add(mntmDeslogar);
		

		
		mnOpes.add(mntmSada);
		frmUsuario.getContentPane().setLayout(null);
	}
}