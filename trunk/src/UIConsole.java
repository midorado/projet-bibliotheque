import java.util.Scanner;


public class UIConsole {
	
	private static Scanner sc = new Scanner(System.in);
	
	public static void menuPrincipal() {
		clearConsole();
		
		int choix = -1;
		
		System.out.println("***********************************************");
		System.out.println("********** Bienvenue sur Biblio 4000 **********");
		System.out.println("***********************************************");
		
		do {
			System.out.println("Que voulez-vous faire ?");
			System.out.println("1 - Gestion des emprunts");
			System.out.println("2 - Gestion des médias");
			System.out.println("3 - Gestion des membres");
			System.out.println("0 - Quitter");
			choix = sc.nextInt();
		}
		while(choix < 0 || choix > 3);
		
		switch(choix) {
			case 0: System.exit(0);
				break;
			case 1: menuEmprunts();
				break;
			case 2: menuMedias();
				break;
			case 3: menuMembres();
				break;
		}
	}
	
	private static void menuEmprunts() {
		clearConsole();
		
		int choix = -1;
		
		System.out.println("***********************************************");
		System.out.println("************ Gestion des emprunts *************");
		System.out.println("***********************************************");
		
		do {
			System.out.println("Que voulez-vous faire ?");
			System.out.println("1 - Effectuer un nouvel emprunt");
			System.out.println("2 - Terminer un emprunt");
			System.out.println("3 - Lister les emprunts en cours");
			System.out.println("4 - Retour au menu principal");
			System.out.println("0 - Quitter");
			choix = sc.nextInt();
		}
		while(choix < 0 || choix > 4);
		
		switch(choix) {
			case 0: System.exit(0);
				break;
			case 1: menuNouvelEmprunt();
				break;
			case 2: menuTerminerEmprunt();
				break;
			case 3: menuListeEmpruntsEnCours();
				break;
			case 4: menuPrincipal();
				break;
		}
	}

	private static void menuMedias() {
		clearConsole();

		int choix = -1;
		
		System.out.println("***********************************************");
		System.out.println("************* Gestion des medias *************");
		System.out.println("***********************************************");
		
		do {
			System.out.println("Que voulez-vous faire ?");
			System.out.println("1 - Ajouter un media");
			System.out.println("2 - Supprimer un media");
			System.out.println("3 - Retour au menu principal");
			System.out.println("0 - Quitter");
			choix = sc.nextInt();
		}
		while(choix < 0 || choix > 4);
		
		switch(choix) {
			case 0: System.exit(0);
				break;
			case 1: menuAjoutMedia();
				break;
			case 2: menuSupprimerMedia();
				break;
			case 3: menuPrincipal();
				break;
		}
	}

	private static void menuMembres() {
		clearConsole();

		int choix = -1;
		
		System.out.println("***********************************************");
		System.out.println("************* Gestion des membres *************");
		System.out.println("***********************************************");
		
		do {
			System.out.println("Que voulez-vous faire ?");
			System.out.println("1 - Ajouter un membre");
			System.out.println("2 - Supprimer un membre");
			System.out.println("3 - Retour au menu principal");
			System.out.println("0 - Quitter");
			choix = sc.nextInt();
		}
		while(choix < 0 || choix > 4);
		
		switch(choix) {
			case 0: System.exit(0);
				break;
			case 1: menuAjoutMembre();
				break;
			case 2: menuSupprimerMembre();
				break;
			case 3: menuPrincipal();
				break;
		}
	}
	
	private static void menuTerminerEmprunt() {
		clearConsole();

		String isbn;
		int idMembre;
		
		System.out.println("***********************************************");
		System.out.println("************* Terminer un emprunt *************");
		System.out.println("***********************************************");
		
		System.out.print("ISBN du media : ");
		isbn = sc.nextLine();
		System.out.print("\nIdentifiant du membre : ");
		idMembre = sc.nextInt();

		Bibliotheque.terminerEmprunt(Bibliotheque.getEmprunt(isbn, idMembre));
		System.out.println();
	}

	private static void menuNouvelEmprunt() {
		// TODO Auto-generated method stub
		
	}
	
	private static void menuListeEmpruntsEnCours() {
		// TODO Auto-generated method stub
		
	}
	
	private static void menuAjoutMedia() {
		clearConsole();
		
		int choix = -1;
		
		System.out.println("***********************************************");
		System.out.println("*************** Ajouter un media **************");
		System.out.println("***********************************************");
		
		do {
			System.out.println("Quel type de média voulez-vous ajouter dans la bibliothèque ?");
			System.out.println("1 - Littéraire (magasine, livre)");
			System.out.println("2 - Audiovisuel (cd, dvd, coffret dvd)");
			System.out.println("3 - Audiolivre");
			System.out.println("4 - Retour à la gestion des médias");
			System.out.println("0 - Quitter");
			choix = sc.nextInt();
		}
		while(choix < 0 || choix > 5);
		
		switch(choix) {
			case 0: System.exit(0);
				break;
			case 1: ajoutMediaLitteraire();
				break;
			case 2: ajoutMediaAudioVisuel();
				break;
			case 3: ajoutAudioLivre();
				break;
			case 4: menuMedias();
				break;
		}
	}
	
	private static void menuSupprimerMedia() {
		System.out.println("***********************************************");
		System.out.println("************** Supprimer un media *************");
		System.out.println("***********************************************");
		
		String isbn;
		
		System.out.println("ISBN du média à supprimer : ");
		isbn = sc.nextLine();
		
		Bibliotheque.delMedia(isbn);
		
		System.out.println("Le media "+isbn+ " à bien été supprimé");
		
		menuMedias(); // On retourne à l'étape précédente
	}
	
	private static void ajoutMediaLitteraire() {}
	private static void ajoutMediaAudioVisuel() {}
	private static void ajoutAudioLivre() {}
	
	private static void menuAjoutMembre() {
		// TODO Auto-generated method stub
		
	}
	
	private static void menuSupprimerMembre() {
		// TODO Auto-generated method stub
		
	}

	

	private static void clearConsole() {
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}
}
