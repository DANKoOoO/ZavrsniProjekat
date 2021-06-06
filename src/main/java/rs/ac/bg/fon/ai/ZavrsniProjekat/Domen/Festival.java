package rs.ac.bg.fon.ai.ZavrsniProjekat.Domen;

import java.sql.Date;

import javax.management.RuntimeErrorException;

/**
 * Klasa koja predstavlja Festival.
 * 
 * Festival ima id festivala i id grada kao Integer vrednosti.
 * Festival ima naziv festivala kao String vrednost.
 * Festival ima datum pocetka i datum zavrsetka kao java.sql.Date vrednosti.
 * 
 * @author danko
 * @version 0.0.0.1
 */
public class Festival {
	/**
	 * Id festivala kao Integer.
	 */
	private int festivalID;
	/**
	 * Naziv festivala kao String.
	 */
	private String naziv;
	/**
	 * Datum pocetka festivala kao String.
	 */
	private Date datumOd;
	/**
	 * Datum zavrsetka festivala kao String.
	 */
	private Date datumDo;
	/**
	 * Id grada kao Integer.
	 */
	private int gradID;
	
	/**
	 * Vraca Id festivala.
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
		if(festivalID<0) {
			throw new RuntimeException("ID mora biti pozitivan ceo broj");
		}
		this.festivalID = festivalID;
	}
	/**
	 * Vraca naziv festivala.
	 * 
	 * @return Naziv festivala kao String.
	 */
	public String getNaziv() {
		return naziv;
	}
	
	/**
	 * Postavlja naziv festivala na novu vrednost.
	 * 
	 * @param naziv Naziv festivala kao String.
	 * 
	 * @throws java.lang.NullPointerException Ako je uneta vrednost parametra naziv null.
	 * @throws java.lang.RuntimeException Ako je unet prazan String.
	 */
	public void setNaziv(String naziv) {
		if(naziv == null) {
			throw new NullPointerException("Naziv festivala ne sme biti null");
		}
		if(naziv.length()<1) {
			throw new RuntimeException("Naziv festivala mora sadrzati barem jedan karakter");
		}
		this.naziv = naziv;
	}
	/**
	 * Vraca datum pocetka festivala.
	 * 
	 * @return Datum pocetka festivala kao java.sql.Date.
	 */
	public Date getDatumOd() {
		return datumOd;
	}
	/**
	 * Postavlja datum pocetka festivala na novu vrednost.
	 * 
	 * @param datumOd Datum pocetka festivala kao java.sql.Date.
	 * 
	 * @throws java.lang.NullPointerException Ako je uneta vrednost parametra datumOd null.
	 * @throws java.lang.RuntimeException Ako je uneta vrednost datuma pre danasnjeg datuma.
	 */
	public void setDatumOd(Date datumOd) {
		Date danas = new Date(System.currentTimeMillis());
		
		if(datumOd == null) {
			throw new NullPointerException("Datum pocetka ne sme biti null");
		}
		if(datumOd.before(danas)) {
			throw new RuntimeException("Datum pocetka mora biti u buducnosti");
		}
		this.datumOd = datumOd;
	}
	/**
	 * Vraca datum zavrsetka festivala.
	 * 
	 * @return Datum zavrsetka festivala kao java.sql.Date.
	 */
	public Date getDatumDo() {
		return datumDo;
	}
	/**
	 * Postavlja datum zavrsetka festivala na novu vrednost.
	 * 
	 * @param datumDo Datum zavrsetka festivala kao java.sql.Date.
	 * 
	 * @throws java.lang.NullPointerException Ako je uneta vrednost parametra datumDo null.
	 * @throws java.lang.RuntimeException Ako je uneta vrednost datuma zavrsetka pre datuma pocetka festivala.
	 */
	public void setDatumDo(Date datumDo) {
		if(datumDo == null) {
			throw new NullPointerException("Datum zavrsetka ne sme biti null");
		}
		if(datumDo.before(this.getDatumOd())) {
			throw new RuntimeException("Datum zavrsetka mora biti posle datuma pocetka");
		}
		this.datumDo = datumDo;
	}
	/**
	 * Vraca id grada.
	 * 
	 * @return id grada kao Integer.
	 */
	public int getGradID() {
		return gradID;
	}
	/**
	 * Postavlja id grada na novu vrednost.
	 * 
	 * @param gradID Id grada kao Integer.
	 * 
	 * @throws java.lang.RuntimeException Ako je unet negativan broj.
	 */
	public void setGradID(int gradID) {
		if(gradID<0) {
			throw new RuntimeException("ID mora biti pozitivan ceo broj");
		}
		this.gradID = gradID;
	}
	
	/**
	 * Vraca formatiran String koji sadrzi naziv festivala, datum pocetka, datum zavrsetka i id grada.
	 *
	 * @return Naziv festivala, datum pocetka, datum zavrsetka i id grada kao String.
	 */
	@Override
	public String toString() {
		return "Festival: " + naziv + "[datum pocetka: " + datumOd + ", datum zavrsetka: " + datumDo + "], gradID=" + gradID;
	}
	/**
	 * Konstruktor koji inicijalizuje objekat i postavlja vrednosti 
	 * za id festivala, naziv, datum pocetka, datum zavrsetka i id grada.
	 * 
	 * @param festivalID Id festivala kao Integer.
	 * @param naziv Naziv festivala kao String.
	 * @param date Datum pocetka festivala kao java.sql.Date.
	 * @param date2 Datum zavrsetka festivala kao java.sql.Date.
	 * @param gradID Id grada kao Integer.
	 */
	public Festival(int festivalID, String naziv, Date date, Date date2, int gradID) {
		super();
		this.festivalID = festivalID;
		setNaziv(naziv);
		setDatumOd(date);		
		setDatumDo(date2);
		setGradID(gradID);
	}
	/**
	 * Konstruktor koji samo inicijalizuje objekat.
	 */
	public Festival() {
		super();
	}
	/**
	 * Poredi dva festivala i vraca true ako su isti, a false ako nisu.
	 * 
	 * Festivali se porede po id-ju, nazivu, datumu pocetka, datumu zavrsetka i id-ju grada.
	 * 
	 * @return
	 * <ul>
	 * 		<li> true ako su oba objekta klase Festival i imaju iste id-jeve, nazive, datume pocetka, datume zavrsetka, id-jeve grada.</li>
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
		
		Festival fe = (Festival) obj;
		
		if(datumOd == null) {
			if(fe.datumOd != null) {
				return false;
			}

		}
		else if(!datumOd.equals(fe.datumOd)) {
			return false;
		}
		
		if(datumDo == null) {
			if(fe.datumDo != null) {
				return false;
			}

		}
		else if(!datumDo.equals(fe.datumDo)) {
			return false;
		}
		
		if(naziv == null) {
			if(fe.naziv != null) {
				return false;
			}		
		}
		else if(!naziv.equals(fe.naziv)) {
			return false;
		}
		
		if(festivalID != fe.festivalID) {
			return false;
		}
		if(gradID != fe.gradID) {
			return false;
		}	

		return true;
	}
	
	
}
