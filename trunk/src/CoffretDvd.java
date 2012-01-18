import java.util.Date;
import java.util.List;


public class CoffretDvd extends AudioVisuel {

	List<Dvd> dvds;
		
	public CoffretDvd(String unIsbn, String unAuteur, String unTitre, Date uneDateParrution, int uneDuree, List listeDvds) {
		super(unIsbn, unAuteur, unTitre, uneDateParrution, uneDuree);
		this.dvds = listeDvds;
	}

	@Override
	public float getPrix() {
		// TODO Auto-generated method stub
		return 0;
	}

}
