import java.util.Date;
import java.util.List;


public class CoffretDvd extends AudioVisuel {

	private List<Dvd> dvds;
		
	public CoffretDvd(String unIsbn, String unAuteur, String unTitre, Date uneDateParrution, int uneDuree, List listeDvds) {
		super(unIsbn, unAuteur, unTitre, uneDateParrution, uneDuree);
		this.dvds = listeDvds;
	}

	public int getNbDvds(){
		return this.dvds.size();
	}
	
	public void addDvd(Dvd monDvd){
		this.dvds.add(monDvd);
	}
	
	@Override
	public float getPrix() {
		return 0;
	}

	/* ****************Debut Get Set******************* */
	public List<Dvd> getDvds() {
		return dvds;
	}
	public void setDvds(List<Dvd> dvds) {
		this.dvds = dvds;
	}
	/* ****************Fin Get Set******************* */
}
