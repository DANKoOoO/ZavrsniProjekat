package rs.ac.bg.fon.ai.ZavrsniProjekat.Domen;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.sql.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class FestivalTest {

	private Festival f;
	@BeforeEach
	void setUp() throws Exception {
		f = new Festival();
	}

	@AfterEach
	void tearDown() throws Exception {
		f = null;
	}

	@Test
	@DisplayName("Testira se ispravno unet id festivala")
	void testSetFestivalID() {
		f.setFestivalID(1);
		assertEquals(1, f.getFestivalID());
	}
	
	@Test
	@DisplayName("Testira se neispravno unet id festivala (<0)")
	void testSetFestivalIDNegativanBroj() {
		assertThrows(java.lang.RuntimeException.class, 
				()-> f.setFestivalID(-1));
	}

	@Test
	@DisplayName("Testira se ispravno unet naziv festivala")
	void testSetNaziv() {
		f.setNaziv("Festival");
		assertEquals("Festival", f.getNaziv());
	}

	@Test
	@DisplayName("Testira se ako je uneta vrednost null umesto naziva festivala")
	void testSetNazivNull() {
		assertThrows(java.lang.NullPointerException.class, 
				()-> f.setNaziv(null));
	}

	@Test
	@DisplayName("Testira se ako je unet prazan string umesto naziva festivala")
	void testSetNazivPrazanString() {
		assertThrows(java.lang.RuntimeException.class, 
				()-> f.setNaziv(""));
	}

	
	@Test
	@DisplayName("Testira se ispravno unet datum pocetka")
	void testSetDatumOd() {
		LocalDate sutra = LocalDate.now().plusDays(1);
		Date dt = Date.valueOf(sutra);
		
		f.setDatumOd(dt);
		assertEquals(dt, f.getDatumOd());
	}
	
	@Test
	@DisplayName("Testira se ako je uneta vrednost null umesto datuma pocetka")
	void testSetDatumOdNull() {
		assertThrows(java.lang.NullPointerException.class, 
				()-> f.setDatumOd(null));
	}
	
	
	@Test
	@DisplayName("Testira se ako unet datum pocetka pre danasnjeg")
	void testSetDatumOdPreDanasnjeg() {
		Date dt = new Date(0);
		assertThrows(java.lang.RuntimeException.class, 
				()-> f.setDatumOd(dt));
	}
	
	@Test
	@DisplayName("Testira se ispravno unet datum zavrsetka")
	void testSetDatumDo() {	
		LocalDate sutra = LocalDate.now().plusDays(1);
		LocalDate danPosle = sutra.plusDays(1);
		Date dt = Date.valueOf(sutra);
		Date dt1 = Date.valueOf(danPosle);
		f.setDatumOd(dt);
		f.setDatumDo(dt1);
		assertEquals(dt1, f.getDatumDo());
	}
	
	@Test
	@DisplayName("Testira se ako je uneta vrednost null umesto datuma zavrsetka")
	void testSetDatumDoNull() {
		assertThrows(java.lang.NullPointerException.class, 
				()-> f.setDatumDo(null));
	}

	@Test
	@DisplayName("Testira se ako unet datum zavrsetka pre pocetnog datuma")
	void testSetDatumDoPrePocetka() {
		LocalDate sutra = LocalDate.now().plusDays(1);
		LocalDate danPre = sutra.minusDays(1);
		Date dt = Date.valueOf(sutra);
		Date dt1 = Date.valueOf(danPre);
		f.setDatumOd(dt);

		assertThrows(java.lang.RuntimeException.class,
				()->f.setDatumDo(dt1));
	}

	@Test
	@DisplayName("Testira se ispravno unet id grada")
	void testSetGradID() {
		f.setGradID(1);
		assertEquals(1, f.getGradID());
	}

	
	@Test
	@DisplayName("Testira se neispravno unet id grada (<0)")
	void testSetGradIDNegativanBroj() {
		assertThrows(java.lang.RuntimeException.class, 
				()-> f.setGradID(-1));
	}

	@Test
	@DisplayName("Testiranje toString metode")
	void testToString() {
		f.setNaziv("Festival");
		f.setGradID(1);
		LocalDate sutra = LocalDate.now().plusDays(1);
		LocalDate danPosle = sutra.plusDays(1);
		Date dt = Date.valueOf(sutra);
		Date dt1 = Date.valueOf(danPosle);
		f.setDatumOd(dt);
		f.setDatumDo(dt1);
		
		String s = f.toString();
		assertTrue(s.contains("Festival"));
		assertTrue(s.contains("1"));
		assertTrue(s.contains(dt.toString()));
		assertTrue(s.contains(dt1.toString()));
	}

	@Test
	@DisplayName("Testiranje parametrizovanog konstruktora")
	void testFestivalIntStringDateDateInt() {
		LocalDate sutra = LocalDate.now().plusDays(1);
		LocalDate danPosle = sutra.plusDays(1);
		Date dt = Date.valueOf(sutra);
		Date dt1 = Date.valueOf(danPosle);
		f.setDatumOd(dt);
		f.setDatumDo(dt1);
		
		f = new Festival(1, "Festival", dt, dt1, 2);
		
		assertNotNull(f);
		assertEquals("Festival", f.getNaziv());
		assertEquals(1, f.getFestivalID());
		assertEquals(2, f.getGradID());
		assertEquals(dt, f.getDatumOd());
		assertEquals(dt1, f.getDatumDo());
	}

	@Test
	@DisplayName("Testiranje neparametrizovanog konstruktora")
	void testFestival() {
		assertNotNull(f);
	}

	@ParameterizedTest
	@CsvSource ({
		"1, Festival, 9999-12-25, 9999-12-26, 2, 1, Festival, 9999-12-25, 9999-12-26, 2, true",
		"9, Festival, 9999-12-25, 9999-12-26, 2, 1, Festival, 9999-12-25, 9999-12-26, 2, false",
		"1, fest, 9999-12-25, 9999-12-26, 2, 1, Festival, 9999-12-25, 9999-12-26, 2, false",
		"1, Festival, 9999-12-24, 9999-12-26, 2, 1, Festival, 9999-12-25, 9999-12-26, 2, false",
		"1, Festival, 9999-12-25, 9999-12-27, 2, 1, Festival, 9999-12-25, 9999-12-26, 2, false",
		"1, Festival, 9999-12-25, 9999-12-26, 9, 1, Festival, 9999-12-25, 9999-12-26, 2, false"
	})
	@DisplayName("Testiranje equals metode")
	void testEqualsObject(int festivalID1, String naziv1, String datumOd1, String datumDo1, int gardID1,
			int festivalID2, String naziv2, String datumOd2, String datumDo2, int gardID2,
			boolean eq) {
		f.setFestivalID(festivalID1);
		f.setNaziv(naziv1);
		f.setGradID(gardID1);
		
		Date dt = Date.valueOf(datumOd1);
		Date dt1 = Date.valueOf(datumDo1);
		
		f.setDatumOd(dt);
		f.setDatumDo(dt1);
		
		Festival f2 = new Festival();
		f2.setFestivalID(festivalID2);
		f2.setNaziv(naziv2);
		f2.setGradID(gardID2);
		
		Date dt2 = Date.valueOf(datumOd2);
		Date dt3 = Date.valueOf(datumDo2);
		
		f2.setDatumOd(dt2);
		f2.setDatumDo(dt3);
		
		assertEquals(eq, f.equals(f2));
	}

}
