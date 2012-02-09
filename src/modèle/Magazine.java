package modèle;
import java.util.Date;


public class Magazine extends Litteraire {

	private String modeParution;
	
	public Magazine(String unIsbn, String unAuteur, String unTitre,
			Date uneDateParrution, int unNbPages, String unModeParution) {
		super(unIsbn, unAuteur, unTitre, uneDateParrution, unNbPages);
		
		this.setModeParution(unModeParution);
	}

	@Override
	public float getPrix() {
		return (float) (getNbPages() * 0.05);
	}

	public String getModeParution() {
		return modeParution;
	}
	public void setModeParution(String modeParution) {
		this.modeParution = modeParution;
	}
	
	public static String[] getLabelValues() {
		String[] lbls = {"ISBN", "Auteur", "Titre", "Date de parution", "Nombre de pages", "Mode de parution"};
		return lbls;
	}
}
