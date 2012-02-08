package vue;

import java.awt.BorderLayout;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public abstract class AjoutFrame extends JFrame {
	
	protected JPanel container;
	protected JLabel lblTitreFrame;
	protected JPanel panelButton;
	protected JButton btnAjouter;
	protected JButton btnAnnuler;
	
	public AjoutFrame(String titreFrame) {
		super(titreFrame);
		
		setSize(400, 350);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		container = new JPanel();
		container.setLayout(new BorderLayout());
		
		lblTitreFrame = new JLabel(titreFrame);
		container.add(lblTitreFrame, BorderLayout.NORTH);
		
		panelButton = new JPanel();
		btnAjouter = new JButton("Ajouter");
		btnAnnuler = new JButton("Annuler");

		panelButton.add(btnAjouter);
		panelButton.add(btnAnnuler);
		
		container.add(panelButton, BorderLayout.SOUTH);
		
		setContentPane(container);
		
		
	}
}
