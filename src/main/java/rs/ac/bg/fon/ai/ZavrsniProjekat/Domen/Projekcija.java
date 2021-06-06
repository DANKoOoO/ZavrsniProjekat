package rs.ac.bg.fon.ai.ZavrsniProjekat.Domen;

import java.sql.Timestamp;

/**
 * Klasa koja predstavlja Projekciju.
 * 
 * Projekcija ima id projekcije, id festivala i id filma kao Integer vrednosti.
 * Projekcija ima datum i vreme projekcije kao java.sql.Timestamp vrednost.
 * 
 * @author danko
 * @version 0.0.0.1
 */
public class Projekcija {
	/**
	 * Id projekcije kao Integer.
	 */
	private int projekcijaID;
	/**
	 * Id festivala kao Integer.
	 */
	private int festivalID;
	/**
	 * Datum i vreme projekcije kao java.sql.Timestamp.
	 */
	private Timestamp datumVremeProjekcije;
	/**
	 * Id filma kao Integer.
	 */
	private int filmID;
	
	/**
	 * Vraca id projekcije.
	 * 
	 * @return Id projekcije kao Integer.
	 */
	public int getProjekcijaID() {
		return projekcijaID;
	}
	/**
	 * Postavlja id projekcije na novu vrednost.
	 * 
	 * @param projekcijaID Id projekcije kao Integer.
	 * 
	 * @throws java.lang.RuntimeException Ako je unet negativan broj.
	 */
	public void setProjekcijaID(int projekcijaID) {
		if(projekcijaID < 0) {
			throw new RuntimeException("ID mora biti pozitivan ceo broj");
		}
		this.projekcijaID = projekcijaID;
	}
	/**
	 * Vraca id festivala.
	 * 
	 * @return Id festivala kao Integer.
	 */
	public int getFestivalID() {
		return festivalID;
	}
	/**
	 * Postavlja id festivala na novu vrednost.
	 * 
	 * @param festivalID Id festivala kao Integer.
	 * 
	 * @throws java.lang.RuntimeException Ako je unet negativan broj.
	 */
	public void setFestivalID(int festivalID) {
		if(festivalID < 0) {
			throw new RuntimeException("ID mora biti pozitivan ceo broj");
		}
		this.festivalID = festivalID;
	}
	/**
	 * Vraca datum i vreme projekcije.
	 * 
	 * @return Datum i vreme projekcije kao java.sql.Timestamp.
	 */
	public Timestamp getDatumVremeProjekcije() {
		return datumVremeProjekcije;
	}
	/**
	 * Postavlja datum i vreme projekcije na novu vrednost.
	 * 
	 * @param datumVremeProjekcije Datum i vreme projekcije kao java.sql.Timestamp.
	 * 
	 * @throws java.lang.NullPointerException Ako je uneta vrednost parametra datumVremeProjekcije null.
	 * @throws java.lang.RuntimeException Ako je uneta vrednost datuma pre danasnjeg.
	 */
	public void setDatumVremeProjekcije(Timestamp datumVremeProjekcije) {
		if(datumVremeProjekcije == null) {
			throw new NullPointerException("Datum i vreme projekcije ne moze biti null");
		}
		if(datumVremeProjekcije.before(new Timestamp(System.currentTimeMillis()))) {
			throw new RuntimeException("Datum i vreme projekcije moraju biti u buducnosti");
		}
		this.datumVremeProjekcije = datumVremeProjekcije;
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
	 * za id projekcije, id festivala, datum i vreme projekcije, id filma.
	 * 
	 * @param projekcijaID Id projekcije kao Integer.
	 * @param festivalID Id festivala kao Integer.
	 * @param timestamp Datum i vreme projekcije kao java.sql.Timestamp.
	 * @param filmID Id filma kao Integer.
	 */
	public Projekcija(int projekcijaID, int festivalID, Timestamp timestamp, int filmID) {
		super();
		setProjekcijaID(projekcijaID);
		setFestivalID(festivalID);
		setDatumVremeProjekcije(timestamp);
		setFilmID(filmID);
	}
	/**
	 * Konstruktor koji samo inicijalizuje objekat.
	 */
	public Projekcija() {
		super();
	}
	/**
	 * Poredi dve projekcije i vraca true ako su isti, a false ako nisu.
	 * 
	 * Projekcije se porede po id-ju,id-ju festivala, id-ju filma i datumu i vremenu projekcije.
	 * 
	 * @return
	 * <ul>
	 * 		<li> true ako su oba objekta klase Projekcija i imaju iste id-jeve, id-jeve festivala, id-jeve filmova i datume i vremena projekcije.</li>
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
