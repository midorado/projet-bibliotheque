import java.util.Date;


public class Emprunt {
	
	private Date dateEmprunt;
	private Date dateRetour;
	private Date dateLimiteRetour;
	private Media media;
	private Membre membre;
	
	public Emprunt(Media med, Membre mb) {
		this.setDateEmprunt(new Date());
		this.setMedia(med);
		this.setMembre(mb);
	}

	/* Début getters & setters */
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

}
