package rs.ac.bg.fon.ai.ZavrsniProjekat.Broker;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import rs.ac.bg.fon.ai.ZavrsniProjekat.Domen.Festival;
import rs.ac.bg.fon.ai.ZavrsniProjekat.Domen.Grad;
import rs.ac.bg.fon.ai.ZavrsniProjekat.Domen.Projekcija;
import rs.ac.bg.fon.ai.ZavrsniProjekat.Domen.Film;
import rs.ac.bg.fon.ai.ZavrsniProjekat.Domen.Glumac;

/**
 * Klasa koja predstavlja Broker baze podataka.
 * 
 * BrokerBP ima konekcioni string kao java.sql.Connection.
 * 
 * @author danko
 * @version 0.1
 */
public class BrokerBP {
	/**
	 * Konekcioni string kao java.sql.Connection.
	 */
	private Connection con;
	
	/**
	 * Konstruktor koji samo inicijalizuje objekat.
	 */
	public BrokerBP() {
		super();
	}
	
		/**
		 * Vraca id poslednje unetog festivala koje je generisala sama baza.
		 * Dodaje novi festival u bazu.
		 * 
		 * @param festival Festival kao rs.ac.bf.fon.ai.ZavrsniProjekat.Domen.Festival.
		 * 
		 * @return Id festivala kao Integer.
		 */
	public int dodajFestival(Festival festival) 
	{			
		int id = 0;
		try 
		{

			Class.forName("com.mysql.cj.jdbc.Driver");			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Festivali","root","");		
			Statement statement = con.createStatement();			
			PreparedStatement ps = con.prepareStatement("INSERT INTO festival (FestivalID, Naziv, DatumOd, DatumDo, GradID) VALUES ("+
					null+", '"+ festival.getNaziv() +"', ?, ?, "+ festival.getGradID() +")",Statement.RETURN_GENERATED_KEYS);
			ps.setDate(1, festival.getDatumOd());
			ps.setDate(2, festival.getDatumDo());
			ps.executeUpdate();

			ResultSet ids = ps.getGeneratedKeys();
			if(ids.next()) {
				id = ids.getInt(1);
			}
			ps.close();
			con.close();	

		} catch(Exception e) 
		{
			System.out.println("Greska prilikom konekcije sa bazom! [Unosenje festivala]\n"+ e);
		}
		return id;
	}
	
	/**
	 * Dodaje listu projekcija u bazu.
	 * 
	 * @param projekcije Lista projekcija kao ArrayList<rs.ac.bf.fon.ai.ZavrsniProjekat.Domen.Projekcija>
	 * @param festivalID Id festival kao Integer.
	 */
	public void dodajProjekcije(ArrayList<Projekcija> projekcije, int festivalID) 
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Festivali","root","");		
			Statement statement = con.createStatement();		
			
			
			
			for (Projekcija projekcija : projekcije) {
				PreparedStatement ps = con.prepareStatement("INSERT INTO projekcija (projekcijaID, festivalID, datumVremeProjekcije, filmID) VALUES ("+
			null+","+ festivalID+ ", ? , "+ projekcija.getFilmID() +")");
				ps.setTimestamp(1, projekcija.getDatumVremeProjekcije());
				ps.executeUpdate();
				ps.close();
			}

