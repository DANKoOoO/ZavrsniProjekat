package rs.ac.bg.fon.ai.ZavrsniProjekat.Domen;

import java.time.LocalDateTime;

public class Projekcija {
	private int projekcijaID;
	private int festivalID;
	private LocalDateTime datumVremeProjekcije;
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
	public LocalDateTime getDatumVremeProjekcije() {
		return datumVremeProjekcije;
	}
	public void setDatumVremeProjekcije(LocalDateTime datumVremeProjekcije) {
		this.datumVremeProjekcije = datumVremeProjekcije;
	}
	public int getFilmID() {
		return filmID;
	}
	public void setFilmID(int filmID) {
		this.filmID = filmID;
	}
	
	
}
