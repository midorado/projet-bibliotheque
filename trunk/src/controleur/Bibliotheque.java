package controleur;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import modèle.Abonne;
import modèle.AudioLivre;
import modèle.Cd;
import modèle.CoffretDvd;
import modèle.Database;
import modèle.Dvd;
import modèle.Emprunt;
import modèle.Livre;
import modèle.Magazine;
import modèle.Media;
import modèle.Membre;
import modèle.Personnel;

/**
 * 
 * Classe contrôleur
 *
 */
public class Bibliotheque {

	private static Database db;
	
	public static void initBibliotheque() {
		db = new Database();
		db.openDatabase();
	}

	public static void closeBibliotheque() {
		System.out.print("Fermeture du programme en cours...");
		db.closeDatabase();
		System.out.println(" Ok !");
		System.exit(0);
	}
	
	public static void addMembre(Membre m) {
		db.storeObject(m);
	}
	
	public static Membre getMembreById(int id) {
		List<Membre> listMembres = (List<Membre>) db.getList(Membre.class);
		
		for(Membre m : listMembres) {
			if(m.getIdentifiant() == id) {
				return m;
			}
		}
		return null;
	}
	
	public static void addMedia(Media m) {
		db.storeObject(m);
	}
	
	public static void delMedia(String isbn) {
		db.removeObject(db.getMediaByIsbn(isbn));
	}
	
	public static Media getMediaByIsbn(String isbn) {
		return db.getMediaByIsbn(isbn);
	}
	
/*	public static void addEmprunt(Emprunt e) {
		db.storeObject(e);
	}
*/
	public static boolean nouvelEmprunt(String isbn, int memberId) {
		Membre mb = getMembreById(memberId);
		Media med = getMediaByIsbn(isbn);
		
		if(mb == null || med == null) 
			return false;
		
		Emprunt e = new Emprunt(med,mb);
		mb.addEmprunt(e);
		
		db.updateObject(mb); // On met à jour le Membre avec son nouvel emprunt
		db.storeObject(e);
		
		return true;
	}
	
/*	public static void terminerEmprunt(Emprunt e) {
		listEmpruntsEnCours.remove(e);
		e.setEnCours(false);
		listEmpruntsTermine.add(e);
	}
*/
	public static boolean mediaExists(String isbn) {
		return (db.getMediaByIsbn(isbn) != null);
	}
	
	public static boolean membreExists(int id) {
		return (db.getMembreById(id) != null);
	}

	public static Emprunt getEmprunt(String isbn, int id) {	
		return db.getEmprunt(isbn, id);	
	}
	
	public static int getNouvelIdMembre() {
		return db.getList(Membre.class).size() + 1;
	}
	
	public static List<Membre> getListMembres() {
		return (List<Membre>) db.getList(Membre.class);
	}
	
	public static List<Membre> getListPersonnels() {
		return null;
	}
	
	public static List<Abonne> getListAbonnes() {
		return (List<Abonne>) db.getList(Abonne.class);
	}
	
	public static List<Media> getListMedias() {
		return (List<Media>) db.getList(Media.class);
	}
	
	public static List<Emprunt> getListEmpruntsEnCours() {
		return db.getEmprunts(true);
	}
	
	public static List<Emprunt> getListEmpruntsTermines() {
		return db.getEmprunts(false);
	}
	
	public static List<AudioLivre> getListAudioLivre(){
		return (List<AudioLivre>) db.getList(AudioLivre.class);
	}
	
	public static List<Livre> getListLivre(){
		return (List<Livre>) db.getList(Livre.class);
	}

	public static Date stringToDate(String date) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		return formatter.parse(date);
	}
	
	/**
	 * 
	 * @param typeObj
	 * @param editable
	 * @return
	 */
	public static String[] getLabelValues(Class<?> typeObj, boolean editable) {
		if(typeObj == Abonne.class) {
			if(!editable) { // Pour un simple affichage
				String[] lbls = {"Identifiant", "Nom", "Prénom", "Date de naissance", "Taux Reduction"};
				return lbls;
			}
			else { // Lorsqu'on créé ou on modifie l'objet : ex: on ne peut pas renseigner l'identifiant
				String[] lbls = {"Nom", "Prénom", "Date de naissance"};
				return lbls;
			}
		}
		else if(typeObj == Personnel.class) {
			if(!editable) {
				String[] lbls = {"Identifiant", "Nom", "Prénom", "Date de naissance","Poste"};
				return lbls;
			}
			else {
				String[] lbls = {"Nom", "Prénom", "Date de naissance", "Poste"};
				return lbls;
			}
		}
		else if(typeObj == AudioLivre.class) {
			if(!editable) {
				String[] lbls = {"ISBN", "Auteur", "Titre", "Date de parution", "Nombre de pages","Prix"};
				return lbls;
			}
			else {
				String[] lbls = {"ISBN", "Auteur", "Titre", "Date de parution", "Nombre de pages"};
				return lbls;
			}
		}
		else if(typeObj == Cd.class) {
			if(!editable) {
				String[] lbls = {"ISBN", "Auteur", "Titre", "Date de parution", "Prix"};
				return lbls;
			}
			else {
				String[] lbls = {"ISBN", "Auteur", "Titre", "Date de parution"};
				return lbls;
			}
		}
		else if(typeObj == CoffretDvd.class) {
			if(!editable) {
				String[] lbls = {"ISBN", "Auteur", "Titre", "Date de parution", "Durée", "Prix"};
				return lbls;
			}
			else {
				String[] lbls = {"ISBN", "Auteur", "Titre", "Date de parution", "Durée"};
				return lbls;
			}		
		}
		else if(typeObj == Dvd.class) {
			if(!editable) {
				String[] lbls = {"ISBN", "Auteur", "Titre", "Date de parution", "Durée", "Prix"};
				return lbls;
			}
			else {
				String[] lbls = {"ISBN", "Auteur", "Titre", "Date de parution", "Durée"};
				return lbls;
			}		
		}
		else if(typeObj == Livre.class) {
			if(!editable) {
				String[] lbls = {"ISBN", "Auteur", "Titre", "Date de parution", "Nombre de pages","Prix", "Empruntable"};
				return lbls;
			}
			else {
				String[] lbls = {"ISBN", "Auteur", "Titre", "Date de parution", "Nombre de pages"};
				return lbls;
			}		
		}
		else if(typeObj == Magazine.class) {
			if(!editable) {
				String[] lbls = {"ISBN", "Auteur", "Titre", "Date de parution", "Nombre de pages", "Mode de parution","Prix"};
				return lbls;
			}
			else {
				String[] lbls = {"ISBN", "Auteur", "Titre", "Date de parution", "Nombre de pages", "Mode de parution"};
				return lbls;
			}
		}
		else {
			throw new IllegalArgumentException();
		}
	}
}
