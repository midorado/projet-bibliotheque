import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
		listEmpruntsEnCours.add(e);
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
	
	public static List<Membre> getListAbonnes() {
		return null;
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
}
