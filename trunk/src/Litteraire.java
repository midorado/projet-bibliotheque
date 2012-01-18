import java.util.Date;


public abstract class Litteraire extends Media {

	private int nbPages;
	private float prixPage;
	
	public Litteraire(String unIsbn, String unAuteur, String unTitre, float unPrix, Date uneDateParrution, int unNbPages, float unPrixPage){
		super(unIsbn, unAuteur, unTitre, uneDateParrution);
		this.setNbPages(unNbPages);
		this.setPrixPage(unPrixPage);
	}

	/* **************** Debut Get Set ******************* */
	
	public int getNbPages() {
		return nbPages;
	}
	public void setNbPages(int nbPages) {
		this.nbPages = nbPages;
	}
	
	public float getPrixPage() {
		return prixPage;
	}
	public void setPrixPage(float prixPage) {
		this.prixPage = prixPage;
	}
	
	/* ****************Fin Get Set******************* */
}
