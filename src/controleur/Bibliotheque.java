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
import modèle.Piste;

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
	
	public static void delMembre(int id) {
		db.removeObject(db.getMembreById(id));
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
	
	public static void EnregistreLecture(String isbn){
		Media m = db.getMediaByIsbn(isbn);
		
		if(m instanceof Livre){
			m = (Livre) m;
			if(!((Livre) m ).enLecture()){
				((Livre) m).demarrerLecture();
			}
		}
		else if(m instanceof Cd){
			m = (Cd) m;
			if(!((Cd) m ).enLecture()){
				((Cd) m).demarrerLecture();
			}
		}
		db.updateObject(m);
	}
	
	public static void stopperLecture(String isbn){
		Media m = db.getMediaByIsbn(isbn);
		
		if(m instanceof Livre){
			m = (Livre) m;
			if(((Livre) m).enLecture()){
				((Livre) m).stopperLecture();
			}
		}
		else if(m instanceof Cd){
			m = (Cd) m;
			if(((Cd) m).enLecture()){
				((Cd) m).stopperLecture();
			}
		}
		db.updateObject(m);
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
	
	public static void terminerEmprunt(String isbn, int id) {
		Emprunt emp = Bibliotheque.getEmpruntEnCours(isbn, id);
		
		emp.setDateRetour(new Date()); // On définit une date de retour --> isEnCours = false
		
		System.out.println(emp.getDateRetour());
		db.storeObject(emp); // On met à jour l'emprunt dans la base

	}

	public static boolean mediaExists(String isbn) {
		return (db.getMediaByIsbn(isbn) != null);
	}
	
	public static boolean membreExists(int id) {
		return (db.getMembreById(id) != null);
	}

	public static Emprunt getEmprunt(String isbn, int id) {	
		return db.getEmprunt(isbn, id);	
	}
	
	public static Emprunt getEmpruntEnCours(String isbn, int id) {
		
		for(Emprunt e : db.getEmprunts(true)) {
			if(e.getMedia().getIsbn().equals(isbn) && e.getMembre().getIdentifiant() == id)
				return e;
		}
		
		return null;
	}
	
	public static boolean isEmpruntable(String isbn) {
		for(Emprunt e : Bibliotheque.getListEmpruntsEnCours()) {
			if(e.getMedia().getIsbn().equals(isbn))
				return false;
		}
		return true;
	}
	
	public static int getNouvelIdMembre() {
		return db.getList(Membre.class).size() + 1;
	}
	
	public static List<Membre> getListMembres() {
		return (List<Membre>) db.getList(Membre.class);
	}
	
	public static List<Personnel> getListPersonnels() {
		return (List<Personnel>) db.getList(Personnel.class);
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

	public static List<Livre> getListMagazine(){
		return (List<Livre>) db.getList(Magazine.class);
	}

	public static List<Cd> getListCd() {
		return (List<Cd>) db.getList(Cd.class);
	}
	
	public static List<Dvd> getListDvd() {
		return (List<Dvd>) db.getList(Dvd.class);
	}
	
	public static List<CoffretDvd> getListCoffretDvd() {
		return (List<CoffretDvd>) db.getList(CoffretDvd.class);
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
				String[] lbls = {"Identifiant", "Nom", "Prénom", "Date de naissance","Poste", "Taux Reduction"};
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
				String[] lbls = {"ISBN", "Auteur", "Titre", "Date de parution", "Prix", "Nombre de pistes", "Durée"};
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
				String[] lbls = {"ISBN", "Auteur", "Titre", "Date de parution", "Nombre de pages","Prix"};
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
		else if(typeObj == Piste.class) {
			if(!editable) {
				String[] lbls = {"Numéro", "Titre", "Durée"};
				return lbls;
			}
			else {
				String[] lbls = {"Numéro", "Titre", "Durée"};
				return lbls;
			}
		}
		else if(typeObj == Emprunt.class) {
			if(!editable) {
				String[] lbls = {"ISBN", "Titre du média", "Identifiant emprunteur","Nom de l'emprunteur", "Date début emprunt", "Date retour limite", "Date retour"};
				return lbls;
			}
			else {
				String[] lbls = {"ISBN du média", "Identifiant emprunteur"};
				return lbls;
			}
		}
		else {
			throw new IllegalArgumentException("Classe non répertoriée");
		}
	}
}
