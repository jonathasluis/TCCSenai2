package outraJanelas;

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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

import DAO.Fazenda;
import Imagem.MetodosImagem;
import JanelasAnimal.CadastrarAnimais;
import JanelasComtabil.NovaCompra;
import JanelasComtabil.NovaVenda;
import JanelasComtabil.Total;
import JanelasFuncionarios.CadastrarFuncionarios;
import banco.Conexao;
import crud.CrudFazenda;

public class NovaFazenda {

	private JFrame frmNovaFazenda;
	private JTextField tfNome;
	private JTextField tfTamanho;
	private JTextField tfEscritura;
	private JTextField tfProprietario;
	public static JLabel lblImg;
	public static JPanel panel;
	public static File img;
	MetodosImagem mI = new MetodosImagem();
	private JButton btnLimpar;
	private JTextField tfQtdAnimais;
	private JTextField tfQtdFuncionarios;
	int contadorEditar = 0;
	private JButton btnDeletar;
	private JTextArea taDescricao;
	Fazenda fazenda = new Fazenda();

	private JTable tabela;
	
	//tabela
	static int teste = 1; 
	static int x1=1;
	private JScrollPane scrollPane;
	//tabela

	//private JTable table;
	JButton btnCancelar;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NovaFazenda window = new NovaFazenda();
					window.frmNovaFazenda.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NovaFazenda() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNovaFazenda = new JFrame();
		frmNovaFazenda.setIconImage(Toolkit.getDefaultToolkit().getImage(NovaFazenda.class.getResource("/libs/img/libs/img/32x32.png")));
		frmNovaFazenda.setTitle("Nova Fazenda");
		frmNovaFazenda.setBounds(100, 100, 1080,720);
		frmNovaFazenda.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmNovaFazenda.setLocationRelativeTo(null);
		frmNovaFazenda.setResizable(false);
		frmNovaFazenda.getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setToolTipText("2 cliques para selecionar a imagem");
		panel.setOpaque(false);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(774, 25, 290, 217);
		frmNovaFazenda.getContentPane().add(panel);
		panel.setLayout(new CardLayout(0, 0));
		
