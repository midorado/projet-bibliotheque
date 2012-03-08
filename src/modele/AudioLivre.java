package modele;
import java.util.Date;
import java.util.List;


public class AudioLivre extends Media {

	private int nbPages;
	private List<Piste> pistes;
	
	public AudioLivre(String unIsbn, String unAuteur, String unTitre,
			Date uneDateParution, int nbPages, List<Piste> pistes) {
		super(unIsbn, unAuteur, unTitre, uneDateParution);

		setNbPages(nbPages);
		setPistes(pistes);
	}

	@Override
	public float getPrix() {
		return 5;
	}

	public int getNbPages() {
		return nbPages;
	}

	public void setNbPages(int nbPages) {
		this.nbPages = nbPages;
	}

	public List<Piste> getPistes() {
		return pistes;
	}

	public void setPistes(List<Piste> pistes) {
		this.pistes = pistes;
	}
	
	public int getDuree() {
		int duree = 0;
		
		for(Piste p : this.pistes) {
			duree += p.getDuree();
		}
		
		return duree;
	}
}
