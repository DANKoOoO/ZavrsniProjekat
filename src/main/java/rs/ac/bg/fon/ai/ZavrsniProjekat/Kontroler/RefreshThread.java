package rs.ac.bg.fon.ai.ZavrsniProjekat.Kontroler;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import rs.ac.bg.fon.ai.ZavrsniProjekat.Broker.BrokerBP;
import rs.ac.bg.fon.ai.ZavrsniProjekat.Domen.Festival;

/**
 * Klasa koja predstavlja nit za osvezavanje tabele.
 * 
 * @author danko
 * @version 1.0
 */

public class RefreshThread extends Thread{
	/**
	 * Broker baze kao rs.ac.bf.fon.ai.ZavrsniProjekat.Broker.BrokerBP.
	 */
	private BrokerBP broker;
	/**
	 * Model tabele festivala kao javax.swing.table.DefaultTableModel.
	 */
	private DefaultTableModel modelFestival;
	/**
	 * Deo naziva festivala kao String.
	 */
	public static String deoNaziva = "";
	/**
	 * Lista festivala kao ArrayList<rs.ac.bg.fon.ai.ZavrsniProjekat.Domen.Festival>.
	 */
	private ArrayList<Festival> festivali; 
	/**
	 * Nit za osvezavanje kao RefreshThread.
	 */
	private static RefreshThread refreshThread = null;
	/**
	 * Konstruktor koji samo inicijalizuje objekat.
	 */
	private RefreshThread() {
		super();
	}
	
	/**
	 * Parametrizovan singleton pattern. Ako klasa nije instancirana, instancira je i vraca nit za osvezavanje.
	 * 
	 * @param broker Broker baze kao rs.ac.bf.fon.ai.ZavrsniProjekat.Broker.BrokerBP.
	 * @param modelFestival kao javax.swing.table.DefaultTableModel.
	 * 
	 * @return nit za osvezavanje kao RefreshThread.
	 */
	public static RefreshThread Instanca(BrokerBP broker, DefaultTableModel modelFestival) {
		if(refreshThread == null)
			refreshThread = new RefreshThread(broker, modelFestival);
		return refreshThread;
	}
	
	/**
	 * Singleton pattern. Ako klasa nije instancirana, instancira je i vraca nit za osvezavanje.
	 * 
	 * @return nit za osvezavanje kao RefreshThread.
	 */
	public static RefreshThread Instanca() {
		if(refreshThread == null)
			refreshThread = new RefreshThread();
		return refreshThread;
	}
	
	/**
	 * Override-ovana run metoda iz Thread klase koja poziva metodu refreshuj.
	 */
	@Override
	public void run() {		
		refreshuj();
	}
	
	/**
	 * Konstruktor koji inicijalizuje objekat i postavlja vrednosti
	 * za brokera i model tabele iz GUI-ja.
	 * 
	 * @param broker Broker bzae kao rs.ac.bf.fon.ai.ZavrsniProjekat.Broker.BrokerBP.
	 * @param modelFestival Model tabele kao javax.swing.table.DefaultTableModel.
	 */
	public RefreshThread(BrokerBP broker, DefaultTableModel modelFestival) {
		super();
		this.broker = broker;
		this.modelFestival = modelFestival;

	}

	/**
	 * Sinhronizovana metoda niti koja na svake 3 sekunde osvezava tabelu GUI-ja.
	 * Ako korisnik promeni deo naziva festivala koji pretrazuje, prekida se
	 * cekanje i osvezavanje se desava odmah.
	 */
	private synchronized void refreshuj() {
	
		try 
		{
			modelFestival.setRowCount(0);
			festivali = null;
			festivali = broker.vratiOdredjeneFestivale(deoNaziva);

			for (Festival f : festivali) {
				Object[] data = {f.getNaziv(), f.getGradID(), f.getDatumOd(), f.getDatumDo()};
				modelFestival.addRow(data);					
			}
			wait(3000);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}

		refreshuj();
	}

	/**
	 * Sinhronizovana metoda koja obavestava sve metode koje cekaju da je doslo do promene.
	 */
	public synchronized void obavesti() {

		notifyAll();
	}
}
