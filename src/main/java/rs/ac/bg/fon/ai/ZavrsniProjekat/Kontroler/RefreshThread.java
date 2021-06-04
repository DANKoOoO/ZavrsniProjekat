package rs.ac.bg.fon.ai.ZavrsniProjekat.Kontroler;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import rs.ac.bg.fon.ai.ZavrsniProjekat.Broker.BrokerBP;
import rs.ac.bg.fon.ai.ZavrsniProjekat.Domen.Festival;

public class RefreshThread extends Thread{
	
	private BrokerBP broker;
	private DefaultTableModel modelFestival;
	public static String deoNaziva = "";
	private ArrayList<Festival> festivali; 
	private boolean ceka = false;
	@Override
	public void run() {		
		refreshuj();
	}
	
	public RefreshThread(BrokerBP broker, DefaultTableModel modelFestival) {
		super();
		this.broker = broker;
		this.modelFestival = modelFestival;
		this.deoNaziva = deoNaziva;
	}

	private synchronized void refreshuj() {
	
		try 
		{
			wait(3000);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		modelFestival.setRowCount(0);
		festivali = null;
		festivali = broker.vratiOdredjeneFestivale(deoNaziva);

		for (Festival f : festivali) {
			Object[] data = {f.getNaziv(), f.getGradID(), f.getDatumOd(), f.getDatumDo()};
			modelFestival.addRow(data);					
		}
		ceka = true;
		refreshuj();
	}

	public synchronized void obavesti() {
		ceka = false;
		notifyAll();
	}
}
