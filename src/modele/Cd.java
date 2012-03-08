package modele;
import java.util.Date;
import java.util.List;


public class Cd extends AudioVisuel {

	private List<Piste> pistes;
	private boolean enLecture;
	
	public Cd(String unIsbn, String unAuteur, String unTitre,
			Date uneDateParrution, List<Piste> pistes) {
		super(unIsbn, unAuteur, unTitre, uneDateParrution, 0);
	
		this.enLecture = false;
		this.pistes = pistes;

	}

	@Override
	public float getPrix() {
		float prix = 10;
		
		if(this.getNbPistes() > 10)
			prix += getNbPistes() * 1; // 1€ la piste supplémentaire
		
		return prix;
	}
	
	@Override
	public int getDuree() {
		int duree = 0;
		
		if(this.pistes != null) {
			for(Piste p : this.pistes)
				duree += p.getDuree();
		}
		
		setDuree(duree);
		return duree;
	}
	
	public int getNbPistes() {
		return pistes.size();
	}
	
	public List<Piste> getPistes() {
		return this.pistes;
	}
	
	public void setPistes(List<Piste> pistes) {
		this.pistes = pistes;
	}
	
	public boolean enLecture(){
		return this.enLecture;
	}
	
	public void demarrerLecture(){
		this.enLecture = true;
	}
	
	public void stopperLecture(){
		this.enLecture = false;
	}
}
