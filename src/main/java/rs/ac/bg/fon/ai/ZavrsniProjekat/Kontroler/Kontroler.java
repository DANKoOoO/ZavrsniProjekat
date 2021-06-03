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
		Film f1 = new Film(0, "Fast and Furious 1", "Vin Disel", 2001);
		Film f2 = new Film(1, "Fast and Furious 2", "Vin Disel", 2005);
		ArrayList<Film> sviFilmovi = new ArrayList<Film>();

		sviFilmovi.add(f1);
		sviFilmovi.add(f2);
		
	
		return sviFilmovi;
	}
}
