package vue;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import modèle.*;

import controleur.Bibliotheque;

public class MainFrame2 extends JFrame implements ActionListener, ChangeListener {
  
	String[] firstItem = {"Livre", "Abonné", "En cours"};
	JTabbedPane pnlOnglet;
	JComboBox<String> listeItems;
	ListePanel pnlData;
	JPanel pnlActionButtons;
	JButton btnAjouter;
	JButton btnSupprimer;
	JButton btnModifier;
	
	public MainFrame2(){
		super("Bibliothèque 4000 !!");
		
		buildInterface();
		buildEvents();
	}

	public void buildInterface() {
		setLocationRelativeTo(null);
		this.setLayout(new BorderLayout(5,5));
		this.setSize(400, 200);
		
		pnlOnglet = new JTabbedPane();
		listeItems = new JComboBox<String>();
		setItemsComboBox(firstItem[0]);
		
		pnlData = new ListePanel(Bibliotheque.getListLivre()); // Par défaut au démarrage de l'appli
		pnlOnglet.addTab("Médias", pnlData);
		pnlOnglet.addTab("Membres", null);
		pnlOnglet.addTab("Emprunts", null);
		
		pnlActionButtons = new JPanel();
		btnAjouter = new JButton("Ajouter");
		btnSupprimer = new JButton("Supprimer");
		btnModifier = new JButton("Modifier");

		pnlActionButtons.add(btnAjouter);
		pnlActionButtons.add(btnModifier);
		pnlActionButtons.add(btnSupprimer);
		
		pnlData.add(listeItems, BorderLayout.NORTH);
		
		this.add(pnlOnglet);
		this.add(pnlActionButtons,BorderLayout.SOUTH);

		this.pack();
	}
	
