package JanelasComtabil;

import java.awt.Color;
import java.awt.ComponentOrientation;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import DAO.Compras;
import JanelasAnimal.CadastrarAnimais;
import JanelasFuncionarios.CadastrarFuncionarios;
import crud.CrudCompras;
import outraJanelas.Login;
import outraJanelas.NovaFazenda;
import outraJanelas.Pergunta;
import outraJanelas.Principal;

public class NovaCompra {////
	
	int editar = 1;
	int x = 0;
	int testebtnProucurarar=0;
	private JFrame frmCompraDeInsumos;
	private JTextField tfProduto;
	private JTextField tfNota;
	private JTextField tfPreco;
	private JTextField txtFornecedor;
	private JButton btnLimpar;
	private JTextField txtProucurarProdutos;
	private JTable table;
	private Compras compra = new Compras();
	private JScrollPane scrollPane;
	private JSpinner spinner;
	static Compras addCompras = new Compras();
	static Compras editarCompras = new Compras();
	static int teste = 1; 
	static int x1=1;
	int idfazenda=0;
	int contador =+1;
	private JButton btnCancelar;
	private JFormattedTextField tfCNPJ;
	int mouseClick = 0;
	static String numero=null;
	private JFormattedTextField tfData;
	private JButton button;
	private JLabel lblSemDadosSalvos;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NovaCompra window = new NovaCompra();
					window.frmCompraDeInsumos.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NovaCompra() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		frmCompraDeInsumos = new JFrame();
		frmCompraDeInsumos.setIconImage(Toolkit.getDefaultToolkit().getImage(NovaCompra.class.getResource("/libs/img/libs/img/32x32.png")));
		frmCompraDeInsumos.setTitle("Compra de Insumos");
		frmCompraDeInsumos.setBounds(100, 100, 1080, 720);
		frmCompraDeInsumos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmCompraDeInsumos.setLocationRelativeTo(null);
		frmCompraDeInsumos.setResizable(false);
		
		 compra.setIdFazenda(Principal.fazenda.getIdFazenda());
		
		JLabel lblProduto = new JLabel("Produto:");
		lblProduto.setForeground(new Color(0, 0, 0));
		lblProduto.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblProduto.setBounds(10, 64, 78, 20);
		frmCompraDeInsumos.getContentPane().add(lblProduto);
		
