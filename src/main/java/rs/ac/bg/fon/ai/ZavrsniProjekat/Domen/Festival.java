package rs.ac.bg.fon.ai.ZavrsniProjekat.Domen;

import java.time.LocalDate;

public class Festival {
	private int festivalID;
	private String naziv;
	private LocalDate datumOd;
	private LocalDate datumDo;
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
	public LocalDate getDatumOd() {
		return datumOd;
	}
	public void setDatumOd(LocalDate datumOd) {
		this.datumOd = datumOd;
	}
	public LocalDate getDatumDo() {
		return datumDo;
	}
	public void setDatumDo(LocalDate datumDo) {
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
	public Festival(int festivalID, String naziv, LocalDate datumOd, LocalDate datumDo, int gradID) {
		super();
		this.festivalID = festivalID;
		this.naziv = naziv;
		this.datumOd = datumOd;
		this.datumDo = datumDo;
		this.gradID = gradID;
	}
	
	
}
