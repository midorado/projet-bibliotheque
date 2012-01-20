import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
			case 0: Bibliotheque.closeBibliotheque();
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
			case 0: Bibliotheque.closeBibliotheque();
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
			case 0: Bibliotheque.closeBibliotheque();
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
			case 0: Bibliotheque.closeBibliotheque();
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
			case 0: Bibliotheque.closeBibliotheque();
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
		
		System.out.println("Le media "+isbn+ " a bien été supprimé");
		
		menuMedias(); // On retourne à l'étape précédente
	}
	
	private static void ajoutMediaLitteraire() {
		clearConsole();
		
		System.out.println("***********************************************");
		System.out.println("********* Ajouter un media littéraire *********");
		System.out.println("***********************************************");
		
		int choix = -1;
		
		do {
			System.out.println("Quel type de média voulez-vous ajouter dans la bibliothèque ?");
			System.out.println("1 - Magasine");
			System.out.println("2 - Livre");
			System.out.println("3 - Retour à la gestion des médias");
			System.out.println("0 - Quitter");
			choix = sc.nextInt();
		}
		while(choix < 0 || choix > 3);
		
		Media media = null;
		String isbn = null, auteur = null, titre = null;
		Date dateParution = null;
		int nbPages = 0;
		
		if(choix > 0 && choix < 3) {
			System.out.print("ISBN : ");
			isbn = sc.nextLine();
			System.out.print("\nAuteur : ");
			auteur = sc.nextLine();
			System.out.print("\nTitre : ");
			titre = sc.nextLine();
			System.out.print("\nDate de parution (JJ/MM/AAAA) : ");
			try {
				dateParution = stringToDate(sc.nextLine());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.print("\nNombre de pages : ");
			nbPages = sc.nextInt();
		}
		
		switch(choix) {
			case 0: Bibliotheque.closeBibliotheque();
				break;
			case 1: String modeParution;
					System.out.print("\nMode de parution (mensuel, hebdomadaire...)");
					modeParution = sc.nextLine();
					media = (Magazine) new Magazine(isbn, auteur, titre, dateParution, nbPages, modeParution);
				break;
			case 2: media = (Livre) new Livre(isbn, auteur, titre, dateParution, nbPages);
				break;
			case 3: menuMedias();
				break;
		}

		if(media != null) {
			Bibliotheque.addMedia(media);
			System.out.println("\nLe media "+titre+" a bien été ajouté");
		}
		
		menuMedias(); // On retourne à l'étape précédente
	}
	
	private static void ajoutMediaAudioVisuel() {
		clearConsole();
		
		System.out.println("***********************************************");
		System.out.println("******** Ajouter un media audiovisuel *********");
		System.out.println("***********************************************");
		
		int choix = -1;
		
		do {
			System.out.println("Quel type de média voulez-vous ajouter dans la bibliothèque ?");
			System.out.println("1 - Cd");
			System.out.println("2 - Dvd");
			System.out.println("3 - Coffret dvd");
			System.out.println("4 - Retour à la gestion des médias");
			System.out.println("0 - Quitter");
			choix = sc.nextInt();
		}
		while(choix < 0 || choix > 4);

		Media media = null;
		String isbn = null, auteur = null, titre = null;
		Date dateParution = null;

		if(choix > 0 && choix < 4) {
			System.out.print("ISBN : ");
			isbn = sc.nextLine();
			System.out.print("\nAuteur : ");
			auteur = sc.nextLine();
			System.out.print("\nTitre : ");
			titre = sc.nextLine();
			System.out.print("\nDate de parution (JJ/MM/AAAA) : ");
			try {
				dateParution = stringToDate(sc.nextLine());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		switch(choix) {
			case 0: Bibliotheque.closeBibliotheque();
				break;
			case 1: List<Piste> pistes = menuPistes();
					media = (Cd) new Cd(isbn, auteur, titre, dateParution, pistes);
				break;
			case 2: System.out.print("\nDurée : ");
					int duree = sc.nextInt();
					media = (Dvd) new Dvd(isbn, auteur, titre, dateParution, duree);
				break;
			case 3: List<Dvd> dvds = menuDvds();
					media = (CoffretDvd) new CoffretDvd(isbn, auteur, titre, dateParution, dvds);
				break;
			case 4: menuMedias();
				break;
		}

		if(media != null) {
			Bibliotheque.addMedia(media);
			System.out.println("\nLe media "+titre+" a bien été ajouté");
		}
		
		menuMedias(); // On retourne à l'étape précédente
	}
	
	private static List<Piste> menuPistes() {
		clearConsole();
		
		System.out.println("***********************************************");
		System.out.println("******* Ajouter une piste audio au cd *********");
		System.out.println("***********************************************");
		
		List<Piste> pistes = new ArrayList<Piste>();
		boolean continuer;
		int numero, duree;
		String titre;
		
		do {
			System.out.print("Numéro : ");
			numero = sc.nextInt();
			System.out.print("\nTitre : ");
			titre = sc.nextLine();
			System.out.print("\nDurée (en seconde) : ");
			duree = sc.nextInt();
			
			Piste p = new Piste(numero, titre, duree);
			pistes.add(p);
			
			System.out.print("\nVoulez-vous ajouter d'autres pistes ? ");
			continuer = sc.nextBoolean();
		}
		while(continuer);
		
		return pistes;
	}
	
	private static List<Dvd> menuDvds() {
		clearConsole();
		
		System.out.println("***********************************************");
		System.out.println("********* Ajouter un dvd au coffret ***********");
		System.out.println("***********************************************");
		
		List<Dvd> dvds = new ArrayList<Dvd>();
		boolean continuer;
		int duree;
		String isbn = null, auteur = null, titre = null;
		Date dateParution = null;
		
		do {
			System.out.print("ISBN : ");
			isbn = sc.nextLine();
			System.out.print("\nTitre : ");
			titre = sc.nextLine();
			System.out.print("\nRéalisateur : ");
			auteur = sc.nextLine();
			System.out.print("\nDate de parution (JJ/MM/AAAA) : ");
			try {
				dateParution = stringToDate(sc.nextLine());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			System.out.print("\nDurée (en seconde) : ");
			duree = sc.nextInt();
			
			Dvd d = new Dvd(isbn, auteur, titre, dateParution, duree);
			dvds.add(d);
			
			System.out.print("Voulez-vous ajouter d'autres dvd ? ");
			continuer = sc.nextBoolean();
		}
		while(continuer);
		
		return dvds;
	}
	
	private static void ajoutAudioLivre() {
		
	}
	
	private static void menuAjoutMembre() {
		// TODO Auto-generated method stub
		
	}
	
	private static void menuSupprimerMembre() {
		// TODO Auto-generated method stub
		
	}

	

	private static void clearConsole() {
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}
	
	private static Date stringToDate(String date) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		return formatter.parse(date);
	}
	
}
