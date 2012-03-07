package vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Date;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import controleur.Bibliotheque;

import modèle.Abonne;
import modèle.AudioLivre;
import modèle.Cd;
import modèle.CoffretDvd;
import modèle.Dvd;
import modèle.Livre;
import modèle.Magazine;
import modèle.Media;
import modèle.Personnel;
import modèle.Piste;

public class EditDialog extends BiblioDialog implements ActionListener {

	private Object objectToEdit;
	
	public EditDialog(String titreFrame, Object objectToEdit) {
		super(titreFrame, null, objectToEdit.getClass());

		this.objectToEdit = objectToEdit;
		
		// On rend non éditable l'ISBN
		if(this.objectToEdit instanceof Media)
			form.getFields()[0].setEditable(false);
		
		// Préremplissage des champs en fonction du type d'objet
		if(this.objectToEdit instanceof Abonne) {
			Abonne o = (Abonne) this.objectToEdit;
			form.presetFieldValues(new String[]{o.getNom(), o.getPrenom(), o.getStringDateNaissance()});
		}
		else if(this.objectToEdit instanceof Personnel) {
			Personnel o = (Personnel) this.objectToEdit;
			form.presetFieldValues(new String[]{o.getNom(), o.getPrenom(), o.getStringDateNaissance(), o.getPoste()});
		}
		else if(this.objectToEdit instanceof AudioLivre) {
			AudioLivre o = (AudioLivre) this.objectToEdit;
			form.presetFieldValues(new String[]{o.getIsbn(), o.getAuteur(), o.getTitre(), o.getStringDateParution(), String.valueOf(o.getNbPages())});
		}
		else if(this.objectToEdit instanceof Cd) {
			Cd o = (Cd) this.objectToEdit;
			form.presetFieldValues(new String[]{o.getIsbn(), o.getAuteur(), o.getTitre(), o.getStringDateParution()});
			listPistes = o.getPistes();
			super.refreshLstDvdOuPiste();
		}
		else if(this.objectToEdit instanceof CoffretDvd) {
			CoffretDvd o = (CoffretDvd) this.objectToEdit;
			form.presetFieldValues(new String[]{o.getIsbn(), o.getAuteur(), o.getTitre(), o.getStringDateParution()});
			listDvds = o.getDvds();
			super.refreshLstDvdOuPiste();
		}
		else if(this.objectToEdit instanceof Dvd) {
			Dvd o = (Dvd) this.objectToEdit;
			form.presetFieldValues(new String[]{o.getIsbn(), o.getAuteur(), o.getTitre(), o.getStringDateParution(), String.valueOf(o.getDuree())});
		}
		else if(this.objectToEdit instanceof Livre) {
			Livre o = (Livre) this.objectToEdit;
			form.presetFieldValues(new String[]{o.getIsbn(), o.getAuteur(), o.getTitre(), o.getStringDateParution(), String.valueOf(o.getNbPages())});
		}
		else if(this.objectToEdit instanceof Magazine) {
			Magazine o = (Magazine) this.objectToEdit;
			form.presetFieldValues(new String[]{o.getIsbn(), o.getAuteur(), o.getTitre(), o.getStringDateParution(), String.valueOf(o.getNbPages()), o.getModeParution()});
		}
		
		
		
		pack();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		
		if(e.getSource() == super.btnValider) {
			boolean err = false;
			
			// Déclaration des variables
			int nbPages = 0, duree = 0, numero = 0;
			String nom = null, prenom = null, poste = null, auteur = null, titre = null, modeParution = null;
			Date dateNaiss = null, dateParution = null;
			
			if(this.typeObj == Abonne.class || this.typeObj == Personnel.class) {
				// Valeurs communes aux membres
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

				auteur = form.getFieldText(1);
				titre = form.getFieldText(2);
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
				else if(typeObj == Dvd.class){
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
			else { // On met à jour les champs de l'objet et on le met à jour dans la base
				if(this.objectToEdit instanceof Abonne) {
					Abonne a = (Abonne) objectToEdit;
					a.setNom(nom);
					a.setPrenom(prenom);
					a.setDateNaissance(dateNaiss);
					Bibliotheque.updateObject(a);
				}
				else if(this.objectToEdit instanceof Personnel) {
					Personnel p = (Personnel) objectToEdit;
					p.setNom(nom);
					p.setPrenom(prenom);
					p.setDateNaissance(dateNaiss);
					p.setPoste(poste);
					Bibliotheque.updateObject(p);
				}
				else if(this.objectToEdit instanceof AudioLivre) {
					AudioLivre ad = (AudioLivre) objectToEdit;
					ad.setAuteur(auteur);
					ad.setDateParution(dateParution);
					ad.setNbPages(nbPages);
					ad.setPistes(listPistes);
					ad.setTitre(titre);
					Bibliotheque.updateObject(ad);
				}
				else if(this.objectToEdit instanceof Cd) {
					Cd cd = (Cd) objectToEdit;
					cd.setAuteur(auteur);
					cd.setDateParution(dateParution);
					cd.setTitre(titre);
					cd.setPistes(listPistes);
					Bibliotheque.updateObject(cd);
				}
				else if(this.objectToEdit instanceof CoffretDvd) {
					CoffretDvd cof = (CoffretDvd) objectToEdit;
					cof.setAuteur(auteur);
					cof.setDateParution(dateParution);
					cof.setDvds(listDvds);
					cof.setTitre(titre);
					cof.setDvds(listDvds);
					Bibliotheque.updateObject(cof);
				}
				else if(this.objectToEdit instanceof Dvd) {
					Dvd d = (Dvd) objectToEdit;
					d.setAuteur(auteur);
					d.setDateParution(dateParution);
					d.setDuree(duree);
					d.setTitre(titre);
					Bibliotheque.updateObject(d);
				}
				else if(this.objectToEdit instanceof Livre) {
					Livre l = (Livre) objectToEdit;
					l.setAuteur(auteur);
					l.setDateParution(dateParution);
					l.setNbPages(nbPages);
					l.setTitre(titre);
					Bibliotheque.updateObject(l);
				}
				else if(this.objectToEdit instanceof Magazine) {
					Magazine m = (Magazine) objectToEdit;
					m.setAuteur(auteur);
					m.setDateParution(dateParution);
					m.setModeParution(modeParution);
					m.setNbPages(nbPages);
					m.setTitre(titre);
					Bibliotheque.updateObject(m);
				}

				retStatus = BiblioDialog.RET_OK;
				
				dispose(); // On ferme la fenetre
			}
		
		}
		// Ajout des pistes dans un CD ou ajout de DVD dans un coffret DVD
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
					super.refreshLstDvdOuPiste();

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
					
					// MAJ de la JList
					super.refreshLstDvdOuPiste();
					
					pack();
				}
			}
			else {
				throw new IllegalArgumentException("La classe doit être de type CoffretDvd ou Cd");
			}
		}
	}

}
