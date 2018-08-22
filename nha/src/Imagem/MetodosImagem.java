package Imagem;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MetodosImagem {
	
	public File selecionaImg() {//seleciona uma imagem do diretorio
		JFileChooser fileC = new JFileChooser();
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("imagens em JPEG e PNG", "jpg","png");
		
		fileC.addChoosableFileFilter(filtro);
		fileC.setAcceptAllFileFilterUsed(false);
		fileC.setCurrentDirectory(new File("D:"));
		fileC.setDialogType(JFileChooser.OPEN_DIALOG);
		fileC.showOpenDialog(fileC);
		
		return fileC.getSelectedFile();
	}
	
	public byte[] getImagem(File img,JPanel panel) {//transforma a imagem em um array de byte
		boolean isPng = false;
		
		if(img != null) {
			isPng = img.getName().endsWith("png");
			
			try {
				BufferedImage image = ImageIO.read(img);
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				int type = BufferedImage.TYPE_INT_RGB;
				
				if(isPng) {
					type = BufferedImage.BITMASK;
				}
				
				BufferedImage novaImg = new BufferedImage(panel.getWidth(),panel.getHeight(), type);
				Graphics2D g = novaImg.createGraphics();
				g.setComposite(AlphaComposite.Src);
				g.drawImage(image, 0, 0, panel.getWidth(), panel.getHeight(), null);
				
				if(isPng)
					ImageIO.write(novaImg, "png", out);
				else
					ImageIO.write(novaImg, "jpg", out);
				
				out.flush();
				byte[] byteArray = out.toByteArray();
				out.close();
				
				return byteArray;
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return null;
	}
	
public void abrirImagem(Object source,File img,JPanel panel,JLabel lblImg,byte[] imgByte) {//abra a imagem
		
		if(source instanceof File) {
			ImageIcon icon = new ImageIcon(img.getAbsolutePath());
			icon.setImage(icon.getImage().getScaledInstance(panel.getWidth(),panel.getHeight(), 100));
			lblImg.setIcon(icon);
		}
		if(source instanceof byte[]) {
			ImageIcon icon = new ImageIcon(imgByte);
			icon.setImage(icon.getImage().getScaledInstance(panel.getWidth(),panel.getHeight(), 100));
			lblImg.setIcon(icon);
		}	
		
	}
}
