package modèle;

import java.util.List;


import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.query.Predicate;


public class Database {

	private ObjectContainer db = null;
	private EmbeddedConfiguration config;
	private static final String PATH_DATABASE = "bibliotheque001.db4o";
	
	/**
	 * Configuration et ouverture de la base
	 */
	private void openDatabase() {
		config = (EmbeddedConfiguration) Db4oEmbedded.newConfiguration();
        config.common().objectClass(Media.class).cascadeOnUpdate(true);
        config.common().objectClass(Membre.class).cascadeOnUpdate(true);
        config.common().objectClass(Emprunt.class).cascadeOnUpdate(true);
        db = Db4oEmbedded.openFile(config, PATH_DATABASE); // Ouvre ou cré et ouvre la base
	}
	
	/**
	 * Fermeture de la base
	 */
	private void closeDatabase() {
		db.close();
		db = null;
	}
	
	public void storeObject(Object o) {
		this.openDatabase();
		db.store(o);
		this.closeDatabase();
	}
	
	public void updateObject(Object o) {
		this.openDatabase();
		db.store(o);
		this.closeDatabase();
	}
	
	public void removeObject(Object o) {
		this.openDatabase();
		db.delete(o);
		this.closeDatabase();
	}
	
	/**
	 * R�cup�rer une liste d'objets en fonction de la classe
	 * @param classQuery
	 * @return
	 */
	public List<?> getList(Class<?> classQuery) {
		this.openDatabase();
		List<?> ret = db.query(classQuery);
		this.closeDatabase();
		
		return ret;
	}
	
	/**
	 * Recup�rer les emprunts termin�s ou en cours
	 * @param enCours
	 * @return
	 */
	public List<Emprunt> getEmprunts(final boolean enCours) {
		this.openDatabase();
		
		List<Emprunt> result = db.query(new Predicate<Emprunt>() {
			@Override
			public boolean match(Emprunt e) {
				return enCours ? e.isEnCours() : !e.isEnCours();
			}
		});
		
		return result;
	}
	
	/**
	 * Retourne un Emprunt en fonction de l'isbn du Media et de l'identifiant du Membre
	 * @param isbn
	 * @param id
	 * @return
	 */
	public Emprunt getEmprunt(final String isbn, final int id) {
		this.openDatabase();
		
		ObjectSet<Emprunt> result = db.query(new Predicate<Emprunt>() {
			@Override
			public boolean match(Emprunt e) {
				return e.getMedia().getIsbn().equals(isbn) && e.getMembre().getIdentifiant() == id;
			}
		});
		
		Emprunt ret = null;
		
		if(!result.isEmpty())
			ret = (Emprunt) result.get(0);
		
		this.closeDatabase();
		
		return ret;
	}
	
	/**
	 * Retourne un Media en fonction de son isbn
	 * @param isbn
	 * @return
	 */
	public Media getMediaByIsbn(final String isbn) {
		this.openDatabase();
		
		ObjectSet<Media> result = db.query(new Predicate<Media>() {
			@Override
			public boolean match(Media m) {
				return m.getIsbn().equals(isbn);
			}
		});
		
		Media ret = null;
		
		if(!result.isEmpty())
			ret = result.get(0);
		
		this.closeDatabase();
		
		return ret;
	}
	
	/**
	 * Retourne un Membre en fonction de son identifiant
	 * @param id
	 * @return
	 */
	public Membre getMembreById(final int id) {
		this.openDatabase();
		
		ObjectSet<Membre> result = db.query(new Predicate<Membre>() {
			@Override
			public boolean match(Membre m) {
				return m.getIdentifiant() == id;
			}
		});
		
		Membre ret = null;
		
		if(!result.isEmpty())
			ret = result.get(0);
		
		this.closeDatabase();
		
		return ret;
	}
}
