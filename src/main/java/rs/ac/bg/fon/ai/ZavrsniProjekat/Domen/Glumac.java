package rs.ac.bg.fon.ai.ZavrsniProjekat.Domen;

import javax.management.RuntimeErrorException;

public class Glumac {
	private int glumacID;
	private String imePrezime;
	private int filmID;
	
	public int getGlumacID() {
		return glumacID;
	}
	
	public void setGlumacID(int glumacID) {
		if(glumacID < 0) {
			throw new RuntimeException("ID mora biti pozitivan ceo broj");
		}
		this.glumacID = glumacID;
	}
	
	public String getImePrezime() {
		return imePrezime;
	}
	
	public void setImePrezime(String imePrezime) {
		if(imePrezime == null) {
			throw new NullPointerException("Ime i prezime ne sme biti null");
		}
		if((imePrezime.split(" ")).length < 2) {
			throw new RuntimeException("Ime i prezime mora imati barem 2 reci odvojene praznim poljem");
		}
		char[] chars = imePrezime.toCharArray();
		for (char c : chars) {
			if(Character.isDigit(c)) {
				throw new RuntimeException("Ime i prezime ne sme sadrzati cifre");
			}
		}
		this.imePrezime = imePrezime;
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
	
	public Glumac(int glumacID, String imePrezime, int filmID) {
		super();
		setGlumacID(glumacID);
		setImePrezime(imePrezime);
		setFilmID(filmID);;
	}
	
	@Override
	public String toString() {
		return imePrezime;
	}

	public Glumac() {
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
		Glumac gl = (Glumac) obj;
		if(imePrezime == null) {
			if(gl.imePrezime != null) {
				return false;
			}
			else if(!imePrezime.equals(gl.imePrezime)) {
				return false;
			}
		}
		if(glumacID != gl.glumacID) {
			return false;
		}
		if(filmID != gl.filmID) {
			return false;
		}	
		return true;
	}
	
	
}
