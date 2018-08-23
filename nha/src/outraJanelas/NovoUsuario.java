package outraJanelas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import DAO.Usuario;
import crud.crudUsuarios;
import java.awt.Color;
import javax.swing.border.BevelBorder;

public class NovoUsuario {///
	

	private JFrame frame;
	private JTextField tfUsuario;
	private JPasswordField pfSenha;
	private JPasswordField pfConfirma;
	private JTextField tfEmail;
	Usuario usuario = new Usuario();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NovoUsuario window = new NovoUsuario();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NovoUsuario() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(NovoUsuario.class.getResource("/libs/img/libs/img/32x32.png")));
		frame.setBounds(100, 100, 400, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(NovoUsuario.class.getResource("/libs/img/libs/img/128x128.png")));
		lblLogo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setBounds(10, 45, 374, 128);
		frame.getContentPane().add(lblLogo);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUsuario.setBounds(10, 188, 78, 20);
		frame.getContentPane().add(lblUsuario);
		
		tfUsuario = new JTextField();
		tfUsuario.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.GRAY, Color.GRAY, Color.DARK_GRAY));
		tfUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					tfEmail.requestFocus();
					
				}
			}
		});
		tfUsuario.setBounds(100, 188, 220, 20);
		frame.getContentPane().add(tfUsuario);
		tfUsuario.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSenha.setBounds(10, 248, 50, 23);
		frame.getContentPane().add(lblSenha);
		
		JLabel lblConfirmarSenha = new JLabel("Confirmar Senha");
		lblConfirmarSenha.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblConfirmarSenha.setBounds(10, 278, 113, 23);
		frame.getContentPane().add(lblConfirmarSenha);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnSalvar.setBackground(new Color(245, 245, 245));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nomeUsuario = tfUsuario.getText().toString();
				String senha = String.valueOf(pfSenha.getPassword());
				String senhConfirm= String.valueOf(pfConfirma.getPassword());
				
				
				if (nomeUsuario.trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe um usuario válida","ALERTA!",JOptionPane.WARNING_MESSAGE);
					tfUsuario.requestFocus();
					return;
				}
				if (!(tfEmail.getText().contains("@") && tfEmail.getText().contains(".com"))) {
					JOptionPane.showMessageDialog(null, "Informe um e-mail válida","ALERTA!",JOptionPane.WARNING_MESSAGE);
					tfUsuario.requestFocus();
					return;
				}
				if (senha.trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe uma senha válida","ALERTA!",JOptionPane.WARNING_MESSAGE);
					pfSenha.requestFocus();
					return;
				}
				if (senhConfirm.trim().equals("")) {
					JOptionPane.showMessageDialog(null, "confirme sua senha","ALERTA!",JOptionPane.WARNING_MESSAGE);
					pfSenha.requestFocus();
					return;
				}
				if(!(senha.equals(senhConfirm))) {
					JOptionPane.showMessageDialog(null, "As senhas não são iguais","ALERTA!",JOptionPane.WARNING_MESSAGE);
					pfConfirma.requestFocus();
					return;
				}
				
				DAOUsuario();
				if (verificaSeTemUsuario()) {
					new crudUsuarios().addUsuario(usuario);
					Pergunta.usuario = usuario;
					JOptionPane.showMessageDialog(null, "Bem-Vindo "+usuario.getUsuario());
					frame.dispose();
					Principal.main(null);
				}else {
					JOptionPane.showMessageDialog(null, "esse usuario ja existe", "ALERTA!", JOptionPane.WARNING_MESSAGE);
					tfUsuario.requestFocus();
					return;
				}
				
			}
		});
		btnSalvar.setBounds(279, 338, 105, 23);
		frame.getContentPane().add(btnSalvar);
		
		pfSenha = new JPasswordField();
		pfSenha.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.GRAY, Color.GRAY, Color.DARK_GRAY));
		pfSenha.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					pfConfirma.requestFocus();
				}
			}
		});
		pfSenha.setBounds(100, 248, 220, 20);
		frame.getContentPane().add(pfSenha);
		
		pfConfirma = new JPasswordField();
		pfConfirma.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(245, 245, 245), Color.GRAY, Color.GRAY, Color.DARK_GRAY));
		pfConfirma.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					btnSalvar.doClick();
					
				}
			}
		});
		pfConfirma.setBounds(120, 280, 200, 20);
		frame.getContentPane().add(pfConfirma);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnCancelar.setBackground(new Color(245, 245, 245));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				Login.main(null);
			}
		});
		btnCancelar.setBounds(164, 338, 105, 23);
		frame.getContentPane().add(btnCancelar);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEmail.setBounds(10, 218, 78, 20);
		frame.getContentPane().add(lblEmail);
		
		tfEmail = new JTextField();
		tfEmail.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.GRAY, Color.GRAY, Color.DARK_GRAY));
		tfEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					pfSenha.requestFocus();
				}
			}
		});
		tfEmail.setToolTipText("Insira um e-mail v\u00E1lido caso haja necessidade de recuperar sua senha!");
		tfEmail.setColumns(10);
		tfEmail.setBounds(100, 219, 220, 20);
		frame.getContentPane().add(tfEmail);
		
		JLabel lblNovoUsuario = new JLabel("Novo Usuario");
		lblNovoUsuario.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNovoUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblNovoUsuario.setBounds(10, 0, 374, 38);
		frame.getContentPane().add(lblNovoUsuario);
		
		JLabel lblFundo = new JLabel("");
		lblFundo.setIcon(new ImageIcon(NovoUsuario.class.getResource("/libs/img/libs/img/fundo400.jpg")));
		lblFundo.setBounds(0, 0, 394, 371);
		frame.getContentPane().add(lblFundo);
	}
	
	void DAOUsuario() {
		usuario.setUsuario(tfUsuario.getText().toLowerCase());
		usuario.setEmail(tfEmail.getText().toLowerCase());
		usuario.setSenha(String.copyValueOf(pfSenha.getPassword()));
	}
	
	boolean verificaSeTemUsuario() {
		boolean resposta=true;
		ResultSet rs = new crudUsuarios().selectUsuarioPeloNome(usuario.getUsuario());
		
		try {
			if (rs.next()) {
				if (tfUsuario.getText().equalsIgnoreCase(rs.getString("usuario"))) {
					resposta=false;
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resposta;
	}
}
