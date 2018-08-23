package outraJanelas;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

import banco.Conexao;

public class Splash {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Splash window = new Splash();
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
	public Splash() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Splash.class.getResource("/libs/img/libs/img/32x32.png")));
		frame.setResizable(false);
		frame.setBounds(100, 100, 430, 230);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setUndecorated(true); //tirar os botoes de cima e a borda
		frame.getContentPane().setBackground(new Color(1.0f,1.0f,1.0f,0.0f)); //tranparente
		frame.setBackground(new Color(1.0f,1.0f,1.0f,0.0f));				  //tranparente
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JLabel lblX = new JLabel("X");
		lblX.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblX.setForeground(Color.RED);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblX.setForeground(Color.black);
			}
		});
		lblX.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setBounds(391, 11, 19, 14);
		frame.getContentPane().add(lblX);
		
		ImageIcon iconAnimal = new ImageIcon("/img/gradiente_Branco.jpg");
		iconAnimal.setImage(iconAnimal.getImage().getScaledInstance(420, 250 ,100));
		
		progressBar = new JProgressBar();
		progressBar.setOpaque(true);
		progressBar.setBounds(0, 210, 430, 12);
		progressBar.setForeground(new Color(102, 204, 51));
		progressBar.setStringPainted(true);
		progressBar.setMaximum(30);
		frame.getContentPane().add(progressBar);
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Splash.class.getResource("/libs/img/libs/img/128x128.png")));
		label.setBounds(0, 0, 410, 218);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(label);
		
		new Thread(proximaJanela).start();	//inicia a tarefa paralela
		Conexao.getConexao();//estabelece conexao
	}
	
	Runnable proximaJanela = new Runnable() {	
		@Override
		public void run() {
			int x=0;
			try {
				while(true) {
					Thread.sleep(100);
					 x++;
					 progressBar.setValue(x);
					 if(x==30) {
						 break;
					 }
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			frame.dispose();
			Login.main(null);
		}
	};
	private JProgressBar progressBar;
}