	public void buildEvents() {
		pnlOnglet.addChangeListener(this); // Pour le rafraichissement lors d'un changement d'onglet
		listeItems.addActionListener(this);
		btnAjouter.addActionListener(this);
		btnSupprimer.addActionListener(this);
		btnModifier.addActionListener(this);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment quitter l'application ?", "Fermeture de l'application", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					Bibliotheque.closeBibliotheque();
				}
			} 
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String selectedItem = (String) this.listeItems.getSelectedItem();
		int selectedIndex = this.pnlOnglet.getSelectedIndex();
		
		/* ===== Bouton AJOUTER ===== */
		if(e.getSource() == this.btnAjouter) { 
			AjoutDialog ajd = null;
			
			// Média
			if(selectedIndex == 0) {
				
				if(selectedItem.equals("Livre"))
					ajd = new AjoutDialog("Ajouter un livre", Livre.class, null);					
				else if(selectedItem.equals("Magazine"))
					ajd = new AjoutDialog("Ajouter un magazine", Magazine.class, null);
				else if(selectedItem.equals("CD"))
					ajd = new AjoutDialog("Ajouter un CD", Cd.class, null);
				else if(selectedItem.equals("DVD"))
					ajd = new AjoutDialog("Ajouter un DVD", Dvd.class, null);
				else if(selectedItem.equals("Coffret DVD"))
					ajd = new AjoutDialog("Ajouter un coffret DVD", CoffretDvd.class, null);
				else if(selectedItem.equals("AudioLivre"))
					ajd = new AjoutDialog("Ajouter un audiolivre", AudioLivre.class, null);
			}
			// Membre
			else if(selectedIndex == 1) {
				if(selectedItem == "Abonné")
					ajd = new AjoutDialog("Ajouter un abonné", Abonne.class, null);
				else if(selectedItem == "Personnel")
					ajd = new AjoutDialog("Ajouter un membre du personnel", Personnel.class, null);
			}
			
			if(selectedIndex == 0 || selectedIndex == 1) {
				ajd.setVisible(true); // On affiche le formulaire
				
				if(ajd.getReturnStatus() == BiblioDialog.RET_OK) // Si l'objet à été ajouté
					refreshComponents((String) selectedItem); // On met à jour la JTable
			}
			
			// Emprunt
			else if(selectedIndex == 2) {
				EmpruntDialog empdial = new EmpruntDialog(this);
				empdial.setVisible(true);
				
				if(empdial.getReturnStatus() == BiblioDialog.RET_OK)
					refreshComponents((String) selectedItem);
				
			}
			
		}
		
		/* ===== Bouton SUPPRIMER ===== */
		if(e.getSource() == btnSupprimer) {
			int row = pnlData.getTable().getSelectedRow();

			if(row == -1) { // Si aucune ligne n'est sélectionnée
				JOptionPane.showConfirmDialog(this, "Veuillez sélectionner une ligne avant de supprimer", "Aucune ligne sélectionnée", JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE);
			}
			// Média
			else if(selectedIndex == 0) {
				
				String isbn = (String) pnlData.getTable().getValueAt(row, 0);
				String titre = (String) pnlData.getTable().getValueAt(row, 1);
					
				int rep = JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment supprimer le média "+isbn+" ("+titre+") ?", "Suppresion d'un média", JOptionPane.YES_NO_OPTION);

				if(rep == JOptionPane.YES_OPTION) {
					Bibliotheque.delMedia(isbn);
					refreshComponents(selectedItem);
				}
			}
			// Membre
			else if(selectedIndex == 1) {				
				
				int id = Integer.parseInt((String) pnlData.getTable().getValueAt(row, 0));
				String nom = (String) pnlData.getTable().getValueAt(row, 1);
				String prenom = (String) pnlData.getTable().getValueAt(row, 2);
				
				int rep = JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment supprimer le membre "+nom+" "+prenom+" ("+id+") ?", "Suppresion d'un membre", JOptionPane.YES_NO_OPTION);

				if(rep == JOptionPane.YES_OPTION) {
					Bibliotheque.delMembre(id);
					refreshComponents(selectedItem);
				}
			}
			// Emprunt
			else if(selectedIndex == 2) { // Dans ce cas là, on ne fait pas une suppression mais on termine l'emprunt
				String isbn = (String) pnlData.getTable().getValueAt(row, 0);
				int id = Integer.parseInt((String) pnlData.getTable().getValueAt(row, 2));
				
				int rep = JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment terminer l'emprunt en cours ?", "Suppresion d'un membre", JOptionPane.YES_NO_OPTION);
				
				if(rep == JOptionPane.YES_OPTION) {
					Bibliotheque.terminerEmprunt(isbn, id);
					refreshComponents(selectedItem);
				}
			}
		}
		
		/* ===== Changement de sélection d'un item ===== */
		if(e.getSource() == listeItems) {
			if(selectedItem != null) {
				refreshComponents(selectedItem);
			}
		}
	}
	
	/**
	 * Action lors d'un changement d'onglet
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		if(e.getSource() == pnlOnglet) {
			
			refreshComponents(firstItem[pnlOnglet.getSelectedIndex()]); // MAJ JTable
			setItemsComboBox(firstItem[pnlOnglet.getSelectedIndex()]); // MAJ JComboBox
		
		}
	}

	private void refreshComponents(Object item) {
		System.out.println(item);
		// Medias
		if(item.equals("Livre")) {
			pnlData.setList(Bibliotheque.getListLivre());
			btnSupprimer.setText("Supprimer");
			btnSupprimer.setEnabled(true);
		}
		else if(item.equals("Magazine")) {
			pnlData.setList(Bibliotheque.getListMagazine());
			btnSupprimer.setText("Supprimer");
			btnSupprimer.setEnabled(true);
		}
		else if(item.equals("CD")) {
			pnlData.setList(Bibliotheque.getListCd());
			btnSupprimer.setText("Supprimer");
			btnSupprimer.setEnabled(true);
		}
		else if(item.equals("DVD")) {
			pnlData.setList(Bibliotheque.getListDvd());
			btnSupprimer.setText("Supprimer");
			btnSupprimer.setEnabled(true);
		}
		else if(item.equals("Coffret DVD")) {
			pnlData.setList(Bibliotheque.getListCoffretDvd());
			btnSupprimer.setText("Supprimer");
			btnSupprimer.setEnabled(true);
		}
		else if(item.equals("AudioLivre")) {
			pnlData.setList(Bibliotheque.getListAudioLivre());
			btnSupprimer.setText("Supprimer");
			btnSupprimer.setEnabled(true);
		}
		// Membres
		else if(item.equals("Abonné")) {
			pnlData.setList(Bibliotheque.getListAbonnes());
			btnSupprimer.setText("Supprimer");
			btnSupprimer.setEnabled(true);
		}
		else if(item.equals("Personnel")) {
			pnlData.setList(Bibliotheque.getListPersonnels());
			btnSupprimer.setText("Supprimer");
			btnSupprimer.setEnabled(true);
		}
		// Emprunts
		else if(item.equals("En cours")) {
			pnlData.setList(Bibliotheque.getListEmpruntsEnCours());
			btnSupprimer.setText("Terminer l'emprunt");
			btnSupprimer.setEnabled(true);
		}
		else if(item.equals("Terminés")) {
			pnlData.setList(Bibliotheque.getListEmpruntsTermines());
			btnSupprimer.setText("Terminer l'emprunt");
			btnSupprimer.setEnabled(false);
		}
	}
	
	private void setItemsComboBox(String firstItem) {
		listeItems.removeAllItems();
		
		if(firstItem.equals("Livre")) {
			listeItems.addItem("Livre");
			listeItems.addItem("Magazine");
			listeItems.addItem("CD");
			listeItems.addItem("DVD");
			listeItems.addItem("Coffret DVD");
			listeItems.addItem("AudioLivre");
		}
		else if(firstItem.equals("Abonné")) {
			listeItems.addItem("Abonné");
			listeItems.addItem("Personnel");
		}
		else if(firstItem.equals("En cours")) {
			listeItems.addItem("En cours");
			listeItems.addItem("Terminés");
		}
	}
}
