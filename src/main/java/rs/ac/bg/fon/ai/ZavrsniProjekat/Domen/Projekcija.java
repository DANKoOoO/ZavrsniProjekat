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
		if(projekcijaID < 0) {
			throw new RuntimeException("ID mora biti pozitivan ceo broj");
		}
		this.projekcijaID = projekcijaID;
	}
	public int getFestivalID() {
		return festivalID;
	}
	public void setFestivalID(int festivalID) {
		if(festivalID < 0) {
			throw new RuntimeException("ID mora biti pozitivan ceo broj");
		}
		this.festivalID = festivalID;
	}
	public Timestamp getDatumVremeProjekcije() {
		return datumVremeProjekcije;
	}
	public void setDatumVremeProjekcije(Timestamp datumVremeProjekcije) {
		if(datumVremeProjekcije == null) {
			throw new NullPointerException("Datum i vreme projekcije ne moze biti null");
		}
		if(datumVremeProjekcije.before(new Timestamp(System.currentTimeMillis()))) {
			throw new RuntimeException("Datum i vreme projekcije moraju biti u buducnosti");
		}
		this.datumVremeProjekcije = datumVremeProjekcije;
	}
	public int getFilmID() {
		return filmID;
	}
	public void setFilmID(int filmID) {
		if(filmID < 0) {
			throw new RuntimeException("ID mora biti pozitivan ceo broj");
		}
		this.filmID = filmID;
	}
	public Projekcija(int projekcijaID, int festivalID, Timestamp timestamp, int filmID) {
		super();
		setProjekcijaID(projekcijaID);
		setFestivalID(festivalID);
		setDatumVremeProjekcije(timestamp);
		setFilmID(filmID);
	}
	public Projekcija() {
		super();
	}
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(obj == null) {
			return false;
		}
		if(getClass() != obj.getClass()) {
			return false;
		}
		Projekcija pr = (Projekcija) obj;
		if(datumVremeProjekcije == null) {
			if(pr.datumVremeProjekcije != null) {
				return false;
			}
		}
		else if(!datumVremeProjekcije.equals(pr.datumVremeProjekcije)) {
			return false;
		}
		if(filmID != pr.filmID) {
			return false;
		}
		if(projekcijaID != pr.projekcijaID) {
			return false;
		}	
		if(festivalID != pr.festivalID) {
			return false;
		}	
		return true;
	}
	
	
}
