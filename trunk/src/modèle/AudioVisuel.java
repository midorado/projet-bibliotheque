package modèle;
import java.util.Date;


public abstract class AudioVisuel extends Media {

	private int duree;
	
	public AudioVisuel(String unIsbn, String unAuteur, String unTitre, Date uneDateParrution, int uneDuree) {
		super(unIsbn, unAuteur, unTitre, uneDateParrution);
		this.setDuree(uneDuree);
	}

	/* **************** Debut Get Set ******************* */
	public int getDuree() {
		return duree;
	}
	public void setDuree(int duree) {
		this.duree = duree;
	}
	
	/* ****************Fin Get Set******************* */
	
}
