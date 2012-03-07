package vue;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

import controleur.Bibliotheque;

import modèle.Cd;
import modèle.CoffretDvd;
import modèle.Dvd;
import modèle.Media;
import modèle.Piste;

/**
 * 
 * Classe mère de AjoutDialog et EditDialog
 * Elle s'occupe uniquement de la création et de l'affichage des composants (et l'action du bouton annuler).
 * Les actions sont traitées dans les classes filles
 *
 */
public abstract class BiblioDialog extends JDialog implements ActionListener {

	// Constantes de classe
	public static final int RET_CANCEL = 0;
	public static final int RET_OK = 1;
	
	protected int retStatus = -1;
	protected JPanel container;
	protected JPanel panelButton;
	protected JButton btnValider;
	protected JButton btnAnnuler;
	protected DefaultListModel listModel;
	protected JList lstDvdOuPiste;
	protected JButton btnAjoutDvdOuPiste; // Pour les CD ou les DVD
	protected JButton btnSupprDvdOuPiste;
	protected List<Dvd> listDvds;
	protected List<Piste> listPistes;
	protected TextForm form;
	protected String[] labels;
	protected Class<?> typeObj;
	
	public BiblioDialog(String titreFrame, JDialog parent, Class<?> typeObj) {
		super(parent, titreFrame, true);
		
		this.typeObj = typeObj;
		
		buildInterface();
		
		buildEvents();
	}
	
	private void buildInterface() {
		setLocationRelativeTo(null);
		setResizable(false);
		
		container = new JPanel();
		container.setLayout(new BorderLayout());
		
		// Boutons
		panelButton = new JPanel();
		btnValider = new JButton("Valider");
		btnAnnuler = new JButton("Annuler");
		
		panelButton.add(btnValider);
		panelButton.add(btnAnnuler);
		
		container.add(panelButton, BorderLayout.SOUTH);
		
		// Récupération des labels en fonction du type d'objet et création du formulaire
		labels = Bibliotheque.getLabelValues(typeObj, true);

		form = new TextForm(labels);
		
		container.add(form, BorderLayout.NORTH);
		

		// Bouton ajouter Dvd ou ajouter Piste et label pour afficher la liste
		this.btnAjoutDvdOuPiste = new JButton();
		this.btnSupprDvdOuPiste = new JButton("Supprimer la sélection");
		
		if(typeObj == CoffretDvd.class || typeObj == Cd.class) {

			JPanel panAjoutDvdOuPiste = new JPanel(new BorderLayout());
			JPanel panButtons = new JPanel();

			this.listModel = new DefaultListModel();
			this.lstDvdOuPiste = new JList(this.listModel);
			this.lstDvdOuPiste.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);		

			JScrollPane listScrollPane = new JScrollPane(lstDvdOuPiste, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

			panButtons.add(btnAjoutDvdOuPiste);
			panButtons.add(btnSupprDvdOuPiste);
			
			panAjoutDvdOuPiste.add(listScrollPane, BorderLayout.NORTH);
			panAjoutDvdOuPiste.add(panButtons, BorderLayout.CENTER);			
		
			container.add(panAjoutDvdOuPiste);			
			
			if(typeObj == CoffretDvd.class) {
				this.btnAjoutDvdOuPiste.setText("Ajouter des DVD au coffret");
			}
			else if(typeObj == Cd.class) {
				this.btnAjoutDvdOuPiste.setText("Ajout des pistes au CD");
			}
		}
		
		setContentPane(container);
		
		pack();
	}
	
	private void buildEvents() {
		this.btnValider.addActionListener(this);
		this.btnAnnuler.addActionListener(this);
		this.btnAjoutDvdOuPiste.addActionListener(this);
		this.btnSupprDvdOuPiste.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnAnnuler) {
			this.retStatus = BiblioDialog.RET_CANCEL;
			setVisible(false);
			dispose();
		}
		else if(e.getSource() == btnSupprDvdOuPiste) {
			int index = lstDvdOuPiste.getSelectedIndex();
			
			if(index == -1) {
				JOptionPane.showConfirmDialog(this, "Veuillez sélectionner une ligne avant de supprimer", "Aucune ligne sélectionnée", JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE);
			}
			else {
				if(typeObj == CoffretDvd.class)
					listDvds.remove(index);
				else if(typeObj == Cd.class)
					listPistes.remove(index);
				
				refreshLstDvdOuPiste();
			}				
		}
	}
	
	// Remplissage de la JList pour les coffrets DVD ou les CD
	public void refreshLstDvdOuPiste() {
		listModel.clear();
		
		if(listDvds != null && !listDvds.isEmpty()) {
			System.out.println("TAILLE DVDS : "+listDvds.size());
			for(Dvd d : listDvds) {
				System.out.println(d.getTitre());
				listModel.addElement(d.getTitre());
			}
		}
		else if(listPistes != null && !listPistes.isEmpty()) {
			for(Piste p : listPistes)
				listModel.addElement(p.getNumero()+" - "+p.getTitre()+" ("+p.getDuree()+" secondes)");
		}
	}
	
	public TextForm getForm() {
		return this.form;
	}
	
	public int getReturnStatus() {
		return this.retStatus;
	}
}
