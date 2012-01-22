import java.util.Date;


public class Emprunt {
	
	private Date dateEmprunt;
	private Date dateRetour;
	private Date dateLimiteRetour;
	private Media media;
	private Membre membre;
	private boolean enCours; // Si le media n'a pas encore été rendu à la bibliothèque
	
	public Emprunt(Media med, Membre mb) {
		this.setDateEmprunt(new Date());
		this.setMedia(med);
		this.setMembre(mb);
	}

	/* DÃ©but getters & setters */
	public Date getDateEmprunt() {
		return dateEmprunt;
	}
	public void setDateEmprunt(Date dateEmprunt) {
		this.dateEmprunt = dateEmprunt;
	}
	public Media getMedia() {
		return media;
	}
	public void setMedia(Media media) {
		this.media = media;
	}
	public Membre getMembre() {
		return membre;
	}
	public void setMembre(Membre membre) {
		this.membre = membre;
	}
	public Date getDateRetour() {
		return dateRetour;
	}
	public void setDateRetour(Date dateRetour) {
		this.dateRetour = dateRetour;
	}
	public Date getDateLimiteRetour() {
		return dateLimiteRetour;
	}
	public void setDateLimiteRetour(Date dateLimiteRetour) {
		this.dateLimiteRetour = dateLimiteRetour;
	}
	public boolean isEnCours() {
		return enCours;
	}
	public void setEnCours(boolean enCours) {
		this.enCours = enCours;
	}

}
