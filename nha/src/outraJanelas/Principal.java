package outraJanelas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
//
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import DAO.Fazenda;
import JanelasAnimal.CadastrarAnimais;
import JanelasComtabil.NovaCompra;
import JanelasComtabil.NovaVenda;
import JanelasComtabil.Total;
import JanelasFuncionarios.CadastrarFuncionarios;

public class Principal {

	public static JFrame frmPrincipal;
	static JButton button;
	public static Fazenda fazenda = new Fazenda();
	private JButton btnAnimais;
	private JLabel lblAnimais;
	private JButton btnFuncionarios;
	private JLabel lblFuncionarios;
	private JButton btnCompras;
	private JLabel lblCompras;
	private JButton btnVendas;
	private JLabel lblVendas;
	private JButton btnUsuario;
	private JLabel lblUsuario;
	private JPanel panelSite;
	private JLabel lblImgSite;
	private JLabel lblSite;
	private JPanel panelPdf;
	private JLabel lblImgPdf;
	private JLabel lblManual;
	private JLabel lblVersao;
	private JLabel lblNewLabel;
	private JLabel foto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		
		
		
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					Principal window = new Principal();
					window.frmPrincipal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
	}

	/**
	 * Create the application.
	 */
	public Principal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
	
		frmPrincipal = new JFrame();
		frmPrincipal.setType(Type.POPUP);
		frmPrincipal.setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/libs/img/libs/img/32x32.png")));
		frmPrincipal.setTitle("Principal");
		frmPrincipal.setBounds(100, 100, 1080, 720);
		frmPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPrincipal.setResizable(false);
		frmPrincipal.setLocationRelativeTo(null);
		frmPrincipal.getContentPane().setLayout(null);
		
		Pergunta.main(null);
		Pergunta.contador = 0;
		
		ImageIcon iconAnimal = new ImageIcon(Principal.class.getResource("/libs/img/libs/img/vaca1.png"));
		iconAnimal.setImage(iconAnimal.getImage().getScaledInstance(135,101 ,100));
		btnAnimais = new JButton("");
		btnAnimais.setBorderPainted(false);
		btnAnimais.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAnimais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastrarAnimais.main(null);
				frmPrincipal.setVisible(false);
			}
		});
		btnAnimais.setContentAreaFilled(false);
		btnAnimais.setBackground(Color.GRAY);
		btnAnimais.setBounds(12, 30, 135,101);
		btnAnimais.setIcon(iconAnimal);
		frmPrincipal.getContentPane().add(btnAnimais);
		
		lblAnimais = new JLabel("Animais");
		lblAnimais.setForeground(Color.DARK_GRAY);
		lblAnimais.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAnimais.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnimais.setBounds(10, 132, 120, 14);
		frmPrincipal.getContentPane().add(lblAnimais);
		
		ImageIcon iconFuncionarios = new ImageIcon(Principal.class.getResource("/libs/img/libs/img/t3.png"));
		iconFuncionarios.setImage(iconFuncionarios.getImage().getScaledInstance(135, 101 ,100));
		btnFuncionarios = new JButton("");
		btnFuncionarios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnFuncionarios.setBorderPainted(false);
		btnFuncionarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastrarFuncionarios.main(null);
				frmPrincipal.setVisible(false);
				
			}
		});
		btnFuncionarios.setContentAreaFilled(false);
		btnFuncionarios.setBounds(245, 30, 135,101);
		btnFuncionarios.setIcon(iconFuncionarios);
		frmPrincipal.getContentPane().add(btnFuncionarios);
		
		lblFuncionarios = new JLabel("Funcionarios");
		lblFuncionarios.setHorizontalAlignment(SwingConstants.CENTER);
		lblFuncionarios.setForeground(Color.DARK_GRAY);
		lblFuncionarios.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFuncionarios.setBounds(255, 132, 120, 14);
		frmPrincipal.getContentPane().add(lblFuncionarios);
		//aa
		ImageIcon iconCompras = new ImageIcon(Principal.class.getResource("/libs/img/libs/img/compra12.png"));
		iconCompras.setImage(iconCompras.getImage().getScaledInstance(135,101 ,100));
		btnCompras = new JButton("");
		btnCompras.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCompras.setBorderPainted(false);
		btnCompras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NovaCompra.main(null);
				frmPrincipal.setVisible(false);
			}
		});
		btnCompras.setContentAreaFilled(false);
		btnCompras.setBounds(470, 30, 135,101);
		btnCompras.setIcon(iconCompras);
		frmPrincipal.getContentPane().add(btnCompras);
		
		lblCompras = new JLabel("Compras");
		lblCompras.setHorizontalAlignment(SwingConstants.CENTER);
		lblCompras.setForeground(Color.DARK_GRAY);
		lblCompras.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCompras.setBounds(470, 133, 120, 14);
		frmPrincipal.getContentPane().add(lblCompras);
		
		ImageIcon iconVendas = new ImageIcon(Principal.class.getResource("/libs/img/libs/img/t1.png"));
		iconVendas.setImage(iconVendas.getImage().getScaledInstance(135,101 ,100));
		btnVendas = new JButton("");
		btnVendas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnVendas.setBorderPainted(false);
		btnVendas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NovaVenda.main(null);
				frmPrincipal.setVisible(false);
			}
		});
		btnVendas.setContentAreaFilled(false);
		btnVendas.setBounds(700, 30, 135,101);
		btnVendas.setIcon(iconVendas);
		frmPrincipal.getContentPane().add(btnVendas);
		
		lblVendas = new JLabel("Vendas");
		lblVendas.setHorizontalAlignment(SwingConstants.CENTER);
		lblVendas.setForeground(Color.DARK_GRAY);
		lblVendas.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblVendas.setBounds(698, 133, 120, 14);
		frmPrincipal.getContentPane().add(lblVendas);
		
		ImageIcon iconUsuario = new ImageIcon(Principal.class.getResource("/libs/img/libs/img/usu1.png"));
		iconUsuario.setImage(iconUsuario.getImage().getScaledInstance(135,101 ,100));
		btnUsuario = new JButton("");
		btnUsuario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUsuario.setBorderPainted(false);
		btnUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario.main(null);;
				frmPrincipal.setVisible(false);
			}
		});
		btnUsuario.setContentAreaFilled(false);
		btnUsuario.setBounds(918, 30, 135,101);
		btnUsuario.setIcon(iconUsuario);
		frmPrincipal.getContentPane().add(btnUsuario);
		
		lblUsuario = new JLabel("Usu\u00E1rio");
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario.setForeground(Color.DARK_GRAY);
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUsuario.setBounds(918, 133, 120, 14);
		frmPrincipal.getContentPane().add(lblUsuario);
		
		panelSite = new JPanel();
		panelSite.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					URI link = new URI("www.youtube.com");//abrir navegador
					Desktop.getDesktop().browse(link);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		panelSite.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelSite.setOpaque(false);
		panelSite.setBounds(10, 454, 186, 40);
		frmPrincipal.getContentPane().add(panelSite);
		panelSite.setLayout(new BorderLayout(0, 0));
		
		ImageIcon iconSite = new ImageIcon("src/img/WWW-Icon.png");
		iconSite.setImage(iconSite.getImage().getScaledInstance(40, 40 ,100));
		lblImgSite = new JLabel("");
		panelSite.add(lblImgSite, BorderLayout.WEST);
		lblImgSite.setIcon(new ImageIcon(Principal.class.getResource("/libs/img/libs/img/www.png")));
		
		lblSite = new JLabel("  www.gan.com");
		panelSite.add(lblSite, BorderLayout.CENTER);
		
		panelPdf = new JPanel();
		panelPdf.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					URI link = new URI("www.youtube.com");//abrir navegador
					Desktop.getDesktop().browse(link);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panelPdf.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelPdf.setOpaque(false);
		panelPdf.setBounds(10, 506, 186, 40);
		frmPrincipal.getContentPane().add(panelPdf);
		panelPdf.setLayout(new BorderLayout(0, 0));
		
		ImageIcon iconPdf = new ImageIcon("src/img/pdf.png");
		iconPdf.setImage(iconPdf.getImage().getScaledInstance(40, 40, 100));
		lblImgPdf = new JLabel("");
		panelPdf.add(lblImgPdf, BorderLayout.WEST);
		lblImgPdf.setIcon(new ImageIcon(Principal.class.getResource("/libs/img/libs/img/pdf.png")));
		
		lblManual = new JLabel("  Manual");
		panelPdf.add(lblManual, BorderLayout.CENTER);
		
		lblVersao = new JLabel("Versao 2.0");
		lblVersao.setForeground(Color.BLACK);
		lblVersao.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVersao.setBounds(984, 645, 80, 14);
		frmPrincipal.getContentPane().add(lblVersao);
		
		lblNewLabel = new JLabel("Todos os direitos reservados \u00A92017-2018");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(10, 590, 1054, 29);
		frmPrincipal.getContentPane().add(lblNewLabel);
		
		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon(Principal.class.getResource("/libs/logo Principal.png")));
		label.setBounds(10, 233, 1054, 300);
		frmPrincipal.getContentPane().add(label);
		
		foto = new JLabel("");
		foto.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		foto.setIcon(new ImageIcon(Principal.class.getResource("/libs/img/libs/img/teste2.jpg")));
		foto.setFont(new Font("Tahoma", Font.BOLD, 11));
		foto.setForeground(Color.WHITE);
		foto.setBorder(null);
		foto.setBounds(0, 0, 1074, 634);
		frmPrincipal.getContentPane().add(foto);
		
		
		menu();
	}
	
	public void menu() {	
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(105, 105, 105), new Color(105, 105, 105), new Color(105, 105, 105), new Color(105, 105, 105)));
		menuBar.setForeground(Color.GREEN);
		menuBar.setBackground(Color.DARK_GRAY);
		frmPrincipal.setJMenuBar(menuBar);
		
		JMenu mnInicio = new JMenu("");
		mnInicio.setBorder(new SoftBevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY));
		mnInicio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnInicio.setOpaque(true);
		mnInicio.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mnInicio.setIcon(new ImageIcon(Principal.class.getResource("/libs/img/libs/img/Icone_Inicio.png")));
		mnInicio.setForeground(Color.WHITE);
		mnInicio.setBackground(Color.DARK_GRAY);
		mnInicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Principal.frmPrincipal.setVisible(true);
				//frmPrincipal.dispose();
			}
		});
		menuBar.add(mnInicio);
		
		JMenu mnNewMenu = new JMenu("");
		mnNewMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnNewMenu.setIcon(new ImageIcon(Principal.class.getResource("/libs/img/libs/img/Icone_GEstao.png")));
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
				frmPrincipal.dispose();
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
				frmPrincipal.dispose();
			}
		});
		
		JMenu mnNewMenu_1 = new JMenu("");
		mnNewMenu_1.setBorder(new SoftBevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY));
		
		mnNewMenu_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnNewMenu_1.setIcon(new ImageIcon(Principal.class.getResource("/libs/img/libs/img/Icone_Financeiro.png")));
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
				frmPrincipal.dispose();
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
				frmPrincipal.dispose();
			}
		});
		mntmNovaVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NovaVenda.main(null);
				frmPrincipal.dispose();
			}
		});
		
		JMenu mnOpes = new JMenu("");
		mnOpes.setBorder(new SoftBevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY));
		mnOpes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnOpes.setIcon(new ImageIcon(Principal.class.getResource("/libs/img/libs/img/Icone_OPCAO.png")));
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
				frmPrincipal.dispose();
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
				Pergunta.outroFrame = frmPrincipal;
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
				frmPrincipal.dispose();
				Login.main(null);
			}
		});
		mnOpes.add(mntmDeslogar);
		
		
		
		mnOpes.add(mntmSada);
		frmPrincipal.getContentPane().setLayout(null);
		frmPrincipal.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{frmPrincipal.getContentPane(), btnAnimais, lblAnimais, btnFuncionarios, lblFuncionarios, btnCompras, lblCompras, btnVendas, lblVendas, btnUsuario, lblUsuario, panelSite, lblImgSite, lblSite, panelPdf, lblImgPdf, lblManual, lblVersao, lblNewLabel, foto, menuBar, mnInicio, mnNewMenu, mntmCadastrarAnimais, mntmCadastrarFuncionarios, mnNewMenu_1, mntmCompra, mntmNovaVenda, mntmTotal, mnOpes, mntmNovaFazenda, mntmMudarFazenda, mntmDeslogar, mntmSada}));
	}
}
