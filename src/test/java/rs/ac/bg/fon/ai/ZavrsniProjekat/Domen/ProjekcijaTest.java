package rs.ac.bg.fon.ai.ZavrsniProjekat.Domen;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ProjekcijaTest {

	private Projekcija p;
	@BeforeEach
	void setUp() throws Exception {
		p = new Projekcija();
	}

	@AfterEach
	void tearDown() throws Exception {
		p = null;
	}

	@Test
	@DisplayName("Testira ispravno unet id projekcije")
	void testSetProjekcijaID() {
		p.setProjekcijaID(1);
		assertEquals(1, p.getProjekcijaID());
	}

	@Test
	@DisplayName("Testira neispravno unet id projekcije (<0)")
	void testSetProjekcijaIDNegativanBroj() {
		assertThrows(java.lang.RuntimeException.class,
				()-> p.setProjekcijaID(-1));
	}

	@Test
	@DisplayName("Testira ispravno unet id festivala")
	void testSetFestivalID() {
		p.setFestivalID(1);
		assertEquals(1, p.getFestivalID());
	}

	@Test
	@DisplayName("Testira neispravno unet id festivala (<0)")
	void testSetFestivalIDnegativanBroj() {
		assertThrows(java.lang.RuntimeException.class,
				()-> p.setFestivalID(-1));
	}
	
	@Test
	@DisplayName("Testira ispravno unet datum i vreme projekcije")
	void testSetDatumVremeProjekcije() {
		fail("Not yet implemented");
	}
	
	@Test
	@DisplayName("Testira ako je uneta vrednost null imesto datuma i vremena projekcije")
	void testSetDatumVremeProjekcijeNull() {
		assertThrows(java.lang.NullPointerException.class,
				()-> p.setDatumVremeProjekcije(null));
	}
	
	@Test
	@DisplayName("Testira ako je unet datum i vreme projekcije pre sadasnjeg")
	void testSetDatumVremeProjekcijeProsaoDatum() {
		Timestamp ts = new Timestamp(0);
		assertThrows(java.lang.RuntimeException.class,
				()-> p.setDatumVremeProjekcije(ts));
	}

	@Test
	@DisplayName("Testira ispravno unet id filma")
	void testSetFilmID() {
		p.setFilmID(1);
		assertEquals(1, p.getFilmID());
	}
	
	@Test
	@DisplayName("Testira neispravno unet id filma (<0)")
	void testSetFilmIDNegativanBroj() {
		assertThrows(java.lang.RuntimeException.class,
				()-> p.setFilmID(-1));
	}

	@Test
	@DisplayName("Testiranje parametrizovanog konstruktora")
	void testProjekcijaIntIntTimestampInt() {
		p = new Projekcija(1,2,new Timestamp(System.currentTimeMillis()),3);
		assertNotNull(p);
		assertEquals(1, p.getProjekcijaID());
		assertEquals(2, p.getFestivalID());
		assertEquals(3, p.getFilmID());
	}

	@Test
	@DisplayName("Testiranje neparametrizovanog konstruktora")
	void testProjekcija() {
		assertNotNull(p);
	}

	@ParameterizedTest
	@CsvSource ({
		"1, 2, 2020-12-12 01:00:00, 3, 1, 2, 2020-12-12 01:00:00, 3, true",
		"9, 2, 2020-12-12 01:00:00, 3, 1, 2, 2020-12-12 01:00:00, 3, false",
		"1, 9, 2020-12-12 01:00:00, 3, 1, 2, 2020-12-12 01:00:00, 3, false",
		"1, 2, 2020-12-12 21:00:00, 3, 1, 2, 2020-12-12 01:00:00, 3, false",
		"1, 2, 2020-12-12 01:00:00, 9, 1, 2, 2020-12-12 01:00:00, 3, false"
	})
	@DisplayName("Testiranje equals metode")
	void testEqualsObject(int projekcijaID1, int festivalID1, String datumVreme1, int filmID1,
			int projekcijaID2, int festivalID2, String datumVreme2, int filmID2,
			boolean eq) {
		Timestamp dv1 = Timestamp.valueOf(datumVreme1);
		Timestamp dv2 = Timestamp.valueOf(datumVreme2);
		
		p.setProjekcijaID(projekcijaID1);
		p.setFestivalID(festivalID1);
		p.setDatumVremeProjekcije(dv1);
		p.setFilmID(filmID1);
		
		Projekcija p2 = new Projekcija();
		p2.setProjekcijaID(projekcijaID2);
		p2.setFestivalID(festivalID2);
		p2.setDatumVremeProjekcije(dv2);
		p2.setFilmID(filmID2);
		
		assertEquals(eq, p.equals(p2));
	}

}
