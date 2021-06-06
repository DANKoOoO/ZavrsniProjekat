package rs.ac.bg.fon.ai.ZavrsniProjekat.Kontroler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import rs.ac.bg.fon.ai.ZavrsniProjekat.Broker.BrokerBP;
import rs.ac.bg.fon.ai.ZavrsniProjekat.Domen.Festival;
import rs.ac.bg.fon.ai.ZavrsniProjekat.Domen.Film;
import rs.ac.bg.fon.ai.ZavrsniProjekat.Domen.Grad;
import rs.ac.bg.fon.ai.ZavrsniProjekat.Domen.Projekcija;

/**
 * Klasa koja predstavlja Kontroler logike.
 * 
 * Kontroler ima broker baze kao rs.ac.bf.fon.ai.ZavrsniProjekat.Broker.BrokerBP vrednost.
 * Kontroler ima datum ima kontoler logike kao Kontroler.
 * Kontroler ima listu prokjekcija za unos kao ArrayList<rs.ac.bf.fon.ai.ZavrsniProjekat.Domen.Projekcija>.
 * Kontroler ima nit za osvezavanje kao RefreshThread.
 * 
 * @author danko
 * @version 0.1
 */
public class Kontroler {
	/**
	 * Broker baze kao rs.ac.bf.fon.ai.ZavrsniProjekat.Broker.BrokerBP.
	 */
	private BrokerBP broker;
	/**
	 * Kontroler logike kao Kontroler.
	 */
	private static Kontroler kontroler = null;
	/**
	 * Lista projekcija kao ArrayList<rs.ac.bf.fon.ai.ZavrsniProjekat.Domen.Projekcija>.
	 */
	private ArrayList<Projekcija> projekcijeZaUnos = new ArrayList<Projekcija>();
	
	/**
	 * Konstruktor koji inicijalizuje objekat i inicijalizuje parametar broker.
	 */
	private Kontroler() {
		broker = new BrokerBP();
	}
	/**
	 * Singleton pattern. Ako klasa nije instancirana, instancira je i vraca kontoler logike.
	 * 
	 * @return Kontroler logike kao Kontroler.
	 */
	public static Kontroler Instanca()  
	{
		if(kontroler == null)
			kontroler = new Kontroler();
		
		return kontroler;
	}
	
	/**
	 * Pravi novu klasu Festival od prosledjenih vredenosti,
	 * prosledjuje je funkciji iz brokera koja upisuje festival u bazu
	 * i vraca njen id.
	 * Zatim poziva funkciju brokera za dodavanje projekcija iz liste sa dobijenim id-jem festivala.
	 *
	 * 
	 * @param nazivFestivala Naziv festivala kao String.
	 * @param datumPocetka Datum pocetka kao String.
	 * @param datumZavrsetka Datum zavrsetka kao String.
	 * @param grad Grad kao Grad.
	 * @return Uspesno sacuvan festival i projekcije kao boolean.
	 * 
	 * @throws java.lang.RuntimeException Ukoliko je doslo do greske prilikom parsiranja datuma.
	 */
	public boolean SacuvajFestival(String nazivFestivala, String datumPocetka, String datumZavrsetka, Grad grad) 
	{
		try {
			//java.sql.Date datumTEST = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(datumZavrsetka).getTime());
			Festival f = new Festival(0, nazivFestivala, 
					new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(datumPocetka).getTime()), 
					new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(datumZavrsetka).getTime()), grad.getGradID());
			int festivalID = broker.dodajFestival(f);
			
			broker.dodajProjekcije(projekcijeZaUnos, festivalID); 

