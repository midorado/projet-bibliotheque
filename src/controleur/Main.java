package controleur;

import java.util.List;

import vue.AjoutFrame;
import vue.MainFrame;
import modèle.Abonne;
import modèle.Dvd;
import modèle.Livre;
import modèle.Media;
import modèle.Piste;

/**
 * 
 * @author Julien Pozzani et Achille Guillon
 *
 */
public class Main {

	public static void main(String[] args) {

	    Bibliotheque.initBibliotheque(); // Initialise le contrôleur et la base de données

	 //   UIConsole ui = new UIConsole();
	 //   ui.start(); // Lancement de l'interface utilisateur
	   //  Bibliotheque.addMedia(new Livre("AZSAS15", "nAuteur", "unTitre", new Date(), 555));
	   //  Bibliotheque.addMedia(new Livre("AZSdAS15", "auteur2", "unTitrdsce", new Date(), 555));
	    List<Media> list = null;
	    list = Bibliotheque.getListMedias();
	    System.out.println("Nb obj : "+list.size());

	    for(Media m : list) {
	    	System.out.println("cqefeqfqe");
	    	System.out.println(m.getAuteur());
	    }
	 //MainFrame framePrincipale = new MainFrame(); 
	 //framePrincipale.setVisible(true);
	   
	    AjoutFrame af = new AjoutFrame("Ajouter un livre", Abonne.class);
	    af.setVisible(true);
	    
	//    Bibliotheque.closeBibliotheque(); // Execute les sauvegardes et ferme la base*/
		
	}

}
