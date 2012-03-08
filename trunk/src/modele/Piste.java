package modele;

public class Piste {
	
	private int numero;
	private String titre;
	private int duree; // Dur�e de la chanson en seconde
	
	public Piste(int num, String titre, int duree) {
		this.setNumero(num);
		this.setTitre(titre);
		this.setDuree(duree);
	}

	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public int getDuree() {
		return duree;
	}
	public void setDuree(int duree) {
		this.duree = duree;
	}
	
	public static String[] getLabelValues() {
		String[] lbls = {"Numéro de piste", "Titre", "Durée"};
		return lbls;
	}

}
