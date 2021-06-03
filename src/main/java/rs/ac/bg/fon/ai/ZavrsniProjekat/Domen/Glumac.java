package rs.ac.bg.fon.ai.ZavrsniProjekat.Domen;

public class Glumac {
	private int glumacID;
	private String imePrezime;
	private int filmID;
	public int getGlumacID() {
		return glumacID;
	}
	public void setGlumacID(int glumacID) {
		this.glumacID = glumacID;
	}
	public String getImePrezime() {
		return imePrezime;
	}
	public void setImePrezime(String imePrezime) {
		this.imePrezime = imePrezime;
	}
	public int getFilmID() {
		return filmID;
	}
	public void setFilmID(int filmID) {
		this.filmID = filmID;
	}
	public Glumac(int glumacID, String imePrezime, int filmID) {
		super();
		this.glumacID = glumacID;
		this.imePrezime = imePrezime;
		this.filmID = filmID;
	}
	@Override
	public String toString() {
		return imePrezime;
	}
	
	
}