			projekcijeZaUnos.clear();
			return true;
			
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}

	}
	/**
	 * Nije implementirana.
	 * 
	 * @param deoImena
	 */
	public void pretraziFestivale(String deoImena) 
	{
		
		return;
	}
	/**
	 * Vraca sve filmove.
	 * 
	 * @return Lista filmova kao ArrayList<rs.ac.bf.fon.ai.ZavrsniProjekat.Domen.Film>
	 */
	public ArrayList<Film> VratiSveFilmove() {
		return broker.getSviFilmovi();
	}
	/**
	 * Vraca boolean vrednost koja zavisi od toga da li su prosledjene vrednosti
	 * ispravno unete.
	 * 
	 * <ul>
	 * 		<li>true ako uneti stringovi nisu null vrednost i ako nisu prazni</li>
	 * 		<li>false u svim ostalim slucajevima</li>
	 * </ul>
	 * 
	 * @param strings Lista stringova iz text-boxova GUI-ja kao String...
	 * @return Da li su spravno uneta sva polja kao boolean.
	 */
	public boolean ispravnoUnetiSviTextBoxovi(String...strings) 
	{
		for (String s : strings) {
			if(s==null || s.isEmpty()) 
			{
				return false;
			}
		}
		return true;
	}
	/**
	 * Vraca sve gradove.
	 * 
	 * @return Lista gradova kao ArrayList<rs.ac.bf.fon.ai.ZavrsniProjekat.Domen.Grad>
	 */
	public ArrayList<Grad> VratiSveGradove() {
		return broker.getSviGradovi();
	}

	/**
	 * Vraca boolean vrednost koja zavisi od toga da li su prosledjene vrednosti
	 * ispravno unete.
	 * 
	 * <ul>
	 * 		<li>true ako uneti stringovi imaju 3 dela kada se razdvoje sa '-',
	 * 			ako su duzine tih novih stringova redom 4,2,2 i
	 * 			ako su im vrednosti redom izmedju (0000, 9999),(1,12),(1,31)</li>
	 * 		<li>false u svim ostalim slucajevima</li>
	 * </ul>
	 * 
	 * @param strings Lista stringova iz text-boxova GUI-ja kao String...
	 * @return Da li su spravno uneta sva polja kao boolean.
	 */
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

	/**
	 * Vraca boolean vrednost koja zavisi od toga da li su prosledjene vrednosti
	 * ispravno unete.
	 * 
	 * <ul>
	 * 		<li>true ako uneti stringovi imaju 3 dela kada se razdvoje sa ':',
	 * 			ako su duzine tih novih stringova redom 2,2,2 i
	 * 			ako su im vrednosti redom izmedju (0, 24),(0,59),(0,59)</li>
	 * 		<li>false u svim ostalim slucajevima</li>
	 * </ul>
	 * 
	 * @param strings Lista stringova iz text-boxova GUI-ja kao String...
	 * @return Da li su spravno uneta sva polja kao boolean.
	 */
	public boolean proveriFormatVremena(String...strings) {
		for (String s : strings) {
			String[] podeli = s.split(":");
			if(podeli.length != 3) return false;
			if(podeli[0].length() != 2 || podeli[1].length() != 2 || podeli[2].length() != 2) return false;
			try {
				if(Integer.parseInt(podeli[0]) > 24 || Integer.parseInt(podeli[1]) < 0) return false;
				if(Integer.parseInt(podeli[1]) > 59 || Integer.parseInt(podeli[1]) < 0) return false;
				if(Integer.parseInt(podeli[2]) > 59 || Integer.parseInt(podeli[2]) < 0) return false;
			}
			catch (Exception e) {
				return false;
			}

		}
		return true;
		
	}
	/**
	 * Vraca boolean vrednost koja zavisi od toga da je prosledjena vrednost
	 * ispravno uneta.
	 * Poziva metode proveriFormatDatuma i proveriFormatVremena.
	 * 
	 * <ul>
	 * 		<li>true ako kada se podeli ulazni string sa razmakom (' ')
	 * 			metoda proveriFormatDatuma ciji je parametar prvi deo stringa vrati true i
	 * 			metoda proveriFormatVremena ciji je parametar drugi deo stringa vrati true</li>
	 * 		<li>false u svim ostalim slucajevima</li>
	 * </ul>
	 * 
	 * @param strings Lista stringova iz text-boxova GUI-ja kao String...
	 * @return Da li su spravno uneta sva polja kao boolean.
	 */
	public boolean proveriFormatDatumaIVremena(String datumVreme) {
		String[] dv = datumVreme.split(" ");

		if(!proveriFormatDatuma(dv[0])) {
			return false;
		}
		if(!proveriFormatVremena(dv[1])) {
			return false;
		}
		return true;
	}
	/**
	 * Dodaje u listu projekcija novu projeciju. 
	 * 
	 * @param p Projekcija za unos u listu kao rs.ac.bf.fon.ai.ZavrsniProjekat.Domen.Projekcija.
	 */
	public void dodajProjekciju(Projekcija p) {
		projekcijeZaUnos.add(p);
	}
	/**
	 * Vraca odredjene festivale iz baze u odnosu na vrednost parametra deoNaziva i stavlja ih u GUI tabelu.
	 * Ovaj thread vraca osvezenu listu na svake 3 sekunde.
	 * 
	 * @param deoNaziva Deo naziva festivala kao String.
	 * @param modelFestival Model iz tabele iz GUI-ja kao javax.swing.table.DefaultTableModel.
	 */
	public void VratiOdredjeneFestivale(String deoNaziva, DefaultTableModel modelFestival) {
		RefreshThread rf = RefreshThread.Instanca(broker, modelFestival);
		RefreshThread.Instanca().start();
	}
	/**
	 * Vraca odredjene festivale iz baze u odnosu na vrednost parametra deoNaziva i stavlja ih u GUI tabelu.
	 * Ova metoda se poziva ne promenu text-boxa iz GUI-a.
	 * 
	 * @param deoNaziva Deo naziva festivala kao String.
	 */
	public void promeniDeoNaziva(String deoNaziva) {		
		RefreshThread.Instanca().deoNaziva = deoNaziva;
		RefreshThread.Instanca().obavesti();
	}
}
