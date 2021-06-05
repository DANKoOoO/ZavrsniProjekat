package rs.ac.bg.fon.ai.ZavrsniProjekat.Domen;

import java.util.ArrayList;
import java.util.Calendar;

import javax.management.RuntimeErrorException;

public class Film {
	private int filmID;
	private String naziv;
	private String reziser;
	private int godina;
	
	public int getFilmID() {
		return filmID;
	}
	public void setFilmID(int filmID) {
		if(filmID < 0) {
			throw new RuntimeException("ID mora biti pozitivan ceo broj");
		}
		this.filmID = filmID;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		if(naziv == null) {
			throw new NullPointerException("Naziv filma ne sme bit null");
		}
		if(naziv.length() < 1) {
			throw new RuntimeException("Naziv filma mora sadrzati bar 1 karakter");
		}
		this.naziv = naziv;
	}
	public String getReziser() {
		return reziser;
	}
	public void setReziser(String reziser) {
		if(reziser == null) {
			throw new NullPointerException("Ime rezisera ne sme biti null");
		}
		if((reziser.split(" ")).length < 2) {
			throw new RuntimeException("Reziser mora imati barem 2 reci odvojene praznim poljem (ime i prezime)");
		}
		char[] chars = reziser.toCharArray();
		for (char c : chars) {
			if(Character.isDigit(c)) {
				throw new RuntimeException("Ime rezisera ne sme sadrzati cifre");
			}
		}
		this.reziser = reziser;
	}	
	public int getGodina() {
		return godina;
	}
	public void setGodina(int godina) {
		if(godina < 1900 || godina > Calendar.getInstance().get(Calendar.YEAR)) {
			throw new RuntimeException("Godina kada je film snimljen mora biti izmedju 1900 i trenutne godine");
		}
		this.godina = godina;
	}
	@Override
	public String toString() {		
		return "Film: " + naziv + ", reziser:" + reziser + ", godina: " + godina + "\n";
	}
	public Film(int filmID, String naziv, String reziser, int godina) {
		super();
		setFilmID(filmID);
		setNaziv(naziv);
		setReziser(reziser);
		setGodina(godina);
	}
	public Film() {
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
		Film fl = (Film) obj;
		if(naziv == null) {
			if(fl.naziv != null) {
				return false;
			}
			else if(!naziv.equals(fl.naziv)) {
				return false;
			}
		}
		if(reziser == null) {
			if(fl.reziser != null) {
				return false;
			}
			else if(!reziser.equals(fl.reziser)) {
				return false;
			}
		}
		if(filmID != fl.filmID) {
			return false;
		}
		if(godina != fl.godina) {
			return false;
		}	
		return true;
	}
	
	
}
