package modèle;
import java.util.Date;


public class Livre extends Litteraire {

	private boolean enLecture;
	
	public Livre(String unIsbn, String unAuteur, String unTitre,
			Date uneDateParrution, int unNbPages) {
		super(unIsbn, unAuteur, unTitre, uneDateParrution, unNbPages);
		this.enLecture = false;
		// TODO Auto-generated constructor stub
	}

	@Override
	public float getPrix() {
		return (float) (getNbPages() * 0.01);
	}
	
	
	public boolean enLecture(){
		return this.enLecture;
	}
	
	public void demarrerLecture(){
		this.enLecture = true;
	}
	
	public void stopperLecture(){
		this.enLecture = false;
	}

}
