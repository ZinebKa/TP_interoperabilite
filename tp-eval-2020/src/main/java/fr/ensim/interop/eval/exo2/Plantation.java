package fr.ensim.interop.eval.exo2;

import java.util.Date;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

@Entity
public class Plantation {

	private int id;
	
	@NotEmpty
	//private Date date;
	private String date;
	@NotEmpty
	private String parcelle;
	
	@NotEmpty
	private String nomPlante;
	
	@NotEmpty
	private String famillePlante;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getParcelle() {
		return parcelle;
	}

	public void setParcelle(String parcelle) {
		this.parcelle = parcelle;
	}

	public String getNomPlante() {
		return nomPlante;
	}

	public void setNomPlante(String nomPlante) {
		this.nomPlante = nomPlante;
	}

	public String getFamillePlante() {
		return famillePlante;
	}

	public void setFamillePlante(String famillePlante) {
		this.famillePlante = famillePlante;
	}

	public Plantation(int id, @NotEmpty String date, @NotEmpty String parcelle, @NotEmpty String nomPlante,
			@NotEmpty String famillePlante) {
		super();
		this.id = id;
		this.date = date;
		this.parcelle = parcelle;
		this.nomPlante = nomPlante;
		this.famillePlante = famillePlante;
	}

	public Plantation() {
		super();
	}

	@Override
	public String toString() {
		return "Plantation [id=" + id + ", date=" + date + ", parcelle=" + parcelle + ", nomPlante=" + nomPlante
				+ ", famillePlante=" + famillePlante + "]";
	}
	
	
}
