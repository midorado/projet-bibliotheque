package vue;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
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
import modèle.Piste;

import controleur.Bibliotheque;

public class AjoutDialog extends JDialog implements ActionListener {
	
	private int retStatus = -1;
	private JPanel container;
	private JPanel panelButton;
	private JButton btnAjouter;
	private JButton btnAnnuler;
	private JButton btnAjoutDvdOuPiste; // Pour les CD ou les DVD
	private JLabel lblListeDvdOuPiste; // idem
	private List<Dvd> listDvds;
	private List<Piste> listPistes;
	private TextForm form;
	private String[] labels;
	private Class<?> typeObj;
	private boolean save = true;
	private Object objectToEdit = null;

	/**
	 * Premier constructeur destiné à l'ajout d'un nouveau média ou membre
	 * @param titreFrame
	 * @param typeObj
	 * @param parent
	 */
	public AjoutDialog(String titreFrame, Class<?> typeObj, JDialog parent) {

		super(parent, titreFrame, true);
		
		this.typeObj = typeObj;
		
		buildInterface();
		
		buildEvents();
		
		listDvds = new ArrayList<Dvd>();
		listPistes = new ArrayList<Piste>();
	}
	
	/**
	 * Deuxième constructeur destiné à la modification d'un media ou d'un membre déjà existant
	 * @param titreFrame
	 * @param typeObj
	 * @param parent
	 * @param objectToEdit
	 */
	public AjoutDialog(String titreFrame, Class<?> typeObj, JDialog parent, Object objectToEdit) {
		this(titreFrame, typeObj, parent);
		
		this.objectToEdit = objectToEdit;

		if(objectToEdit instanceof Abonne) {
			Abonne o = (Abonne) objectToEdit;
			form.presetFieldValues(new String[]{String.valueOf(o.getIdentifiant()), o.getNom(), o.getPrenom(), o.getStringDateNaissance()});
		}
		else if(objectToEdit instanceof Personnel) {
			Personnel o = (Personnel) objectToEdit;
			form.presetFieldValues(new String[]{String.valueOf(o.getIdentifiant()), o.getNom(), o.getPrenom(), o.getStringDateNaissance(), o.getPoste()});
		}
		else if(objectToEdit instanceof AudioLivre) {
			AudioLivre o = (AudioLivre) objectToEdit;
			form.presetFieldValues(new String[]{o.getIsbn(), o.getAuteur(), o.getTitre(), o.getStringDateParution(), String.valueOf(o.getNbPages())});
		}
		else if(objectToEdit instanceof Cd) {
			Cd o = (Cd) objectToEdit;
			form.presetFieldValues(new String[]{o.getIsbn(), o.getAuteur(), o.getTitre(), o.getStringDateParution()});
			listPistes = o.getPistes();
		}
		else if(objectToEdit instanceof CoffretDvd) {
			CoffretDvd o = (CoffretDvd) objectToEdit;
			form.presetFieldValues(new String[]{o.getIsbn(), o.getAuteur(), o.getTitre(), o.getStringDateParution()});
			listDvds = o.getDvds();
		}
		else if(objectToEdit instanceof Dvd) {
			Dvd o = (Dvd) objectToEdit;
			form.presetFieldValues(new String[]{o.getIsbn(), o.getAuteur(), o.getTitre(), o.getStringDateParution()});
		}
		else if(objectToEdit instanceof Livre) {
			Livre o = (Livre) objectToEdit;
			form.presetFieldValues(new String[]{o.getIsbn(), o.getAuteur(), o.getTitre(), o.getStringDateParution(), String.valueOf(o.getNbPages())});
		}
		else if(objectToEdit instanceof Magazine) {
			Magazine o = (Magazine) objectToEdit;
			form.presetFieldValues(new String[]{o.getIsbn(), o.getAuteur(), o.getTitre(), o.getStringDateParution(), String.valueOf(o.getNbPages())});
		}
	}

