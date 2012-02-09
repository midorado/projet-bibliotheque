package vue;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TextForm extends JPanel {

  private JTextField[] fields;

  // Create a form with the specified labels, tooltips, and sizes.
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
		  throw new IllegalArgumentException();
	  
	  return this.fields[i].getText();
  }
}