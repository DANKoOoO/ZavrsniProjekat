package rs.ac.bg.fon.ai.ZavrsniProjekat.Kontroler;

import java.util.ArrayList;
import java.util.Vector;

import rs.ac.bg.fon.ai.ZavrsniProjekat.Domen.Film;
import rs.ac.bg.fon.ai.ZavrsniProjekat.Domen.Grad;

public class Kontroler {
	public void SacuvajFestival(String nazivFestivala, String datumPocetka, String datumZavrsetka, Grad grad) 
	{
		
	}
	public void PretraziFestivale(String deoImena) 
	{
		
		
	}
	public ArrayList<Film> VratiSveFilmove() {
		Film f1 = new Film();
		Film f2 = new Film();
		ArrayList<Film> sviFilmovi = new ArrayList<Film>();
		f1.setNaziv("A");
		f1.setGodina(1997);
		f1.setReziser("Aaa");
		f2.setNaziv("B");
		f2.setGodina(2005);
		f2.setReziser("Bbb");
		sviFilmovi.add(f1);
		sviFilmovi.add(f2);
		
	
		return sviFilmovi;
	}
}
