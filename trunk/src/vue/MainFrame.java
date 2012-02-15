package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;

import modèle.*;

import controleur.Bibliotheque;

public class MainFrame extends JFrame {
  
	JTabbedPane pnlOnglet;
	
	public MainFrame(){
		super("Bibliothèque 4000 !!");
		
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400, 200);
		
		pnlOnglet = new JTabbedPane();
				
		/* ajout des Onglets Media */
				
		ListePanel pnlMedia = new ListePanel();
		pnlMedia.setList(Bibliotheque.getListLivre());
		
		/* liste des media */
		JComboBox listeMedia = new JComboBox();
		listeMedia.setPreferredSize(new Dimension(100,20));
		listeMedia.addItem("Livre");
		listeMedia.addItem("Cd");
		listeMedia.addItem("CoffreDvd");
		listeMedia.addItem("AudioLivre");
		listeMedia.addItem("Magazine");
		pnlMedia.add(listeMedia, BorderLayout.NORTH);
		pnlOnglet.addTab("Medias", pnlMedia);
				
		/* ajout onglet Membre */
		
		ListePanel pnlMembre = new ListePanel();
		System.out.println("taille liste : "+Bibliotheque.getListAbonnes().size());
		pnlMembre.setList(Bibliotheque.getListAbonnes());
		pnlMembre.setBackground(Color.CYAN);
		pnlOnglet.addTab("Membres", pnlMembre);
		
		/* ajout onglet Emprunt */
		
		ListePanel pnlEmprunt = new ListePanel();
		pnlEmprunt.setList(Bibliotheque.getListEmpruntsEnCours());
		pnlEmprunt.setBackground(Color.MAGENTA);
		pnlOnglet.addTab("Emprunts", pnlEmprunt);
		/* 	Fin Onglets */ 
		
		this.add(pnlOnglet);
		this.setVisible(true);
		this.pack();

	}
	
}
