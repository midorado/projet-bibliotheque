import java.util.Date;
import java.util.List;

import com.db4o.Db4o;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
     Dvd undvd = new Dvd("qefcqef", "defqefqes", "qefqefqe", new Date(), 3000);
     Dvd undvd2 = new Dvd("qefcqef", "defqefqes", "qefqefqe", new Date(), 3000);
     Dvd undvd3 = new Dvd("qefcqef", "defqefqes", "qefqefqe", new Date(), 3000);
     
     Database.openDatabase();
   /*  Database.storeObject(undvd);
     Database.storeObject(undvd2);
     Database.storeObject(undvd3);*/
     
     //List<Object> listDvds = Database.getListByExemple(Dvd.class);
     List<Dvd> lo = (List<Dvd>) Database.getList(Dvd.class);
     
     for(Dvd d : lo) {
    	 System.out.println("Auteur : "+d.getAuteur());
     }
     Database.closeDatabase();
	}

}
