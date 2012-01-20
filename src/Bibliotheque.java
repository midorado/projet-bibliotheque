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
	private static List<Emprunt> listEmpruntsTermine;
	private static List<Emprunt> listEmpruntsEnCours;
	
	public static void initBibliotheque() {
		
		db = new Database();
		db.openDatabase();
		
	//	listMembres = (List<Membre>) db.getList(Membre.class);
	//	listMedias = (List<Media>) db.getList(Media.class);
	//	listEmpruntsTermine = db.getEmprunts(false);
	//	listEmpruntsEnCours = db.getEmprunts(true);
	}
	
	public static void updateBibliotheque() {
		db.updateObject(listMembres);
		db.updateObject(listMedias);
		db.updateObject(listEmpruntsEnCours);
	}
	
	public static void closeBibliotheque() {
		updateBibliotheque();
		db.closeDatabase();
		System.exit(0);
	}
	
	public static void addMembre(Membre m) {
		listMembres.add(m);
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
	
	public static void addEmprunt(Emprunt e) {
		listEmpruntsEnCours.add(e);
	}
	
	public static void terminerEmprunt(Emprunt e) {
		e.setEnCours(false);
		listEmpruntsTermine.add(e);
		listEmpruntsEnCours.remove(e);
	}
	
	public static Emprunt getEmprunt(String isbn, int id) {
		
		db.openDatabase();
		Emprunt e = db.getEmprunt(isbn, id);
		db.closeDatabase();
		
		return e;		
	}
}
