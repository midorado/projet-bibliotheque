package vue;

import java.awt.BorderLayout;
import java.text.DateFormat;
import java.util.List;
import java.util.Properties;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DateFormatter;

import controleur.Bibliotheque;


import modèle.*;

public class ListePanel extends JPanel {
	
	JTable table;
	
	public JTable getTable() {
		return this.table;
	}

	public ListePanel(List<?> listeData) {
		
		this.setLayout(new BorderLayout());
		this.table = new JTable();
		setList(listeData);
	
		this.add(new JScrollPane(this.table), BorderLayout.CENTER);	
		this.setVisible(true);	
	}
	
	public void setList(List<?> listeData) {
		
		Object[][] mesDonnees = null;
		String[] mesLabels = null;

		if(listeData == null || listeData.isEmpty()) {
			this.table.setModel(new DefaultTableModel()); // JTable VIDE
		}
		else {
			// Médias
			if(listeData.get(0) instanceof Livre) {
				
				mesDonnees = new String[listeData.size()][Bibliotheque.getLabelValues(Livre.class, false).length];
				List<Livre> data = (List<Livre>) listeData;
				
				for(int i = 0; i < listeData.size(); i++) {		
						mesDonnees[i][0] = data.get(i).getIsbn();
						mesDonnees[i][1] = data.get(i).getAuteur();
						mesDonnees[i][2] = data.get(i).getTitre();
						mesDonnees[i][3] = data.get(i).getDateParution().toLocaleString();
						mesDonnees[i][4] = String.valueOf(data.get(i).getNbPages());
						mesDonnees[i][5] = String.valueOf(data.get(i).getPrix());
				}
				mesLabels = Bibliotheque.getLabelValues(Livre.class, false);
			}
			
			else if(listeData.get(0) instanceof Magazine) {
				
				mesDonnees = new String[listeData.size()][Bibliotheque.getLabelValues(Magazine.class, false).length];
				List<Magazine> data = (List<Magazine>) listeData;
				
				for(int i = 0; i < listeData.size(); i++) {		
						mesDonnees[i][0] = data.get(i).getIsbn();
						mesDonnees[i][1] = data.get(i).getAuteur();
						mesDonnees[i][2] = data.get(i).getTitre();
						mesDonnees[i][3] = data.get(i).getDateParution().toLocaleString();
						mesDonnees[i][4] = String.valueOf(data.get(i).getNbPages());
						mesDonnees[i][5] = String.valueOf(data.get(i).getModeParution());
						mesDonnees[i][6] = String.valueOf(data.get(i).getPrix());
				}
				mesLabels = Bibliotheque.getLabelValues(Magazine.class, false);
			}
			
			else if(listeData.get(0) instanceof Cd) {
					
				mesDonnees = new String[listeData.size()][Bibliotheque.getLabelValues(Cd.class, false).length];
				List<Cd> data = (List<Cd>) listeData;
				
				for(int i = 0; i < listeData.size(); i++){		
						mesDonnees[i][0] = data.get(i).getIsbn();
						mesDonnees[i][1] = data.get(i).getAuteur();
						mesDonnees[i][2] = data.get(i).getTitre();
						mesDonnees[i][3] = data.get(i).getDateParution().toLocaleString();
						mesDonnees[i][4] = String.valueOf(data.get(i).getPrix());
						mesDonnees[i][5] = String.valueOf(data.get(i).getNbPistes());
						mesDonnees[i][6] = String.valueOf(data.get(i).getDuree());
					//	mesDonnees[i][7] = String.valueOf(data.get(i).isEmpruntable());
				}
				mesLabels = Bibliotheque.getLabelValues(Cd.class, false);
			}
			
			else if(listeData.get(0) instanceof Dvd) {
				
				mesDonnees = new String[listeData.size()][Bibliotheque.getLabelValues(Dvd.class, false).length];
				List<Dvd> data = (List<Dvd>) listeData;

				for(int i = 0; i < listeData.size(); i++){		
						mesDonnees[i][0] = data.get(i).getIsbn();
						mesDonnees[i][1] = data.get(i).getAuteur();
						mesDonnees[i][2] = data.get(i).getTitre();
						mesDonnees[i][3] = data.get(i).getDateParution().toLocaleString();
						mesDonnees[i][4] = String.valueOf(data.get(i).getDuree());
						mesDonnees[i][5] = String.valueOf(data.get(i).getPrix());
				}
				mesLabels = Bibliotheque.getLabelValues(Dvd.class, false);
			}
			
			else if(listeData.get(0) instanceof CoffretDvd) {
				
				mesDonnees = new String[listeData.size()][Bibliotheque.getLabelValues(CoffretDvd.class, false).length];
				List<CoffretDvd> data = (List<CoffretDvd>) listeData;
				
				for(int i = 0; i < listeData.size(); i++){		
						mesDonnees[i][0] = data.get(i).getIsbn();
						mesDonnees[i][1] = data.get(i).getAuteur();
						mesDonnees[i][2] = data.get(i).getTitre();
						mesDonnees[i][3] = data.get(i).getDateParution().toLocaleString();
						mesDonnees[i][4] = String.valueOf(data.get(i).getDuree());
						mesDonnees[i][5] = String.valueOf(data.get(i).getPrix());
				}
				mesLabels = Bibliotheque.getLabelValues(CoffretDvd.class, false);
			}
			// Membres
			else if(listeData.get(0) instanceof Abonne){
				
				mesDonnees = new String[listeData.size()][Bibliotheque.getLabelValues(Abonne.class, false).length];
				List<Abonne> data = (List<Abonne>) listeData;
				
				for(int i = 0; i < listeData.size(); i++){		
						mesDonnees[i][0] = String.valueOf(data.get(i).getIdentifiant());
						mesDonnees[i][1] = data.get(i).getNom();
						mesDonnees[i][2] = data.get(i).getPrenom();
						mesDonnees[i][3] = data.get(i).getDateNaissance().toLocaleString();
						mesDonnees[i][4] = String.valueOf(data.get(i).getTauxReduction());
				}
				mesLabels = Bibliotheque.getLabelValues(Abonne.class, false);
			}
			
			else if(listeData.get(0) instanceof Personnel){
				
				mesDonnees = new String[listeData.size()][Bibliotheque.getLabelValues(Personnel.class, false).length];
				List<Personnel> data = (List<Personnel>) listeData;
				
				for(int i = 0; i < listeData.size(); i++){		
						mesDonnees[i][0] = String.valueOf(data.get(i).getIdentifiant());
						mesDonnees[i][1] = data.get(i).getNom();
						mesDonnees[i][2] = data.get(i).getPrenom();
						mesDonnees[i][3] = data.get(i).getDateNaissance().toLocaleString();
						mesDonnees[i][4] = data.get(i).getPoste();
						mesDonnees[i][5] = String.valueOf(data.get(i).getTauxReduction());
				}
				mesLabels = Bibliotheque.getLabelValues(Personnel.class, false);
				
			}
			// Emprunts
			else if(listeData.get(0) instanceof Emprunt){

				mesDonnees = new String[listeData.size()][Bibliotheque.getLabelValues(Emprunt.class, false).length];
				List<Emprunt> data = (List<Emprunt>) listeData;
				
				for(int i = 0; i < listeData.size(); i++) {	
						mesDonnees[i][0] = data.get(i).getMedia().getIsbn();
						mesDonnees[i][1] = data.get(i).getMedia().getTitre();
						mesDonnees[i][2] = String.valueOf(data.get(i).getMembre().getIdentifiant());
						mesDonnees[i][3] = data.get(i).getMembre().getNom() +" "+ data.get(i).getMembre().getPrenom();
						mesDonnees[i][4] = data.get(i).getDateEmprunt().toLocaleString();
						mesDonnees[i][5] = data.get(i).getDateLimiteRetour().toLocaleString();

						if(data.get(i).getDateRetour() == null) {
							mesDonnees[i][6] = "...";
						}
						else {
							mesDonnees[i][6] = data.get(i).getDateRetour().toLocaleString();
						}
				}
				mesLabels = Bibliotheque.getLabelValues(Emprunt.class, false);
				
			}
			
			this.table.setModel(new DefaultTableModel(mesDonnees, mesLabels));
		}
	}
}