package vue;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import modèle.*;

import controleur.Bibliotheque;

public class MainFrame extends JFrame implements ActionListener, ChangeListener {
  
	String[] firstItem = {"Livre", "Abonné", "En cours", "En lecture"};
	JTabbedPane pnlOnglet;
	JComboBox comboItems;
	ListePanel pnlData;
	JPanel pnlActionButtons;
	JButton btnEmprunter;
	JButton btnAjouter;
	JButton btnSupprimer;
	JButton btnModifier;
	JButton btnLecture;
	
	public MainFrame() {
		super("Biblio 4000 !!!");
		
		buildInterface();
		buildEvents();
	}

	public void buildInterface() {
		setLocationRelativeTo(null);
		this.setLayout(new BorderLayout(5,5));
		this.setSize(400, 200);
		
		pnlOnglet = new JTabbedPane();
		comboItems = new JComboBox<String>();
		setItemsComboBox(firstItem[0]);
		
		pnlData = new ListePanel(Bibliotheque.getListLivre()); // Par défaut au démarrage de l'appli
		pnlOnglet.addTab("Médias", pnlData);
		pnlOnglet.addTab("Membres", null);
		pnlOnglet.addTab("Emprunts", null);
		pnlOnglet.addTab("Lectures en cours", null);
		
		btnEmprunter = new JButton("Emprunter");
		btnAjouter = new JButton("Ajouter");
		btnSupprimer = new JButton("Supprimer");
		btnModifier = new JButton("Modifier");
		btnLecture = new JButton("Démarrer/Arrêter lecture");
		
		pnlActionButtons = new JPanel();
		pnlActionButtons.add(btnAjouter);
		pnlActionButtons.add(btnModifier);
		pnlActionButtons.add(btnSupprimer);
		pnlActionButtons.add(btnLecture);
	
		// Ajout des bordures vides autour des composants
		pnlOnglet.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0), btnEmprunter.getBorder()));
		comboItems.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(), btnEmprunter.getBorder()));
		
		// Ajout des composants dans les panels
		JPanel pnlButtons = new JPanel(new BorderLayout());
		pnlButtons.add(btnEmprunter, BorderLayout.NORTH);
		pnlButtons.add(pnlActionButtons, BorderLayout.CENTER);

		pnlData.add(comboItems, BorderLayout.NORTH);
		
		this.add(pnlOnglet);
		this.add(pnlButtons,BorderLayout.SOUTH);

		this.pack();
	}
	
	public void buildEvents() {
		pnlOnglet.addChangeListener(this); // Pour le rafraichissement lors d'un changement d'onglet
		comboItems.addActionListener(this);
		btnAjouter.addActionListener(this);
		btnSupprimer.addActionListener(this);
		btnModifier.addActionListener(this);
		btnLecture.addActionListener(this);
		btnEmprunter.addActionListener(this);
		
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
		
		String selectedItem = (String) this.comboItems.getSelectedItem();
		int selectedIndex = this.pnlOnglet.getSelectedIndex();
		int selectedRow = pnlData.getTable().getSelectedRow();
		
		/* ===== Bouton EMPRUNTER ===== */
		if(e.getSource() == this.btnEmprunter) {
			if(selectedRow == -1) { // Si aucune ligne n'est sélectionnée
				JOptionPane.showConfirmDialog(this, "Veuillez sélectionner une ligne avant d'effectuer un emprunt", "Aucune ligne sélectionnée", JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE);
			}
			else {
				String isbn = (String) pnlData.getTable().getValueAt(selectedRow, 0);
				
				EmpruntDialog empdial = new EmpruntDialog(this, isbn);
				empdial.setVisible(true);
				
			}
		}
		/* ===== Bouton AJOUTER ===== */
		else if(e.getSource() == this.btnAjouter) {
			AjoutDialog ajd = null;
			
			// Média
			if(selectedIndex == 0) {
				
				if(selectedItem.equals("Livre"))
					ajd = new AjoutDialog("Ajouter un livre", Livre.class, true);					
				else if(selectedItem.equals("Magazine"))
					ajd = new AjoutDialog("Ajouter un magazine", Magazine.class, true);
				else if(selectedItem.equals("CD"))
					ajd = new AjoutDialog("Ajouter un CD", Cd.class, true);
				else if(selectedItem.equals("DVD"))
					ajd = new AjoutDialog("Ajouter un DVD", Dvd.class, true);
				else if(selectedItem.equals("Coffret DVD"))
					ajd = new AjoutDialog("Ajouter un coffret DVD", CoffretDvd.class, true);
				else if(selectedItem.equals("AudioLivre"))
					ajd = new AjoutDialog("Ajouter un audiolivre", AudioLivre.class, true);
			}
			// Membre
			else if(selectedIndex == 1) {
				if(selectedItem == "Abonné")
					ajd = new AjoutDialog("Ajouter un abonné", Abonne.class, true);
				else if(selectedItem == "Personnel")
					ajd = new AjoutDialog("Ajouter un membre du personnel", Personnel.class, true);
			}
			
			if(selectedIndex == 0 || selectedIndex == 1) {
				ajd.setVisible(true); // On affiche le formulaire
				
				if(ajd.getReturnStatus() == BiblioDialog.RET_OK) // Si l'objet à été ajouté
					refreshComponents((String) selectedItem); // On met à jour la JTable
			}			
		}
		
		/* ===== Bouton MODIFIER ===== */
		if(e.getSource() == btnModifier) {
			if(selectedRow == -1) { // Si aucune ligne n'est sélectionnée
				JOptionPane.showConfirmDialog(this, "Veuillez sélectionner une ligne avant de la modifier", "Aucune ligne sélectionnée", JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE);
			}
			
			EditDialog ajd = null;
			
			// Média
			if(selectedIndex == 0) {
				String isbn = (String) pnlData.getTable().getValueAt(selectedRow, 0);
				Media m = Bibliotheque.getMediaByIsbn(isbn);

				if(selectedItem.equals("Livre"))
					ajd = new EditDialog("Modifier un livre", m, true);					
				else if(selectedItem.equals("Magazine"))
					ajd = new EditDialog("Modifier un magazine", m, true);
				else if(selectedItem.equals("CD"))
					ajd = new EditDialog("Modifier un CD", m, true);
				else if(selectedItem.equals("DVD"))
					ajd = new EditDialog("Modifier un DVD", m, true);
				else if(selectedItem.equals("Coffret DVD"))
					ajd = new EditDialog("Modifier un coffret DVD", m, true);
				else if(selectedItem.equals("AudioLivre"))
					ajd = new EditDialog("Modifier un audiolivre", m, true);

			}
			// Membre
			else if(selectedIndex == 1) {
				int id = Integer.parseInt((String) pnlData.getTable().getValueAt(selectedRow, 0));
				Membre m = Bibliotheque.getMembreById(id);
				
				if(selectedItem == "Abonné")
					ajd = new EditDialog("Ajouter un abonné", m, true);
				else if(selectedItem == "Personnel")
					ajd = new EditDialog("Ajouter un membre du personnel", m, true);
			}
			
			if(selectedIndex == 0 || selectedIndex == 1) {
				ajd.setVisible(true); // On affiche le formulaire
				
				if(ajd.getReturnStatus() == BiblioDialog.RET_OK) // Si l'objet à été ajouté
					refreshComponents((String) selectedItem); // On met à jour la JTable
			}
		}
		
		/* ===== Bouton SUPPRIMER ===== */
		if(e.getSource() == btnSupprimer) {

			if(selectedRow == -1) { // Si aucune ligne n'est sélectionnée
				JOptionPane.showConfirmDialog(this, "Veuillez sélectionner une ligne avant de supprimer", "Aucune ligne sélectionnée", JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE);
			}
			// Média
			else if(selectedIndex == 0) {
				
				String isbn = (String) pnlData.getTable().getValueAt(selectedRow, 0);
				String titre = (String) pnlData.getTable().getValueAt(selectedRow, 1);
					
				int rep = JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment supprimer le média "+isbn+" ("+titre+") ?", "Suppresion d'un média", JOptionPane.YES_NO_OPTION);

				if(rep == JOptionPane.YES_OPTION) {
					Bibliotheque.delMedia(isbn);
					refreshComponents(selectedItem);
				}
			}
			// Membre
			else if(selectedIndex == 1) {				
				
				int id = Integer.parseInt((String) pnlData.getTable().getValueAt(selectedRow, 0));
				String nom = (String) pnlData.getTable().getValueAt(selectedRow, 1);
				String prenom = (String) pnlData.getTable().getValueAt(selectedRow, 2);
				
				int rep = JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment supprimer le membre "+nom+" "+prenom+" ("+id+") ?", "Suppresion d'un membre", JOptionPane.YES_NO_OPTION);

				if(rep == JOptionPane.YES_OPTION) {
					Bibliotheque.delMembre(id);
					refreshComponents(selectedItem);
				}
			}
			// Emprunt
			else if(selectedIndex == 2) { // Dans ce cas là, on ne fait pas une suppression mais on termine l'emprunt
				String isbn = (String) pnlData.getTable().getValueAt(selectedRow, 0);
				int id = Integer.parseInt((String) pnlData.getTable().getValueAt(selectedRow, 2));
				
				int rep = JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment terminer l'emprunt en cours ?", "Terminer un emprunt", JOptionPane.YES_NO_OPTION);
				
				if(rep == JOptionPane.YES_OPTION) {
					Bibliotheque.terminerEmprunt(isbn, id);
					refreshComponents(selectedItem);
				}
			}
		}
		
		/* ===== Bouton LECTURE ===== */
		if(e.getSource() == btnLecture) {

			if(comboItems.isVisible()) {
				if(!selectedItem.equals("Livre") && !selectedItem.equals("Cd")) {
					JOptionPane.showConfirmDialog(this, "Vous ne pouvez pas lancer de lecture sur ce type de média", "Lecture impossible", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
				}
			}
			if(selectedRow == -1) {
				JOptionPane.showConfirmDialog(this, "Veuillez sélectionner une ligne avant de lancer la lecture", "Aucune ligne sélectionnée", JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE);
			}
			else {
				String isbn = (String) pnlData.getTable().getValueAt(selectedRow, 0);
				if(Bibliotheque.isEnCoursDemprunt(isbn)) {
					JOptionPane.showConfirmDialog(this, "Le média que vous avez sélectionné est déjà en cours d'emprunt", "Lecture impossible", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
				}
				else if(Bibliotheque.getMediaByIsbn(isbn) instanceof Livre) {
					Livre l = (Livre) Bibliotheque.getMediaByIsbn(isbn);
					
					if(l.enLecture())
						l.stopperLecture();
					else
						l.demarrerLecture();
					
					Bibliotheque.updateMedia(l);
				}
				else if(Bibliotheque.getMediaByIsbn(isbn) instanceof Livre) {
					Cd cd = (Cd) Bibliotheque.getMediaByIsbn(isbn);
					
					if(cd.enLecture()) 
						cd.stopperLecture();
					else
						cd.demarrerLecture();
					
					Bibliotheque.updateMedia(cd);
				}
				
				if(!comboItems.isVisible())
					refreshComponents("En lecture");
			}
		}
		
		/* ===== Changement de sélection d'un item ===== */
		if(e.getSource() == comboItems) {
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
			setVisibleComponents(pnlOnglet.getSelectedIndex());
			
		
		}
	}
	
	/**
	 * 0 => Medias
	 * 1 => Membres
	 * 2 => Emprunts
	 * 3 => Lectures en cours 
	 * @param selectedOnglet
	 */
	private void setVisibleComponents(int selectedOnglet) {
		switch(selectedOnglet) {
		case 0: btnAjouter.setVisible(true);
				btnModifier.setVisible(true);
				btnSupprimer.setVisible(true);
				btnLecture.setVisible(true);
				comboItems.setVisible(true);
				btnEmprunter.setVisible(true);
			break;
		case 1: btnAjouter.setVisible(true);
				btnModifier.setVisible(true);
				btnSupprimer.setVisible(true);
				btnLecture.setVisible(false);
				comboItems.setVisible(true);
				btnEmprunter.setVisible(false);
			break;
		case 2: btnAjouter.setVisible(false);
				btnModifier.setVisible(false);
				btnSupprimer.setVisible(true);
				btnLecture.setVisible(false);
				comboItems.setVisible(true);
				btnEmprunter.setVisible(false);
			break;
		case 3: btnAjouter.setVisible(false);
				btnModifier.setVisible(false);
				btnSupprimer.setVisible(false);
				btnLecture.setVisible(true);
				comboItems.setVisible(false);
				btnEmprunter.setVisible(false);
			break;
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
		// Lecture
		else if(item.equals("En lecture")) {
			pnlData.setLectureEnCoursList(Bibliotheque.getListMediasEnLecture());
		}
	}
	
	private void setItemsComboBox(String firstItem) {
		comboItems.removeAllItems();
		
		if(firstItem.equals("Livre")) {
			comboItems.addItem("Livre");
			comboItems.addItem("Magazine");
			comboItems.addItem("CD");
			comboItems.addItem("DVD");
			comboItems.addItem("Coffret DVD");
			comboItems.addItem("AudioLivre");
		}
		else if(firstItem.equals("Abonné")) {
			comboItems.addItem("Abonné");
			comboItems.addItem("Personnel");
		}
		else if(firstItem.equals("En cours")) {
			comboItems.addItem("En cours");
			comboItems.addItem("Terminés");
		}
	}
}
