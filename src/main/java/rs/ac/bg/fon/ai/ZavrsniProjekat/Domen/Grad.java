package rs.ac.bg.fon.ai.ZavrsniProjekat.Domen;

/**
 * Klasa koja predstavlja Grad.
 * 
 * Grad ima id grada kao Integer vrednost.
 * Grad ima naziv i drzavu kao String vrednosti.
 * 
 * @author danko
 * @version 0.0.0.1
 *
 */


public class Grad {
	/**
	 * Id grada kao Integer.
	 */
	private int gradID;
	/**
	 * Naziv grada kao String.
	 */
	private String naziv;
	/**
	 * Nazvi drzave kao String.
	 */
	private String drzava;
	
	/**
	 * Vraca id grada.
	 * 
	 * @return Id grada kao Integer.
	 */
	public int getGradID() {
		return gradID;
	}
	/**
	 * Postavlja id grada na novu vrednost.
	 * 
	 * @param gradID Id grada kao Integer.
	 * 
	 * @throws java.lang.NullPointerException Ako je unet negativan broj. 
	 */
	public void setGradID(int gradID) {
		if(gradID < 0) {
			throw new RuntimeException("ID mora biti pozitivan ceo broj");
		}
		this.gradID = gradID;
	}
	/**
	 * Vraca naziv grada.
	 * 
	 * @return Naziv kao String.
	 */
	public String getNaziv() {
		return naziv;
	}
	/**
	 * Postavlja naziv grada na novu vrednost.
	 * 
	 * @param naziv Naziv grada kao String vrednost.
	 * 
	 * @throws java.lang.NullPointerException Ako je uneta vrednost parametra naziv null.
	 * @throws java.lang.RuntimeException Ako je unet prazan String.
	 */
	public void setNaziv(String naziv) {
		if(naziv == null) {
			throw new NullPointerException("Grad ne sme biti null");
		}
		if((naziv.length() == 0)){
			throw new RuntimeException("Grad mora da ima barem 1 slovo");
		}
		this.naziv = naziv;
	}
	
	/**
	 * Vraca naziv drzave.
	 * 
	 * @return Drzavu kao String vrednost.
	 */
	public String getDrzava() {
		return drzava;
	}
	
	/**
	 * Postavlja naziv drzave na novu vrednost.
	 * 
	 * @param drzava Naziv drzave kao String vrednost.
	 * 
	 * @throws java.lang.NullPointerException Ako je uneta vrednost parametra drzava null.
	 * @throws java.lang.RuntimeException Ako je unet prazan String.
	 */
	public void setDrzava(String drzava) {
		if(drzava == null) {
			throw new NullPointerException("Drzava ne sme biti null");
		}
		if((drzava.length() == 0)){
			throw new RuntimeException("Drzava mora da ima barem 1 slovo");
		}
		this.drzava = drzava;
	}
	
	/**
	 * Vraca formatiran String  koji prikazuje naziv grada i naziv dzave.
	 * 
	 * @return Naziv grada i naziv dzave kao String.
	 */
	@Override
	public String toString() {
		return "Grad: " + naziv + " (" + drzava + ")";
	}
	
	/**
	 * Konstruktor koji inicijalizuje objekat i postavlja vrednosti 
	 * za id grada, naziv grada i naziv drzave.
	 * 
	 * @param gradID Id grada kao Integer.
	 * @param naziv Naziv grada kao String.
	 * @param drzava Naziv drzave kao String.
	 */
	public Grad(int gradID, String naziv, String drzava) {
		super();
		setGradID(gradID);
		setNaziv(naziv);
		setDrzava(drzava);
	}
	
	/**
	 * Konstruktor koji samo inicijalizuje objekat.
	 */
	public Grad() {
		super();
	}
	
	/**
	 * Poredi dva grada i vraca true ako su isti, a false ako nisu.
	 * 
	 * Gradovi se porede po id-ju, nazivu i nazivu drzave.
	 * 
	 * @return
	 * <ul>
	 * 		<li> true ako su oba objekta klase Grad i imaju iste id-jeve, nazive i drzave.</li>
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
		Grad gr = (Grad) obj;
		if(naziv == null) {
			if(gr.naziv != null) {
				return false;
			}
		}
		else if(!naziv.equals(gr.naziv)) {
			return false;
		}
		if(drzava == null) {
			if(gr.drzava != null) {
				return false;
			}	
		}
		else if(!drzava.equals(gr.drzava)) {
			return false;
		}
		if(gradID != gr.gradID) {
			return false;
		}

		return true;
	}
	
	
}
