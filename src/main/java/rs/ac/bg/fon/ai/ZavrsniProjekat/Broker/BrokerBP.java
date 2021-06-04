package rs.ac.bg.fon.ai.ZavrsniProjekat.Broker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import rs.ac.bg.fon.ai.ZavrsniProjekat.Domen.Festival;
import rs.ac.bg.fon.ai.ZavrsniProjekat.Domen.Grad;
import rs.ac.bg.fon.ai.ZavrsniProjekat.Domen.Projekcija;
import rs.ac.bg.fon.ai.ZavrsniProjekat.Domen.Film;
import rs.ac.bg.fon.ai.ZavrsniProjekat.Domen.Glumac;

public class BrokerBP {
	
	private ArrayList<Grad> sviGradovi;
	private ArrayList<Festival> sviFestivali;
	private ArrayList<Projekcija> sveProjekcije;
	private ArrayList<Film> sviFilmovi;
	private ArrayList<Glumac> sviGlumci;
	
	private Connection con;
	
	public BrokerBP() {
		super();
		sviGradovi = new ArrayList<Grad>();
		sviFestivali = new ArrayList<Festival>();
		sveProjekcije = new ArrayList<Projekcija>();
		sviFilmovi = new ArrayList<Film>();
		sviGlumci = new ArrayList<Glumac>();
		
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Festivali","root","");		
			
			vratiSveGradove();
			vratiSveFestivale();
			vratiSveProjekcije();
			vratiSveFilmove();
			vratiSveGlumce();
			
			con.close();			
		}
		catch(Exception e) 
		{
			System.out.println("Greska prilikom konekcije sa bazom!\n"+ e);

		}
	}
	
		
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
	
	private ArrayList<Grad> vratiSveGradove(){	
		try 
		{			
			Statement statement = con.createStatement();			
			ResultSet rs = statement.executeQuery("select * from grad");
			
			while(rs.next()) 
			{
				sviGradovi.add(new Grad(rs.getInt(1), rs.getString(2),  rs.getString(3)));								
			}						
			return sviGradovi;
		}
		catch(Exception e) 
		{
			System.out.println("Greska prilikom pozivanja sql naredbe za vracanje svih gradova!\n"+ e);
			return null;
		}
	}
	
	private ArrayList<Festival> vratiSveFestivale(){	
		try 
		{			
			Statement statement = con.createStatement();			
			ResultSet rs = statement.executeQuery("select * from festival");
			
			while(rs.next()) 
			{
				sviFestivali.add(new Festival(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getDate(4), rs.getInt(5)));								
			}						
			return sviFestivali;
		}
		catch(Exception e) 
		{
			System.out.println("Greska prilikom pozivanja sql naredbe za vracanje svih festivala!\n"+ e);
			return null;
		}
	}

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
				odredjeniFestivali.add(new Festival(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getDate(4), rs.getInt(5)));								
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

	
	private ArrayList<Projekcija> vratiSveProjekcije(){
		try 
		{			
			Statement statement = con.createStatement();			
			ResultSet rs = statement.executeQuery("select * from projekcija");
			
			while(rs.next()) 
			{
				sveProjekcije.add(new Projekcija(rs.getInt(1), rs.getInt(2), rs.getTimestamp(3), rs.getInt(4)));								
			}						
			return sveProjekcije;
		}
		catch(Exception e) 
		{
			System.out.println("Greska prilikom pozivanja sql naredbe za vracanje svih projekcija!\n"+ e);
			return null;
		}
	}

	private ArrayList<Film> vratiSveFilmove(){
		try 
		{			
			Statement statement = con.createStatement();			
			ResultSet rs = statement.executeQuery("select * from film");
			
			while(rs.next()) 
			{
				sviFilmovi.add(new Film(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));								
			}						
			return sviFilmovi;
		}
		catch(Exception e) 
		{
			System.out.println("Greska prilikom pozivanja sql naredbe za vracanje svih filmova!\n"+ e);
			return null;
		}
	}

	private ArrayList<Glumac> vratiSveGlumce(){	
		try 
		{			
			Statement statement = con.createStatement();			
			ResultSet rs = statement.executeQuery("select * from glumac");
			
			while(rs.next()) 
			{
				sviGlumci.add(new Glumac(rs.getInt(1), rs.getString(2), rs.getInt(3)));								
			}						
			return sviGlumci;
		}
		catch(Exception e) 
		{
			System.out.println("Greska prilikom pozivanja sql naredbe za vracanje svih glumaca!\n"+ e);
			return null;
		}
	}


	public ArrayList<Grad> getSviGradovi() {
		return sviGradovi;
	}


	public void setSviGradovi(ArrayList<Grad> sviGradovi) {
		this.sviGradovi = sviGradovi;
	}


	public ArrayList<Festival> getSviFestivali() {
		return sviFestivali;
	}


	public void setSviFestivali(ArrayList<Festival> sviFestivali) {
		this.sviFestivali = sviFestivali;
	}


	public ArrayList<Projekcija> getSveProjekcije() {
		return sveProjekcije;
	}


	public void setSveProjekcije(ArrayList<Projekcija> sveProjekcije) {
		this.sveProjekcije = sveProjekcije;
	}


	public ArrayList<Film> getSviFilmovi() {
		return sviFilmovi;
	}


	public void setSviFilmovi(ArrayList<Film> sviFilmovi) {
		this.sviFilmovi = sviFilmovi;
	}


	public ArrayList<Glumac> getSviGlumci() {
		return sviGlumci;
	}


	public void setSviGlumci(ArrayList<Glumac> sviGlumci) {
		this.sviGlumci = sviGlumci;
	}
	
	
}