		lblImg = new JLabel("");
		lblImg.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getClickCount()==2) {
					img = mI.selecionaImg();
					if(img != null) {
						lblImg.setHorizontalAlignment(SwingConstants.LEADING);
					}
					mI.abrirImagem(img, img, panel, lblImg,null);
				}
			}
		});
		lblImg.setHorizontalAlignment(SwingConstants.CENTER);
		lblImg.setIcon(new ImageIcon(NovaFazenda.class.getResource("/libs/img/libs/img/128x128.png")));
		panel.add(lblImg, "name_20449716211995");
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnSalvar.setBackground(new Color(245, 245, 245));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tfNome.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "insira um nome", "ALERTA!",JOptionPane.WARNING_MESSAGE);
					tfNome.requestFocus();
					return;
				}
				if(tfTamanho.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "insira o tamanho da propriedade", "ALERTA!",JOptionPane.WARNING_MESSAGE);
					tfTamanho.requestFocus();
					return;
				}
				if (tfEscritura.getText().trim().equals("")) {
					int resposta = JOptionPane.showConfirmDialog(null, "Deseja deixar o campo escritura nulo", "ALERTA!",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
					if (resposta == JOptionPane.YES_OPTION) {
						tfEscritura.setText("00000");
					}else {
						tfEscritura.requestFocus();
						return;
					}
				}
				if (tfProprietario.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "insira o nome do proprietário", "ALERTA!",JOptionPane.WARNING_MESSAGE);
					tfProprietario.requestFocus();
					return;
				}
				if (taDescricao.getText().trim().equals("")) {
					int resposta = JOptionPane.showConfirmDialog(null, "Deseja deixar o campo descrição nulo", "ALERTA!",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
					if (resposta == JOptionPane.YES_OPTION) {
						taDescricao.setText(null);
					}else {
						taDescricao.requestFocus();
						return;
					}
				}
				
				if (contadorEditar==0) {
					DAOFazenda();
					//tabela
					x1=0;
					if (scrollPane.getHeight()<=315) {
						int x = (teste*16)+scrollPane.getHeight();
						scrollPane.setBounds(10, 253, 1054, x);
					}
					//tabela
					new CrudFazenda().addFazenda(fazenda);
					JOptionPane.showMessageDialog(null, "Fazenda cadastrada com sucesso!");
					btnLimpar.doClick();
					colocaDadosNaTabela(CrudFazenda.selecionaFazenda(Pergunta.usuario));
					
					

				}

				if (contadorEditar==1) {
					int resposta = JOptionPane.showConfirmDialog(null, "voce deseja alterar essa Fazenda? ", "alerta",
							JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
					if(resposta==JOptionPane.YES_OPTION) {
						DAOFazenda();
						new CrudFazenda().updFazenda(fazenda);
						btnCancelar.doClick();
						JOptionPane.showMessageDialog(null, "Fazenda alterada com sucesso!");
						colocaDadosNaTabela(CrudFazenda.selecionaFazenda(Pergunta.usuario));
					}else {
						return;
					}
				}	

			}
		});
		btnSalvar.setBounds(975, 602, 89, 23);
		frmNovaFazenda.getContentPane().add(btnSalvar);
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(245, 245, 245), null, null, null));
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tfEscritura.setText(null);
				tfNome.setText(null);
				tfTamanho.setText(null);
				tfProprietario.setText(null);
				taDescricao.setText(null);
				lblImg.setHorizontalAlignment(SwingConstants.CENTER);
				lblImg.setIcon(new ImageIcon(NovaFazenda.class.getResource("/libs/img/libs/img/128x128.png")));
				tfQtdAnimais.setText("0");
				tfQtdFuncionarios.setText("0");
				img=null;
			}
		});
		btnLimpar.setBounds(676, 602, 89, 23);
		btnLimpar.setBackground(new Color(245, 245, 245));
		frmNovaFazenda.getContentPane().add(btnLimpar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (contadorEditar==0) {
					Principal.frmPrincipal.setVisible(true);
					frmNovaFazenda.dispose();
				}
				if(contadorEditar==1) {
					contadorEditar=0;
					btnDeletar.setEnabled(false);
					btnLimpar.setEnabled(true);
					btnLimpar.doClick();
				}
			}
		});
		btnCancelar.setBounds(778, 602, 89, 23);
		btnCancelar.setBackground(new Color(245, 245, 245));
		frmNovaFazenda.getContentPane().add(btnCancelar);
		
		btnDeletar = new JButton("Deletar");
		btnDeletar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int resposta = JOptionPane.showConfirmDialog(null, "Todos os dados dessa Fazenda serão excluidos,deseja continuar", "ALERTA!",
						JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				if(resposta==JOptionPane.YES_OPTION) {
					new CrudFazenda().deletaFazenda(fazenda);
					btnCancelar.doClick();//para zerar variavel editar e habilitar os botoes
					btnLimpar.doClick();
					colocaDadosNaTabela(CrudFazenda.selecionaFazenda(Pergunta.usuario));
					
					//tabela
					x1=0;
					if (tabela.getRowCount()<=18) {
						int x = scrollPane.getHeight()-16;
						scrollPane.setBounds(10, 253, 1054, x);
					}
					//tabela
					
					if(fazenda.getIdFazenda()==Principal.fazenda.getIdFazenda()) {//se a fazenda logada for excluida manda mudar
						Pergunta.main(null);
						Pergunta.contador = 1;
						Pergunta.outroFrame=frmNovaFazenda;
					}
				}
			}
		});
		btnDeletar.setEnabled(false);
		btnDeletar.setBounds(876, 602, 89, 23);
		btnDeletar.setBackground(new Color(245, 245, 245));
		frmNovaFazenda.getContentPane().add(btnDeletar);
		
		JLabel lblNonaFazenda = new JLabel("Fazendas");
		lblNonaFazenda.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNonaFazenda.setHorizontalAlignment(SwingConstants.CENTER);
		lblNonaFazenda.setBounds(10, 11, 1054, 25);
		frmNovaFazenda.getContentPane().add(lblNonaFazenda);
		
		tfNome = new JTextField();
		tfNome.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.GRAY, Color.GRAY, Color.DARK_GRAY));
		tfNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					tfTamanho.requestFocus();
				}
			}
		});
		tfNome.setBounds(80, 61, 200, 20);
		frmNovaFazenda.getContentPane().add(tfNome);
		tfNome.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNome.setBounds(10, 60, 60, 20);
		frmNovaFazenda.getContentPane().add(lblNome);
		
		tfTamanho = new JTextField();
		tfTamanho.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.GRAY, Color.GRAY, Color.DARK_GRAY));
		tfTamanho.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					tfEscritura.requestFocus();
				}
			}
		});
		tfTamanho.setBounds(80, 92, 200, 20);
		frmNovaFazenda.getContentPane().add(tfTamanho);
		tfTamanho.setColumns(10);
		
		JLabel lblTamanho = new JLabel("Tamanho:");
		lblTamanho.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTamanho.setBounds(10, 91, 60, 20);
		frmNovaFazenda.getContentPane().add(lblTamanho);
		
		tfEscritura = new JTextField();
		tfEscritura.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.GRAY, Color.GRAY, Color.DARK_GRAY));
		tfEscritura.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					tfProprietario.requestFocus();
				}
			}
		});
		tfEscritura.setColumns(10);
		tfEscritura.setBounds(80, 123, 200, 20);
		frmNovaFazenda.getContentPane().add(tfEscritura);
		
		JLabel lblEscritura = new JLabel("Escritura:");
		lblEscritura.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEscritura.setBounds(10, 123, 60, 20);
		frmNovaFazenda.getContentPane().add(lblEscritura);
		
		tfProprietario = new JTextField();
		tfProprietario.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.GRAY, Color.GRAY, Color.DARK_GRAY));
		tfProprietario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					taDescricao.requestFocus();
				}
			}
		});
		tfProprietario.setColumns(10);
		tfProprietario.setBounds(510, 61, 190, 20);
		frmNovaFazenda.getContentPane().add(tfProprietario);
		
		JLabel lblPro = new JLabel("Propriet\u00E1rio:");
		lblPro.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPro.setBounds(420, 61, 80, 20);
		frmNovaFazenda.getContentPane().add(lblPro);
		
		JLabel lblDescrio_1 = new JLabel("Descri\u00E7\u00E3o:");
		lblDescrio_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblDescrio_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDescrio_1.setBounds(420, 92, 70, 150);
		frmNovaFazenda.getContentPane().add(lblDescrio_1);
		
		scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(245, 245, 245));
		scrollPane.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setInheritsPopupMenu(true);
		scrollPane.setBounds(10, 253, 1054, 23);
		frmNovaFazenda.getContentPane().add(scrollPane);
		
		tabela = new JTable();
		tabela.setSelectionBackground(new Color(135, 206, 250));
		tabela.setGridColor(SystemColor.activeCaption);
		tabela.setBackground(new Color(245, 245, 245));
		tabela.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				pegaDadosDaTabela();
				btnLimpar.setEnabled(false);
				btnDeletar.setEnabled(true);
				contadorEditar=1;
			}
		});
		tabela.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID ", "Nome da Fazenda", "Tamanho da Fazenda", "Escritura da Fazenda", "Descri\u00E7\u00E3o da Fazenda", "Proprietario"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tabela.getColumnModel().getColumn(0).setResizable(false);
		tabela.getColumnModel().getColumn(0).setPreferredWidth(99);
		tabela.getColumnModel().getColumn(1).setResizable(false);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(203);
		tabela.getColumnModel().getColumn(2).setResizable(false);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(160);
		tabela.getColumnModel().getColumn(3).setResizable(false);
		tabela.getColumnModel().getColumn(3).setPreferredWidth(160);
		tabela.getColumnModel().getColumn(4).setResizable(false);
		tabela.getColumnModel().getColumn(4).setPreferredWidth(270);
		tabela.getColumnModel().getColumn(5).setResizable(false);
		tabela.getColumnModel().getColumn(5).setPreferredWidth(160);
		tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tabela);
		
		JLabel lblQuantidadeDeAnimais = new JLabel("Quantidade de animais:");
		lblQuantidadeDeAnimais.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblQuantidadeDeAnimais.setBounds(10, 178, 157, 20);
		frmNovaFazenda.getContentPane().add(lblQuantidadeDeAnimais);
		
		JLabel lblQuantidadeDeFuncionaris = new JLabel("Quantidade de funcionarios:");
		lblQuantidadeDeFuncionaris.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblQuantidadeDeFuncionaris.setBounds(10, 209, 180, 20);
		frmNovaFazenda.getContentPane().add(lblQuantidadeDeFuncionaris);
		
		tfQtdAnimais = new JTextField();
		tfQtdAnimais.setText("0");
		tfQtdAnimais.setEditable(false);
		tfQtdAnimais.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfQtdAnimais.setOpaque(false);
		tfQtdAnimais.setBounds(200, 178, 76, 20);
		frmNovaFazenda.getContentPane().add(tfQtdAnimais);
		tfQtdAnimais.setColumns(10);
		
		tfQtdFuncionarios = new JTextField();
		tfQtdFuncionarios.setText("0");
		tfQtdFuncionarios.setOpaque(false);
		tfQtdFuncionarios.setEditable(false);
		tfQtdFuncionarios.setColumns(10);
		tfQtdFuncionarios.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfQtdFuncionarios.setBounds(200, 210, 76, 20);
		frmNovaFazenda.getContentPane().add(tfQtdFuncionarios);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(510, 92, 190, 150);
		frmNovaFazenda.getContentPane().add(scrollPane_1);
		
		taDescricao = new JTextArea();
		taDescricao.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.GRAY, Color.GRAY, Color.DARK_GRAY));
		taDescricao.setLineWrap(true);
		taDescricao.setWrapStyleWord(true);
		scrollPane_1.setViewportView(taDescricao);
		
		JLabel lblFUN = new JLabel("");
		lblFUN.setIcon(new ImageIcon(NovaFazenda.class.getResource("/libs/img/libs/img/32x32Fun.png")));
		lblFUN.setBounds(286, 209, 32, 37);
		frmNovaFazenda.getContentPane().add(lblFUN);
		
		JLabel lblVACA = new JLabel("");
		lblVACA.setIcon(new ImageIcon(NovaFazenda.class.getResource("/libs/img/libs/img/32x32Vaca.png")));
		lblVACA.setBounds(286, 167, 32, 43);
		frmNovaFazenda.getContentPane().add(lblVACA);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(NovaFazenda.class.getResource("/libs/img/libs/img/teste13.jpg")));
		label.setBounds(0, -22, 1074, 670);
		frmNovaFazenda.getContentPane().add(label);
		
		//tabela
				x1=1;
				//tabela
		menu();
		colocaDadosNaTabela(CrudFazenda.selecionaFazenda(Pergunta.usuario));
		
		//tabela
				//IF PARA VERIFICAR SE A TABLE ESTIVER VAZIA E DEIXAR VISIBLE.(FALSE)
				if (tabela.getRowCount()== 0) {
					scrollPane.setVisible(false);
				}
				//tabela
	}
	
	void DAOFazenda() {
		fazenda.setNome(tfNome.getText());
		fazenda.setTamanho(tfTamanho.getText());
		fazenda.setEscritura(tfEscritura.getText());
		fazenda.setProprietario(tfProprietario.getText());
		fazenda.setDescricao(taDescricao.getText());
		fazenda.setImg(mI.getImagem(img, panel));
		fazenda.setIdUsuario(Pergunta.usuario.getIdUsuario());
	}
	
	void colocaDadosNaTabela(ResultSet rs) {
		//tabela
				scrollPane.setVisible(true);
				//tabela
		DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
		modelo.setNumRows(0);
		
		try {
			while (rs.next()) {
				modelo.addRow(new Object[] {rs.getInt("idfazenda"),rs.getString("nome"),rs.getString("tamanho"),rs.getString("escritura"),rs.getString("descri"),
						rs.getString("proprietario")});	
				
				//tabela
				if (x1==1) {
					if (tabela.getRowCount() >= teste & scrollPane.getHeight()<=339) {
						teste=1;
						teste=+1;
						int x = (teste*16)+scrollPane.getHeight();
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
		
		fazenda.setIdFazenda(Integer.parseInt(tabela.getValueAt(linha, 0).toString()));
		fazenda.setNome(tabela.getValueAt(linha, 1).toString());
		fazenda.setTamanho(tabela.getValueAt(linha, 2).toString());
		fazenda.setEscritura(tabela.getValueAt(linha, 3).toString());
		fazenda.setDescricao(tabela.getValueAt(linha, 4).toString());
		fazenda.setProprietario(tabela.getValueAt(linha, 5).toString());
		
		ResultSet rs = null;
		String sql = "SELECT (img) FROM fazenda WHERE idfazenda=?";
		
		try {
			PreparedStatement stmt = Conexao.conexao.prepareStatement(sql);
			stmt.setInt(1, Integer.parseInt(tabela.getValueAt(linha, 0).toString()));
			rs = stmt.executeQuery();
			stmt.execute();
			stmt.close();
				
			if(rs.next()) {
				fazenda.setImg(rs.getBytes("img"));
			}
				
			tfNome.setText(fazenda.getNome());
			tfTamanho.setText(fazenda.getTamanho());
			tfEscritura.setText(fazenda.getEscritura());
			taDescricao.setText(fazenda.getDescricao());
			tfProprietario.setText(fazenda.getProprietario());
			contaQtdAnimaisFuncionarios(fazenda.getIdFazenda());
			
			if(fazenda.getImg()!= null) {
				lblImg.setHorizontalAlignment(SwingConstants.LEADING);
				mI.abrirImagem(fazenda.getImg(), null, panel, lblImg, fazenda.getImg());
			}else {
				lblImg.setHorizontalAlignment(SwingConstants.CENTER);
				lblImg.setIcon(new ImageIcon(NovaFazenda.class.getResource("/libs/img/libs/img/128x128.png")));
			}
			
		} catch (SQLException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void contaQtdAnimaisFuncionarios(int idFazenda) {
		int qtdAnimais=0;
		ResultSet rsAnimais = null;
		String sqlAnimais = "SELECT quantidade FROM animais where idfazenda=?";
		try {
			PreparedStatement stmtAnimais = Conexao.conexao.prepareStatement(sqlAnimais);
			stmtAnimais.setInt(1, idFazenda);
			rsAnimais = stmtAnimais.executeQuery();
			stmtAnimais.execute();
			stmtAnimais.close();
			
			while (rsAnimais.next()) {
				qtdAnimais += rsAnimais.getInt("quantidade");//faz a soma da quantidade de animais
			}
			tfQtdAnimais.setText(String.valueOf(qtdAnimais));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ResultSet rsFuncionarios = null;
		String sqlFuncionarios = "SELECT count(nome_fun) AS qtdFuncionarios FROM funcionarios where idfazenda=?";//vai retornar um valor contado a qtd de linhas
		try {
			PreparedStatement stmtFuncionarios = Conexao.conexao.prepareStatement(sqlFuncionarios);
			stmtFuncionarios.setInt(1, idFazenda);
			rsFuncionarios = stmtFuncionarios.executeQuery();
			stmtFuncionarios.execute();
			stmtFuncionarios.close();
			
			if (rsFuncionarios.next()) {
				tfQtdFuncionarios.setText(rsFuncionarios.getString("qtdFuncionarios"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void menu() {	
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(105, 105, 105), new Color(105, 105, 105), new Color(105, 105, 105), new Color(105, 105, 105)));
		menuBar.setForeground(Color.GREEN);
		menuBar.setBackground(Color.DARK_GRAY);
		frmNovaFazenda.setJMenuBar(menuBar);
		
		JMenu mnInicio = new JMenu("");
		mnInicio.setBorder(new SoftBevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY));
		mnInicio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnInicio.setOpaque(true);
		mnInicio.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mnInicio.setIcon(new ImageIcon(NovaFazenda.class.getResource("/libs/img/libs/img/Icone_Inicio.png")));
		mnInicio.setForeground(Color.WHITE);
		mnInicio.setBackground(Color.DARK_GRAY);
		mnInicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Principal.frmPrincipal.setVisible(true);
				frmNovaFazenda.dispose();
			}
		});
		menuBar.add(mnInicio);
		
		JMenu mnNewMenu = new JMenu("");
		mnNewMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnNewMenu.setIcon(new ImageIcon(NovaFazenda.class.getResource("/libs/img/libs/img/Icone_GEstao.png")));
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
				frmNovaFazenda.dispose();
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
				frmNovaFazenda.dispose();
			}
		});
		
		JMenu mnNewMenu_1 = new JMenu("");
		mnNewMenu_1.setBorder(new SoftBevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY));
		
		mnNewMenu_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnNewMenu_1.setIcon(new ImageIcon(NovaFazenda.class.getResource("/libs/img/libs/img/Icone_Financeiro.png")));
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
				frmNovaFazenda.dispose();
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
				frmNovaFazenda.dispose();
			}
		});
		mntmNovaVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NovaVenda.main(null);
				frmNovaFazenda.dispose();
			}
		});
		
		JMenu mnOpes = new JMenu("");
		mnOpes.setBorder(new SoftBevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY));
		mnOpes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnOpes.setIcon(new ImageIcon(NovaFazenda.class.getResource("/libs/img/libs/img/Icone_OPCAO.png")));
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
				//N0ovaFazenda.main(null);
				//frmPrincipal.dispose();
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
				Pergunta.outroFrame = frmNovaFazenda;
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
				frmNovaFazenda.dispose();
				Login.main(null);
			}
		});
		mnOpes.add(mntmDeslogar);
		
	
		
		mnOpes.add(mntmSada);
		frmNovaFazenda.getContentPane().setLayout(null);
	}
}