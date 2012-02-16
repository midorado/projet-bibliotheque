package vue;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.print.attribute.standard.Media;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modèle.Membre;

public class TextForm extends JPanel {

	private JTextField[] fields;
  
	/**
	 * Créé un formulaire en fonction du type d'objet
	 * @param labels
	 */
	public TextForm(String[] labels) {
		super(new BorderLayout());
	  
		JPanel labelPanel = new JPanel(new GridLayout(labels.length, 1));
		JPanel fieldPanel = new JPanel(new GridLayout(labels.length, 1));
    
		add(labelPanel, BorderLayout.WEST);
		add(fieldPanel, BorderLayout.CENTER);
    
		fields = new JTextField[labels.length];
	
		for (int i = 0; i < labels.length; i++) {
			fields[i] = new JTextField();
	 	    fields[i].setColumns(20);
	 	    
	 	    JLabel lab = new JLabel(labels[i]+" :", JLabel.RIGHT);   
	 	    JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
	 	    p.add(fields[i]);
	 	    
	 	    labelPanel.add(lab);
	 	    fieldPanel.add(p);
	    }
	}
  
	public JTextField[] getFields() {
		return this.fields;
	}
  
	public String getFieldText(int i) {
		if(i < 0 || i > this.fields.length-1)
			throw new IllegalArgumentException("int : "+i);
	  
		return this.fields[i].getText();
	}
}