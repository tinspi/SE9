package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

import static org.junit.Assert.*;

import org.junit.Test;

public class GeldbetragTest {

	@Test
	public void testGeldbetragGetter() 
	{
		Geldbetrag betrag = new Geldbetrag(100);
		assertEquals(1, betrag.getEuro());
		assertEquals(0, betrag.getCent());
		assertEquals("1,00", betrag.toString());
		
		betrag = new Geldbetrag(0);
		assertEquals(0, betrag.getEuro());
		assertEquals(0, betrag.getCent());
		assertEquals("0,00", betrag.toString());
		
		betrag = new Geldbetrag(99);
		assertEquals(0, betrag.getEuro());
		assertEquals(99, betrag.getCent());
		assertEquals("0,99", betrag.toString());
		
		betrag = new Geldbetrag(101);
		assertEquals(1, betrag.getEuro());
		assertEquals(01, betrag.getCent());
		assertEquals("1,01", betrag.toString());
	}
	
	@Test
	public void testEqualsHashcode()
	{
		Geldbetrag betrag1 = new Geldbetrag(100);
		Geldbetrag betrag2 = new Geldbetrag(100);
		
		assertTrue(betrag1.equals(betrag2));
		assertTrue(betrag1.hashCode() == betrag2.hashCode());
		
		Geldbetrag betrag3 = new Geldbetrag(101);
		assertFalse(betrag3.equals(betrag1));
		assertFalse(betrag3.hashCode() == betrag1.hashCode());
	}
	
	@Test
	public void testAdd()
	{
		Geldbetrag betrag1 = new Geldbetrag(500);
		Geldbetrag betrag2 = new Geldbetrag(138);
		assertEquals(6, Geldbetrag.add(betrag1, betrag2).getEuro());
		assertEquals(38, Geldbetrag.add(betrag1, betrag2).getCent());
		
	}

	@Test
	public void testSub()
	{
		Geldbetrag betrag1 = new Geldbetrag(500);
		Geldbetrag betrag2 = new Geldbetrag(138);
		assertEquals(3, Geldbetrag.diff(betrag1, betrag2).getEuro());
		assertEquals(62, Geldbetrag.diff(betrag1, betrag2).getCent());
		
	}

	@Test
	public void testMultiply()
	{
		Geldbetrag betrag1 = new Geldbetrag(50);
		int faktor = 2;
		assertEquals(1, Geldbetrag.multiply(betrag1, faktor).getEuro());
		assertEquals(0, Geldbetrag.multiply(betrag1, faktor).getCent());
		assertEquals("1,00", Geldbetrag.multiply(betrag1, faktor).toString());
	}
	

	@Test
	public void testParser()
	{
		String betrag = "40,55";
		
		assertEquals(40, new Geldbetrag(betrag).getEuro());
		assertEquals(55, new Geldbetrag(betrag).getCent());
		assertEquals("40,55", new Geldbetrag(betrag).toString());
	}
	
	@Test
	public void testcompareTo()
	{
		Geldbetrag betrag1 = new Geldbetrag(499);
		Geldbetrag betrag2 = new Geldbetrag(500);
		Geldbetrag betrag3 = new Geldbetrag(250);
		Geldbetrag betrag4 = new Geldbetrag(499);
		
		assertTrue(betrag1.compareTo(betrag2) < 0);
		assertTrue(betrag1.compareTo(betrag3) > 0);
		assertTrue(betrag1.compareTo(betrag4) == 0);
	}
}
