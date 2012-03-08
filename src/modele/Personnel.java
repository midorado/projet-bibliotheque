package modele;
import java.util.Date;


public class Personnel extends Membre {

	private String poste;

	public Personnel(int identifiant, String nom, String prenom, Date dateNaiss, String poste) {
		super(identifiant, nom, prenom, dateNaiss);
		this.setPoste(poste);
	}

	@Override
	public float getTauxReduction() {
		return 0.5f;
	}

	public String getPoste() {
		return poste;
	}

	public void setPoste(String poste) {
		this.poste = poste;
	}
}
