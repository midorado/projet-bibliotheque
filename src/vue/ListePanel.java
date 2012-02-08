package vue;

import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import modèle.Media;

public class ListePanel extends JPanel{
	
	JTable tableau;
	DefaultTableModel modeleTableau;
	
	public ListePanel(){
		
		this.setLayout(new BorderLayout());
		
		this.add(new JScrollPane(this.tableau), BorderLayout.CENTER);	
		this.setVisible(true);	
	}
	
	public void setTableauModele(String[] titre	,Object[] objects){
		modeleTableau.addColumn(titre, objects);
		this.tableau = new JTable(modeleTableau);
	}
}
