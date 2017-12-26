package gui;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ResizableImage {

	public static void setResizeImage(JPanel panel, JButton button, String path, int width, int height) {
		Image imgResize = loadImage(panel, path, width, height);
		button.setIcon(new ImageIcon(imgResize));
		button.setDisabledIcon(new ImageIcon(imgResize));
	}

	public static void setResizeImage(JPanel panel, JLabel label, String path, int width, int height) {
		Image imgResize = loadImage(panel, path, width, height);
		label.setIcon(new ImageIcon(imgResize));
		label.setDisabledIcon(new ImageIcon(imgResize));
	}

	private static Image loadImage(JPanel panel, String path, int width, int height) {
		Image imgOriginal;
		try {
			imgOriginal = new ImageIcon(panel.getClass().getResource(path)).getImage();
		} catch (Exception e) {
			imgOriginal = new ImageIcon(panel.getClass().getResource("/img/DE000.jpg")).getImage();
		}
		Image imgResize = imgOriginal.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return imgResize;
	}

}
