package rs.ac.bg.fon.ai.ZavrsniProjekat.Domen;

import java.util.ArrayList;
import java.util.Calendar;

import javax.management.RuntimeErrorException;

/**
 * Klasa koja predstavlja Film.
 * 
 * Film ima id filma i godinu snimanja filma kao Integer vrednosti.
 * Film ima naziv filma i ime prezime rezisera kao String vrednosti.
 * 
 * @author danko
 * @version 0.0.0.1
 */
public class Film {
	/**
	 * Id filma kao Integer.
	 */
	private int filmID;
	/**
	 * Naziv filma kao String.
	 */
	private String naziv;
	/**
	 * Ime i prezime rezisera kao String.
	 */
	private String reziser;
	/**
	 * Godinu snimanja filma kao Integer. 
	 */
	private int godina;
	/**
	 * Vraca id filma.
	 * 
	 * @return Id filma kao Integer.
	 */
	public int getFilmID() {
		return filmID;
	}
	/**
	 * Postavlja id filma na novu vrednost.
	 * 
	 * @param filmID Id filma kao Integer.
	 * 
	 * @throws java.lang.RuntimeException Ako je unet negativan broj.
	 */
	public void setFilmID(int filmID) {
		if(filmID < 0) {
			throw new RuntimeException("ID mora biti pozitivan ceo broj");
		}
		this.filmID = filmID;
	}
	/**
	 * Vraca naziv filma.
	 * 
	 * @return Naziv filma kao String.
	 */
	public String getNaziv() {
		return naziv;
	}
	
	/**
	 * Postavlja naziv filma na novu vrednost.
	 * 
	 * @param naziv Naziv kao String.
	 * 
	 * @throws java.lang.NullPointerException Ako je uneta vrednost parametra naziv null.
	 * @throws java.lang.RuntimeException Ako je unet prazan String.
	 */
	public void setNaziv(String naziv) {
		if(naziv == null) {
			throw new NullPointerException("Naziv filma ne sme bit null");
		}
		if(naziv.length() < 1) {
			throw new RuntimeException("Naziv filma mora sadrzati bar 1 karakter");
		}
		this.naziv = naziv;
	}
	/**
	 * Vraca ime i prezime rezisera.
	 * 
	 * @return Ime prezime rezisera kao String.
	 */
	public String getReziser() {
		return reziser;
	}
	/**
	 * Postavlja ime i prezime rezisera na novu vrednost.
	 * 
	 * @param reziser Ime i prezime rezisera kao String.
	 * 
	 * @throws java.lang.NullPointerException Ako je uneta vrednost parametra reziser null.
	 * @throws java.lang.RuntimeException Ako je unet String bez razmaka.
	 * @throws java.lang.RuntimeException Ako je unet String koji sadrzi cifre.
	 */
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
	/**
	 * Vraca godinu snimanja filma.
	 * 
	 * @return Godina snimanja filma kao Integer.
	 */
	public int getGodina() {
		return godina;
	}
	/**
	 * Postavlja godinu snimanja filma na novu vrednost.
	 * 
	 * @param godina Godina snimanja filma kao Integer.
	 * 
	 * @throws java.lang.RuntimeException Ako je unet broj manji od 1900 ili veci od trenutne godine.
	 */
	public void setGodina(int godina) {
		if(godina < 1900 || godina > Calendar.getInstance().get(Calendar.YEAR)) {
			throw new RuntimeException("Godina kada je film snimljen mora biti izmedju 1900 i trenutne godine");
		}
		this.godina = godina;
	}
	/**
	 * Vraca formatiran string koji sadrzi naziv filma, ime i prezime rezisera i godinu snimanja filma.
	 * 
	 * @return naziv filma, ime i prezime rezisera i godinu kao String.
	 */
	@Override
	public String toString() {		
		return "Film: " + naziv + ", reziser:" + reziser + ", godina: " + godina + "\n";
	}
	/**
	 * Konstruktor koji inicijalizuje objekat i postavlja vrednosti 
	 * za id filma, naziv filma, ime i prezime rezisera i godinu.
	 * 
	 * @param filmID Id filma kao Integer.
	 * @param naziv Naziv filma kao String.
	 * @param reziser Ime i prezime rezisera kao String.
	 * @param godina Godinu snimanja filma kao Integer.
	 */
	public Film(int filmID, String naziv, String reziser, int godina) {
		super();
		setFilmID(filmID);
		setNaziv(naziv);
		setReziser(reziser);
		setGodina(godina);
	}
	/**
	 * Konstruktor koji samo inicijalizuje objekat.
	 */
	public Film() {
		super();
	}
	/**
	 * Poredi dva filma i vraca true ako su isti, a false ako nisu.
	 * 
	 * Filmovi se porede po id-ju, nazivu, imenu i prezimenu rezisera, id-ju filma i godini snimanja.
	 * 
	 * @return
	 * <ul>
	 * 		<li> true ako su oba objekta klase Film i imaju iste id-jeve, nazive, imena i prezimena rezisera, id-jeve filma i godine snimanja.</li>
	 * 		<li> false u svim ostalim slucajevima</li>
	 * </ul>
	 */
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
		}
		else if(!naziv.equals(fl.naziv)) {
			return false;
		}
		if(reziser == null) {
			if(fl.reziser != null) {
				return false;
			}	
		}
		else if(!reziser.equals(fl.reziser)) {
			return false;
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
