import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Classe contrôleur
 *
 */
public class Bibliotheque {

	private static Database db;
	
	private static List<Membre> listMembres;
	private static List<Media> listMedias;
//	private static List<Emprunt> listEmpruntsTermine;
//	private static List<Emprunt> listEmpruntsEnCours;
	
	public static void initBibliotheque() {
		
		db = new Database();
		db.openDatabase();
		
	//	listMembres = new ArrayList<Membre>();
	//	listMedias = new ArrayList<Media>();
	//	listEmpruntsTermine = new ArrayList<Emprunt>();
	//	listEmpruntsEnCours = new ArrayList<Emprunt>();
		
		listMembres = (List<Membre>) db.getList(Membre.class);
		listMedias = (List<Media>) db.getList(Media.class);
	//	listEmpruntsTermine = db.getEmprunts(false);
	//	listEmpruntsEnCours = db.getEmprunts(true);
	}
	
	public static void updateBibliotheque() {
		db.updateObject(listMembres);
		db.updateObject(listMedias);
		//db.updateObject(listEmpruntsTermine);
		//db.updateObject(listEmpruntsEnCours);
	}
	
	public static void closeBibliotheque() {
		System.out.println("Fermeture du programme en cours...");
		updateBibliotheque();
		db.closeDatabase();
		System.exit(0);
	}
	
	public static void addMembre(Membre m) {
		listMembres.add(m);
	}
	
	public static Membre getMembreById(int id) {
		for(Membre m : listMembres) {
			if(m.getIdentifiant() == id) {
				return m;
			}
		}
		return null;
	}
	
	public static void addMedia(Media m) {
		listMedias.add(m);
	}
	
	public static boolean delMedia(String isbn) {
		
		for(Media m : listMedias) {
			if(m.getIsbn().equals(isbn)) {
				listMedias.remove(m);
				return true;
			}
		}
		
		return false;
	}
	
	public static Media getMediaByIsbn(String isbn) {
		for(Media m : listMedias) {
			if(m.getIsbn().equals(isbn)) {
				return m;
			}
		}
		return null;
	}
	
/*	public static void addEmprunt(Emprunt e) {
		listEmpruntsEnCours.add(e);
	}
*/	
	public static void nouvelEmprunt(String isbn, int memberId) {
		Membre m = getMembreById(memberId);
		Emprunt e = new Emprunt(getMediaByIsbn(isbn), m);
		m.addEmprunt(e);
	}
	
/*	public static void terminerEmprunt(Emprunt e) {
		listEmpruntsEnCours.remove(e);
		e.setEnCours(false);
		listEmpruntsTermine.add(e);
	}
*/	
	public static Emprunt getEmprunt(String isbn, int id) {
		
		Emprunt e = db.getEmprunt(isbn, id);

		return e;		
	}
	
	public static int getNouvelIdMembre() {
		return listMembres.size() + 1;
	}
	
	public static List<Membre> getListMembres() {
		return listMembres;
	}
	
	public static List<Membre> getListPersonnels() {
		return null;
	}
	
	public static List<Membre> getListAbonnes() {
		return null;
	}
	
	public static List<Media> getListMedias() {
		return listMedias;
	}
}
