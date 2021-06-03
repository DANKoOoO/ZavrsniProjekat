package rs.ac.bg.fon.ai.ZavrsniProjekat.Domen;

public class Grad {
	private int gradID;
	private String naziv;
	private String drzava;
	
	public int getGradID() {
		return gradID;
	}
	public void setGradID(int gradID) {
		this.gradID = gradID;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getDrzava() {
		return drzava;
	}
	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}
	@Override
	public String toString() {
		return "Grad: " + naziv + " (" + drzava + ")";
	}
	
	public Grad(int gradID, String naziv, String drzava) {
		super();
		this.gradID = gradID;
		this.naziv = naziv;
		this.drzava = drzava;
	}
	
	
}
