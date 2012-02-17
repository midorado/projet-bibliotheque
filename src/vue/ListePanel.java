package vue;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controleur.Bibliotheque;


import modèle.*;

public class ListePanel extends JPanel {
	
	JTable liste;
	

	public ListePanel(List<?> listeData) {
		
		this.setLayout(new BorderLayout());
		setList(listeData);
		this.add(new JScrollPane(this.liste), BorderLayout.CENTER);	
		this.setVisible(true);	
	}
	
	public void setList(List<?> listeData) {
		
		Object[][] mesDonnees = null;
		String[] mesLabels = null;

		if(listeData != null && !listeData.isEmpty()) {
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
						mesDonnees[i][6] = String.valueOf(data.get(i).isEmpruntable());
				}
				mesLabels = Bibliotheque.getLabelValues(Livre.class, false);
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
						mesDonnees[i][0] = data.get(i).getIdentifiant();
						mesDonnees[i][1] = data.get(i).getNom();
						mesDonnees[i][2] = data.get(i).getPrenom();
						mesDonnees[i][3] = data.get(i).getDateNaissance().toLocaleString();
						mesDonnees[i][4] = data.get(i).getPoste();
						mesDonnees[i][5] = String.valueOf(data.get(i).getTauxReduction());
				}
				mesLabels = Bibliotheque.getLabelValues(Personnel.class, false);
			}
			
		//	this.invalidate();
			this.liste.invalidate();
			
			this.liste = new JTable(mesDonnees, mesLabels);
			
			this.revalidate();
			this.liste.revalidate();
		//	this.liste.repaint();
		//	this.repaint();
		}
	}
	
	public void invalidateTable() {
		this.liste.invalidate();
	}
	
	public void revalidateTable() {
		this.liste.revalidate();
	}
}
