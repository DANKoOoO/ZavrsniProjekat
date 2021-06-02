package rs.ac.bg.fon.ai.ZavrsniProjekat.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JLabel;

public class GlavnaForma {

	private JFrame frame;
	private JTextField txtNaziv;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTable tblProjekcije;
	private JTable tblFestivali;
	private JTextField txtPretraga;

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
		
		textField_1 = new JTextField();
		textField_1.setBounds(114, 11, 338, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(114, 52, 338, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(114, 96, 338, 20);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(114, 143, 338, 22);
		frame.getContentPane().add(comboBox);
		
		JButton btnPretrazi = new JButton("Pretrazi");
		btnPretrazi.setBounds(745, 40, 146, 44);
		frame.getContentPane().add(btnPretrazi);
		
		tblProjekcije = new JTable();
		tblProjekcije.setBounds(24, 182, 428, 231);
		frame.getContentPane().add(tblProjekcije);
		
		JButton btnSacuvajFestival = new JButton("Sacuvaj festival");
		btnSacuvajFestival.setBounds(306, 424, 146, 44);
		frame.getContentPane().add(btnSacuvajFestival);
		
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
		
		tblFestivali = new JTable();
		tblFestivali.setBounds(494, 96, 397, 385);
		frame.getContentPane().add(tblFestivali);
		
		txtPretraga = new JTextField();
		txtPretraga.setBounds(494, 46, 241, 32);
		frame.getContentPane().add(txtPretraga);
		txtPretraga.setColumns(10);
		
		JButton btnDodajProjekciju = new JButton("Dodaj projekciju");
		btnDodajProjekciju.setBounds(24, 424, 146, 44);
		frame.getContentPane().add(btnDodajProjekciju);
		

	}
}
