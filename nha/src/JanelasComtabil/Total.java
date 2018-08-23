package JanelasComtabil;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import com.toedter.calendar.JCalendar;

import DAO.Compras;
import DAO.Vendas;
import JanelasAnimal.CadastrarAnimais;
import JanelasFuncionarios.CadastrarFuncionarios;
import banco.Conexao;
import crud.CrudCompras;
import crud.CrudVendas;
import outraJanelas.Login;
import outraJanelas.NovaFazenda;
import outraJanelas.Pergunta;
import outraJanelas.Principal;
import java.awt.SystemColor;

public class Total {//

	private JFrame frmRelattio;
	static JLabel valorGasto;
	static JLabel valorReceita;
	private JLabel label;
	private Vendas vendas = new Vendas();
	private Compras compras = new Compras();
	private JTable tabelaReceita;
	private JScrollPane scrollPaneReceita;
	private JCheckBox chckbxAno;
	private JCheckBox chckbxAnomes;
	private JCheckBox chckbxAnomesdia;
	private JCalendar calendar;
	Date data = new Date();
	
	//tabela
		static int teste = 0; 
		static int x1=1;
		private JTable tabelaGasto;
		private JScrollPane scrollPaneGasto;
		private JLabel lblSemdados2;
		private JLabel lblSemDados1;
		private JButton btnGraficoSetor;
		private JButton btnGrficoBarra;
		//tabela

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Total window = new Total();
					window.frmRelattio.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Total() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frmRelattio = new JFrame();
		frmRelattio.setTitle("Relat\u00F3tio");
		frmRelattio.setIconImage(Toolkit.getDefaultToolkit().getImage(Total.class.getResource("/libs/img/libs/img/32x32.png")));
		frmRelattio.setBounds(100, 100, 1080, 720);
		frmRelattio.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmRelattio.setResizable(false);
		frmRelattio.setLocationRelativeTo(null);
		
		
		frmRelattio.getContentPane().setLayout(null);
		
		ImageIcon icon = new ImageIcon(Total.class.getResource("/libs/img/libs/img/relatio.png"));
		icon.setImage(icon.getImage().getScaledInstance(44, 40, 100));
		JLabel lblIcone = new JLabel("");
		lblIcone.setBounds(1006, 6, 44, 40);
		lblIcone.setIcon(icon);
		frmRelattio.getContentPane().add(lblIcone);
		
		JLabel lblRelatorioContabil = new JLabel("Relat\u00F3rio Cont\u00E1bil");
		lblRelatorioContabil.setBounds(10, 11, 1054, 25);
		lblRelatorioContabil.setHorizontalAlignment(SwingConstants.CENTER);
		lblRelatorioContabil.setFont(new Font("Tahoma", Font.BOLD, 25));
		frmRelattio.getContentPane().add(lblRelatorioContabil);
		
		JLabel lblTotalGasto = new JLabel("Total Gasto");
		lblTotalGasto.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTotalGasto.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalGasto.setBounds(10, 219, 519, 20);
		frmRelattio.getContentPane().add(lblTotalGasto);
		
		JLabel lblReceita = new JLabel("Receita");
		lblReceita.setHorizontalAlignment(SwingConstants.CENTER);
		lblReceita.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblReceita.setBounds(545, 219, 519, 20);
		frmRelattio.getContentPane().add(lblReceita);
		
		valorGasto = new JLabel("");
		valorGasto.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		valorGasto.setForeground(new Color(0, 0, 0));
		valorGasto.setFont(new Font("Tahoma", Font.BOLD, 20));
		valorGasto.setHorizontalAlignment(SwingConstants.CENTER);
		valorGasto.setBounds(387, 518, 126, 25);
		frmRelattio.getContentPane().add(valorGasto);
		
		valorReceita = new JLabel("");
		valorReceita.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		valorReceita.setFont(new Font("Tahoma", Font.BOLD, 20));
		valorReceita.setForeground(new Color(0, 0, 0));
		valorReceita.setHorizontalAlignment(SwingConstants.CENTER);
		valorReceita.setBounds(646, 518, 126, 25);
		frmRelattio.getContentPane().add(valorReceita);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setForeground(new Color(0, 0, 0));
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTotal.setBounds(339, 576, 104, 40);
		frmRelattio.getContentPane().add(lblTotal);
		
