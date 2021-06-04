package rs.ac.bg.fon.ai.ZavrsniProjekat.Kontroler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;

import rs.ac.bg.fon.ai.ZavrsniProjekat.Broker.BrokerBP;
import rs.ac.bg.fon.ai.ZavrsniProjekat.Domen.Festival;
import rs.ac.bg.fon.ai.ZavrsniProjekat.Domen.Film;
import rs.ac.bg.fon.ai.ZavrsniProjekat.Domen.Grad;

public class Kontroler {
	
	private BrokerBP broker;
	private static Kontroler kontroler = null;
	
	private Kontroler() {
		broker = new BrokerBP();
	}
	
	public static Kontroler Instanca()  
	{
		if(kontroler == null)
			kontroler = new Kontroler();
		
		return kontroler;
	}
	
	public void SacuvajFestival(String nazivFestivala, String datumPocetka, String datumZavrsetka, Grad grad) 
	{
		try {
			java.sql.Date datumTEST = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(datumZavrsetka).getTime());
			Festival f = new Festival(0, nazivFestivala, new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(datumPocetka).getTime()), new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(datumZavrsetka).getTime()), grad.getGradID());
			broker.dodajFestival(f);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//broker.dodajProjekcije(null); //treba uneti preko tabele listu projekcija.
	}
	
	public void PretraziFestivale(String deoImena) 
	{
		
		
	}
	
	public ArrayList<Film> VratiSveFilmove() {
		return broker.getSviFilmovi();
	}
	
	public boolean ispravnoUnetiSviTextBoxovi(String...strings) 
	{
		for (String s : strings) {
			if(s.isEmpty()) 
			{
				return false;
			}
		}
		return true;
	}

	public ArrayList<Grad> VratiSveGradove() {
		return broker.getSviGradovi();
	}

	public boolean proveriFormatDatuma(String...strings) {
		for (String s : strings) {
			String[] podeli = s.split("-");
			if(podeli.length != 3) return false;
			if(podeli[0].length() != 4 || podeli[1].length() != 2 || podeli[2].length() != 2) return false;
			try {
				Integer.parseInt(podeli[0]);
				if(Integer.parseInt(podeli[1]) > 12 || Integer.parseInt(podeli[1]) < 1) return false;
				if(Integer.parseInt(podeli[2]) > 31 || Integer.parseInt(podeli[2]) < 1) return false;
			}
			catch (Exception e) {
				return false;
			}

		}
		return true;
	}
}
