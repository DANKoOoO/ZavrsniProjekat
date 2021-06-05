package rs.ac.bg.fon.ai.ZavrsniProjekat.Domen;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


class GradTest {
	private Grad g;
	@BeforeEach
	void setUp() throws Exception {
		g = new Grad();
	}

	@AfterEach
	void tearDown() throws Exception {
		g = null;
	}

	@Test
	@DisplayName("Testira neispravno unet id grada (<0)")
	void testSetGradID() {
		g.setGradID(1);
		assertEquals(1, g.getGradID());
	}

	@Test
	@DisplayName("Testira ispravno unet id grada")
	void testSetGradIDNegativanBroj() {
		assertThrows(java.lang.RuntimeException.class, 
				()-> g.setGradID(-1) );
	}
	
	@Test
	@DisplayName("Testira ispravno unet naziv grada")
	void testSetNaziv() {
		g.setNaziv("Beograd");
		assertEquals("Beograd", g.getNaziv());
	}
	
	@Test
	@DisplayName("Testira ako je uneta vrednost null umesto naziva grada")
	void testSetNazivNull() {
		assertThrows(java.lang.NullPointerException.class, 
				()-> g.setNaziv(null));
	}
	
	@Test
	@DisplayName("Testira ako je unet prazan string umesto naziva grada")
	void testSetNazivPrazanString() {
		assertThrows(java.lang.RuntimeException.class, 
				()-> g.setNaziv(""));
	}
	
	@Test
	@DisplayName("Testira ispravno unet naziv drzave")
	void testSetDrzava() {
		g.setDrzava("Srbija");
		assertEquals("Srbija", g.getDrzava());
	}

	@Test
	@DisplayName("Testira ako je uneta vrednost null umesto naziva drzave")
	void testSetDrzavaNull() {
		assertThrows(java.lang.NullPointerException.class, 
				()-> g.setDrzava(null));
	}
	
	@Test
	@DisplayName("Testira ako je unet prazan string umesto naziva drzave")
	void testSetDrzavaPrazanString() {
		assertThrows(java.lang.RuntimeException.class, 
				()-> g.setDrzava(""));
	}
	
	@Test
	@DisplayName("Testiranje toString metode")
	void testToString() {
		g.setNaziv("Beograd");
		g.setDrzava("Srbija");
		
		String s = g.toString();
		assertTrue(s.contains("Beograd"));
		assertTrue(s.contains("Srbija"));
	}

	@Test
	@DisplayName("Testiranje parametrizovanog konstruktora")
	void testGradIntStringString() {
		g = new Grad(1, "Beograd", "Srbija");
		assertNotNull(g);
		assertEquals(1, g.getGradID());
		assertEquals("Beograd", g.getNaziv());
		assertEquals("Srbija", g.getDrzava());
	}

	@Test
	@DisplayName("Testiranje neparametrizovanog konstruktora")
	void testGrad() {
		assertNotNull(g);
	}

	@ParameterizedTest
	@CsvSource ({
		"1, Beograd , Srbija, 1, Beograd, Srbija, true",
		"2, Beograd , Srbija, 1, Beograd, Srbija, false",
		"1, Smederevo, Srbija, 1, Beograd, Srbija, false",
		"1, Beograd, Italija, 1, Beograd, Srbija, false"
	})
	@DisplayName("Testiranje equals metode")
	void testEqualsObject(int gradID1, String naziv1, String drzava1,
			int gradID2, String naziv2, String drzava2,
			boolean eq) {
		g.setGradID(gradID1);
		g.setNaziv(naziv1);
		g.setDrzava(drzava1);

		Grad g2 = new Grad();
		g2.setGradID(gradID2);
		g2.setNaziv(naziv2);
		g2.setDrzava(drzava2);
		
		assertEquals(eq, g.equals(g2));
	}

}
