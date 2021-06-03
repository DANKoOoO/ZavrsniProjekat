package rs.ac.bg.fon.ai.ZavrsniProjekat.Domen;

import java.sql.Date;

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
		this.festivalID = festivalID;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public Date getDatumOd() {
		return datumOd;
	}
	public void setDatumOd(Date datumOd) {
		this.datumOd = datumOd;
	}
	public Date getDatumDo() {
		return datumDo;
	}
	public void setDatumDo(Date datumDo) {
		this.datumDo = datumDo;
	}
	public int getGradID() {
		return gradID;
	}
	public void setGradID(int gradID) {
		this.gradID = gradID;
	}
	
	
	@Override
	public String toString() {
		return "Festival: " + naziv + "[datum pocetka: " + datumOd + ", datum zavrsetka: " + datumDo + "], gradID=" + gradID;
	}
	public Festival(int festivalID, String naziv, Date date, Date date2, int gradID) {
		super();
		this.festivalID = festivalID;
		this.naziv = naziv;
		this.datumOd = date;
		this.datumDo = date2;
		this.gradID = gradID;
	}
	
	
}
