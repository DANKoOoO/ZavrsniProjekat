package rs.ac.bg.fon.ai.ZavrsniProjekat.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import rs.ac.bg.fon.ai.ZavrsniProjekat.Domen.Film;
import rs.ac.bg.fon.ai.ZavrsniProjekat.Kontroler.Kontroler;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JButton;

public class DodavanjeProjekcije extends JFrame {

	private JPanel contentPane;
	private JTextField txtFilm;
	private JTable tblFilmovi;
	private JTextField txtDatumVreme;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DodavanjeProjekcije frame = new DodavanjeProjekcije();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DodavanjeProjekcije() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 676, 511);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		DefaultTableModel modelFilm = new DefaultTableModel();
		modelFilm.addColumn("Naziv");
		modelFilm.addColumn("Reziser");
		modelFilm.addColumn("Godina");

		for (Film f : Kontroler.Instanca().VratiSveFilmove()) {
			Object[] data = {f.getNaziv(), f.getReziser(), f.getGodina()};
			modelFilm.addRow(data);
		}
		
		tblFilmovi = new JTable(modelFilm);
		tblFilmovi.setBounds(0, 0, 1, 1);
		contentPane.add(tblFilmovi);
		
		JScrollPane scrollPaneFilmovi = new JScrollPane(tblFilmovi);
		scrollPaneFilmovi.setBounds(20, 79, 346, 370);
		contentPane.add(scrollPaneFilmovi);
		
		txtFilm = new JTextField();
		txtFilm.setBounds(20, 37, 346, 20);
		contentPane.add(txtFilm);
		txtFilm.setColumns(10);
		
		txtDatumVreme = new JTextField();
		txtDatumVreme.setBounds(397, 37, 253, 20);
		contentPane.add(txtDatumVreme);
		txtDatumVreme.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Datum i vreme (yyyy-MM-dd HH:mm:ss)");
		lblNewLabel.setBounds(397, 92, 253, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnDodaj = new JButton("DodajProjekciju");
		btnDodaj.setBounds(440, 255, 185, 45);
		contentPane.add(btnDodaj);
		
		JLabel lblNewLabel_1 = new JLabel("Pretraga filmova:");
		lblNewLabel_1.setBounds(20, 11, 238, 14);
		contentPane.add(lblNewLabel_1);
		
		
		
	}
}
