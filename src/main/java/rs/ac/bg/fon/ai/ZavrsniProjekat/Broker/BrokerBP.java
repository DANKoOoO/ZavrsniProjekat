package rs.ac.bg.fon.ai.ZavrsniProjekat.Broker;

import java.sql.Connection;
import java.sql.DriverManager;
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
			
			con.close();			
		}
		catch(Exception e) 
		{
			System.out.println("Greska prilikom konekcije sa bazom!\n"+ e);

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
}
