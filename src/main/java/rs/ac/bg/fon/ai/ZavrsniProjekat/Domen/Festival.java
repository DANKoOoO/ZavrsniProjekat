package rs.ac.bg.fon.ai.ZavrsniProjekat.Domen;

import java.sql.Date;

import javax.management.RuntimeErrorException;

public class Festival {
	private int festivalID;
	private String naziv;
	private Date datumOd;
	private Date datumDo;
	private int gradID;
	
	public int getFestivalID() {
		return festivalID;
	}
	public void setFestivalID(int festivalID) {
		if(festivalID<0) {
			throw new RuntimeException("ID mora biti pozitivan ceo broj");
		}
		this.festivalID = festivalID;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		if(naziv == null) {
			throw new NullPointerException("Naziv festivala ne sme biti null");
		}
		if(naziv.length()<1) {
			throw new RuntimeException("Naziv festivala mora sadrzati barem jedan karakter");
		}
		this.naziv = naziv;
	}
	public Date getDatumOd() {
		return datumOd;
	}
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
	public Date getDatumDo() {
		return datumDo;
	}
	public void setDatumDo(Date datumDo) {
		if(datumDo == null) {
			throw new NullPointerException("Datum zavrsetka ne sme biti null");
		}
		if(datumDo.before(this.getDatumOd())) {
			throw new RuntimeException("Datum zavrsetka mora biti posle datuma pocetka");
		}
		this.datumDo = datumDo;
	}
	public int getGradID() {
		return gradID;
	}
	public void setGradID(int gradID) {
		if(gradID<0) {
			throw new RuntimeException("ID mora biti pozitivan ceo broj");
		}
		this.gradID = gradID;
	}
	
	
	@Override
	public String toString() {
		return "Festival: " + naziv + "[datum pocetka: " + datumOd + ", datum zavrsetka: " + datumDo + "], gradID=" + gradID;
	}
	public Festival(int festivalID, String naziv, Date date, Date date2, int gradID) {
		super();
		this.festivalID = festivalID;
		setNaziv(naziv);
		setDatumOd(date);		
		setDatumDo(date2);
		setGradID(gradID);
	}
	public Festival() {
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
