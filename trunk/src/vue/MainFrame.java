package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;

import modèle.*;

import controleur.Bibliotheque;

public class MainFrame extends JFrame implements ActionListener {
  
	JTabbedPane pnlOnglet;
	JComboBox<String> listeMedia;
	JComboBox<String> listeMembre;
	JComboBox<String> listeEmprunt;
	ListePanel pnlMedia;
	ListePanel pnlMembre;
	ListePanel pnlEmprunt;
	JPanel pnlActionButtons;
	
	JButton btnAjouter;
	JButton btnSupprimer;
	JButton btnModifier;
	
	public MainFrame(){
		super("Bibliothèque 4000 !!");
		
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400, 200);
		
		pnlOnglet = new JTabbedPane();
				
		/* ajout des Onglets Media */
				
		pnlMedia = new ListePanel(Bibliotheque.getListLivre());
		
		/* liste des types de media */
		listeMedia = new JComboBox<String>();
		listeMedia.addItem("Livre");
		listeMedia.addItem("Magazine");
		listeMedia.addItem("CD");
		listeMedia.addItem("DVD");
		listeMedia.addItem("Coffret DVD");
		listeMedia.addItem("AudioLivre");
		
		pnlMedia.add(listeMedia, BorderLayout.NORTH);
		pnlOnglet.addTab("Medias", pnlMedia);
				
		/* ajout onglet Membre */
		
		pnlMembre = new ListePanel(Bibliotheque.getListMembres());
		pnlOnglet.addTab("Membres", pnlMembre);
		
		/* liste des types de membre */
		listeMembre = new JComboBox<String>();
		listeMembre.addItem("Abonné");
		listeMembre.addItem("Personnel");
		
		pnlMembre.add(listeMembre, BorderLayout.NORTH);
		
		/* ajout onglet Emprunt */
		
		pnlEmprunt = new ListePanel(Bibliotheque.getListEmpruntsEnCours());
		pnlOnglet.addTab("Emprunts", pnlEmprunt);
		
		/* liste des types d'emprunts */
		listeEmprunt = new JComboBox<String>();
		listeEmprunt.addItem("En cours");
		listeEmprunt.addItem("Terminés");
		
		pnlEmprunt.add(listeEmprunt, BorderLayout.NORTH);
		
		/* 	Fin Onglets */ 
		
		pnlActionButtons = new JPanel();
		btnAjouter = new JButton("Ajouter");
		btnSupprimer = new JButton("Supprimer");
		btnModifier = new JButton("Modifier");
		
		btnAjouter.addActionListener(this);
		btnSupprimer.addActionListener(this);
		btnModifier.addActionListener(this);

		pnlActionButtons.add(btnAjouter);
		pnlActionButtons.add(btnModifier);
		pnlActionButtons.add(btnSupprimer);
		
		listeMedia.addActionListener(this);
		listeMembre.addActionListener(this);
		listeEmprunt.addActionListener(this);

		this.add(pnlOnglet);
		this.add(pnlActionButtons,BorderLayout.SOUTH);
		this.setVisible(true);
		this.pack();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		/* ===== Bouton AJOUTER ===== */
		if(e.getSource() == this.btnAjouter) { 
			AjoutDialog ajd = null;
			
			if(this.pnlMedia.isShowing()) {
				
				if(this.listeMedia.getSelectedItem() == "Livre")
					ajd = new AjoutDialog("Ajouter un livre", Livre.class, null);					
				else if(this.listeMedia.getSelectedItem() == "Magazine")
					ajd = new AjoutDialog("Ajouter un magazine", Magazine.class, null);
				else if(this.listeMedia.getSelectedItem() == "CD")
					ajd = new AjoutDialog("Ajouter un CD", Cd.class, null);
				else if(this.listeMedia.getSelectedItem() == "DVD")
					ajd = new AjoutDialog("Ajouter un DVD", Dvd.class, null);
				else if(this.listeMedia.getSelectedItem() == "Coffret DVD")
					ajd = new AjoutDialog("Ajouter un coffret DVD", CoffretDvd.class, null);
				else if(this.listeMedia.getSelectedItem() == "AudioLivre")
					ajd = new AjoutDialog("Ajouter un audiolivre", AudioLivre.class, null);
					 
				ajd.setVisible(true);
				
				if(ajd.getReturnStatus() == BiblioDialog.RET_OK)
					refreshTable((String) listeMedia.getSelectedItem());
				
			}
			else if(this.pnlMembre.isShowing()) {
				if(this.listeMembre.getSelectedItem() == "Abonné")
					ajd = new AjoutDialog("Ajouter un abonné", Abonne.class, null);
				else if(this.listeMembre.getSelectedItem() == "Personnel")
					ajd = new AjoutDialog("Ajouter un membre du personnel", Personnel.class, null);
				
				ajd.setVisible(true);
				
				if(ajd.getReturnStatus() == BiblioDialog.RET_OK)
					refreshTable((String) listeMembre.getSelectedItem());
			}
			else if(this.pnlEmprunt.isShowing()) {
				
			}
			
		}
		
