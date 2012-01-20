import java.util.Date;


public class Livre extends Litteraire {

	public Livre(String unIsbn, String unAuteur, String unTitre,
			Date uneDateParrution, int unNbPages) {
		super(unIsbn, unAuteur, unTitre, uneDateParrution, unNbPages);
		// TODO Auto-generated constructor stub
	}

	@Override
	public float getPrix() {
		return (float) (getNbPages() * 0.01);
	}

}