		label = new JLabel("0.0");
		label.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(new Color(0, 0, 0));
		label.setFont(new Font("Tahoma", Font.BOLD, 20));
		label.setBounds(442, 571, 225, 51);
		frmRelattio.getContentPane().add(label);
		
		scrollPaneReceita = new JScrollPane();
		scrollPaneReceita.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		scrollPaneReceita.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneReceita.setBounds(545, 250, 519, 23);
		frmRelattio.getContentPane().add(scrollPaneReceita);
		
		tabelaReceita = new JTable();
		tabelaReceita.setSelectionBackground(new Color(135, 206, 250));
		tabelaReceita.setGridColor(SystemColor.activeCaption);
		tabelaReceita.setBackground(new Color(245, 245, 245));
		tabelaReceita.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Produto", "Pre\u00E7o", "Data"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tabelaReceita.getColumnModel().getColumn(0).setResizable(false);
		tabelaReceita.getColumnModel().getColumn(0).setPreferredWidth(200);
		tabelaReceita.getColumnModel().getColumn(1).setResizable(false);
		tabelaReceita.getColumnModel().getColumn(1).setPreferredWidth(151);
		tabelaReceita.getColumnModel().getColumn(2).setResizable(false);
		tabelaReceita.getColumnModel().getColumn(2).setPreferredWidth(166);
		tabelaReceita.getTableHeader().setReorderingAllowed(false);
		tabelaReceita.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPaneReceita.setViewportView(tabelaReceita);
		
		JLabel lblFiltrarData = new JLabel("Filtrar Data:");
		lblFiltrarData.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFiltrarData.setBounds(10, 59, 84, 14);
		frmRelattio.getContentPane().add(lblFiltrarData);
		
