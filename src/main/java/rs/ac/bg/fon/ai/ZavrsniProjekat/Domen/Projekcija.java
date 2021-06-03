package rs.ac.bg.fon.ai.ZavrsniProjekat.Domen;

import java.sql.Timestamp;

public class Projekcija {
	private int projekcijaID;
	private int festivalID;
	private Timestamp datumVremeProjekcije;
	private int filmID;
	
	public int getProjekcijaID() {
		return projekcijaID;
	}
	public void setProjekcijaID(int projekcijaID) {
		this.projekcijaID = projekcijaID;
	}
	public int getFestivalID() {
		return festivalID;
	}
	public void setFestivalID(int festivalID) {
		this.festivalID = festivalID;
	}
	public Timestamp getDatumVremeProjekcije() {
		return datumVremeProjekcije;
	}
	public void setDatumVremeProjekcije(Timestamp datumVremeProjekcije) {
		this.datumVremeProjekcije = datumVremeProjekcije;
	}
	public int getFilmID() {
		return filmID;
	}
	public void setFilmID(int filmID) {
		this.filmID = filmID;
	}
	public Projekcija(int projekcijaID, int festivalID, Timestamp timestamp, int filmID) {
		super();
		this.projekcijaID = projekcijaID;
		this.festivalID = festivalID;
		this.datumVremeProjekcije = timestamp;
		this.filmID = filmID;
	}
	
	
}
