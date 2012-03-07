package vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import modèle.Abonne;
import modèle.AudioLivre;
import modèle.Cd;
import modèle.CoffretDvd;
import modèle.Dvd;
import modèle.Livre;
import modèle.Magazine;
import modèle.Personnel;
import modèle.Piste;
import controleur.Bibliotheque;

public class AjoutDialog extends BiblioDialog implements ActionListener {

	public AjoutDialog(String titreFrame, Class<?> typeObj) {
		super(titreFrame, null, typeObj);
		
		super.listDvds = new ArrayList<Dvd>();
		super.listPistes = new ArrayList<Piste>();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		
		if(e.getSource() == super.btnValider) {
			boolean err = false;
			
			// Déclaration des variables
			int id = 0, nbPages = 0, duree = 0, numero = 0;
			String nom = null, prenom = null, poste = null, isbn = null, auteur = null, titre = null, modeParution = null;
			Date dateNaiss = null, dateParution = null;
			
			if(this.typeObj == Abonne.class || this.typeObj == Personnel.class) {
				// Valeurs communes aux membres
			/***********************************************/
				id = Bibliotheque.getNouvelIdMembre();
				nom = form.getFieldText(0);
				prenom = form.getFieldText(1);
				dateNaiss = null;
				try {
					dateNaiss = Bibliotheque.stringToDate(form.getFieldText(2));
				} catch (ParseException e1) {
					err = true;
				}
				
				// Valeurs spécifiques
				if(this.typeObj == Personnel.class) 
					poste = form.getFieldText(3);
	
			}
			else if(this.typeObj == Piste.class){
				try {
					numero = Integer.parseInt(form.getFieldText(0));
					duree = Integer.parseInt(form.getFieldText(2));
				}
				catch(NumberFormatException nfe) {
					err = true;
				}
				
				titre = form.getFieldText(1);
			}
			else {
				// Valeurs communes aux médias
				isbn = form.getFieldText(0);
				auteur = form.getFieldText(1);
				titre = form.getFieldText(2);
				dateParution = null;
				
				if(Bibliotheque.getMediaByIsbn(isbn) != null) {
					err = true;
					JOptionPane.showMessageDialog(this, "Un média avec ce même ISBN existe déjà","Erreur", JOptionPane.ERROR_MESSAGE);
				}
				else {
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
					else if(typeObj == Dvd.class){
						try {
							duree = Integer.parseInt(form.getFieldText(4));
						}
						catch (NumberFormatException nfe) {
							err = true;
						}
					}
				}
			}
			
			if(err) { // Message d'erreur
				JOptionPane.showMessageDialog(this, "Veuillez remplir correctement tout les champs","Erreur", JOptionPane.ERROR_MESSAGE);
			}
			else { // Ajout de l'objet dans la base
				if(this.typeObj == Abonne.class)
					Bibliotheque.addMembre(new Abonne(id, nom, prenom, dateNaiss));
				else if(this.typeObj == Personnel.class)
					Bibliotheque.addMembre(new Personnel(id, nom, prenom, dateNaiss, poste));
				else if(this.typeObj == AudioLivre.class)
					Bibliotheque.addMedia(new AudioLivre(isbn, auteur, titre, dateParution, nbPages, null));
				else if(this.typeObj == Cd.class)
					Bibliotheque.addMedia(new Cd(isbn, auteur, titre, dateParution, listPistes));
				else if(this.typeObj == CoffretDvd.class)
					Bibliotheque.addMedia(new CoffretDvd(isbn, auteur, titre, dateParution, listDvds));
				else if(this.typeObj == Dvd.class)
					Bibliotheque.addMedia(new Dvd(isbn, auteur, titre, dateParution, duree));
				else if(this.typeObj == Livre.class)
					Bibliotheque.addMedia(new Livre(isbn, auteur, titre, dateParution, nbPages));
				else if(this.typeObj == Magazine.class)
					Bibliotheque.addMedia(new Magazine(isbn, auteur, titre, dateParution, nbPages, modeParution));
				
				retStatus = BiblioDialog.RET_OK;
				
				dispose(); // On ferme la fenetre
			}
		}
		else if(e.getSource() == btnAjoutDvdOuPiste) {
			if(this.typeObj == CoffretDvd.class) {
				AjoutDialog frameDvd = new AjoutDialog("Ajouter des DVD au coffret", Dvd.class);
				frameDvd.setVisible(true);
				
				if(frameDvd.getReturnStatus() == BiblioDialog.RET_OK) {
					
					// On créé l'objet et on le stock dans l'ArrayList
					String isbn = frameDvd.getForm().getFieldText(0);
					String auteur = frameDvd.getForm().getFieldText(1);
					String titre = frameDvd.getForm().getFieldText(2);
					Date dateParution = null;
					try {
						dateParution = Bibliotheque.stringToDate(frameDvd.getForm().getFieldText(3));
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					int duree = Integer.parseInt(frameDvd.getForm().getFieldText(4));

					Dvd d = new Dvd(isbn, auteur, titre, dateParution, duree);
					listDvds.add(d);
					
					// MAJ de la JList
					super.listModel.addElement(d.getTitre());

					pack();
				}
				
			}
			else if(this.typeObj == Cd.class) {
				AjoutDialog framePiste = new AjoutDialog("Ajouter des pistes au CD", Piste.class);
				framePiste.setVisible(true);
				
				if(framePiste.getReturnStatus() == BiblioDialog.RET_OK) {
					
					// On créé l'objet et on le stock dans l'ArrayList
					int numero = Integer.parseInt(framePiste.getForm().getFieldText(0));
					String titre = framePiste.getForm().getFieldText(1);
					int duree = Integer.parseInt(framePiste.getForm().getFieldText(2));
				
					Piste p = new Piste(numero, titre, duree);
					listPistes.add(p);
					for(Piste p2 : listPistes)
						System.out.println(p2.getTitre());
					// MAJ de la JList
					super.listModel.addElement(p.getNumero()+" - "+p.getTitre());

					pack();
				}
			}
			else {
				throw new IllegalArgumentException("La classe doit être de type CoffretDvd ou Cd");
			}
		
		}
	}
}
