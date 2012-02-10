package vue;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Date;

import javax.print.attribute.standard.Media;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import modèle.Abonne;
import modèle.AudioLivre;
import modèle.Cd;
import modèle.CoffretDvd;
import modèle.Dvd;
import modèle.Livre;
import modèle.Magazine;
import modèle.Personnel;

import controleur.Bibliotheque;

public class AjoutFrame extends JFrame implements ActionListener {

	private JPanel container;
	private JPanel panelButton;
	private JButton btnAjouter;
	private JButton btnAnnuler;
	private TextForm form;
	private String[] labels;
	private Class<?> typeObj;
	
	public AjoutFrame(String titreFrame, Class<?> typeObj) {
		super(titreFrame);
		
		this.typeObj = typeObj;
		
		buildInterface();
		
		buildEvents();
	}

	private void buildInterface() {
		setSize(400, 350);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		container = new JPanel();
		container.setLayout(new BorderLayout());
		
		// Boutons
		panelButton = new JPanel();
		btnAjouter = new JButton("Ajouter");
		btnAnnuler = new JButton("Annuler");
		
		panelButton.add(btnAjouter);
		panelButton.add(btnAnnuler);
		
		container.add(panelButton, BorderLayout.SOUTH);
		
		// Formulaire
		if(this.typeObj == Abonne.class)
			labels = Abonne.getLabelValues();
		else if(this.typeObj == Personnel.class)
			labels = Personnel.getLabelValues();
		else if(this.typeObj == AudioLivre.class)
			labels = AudioLivre.getLabelValues();
		else if(this.typeObj == Cd.class)
			labels = Cd.getLabelValues();
		else if(this.typeObj == CoffretDvd.class)
			labels = CoffretDvd.getLabelValues();
		else if(this.typeObj == Dvd.class)
			labels = Dvd.getLabelValues();
		else if(this.typeObj == Livre.class)
			labels = Livre.getLabelValues();
		else if(this.typeObj == Magazine.class)
			labels = Magazine.getLabelValues();
		
		form = new TextForm(labels, Media.class);
		
		container.add(form, BorderLayout.NORTH);
		
		setContentPane(container);
	}
	
	private void buildEvents() {
		btnAjouter.addActionListener(this);
		btnAnnuler.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnAnnuler) {
			super.dispose();
		}
		else if(e.getSource() == btnAjouter) {
			boolean err = false;
			
			int id = 0, nbPages = 0, duree = 0;
			String nom = null, prenom = null, poste = null, isbn = null, auteur = null, titre = null, modeParution = null;
			Date dateNaiss = null, dateParution = null;
			
			// Valeurs communes aux membres
			if(this.typeObj == Abonne.class || this.typeObj == Personnel.class) {
				id = Bibliotheque.getNouvelIdMembre();
				nom = form.getFieldText(1);
				prenom = form.getFieldText(2);
				dateNaiss = null;
				try {
					dateNaiss = Bibliotheque.stringToDate(form.getFieldText(3));
				} catch (ParseException e1) {
					err = true;
				}
				
				// Valeurs spécifiques
				if(this.typeObj == Personnel.class) 
					poste = form.getFieldText(3);
				
			}
			else { // Valeurs communes aux médias
				isbn = form.getFields()[0].getText();
				auteur = form.getFields()[1].getText();
				titre = form.getFields()[2].getText();
				dateParution = null;
				try {
					dateParution = Bibliotheque.stringToDate(form.getFieldText(3));
				} catch (ParseException e1) {
					err = true;
				}
				
				// Valeurs spécifiques
				if(typeObj == AudioLivre.class || typeObj == Livre.class || typeObj == Magazine.class) {
					try {
						nbPages = Integer.parseInt(form.getFieldText(4));
					}
					catch (NumberFormatException nfe) {
						err = true;
					}
					
					if(typeObj == Magazine.class)
						modeParution = form.getFieldText(5);
				}
				else if(typeObj == Dvd.class || typeObj == CoffretDvd.class){
					try {
						duree = Integer.parseInt(form.getFieldText(4));
					}
					catch (NumberFormatException nfe) {
						err = true;
					}
				}
			}
			
			if(err) { // Message d'erreur
				JOptionPane.showMessageDialog(this, "Veuillez remplir correctement tout les champs","Erreur", JOptionPane.ERROR_MESSAGE);
			}
			else {
				if(this.typeObj == Abonne.class)
					Bibliotheque.addMembre(new Abonne(id, nom, prenom, dateNaiss));
				else if(this.typeObj == Personnel.class)
					Bibliotheque.addMembre(new Personnel(id, nom, prenom, dateNaiss, poste));
				else if(this.typeObj == AudioLivre.class)
					Bibliotheque.addMedia(new AudioLivre(isbn, auteur, titre, dateParution, nbPages, null));
				else if(this.typeObj == Cd.class)
					Bibliotheque.addMedia(new Cd(isbn, auteur, titre, dateParution, null));
				else if(this.typeObj == CoffretDvd.class)
					Bibliotheque.addMedia(new CoffretDvd(isbn, auteur, titre, dateParution, null));
				else if(this.typeObj == Dvd.class)
					Bibliotheque.addMedia(new Dvd(isbn, auteur, titre, dateParution, duree));
				else if(this.typeObj == Livre.class)
					Bibliotheque.addMedia(new Livre(isbn, auteur, titre, dateParution, nbPages));
				else if(this.typeObj == Magazine.class)
					Bibliotheque.addMedia(new Magazine(isbn, auteur, titre, dateParution, nbPages, modeParution));
				
				this.dispose(); // On ferme la fenetre
			}
	
			//	Bibliotheque.addMedia(new Livre(isbn, auteur, titre, dateParution, nbPages));
				
		
		}
	}
}
