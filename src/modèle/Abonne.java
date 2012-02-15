package modèle;
import java.util.Date;


public class Abonne extends Membre {

	public Abonne(int identifiant, String nom, String prenom, Date dateNaiss) {
		super(identifiant, nom, prenom, dateNaiss);
		// TODO Auto-generated constructor stub
	}

	@Override
	public float getTauxReduction() {
		float reduc = 0;
		
		if(super.getAge() < 25)
			reduc = 0.1f;
		
		return reduc;
	}
}
