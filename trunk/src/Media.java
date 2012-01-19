import java.util.Date;


public abstract class Media {
	
	private String isbn;
	private String auteur;
	private String titre;
	private Date dateParution;
	private boolean empruntable;
	
	/**
	 * 
	 * @param unIsbn
	 * @param unAuteur
	 * @param unTitre
	 * @param uneDateParution
	 */
	public Media(String unIsbn, String unAuteur, String unTitre, Date uneDateParution){
		this.setIsbn(unIsbn);
		this.setAuteur(unAuteur);
		this.setTitre(unTitre);
		this.setDateParution(uneDateParution);		
	}

	public abstract float getPrix();

	/* **************** Debut Get Set ******************* */
	
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getAuteur() {
		return auteur;
	}
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}

	public Date getDateParution() {
		return dateParution;
	}
	public void setDateParution(Date dateParrution) {
		this.dateParution = dateParrution;
	}
	
	public boolean isEmpruntable() {
		return empruntable;
	}
	public void setEmpruntable(boolean empruntable) {
		this.empruntable = empruntable;
	}
	
	/* ****************Fin Get Set******************* */
}