		/* ===== Bouton SUPPRIMER ===== */
		if(e.getSource() == btnSupprimer) {
			if(this.pnlMedia.isShowing()) {
				int row = pnlMedia.getTable().getSelectedRow();
				
				if(row != -1) { // Test si une ligne est sélectionnée
					String isbn = (String) pnlMedia.getTable().getValueAt(row, 0);
					String titre = (String) pnlMedia.getTable().getValueAt(row, 1);
					
					int rep = JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment supprimer le média "+isbn+" ("+titre+") ?", "Suppresion d'un média", JOptionPane.YES_NO_OPTION);

					if(rep == JOptionPane.YES_OPTION) {
						Bibliotheque.delMedia(isbn);
						refreshTable(listeMedia.getSelectedItem());
					}
				}
			}
			else if(this.pnlMembre.isShowing()) {
				int row = pnlMembre.getTable().getSelectedRow();
				
				if(row != -1) { // Test si une ligne est sélectionnée
					int id = Integer.parseInt((String) pnlMedia.getTable().getValueAt(row, 0));
					String nom = (String) pnlMedia.getTable().getValueAt(row, 1);
					String prenom = (String) pnlMedia.getTable().getValueAt(row, 2);
					
					int rep = JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment supprimer "+nom+" "+prenom+" ("+id+") ?", "Suppresion d'un membre", JOptionPane.YES_NO_OPTION);

					if(rep == JOptionPane.YES_OPTION) {
						Bibliotheque.delMembre(id);
						refreshTable(listeMembre.getSelectedItem());
					}
				}
			}
			else if(this.pnlEmprunt.isShowing()) { // Dans ce cas là, on ne fait pas une suppression mais on termine l'emprunt
				
			}
		}
		
		/* ===== Changement de sélection d'un item ===== */
		if(e.getSource() == listeMedia) { 
			refreshTable(listeMedia.getSelectedItem());
		}
		
		if(e.getSource() == listeMembre) {
			refreshTable(listeMembre.getSelectedItem());
		}
		
		if(e.getSource() == listeEmprunt) {
			refreshTable(listeEmprunt.getSelectedItem());
		}
	}
	
	public void refreshTable(Object item) {
		// Medias
		if(item.equals("Livre"))
			pnlMedia.setList(Bibliotheque.getListLivre());
		else if(item.equals("Magazine"))
			pnlMedia.setList(Bibliotheque.getListMagazine());
		else if(item.equals("CD"))
			pnlMedia.setList(Bibliotheque.getListCd());
		else if(item.equals("DVD"))
			pnlMedia.setList(Bibliotheque.getListDvd());
		else if(item.equals("Coffret DVD"))
			pnlMedia.setList(Bibliotheque.getListCoffretDvd());
		else if(item.equals("AudioLivre"))
			pnlMedia.setList(Bibliotheque.getListAudioLivre());
		// Membres
		else if(item.equals("Abonné"))
			pnlMembre.setList(Bibliotheque.getListAbonnes());
		else if(item.equals("Personnel"))
			pnlMembre.setList(Bibliotheque.getListPersonnels());
		// Emprunts
		else if(item.equals("En cours"))
			pnlEmprunt.setList(Bibliotheque.getListEmpruntsEnCours());
		else if(item.equals("Terminés"))
			pnlEmprunt.setList(Bibliotheque.getListEmpruntsTermines());
	}
	
}
