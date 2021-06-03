package rs.ac.bg.fon.ai.ZavrsniProjekat.Domen;

import java.util.ArrayList;

public class Film {
	private int filmID;
	private String naziv;
	private String reziser;
	private int godina;
	
	public int getFilmID() {
		return filmID;
	}
	public void setFilmID(int filmID) {
		this.filmID = filmID;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getReziser() {
		return reziser;
	}
	public void setReziser(String reziser) {
		this.reziser = reziser;
	}	
	public int getGodina() {
		return godina;
	}
	public void setGodina(int godina) {
		this.godina = godina;
	}
	@Override
	public String toString() {		
		return "Film: " + naziv + ", reziser:" + reziser + ", godina: " + godina + "\n";
	}
	public Film(int filmID, String naziv, String reziser, int godina) {
		super();
		this.filmID = filmID;
		this.naziv = naziv;
		this.reziser = reziser;
		this.godina = godina;
	}
	
	
}
