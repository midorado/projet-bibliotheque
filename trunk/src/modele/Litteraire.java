package modele;
import java.util.Date;


public abstract class Litteraire extends Media {

	private int nbPages;
	
	public Litteraire(String unIsbn, String unAuteur, String unTitre, Date uneDateParrution, int unNbPages){
		super(unIsbn, unAuteur, unTitre, uneDateParrution);
		this.setNbPages(unNbPages);
	}

	/* **************** Debut Get Set ******************* */
	
	public int getNbPages() {
		return nbPages;
	}
	public void setNbPages(int nbPages) {
		this.nbPages = nbPages;
	}
	
	/* ****************Fin Get Set******************* */
}
