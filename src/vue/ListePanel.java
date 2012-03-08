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


import modele.*;

public class ListePanel extends JPanel {
	
	JTable table;
	
	public JTable getTable() {
		return this.table;
	}

	public ListePanel(List<?> listeData) {
		
		this.setLayout(new BorderLayout());
		this.table = new JTable();
		setList(listeData); // On ajoute les données envoyées par défault
	
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
			if(listeData.get(0) instanceof AudioLivre) {
				mesDonnees = new String[listeData.size()][Bibliotheque.getLabelValues(AudioLivre.class, false).length];
				List<AudioLivre> data = (List<AudioLivre>) listeData;

				for(int i = 0; i < listeData.size(); i++) {		
						mesDonnees[i][0] = data.get(i).getIsbn();
						mesDonnees[i][1] = data.get(i).getAuteur();
						mesDonnees[i][2] = data.get(i).getTitre();
						mesDonnees[i][3] = data.get(i).getFullStringDateParution();
						mesDonnees[i][4] = String.valueOf(data.get(i).getNbPages());
						mesDonnees[i][5] = String.valueOf(data.get(i).getDuree());
						mesDonnees[i][6] = String.valueOf(data.get(i).getPrix());
				}
				mesLabels = Bibliotheque.getLabelValues(AudioLivre.class, false);
			}
			if(listeData.get(0) instanceof Livre) {
				
				mesDonnees = new String[listeData.size()][Bibliotheque.getLabelValues(Livre.class, false).length];
				List<Livre> data = (List<Livre>) listeData;
				
				for(int i = 0; i < listeData.size(); i++) {		
						mesDonnees[i][0] = data.get(i).getIsbn();
						mesDonnees[i][1] = data.get(i).getAuteur();
						mesDonnees[i][2] = data.get(i).getTitre();
						mesDonnees[i][3] = data.get(i).getFullStringDateParution();
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
						mesDonnees[i][3] = data.get(i).getFullStringDateParution();
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
						mesDonnees[i][3] = data.get(i).getFullStringDateParution();
						mesDonnees[i][4] = String.valueOf(data.get(i).getPrix());
						mesDonnees[i][5] = String.valueOf(data.get(i).getNbPistes());
						mesDonnees[i][6] = String.valueOf(data.get(i).getDuree());
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
						mesDonnees[i][3] = data.get(i).getFullStringDateParution();
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
						mesDonnees[i][3] = data.get(i).getFullStringDateParution();
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
						mesDonnees[i][3] = data.get(i).getStringDateNaissance();
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
						mesDonnees[i][3] = data.get(i).getStringDateNaissance();
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
						if(data.get(i).getMedia() != null) {
							mesDonnees[i][0] = data.get(i).getMedia().getIsbn();
							mesDonnees[i][1] = data.get(i).getMedia().getTitre();
						}
						else {
							mesDonnees[i][0] = "Média supprimé";
							mesDonnees[i][1] = "Média supprimé";
						}
						if(data.get(i).getMembre() != null) {
							mesDonnees[i][2] = String.valueOf(data.get(i).getMembre().getIdentifiant());
							mesDonnees[i][3] = data.get(i).getMembre().getNom() +" "+ data.get(i).getMembre().getPrenom();
						}
						else {
							mesDonnees[i][2] = "Membre supprimé";
							mesDonnees[i][3] = "Membre supprimé";
						}
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
			
			this.table.setModel(new DefaultTableModel(mesDonnees, mesLabels) {
				@Override
	            public boolean isCellEditable(int row, int column) {
	                return false;
	            }
			});
		}
	}
	
	// JTable spécifique pour afficher les médias en cours de lecture : seulement les livres et les CD --> cast en Media
	public void setLectureEnCoursList(List<Media> listeData) {
		String[] mesLabels = Bibliotheque.getLabelValues(Media.class, false);
		Object[][] mesDonnees = new String[listeData.size()][mesLabels.length];

		if(listeData == null || listeData.isEmpty()) {
			this.table.setModel(new DefaultTableModel()); // JTable VIDE
		}
		else {
			for(int i=0; i<listeData.size(); i++) {
				Media m = listeData.get(i);

				mesDonnees[i][0] = m.getIsbn();
				mesDonnees[i][1] = m.getAuteur();
				mesDonnees[i][2] = m.getTitre();
				mesDonnees[i][3] = m.getDateParution().toLocaleString();
				mesDonnees[i][4] = String.valueOf(m.getPrix());
			}
			
			this.table.setModel(new DefaultTableModel(mesDonnees, mesLabels) {
				@Override
	            public boolean isCellEditable(int row, int column) {
	                return false;
	            }
			});
		}
		
	}
}
