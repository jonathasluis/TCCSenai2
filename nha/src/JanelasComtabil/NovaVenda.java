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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
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

import DAO.Animal;
import DAO.Vendas;
import JanelasAnimal.CadastrarAnimais;
import JanelasFuncionarios.CadastrarFuncionarios;
import banco.Conexao;
import crud.CrudAnimal;
import crud.CrudVendas;
import outraJanelas.Login;
import outraJanelas.NovaFazenda;
import outraJanelas.Pergunta;
import outraJanelas.Principal;

public class NovaVenda {
//
	private JFrame frmNovaVenda;
	private JTextField tfProduto;
	private JRadioButton rdbtnPlantio;
	private JRadioButton rdbtnAnimal;
	private JRadioButton rdbtnSubproduto;
	private JTextField tfPreco;
	private JTextField tfCliente;
	public static JComboBox<String> cbAnimal;
	private JLabel lblProduto;
	private JLabel lblAnimal;
	int id;
	private JButton btnLimpar;
	private JTable table;
	private JTextField tfProcurar;
	private JFormattedTextField ftfData;
	private MaskFormatter mask;
	static String numero=null;
	Vendas venda = new Vendas();
	private JSpinner spinner;
	int contadorEditar = 0;
	private JScrollPane scrollPane;
	int quantidadeAntes=0;
	private JTextField tfNota;
	private JButton btnCancelar;
	
	
	
