package modèle;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class Emprunt {
	
	private Date dateEmprunt;
	private Date dateRetour;
	private Date dateLimiteRetour;
	private Media media;
	private Membre membre;
	
	public Emprunt(Media med, Membre mb) {
		this.setDateEmprunt(new Date());
		this.setDateLimiteRetour(dateEmprunt);
		this.setMedia(med);
		this.setMembre(mb);
	}

	/* DÃ©but getters & setters */
	public Date getDateEmprunt() {
		return dateEmprunt;
	}
	public void setDateEmprunt(Date date) {
		this.dateEmprunt = date;
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
	public void setDateRetour(Date date) {
		this.dateRetour = date;
	}
	public Date getDateLimiteRetour() {
		return dateLimiteRetour;
	}
	private void setDateLimiteRetour(Date dateEmprunt) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(dateEmprunt);
		cal.add(Calendar.DATE, 10); // 10 jours d'emprunt autorisé
		this.dateLimiteRetour = cal.getTime();
	}
	public boolean isEnCours() {
		return (dateRetour == null);
	}

}
