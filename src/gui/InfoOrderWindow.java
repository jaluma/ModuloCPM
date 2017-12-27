package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import fileUtil.FileUtil;
import internationalization.Internationalization;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ActionEvent;

public class InfoOrderWindow extends JPanel {

	private static final long serialVersionUID = 1L;
	private MainWindow mainWindow;
	private JPanel contentPane;
	private JPanel panelNorth;
	private JLabel lblLogo;
	private JScrollPane scrollPaneOrder;
	private JTextArea txtrInfoOrder;
	private JPanel panelButton;
	private JButton btnFinish;
	private JButton btnCancel;

	public InfoOrderWindow(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
		setLayout(new BorderLayout(0, 0));
		add(getPanelNorth(), BorderLayout.NORTH);
		add(getScrollPaneOrder(), BorderLayout.CENTER);
		add(getPanelSouth(), BorderLayout.SOUTH);
	}

	private JPanel getPanelNorth() {
		if (panelNorth == null) {
			panelNorth = new JPanel();
			panelNorth.setBackground(Color.WHITE);
			FlowLayout flowLayout = (FlowLayout) panelNorth.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			panelNorth.add(getLblLogo());
		}
		return panelNorth;
	}

	private JLabel getLblLogo() {
		if (lblLogo == null) {
			lblLogo = new JLabel("");
			ResizableImage.setResizeImage(this, lblLogo, "/img/logo.png", 250, 100);
		}
		return lblLogo;
	}

	private JScrollPane getScrollPaneOrder() {
		if (scrollPaneOrder == null) {
			scrollPaneOrder = new JScrollPane();
			scrollPaneOrder.setViewportView(getTxtrInfoOrder());
		}
		return scrollPaneOrder;
	}

	private JTextArea getTxtrInfoOrder() {
		if (txtrInfoOrder == null) {
			txtrInfoOrder = new JTextArea();
			txtrInfoOrder.setWrapStyleWord(true);
			txtrInfoOrder.setLineWrap(true);
			txtrInfoOrder.setEditable(false);
			txtrInfoOrder.setText(mainWindow.getOrder().toString());
		}
		return txtrInfoOrder;
	}

	private JPanel getPanelSouth() {
		if (panelButton == null) {
			panelButton = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panelButton.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			panelButton.setBackground(Color.WHITE);
			panelButton.add(getBtnCancel());
			panelButton.add(getBtnFinish());
		}
		return panelButton;
	}

	@SuppressWarnings("deprecation")
	private JButton getBtnFinish() {
		if (btnFinish == null) {
			btnFinish = new JButton(Internationalization.getString("info_finish"));
			btnFinish.setToolTipText(Internationalization.getToolTips("info_finish"));
			btnFinish.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String nameFile = "orders/";
					SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
					nameFile = format.format(mainWindow.getDate()) + "_" + mainWindow.getOrder().getDni();
					nameFile += ".dat";
					
					try {
						FileUtil.saveToFile(nameFile, Arrays.asList(mainWindow.getOrder().toString()));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					removeAll();
					add(new FinishWindow(mainWindow));
					revalidate();
					repaint();
					
				}
			});
		}
		return btnFinish;
	}

	private JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton(Internationalization.getString("info_cancel"));
			btnCancel.setToolTipText(Internationalization.getToolTips("info_cancel"));
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Window w = SwingUtilities.getWindowAncestor(InfoOrderWindow.this);
			        w.dispose();
				}
			});
		}
		return btnCancel;
	}

}
