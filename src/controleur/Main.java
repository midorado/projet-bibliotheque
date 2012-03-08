package controleur;

import vue.MainFrame;

/**
 * 
 * @author Julien Pozzani et Achille Guillon
 *
 */
public class Main {

	public static void main(String[] args) {

	    Bibliotheque.initBibliotheque(); // Initialise le contrôleur et la base de données

	    MainFrame mf = new MainFrame();
	    mf.setVisible(true);
	    
	}

}
