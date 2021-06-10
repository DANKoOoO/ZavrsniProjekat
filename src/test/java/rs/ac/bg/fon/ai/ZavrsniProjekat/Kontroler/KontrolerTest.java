package rs.ac.bg.fon.ai.ZavrsniProjekat.Kontroler;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.ai.ZavrsniProjekat.Domen.Festival;
import rs.ac.bg.fon.ai.ZavrsniProjekat.Domen.Grad;
import rs.ac.bg.fon.ai.ZavrsniProjekat.Domen.Projekcija;

class KontrolerTest {

	@Test
	void testInstanca() {
		Kontroler k = Kontroler.Instanca();
		assertEquals(k, Kontroler.Instanca());
	}

	@Test
	void testSacuvajFestival() {
		String naziv = "festival";
		String datumOd = "9999-12-25";
		String datumDo = "9999-12-26";
		Grad grad = new Grad(1, "Beograd", "Srbija");

		assertTrue(Kontroler.Instanca().SacuvajFestival(naziv, datumOd, datumDo, grad));
	}
	
	@Test
	void testSacuvajFestivalLosDatum() {
		String naziv = "festival";
		String datumOd = "greska";
		String datumDo = "9999-12-26";
		Grad g = new Grad(1, "Beograd", "Srbija");
		assertThrows(java.lang.RuntimeException.class,
				()-> Kontroler.Instanca().SacuvajFestival(naziv,datumOd,datumDo,g));
	}

	@Test
	void testVratiSveFilmove() {
		assertNotNull(Kontroler.Instanca().VratiSveFilmove());
		
		assertTrue(Kontroler.Instanca().VratiSveFilmove().size() >= 0);
	}

	@Test
	void testIspravnoUnetiSviTextBoxovi() {
		String s1 = "A";
		String s2 = "B";
		assertTrue(Kontroler.Instanca().ispravnoUnetiSviTextBoxovi(s1,s2));
	}

	@Test
	void testIspravnoUnetiSviTextBoxoviNull() {
		String s1 = "A";
		String s2 = null;
		assertFalse(Kontroler.Instanca().ispravnoUnetiSviTextBoxovi(s1,s2));
	}

	@Test
	void testIspravnoUnetiSviTextBoxoviPrazanString() {
		String s1 = "A";
		String s2 = "";
		assertFalse(Kontroler.Instanca().ispravnoUnetiSviTextBoxovi(s1,s2));
	}

	@Test
	void testVratiSveGradove() {
		assertNotNull(Kontroler.Instanca().VratiSveGradove());
		
		assertTrue(Kontroler.Instanca().VratiSveGradove().size() > 0);
	}

	@Test
	void testProveriFormatDatuma() {
		String s = "2021-12-12";
		assertTrue(Kontroler.Instanca().proveriFormatDatuma(s));
	}
	@Test
	void testProveriFormatDatumaManjeInformacija() {
		String s = "2021-12";
		assertFalse(Kontroler.Instanca().proveriFormatDatuma(s));
	}

	@Test
	void testProveriFormatDatumaLosFormatGodina() {
		String s = "20212-12-12";
		assertFalse(Kontroler.Instanca().proveriFormatDatuma(s));
	}

	@Test
	void testProveriFormatDatumaLosFormatMesec() {
		String s = "2021-123-12";
		assertFalse(Kontroler.Instanca().proveriFormatDatuma(s));
	}

	@Test
	void testProveriFormatDatumaLosFormatDan() {
		String s = "2021-12-1";
		assertFalse(Kontroler.Instanca().proveriFormatDatuma(s));
	}

	@Test
	void testProveriFormatDatumaLosaGodina() {
		String s = "abcd-12-12";
		assertFalse(Kontroler.Instanca().proveriFormatDatuma(s));
	}

	@Test
	void testProveriFormatDatumaLosMesec() {
		String s = "2021-45-12";
		assertFalse(Kontroler.Instanca().proveriFormatDatuma(s));
	}

	@Test
	void testProveriFormatDatumaLosDan() {
		String s = "2021-12-63";
		assertFalse(Kontroler.Instanca().proveriFormatDatuma(s));
	}

	
	@Test
	void testProveriFormatVremena() {
		String s = "10:10:10";
		assertTrue(Kontroler.Instanca().proveriFormatVremena(s));
	}
	
	@Test
	void testProveriFormatVremenaManjakInformacija() {
		String s = "10:10";
		assertFalse(Kontroler.Instanca().proveriFormatVremena(s));
	}
	@Test
	void testProveriFormatVremenaLosFormatSat() {
		String s = "100:10:10";
		assertFalse(Kontroler.Instanca().proveriFormatVremena(s));
	}
	@Test
	void testProveriFormatVremenaLosFormatMinut() {
		String s = "10:100:10";
		assertFalse(Kontroler.Instanca().proveriFormatVremena(s));
	}
	@Test
	void testProveriFormatVremenaLosFormatSekunda() {
		String s = "10:10:100";
		assertFalse(Kontroler.Instanca().proveriFormatVremena(s));
	}
	void testProveriFormatVremenaLosSat() {
		String s = "26:10:10";
		assertFalse(Kontroler.Instanca().proveriFormatVremena(s));
	}
	@Test
	void testProveriFormatVremenaLosMinut() {
		String s = "10:78:10";
		assertFalse(Kontroler.Instanca().proveriFormatVremena(s));
	}
	@Test
	void testProveriFormatVremenaLosSekunda() {
		String s = "10:10:99";
		assertFalse(Kontroler.Instanca().proveriFormatVremena(s));
	}
	
	
	@Test
	void testProveriFormatDatumaIVremena() {
		String s = "2021-12-12 10:10:10";
		assertTrue(Kontroler.Instanca().proveriFormatDatumaIVremena(s));
	}

	@Test
	void testProveriFormatDatumaIVremenaLosFormat() {
		String s = "2021-12-12.10:10:10";
		assertFalse(Kontroler.Instanca().proveriFormatDatumaIVremena(s));
	}

}
