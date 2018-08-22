package JanelasComtabil;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;
import java.awt.Color;
import javax.swing.border.BevelBorder;

public class GraficoBarra extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GraficoBarra frame = new GraficoBarra();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GraficoBarra() {
		setType(Type.UTILITY);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 915, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setResizable(false);
		setLocationRelativeTo(null);
		
		setContentPane(contentPane);
		criarGrafico();
	}
	
	public void criarGrafico() {
		DefaultCategoryDataset barra = new DefaultCategoryDataset();
		
			
			barra.setValue(Float.parseFloat(Total.valorGasto.getText()),"Gasto","");
			barra.setValue( Float.parseFloat(Total.valorReceita.getText()),"Receita","");
			
			JFreeChart grafico = ChartFactory.createBarChart3D("Total", "", "Valor",barra, PlotOrientation.VERTICAL, true, true, true);
			ChartPanel painel = new ChartPanel(grafico);
			getContentPane().add(painel);
			
			JButton btnSair = new JButton("Sair");
			btnSair.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			btnSair.setBackground(new Color(245, 245, 245));
			btnSair.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			contentPane.add(btnSair, BorderLayout.SOUTH);
		}

}
