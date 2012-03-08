package modèle;
import java.util.Calendar;
import java.util.Date;


public class Dvd extends AudioVisuel {

	
	public Dvd(String unIsbn, String unAuteur, String unTitre, Date uneDateParrution, int uneDuree) {
		super(unIsbn, unAuteur, unTitre, uneDateParrution, uneDuree);
	}
	
	public float getPrix() {
		float prix = 0;
		
		Calendar today = Calendar.getInstance();
		Calendar parution = Calendar.getInstance();
		
		parution.setTime(super.getDateParution());
		
		int years = today.get(Calendar.YEAR) - parution.get(Calendar.YEAR);
		
		today.add(Calendar.YEAR, -years);
		
		if(parution.after(today))
			years--;
	
		if(years < 5)
			prix = 25;
		else
			prix = 15;
		
		return prix;
	}

}
