package rs.ac.bg.fon.ai.ZavrsniProjekat.Domen;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class GlumacTest {
	Glumac g;
	@BeforeEach
	void setUp() throws Exception {
		g = new Glumac();
	}

	@AfterEach
	void tearDown() throws Exception {
		g = null;
	}

	@Test
	@DisplayName("Testira ispravno unet id glumca")
	void testSetGlumacID() {
		g.setGlumacID(1);
		assertEquals(1, g.getGlumacID());
	}

	@Test
	@DisplayName("Testira neispravno unet id glumca (<0)")
	void testSetGlumacIDNegativanBroj() {
		assertThrows(java.lang.RuntimeException.class, 
				()-> g.setGlumacID(-1) );
	}

	
	@Test
	@DisplayName("Testira ispravno uneto ime i prezime")
	void testSetImePrezime() {
		g.setImePrezime("Brad Pitt");
		assertEquals("Brad Pitt", g.getImePrezime());	
	}
	
	@Test
	@DisplayName("Testira ako je uneta vrednost null umesto imena i prezimena")
	void testSetImePrezimeNull() {
		assertThrows(java.lang.NullPointerException.class, 
				()-> g.setImePrezime(null) );
	}
	
	@Test
	@DisplayName("Testira ako je uneto samo ime ili samo prezime")
	void testSetImePrezimeSamoJednaRec() {
		assertThrows(java.lang.RuntimeException.class, 
				()-> g.setImePrezime("Brad") );
	}
	
	@Test
	@DisplayName("Testira ako je uneta cifra u ime i prezime")
	void testSetImePrezimeCifraUneta() {
		assertThrows(java.lang.RuntimeException.class, 
				()-> g.setImePrezime("Brad1 Pitt2") );
	}
	

	@Test
	@DisplayName("Testiranje parametrizovanog konstruktora")
	void testGlumacIntStringInt() {
		g = new Glumac(1,"Brad Pit");
		assertNotNull(g);
		assertEquals(1, g.getGlumacID());
		assertEquals("Brad Pit", g.getImePrezime());
	}

	@Test
	@DisplayName("Testiranje toString metode")
	void testToString() {
		g.setImePrezime("Brad Pit");
		String s = g.toString();
		assertEquals("Brad Pit", s);
	}

	@Test
	@DisplayName("Testiranje neparametrizovanog konstruktora")
	void testGlumac() {
		assertNotNull(g);
	}

	@ParameterizedTest
	@CsvSource ({
		"1, Brad Pit , 1, Brad Pit, true",
		"2, Brad Pit , 1, Brad Pit, false",
		"1, Brad Pit , 2, Brad Pit, false",
		"1, Will Smith, 1, Brad Pit, false"
	})
	@DisplayName("Testiranje equals metode")
	void testEqualsObject(int glumacID1, String imePrezime1, 
			int glumacID2, String imePrezime2,
			boolean eq) {
		g.setGlumacID(glumacID1);
		g.setImePrezime(imePrezime1);

		
		Glumac g2 = new Glumac();
		g2.setGlumacID(glumacID2);
		g2.setImePrezime(imePrezime2);
		
		assertEquals(eq, g.equals(g2));
	}
}
