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
}
