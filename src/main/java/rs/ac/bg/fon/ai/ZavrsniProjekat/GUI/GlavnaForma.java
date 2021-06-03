package rs.ac.bg.fon.ai.ZavrsniProjekat.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import rs.ac.bg.fon.ai.ZavrsniProjekat.Domen.Grad;
import rs.ac.bg.fon.ai.ZavrsniProjekat.Kontroler.Kontroler;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.ScrollPane;

public class GlavnaForma {

	private JFrame frame;
	private JTextField txtNaziv;
	private JTextField textField;
	private JTextField txtNaziFestivala;
	private JTextField txtDatumPocetka;
	private JTextField txtDatumZavrsetka;
	private JTextField txtPretraga;
	
	private JTable tblProjekcija;
	private JTable tblFestival;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GlavnaForma window = new GlavnaForma();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GlavnaForma() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 917, 531);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtNaziFestivala = new JTextField();
		txtNaziFestivala.setBounds(114, 11, 338, 20);
		frame.getContentPane().add(txtNaziFestivala);
		txtNaziFestivala.setColumns(10);
		
		txtDatumPocetka = new JTextField();
		txtDatumPocetka.setBounds(114, 52, 338, 20);
		frame.getContentPane().add(txtDatumPocetka);
		txtDatumPocetka.setColumns(10);
		
		txtDatumZavrsetka = new JTextField();
		txtDatumZavrsetka.setBounds(114, 96, 338, 20);
		frame.getContentPane().add(txtDatumZavrsetka);
		txtDatumZavrsetka.setColumns(10);
		
		final JComboBox<Grad> cbGrad = new JComboBox<Grad>();
		cbGrad.setBounds(114, 143, 338, 22);
		frame.getContentPane().add(cbGrad);
		
		JLabel lblNewLabel = new JLabel("Naziv festivala:");
		lblNewLabel.setBounds(24, 14, 80, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Datum pocetka:");
		lblNewLabel_1.setBounds(24, 55, 80, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Datum zavrsetka:");
		lblNewLabel_2.setBounds(24, 99, 80, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Grad");
		lblNewLabel_3.setBounds(24, 147, 80, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		txtPretraga = new JTextField();
		txtPretraga.setBounds(494, 46, 241, 32);
		frame.getContentPane().add(txtPretraga);
		txtPretraga.setColumns(10);	
				
		final DefaultTableModel modelProjekcija = new DefaultTableModel();
		modelProjekcija.addColumn("Film");
		modelProjekcija.addColumn("Datum i vreme projekcije");

		final DefaultTableModel modelFestival = new DefaultTableModel();
		modelFestival.addColumn("Naziv");
		modelFestival.addColumn("Grad");
		modelFestival.addColumn("Datum od");
		modelFestival.addColumn("Datum do");
		
		tblProjekcija = new JTable(modelProjekcija);
		tblProjekcija.setBounds(0, 0, 1, 1);
		frame.getContentPane().add(tblProjekcija);
				
		tblFestival = new JTable(modelFestival);
		tblFestival.setBounds(0, 0, 1, 1);
		frame.getContentPane().add(tblFestival);
		
		
		JScrollPane scrollPaneProjekcija = new JScrollPane(tblProjekcija);
		scrollPaneProjekcija.setBounds(24, 182, 428, 231);
		frame.getContentPane().add(scrollPaneProjekcija);
		
		JScrollPane scrollPaneFestival = new JScrollPane(tblFestival);
		scrollPaneFestival.setBounds(504, 99, 387, 369);
		frame.getContentPane().add(scrollPaneFestival);
		
		
		JButton btnDodajProjekciju = new JButton("Dodaj projekciju");
		btnDodajProjekciju.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				DodavanjeProjekcije dodavanjeProjekcije = new DodavanjeProjekcije();								
				dodavanjeProjekcije.setVisible(true);
			}
		});
		btnDodajProjekciju.setBounds(24, 424, 146, 44);
		frame.getContentPane().add(btnDodajProjekciju);
		
		JButton btnSacuvajFestival = new JButton("Sacuvaj festival");
		btnSacuvajFestival.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Kontroler.Instanca().SacuvajFestival(txtNaziFestivala.getText(), txtDatumPocetka.getText(), txtDatumZavrsetka.getText(), (Grad) cbGrad.getSelectedItem());				
			}
		});
		btnSacuvajFestival.setBounds(306, 424, 146, 44);
		frame.getContentPane().add(btnSacuvajFestival);
		
		JButton btnPretrazi = new JButton("Pretrazi");
		btnPretrazi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Kontroler.Instanca().PretraziFestivale(txtPretraga.getText());			
			}
		});
		btnPretrazi.setBounds(745, 40, 146, 44);
		frame.getContentPane().add(btnPretrazi);
		
		cbGrad.setModel(new DefaultComboBoxModel<Grad>(Kontroler.Instanca().VratiSveGradove().toArray((new Grad[0]))));
		
	}
}
