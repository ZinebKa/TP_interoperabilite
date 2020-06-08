package fr.ensim.interop.eval.exo1;

import java.util.Date;
import java.util.Locale;

public class Avis {
	// Données en entrée
	private String id;
	private Date date;
	private String refProd;
	private int note;
	private String commentaire;
	private String[] countryCodes;
	private String refFourn;

	
	public Avis( String refProd, int note, String commentaire,String refFourn) {
		super();
		this.refProd = refProd;
		this.note = note;
		this.commentaire = commentaire;
		this.countryCodes = Locale.getISOCountries();
		this.refFourn = refFourn;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getRefProd() {
		return refProd;
	}
	public void setRefProd(String refProd) throws IllegalArgumentException {
		if(!this.refProd.matches("[a-zA-Z0-9]+") || this.refProd.length() > 20)
			throw new IllegalArgumentException("Erreur format référence");
		this.refProd = refProd;
	}
	public int getNote() {
		return note;
	}
	public void setNote(int note) throws IllegalArgumentException {
		if(this.note<0 || this.note >10)
			throw new IllegalArgumentException("La note doit être comprise entre 0 et 10");
		this.note = note;
	}
	public String getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(String commentaire) throws IllegalArgumentException {
		if(this.commentaire.length()>300)
			throw new IllegalArgumentException("Longueur commentaire dépassé");
		this.commentaire = commentaire;
	}
	public String[] getCountryCodes() {
		return countryCodes;
	}
	public void setCountryCodes(String[] countryCodes) {
		this.countryCodes = countryCodes;
	}
	public String getRefFourn() {
		return refFourn;
	}
	public void setRefFourn(String refFourn) throws IllegalArgumentException {
		
		if(!this.refFourn.matches("[a-zA-Z0-9]+") || this.refFourn.length() > 15)
			throw new IllegalArgumentException("Erreur format référence");
	
		this.refFourn = refFourn;
	}
	
	
}
