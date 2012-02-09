package modèle;
import java.util.Date;


public class Dvd extends AudioVisuel {

	
	public Dvd(String unIsbn, String unAuteur, String unTitre, Date uneDateParrution, int uneDuree) {
		super(unIsbn, unAuteur, unTitre, uneDateParrution, uneDuree);
	}
	
	public float getPrix(){
		return 0;
	}
	
	public static String[] getLabelValues() {
		String[] lbls = {"ISBN", "Auteur", "Titre", "Date de parution", "Durée"};
		return lbls;
	}
}
