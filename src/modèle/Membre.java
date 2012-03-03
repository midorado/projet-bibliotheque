package modèle;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public abstract class Membre {
	
	private int identifiant;
	private String nom;
	private String prenom;
	private Date dateNaissance;
	private List<Emprunt> emprunts;
	
	public Membre(int identifiant, String nom, String prenom, Date dateNaiss) {
		this.setIdentifiant(identifiant);
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setDateNaissance(dateNaiss);
		this.setEmprunts(new ArrayList<Emprunt>());
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
	
	
	/* D�but getters & setters */
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
	public List<Emprunt> getEmprunts() {
		return emprunts;
	}
	public void setEmprunts(List<Emprunt> mesEmprunts) {
		this.emprunts = mesEmprunts;
	}
	public void addEmprunt(Emprunt e) {
		this.emprunts.add(e);
	}
	public String getFullStringDateNaissance() {
		DateFormat formatter = DateFormat.getDateInstance(DateFormat.FULL, Locale.FRENCH);
		return formatter.format(dateNaissance);
	}
	public String getStringDateNaissance() {
		SimpleDateFormat pat = new SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH);
		return pat.format(dateNaissance);
	}
}
