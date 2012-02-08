package vue;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LivreAjoutFrame extends AjoutFrame {
	
	private JPanel panelLabel;
	private JPanel panelField;
	
//	private JLabel[] labels;
	private JTextField[] fields;

	public LivreAjoutFrame(String titre) {
		super(titre);

		String[] tabLabels = {"ISBN", "Auteur", "Titre", "Date de parution", "Nombre de pages"};
		
		panelLabel = new JPanel(new GridLayout(tabLabels.length, 1));
		panelField = new JPanel(new GridLayout(tabLabels.length, 1));
		
		super.container.add(panelLabel, BorderLayout.WEST);
		super.container.add(panelField, BorderLayout.CENTER);
		
		//labels = new JLabel[tabLabels.length];
		fields = new JTextField[tabLabels.length];
		
		for(int i=0; i<tabLabels.length; i++) {
		//	labels[i] = new JLabel(tabLabels[i] + " : ");
			fields[i] = new JTextField();
			fields[i].setColumns(22);
			
			JLabel lab = new JLabel(tabLabels[i]+" : ", JLabel.RIGHT);
			lab.setLabelFor(fields[i]);

			panelLabel.add(lab);
			
			JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
		    p.add(fields[i]);
		    panelField.add(p);
		      
		//	panelField.add(fields[i]);
		}

		

	}
}