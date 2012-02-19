package modèle;
import java.util.Date;
import java.util.List;


public class CoffretDvd extends AudioVisuel {

	private List<Dvd> dvds;
		
	public CoffretDvd(String unIsbn, String unAuteur, String unTitre, Date uneDateParrution, List<Dvd> listeDvds) {
		super(unIsbn, unAuteur, unTitre, uneDateParrution, 0);
	
		this.dvds = listeDvds;
	
		// Durée de tout les dvd du coffret
		int dureeTotale = 0;
		
		if(this.dvds != null)
			for(Dvd d : this.dvds)
				dureeTotale += d.getDuree();

		this.setDuree(dureeTotale);		
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
