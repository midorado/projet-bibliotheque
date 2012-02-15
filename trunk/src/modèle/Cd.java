package modèle;
import java.util.Date;
import java.util.List;


public class Cd extends AudioVisuel {

	private List<Piste> pistes;
	
	
	public Cd(String unIsbn, String unAuteur, String unTitre,
			Date uneDateParrution, List<Piste> pistes) {
		super(unIsbn, unAuteur, unTitre, uneDateParrution, 0);
		
		int duree = 0;
		for(Piste p : this.pistes)
			duree += p.getDuree();

		super.setDuree(duree);
	}

	@Override
	public float getPrix() {
		float prix = 10;
		
		if(this.getNbPistes() > 10)
			prix += getNbPistes() * 1;
		
		return prix;
	}
	
	public int getNbPistes() {
		return pistes.size();
	}
}
