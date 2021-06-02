package rs.ac.bg.fon.ai.ZavrsniProjekat.Broker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import rs.ac.bg.fon.ai.ZavrsniProjekat.Domen.Grad;

public class BrokerBP {
	
	private static ArrayList<Grad> sviGradovi = new ArrayList<Grad>();
	
	public static void main(String[] args) {

		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Festivali","root","");
			
			Statement statement = con.createStatement();
			
			ResultSet rs = statement.executeQuery("select * from grad");
			
			while(rs.next()) 
			{
				//sviGradovi.add(new Grad(rs.getInt(1), rs.getString(2),  rs.getString(3)));
				
				System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
			}
			
			con.close();
		}
		catch(Exception e) 
		{
			System.out.println("Greska prilikom konekcije sa bazom!\n"+ e);
		}

	}
}
