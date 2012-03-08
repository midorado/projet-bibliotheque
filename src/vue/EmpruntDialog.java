package vue;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modele.Media;
import modele.Membre;

import controleur.Bibliotheque;

public class EmpruntDialog extends JDialog implements ActionListener {

	private int retStatus = -1;
	private JButton btnValider;
	private JButton btnAnnuler;
	private Media media;
	private JTextField txtRechercheMembre;
	private JButton btnRechercher;
	private JComboBox comboResultMembre;
	private List<Membre> resultMembres;
	
	public EmpruntDialog(JFrame parent, String isbn) {
		super(parent, "Effecter un nouvel emprunt", true);
		
		this.media = Bibliotheque.getMediaByIsbn(isbn);
		
		buildInterface();
		buildEvents();
	}
	
	private void buildInterface() {
		setLayout(new BorderLayout());
		
		setLocationRelativeTo(null);
		setResizable(false);
		
		JPanel pnlTitre = new JPanel();
		JLabel lblTitre = new JLabel("Emprunt du média ["+media.getIsbn()+"] "+media.getTitre()+" ("+media.getPrix()+"€)");
		lblTitre.setFont(new Font("Arial", Font.BOLD, 15));
		pnlTitre.add(lblTitre);
		
		JLabel lblRechercheMembre = new JLabel("Rechercher un membre : ");
		txtRechercheMembre = new JTextField(20);
		btnRechercher = new JButton("Rechercher");
		comboResultMembre = new JComboBox();
		
		JPanel pnlRecherche = new JPanel(new BorderLayout());
		pnlRecherche.add(lblRechercheMembre, BorderLayout.WEST);
		pnlRecherche.add(txtRechercheMembre, BorderLayout.CENTER);
		pnlRecherche.add(btnRechercher, BorderLayout.EAST);
		pnlRecherche.add(comboResultMembre, BorderLayout.SOUTH);
		
		
		JPanel pnlButtons = new JPanel();
		this.btnValider = new JButton("Emprunter");
		this.btnAnnuler = new JButton("Annuler");
		
		pnlButtons.add(btnValider);
		pnlButtons.add(btnAnnuler);
		
		getContentPane().add(pnlTitre, BorderLayout.NORTH);
		getContentPane().add(pnlRecherche, BorderLayout.CENTER);
		getContentPane().add(pnlButtons, BorderLayout.SOUTH);
		
		pack();
	}
	
	private void buildEvents() {
		btnValider.addActionListener(this);
		btnAnnuler.addActionListener(this);
		btnRechercher.addActionListener(this);
	}
	
	public int getReturnStatus() {
		return this.retStatus;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnRechercher) {
			comboResultMembre.removeAllItems();
			
			String recherche = txtRechercheMembre.getText();
			
			if(!recherche.isEmpty()) {
				resultMembres = Bibliotheque.rechercheMembre(recherche);
				
				if(resultMembres != null && !resultMembres.isEmpty())
				for(Membre m : resultMembres) {
					String item = m.getIdentifiant()+" - "+m.getPrenom()+" "+m.getNom()+" ("+m.getStringDateNaissance()+")";
					comboResultMembre.addItem(item);
				}
			}
		}
		else if(e.getSource() == btnAnnuler) {
			this.retStatus = BiblioDialog.RET_CANCEL;
			setVisible(false);
			dispose();
		}
		else if(e.getSource() == btnValider) {
			int selectedIndex = comboResultMembre.getSelectedIndex();
	
			if(selectedIndex != -1) {
				Membre membre = resultMembres.get(selectedIndex);
				int idMembre = membre.getIdentifiant();
				
				// On vérifie que le média n'est pas en cours d'emprunt ou d'écoute/lecture
				if(!Bibliotheque.isEmpruntable(media.getIsbn())) {
					JOptionPane.showConfirmDialog(this, "Le média est déjà en cours d'emprunt ou en cours d'écoute", "Erreur", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
				}
				else { // Tout est OK en principe !
					// On informe du prix du l'emprunt
					float prix = media.getPrix() - (media.getPrix() * membre.getTauxReduction()); // On applique la réduction
					JOptionPane.showConfirmDialog(this, "Le tarif du prêt est de "+prix+"€", "Erreur", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
					// On créé un nouvel emprunt
					Bibliotheque.nouvelEmprunt(media.getIsbn(), idMembre);
					this.retStatus = BiblioDialog.RET_OK; 	// On informe la frame parente que tout est OK
					this.dispose();
				}
			}
		}
	}
}
