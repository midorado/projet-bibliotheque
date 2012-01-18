import java.util.Date;


public class Personnel extends Membre {

	public Personnel(int identifiant, String nom, String prenom, Date dateNaiss) {
		super(identifiant, nom, prenom, dateNaiss);
		// TODO Auto-generated constructor stub
	}

	@Override
	public float getTauxReduction() {
		return 0.5f;
	}

}
