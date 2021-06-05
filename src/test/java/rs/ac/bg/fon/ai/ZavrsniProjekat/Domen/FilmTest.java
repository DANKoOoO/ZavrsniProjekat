package rs.ac.bg.fon.ai.ZavrsniProjekat.Domen;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class FilmTest {
	private Film f;
	@BeforeEach
	void setUp() throws Exception {
		f = new Film();
	}

	@AfterEach
	void tearDown() throws Exception {
		f = null;
	}

	@Test
	@DisplayName("Testira ispravno unet id filma")
	void testSetFilmID() {
		f.setFilmID(1);
		assertEquals(1, f.getFilmID());
	}

	@Test
	@DisplayName("Testira neispravno unet id filma (<0)")
	void testSetFilmIDNegativanBroj() {
		assertThrows(java.lang.RuntimeException.class,
				()-> f.setFilmID(-1));
	}

	
	@Test
	@DisplayName("Testira ispravno unet naziv filma")
	void testSetNaziv() {
		f.setNaziv("Bad Boys");
		assertEquals("Bad Boys", f.getNaziv());
	}
	
	@Test
	@DisplayName("Testira ako je uneta vrednost null umesto naziva filma")
	void testSetNazivNull() {
		assertThrows(java.lang.NullPointerException.class,
				()-> f.setNaziv(null));
	}
	
	@Test
	@DisplayName("Testira ako je unet prazan stringl umesto naziva filma")
	void testSetNazivPrazanString() {
		assertThrows(java.lang.RuntimeException.class,
				()-> f.setNaziv(""));
	}

	@Test
	@DisplayName("Testira ispravno uneto ime i prezime rezisera")
	void testSetReziser() {
		f.setReziser("Vin Disel");
		assertEquals("Vin Disel", f.getReziser());
	}
	
	@Test
	@DisplayName("Testira ako je uneta vrednost null umesto imena i prezimena rezisera")
	void testSetReziserNull() {
		assertThrows(java.lang.NullPointerException.class,
				()-> f.setReziser(null));
	}
	
	@Test
	@DisplayName("Testira ako je uneto samo ime ili samo prezime rezisera")
	void testSetReziserImeIPrezime() {
		assertThrows(java.lang.RuntimeException.class,
				()-> f.setReziser("Vin"));
	}

	@Test
	@DisplayName("Testira ako su unete cifre u ime ili prezime rezisera")
	void testSetReziserCifreUImenu() {
		assertThrows(java.lang.RuntimeException.class,
				()-> f.setReziser("Vin1 Disel2"));
	}
	
	@Test
	@DisplayName("Testira ispravno unetu godinu filma")
	void testSetGodina() {
		f.setGodina(2000);
		assertEquals(2000, f.getGodina());
	}

	@Test
	@DisplayName("Testira da li je godina izmedju 1900 i sadasnje godine")
	void testSetGodinaPogresnaGodina() {
		assertThrows(java.lang.RuntimeException.class,
				()-> f.setGodina(1889));
	}

	
	@Test
	@DisplayName("Testiranje toString metodu")
	void testToString() {
		f.setNaziv("Bad Boys");
		f.setReziser("Vin Disel");
		f.setGodina(2000);
		
		String s = f.toString();
		assertTrue(s.contains("Bad Boys"));
		assertTrue(s.contains("Vin Disel"));
		assertTrue(s.contains("2000"));
	}

	@Test
	@DisplayName("Testiranje parametrizovanog konstruktora")
	void testFilmIntStringStringInt() {
		f = new Film(1, "Bad Boys", "Vin Disel", 2000);
		assertNotNull(f);
		assertEquals(1, f.getFilmID());
		assertEquals("Bad Boys", f.getNaziv());
		assertEquals("Vin Disel", f.getReziser());
		assertEquals(2000, f.getGodina());
	}

	@Test
	@DisplayName("Testiranje neparametrizovanog konstruktora")
	void testFilm() {
		assertNotNull(f);
	}
	
	@ParameterizedTest
	@CsvSource ({
		"1, Bad Boys, Vin Disel 2000, 1, Bad Boys, Vin Disel 2000, true",
		"2, Bad Boys, Vin Disel 2000, 1, Bad Boys, Vin Disel 2000, false",
		"1, Fast and Furious, Vin Disel 2000, 1, Bad Boys, Vin Disel 2000, false",
		"1, Bad Boys, Vin Disel 2001, 1, Bad Boys, Vin Disel 2000, false"
	})
	@DisplayName("Testiranje equals metode")
	void testEqualsObject(int filmID1, String naziv1, String reziser1, int godina1,
			int filmID2, String naziv2, String reziser2, int godina2,
			boolean eq) {
		f.setFilmID(filmID1);
		f.setNaziv(naziv1);
		f.setReziser(reziser1);
		f.setGodina(godina1);
		
		Film f2 = new Film();
		f2.setFilmID(filmID2);
		f2.setNaziv(naziv2);
		f2.setReziser(reziser2);
		f2.setGodina(godina2);
		
		assertEquals(eq, f.equals(f2));
	}

}