			con.close();			
		} catch(Exception e) 
		{
			System.out.println("Greska prilikom konekcije sa bazom! [Unosenje projekcija]\n"+ e);

		}
	}
	
	/**
	 * Vraca sve gradove iz baze podataka.
	 * 
	 * @return Lista gradova kao ArrayList<rs.ac.bf.fon.ai.ZavrsniProjekat.Domen.Grad>.
	 */
	public ArrayList<Grad> vratiSveGradove(){	
		ArrayList<Grad> sviGradovi = new ArrayList<Grad>();
		try 
		{	
			Class.forName("com.mysql.cj.jdbc.Driver");			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Festivali","root","");		
			
			
			Statement statement = con.createStatement();			
			ResultSet rs = statement.executeQuery("select * from grad");
			
			while(rs.next()) 
			{
				sviGradovi.add(new Grad(rs.getInt(1), rs.getString(2),  rs.getString(3)));								
			}			
			
			con.close();
			return sviGradovi;

		}
		catch(Exception e) 
		{
			System.out.println("Greska prilikom pozivanja sql naredbe za vracanje svih gradova!\n"+ e);
			return null;
		}
	}
	/**
	 * Vraca sve festivale iz baze podataka.
	 * 
	 * @return Lista festivala kao ArrayList<rs.ac.bf.fon.ai.ZavrsniProjekat.Domen.Festival>.
	 */
	public ArrayList<Festival> vratiSveFestivale(){
		ArrayList<Festival> sviFestivali = new ArrayList<Festival>();
		try 
		{		
			Class.forName("com.mysql.cj.jdbc.Driver");			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Festivali","root","");		
			
			Statement statement = con.createStatement();			
			ResultSet rs = statement.executeQuery("select * from festival");
			
			while(rs.next()) 
			{
				if(rs.getDate(3).before(new Date(System.currentTimeMillis()))) {
					PreparedStatement st = con.prepareStatement("DELETE FROM festival WHERE FestivalID = "+rs.getInt(1));
					st.executeUpdate();
					
				} else {
					sviFestivali.add(new Festival(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getDate(4), rs.getInt(5)));		
				}
			}		
			con.close();
			return sviFestivali;
		}
		catch(Exception e) 
		{
			System.out.println("Greska prilikom pozivanja sql naredbe za vracanje svih festivala!\n"+ e);
			return null;
		}
	}
	/**
	 * Vraca odredjene festivale iz baze podataka u odnosu na parametar deoNaziva.
	 * 
	 * @param deoNaziva Deo naziva festivala koje je korisnik uneo kao String.
	 * 
	 * @return Lista festivala kao ArrayList<rs.ac.bf.fon.ai.ZavrsniProjekat.Domen.Festival>.
	 */
	public ArrayList<Festival> vratiOdredjeneFestivale(String deoNaziva){	
		ArrayList<Festival> odredjeniFestivali = new ArrayList<Festival>();
		try 
		{			
			Class.forName("com.mysql.cj.jdbc.Driver");			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Festivali","root","");			
			Statement statement = con.createStatement();			
			ResultSet rs = statement.executeQuery("select * from festival where naziv like '%"+ deoNaziva +"%'");
			
			while(rs.next()) 
			{
				if(rs.getDate(3).before(new Date(System.currentTimeMillis()))) {
					PreparedStatement st = con.prepareStatement("DELETE FROM festival WHERE FestivalID = "+rs.getInt(1));
					st.executeUpdate();
				} else {
					odredjeniFestivali.add(new Festival(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getDate(4), rs.getInt(5)));		
				}
			}		
			con.close();
			return odredjeniFestivali;
		}
		catch(Exception e) 
		{
			System.out.println("Greska prilikom pozivanja sql naredbe za vracanje svih festivala!\n"+ e);
			return null;
		}
	}

	/**
	 * Vraca sve projekcije iz baze podataka.
	 * 
	 * @return Lista projekcija kao ArrayList<rs.ac.bf.fon.ai.ZavrsniProjekat.Domen.Projekcija>.
	 */
	public ArrayList<Projekcija> vratiSveProjekcije(){
		ArrayList<Projekcija> sveProjekcije = new ArrayList<Projekcija>();
		try 
		{			
			Class.forName("com.mysql.cj.jdbc.Driver");			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Festivali","root","");		
			
			Statement statement = con.createStatement();			
			ResultSet rs = statement.executeQuery("select * from projekcija");
			
			while(rs.next()) 
			{
				if(rs.getTimestamp(3).before(new Timestamp(System.currentTimeMillis()))) {
					PreparedStatement st = con.prepareStatement("DELETE FROM projekcija WHERE ProjekcijaID = "+rs.getInt(1));
					st.executeUpdate();
				} else {
					sveProjekcije.add(new Projekcija(rs.getInt(1), rs.getInt(2), rs.getTimestamp(3), rs.getInt(4)));								
				}			
			}
			
			con.close();
			return sveProjekcije;
		}
		catch(Exception e) 
		{
			System.out.println("Greska prilikom pozivanja sql naredbe za vracanje svih projekcija!\n"+ e);
			return null;
		}
	}

	/**
	 * Vraca sve filmove iz baze podataka.
	 * 
	 * @return Lista filmova kao ArrayList<rs.ac.bf.fon.ai.ZavrsniProjekat.Domen.Film>.
	 */
	public ArrayList<Film> vratiSveFilmove(){
		ArrayList<Film> sviFilmovi = new ArrayList<Film>();
		try 
		{			
			Class.forName("com.mysql.cj.jdbc.Driver");			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Festivali","root","");		
			
			Statement statement = con.createStatement();			
			ResultSet rs = statement.executeQuery("select * from film");
			
			while(rs.next()) 
			{
				sviFilmovi.add(new Film(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));								
			}				
			con.close();
			return sviFilmovi;
		}
		catch(Exception e) 
		{
			System.out.println("Greska prilikom pozivanja sql naredbe za vracanje svih filmova!\n"+ e);
			return null;
		}
	}
	/**
	 * Vraca sve glumce iz baze podataka.
	 * 
	 * @return Lista glumaca kao ArrayList<rs.ac.bf.fon.ai.ZavrsniProjekat.Domen.Glumac>.
	 */
	public ArrayList<Glumac> vratiSveGlumce(){	
		ArrayList<Glumac> sviGlumci = new ArrayList<Glumac>();
		try 
		{			
			Class.forName("com.mysql.cj.jdbc.Driver");			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Festivali","root","");		
			
			Statement statement = con.createStatement();			
			ResultSet rs = statement.executeQuery("select * from glumac");
			
			while(rs.next()) 
			{
				sviGlumci.add(new Glumac(rs.getInt(1), rs.getString(2), rs.getInt(3)));								
			}		
			
			con.close();
			return sviGlumci;
		}
		catch(Exception e) 
		{
			System.out.println("Greska prilikom pozivanja sql naredbe za vracanje svih glumaca!\n"+ e);
			return null;
		}
	}

	
}
