package test;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import model.Asiakas;
import model.dao.Dao;

@TestMethodOrder(OrderAnnotation.class)
class JUnit_testaa_autot {

	@Test
	@Order(1) 
	public void testPoistaKaikkiAsiakkaat() {
		//Poistetaan kaikki asiakkaat
		Dao dao = new Dao();
		dao.poistaKaikkiAsiakkaat("nimda");
		ArrayList<Asiakas> asiakkaat = dao.listaaKaikki();
		assertEquals(0, asiakkaat.size());
	}
	
	@Test
	@Order(2) 
	public void testLisaaAsiakas() {		
		//Tehd‰‰n muutama uusi testiasiakas
		Dao dao = new Dao();
		Asiakas asiakas_1 = new Asiakas(18, "Anna", "Alanko", "09-123234", "anna-alanko@sposti.fi");
		Asiakas asiakas_2 = new Asiakas(19, "Benjami", "Backlund", "040-123234", "benjami@sposti.fi");
		assertEquals(true, dao.lisaaAsiakas(asiakas_1));
		assertEquals(true, dao.lisaaAsiakas(asiakas_2));
	}
	
	@Test
	@Order(3) 
	public void testMuutaAsiakas() {
		//Muutetaan yht‰ asiakasta
		Dao dao = new Dao();
		Asiakas muutettava = dao.etsiAsiakas(18);
		muutettava.setEtunimi("Anja");
		muutettava.setSukunimi("Alanko");
		muutettava.setPuhelin("050-123234");
		muutettava.setSposti("anja.alanko@sposti.fi");
		dao.muutaAsiakas(muutettava);	
		assertEquals("Anja", dao.etsiAsiakas(18).getEtunimi());
		assertEquals("Alanko", dao.etsiAsiakas(18).getSukunimi());
		assertEquals("050-123234", dao.etsiAsiakas(18).getPuhelin());
		assertEquals("anja.alanko@sposti.fi", dao.etsiAsiakas(18).getSposti());
	}
	
	@Test
	@Order(4) 
	public void testPoistaAsiakas() {
		Dao dao = new Dao();
		dao.poistaAsiakas(18);
		assertEquals(null, dao.etsiAsiakas(18));
	}

}
