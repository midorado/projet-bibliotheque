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
	//	pnlMedia.setList(Bibliotheque.getListLivre());
		
		/* liste des types de media */
		listeMedia = new JComboBox<String>();
	//	listeMedia.setPreferredSize(new Dimension(100,20));
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
		System.out.println("taille liste : "+Bibliotheque.getListAbonnes().size());
	//	pnlMembre.setList(Bibliotheque.getListAbonnes());
		pnlOnglet.addTab("Membres", pnlMembre);
		
		/* liste des types de membre */
		listeMembre = new JComboBox<String>();
		listeMembre.addItem("Abonné");
		listeMembre.addItem("Personnel");
		
		pnlMembre.add(listeMembre, BorderLayout.NORTH);
		
		/* ajout onglet Emprunt */
		
		pnlEmprunt = new ListePanel(Bibliotheque.getListEmpruntsEnCours());
	//	pnlEmprunt.setList(Bibliotheque.getListEmpruntsEnCours());
		pnlEmprunt.setBackground(Color.MAGENTA);
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

		if(e.getSource() == this.btnAjouter) {
			AjoutDialog ajd = null;
			
			if(this.pnlMedia.isShowing()) {
				
				if(this.listeMedia.getSelectedItem() == "Livre") {
					ajd = new AjoutDialog("Ajouter un livre", Livre.class, null);					
				}
				else if(this.listeMedia.getSelectedItem() == "Magazine") {
					ajd = new AjoutDialog("Ajouter un magazine", Magazine.class, null);
				}
				else if(this.listeMedia.getSelectedItem() == "CD") {
					ajd = new AjoutDialog("Ajouter un CD", Cd.class, null);
				}
				else if(this.listeMedia.getSelectedItem() == "DVD") {
					ajd = new AjoutDialog("Ajouter un DVD", Dvd.class, null);
				}
				else if(this.listeMedia.getSelectedItem() == "Coffret DVD") {
					ajd = new AjoutDialog("Ajouter un coffret DVD", CoffretDvd.class, null);
				}
				else if(this.listeMedia.getSelectedItem() == "AudioLivre") {
					ajd = new AjoutDialog("Ajouter un audiolivre", AudioLivre.class, null);
				}
					 
				ajd.setVisible(true);
				listeMedia.repaint();
				
			}
			else if(this.pnlMembre.isShowing()) {
				if(this.listeMembre.getSelectedItem() == "Abonné") {
					ajd = new AjoutDialog("Ajouter un abonné", Abonne.class, null);
				}
				else if(this.listeMembre.getSelectedItem() == "Personnel") {
					ajd = new AjoutDialog("Ajouter un membre du personnel", Personnel.class, null);
				}
				
				ajd.setVisible(true);
			}
			else if(this.pnlEmprunt.isShowing()) {
				
			}
			
			
		}
		
		if(e.getSource() == listeMedia) {

			if(this.listeMedia.getSelectedItem() == "Livre") {
				pnlMedia.setList(Bibliotheque.getListLivre());
			}
			else if(this.listeMedia.getSelectedItem() == "Magazine") {
				pnlMedia.setList(Bibliotheque.getListMagazine());
				pnlMedia.repaint();
				System.out.println("fuckkkk");
			}
			else if(this.listeMedia.getSelectedItem() == "CD") {
				
				pnlMedia.setList(Bibliotheque.getListCd());
			
				System.out.println("CD selectionne");
			}
			else if(this.listeMedia.getSelectedItem() == "DVD") {
			}
			else if(this.listeMedia.getSelectedItem() == "Coffret DVD") {
			}
			else if(this.listeMedia.getSelectedItem() == "AudioLivre") {
			}
		}
		
		if(e.getSource() == listeMembre) {
			
		}
		
		if(e.getSource() == listeEmprunt) {
			
		}
	}
	
}