	static int teste = 1; 
	static int x1=1;
	private JButton btnProcurar;
	private JLabel lblSemdados;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NovaVenda window = new NovaVenda();
					window.frmNovaVenda.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NovaVenda() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {//inicio formatação mascara
			mask = new MaskFormatter("####-##-##");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//fim formatação mascara
		
		frmNovaVenda = new JFrame();
		frmNovaVenda.setIconImage(Toolkit.getDefaultToolkit().getImage(NovaVenda.class.getResource("/libs/img/32x32.png")));
		frmNovaVenda.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 12));
		frmNovaVenda.setTitle("Nova Venda");
		frmNovaVenda.setBounds(100, 100, 1080, 720);
		frmNovaVenda.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmNovaVenda.setLocationRelativeTo(null);
		frmNovaVenda.setResizable(false);
		frmNovaVenda.getContentPane().setLayout(null);
		
		ImageIcon icon = new ImageIcon(NovaVenda.class.getResource("/libs/img/t1.png"));
		icon.setImage(icon.getImage().getScaledInstance(46, 48, 100));
		JLabel label = new JLabel("");
		label.setBounds(1004, 6, 46, 48);
		label.setIcon(icon);
		frmNovaVenda.getContentPane().add(label);
		
		
		JLabel lblNovaVenda = new JLabel("Nova Venda");
		lblNovaVenda.setHorizontalAlignment(SwingConstants.CENTER);
		lblNovaVenda.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNovaVenda.setBounds(0, 11, 1074, 39);
		frmNovaVenda.getContentPane().add(lblNovaVenda);
		
		JLabel lblTipoDoProduto = new JLabel("Tipo do Produto:");
		lblTipoDoProduto.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTipoDoProduto.setBounds(10, 80, 107, 20);
		frmNovaVenda.getContentPane().add(lblTipoDoProduto);
		
		rdbtnAnimal = new JRadioButton("Animal");
		rdbtnAnimal.setBackground(new Color(245, 245, 245));
		rdbtnAnimal.setOpaque(false);
		rdbtnAnimal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbAnimal.setEnabled(true);
				tfProduto.setEnabled(false);
			}
		});
		rdbtnAnimal.setBounds(123, 80, 66, 20);
		frmNovaVenda.getContentPane().add(rdbtnAnimal);
		
		rdbtnSubproduto = new JRadioButton("Subproduto");
		rdbtnSubproduto.setBackground(new Color(245, 245, 245));
		rdbtnSubproduto.setOpaque(false);
		rdbtnSubproduto.setSelected(true);
		rdbtnSubproduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbAnimal.setEnabled(true);
				tfProduto.setEnabled(true);
			}
		});
		rdbtnSubproduto.setBounds(191, 80, 94, 20);
		frmNovaVenda.getContentPane().add(rdbtnSubproduto);
		
		rdbtnPlantio = new JRadioButton("Plantio");
		rdbtnPlantio.setBackground(new Color(245, 245, 245));
		rdbtnPlantio.setOpaque(false);
		rdbtnPlantio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbAnimal.setEnabled(false);
				tfProduto.setEnabled(true);
			}
		});
		rdbtnPlantio.setBounds(287, 80, 66, 20);
		frmNovaVenda.getContentPane().add(rdbtnPlantio);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnPlantio);
		bg.add(rdbtnSubproduto);
		bg.add(rdbtnAnimal);
		
		lblProduto = new JLabel("Produto:");
		lblProduto.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblProduto.setBounds(10, 111, 78, 20);
		frmNovaVenda.getContentPane().add(lblProduto);
		
		tfProduto = new JTextField();
		tfProduto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode()==KeyEvent.VK_ENTER) {
					tfCliente.requestFocus();
				}
					
				
			}
		});
		tfProduto.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.GRAY, Color.GRAY, Color.DARK_GRAY));
		tfProduto.setBounds(108, 111, 200, 20);
		frmNovaVenda.getContentPane().add(tfProduto);
		tfProduto.setColumns(10);
		
		lblAnimal = new JLabel("Lote de animais:");
		lblAnimal.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAnimal.setBounds(10, 142, 131, 20);
		frmNovaVenda.getContentPane().add(lblAnimal);
		
		cbAnimal = new JComboBox<String>();
		cbAnimal.setBackground(new Color(245, 245, 245));
		cbAnimal.setBounds(120, 142, 188, 20);
		frmNovaVenda.getContentPane().add(cbAnimal);
		
		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblQuantidade.setBounds(405, 142, 89, 20);
		frmNovaVenda.getContentPane().add(lblQuantidade);
		
		spinner = new JSpinner();
		spinner.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					tfPreco.requestFocus();
				}
			}
		});
		spinner.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.GRAY, Color.GRAY, Color.DARK_GRAY));
		spinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinner.setBounds(546, 142, 200, 20);
		frmNovaVenda.getContentPane().add(spinner);
		
		JLabel lblPreco = new JLabel("Pre\u00E7o:");
		lblPreco.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPreco.setBounds(787, 80, 57, 20);
		frmNovaVenda.getContentPane().add(lblPreco);
		
		tfPreco = new JTextField();
		tfPreco.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.GRAY, Color.GRAY, Color.DARK_GRAY));
		tfPreco.setColumns(10);
		tfPreco.setBounds(910, 81, 154, 20);
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
					ftfData.requestFocus();
				}
			}
		});
		frmNovaVenda.getContentPane().add(tfPreco);
		
		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCliente.setBounds(405, 80, 100, 20);
		frmNovaVenda.getContentPane().add(lblCliente);
		
		tfCliente = new JTextField();
		tfCliente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					tfNota.requestFocus();
				}
			}
		});
		tfCliente.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.GRAY, Color.GRAY, Color.DARK_GRAY));
		tfCliente.setColumns(10);
		tfCliente.setBounds(546, 80, 200, 20);
		frmNovaVenda.getContentPane().add(tfCliente);
		
		JLabel lblDataDaVenda = new JLabel("Data da Venda:");
		lblDataDaVenda.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDataDaVenda.setBounds(787, 111, 113, 20);
		frmNovaVenda.getContentPane().add(lblDataDaVenda);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBorder(new BevelBorder(BevelBorder.RAISED, Color.WHITE, null, null, null));
		btnSalvar.setBackground(new Color(245, 245, 245));
		btnSalvar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if(tfCliente.getText().trim().equals("")) {//inicio do tratamento de informaçao 
					JOptionPane.showMessageDialog(null, "Insira o nome do Cliente", "ALERTA!", JOptionPane.WARNING_MESSAGE);
					tfCliente.requestFocus();
					return;
				}
				if (tfNota.getText().trim().equals("")) {
					int x = JOptionPane.showConfirmDialog(null, "Você deseja deixar a nota como nulo?", "ALERTA!",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
					if (x==JOptionPane.YES_OPTION) {
						tfNota.setText("00000");
				
					}else {
						tfNota.requestFocus();
						return;
					}
				}
				if(ftfData.getText().contains(" ")) {
					JOptionPane.showMessageDialog(null, "Insira a data da Venda", "ALERTA!", JOptionPane.WARNING_MESSAGE);
					ftfData.requestFocus();
					return;
				}
				if(tfPreco.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Insira o valor", "ALERTA!", JOptionPane.WARNING_MESSAGE);
					tfPreco.requestFocus();
					return;
				}
				if (spinner.getValue().equals(0)) {
					JOptionPane.showMessageDialog(null, "Insira uma quantidade!", "ALERTA!",JOptionPane.WARNING_MESSAGE);
					spinner.requestFocus();
					return;
				}
				if((rdbtnSubproduto.isSelected() && tfProduto.getText().trim().equals("")) ||
						(rdbtnPlantio.isSelected() && tfProduto.getText().trim().equals(""))) {
					JOptionPane.showMessageDialog(null, "Insira o produto", "ALERTA!", JOptionPane.WARNING_MESSAGE);
					tfProduto.requestFocus();
					return;
				}//fim do tratamento de informaçao 
				
				//TESTE DE CADASTRO DE PREÇO
				numero = tfPreco.getText().toString();
				if (numero.contains(",")) {
					numero = numero.replace(".", ",");
				}
				
				//FIM DO TESTE
				
				if (contadorEditar==0) {
					pegaIdAnimal();
					preencherDAOparaSalvarVenda();
					alteraAnimal(false,0);
					if (rdbtnAnimal.isSelected() && quantidadeAntes < Integer.parseInt(spinner.getValue().toString())) {
						JOptionPane.showMessageDialog(null, "Quandidade superior ao numero de animais!", "ALERTA", JOptionPane.WARNING_MESSAGE);
						return;
					}
					x1=0;
					if (scrollPane.getHeight()<=315) {
						int x = (teste*17)+scrollPane.getHeight();
						scrollPane.setBounds(10, 253, 1054, x);
						lblSemdados.setVisible(false);
					}
					alteraAnimal(true,Integer.parseInt(spinner.getValue().toString()));
					new CrudVendas().addvendas(venda);
					JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
					btnLimpar.doClick();
					criaTabela(CrudVendas.selecionaVendas(venda));
				}
				
				if (contadorEditar==1) {
					x1=0;
					int resposta = JOptionPane.showConfirmDialog(null, "Você deseja alterar os dados já salvos?","ALERTA", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
					if (resposta == JOptionPane.YES_NO_OPTION) {
						pegaIdAnimal();
						preencherDAOparaSalvarVenda();
						alteraAnimal(false,0);
						if (rdbtnAnimal.isSelected() && quantidadeAntes < Integer.parseInt(spinner.getValue().toString())) {
							JOptionPane.showMessageDialog(null, "Quandidade superior ao numero de animais!", "ALERTA", JOptionPane.WARNING_MESSAGE);
							return;
						}
						alteraAnimal(true,conta(table.getSelectedRow()));
						new CrudVendas().updVendas(venda);
						criaTabela(CrudVendas.selecionaVendas(venda));
						btnCancelar.doClick();
					
					}
				}
			}
		});
		btnSalvar.setBounds(975, 596, 89, 23);
		frmNovaVenda.getContentPane().add(btnSalvar);
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.setBorder(new BevelBorder(BevelBorder.RAISED, Color.WHITE, null, null, null));
		btnLimpar.setBackground(new Color(245, 245, 245));
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfCliente.setText(null);
				ftfData.setValue(null);
				tfPreco.setText(null);
				tfProduto.setText(null);
				rdbtnSubproduto.setSelected(true);
				spinner.setValue(0);
				cbAnimal.setSelectedIndex(0);
				tfNota.setText(null);
			}
		});
		btnLimpar.setBounds(876, 596, 89, 23);
		frmNovaVenda.getContentPane().add(btnLimpar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBorder(new BevelBorder(BevelBorder.RAISED, Color.WHITE, null, null, null));
		btnCancelar.setBackground(new Color(245, 245, 245));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (contadorEditar==0) {
					frmNovaVenda.dispose();
					Principal.frmPrincipal.setVisible(true);
				}
				if (contadorEditar==1) {
					contadorEditar=0;
					ftfData.setEditable(true);
					btnLimpar.setEnabled(true);
					btnLimpar.doClick();
				}
			}
		});
		btnCancelar.setBounds(777, 596, 89, 23);
		frmNovaVenda.getContentPane().add(btnCancelar);
		
		Date data = new Date();
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		String formatada = formato.format(data);
		
		scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(245, 245, 245));
		scrollPane.setFont(new Font("Arial", Font.BOLD, 13));
		scrollPane.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		scrollPane.setBounds(10, 253, 1054, 23);
		
		frmNovaVenda.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setGridColor(SystemColor.activeCaption);
		table.setSelectionBackground(new Color(135, 206, 250));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				contadorEditar=1;
				ftfData.setEditable(false);
				btnLimpar.setEnabled(false);
				pegaDadosDaTabela();
			}
		});
		
		table.setToolTipText("Clique para editar os dados\r\n");
		table.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		table.setIgnoreRepaint(true);
		table.setFont(new Font("Arial", Font.PLAIN, 13));
		table.setForeground(SystemColor.controlText);
		table.setBackground(new Color(245, 245, 245));
		
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"ID da venda", "Tipo do Produto", "Produto", "Animal", "Cliente", "Numero da Nota", "Quantidade ", "Pre\u00E7o", "Data da venda"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(5).setResizable(false);
		table.getColumnModel().getColumn(6).setResizable(false);
		table.getColumnModel().getColumn(7).setResizable(false);
		table.getColumnModel().getColumn(8).setResizable(false);
	
		scrollPane.setViewportView(table);
		
		ftfData = new JFormattedTextField(mask);
		ftfData.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.GRAY, Color.GRAY, Color.DARK_GRAY));
		ftfData.setText(formatada);
		ftfData.setBounds(910, 111, 154, 20);
		frmNovaVenda.getContentPane().add(ftfData);
		
		tfProcurar = new JTextField();
		tfProcurar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode()==KeyEvent.VK_ENTER) {
					btnProcurar.doClick();
				}
			}
		});
		tfProcurar.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.GRAY, Color.GRAY, Color.DARK_GRAY));
		tfProcurar.setToolTipText("");
		tfProcurar.setForeground(Color.BLACK);
		tfProcurar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfProcurar.setColumns(10);
		tfProcurar.setBounds(10, 222, 331, 20);
		frmNovaVenda.getContentPane().add(tfProcurar);
	
		
		
		btnProcurar = new JButton("Proucurar");
		btnProcurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				x1=0;
				//TRATAMENTO PARA AUMENTAR E DIMINUIR TABELA
				criaTabela(CrudVendas.procuraVendas(tfProcurar.getText().toString(), venda));
				int tabel = table.getRowCount();
				if (table.getRowCount()>19) {
					tabel=18;
				}
				int linha = tabel*17;
				int valor = 23+linha;
				scrollPane.setBounds(10, 253, 1054, valor);
				if (!(tfProcurar.getText()).trim().equals("")) {
					if (table.getRowCount()==0) {
						int x2 = 23;
						scrollPane.setBounds(10, 253, 1054, x2);
						new CrudVendas();
						criaTabela(CrudVendas.selecionaVendas(venda));
						lblSemdados.setVisible(true);
					}
				}
				
			}
		});
		btnProcurar.setForeground(Color.DARK_GRAY);
		btnProcurar.setFont(new Font("Tahoma", btnProcurar.getFont().getStyle(), btnProcurar.getFont().getSize()));
		btnProcurar.setBorder(new BevelBorder(BevelBorder.RAISED, Color.WHITE, null, null, null));
		btnProcurar.setBackground(new Color(245, 245, 245));
		btnProcurar.setBounds(351, 222, 89, 23);
		frmNovaVenda.getContentPane().add(btnProcurar);
		
		JLabel lblNumeroDaNota = new JLabel("Numero da Nota:");
		lblNumeroDaNota.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNumeroDaNota.setBounds(405, 111, 131, 20);
		frmNovaVenda.getContentPane().add(lblNumeroDaNota);
		
		tfNota = new JTextField();
		tfNota.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					spinner.requestFocus();
				}
			}
		});
		tfNota.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.GRAY, Color.GRAY, Color.DARK_GRAY));
		tfNota.setColumns(10);
		tfNota.setBounds(546, 111, 200, 20);
		frmNovaVenda.getContentPane().add(tfNota);
		
		lblSemdados = new JLabel("Sem dados salvos!");
		lblSemdados.setHorizontalAlignment(SwingConstants.CENTER);
		lblSemdados.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSemdados.setBounds(10, 287, 1054, 32);
		frmNovaVenda.getContentPane().add(lblSemdados);
		
		JLabel lblFundo = new JLabel("");
		lblFundo.setIcon(new ImageIcon(NovaVenda.class.getResource("/libs/img/teste13.jpg")));
		lblFundo.setBounds(0, -22, 1074, 670);
		frmNovaVenda.getContentPane().add(lblFundo);
		
		menu();
		comboBoxAnimal();
		venda.setIdFazenda(Principal.fazenda.getIdFazenda());
		criaTabela(CrudVendas.selecionaVendas(venda));
		if (table.getRowCount()== 0) {
			scrollPane.setVisible(false);
			lblSemdados.setVisible(true);
		}
		
	}
	
	void preencherDAOparaSalvarVenda() {
		venda.setCliente(tfCliente.getText());
		venda.setDataVenda(ftfData.getText());
		venda.setPreco(Double.parseDouble(numero));
		venda.setQuantidade((Integer.parseInt(spinner.getValue().toString())));
		venda.setIdFazenda(Principal.fazenda.getIdFazenda());
		venda.setNumeroDaNota(tfNota.getText());
		
		if (rdbtnAnimal.isSelected()) {
			venda.setProduto(null);
			venda.setIdanimal(id);
			venda.setTipoDoProduto(1);
		}
		if (rdbtnSubproduto.isSelected()) {
			venda.setProduto(tfProduto.getText());
			venda.setIdanimal(id);
			venda.setTipoDoProduto(2);
		}
		if (rdbtnPlantio.isSelected()) {
			venda.setIdanimal(1);
			venda.setProduto(tfProduto.getText());
			venda.setTipoDoProduto(3);
		}
	}
	
	//MÉTODO PARA COLOCAR OS DADOS NA TABELA a
	public void criaTabela(ResultSet rs) {
		
		DefaultTableModel tabela = (DefaultTableModel) table.getModel();
		tabela.setRowCount(0);
		
		String tipo=null;
		String animal=null;
		lblSemdados.setVisible(false);
		scrollPane.setVisible(true);
		
		try {
			while(rs.next()) {
				
				if (rs.getInt("tipodoproduto")==1) {
					tipo="Animal";
				}
				if (rs.getInt("tipodoproduto")==2) {
					tipo="Subproduto";
				}
				if (rs.getInt("tipodoproduto")==3) {
					tipo="Plantio";
				}
				
				
				ResultSet dados=null;//inicio do resultset para pegar o nome do animal
				String sql = "SELECT idvendas, animais.nomelote FROM vendas "+
						"INNER JOIN animais ON vendas.idanimal = animais.idanimal where idvendas=?";
				try {
					PreparedStatement stmt = Conexao.conexao.prepareStatement(sql);
					stmt.setInt(1, rs.getInt("idvendas"));
					dados = stmt.executeQuery();
					stmt.execute();
					stmt.close();	
					
					if (dados.next()) {
						animal=dados.getString("nomeLote");
					}
					if (rs.getInt("idanimal")==1) {
						animal="---";
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("erro ao pegar nome animal");
				}//fim do resultset para pegar o nome do animal
				
				tabela.addRow(new Object[] {rs.getInt("idvendas"),tipo,rs.getString("produto"),animal,rs.getString("cliente"),
	 					rs.getString("numeronota"),rs.getInt("qtd"),rs.getDouble("preco"),rs.getString("datavenda")});
				  

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
	
	void comboBoxAnimal() {
		ResultSet dados=null;
		String sql = "SELECT nomeLote, raca.nome_ra, raca FROM animais "
				+ "INNER JOIN raca ON raca = idraca where idfazenda = ? order by nomeLote";//seleciona o animal com o nome da raça
		try {
			PreparedStatement stmt = Conexao.conexao.prepareStatement(sql);
			stmt.setInt(1, Principal.fazenda.getIdFazenda());
			dados = stmt.executeQuery();
			stmt.execute();
			stmt.close();
			cbAnimal.removeAllItems();
			while(dados.next()) {
				ResultSet dados2=null;
				String sql2 = "SELECT  id_especie, especie.nome_es FROM raca " + 
						"INNER JOIN especie " + 
						"ON id_especie = idespecie where idraca = ?";//seleciona o nome da especie da raça
				
				PreparedStatement stmt2 = Conexao.conexao.prepareStatement(sql2);
				stmt2.setInt(1, dados.getInt("raca"));
				dados2 = stmt2.executeQuery();
				stmt2.execute();
				stmt2.close();
				
				if (dados2.next()) {
					cbAnimal.addItem(dados.getString("nomeLote")+" - "+dados.getString("nome_ra")+" - "+dados2.getString("nome_es"));
				}
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("erro ao preencher comboBox animnal");
		}
	}
	
	void pegaIdAnimal() {
		String palavraCheia = cbAnimal.getSelectedItem().toString();
		String[] palavraCortada = palavraCheia.split("-");
		ResultSet rs = null;
		String sql = "select idanimal from animais where nomeLote=?";
		try {
			PreparedStatement stmt = Conexao.conexao.prepareStatement(sql);
			stmt.setString(1, palavraCortada[0]);
			rs = stmt.executeQuery();
			stmt.execute();
			stmt.close();
			
			if (rs.next()) {
				id = rs.getInt("idanimal");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void alteraAnimal(boolean resposta,int quantidadePraTirar) {
			Animal animal = new Animal();
			animal.setIdFazenda(Principal.fazenda.getIdFazenda());
			ResultSet rs = CrudAnimal.selecionaAnimais(animal);
			
				try {
					while (rs.next()) {
						if(rs.getInt("idanimal")==id) {
							quantidadeAntes = rs.getInt("quantidade");
							break;
						}
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			if (resposta) {
				String sql = "UPDATE animais set quantidade=? where idanimal=?";
				try {
					PreparedStatement stmt = Conexao.conexao.prepareStatement(sql);
						stmt.setInt(1, quantidadeAntes-quantidadePraTirar);
						stmt.setInt(2, id);
						stmt.execute();
						stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}
	
	void pegaDadosDaTabela() {
		int linha = table.getSelectedRow();
		
		venda.setId(Integer.parseInt(table.getValueAt(linha, 0).toString())) ;
		
		if (table.getValueAt(linha, 1).toString().equalsIgnoreCase("animal")) {
			rdbtnAnimal.doClick();
		}
		if (table.getValueAt(linha, 1).toString().equalsIgnoreCase("subproduto")) {
			rdbtnSubproduto.doClick();
		}
		if (table.getValueAt(linha, 1).toString().equalsIgnoreCase("plantio")) {
			rdbtnPlantio.doClick();
		}
		
		if (table.getValueAt(linha, 2) == null) {
			tfProduto.setText(null);
		}else {
			tfProduto.setText(table.getValueAt(linha, 2).toString());
		}
		
		tfCliente.setText(table.getValueAt(linha, 4).toString());
		tfNota.setText(table.getValueAt(linha, 5).toString());
		spinner.setValue(table.getValueAt(linha, 6));
		tfPreco.setText(table.getValueAt(linha, 7).toString());
		ftfData.setText(table.getValueAt(linha, 8).toString());
		
		if (!rdbtnPlantio.isSelected()) {
			String palavraCheia = null;
			String[] palavraCortada = null;
			
			for(int x=0; x < cbAnimal.getItemCount();x++) {
				cbAnimal.setSelectedIndex(x);
				palavraCheia = cbAnimal.getSelectedItem().toString();
				palavraCheia = cbAnimal.getSelectedItem().toString();
				palavraCortada =  palavraCheia.split(" ");
				
				if (palavraCortada[0].equals(table.getValueAt(linha, 3).toString())) {
					break;
				}
			}
		}
	}
	
	int conta(int linha) {
		int quantidade=0;
		
		if (Integer.parseInt(spinner.getValue().toString()) == Integer.parseInt(table.getValueAt(linha, 6).toString())) {
			quantidade = 0;
		}
		else {
			quantidade = Integer.parseInt(spinner.getValue().toString()) - Integer.parseInt(table.getValueAt(linha, 6).toString());
		}
		
		return quantidade;
	}
	
	public void menu() {	
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(105, 105, 105), new Color(105, 105, 105), new Color(105, 105, 105), new Color(105, 105, 105)));
		menuBar.setForeground(Color.GREEN);
		menuBar.setBackground(Color.DARK_GRAY);
		frmNovaVenda.setJMenuBar(menuBar);
		
		JMenu mnInicio = new JMenu("");
		mnInicio.setBorder(new SoftBevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY));
		mnInicio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnInicio.setOpaque(true);
		mnInicio.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mnInicio.setIcon(new ImageIcon(NovaVenda.class.getResource("/libs/img/Icone_Inicio.png")));
		mnInicio.setForeground(Color.WHITE);
		mnInicio.setBackground(Color.DARK_GRAY);
		mnInicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Principal.frmPrincipal.setVisible(true);
				frmNovaVenda.dispose();
			}
		});
		menuBar.add(mnInicio);
		
		JMenu mnNewMenu = new JMenu("");
		mnNewMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnNewMenu.setIcon(new ImageIcon(NovaVenda.class.getResource("/libs/img/Icone_GEstao.png")));
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
				frmNovaVenda.dispose();
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
				frmNovaVenda.dispose();
			}
		});
		
		JMenu mnNewMenu_1 = new JMenu("");
		mnNewMenu_1.setBorder(new SoftBevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY));
		
		mnNewMenu_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnNewMenu_1.setIcon(new ImageIcon(NovaVenda.class.getResource("/libs/img/Icone_Financeiro.png")));
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
				frmNovaVenda.dispose();
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
				frmNovaVenda.dispose();
			}
		});
		mntmNovaVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//NovaVenda.main(null);
				//frmNovaVenda.dispose();
			}
		});
		
		JMenu mnOpes = new JMenu("");
		mnOpes.setBorder(new SoftBevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY));
		mnOpes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnOpes.setIcon(new ImageIcon(NovaVenda.class.getResource("/libs/img/Icone_OPCAO.png")));
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
				frmNovaVenda.dispose();
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
				Pergunta.outroFrame=frmNovaVenda;
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
				frmNovaVenda.dispose();
				Login.main(null);
			}
		});
		mnOpes.add(mntmDeslogar);
		
	
		
		mnOpes.add(mntmSada);
		frmNovaVenda.getContentPane().setLayout(null);
	}
}

