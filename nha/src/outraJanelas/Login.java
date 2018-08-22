package outraJanelas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import DAO.Usuario;
import banco.Conexao;
import crud.crudUsuarios;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;

public class Login {//
	String acesso="nao";
	private JFrame frmLogin;
	private JTextField tfUsuario;
	private JPasswordField pfSenha;
	String armazenar ;
	private JButton btnEntrar;
	String emailSistema = "gan.gerenciamentodeagronegocio@gmail.com";//o novo
	String senha = "gansenha@123";
	String destino;
	String titulo = "Nova senha GAN(Gereciamento de Agronegócio)";
	String novaSenha;
	String msg;
	Usuario usuario = new Usuario();
	static int idUsu;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/libs/img/32x32.png")));
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 400, 400);
		frmLogin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		frmLogin.setResizable(false);
		frmLogin.setLocationRelativeTo(null);
		
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(10, 0, 374, 34);
		frmLogin.getContentPane().add(lblNewLabel);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUsuario.setBounds(10, 202, 54, 20);
		frmLogin.getContentPane().add(lblUsuario);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSenha.setBounds(10, 233, 54, 20);
		frmLogin.getContentPane().add(lblSenha);
		
		tfUsuario = new JTextField();
		tfUsuario.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.GRAY, Color.GRAY, Color.DARK_GRAY));
		tfUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {//evento de apertar ENTER
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					pfSenha.requestFocus();
				}
			}
		});
		tfUsuario.setBounds(100, 203, 220, 20);
		frmLogin.getContentPane().add(tfUsuario);
		tfUsuario.setColumns(10);
		
		pfSenha = new JPasswordField();
		pfSenha.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.GRAY, Color.GRAY, Color.DARK_GRAY));
		pfSenha.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {//evento de apertar ENTER
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnEntrar.doClick();
				}
			}
		});
		pfSenha.setBounds(100, 234, 220, 20);
		frmLogin.getContentPane().add(pfSenha);
		
		btnEntrar = new JButton("Entrar");
		btnEntrar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnEntrar.setBackground(new Color(245, 245, 245));
		btnEntrar.setBounds(279, 338, 105, 23);
		frmLogin.getContentPane().add(btnEntrar);
		
		JLabel lblEsqueceuSuaSenha = new JLabel("Esqueceu sua Senha?");
		lblEsqueceuSuaSenha.setForeground(Color.BLACK);
		lblEsqueceuSuaSenha.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {//evento de mudar a cor do label
				lblEsqueceuSuaSenha.setForeground(Color.red);
			}
			@Override
			public void mouseExited(MouseEvent e) {//evento de mudar a cor do label
				lblEsqueceuSuaSenha.setForeground(Color.black);
			}
			@Override
			public void mouseClicked(MouseEvent e) {//evento de clique no label
				if(tfUsuario.getText().trim().equals("")) {// verivicaçao se o campo tfUsuario esta vazio
					JOptionPane.showMessageDialog(null, "Insira um usuario");
					tfUsuario.requestFocus();
					return;
				}
				
				ResultSet rs = new crudUsuarios().selectUsuarioPeloNome(tfUsuario.getText());//select para selecionar o usuario do tfUsuario
				try {
					if(rs.next()) { // verificaçao se tem usurio
						destino = rs.getString("email");
						novaSenha = gerarSenhaAleatoria();
						msg = "Sua nova senha "+tfUsuario.getText()+" é: "+novaSenha;
						
					}else {
						JOptionPane.showMessageDialog(null, "<html><body><p width='150px' align='center'>O usuario inserido não "+ "\n<html><body><p width='150px'"
								+ " align='center'>corresponde a nenhuma conta.", null, JOptionPane.ERROR_MESSAGE);
						tfUsuario.selectAll();
						return;
					} //fim verificaçao se tem usurio
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
				
				int resposta = JOptionPane.showConfirmDialog(null, "<html><body><p width='230px' align='center'>A sua senha sera alterada e enviada para o e-mail:\n"
						+"<html><body><p width='230px' align='center'>"+destino +"\n<html><body><p width='230px' align='center'>Deseja continuar?" , 
						"Alerta", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
				
				if(resposta != JOptionPane.YES_OPTION) {//resposta do JOptionPane
					return;
				}
				
				new crudUsuarios().updSenhaUsuario(novaSenha, tfUsuario.getText());//atualiza a senha
				new EnviarEmail().enviarEmail(emailSistema, senha, destino, titulo, msg);//envia o email
			
			}//fim evento de clique no label
		});
		lblEsqueceuSuaSenha.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblEsqueceuSuaSenha.setBounds(243, 265, 105, 14);
		frmLogin.getContentPane().add(lblEsqueceuSuaSenha);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnCadastrar.setBackground(new Color(245, 245, 245));
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NovoUsuario.main(null);
				frmLogin.dispose();
			}
		});
		btnCadastrar.setBounds(164, 338, 105, 23);
		frmLogin.getContentPane().add(btnCadastrar);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Login.class.getResource("/libs/img/128x128.png")));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD, 16));
		label.setBounds(10, 45, 374, 128);
		frmLogin.getContentPane().add(label);
		
		JLabel lblFundo = new JLabel("");
		lblFundo.setIcon(new ImageIcon(Login.class.getResource("/libs/img/fundo400.jpg")));
		lblFundo.setBounds(0, 0, 394, 371);
		frmLogin.getContentPane().add(lblFundo);
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logar();	
			}			
		});
		
		Conexao.getConexao();
	}	
	
	public void logar() {

		ResultSet rs = null;
		String campoUsuario = tfUsuario.getText();
		String senha = String.valueOf(pfSenha.getPassword());
		String sql = "select*from usuario where usuario=?";
		
		if(tfUsuario.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Insira um usuario");
			return;
		}
		JOptionPane.showMessageDialog(null, "logandooo");

		try {
			PreparedStatement stmt = new Conexao().getConexao().prepareStatement(sql);
			stmt.setString(1, campoUsuario);
			rs = stmt.executeQuery();
			stmt.execute();
			stmt.close();
			JOptionPane.showMessageDialog(null, "logandooo");

			if(rs.next()) {
				String teste = rs.getString("usuario");
				
				if(teste.equals(campoUsuario)) {
						if (senha.equals(rs.getString("senha"))) {
							Usuario usuario = new Usuario();
							usuario.setEmail(rs.getString("email"));
							usuario.setIdUsuario(rs.getInt("idusuario"));
							 idUsu = usuario.getIdUsuario();
							usuario.setSenha(rs.getString("senha"));
							usuario.setUsuario(rs.getString("usuario"));
							Pergunta.usuario = usuario;
							JOptionPane.showMessageDialog(null, "Bem-Vindo "+usuario.getUsuario());
							frmLogin.dispose();
							Principal.main(null);
						}else {//if senha
							JOptionPane.showMessageDialog(null, "A senha inserida está incorreta.",null, JOptionPane.ERROR_MESSAGE);
							pfSenha.selectAll();
						}
					}
			}else {//if usuario
				JOptionPane.showMessageDialog(null, "<html><body><p width='150px' align='center'>O usuario inserido não "
						+ "\n<html><body><p width='150px' align='center'>corresponde a nenhuma conta.", null, JOptionPane.ERROR_MESSAGE);
				tfUsuario.selectAll();
			}

		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, e1.toString());
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	

	}

	private static String gerarSenhaAleatoria() {
        int qtdeMaximaCaracteres = 8;
        String[] caracteres = {"1", "2", "4", "5", "6", "7", "8", "3",
        					   "9", "a", "b", "c", "d", "e", "f", "g",
        					   "h", "i", "j", "k", "l", "m", "n", "o",
        					   "p", "q", "r", "s", "t", "u", "v", "w",
        					   "x", "y", "z", "A", "B", "C", "D", "E", 
        					   "F", "G", "H", "I", "J", "K", "L", "M", 
        					   "N", "O", "P", "Q", "R", "S", "T", "U",
        					   "V", "W", "X", "Y", "Z", "ç", "Ç", "0" };
       
        StringBuilder senha = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < qtdeMaximaCaracteres; i++) {
            int posicao =  random.nextInt(caracteres.length);
            senha.append(caracteres[posicao]);
        }
        return senha.toString();
    }
}