		calendar = new JCalendar();
		calendar.getDayChooser().setOpaque(false);
		calendar.setOpaque(false);
		calendar.getDayChooser().getDayPanel().setOpaque(false);
		calendar.getDayChooser().addPropertyChangeListener(new PropertyChangeListener() {
			
			public void propertyChange(PropertyChangeEvent evt) {//evento mudar dia
				if(chckbxAnomesdia.isSelected()) {
					colocaDadosNaTabelaGasto(new CrudCompras().procurarCompraDataAnoDia(String.valueOf(calendar.getYearChooser().getValue()),
							String.valueOf(calendar.getMonthChooser().getMonth()+1), 
							String.valueOf(calendar.getDayChooser().getDay()),  Principal.fazenda.getIdFazenda()));
					
					
					colocaDadosNaTabelaReceita(new CrudVendas().procurarVendasDataAnoDia(String.valueOf(calendar.getYearChooser().getValue()),
							String.valueOf(calendar.getMonthChooser().getMonth()+1), 
							String.valueOf(calendar.getDayChooser().getDay()),  Principal.fazenda.getIdFazenda()));
					
					valor(new CrudCompras().procurarCompraDataAnoDia(String.valueOf(calendar.getYearChooser().getValue()),//soma dos valores
							String.valueOf(calendar.getMonthChooser().getMonth()+1), 
							String.valueOf(calendar.getDayChooser().getDay()),  Principal.fazenda.getIdFazenda()),
						new CrudVendas().procurarVendasDataAnoDia(String.valueOf(calendar.getYearChooser().getValue()),
							String.valueOf(calendar.getMonthChooser().getMonth()+1), 
							String.valueOf(calendar.getDayChooser().getDay()),  Principal.fazenda.getIdFazenda()));
					
					
				}
			}
		});
		calendar.getMonthChooser().addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {//evento mudar mes
				
				if(chckbxAnomes.isSelected()) {   
					colocaDadosNaTabelaGasto(new CrudCompras().procurarCompraDataAnoMes(String.valueOf(calendar.getYearChooser().getValue()),
							String.valueOf(calendar.getMonthChooser().getMonth()+1), Principal.fazenda.getIdFazenda()));
					
					colocaDadosNaTabelaReceita(new CrudVendas().procurarVendasDataAnoMes(String.valueOf(calendar.getYearChooser().getValue()),
							String.valueOf(calendar.getMonthChooser().getMonth()+1), Principal.fazenda.getIdFazenda()));
					
					valor(new CrudCompras().procurarCompraDataAnoMes(String.valueOf(calendar.getYearChooser().getValue()),
							String.valueOf(calendar.getMonthChooser().getMonth()+1), Principal.fazenda.getIdFazenda()), 
						new CrudVendas().procurarVendasDataAnoMes(String.valueOf(calendar.getYearChooser().getValue()),
							String.valueOf(calendar.getMonthChooser().getMonth()+1), Principal.fazenda.getIdFazenda()));
				}
				
				if(chckbxAnomesdia.isSelected()) {
					colocaDadosNaTabelaGasto(new CrudCompras().procurarCompraDataAnoDia(String.valueOf(calendar.getYearChooser().getValue()),
							String.valueOf(calendar.getMonthChooser().getMonth()+1), 
							String.valueOf(calendar.getDayChooser().getDay()),  Principal.fazenda.getIdFazenda()));
					
					colocaDadosNaTabelaReceita(new CrudVendas().procurarVendasDataAnoDia(String.valueOf(calendar.getYearChooser().getValue()),
							String.valueOf(calendar.getMonthChooser().getMonth()+1), 
							String.valueOf(calendar.getDayChooser().getDay()),  Principal.fazenda.getIdFazenda()));
					
					valor(new CrudCompras().procurarCompraDataAnoDia(String.valueOf(calendar.getYearChooser().getValue()),
							String.valueOf(calendar.getMonthChooser().getMonth()+1), 
							String.valueOf(calendar.getDayChooser().getDay()),  Principal.fazenda.getIdFazenda()),
						new CrudVendas().procurarVendasDataAnoDia(String.valueOf(calendar.getYearChooser().getValue()),
							String.valueOf(calendar.getMonthChooser().getMonth()+1), 
							String.valueOf(calendar.getDayChooser().getDay()),  Principal.fazenda.getIdFazenda()));
				}
				
			}
		});
		calendar.getYearChooser().addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {//evento mudar ano
				
				if(chckbxAno.isSelected()) {
					colocaDadosNaTabelaGasto(new CrudCompras()
							.procurarCompraDataAno(String.valueOf(calendar.getYearChooser().getValue()), Principal.fazenda.getIdFazenda()));
					
					colocaDadosNaTabelaReceita(new CrudVendas()
							.procurarVendasDataAno(String.valueOf(calendar.getYearChooser().getValue()), Principal.fazenda.getIdFazenda()));
					
					valor(new CrudCompras()
							.procurarCompraDataAno(String.valueOf(calendar.getYearChooser().getValue()), Principal.fazenda.getIdFazenda()),
						new CrudVendas()
							.procurarVendasDataAno(String.valueOf(calendar.getYearChooser().getValue()), Principal.fazenda.getIdFazenda()));
				}
				
				if (chckbxAnomes.isSelected()) {
					colocaDadosNaTabelaGasto(new CrudCompras().procurarCompraDataAnoMes(String.valueOf(calendar.getYearChooser().getValue()),
							String.valueOf(calendar.getMonthChooser().getMonth()+1), Principal.fazenda.getIdFazenda()));
					
					colocaDadosNaTabelaReceita(new CrudVendas().procurarVendasDataAnoMes(String.valueOf(calendar.getYearChooser().getValue()),
							String.valueOf(calendar.getMonthChooser().getMonth()+1), Principal.fazenda.getIdFazenda()));
					
					valor(new CrudCompras().procurarCompraDataAnoMes(String.valueOf(calendar.getYearChooser().getValue()),
							String.valueOf(calendar.getMonthChooser().getMonth()+1), Principal.fazenda.getIdFazenda()),
						new CrudVendas().procurarVendasDataAnoMes(String.valueOf(calendar.getYearChooser().getValue()),
							String.valueOf(calendar.getMonthChooser().getMonth()+1), Principal.fazenda.getIdFazenda()));
				}
				if(chckbxAnomesdia.isSelected()) {
					colocaDadosNaTabelaGasto(new CrudCompras().procurarCompraDataAnoDia(String.valueOf(calendar.getYearChooser().getValue()),
							String.valueOf(calendar.getMonthChooser().getMonth()+1), 
							String.valueOf(calendar.getDayChooser().getDay()),  Principal.fazenda.getIdFazenda()));
					
					colocaDadosNaTabelaReceita(new CrudVendas().procurarVendasDataAnoDia(String.valueOf(calendar.getYearChooser().getValue()),
							String.valueOf(calendar.getMonthChooser().getMonth()+1), 
							String.valueOf(calendar.getDayChooser().getDay()),  Principal.fazenda.getIdFazenda()));
					
					valor(new CrudCompras().procurarCompraDataAnoDia(String.valueOf(calendar.getYearChooser().getValue()),
							String.valueOf(calendar.getMonthChooser().getMonth()+1), 
							String.valueOf(calendar.getDayChooser().getDay()),  Principal.fazenda.getIdFazenda()),
						new CrudVendas().procurarVendasDataAnoDia(String.valueOf(calendar.getYearChooser().getValue()),
							String.valueOf(calendar.getMonthChooser().getMonth()+1), 
							String.valueOf(calendar.getDayChooser().getDay()),  Principal.fazenda.getIdFazenda()));
				}
				

			}
		});
		
		calendar.getDayChooser().setVisible(false);
		calendar.getMonthChooser().setVisible(false);
		calendar.setBounds(147, 59, 217, 137);
		frmRelattio.getContentPane().add(calendar);

		
		chckbxAno = new JCheckBox("Ano");
		chckbxAno.setOpaque(false);
		chckbxAno.setSelected(true);
		chckbxAno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxAno.isSelected()) {
					calendar.getDayChooser().setVisible(false);
					calendar.getMonthChooser().setVisible(false);
					
					colocaDadosNaTabelaGasto(new CrudCompras()
							.procurarCompraDataAno(String.valueOf(calendar.getYearChooser().getValue()), Principal.fazenda.getIdFazenda()));
					
					colocaDadosNaTabelaReceita(new CrudVendas()
							.procurarVendasDataAno(String.valueOf(calendar.getYearChooser().getValue()), Principal.fazenda.getIdFazenda()));
					
					valor(new CrudCompras()
							.procurarCompraDataAno(String.valueOf(calendar.getYearChooser().getValue()), Principal.fazenda.getIdFazenda()),
						new CrudVendas()
							.procurarVendasDataAno(String.valueOf(calendar.getYearChooser().getValue()), Principal.fazenda.getIdFazenda()));
					
					
				}
			}
		});
		chckbxAno.setBounds(370, 59, 97, 23);
		frmRelattio.getContentPane().add(chckbxAno);
		
		
		
		chckbxAnomes = new JCheckBox("Ano-Mes");
		chckbxAnomes.setOpaque(false);
		chckbxAnomes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxAnomes.isSelected()) {
					calendar.getDayChooser().setVisible(false);
					calendar.getMonthChooser().setVisible(true);
					
					colocaDadosNaTabelaGasto(new CrudCompras().procurarCompraDataAnoMes(String.valueOf(calendar.getYearChooser().getValue()),
							String.valueOf(calendar.getMonthChooser().getMonth()+1), Principal.fazenda.getIdFazenda()));
					
					colocaDadosNaTabelaReceita(new CrudVendas().procurarVendasDataAnoMes(String.valueOf(calendar.getYearChooser().getValue()),
							String.valueOf(calendar.getMonthChooser().getMonth()+1), Principal.fazenda.getIdFazenda()));
					
					valor(new CrudCompras().procurarCompraDataAnoMes(String.valueOf(calendar.getYearChooser().getValue()),
							String.valueOf(calendar.getMonthChooser().getMonth()+1), Principal.fazenda.getIdFazenda()),
						new CrudVendas().procurarVendasDataAnoMes(String.valueOf(calendar.getYearChooser().getValue()),
							String.valueOf(calendar.getMonthChooser().getMonth()+1), Principal.fazenda.getIdFazenda()));
				}
			}
		});
		chckbxAnomes.setBounds(370, 88, 97, 23);
		frmRelattio.getContentPane().add(chckbxAnomes);
		
		chckbxAnomesdia = new JCheckBox("Ano-Mes-Dia");
		chckbxAnomesdia.setOpaque(false);
		chckbxAnomesdia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxAnomesdia.isSelected()) {
					calendar.getDayChooser().setVisible(true);
					calendar.getMonthChooser().setVisible(true);

					
					colocaDadosNaTabelaGasto(new CrudCompras().procurarCompraDataAnoDia(String.valueOf(calendar.getYearChooser().getValue()),
							String.valueOf(calendar.getMonthChooser().getMonth()+1), String.valueOf(calendar.getDayChooser().getDay()),  Principal.fazenda.getIdFazenda()));
					
					colocaDadosNaTabelaReceita(new CrudVendas().procurarVendasDataAnoDia(String.valueOf(calendar.getYearChooser().getValue()),
							String.valueOf(calendar.getMonthChooser().getMonth()+1), String.valueOf(calendar.getDayChooser().getDay()),  Principal.fazenda.getIdFazenda()));
				
					valor(new CrudCompras().procurarCompraDataAnoDia(String.valueOf(calendar.getYearChooser().getValue()),
							String.valueOf(calendar.getMonthChooser().getMonth()+1), String.valueOf(calendar.getDayChooser().getDay()),  Principal.fazenda.getIdFazenda()),
						new CrudVendas().procurarVendasDataAnoDia(String.valueOf(calendar.getYearChooser().getValue()),
							String.valueOf(calendar.getMonthChooser().getMonth()+1), String.valueOf(calendar.getDayChooser().getDay()),  Principal.fazenda.getIdFazenda()));
				}
			}
		});
		chckbxAnomesdia.setBounds(370, 114, 97, 23);
		frmRelattio.getContentPane().add(chckbxAnomesdia);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(chckbxAno);
		bg.add(chckbxAnomes);
		bg.add(chckbxAnomesdia);
		
		DefaultPieDataset pizza = new DefaultPieDataset();
		
		pizza.setValue("Produto", 5);
		pizza.setValue("Compra",5);
		
		JFreeChart grafico = ChartFactory.createPieChart("sal",pizza,true,true,false);
		ChartPanel painel = new ChartPanel(grafico);
		frmRelattio.getContentPane().add(painel);
		
		btnGraficoSetor = new JButton("Gr\u00E1fico de setor");
		btnGraficoSetor.setBackground(new Color(245, 245, 245));
		btnGraficoSetor.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnGraficoSetor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GraficoPizza.main(null);
			}
		});
		btnGraficoSetor.setBounds(736, 593, 139, 23);
		frmRelattio.getContentPane().add(btnGraficoSetor);
		
		btnGrficoBarra = new JButton("Gr\u00E1fico de barra");
		btnGrficoBarra.setBackground(new Color(245, 245, 245));
		btnGrficoBarra.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnGrficoBarra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GraficoBarra.main(null);
			}
		});
		btnGrficoBarra.setBounds(885, 593, 139, 23);
		frmRelattio.getContentPane().add(btnGrficoBarra);
		
		scrollPaneGasto = new JScrollPane();
		scrollPaneGasto.setBackground(new Color(245, 245, 245));
		scrollPaneGasto.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		scrollPaneGasto.setBounds(10, 250, 519, 23);
		frmRelattio.getContentPane().add(scrollPaneGasto);
		
		tabelaGasto = new JTable();
		tabelaGasto.setGridColor(SystemColor.activeCaption);
		tabelaGasto.setSelectionBackground(new Color(135, 206, 250));
		tabelaGasto.setBackground(new Color(245, 245, 245));
		tabelaGasto.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Produto", "Pre\u00E7o", "Data"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tabelaGasto.getColumnModel().getColumn(0).setResizable(false);
		tabelaGasto.getColumnModel().getColumn(1).setResizable(false);
		tabelaGasto.getColumnModel().getColumn(2).setResizable(false);
		scrollPaneGasto.setViewportView(tabelaGasto);
		
		JLabel lblGastos = new JLabel("Gastos:");
		lblGastos.setHorizontalAlignment(SwingConstants.CENTER);
		lblGastos.setForeground(new Color(0, 0, 0));
		lblGastos.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblGastos.setBounds(289, 510, 104, 40);
		frmRelattio.getContentPane().add(lblGastos);
		
		JLabel lblReceita_1 = new JLabel("Receita:");
		lblReceita_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblReceita_1.setForeground(new Color(0, 0, 0));
		lblReceita_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblReceita_1.setBounds(545, 510, 104, 40);
		frmRelattio.getContentPane().add(lblReceita_1);
		
		lblSemDados1 = new JLabel("Sem dados salvos!");
		lblSemDados1.setVisible(false);
		lblSemDados1.setHorizontalAlignment(SwingConstants.CENTER);
		lblSemDados1.setForeground(new Color(0, 0, 0));
		lblSemDados1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSemDados1.setBounds(147, 264, 217, 40);
		frmRelattio.getContentPane().add(lblSemDados1);
		
		lblSemdados2 = new JLabel("Sem dados salvos!");
		lblSemdados2.setVisible(false);
		lblSemdados2.setHorizontalAlignment(SwingConstants.CENTER);
		lblSemdados2.setForeground(new Color(0, 0, 0));
		lblSemdados2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSemdados2.setBounds(711, 264, 206, 40);
		frmRelattio.getContentPane().add(lblSemdados2);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(Total.class.getResource("/libs/img/libs/img/teste13.jpg")));
		label_1.setBounds(0, -19, 1074, 670);
		frmRelattio.getContentPane().add(label_1);
		
		compras.setIdFazenda(Principal.fazenda.getIdFazenda());
		vendas.setIdFazenda(Principal.fazenda.getIdFazenda());
		
		menu();
		
		colocaDadosNaTabelaReceita(CrudVendas.selecionaVendas(vendas));
		colocaDadosNaTabelaGasto(CrudCompras.selecionaCompras(compras));
	}
	
	void colocaDadosNaTabelaGasto(ResultSet rs) {
		DefaultTableModel modelo = (DefaultTableModel) tabelaGasto.getModel();
		modelo.setNumRows(0);
		scrollPaneGasto.setVisible(true);
		lblSemDados1.setVisible(false);
		btnGraficoSetor.setEnabled(true);
		btnGrficoBarra.setEnabled(true);
		
		try {
			while (rs.next()) {
				modelo.addRow(new Object[] {rs.getString("produto"),rs.getString("preco"),rs.getString("data_compra")});
				
			}
			
			//z	//tabela
			if (x1==1) {
			if (scrollPaneGasto.getHeight()<250) {
				
				int tabel = tabelaGasto.getRowCount();
				if (tabelaGasto.getRowCount()>14) {
					tabel=13;
				}
				int linha = tabel*16;
				int valor = 23+linha;
				scrollPaneGasto.setBounds(10, 250, 519, valor);
				if (tabelaGasto.getRowCount()==0) {
					scrollPaneGasto.setVisible(false);
					lblSemDados1.setVisible(true);
					btnGraficoSetor.setEnabled(false);
					btnGrficoBarra.setEnabled(false);
					
				}
				
			}
			//tabela
			
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void colocaDadosNaTabelaReceita(ResultSet rs) {
		ResultSet dadosAnimal=null;
		String produto=null;
		
		DefaultTableModel modelo = (DefaultTableModel) tabelaReceita.getModel();
		modelo.setNumRows(0);
		scrollPaneReceita.setVisible(true);
		lblSemdados2.setVisible(false);
		btnGraficoSetor.setEnabled(true);
		btnGrficoBarra.setEnabled(true);
		try {
			while (rs.next()) {
				
				if (rs.getString("produto")==(null)) {
					String sql = "select * from animais where idanimal=?";
					PreparedStatement stmt = Conexao.conexao.prepareStatement(sql);
					stmt.setInt(1, rs.getInt("idanimal"));
					dadosAnimal = stmt.executeQuery();
					stmt.execute();
					stmt.close();
					
					if (dadosAnimal.next()) {
						produto=dadosAnimal.getString("nomelote")+" (lote de animais)";
					}
				}else {
					produto=rs.getString("produto");
				}
				
				modelo.addRow(new Object[] {produto,rs.getString("preco"),rs.getString("datavenda")});	
			}
			
			//tabela
			if (x1==1) {
			if (scrollPaneReceita.getHeight()<250) {
				
				int tabel = tabelaReceita.getRowCount();
				if (tabelaReceita.getRowCount()>14) {
					tabel=13;
				}
				int linha = tabel*16;
				int valor = 23+linha;
				scrollPaneReceita.setBounds(545, 250, 519, valor);
				if (tabelaReceita.getRowCount()==0) {
					scrollPaneReceita.setVisible(false);
					lblSemdados2.setVisible(true);
					btnGraficoSetor.setEnabled(false);
					btnGrficoBarra.setEnabled(false);
				}
				
			}
			//tabela
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void valor(ResultSet dadosCompras, ResultSet dadosVendas) {
		
		 float valor1=0,valor2=0,total=0;
		
		try {
			while(dadosCompras.next()) {
				valor1 = valor1 + dadosCompras.getFloat("preco");
			}
			
			while(dadosVendas.next()) {
				valor2 = valor2 + dadosVendas.getFloat("preco");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		valorGasto.setText(String.valueOf(valor1));
		valorReceita.setText(String.valueOf(valor2));
		total = valor2 - valor1;
		if(total < 0) {
			label.setForeground(Color.RED);
		}
		if(total > 0) {
			label.setForeground(Color.GREEN);
		}
		label.setText(String.valueOf(total));
		
		
		
	
		
		
		
	}
	
	
	
		
		
	
	
	public void menu() {	
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(105, 105, 105), new Color(105, 105, 105), new Color(105, 105, 105), new Color(105, 105, 105)));
		menuBar.setForeground(Color.GREEN);
		menuBar.setBackground(Color.DARK_GRAY);
		frmRelattio.setJMenuBar(menuBar);
		
		JMenu mnInicio = new JMenu("");
		mnInicio.setBorder(new SoftBevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY));
		mnInicio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnInicio.setOpaque(true);
		mnInicio.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mnInicio.setIcon(new ImageIcon(Total.class.getResource("/libs/img/libs/img/Icone_Inicio.png")));
		mnInicio.setForeground(Color.WHITE);
		mnInicio.setBackground(Color.DARK_GRAY);
		mnInicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Principal.frmPrincipal.setVisible(true);
				frmRelattio.dispose();
			}
		});
		menuBar.add(mnInicio);
		
		JMenu mnNewMenu = new JMenu("");
		mnNewMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnNewMenu.setIcon(new ImageIcon(Total.class.getResource("/libs/img/libs/img/Icone_GEstao.png")));
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
				frmRelattio.dispose();
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
				frmRelattio.dispose();
			}
		});
		
		JMenu mnNewMenu_1 = new JMenu("");
		mnNewMenu_1.setBorder(new SoftBevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY));
		
		mnNewMenu_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnNewMenu_1.setIcon(new ImageIcon(Total.class.getResource("/libs/img/libs/img/Icone_Financeiro.png")));
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
				frmRelattio.dispose();
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
				//Total.main(null);
				//frmRelattio.dispose();
			}
		});
		mntmNovaVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NovaVenda.main(null);
				frmRelattio.dispose();
			}
		});
		
		JMenu mnOpes = new JMenu("");
		mnOpes.setBorder(new SoftBevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY));
		mnOpes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnOpes.setIcon(new ImageIcon(Total.class.getResource("/libs/img/libs/img/Icone_OPCAO.png")));
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
				frmRelattio.dispose();
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
				Pergunta.outroFrame=frmRelattio;
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
				frmRelattio.dispose();
				Login.main(null);
			}
		});
		mnOpes.add(mntmDeslogar);

		
		mnOpes.add(mntmSada);
		frmRelattio.getContentPane().setLayout(null);
	}
}
