import java.util.List;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.ext.DatabaseClosedException;
import com.db4o.ext.Db4oException;
import com.db4o.query.Predicate;
import com.db4o.query.Query;


public class Database {

	private static ObjectContainer db = null;
	private static EmbeddedConfiguration config;
	
	public static void openDatabase() {
		config = (EmbeddedConfiguration) Db4oEmbedded.newConfiguration();
        config.common().objectClass(Media.class).cascadeOnUpdate(true);
        db = Db4oEmbedded.openFile(config, "bibliotheque2.db4o");
	}
	
	public static void closeDatabase() {
		db.close();
		db = null;
	}
	
	public static void storeObject(Object o) {
		db.store(o);
		db.commit();
	}
	
	public static List<?> getList(Class<?> classQuery) {
		return db.query(classQuery);
	}
	
	public static Media getMediaByIsbn(final String isbn) {
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
}
