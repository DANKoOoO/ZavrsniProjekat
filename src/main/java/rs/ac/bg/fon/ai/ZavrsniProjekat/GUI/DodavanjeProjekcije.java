package rs.ac.bg.fon.ai.ZavrsniProjekat.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import rs.ac.bg.fon.ai.ZavrsniProjekat.Domen.Film;
import rs.ac.bg.fon.ai.ZavrsniProjekat.Domen.Projekcija;
import rs.ac.bg.fon.ai.ZavrsniProjekat.Kontroler.Kontroler;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;

public class DodavanjeProjekcije extends JFrame {

	private JPanel contentPane;
	private JTextField txtFilm;
	private JTable tblFilmovi;
	private JTextField txtDatumVreme;
	
	private ArrayList<Film> sviFilmovi = Kontroler.Instanca().VratiSveFilmove();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DodavanjeProjekcije frame = new DodavanjeProjekcije(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param modelProjekcija 
	 * @param tblProjekcija 
	 */
	public DodavanjeProjekcije(final DefaultTableModel modelProjekcija) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 676, 511);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		final DefaultTableModel modelFilm = new DefaultTableModel();
		modelFilm.addColumn("Naziv");
		modelFilm.addColumn("Reziser");
		modelFilm.addColumn("Godina");
		modelFilm.addColumn("FilmID");

		
		for (Film f : sviFilmovi) {
			Object[] data = {f.getNaziv(), f.getReziser(), f.getGodina(), f.getFilmID()};
			modelFilm.addRow(data);
		}
		
		tblFilmovi = new JTable(modelFilm);
		tblFilmovi.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblFilmovi.setBounds(0, 0, 1, 1);
		contentPane.add(tblFilmovi);
		
		JScrollPane scrollPaneFilmovi = new JScrollPane(tblFilmovi);
		scrollPaneFilmovi.setBounds(20, 79, 346, 370);
		contentPane.add(scrollPaneFilmovi);
		
		tblFilmovi.removeColumn(tblFilmovi.getColumnModel().getColumn(3));
		
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
		btnDodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tblFilmovi.getSelectionModel().isSelectionEmpty()) {
					JFrame f=new JFrame();  
					JOptionPane.showMessageDialog(f,"Nije odabran ni jedan film!");  
				}else if(!Kontroler.Instanca().ispravnoUnetiSviTextBoxovi(txtDatumVreme.getText()) || !Kontroler.Instanca().proveriFormatDatumaIVremena(txtDatumVreme.getText())) 
				{
					JFrame f=new JFrame();  
					JOptionPane.showMessageDialog(f,"Nije unet datum ili je los format!");  
				}
				else {
					for (Film film : sviFilmovi) {
						if(film.getFilmID() == (int)tblFilmovi.getModel().getValueAt(tblFilmovi.getSelectedRow(),3)) 
						{
							Object[] data = {film.getNaziv(), txtDatumVreme.getText()};
							modelProjekcija.addRow(data);

							Timestamp timestamp = Timestamp.valueOf(txtDatumVreme.getText());
							Projekcija p = new Projekcija(0, 0, timestamp, film.getFilmID());
							Kontroler.Instanca().dodajProjekciju(p); 
							System.out.println(film);
						}
					}
				}
			}
		});
		btnDodaj.setBounds(440, 255, 185, 45);
		contentPane.add(btnDodaj);
		
		JLabel lblNewLabel_1 = new JLabel("Pretraga filmova:");
		lblNewLabel_1.setBounds(20, 11, 238, 14);
		contentPane.add(lblNewLabel_1);
		
		txtFilm.getDocument().addDocumentListener(new DocumentListener() {
			public void removeUpdate(DocumentEvent e) {
				SortirajTabelu(txtFilm.getText(), modelFilm);
			}
			public void insertUpdate(DocumentEvent e) {
				SortirajTabelu(txtFilm.getText(), modelFilm);
			}
			public void changedUpdate(DocumentEvent e) {
				SortirajTabelu(txtFilm.getText(), modelFilm);
			}
		});
		
	}

	public void SortirajTabelu(String deoFilma, DefaultTableModel modelFilm) {
		modelFilm.setRowCount(0);
		for (Film f : sviFilmovi) {
			if(f.getNaziv().toLowerCase().contains(deoFilma.toLowerCase())) {
				Object[] data = {f.getNaziv(), f.getReziser(), f.getGodina(), f.getFilmID()};
				modelFilm.addRow(data);
			}
		}
	}
}