		tfProduto = new JTextField();
		tfProduto.setForeground(new Color(0, 0, 0));
		tfProduto.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfProduto.addKeyListener(new KeyAdapter() {
			//EVENTO PARA QUANDO APERTAR "ENTER" DAR FOCO EM OUTRA CAIXA DE TEXTO
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode()==KeyEvent.VK_ENTER) 
					txtFornecedor.requestFocus();
				}
			});
		tfProduto.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), Color.GRAY, new Color(128, 128, 128), new Color(64, 64, 64)));
		tfProduto.setBounds(108, 65, 200, 20);
		frmCompraDeInsumos.getContentPane().add(tfProduto);
		tfProduto.setColumns(10);
		
		JLabel lblDataDaCompra = new JLabel("Data da Compra:");
		lblDataDaCompra.setForeground(new Color(0, 0, 0));
		lblDataDaCompra.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDataDaCompra.setBounds(405, 65, 125, 20);
		frmCompraDeInsumos.getContentPane().add(lblDataDaCompra);
	
		
		JLabel lblNumeroDaNota = new JLabel("Numero da Nota:");
		lblNumeroDaNota.setForeground(new Color(0, 0, 0));
		lblNumeroDaNota.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNumeroDaNota.setBounds(405, 100, 131, 20);
		frmCompraDeInsumos.getContentPane().add(lblNumeroDaNota);
		
		tfNota = new JTextField();
		tfNota.setForeground(new Color(0, 0, 0));
		tfNota.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfNota.addKeyListener(new KeyAdapter() {
			//EVENTO PARA QUANDO APERTAR "ENTER" DAR FOCO EM OUTRA CAIXA DE TEXTO
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					tfPreco.requestFocus();
				}
			}
		});
		tfNota.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.GRAY, Color.GRAY, Color.DARK_GRAY));
		tfNota.setColumns(10);
		tfNota.setBounds(535, 100, 188, 20);
		frmCompraDeInsumos.getContentPane().add(tfNota);
		
		JLabel lblPreo = new JLabel("Pre\u00E7o:");
		lblPreo.setForeground(new Color(0, 0, 0));
		lblPreo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPreo.setBounds(787, 65, 57, 20);
		frmCompraDeInsumos.getContentPane().add(lblPreo);
		

		
		
		tfPreco = new JTextField();
	
		tfPreco.setForeground(new Color(0, 0, 0));
		tfPreco.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfPreco.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE || (e.getKeyChar() == KeyEvent.VK_0)|| (e.getKeyChar() == KeyEvent.VK_1)|| (e.getKeyChar() == KeyEvent.VK_2) 
			            || (e.getKeyChar() == KeyEvent.VK_3)|| (e.getKeyChar() == KeyEvent.VK_4)|| (e.getKeyChar() == KeyEvent.VK_5)|| (e.getKeyChar() == KeyEvent.VK_6)|| (e.getKeyChar() == KeyEvent.VK_7)
			            || (e.getKeyChar() == KeyEvent.VK_8)|| (e.getKeyChar() == KeyEvent.VK_9)||(e.getKeyChar() == KeyEvent.VK_ENTER)||(e.getKeyChar() == KeyEvent.VK_COMMA))
			    { 
					
			    }else{
			         e.consume();
			    }
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					spinner.requestFocus();
				}
			}
		});
		tfPreco.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.GRAY, Color.GRAY, Color.DARK_GRAY));
		tfPreco.setBounds(886, 65, 164, 20);
		frmCompraDeInsumos.getContentPane().add(tfPreco);
		tfPreco.setColumns(10);
		
		JLabel lblFornecedor = new JLabel("Fornecedor:");
		lblFornecedor.setBackground(Color.GRAY);
		lblFornecedor.setForeground(new Color(0, 0, 0));
		lblFornecedor.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFornecedor.setBounds(10, 100, 100, 20);
		frmCompraDeInsumos.getContentPane().add(lblFornecedor);
		
		txtFornecedor = new JTextField();
		txtFornecedor.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.GRAY, Color.GRAY, Color.DARK_GRAY));
		txtFornecedor.setForeground(new Color(0, 0, 0));
		txtFornecedor.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtFornecedor.addKeyListener(new KeyAdapter() {
			//EVENTO PARA QUANDO APERTAR "ENTER" DAR FOCO EM OUTRA CAIXA DE TEXTO
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					tfCNPJ.requestFocus();
				}
			}
		});
		txtFornecedor.setBounds(108, 100, 200, 20);
		frmCompraDeInsumos.getContentPane().add(txtFornecedor);
		txtFornecedor.setColumns(10);
	
		JLabel lblCnpj = new JLabel("CNPJ:");
		lblCnpj.setForeground(new Color(0, 0, 0));
		lblCnpj.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCnpj.setBounds(10, 135, 66, 20);
		frmCompraDeInsumos.getContentPane().add(lblCnpj);
		
		
		
		//BOTÃO SALVAR E ADD OS DADOS NA TABELA DO BD
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setForeground(Color.DARK_GRAY);
		btnSalvar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnSalvar.setBackground(new Color(245, 245, 245));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//COMEÇO DO TRATAMENTO DE INFORMAÇÃO 
					if(tfProduto.getText().trim().equals("")) {
						tfProduto.requestFocus();
						JOptionPane.showMessageDialog(null, "Insira um Produto!", "ALERTA!",JOptionPane.WARNING_MESSAGE);
						return;
					}
					if(txtFornecedor.getText().trim().equals("")){
						txtFornecedor.requestFocus();
						JOptionPane.showMessageDialog(null, "Insira um Fornecedor!", "ALERTA!",JOptionPane.WARNING_MESSAGE);
						return;
					}
					if(tfCNPJ.getText().contains(" ")) {
						int x = JOptionPane.showConfirmDialog(null, "Você deseja deixar o CNPJ nulo?", "ALERTA!",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
						if (x== JOptionPane.YES_OPTION) {
							tfCNPJ.setText("000.000.000/0000-00");
							
						}else {
							tfCNPJ.requestFocus();
							return;
						}
					}	//as
					if(tfNota.getText().trim().equals("")) {
						int x = JOptionPane.showConfirmDialog(null, "Você deseja deixar a nota como nulo?", "ALERTA!",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
						if (x==JOptionPane.YES_OPTION) {
							tfNota.setText("00000000000000000");
							tfPreco.requestFocus();
							
						}else {
							tfNota.requestFocus();
							return;
						}
					}				
					if(tfPreco.getText().trim().equals("")) {
						tfPreco.requestFocus();
						JOptionPane.showMessageDialog(null, "Insira um valor!", "ALERTA!",JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					if (spinner.getValue().equals(0)) {
						JOptionPane.showMessageDialog(null, "Insira uma quantidade!", "ALERTA!",JOptionPane.WARNING_MESSAGE);
						spinner.requestFocus();
						return;
					}//FIM DOS TESTES DE TRATAMENTO DE INFORMAÇÃO
					if (editar==1) {
						x1=0;
						colocaDadosDAO();
						new CrudCompras().addCompras(addCompras);
						
						//TESTE DE CADASTRO DE PREÇO
						 numero = tfPreco.getText().toString();
						numero = numero.replace(".", ",");
						//FIM DO TESTE
						
						//ADD LINHA NA TABELA DEPOIS DE SALVAR OS DADOS
						JOptionPane.showMessageDialog(null, "Salvos com sucesso!","SUCESSO!",JOptionPane.INFORMATION_MESSAGE);
						if (scrollPane.getHeight()<=315) {
							int x = (teste*17)+scrollPane.getHeight();
							lblSemDadosSalvos.setVisible(false);
							scrollPane.setBounds(10, 253, 1054, x);
						}
						new CrudCompras();
						criaTabela(CrudCompras.selecionaCompras(compra));
						btnLimpar.doClick();
						
						
					}
					//TESTE DE SALVAR AS ALTERAÇÕES 
					if (editar==0) {
						int resposta = JOptionPane.showConfirmDialog(null, "Você deseja alterar os dados já salvos?","ALERTA", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
						if (resposta == 0) {
							x1=0;
							update();
							new CrudCompras().updCompras(addCompras);
						
							criaTabela(CrudCompras.selecionaCompras(compra));
							btnCancelar.doClick();
						
						}
					}
			}
		});
		btnSalvar.setBounds(961, 602, 89, 23);
		frmCompraDeInsumos.getContentPane().add(btnSalvar);
		
		//LIMPAR TODOS OS DADOS 
		btnLimpar = new JButton("Limpar");
		btnLimpar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnLimpar.setForeground(Color.DARK_GRAY);
		btnLimpar.setBackground(new Color(245, 245, 245));
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tfCNPJ.setText(null);
				spinner.setValue(0);
				tfNota.setText(null);
				tfProduto.setText(null);
				tfPreco.setText(null);
				txtFornecedor.setText(null);
				tfProduto.requestFocus();
				tfData.setText(null);
				
				
				
			}
		});
		btnLimpar.setBounds(860, 602, 89, 23);
		frmCompraDeInsumos.getContentPane().add(btnLimpar);
		
		//CANCELAR A OPERAÇÃO E VOLTAR PARA A TELA PRINCIPAL
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnCancelar.setForeground(Color.DARK_GRAY);
		btnCancelar.setBackground(new Color(245, 245, 245));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(editar==1) {
					frmCompraDeInsumos.dispose();
					Principal.frmPrincipal.setVisible(true);
				}else if(editar==0) {
					editar=1;
					btnLimpar.setEnabled(true);
					btnLimpar.doClick();
				}
			}
		});
		btnCancelar.setBounds(761, 602, 89, 23);
		frmCompraDeInsumos.getContentPane().add(btnCancelar);
		
		
		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setForeground(Color.BLACK);
		lblQuantidade.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblQuantidade.setBounds(787, 100, 89, 20);
		frmCompraDeInsumos.getContentPane().add(lblQuantidade);
		
		
		spinner = new JSpinner();
		spinner.setForeground(new Color(0, 0, 0));
		spinner.setFont(new Font("Tahoma", Font.PLAIN, 11));
		spinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinner.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.GRAY, Color.GRAY, Color.DARK_GRAY));
		spinner.setBounds(886, 100, 164, 20);
		frmCompraDeInsumos.getContentPane().add(spinner);
		
		
		txtProucurarProdutos = new JTextField();
		txtProucurarProdutos.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					button.doClick();
				}
			}
		});
		txtProucurarProdutos.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, new Color(128, 128, 128), new Color(128, 128, 128), Color.DARK_GRAY));
		txtProucurarProdutos.setToolTipText("");
		txtProucurarProdutos.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtProucurarProdutos.setForeground(Color.BLACK);
		txtProucurarProdutos.setColumns(10);
		txtProucurarProdutos.setBounds(10, 222, 331, 20);
		frmCompraDeInsumos.getContentPane().add(txtProucurarProdutos);
		
		
		
		
		
		button = 
				new JButton("Proucurar");
		
		button.addActionListener(new ActionListener() {
			
			//BOTÃO PROUCURAR
			public void actionPerformed(ActionEvent arg0) {
				//VARIAVEL PARA REGULAR O TAMNHO DA TABELA
				x1=0;	
				//CRIAR TABELA COM OS DADOS QUE FORAM PROUCURADOS 
				criaTabela(new CrudCompras().procurarCompra(txtProucurarProdutos.getText().toString(),idfazenda));
				//TRATAMENTO PARA AUMENTAR E DIMINUIR TABELA
				int tabel = table.getRowCount();
				if (table.getRowCount()>19) {
					tabel=18;
				}
				int linha = tabel*17;
				int valor = 23+linha;
				scrollPane.setBounds(10, 253, 1054, valor);
				if (!(txtProucurarProdutos.getText()).trim().equals("")) {
					if (table.getRowCount()==0) {
						int x2 = 23;
						scrollPane.setBounds(10, 253, 1054, x2);
						lblSemDadosSalvos.setVisible(true);
					}
				}else {
					//new CrudCompras();
					//(CrudCompras.selecionaCompras(compra));
		
				}
				//FIM DO TRATAMENTO 
				
				
			}
		});
		button.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(255, 255, 255), null, null, null));
		button.setForeground(Color.DARK_GRAY);
		button.setBackground(new Color(245, 245, 245));
		button.setFont(new Font("Tahoma", button.getFont().getStyle(), button.getFont().getSize()));
		button.setBounds(349, 222, 89, 23);
		frmCompraDeInsumos.getContentPane().add(button);
		
		try {
			tfCNPJ = new JFormattedTextField(new MaskFormatter("###.###.###/####-##"));
			tfCNPJ.setForeground(new Color(0, 0, 0));
			tfCNPJ.setFont(new Font("Tahoma", Font.PLAIN, 11));
			tfCNPJ.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.GRAY, Color.GRAY, Color.DARK_GRAY));
			tfCNPJ.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					if (arg0.getKeyCode()==KeyEvent.VK_ENTER) {
						tfData.requestFocus();
					}
				}
			});
			tfCNPJ.setBounds(108, 135, 200, 20);
			frmCompraDeInsumos.getContentPane().add(tfCNPJ);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		
		scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(245, 245, 245));
		scrollPane.setFont(new Font("Tahoma", Font.PLAIN, 11));
		scrollPane.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		scrollPane.setBounds(10, 253, 1054, 23);
		
		frmCompraDeInsumos.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setGridColor(SystemColor.activeCaption);
		table.setSelectionBackground(new Color(135, 206, 250));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
					
				btnLimpar.setEnabled(false);
				editar();
				
				}
		});
		
		table.setToolTipText("");
		table.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		table.setIgnoreRepaint(true);
		table.setFont(new Font("Tahoma", Font.PLAIN, 13));
		table.setForeground(new Color(0, 0, 0));
		table.setBackground(new Color(245, 245, 245));
		
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID da compra", "Produto", "Fornecedor", "CNPJ fornecedor", "N\u00FAmero da nota", "Quantidade ", "Pre\u00E7o", "Data da compra"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				true, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(5).setResizable(false);
		table.getColumnModel().getColumn(6).setResizable(false);
		table.getColumnModel().getColumn(7).setResizable(false);
	
		
	
	
		
		scrollPane.setViewportView(table);
		
		//IMAGEICON PARA COLOCAR IMAGEM NA TELA E REDIMENSIONAR 
		ImageIcon icon = new ImageIcon(NovaCompra.class.getResource("/libs/img/libs/img/compra12.png"));
		icon.setImage(icon.getImage().getScaledInstance(46, 48, 100));
		JLabel llll = new JLabel("");
		llll.setBounds(1004, 6, 46, 48);
		llll.setIcon(icon);
		
		
		//IMAGEICON PARA COLOCAR IMAGEM NA TELA E REDIMENSIONAR 
		//ImageIcon img = new ImageIcon("src/img/fundo3.jpg");
		//img.setImage(img.getImage().getScaledInstance(1074, 671, 100));
		frmCompraDeInsumos.getContentPane().add(llll);
		
		Date data = new Date();
		SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
		String formatada = formato.format(data);
		
		try {
			tfData = new JFormattedTextField(new MaskFormatter("####-##-##"));
			tfData.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					if (arg0.getKeyCode()==KeyEvent.VK_ENTER) {
						tfNota.requestFocus();
					}
				}
			});
			tfData.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.GRAY, Color.GRAY, Color.DARK_GRAY));
			tfData.setBounds(535, 65, 186, 20);
			frmCompraDeInsumos.getContentPane().add(tfData);
			tfData.setText(formatada);
			tfData.setToolTipText("aaaa-mm-dd");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JLabel lblComprasDeInsumos = new JLabel("Compras de insumos");
		lblComprasDeInsumos.setHorizontalAlignment(SwingConstants.CENTER);
		lblComprasDeInsumos.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblComprasDeInsumos.setBounds(10, 11, 1054, 25);
		frmCompraDeInsumos.getContentPane().add(lblComprasDeInsumos);
		
		lblSemDadosSalvos = new JLabel("Sem dados salvos!");
		lblSemDadosSalvos.setHorizontalAlignment(SwingConstants.CENTER);
		lblSemDadosSalvos.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSemDadosSalvos.setBounds(10, 296, 1054, 32);
		frmCompraDeInsumos.getContentPane().add(lblSemDadosSalvos);
		
		
		
		JLabel label = new JLabel("");
		label.setBackground(new Color(245, 245, 245));
		label.setIcon(new ImageIcon(NovaCompra.class.getResource("/libs/img/libs/img/teste13.jpg")));
		//label.setIcon(img);
		label.setBounds(0, -22, 1074, 671);
		frmCompraDeInsumos.getContentPane().add(label);
		
		
		//IF PARA VERIFICAR SE A TABLE ESTIVER VAZIA E DEIXAR VISIBLE.(FALSE)
		if (table.getRowCount()==0) {
			scrollPane.setVisible(false);
			lblSemDadosSalvos.setVisible(true);
			
		}
		
		//CHAMAR MÉTODO
		x1=1;
		new CrudCompras();
		criaTabela(CrudCompras.selecionaCompras(compra));
		menu();
	}
		//MÉTODO PARA COLOCAR OS DADOS NA TABELA a
	public void criaTabela(ResultSet rs) {
		
		DefaultTableModel tabela = (DefaultTableModel) table.getModel();
		tabela.setRowCount(0);
	
		try {
			while(rs.next()) {
				idfazenda=rs.getInt("id_fazenda");
				scrollPane.setVisible(true);
				lblSemDadosSalvos.setVisible(false);
				tabela.addRow(new Object[] {rs.getInt("id_compras"),rs.getString("produto"),rs.getString("fornecedor"),rs.getString("cnpj"),rs.getString("numero_nota"),
	 					rs.getInt("qtd"),rs.getDouble("preco"),rs.getString("data_compra")});
				
				
				//s
				//IF PARA FAZER A TABELA AUMENTAR 
				
				if (x1==1) {
					if (tabela.getRowCount() >= teste & scrollPane.getHeight()<=315) {
						teste=1;
						teste=+1;
						int x = (teste*17)+scrollPane.getHeight();
						scrollPane.setBounds(10, 253, 1054, x);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void colocaDadosDAO() {
		String preco = tfPreco.getText();
		preco = preco.replace(",", ".");
		int teste = (int) spinner.getValue();
		addCompras.setProduto(tfProduto.getText().toString());
		addCompras.setCnpj(tfCNPJ.getText().toString());
		addCompras.setDataCompra(tfData.getText().toString());
		addCompras.setFornecedor(txtFornecedor.getText().toString());
		addCompras.setNumeroNota(tfNota.getText().toString());
		addCompras.setQuantidade(teste);
		addCompras.setPreco(Double.parseDouble(preco));
		addCompras.setIdFazenda(Principal.fazenda.getIdFazenda());
	
	}
	
	public void editar() {
		int linha = table.getSelectedRow();
		
		tfProduto.setText(table.getValueAt(linha, 1).toString());
		txtFornecedor.setText(table.getValueAt(linha, 2).toString());
		tfCNPJ.setText(table.getValueAt(linha, 3).toString());
		tfNota.setText(table.getValueAt(linha, 4).toString());
		numero=table.getValueAt(linha, 6).toString();
		spinner.setValue(Integer.parseInt(table.getValueAt(linha, 5).toString()));
		tfData.setText(table.getValueAt(linha, 7).toString());
		

		addCompras.setId(Integer.parseInt(table.getValueAt(linha, 0).toString()));

		tfPreco.setText(numero);
		editar = 0;
	
	}
	public void update() {
		
		numero = tfPreco.getText().toString();
		numero = numero.replace(",", ".");
		addCompras.setProduto(tfProduto.getText().toString());
		addCompras.setCnpj(tfCNPJ.getText().toString());
		addCompras.setFornecedor(txtFornecedor.getText().toString());
		addCompras.setNumeroNota(tfNota.getText().toString());
		addCompras.setQuantidade((int) spinner.getValue());
		addCompras.setPreco(Double.parseDouble(numero));
		addCompras.setIdFazenda(Principal.fazenda.getIdFazenda());
		

	}
	public void menu() {	
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(105, 105, 105), new Color(105, 105, 105), new Color(105, 105, 105), new Color(105, 105, 105)));
		menuBar.setForeground(Color.GREEN);
		menuBar.setBackground(Color.DARK_GRAY);
		frmCompraDeInsumos.setJMenuBar(menuBar);
		
		JMenu mnInicio = new JMenu("");
		mnInicio.setBorder(new SoftBevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY));
		mnInicio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnInicio.setOpaque(true);
		mnInicio.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mnInicio.setIcon(new ImageIcon(NovaCompra.class.getResource("/libs/img/libs/img/Icone_Inicio.png")));
		mnInicio.setForeground(Color.WHITE);
		mnInicio.setBackground(Color.DARK_GRAY);
		mnInicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Principal.frmPrincipal.setVisible(true);
				frmCompraDeInsumos.dispose();
			}
		});
		menuBar.add(mnInicio);
		
		JMenu mnNewMenu = new JMenu("");
		mnNewMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnNewMenu.setIcon(new ImageIcon(NovaCompra.class.getResource("/libs/img/libs/img/Icone_GEstao.png")));
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
				frmCompraDeInsumos.dispose();
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
				frmCompraDeInsumos.dispose();
			}
		});
		
		JMenu mnNewMenu_1 = new JMenu("");
		mnNewMenu_1.setBorder(new SoftBevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY));
		
		mnNewMenu_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnNewMenu_1.setIcon(new ImageIcon(NovaCompra.class.getResource("/libs/img/libs/img/Icone_Financeiro.png")));
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
				//NovaCompra.main(null);
				//frmCompraDeInsumos.dispose();
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
				frmCompraDeInsumos.dispose();
			}
		});
		mntmNovaVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NovaVenda.main(null);
				frmCompraDeInsumos.dispose();
			}
		});
		
		JMenu mnOpes = new JMenu("");
		mnOpes.setBorder(new SoftBevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY));
		mnOpes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnOpes.setIcon(new ImageIcon(NovaCompra.class.getResource("/libs/img/libs/img/Icone_OPCAO.png")));
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
				frmCompraDeInsumos.dispose();
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
				Pergunta.outroFrame=frmCompraDeInsumos;
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
				frmCompraDeInsumos.dispose();
				Login.main(null);
			}
		});
		mnOpes.add(mntmDeslogar);
		
		
		mnOpes.add(mntmSada);
		frmCompraDeInsumos.getContentPane().setLayout(null);
	}
}