	private void buildInterface() {
	//	setSize(400, 350);
		setLocationRelativeTo(null);
	//	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		
		container = new JPanel();
		container.setLayout(new BorderLayout());
		
		// Boutons
		panelButton = new JPanel();
		btnAjouter = new JButton("Valider");
		btnAnnuler = new JButton("Annuler");
		
		panelButton.add(btnAjouter);
		panelButton.add(btnAnnuler);
		
		container.add(panelButton, BorderLayout.SOUTH);
		
		// Récupération des labels en fonction du type d'objet
		labels = Bibliotheque.getLabelValues(typeObj, true);

		form = new TextForm(labels);
		
		container.add(form, BorderLayout.NORTH);
		

		// Bouton ajouter Dvd ou ajouter Piste et label pour afficher la liste
		this.btnAjoutDvdOuPiste = new JButton();
		if(typeObj == CoffretDvd.class || typeObj == Cd.class) {
	
			JPanel panAjoutDvdOuPiste = new JPanel(new BorderLayout());
			
			this.btnAjoutDvdOuPiste = new JButton();
			this.lblListeDvdOuPiste = new JLabel();
			
			panAjoutDvdOuPiste.add(lblListeDvdOuPiste, BorderLayout.NORTH);
			panAjoutDvdOuPiste.add(btnAjoutDvdOuPiste, BorderLayout.SOUTH);
		
			container.add(panAjoutDvdOuPiste);			
			
			if(typeObj == CoffretDvd.class) {
				this.btnAjoutDvdOuPiste.setText("Ajouter des DVD au coffret");
				this.lblListeDvdOuPiste.setText("<html>DVD du coffret :");
			}
			else if(typeObj == Cd.class) {
				this.btnAjoutDvdOuPiste.setText("Ajout des pistes au CD");
				this.lblListeDvdOuPiste.setText("<html>Pistes du CD :");
				
			}
		}
		
		setContentPane(container);
		
		pack();
	}
	
	private void buildEvents() {
		btnAjouter.addActionListener(this);
		btnAnnuler.addActionListener(this);
		btnAjoutDvdOuPiste.addActionListener(this);
	}
	
	
	public int getReturnStatus() {
		return retStatus;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnAnnuler) {
			this.retStatus = BiblioDialog.RET_CANCEL;
			setVisible(false);
			dispose();
		}
		else if(e.getSource() == btnAjouter) {
			boolean err = false;
			
			// Déclaration des variables
			int id = 0, nbPages = 0, duree = 0, numero = 0;
			String nom = null, prenom = null, poste = null, isbn = null, auteur = null, titre = null, modeParution = null;
			Date dateNaiss = null, dateParution = null;
			
			if(this.typeObj == Abonne.class || this.typeObj == Personnel.class) {
				// Valeurs communes aux membres
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
			else if(objectToEdit == null) { // Ajout de l'objet dans la base
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
			else { // L'objet n'est pas null, dans ce cas il s'agit d'une modification
				Bibliotheque.updateObject(objectToEdit);
				
				retStatus = BiblioDialog.RET_OK;
				
				dispose(); // On ferme la fenetre
			}
		
		}
		else if(e.getSource() == btnAjoutDvdOuPiste) {
			if(this.typeObj == CoffretDvd.class) {
				AjoutDialog frameDvd = new AjoutDialog("Ajouter des DVD au coffret", Dvd.class, this);
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
					
					// MAJ de l'UI
					this.lblListeDvdOuPiste.setText(this.lblListeDvdOuPiste.getText()+"<br /> - "+d.getTitre());

					pack();
				}
				
			}
			else if(this.typeObj == Cd.class) {
				AjoutDialog framePiste = new AjoutDialog("Ajouter des pistes au CD", Piste.class, this);
				framePiste.setVisible(true);
				
				if(framePiste.getReturnStatus() == BiblioDialog.RET_OK) {
					
					// On créé l'objet et on le stock dans l'ArrayList
					int numero = Integer.parseInt(framePiste.getForm().getFieldText(0));
					String titre = framePiste.getForm().getFieldText(1);
					int duree = Integer.parseInt(framePiste.getForm().getFieldText(2));
				
					Piste p = new Piste(numero, titre, duree);
					listPistes.add(p);
					
					// MAJ de l'UI
					this.lblListeDvdOuPiste.setText(this.lblListeDvdOuPiste.getText()+"<br /> - "+p.getNumero()+" "+p.getTitre());

					pack();
				}
			}
			else {
				throw new IllegalArgumentException("La classe doit être de type CoffretDvd ou Cd");
			}
		}
	}
	
	public void dontSave() {
		this.save = false;
	}

	public TextForm getForm() {
		return form;
	}

	public void setForm(TextForm form) {
		this.form = form;
	}
	
	public Class<?> getTypeObj() {
		return typeObj;
	}

	public void setTypeObj(Class<?> typeObj) {
		this.typeObj = typeObj;
	}

}