package rs.ac.bg.fon.ai.ZavrsniProjekat.Domen;

import javax.management.RuntimeErrorException;

/**
 * Klasa koja predstavlja Glumca.
 * 
 * Glumac ima id glumca i id filma kao Integer vrednosti.
 * Glumac ima ime i prezime kao String vrednost.
 * 
 * @author danko
 * @version 0.0.0.1
 */

public class Glumac {
	/**
	 * Id glumca kao Integer.
	 */
	private int glumacID;
	/**
	 * Ime i prezime glumca kao String.
	 */
	private String imePrezime;
	/**
	 * Id filma kao Integer.
	 */
	private int filmID;
	
	/**
	 * Vraca id glumca.
	 * 
	 * @return Id glumca kao Integer.
	 */
	public int getGlumacID() {
		return glumacID;
	}
	
	/**
	 * Postavlja id glumca na novu vrednost.
	 * 
	 * @param glumacID Id glumca kao Integer.
	 * 
	 * @throws java.lang.RuntimeException Ako je unet negativan broj.
	 */
	public void setGlumacID(int glumacID) {
		if(glumacID < 0) {
			throw new RuntimeException("ID mora biti pozitivan ceo broj");
		}
		this.glumacID = glumacID;
	}
	/**
	 * Vraca ime i prezime glumca.
	 * 
	 * @return Ime i prezime glumca kao String.
	 */
	public String getImePrezime() {
		return imePrezime;
	}
	/**
	 * Postavlja ime i prezime glumca na novu vrednost.
	 * 
	 * @param imePrezime Ime i prezime glumca kao String.
	 * 
	 * @throws java.lang.NullPointerException Ako je uneta vrednost parametra imePrezime null.
	 * @throws java.lang.RuntimeException Ako je unet String bez razmaka.
	 * @throws java.lang.RuntimeException Ako je unet String koji sadrzi cifre.
	 */
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
	 * Konstruktor koji inicijalizuje objekat i postavlja vrednosti 
	 * za id grada, ime i prezime glumca i id filma.
	 * 
	 * @param glumacID Id glumca kao Integer.
	 * @param imePrezime Ime i prezime glumca kao String.
	 * @param filmID Id filma kao Integer.
	 */
	public Glumac(int glumacID, String imePrezime, int filmID) {
		super();
		setGlumacID(glumacID);
		setImePrezime(imePrezime);
		setFilmID(filmID);;
	}
	
	/**
	 * Vraca formatiran String koji sadrzi samo ime i prezime glumca.
	 * 
	 * @return Ime i prezime glumca kao String.
	 */
	@Override
	public String toString() {
		return imePrezime;
	}

	/**
	 * Konstruktor koji samo inicijalizuje objekat.
	 */
	public Glumac() {
		super();
	}

	/**
	 * Poredi dva glumca i vraca true ako su isti, a false ako nisu.
	 * 
	 * Glumci se porede po id-ju, imenu i prezimenu i id-ju filma.
	 * 
	 * <ul>
	 * 		<li> true ako su oba objekta klase Glumac i imaju iste id-jeve, imena i prezimena i id-jeve filma.</li>
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
		Glumac gl = (Glumac) obj;
		if(imePrezime == null) {
			if(gl.imePrezime != null) {
				return false;
			}		
		}
		else if(!imePrezime.equals(gl.imePrezime)) {
			return false;
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
