package JanelasAnimal;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import banco.Conexao;
import javax.swing.SwingConstants;
import java.awt.Window.Type;
import java.awt.SystemColor;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import javax.swing.border.BevelBorder;

public class NovaRaca {

	static JFrame frmNovaRaca;
	private JTextField textField;
	private JComboBox<String> comboBox;
	private JButton btnSalvar;
	static int limit=0; //limita apenas uma janela aberta
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					NovaRaca window = new NovaRaca();
					window.frmNovaRaca.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NovaRaca() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNovaRaca = new JFrame();
		frmNovaRaca.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				limit = 0;
			}
		});
		frmNovaRaca.setType(Type.UTILITY);
		frmNovaRaca.setTitle("Nova Ra\u00E7a");
		frmNovaRaca.setResizable(false);
		frmNovaRaca.setBounds(100, 100, 300, 200);
		frmNovaRaca.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmNovaRaca.setLocationRelativeTo(null);
		frmNovaRaca.getContentPane().setLayout(null);
		
		JLabel lblDespcie = new JLabel("Esp\u00E9cie:");
		lblDespcie.setHorizontalAlignment(SwingConstants.CENTER);
		lblDespcie.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDespcie.setBounds(10, 16, 274, 14);
		frmNovaRaca.getContentPane().add(lblDespcie);
		
		textField = new JTextField();
		textField.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.GRAY, Color.GRAY, Color.DARK_GRAY));
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					btnSalvar.doClick();
				}
			}
		});
		textField.setColumns(10);
		textField.setBounds(10, 97, 274, 20);
		frmNovaRaca.getContentPane().add(textField);
		
		JButton button = new JButton("Cancelar");
		button.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		button.setBackground(new Color(245, 245, 245));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limit = 0;
				frmNovaRaca.dispose();
			}
		});
		button.setBounds(104, 138, 85, 23);
		frmNovaRaca.getContentPane().add(button);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnSalvar.setBackground(new Color(245, 245, 245));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "campo vazio");
					return;	
				}
				
				verificaSeTemRaca();
			}
		});
		btnSalvar.setBounds(199, 138, 85, 23);
		frmNovaRaca.getContentPane().add(btnSalvar);
		
		JLabel lblRaa = new JLabel("Ra\u00E7a:");
		lblRaa.setHorizontalAlignment(SwingConstants.CENTER);
		lblRaa.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRaa.setBounds(10, 72, 274, 14);
		frmNovaRaca.getContentPane().add(lblRaa);
		
		comboBox = new JComboBox<String>();
		comboBox.setBackground(SystemColor.controlHighlight);
		comboBox.setBounds(10, 41, 274, 20);
		frmNovaRaca.getContentPane().add(comboBox);
		
		ImageIcon icon = new ImageIcon("/img/gradiente_Branco.jpg");
		icon.setImage(icon.getImage().getScaledInstance(294, 171 ,100));
		JLabel label = new JLabel("");
		label.setBounds(0, 0, 294, 171);
		frmNovaRaca.getContentPane().add(label);
		label.setIcon(new ImageIcon(NovaRaca.class.getResource("/libs/img/fundoPequeno.jpg")));
		
		
		comboBoxEspecie();
	}
	
	void Salvar() {
		int idEspecie= ComboBox.pegaIdEspecie(comboBox.getSelectedItem().toString());
		
		String sql = "INSERT INTO raca (nome_ra,id_especie) VALUES (?,?)";
		try {
			PreparedStatement stmt = Conexao.conexao.prepareStatement(sql);
			stmt.setString(1, textField.getText().toLowerCase());
			stmt.setInt(2, idEspecie);
			stmt.execute();
			stmt.close();
			JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
			
			if(CadastrarAnimais.indexCbEspecie==0) {
				CadastrarAnimais.cbEspecie.setSelectedIndex(CadastrarAnimais.indexCbEspecie+1);
				CadastrarAnimais.cbEspecie.setSelectedIndex(CadastrarAnimais.indexCbEspecie);

			}else {
				CadastrarAnimais.cbEspecie.setSelectedIndex(CadastrarAnimais.indexCbEspecie-1);
				CadastrarAnimais.cbEspecie.setSelectedIndex(CadastrarAnimais.indexCbEspecie);

			}
			
			frmNovaRaca.dispose();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
	
	void verificaSeTemRaca() {
		int idEspecie= ComboBox.pegaIdEspecie(comboBox.getSelectedItem().toString());
		
		ResultSet dados=null;
		String sql = "select * from raca where nome_ra = ? and id_especie=?";
		
		try {
			PreparedStatement stmt = Conexao.conexao.prepareStatement(sql);
			stmt.setString(1, textField.getText().toLowerCase());
			stmt.setInt(2, idEspecie);
			dados = stmt.executeQuery();
			stmt.execute();
			stmt.close();
			
			if(!dados.next()) {
				Salvar();
			}else {
				JOptionPane.showMessageDialog(null, "Raça já cadastrada!");
				textField.selectAll();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void comboBoxEspecie() {
		ResultSet dados1;
		String sql = "SELECT (nome_es) FROM especie";
		try {
			PreparedStatement stmt = Conexao.conexao.prepareStatement(sql);
			dados1 = stmt.executeQuery();
			stmt.execute();
			stmt.close();

			while(dados1.next()) {
					comboBox.addItem(dados1.getString("nome_es"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("foi nao");
		}
	}
}
