import java.security.Timestamp;
import java.util.Calendar;
import java.util.Date;


public abstract class Membre {
	
	private int identifiant;
	private String nom;
	private String prenom;
	private Date dateNaissance;
	
	public Membre(int identifiant, String nom, String prenom, Date dateNaiss) {
		this.setIdentifiant(identifiant);
		this.setNom(nom);
		this.setPrenom(prenom);
		this.dateNaissance = dateNaiss;
	}
	
	public int getAge() {
		Calendar today = Calendar.getInstance();
		Calendar anniv = Calendar.getInstance();
		
		anniv.setTime(this.dateNaissance);
		int age = today.get(Calendar.YEAR) - anniv.get(Calendar.YEAR);
		
		today.add(Calendar.YEAR, -age);
		
		if(anniv.after(today))
			age = age - 1;
	
		return age;
	}
	
	public abstract float getTauxReduction();
	
	
	/* Début getters & setters */
	public int getIdentifiant() {
		return identifiant;
	}
	public void setIdentifiant(int identifiant) {
		this.identifiant = identifiant;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public Date getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(Date dateNaiss) {
		this.dateNaissance = dateNaiss;
	}
}
