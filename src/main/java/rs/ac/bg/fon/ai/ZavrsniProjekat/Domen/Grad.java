package rs.ac.bg.fon.ai.ZavrsniProjekat.Domen;

public class Grad {
	private int gradID;
	private String naziv;
	private String drzava;
	
	public int getGradID() {
		return gradID;
	}
	public void setGradID(int gradID) {
		if(gradID < 0) {
			throw new RuntimeException("ID mora biti pozitivan ceo broj");
		}
		this.gradID = gradID;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		if(naziv == null) {
			throw new NullPointerException("Grad ne sme biti null");
		}
		if((naziv.length() == 0)){
			throw new RuntimeException("Grad mora da ima barem 1 slovo");
		}
		this.naziv = naziv;
	}
	public String getDrzava() {
		return drzava;
	}
	public void setDrzava(String drzava) {
		if(naziv == null) {
			throw new NullPointerException("Drzava ne sme biti null");
		}
		if((naziv.length() == 0)){
			throw new RuntimeException("Drzava mora da ima barem 1 slovo");
		}
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
	public Grad() {
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
		Grad gr = (Grad) obj;
		if(naziv == null) {
			if(gr.naziv != null) {
				return false;
			}
			else if(!naziv.equals(gr.naziv)) {
				return false;
			}
		}
		if(drzava == null) {
			if(gr.drzava != null) {
				return false;
			}
			else if(!drzava.equals(gr.drzava)) {
				return false;
			}
		}
		if(gradID != gr.gradID) {
			return false;
		}

		return true;
	}
	
	
}
