import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class UIConsole {
	
	private Scanner sc;
	
	public UIConsole() {
		this.sc = new Scanner(System.in);
	}
	
	public void start() {
		this.menuPrincipal();
	}
	
	private void menuPrincipal() {
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
	
	private void menuEmprunts() {
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

	private void menuMedias() {
		clearConsole();

		int choix = -1;
		
		System.out.println("***********************************************");
		System.out.println("************* Gestion des medias **************");
		System.out.println("***********************************************");
		
		do {
			System.out.println("Que voulez-vous faire ?");
			System.out.println("1 - Ajouter un media");
			System.out.println("2 - Supprimer un media");
			System.out.println("3 - Lister les médias");
			System.out.println("4 - Retour au menu principal");
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
			case 3: menuListerMedias();
				break;
			case 4: menuPrincipal();
				break;
		}
	}

	private void menuMembres() {
		clearConsole();

		int choix = -1;
		
		System.out.println("***********************************************");
		System.out.println("************* Gestion des membres *************");
		System.out.println("***********************************************");
		
		do {
			System.out.println("Que voulez-vous faire ?");
			System.out.println("1 - Ajouter un membre");
			System.out.println("2 - Supprimer un membre");
			System.out.println("3 - Lister les membres");
			System.out.println("4 - Retour au menu principal");
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
			case 3: menuListerMembres();
				break;
			case 4: menuPrincipal();
				break;
		}
	}
	
	private void menuTerminerEmprunt() {
		clearConsole();

		String isbn;
		int idMembre;
		
		System.out.println("***********************************************");
		System.out.println("************* Terminer un emprunt *************");
		System.out.println("***********************************************");
		
		System.out.print("ISBN du media : ");
		isbn = sc.next();
		System.out.print("\nIdentifiant du membre : ");
		idMembre = sc.nextInt();

		//Bibliotheque.terminerEmprunt(Bibliotheque.getEmprunt(isbn, idMembre));
		
		System.out.println("L'emprunt est terminé");
		
		menuEmprunts();
	}

	private void menuNouvelEmprunt() {
		clearConsole();

		String isbn;
		int idMembre;
		
		System.out.println("***********************************************");
		System.out.println("*************** Nouvel emprunt ****************");
		System.out.println("***********************************************");
		
		do {
			System.out.print("ISBN du media : ");
			isbn = sc.next();
			
			if(!Bibliotheque.mediaExists(isbn))
				System.out.println("L'isbn saisit ne correspond à aucun média");
		}
		while(!Bibliotheque.mediaExists(isbn));
		
		do {
			System.out.print("Identifiant du membre : ");
			idMembre = sc.nextInt();
			
			if(!Bibliotheque.membreExists(idMembre)) {
				System.out.println("L'identifiant saisit ne correspond à aucun membre");
			}
		}
		while(!Bibliotheque.membreExists(idMembre));
		
		Bibliotheque.nouvelEmprunt(isbn, idMembre);
		
		System.out.println("Nouvel emprunt effectué");
		
		menuEmprunts();
	}
	
	private void menuListeEmpruntsEnCours() {
		clearConsole();
		
		System.out.println("***********************************************");
		System.out.println("******** Liste des emprunts en cours **********");
		System.out.println("***********************************************");
		
		for(Emprunt e : Bibliotheque.getListEmpruntsEnCours()) {
			System.out.println("Membre : "+e.getMembre().getIdentifiant()+" - "+e.getMembre().getNom()+" "+e.getMembre().getPrenom());
			System.out.println("Média : "+e.getMedia().getIsbn()+" - "+e.getMedia().getTitre());
			System.out.println("Date d'emprunt : "+dateToString(e.getDateEmprunt()));
			System.out.println("Date limite de retour : "+dateToString(e.getDateLimiteRetour()));
		}
		
		menuEmprunts();
	}
	
	private void menuAjoutMedia() {
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
	
	private void menuSupprimerMedia() {
		clearConsole();
		
		System.out.println("***********************************************");
		System.out.println("************** Supprimer un media *************");
		System.out.println("***********************************************");
		
		String isbn;
		
		do {
			System.out.println("ISBN du média à supprimer : ");
			isbn = sc.next();
			
			if(!Bibliotheque.mediaExists(isbn))
				System.out.println("L'isbn saisit ne correspond à aucun média");
		}
		while(!Bibliotheque.mediaExists(isbn));
		
		Bibliotheque.delMedia(isbn);
		
		System.out.println("Le media "+isbn+ " a bien été supprimé");
		
		menuMedias(); // On retourne à l'étape précédente
	}
	
	private void menuListerMedias() {
		clearConsole();
		
		System.out.println("***********************************************");
		System.out.println("************** Liste des medias ***************");
		System.out.println("***********************************************");
		
		List<Media> medias = Bibliotheque.getListMedias();
		
		for(Media m : medias) {
			System.out.println("ISBN : "+m.getIsbn()+" | Titre : "+m.getTitre()+" | Auteur : "+m.getAuteur());
			System.out.println("Date de parution : "+dateToString(m.getDateParution())+" | Prix : "+m.getPrix());
		}
		
		menuMedias();
	}
	
	private void ajoutMediaLitteraire() {
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
			isbn = sc.next();
			System.out.print("Auteur : ");
			auteur = sc.next();
			System.out.print("Titre : ");
			titre = sc.next();
			System.out.print("Date de parution (JJ/MM/AAAA) : ");
			try {
				dateParution = stringToDate(sc.next());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			System.out.print("Nombre de pages : ");
			nbPages = sc.nextInt();
		}
		
		switch(choix) {
			case 0: Bibliotheque.closeBibliotheque();
				break;
			case 1: String modeParution;
					System.out.print("Mode de parution (mensuel, hebdomadaire...)");
					modeParution = sc.next();
					media = (Magazine) new Magazine(isbn, auteur, titre, dateParution, nbPages, modeParution);
				break;
			case 2: media = (Livre) new Livre(isbn, auteur, titre, dateParution, nbPages);
				break;
			case 3: menuMedias();
				break;
		}

		if(media != null) {
			Bibliotheque.addMedia(media);
			System.out.println("Le media "+titre+" a bien été ajouté");
		}
		
		menuMedias(); // On retourne à l'étape précédente
	}
	
	private void ajoutMediaAudioVisuel() {
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
			isbn = sc.next();
			System.out.print("Auteur : ");
			auteur = sc.next();
			System.out.print("Titre : ");
			titre = sc.next();
			System.out.print("Date de parution (JJ/MM/AAAA) : ");
			try {
				dateParution = stringToDate(sc.next());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		switch(choix) {
			case 0: Bibliotheque.closeBibliotheque();
				break;
			case 1: List<Piste> pistes = menuPistes();
					media = (Cd) new Cd(isbn, auteur, titre, dateParution, pistes);
				break;
			case 2: System.out.print("Durée : ");
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
	
	private List<Piste> menuPistes() {
		clearConsole();
		
		System.out.println("***********************************************");
		System.out.println("******* Ajouter une piste audio au cd *********");
		System.out.println("***********************************************");
		
		String continuer;
		List<Piste> pistes = new ArrayList<Piste>();
		int numero, duree;
		String titre;
		
		do {
			System.out.print("Numéro : ");
			numero = sc.nextInt();
			System.out.print("Titre : ");
			titre = sc.next();
			System.out.print("Durée (en seconde) : ");
			duree = sc.nextInt();
			
			Piste p = new Piste(numero, titre, duree);
			pistes.add(p);
			
			System.out.print("Voulez-vous ajouter d'autres pistes ? [o/n] ");
			continuer = sc.next();
		}
		while(!continuer.equals("n"));
		
		return pistes;
	}
	
	private List<Dvd> menuDvds() {
		clearConsole();
		
		System.out.println("***********************************************");
		System.out.println("********* Ajouter un dvd au coffret ***********");
		System.out.println("***********************************************");
		
		String continuer;
		List<Dvd> dvds = new ArrayList<Dvd>();
		int duree;
		String isbn = null, auteur = null, titre = null;
		Date dateParution = null;
		
		do {
			System.out.print("ISBN : ");
			isbn = sc.next();
			System.out.print("Titre : ");
			titre = sc.next();
			System.out.print("Réalisateur : ");
			auteur = sc.next();
			System.out.print("Date de parution (JJ/MM/AAAA) : ");
			try {
				dateParution = stringToDate(sc.next());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			System.out.print("Durée (en seconde) : ");
			duree = sc.nextInt();
			
			Dvd d = new Dvd(isbn, auteur, titre, dateParution, duree);
			dvds.add(d);
			
			System.out.print("Voulez-vous ajouter d'autres dvd ? [o/n] ");
			continuer = sc.next();
		}
		while(!continuer.equals("n"));
		
		return dvds;
	}
	
	private void ajoutAudioLivre() {
		clearConsole();
		
		System.out.println("***********************************************");
		System.out.println("*********** Ajouter un audiolivre *************");
		System.out.println("***********************************************");

		String isbn, auteur, titre;
		Date dateParution = null;
		int nbPages;
		
		System.out.print("ISBN : ");
		isbn = sc.next();
		System.out.print("Titre : ");
		titre = sc.next();
		System.out.print("Auteur : ");
		auteur = sc.next();
		System.out.print("Date de parution (JJ/MM/AAAA) : ");
		try {
			dateParution = stringToDate(sc.next());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.print("Nombre de pages : ");
		nbPages = sc.nextInt();
		List<Piste> pistes = menuPistes(); // Ouverture du menu d'ajout de pistes audio
		
		AudioLivre audiolivre = new AudioLivre(isbn, auteur, titre, dateParution, nbPages, pistes);
		
		Bibliotheque.addMedia(audiolivre);
		
		System.out.println("Le media "+titre+" a bien été ajouté");
		
		menuMedias(); // Retour à l'étape précédente
	}
	
	private void menuAjoutMembre() {
		clearConsole();
		
		int choix = -1;
		
		System.out.println("***********************************************");
		System.out.println("************* Ajouter un membre ***************");
		System.out.println("***********************************************");
		
		do {
			System.out.println("Voulez-vous ajouter un membre du personnel ou un abonné ?");
			System.out.println("1 - Personnel");
			System.out.println("2 - Abonné");
			System.out.println("3 - Retour à la gestion des membres");
			System.out.println("0 - Quitter");
			choix = sc.nextInt();
		}
		while(choix < 0 || choix > 3);
		
		int id = Bibliotheque.getNouvelIdMembre();
		Membre membre = null;
		String nom = null, prenom = null;
		Date dateNaiss = null;
		
		if(choix > 0 && choix < 3) {
			System.out.print("Nom : ");
			nom = sc.next();
			System.out.print("Prénom : ");
			prenom = sc.next();
			System.out.print("Date de naissance : ");
			try {
				dateNaiss = stringToDate(sc.next());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		switch(choix) {
			case 0: Bibliotheque.closeBibliotheque();
				break;
			case 1: System.out.println("Poste : ");
					String poste = sc.next();
					membre = new Personnel(id, nom, prenom, dateNaiss, poste);
				break;
			case 2: membre = new Abonne(id, nom, prenom, dateNaiss);
				break;
			case 3: menuMedias();
				break;
		}
		
		Bibliotheque.addMembre(membre);
		
		System.out.println(prenom+" "+nom+" a bien été ajouté");
		
		menuMembres();
	}
	
	private void menuSupprimerMembre() {
		clearConsole();

		System.out.println("***********************************************");
		System.out.println("************ Supprimer un membre **************");
		System.out.println("***********************************************");
		
	}

	private void menuListerMembres() {
		clearConsole();

		System.out.println("***********************************************");
		System.out.println("************* Liste des membres ***************");
		System.out.println("***********************************************");
		
		for(Membre m : Bibliotheque.getListMembres()) {
			System.out.println("Membre n°"+m.getIdentifiant());
			System.out.println(m.getPrenom()+" "+m.getNom());
			System.out.println(dateToString(m.getDateNaissance())+"\n");
			
		}
	}
	
	private void clearConsole() {
	//	System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}
	
	private Date stringToDate(String date) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		return formatter.parse(date);
	}
	
	private String dateToString(Date d) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		return formatter.format(d);
	}
}
