import java.util.Date;


public class Dvd extends AudioVisuel {

	
	public Dvd(String unIsbn, String unAuteur, String unTitre, Date uneDateParrution, int uneDuree) {
		super(unIsbn, unAuteur, unTitre, uneDateParrution, uneDuree);
	}
	
	public float getPrix(){
		return 0;
	}
}
