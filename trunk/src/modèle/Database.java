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
	private static final String PATH_DATABASE = "bibliotheque0017.db4o";
	
	/**
	 * Configuration et ouverture de la base
	 */
	public void openDatabase() {
		config = (EmbeddedConfiguration) Db4oEmbedded.newConfiguration();
		
		// La mise à jour en cascade permet de mettre à jour les objets créés dans des objets
        config.common().objectClass(Media.class).cascadeOnUpdate(true);
        config.common().objectClass(Membre.class).cascadeOnUpdate(true);
        config.common().objectClass(Emprunt.class).cascadeOnUpdate(true);
        config.common().objectClass(Cd.class).cascadeOnUpdate(true);
        config.common().objectClass(CoffretDvd.class).cascadeOnUpdate(true);
        config.common().objectClass(Cd.class).cascadeOnDelete(true);
        config.common().objectClass(CoffretDvd.class).cascadeOnDelete(true);
        
        db = Db4oEmbedded.openFile(config, PATH_DATABASE); // Ouvre ou créé et ouvre la base
	}
	
	/**
	 * Fermeture de la base
	 */
	public void closeDatabase() {
		db.close();
		db = null;
	}
	
	public void storeObject(Object o) {
		db.store(o);
	}
	
	public void updateObject(Object o) {
		db.store(o);
	}
	
	public void removeObject(Object o) {
		db.delete(o);
	}
	
	public List<Membre> rechercheMembre(final String rech) {
		List<Membre> result = db.query(new Predicate<Membre>() {
			@Override
			public boolean match(Membre m) {
				return m.getNom().equalsIgnoreCase(rech) || 
					   m.getPrenom().equalsIgnoreCase(rech) || 
					   m.getNom().startsWith(rech) || 
					   m.getPrenom().startsWith(rech);
			}
		});
		
		return result;
	}
	
	/**
	 * R�cup�rer une liste d'objets en fonction de la classe
	 * @param classQuery
	 * @return
	 */
	public List<?> getList(Class<?> classQuery) {
		List<?> ret = db.query(classQuery);
		
		return ret;
	}
	
	/**
	 * Recup�rer les emprunts termin�s ou en cours
	 * @param enCours
	 * @return
	 */
	public List<Emprunt> getEmprunts(final boolean enCours) {
		
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

		ObjectSet<Emprunt> result = db.query(new Predicate<Emprunt>() {
			@Override
			public boolean match(Emprunt e) {
				return e.getMedia().getIsbn().equals(isbn) && e.getMembre().getIdentifiant() == id;
			}
		});
		
		if(!result.isEmpty())
			return result.get(0);
		else
			return null;
	}
	
	/**
	 * Retourne un Media en fonction de son isbn
	 * @param isbn
	 * @return
	 */
	public Media getMediaByIsbn(final String isbn) {
		
		ObjectSet<Media> result = db.query(new Predicate<Media>() {
			@Override
			public boolean match(Media m) {
				return m.getIsbn().equals(isbn);
			}
		});

		if(!result.isEmpty())
			return result.get(0);
		else
			return null;
	}
	
	/**
	 * Retourne un Membre en fonction de son identifiant
	 * @param id
	 * @return
	 */
	public Membre getMembreById(final int id) {

		ObjectSet<Membre> result = db.query(new Predicate<Membre>() {
			@Override
			public boolean match(Membre m) {
				return m.getIdentifiant() == id;
			}
		});
			
		if(!result.isEmpty())
			return result.get(0);
		else
			return null;
	}
}
