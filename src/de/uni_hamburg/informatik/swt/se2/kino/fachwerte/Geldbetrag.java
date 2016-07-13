package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

/** 
 * Implementiert den Fachwert Geldbetrag. 
 * @author tinspi
 *
 */

public final class Geldbetrag implements Comparable<Geldbetrag>
{

	private final int _eurocent;
	
	
	/**
	 * Initialisiert ein neues Exemplar eines Geldbetrags mittels Int 
	 * @param eurocent Der Betrag in cent. 
	 * 
	 * @require cent != oder < 0
	 */
	public Geldbetrag(int inputZahl)
	{
		
		assert validate(inputZahl) : "Vorbedingung verletzt: validate inputZahl";
		
		_eurocent = inputZahl; 
	}
		
	private boolean validate(int inputZahl) {
		return inputZahl < Integer.MAX_VALUE && inputZahl >= 0;
	}

	
	
	/**
	 * Initialisiert ein neues Exemplar eines Geldbetrags mittels String
	 * @param inputString der übergebene String
	 * @require validate inputString 
	 */
	
	public Geldbetrag(String inputString)
	{
		 
        assert validate(inputString) : "Vorbedingung verletzt: validate inputString";

		
		if (inputString.matches("[0-9]{1,7},[0-9]{2}"))
	        {
	            _eurocent = Integer.parseInt(inputString.replace(",", ""));
	        }
	        else if (inputString.matches("[-]?[0-9]{1,7}"))
	        {
	            _eurocent = Integer.parseInt(inputString)*100;
	        }
	        else 
	        {
	            _eurocent = 0;
	        }
	}
	
	private boolean validate(String inputString) {
		
		return inputString.matches("[0-9]{1,7},[0-9]{2}") || inputString.matches("[-]?[0-9]{1,7}");
	}




	/**
	 * Liefert den Euro-Betrag des Geldbetrags zurück
	 * @return 
	 */
	
	public int getEuro()
	{
		return _eurocent/100;
	}
	
	
	
	/**
	 * Liefert den Cent-Betrag des Geldbetrags zurück
	 * @return
	 */
	public int getCent()
	{
		return Math.abs(_eurocent % 100); 
	}
	
	/**
	 * Liefert den Eurocent
	 * @return
	 */
	public int getEurocent()
	{
		return _eurocent;
	}
	
	
	/**
	 * Liefert den hashCode 
	 */
	
	public int hashCode()
	    {
	        return _eurocent;
	    }
	
	/**
	 * Gibt einen String in Form von "Euro,Cent" zurück.
	 * @ensure result != ""
	 */
	
	public String toString()
	{
		int euro = _eurocent / 100;
		int cent = Math.abs(_eurocent % 100);
		
		String centString;
		
		
		if (cent < 10)
		{
			centString = "0" + cent;
		}
		
		else
		{
			centString = Integer.toString(cent);
		}
		
		return euro + "," + centString;
	}
	
	/**
	 * Addieren von zwei Geldbeträgen
	 * @param sum1
	 * @param sum2
	 * @return das Ergebnis der Addition in Eurocent
	 */
	
	public static Geldbetrag add(Geldbetrag sum1, Geldbetrag sum2)
	{
		assert sum1.isAdditionPossible(sum2) : "Vorbedingung verletzt: Summe wäre größer als Integer.MAX_VALUE";
		
		return new Geldbetrag(sum1._eurocent + sum2._eurocent);
	}
	
	
	private boolean isAdditionPossible(Geldbetrag other) {
		
		return  ((long)this._eurocent + (long)other._eurocent) < Integer.MAX_VALUE;
	}




	/**
	 * Subrathieren des größeren vom kleineren Geldbetrag
	 * @param sub1
	 * @param sub2
	 * @return das Ergebnis der Subtraktion in Eurocent
	 */
	public static Geldbetrag diff(Geldbetrag sub1, Geldbetrag sub2)
	{
		int betrag1 = sub1._eurocent;
		int betrag2 = sub2._eurocent;
		
		if (betrag1 > betrag2 || betrag1 == betrag2)
		{
			return new Geldbetrag(betrag1 - betrag2);
		}
		else 
		{
			return new Geldbetrag(betrag2 - betrag1);
		}
		
	}
	

	/**
	 * Multipliziert einen Geldbetrag mit einem Faktor 
	 * @param geldbetrag
	 * @param faktor
	 * @return das Ergebnis der Multiplikation in Eurocent
	 */
	
	public static Geldbetrag multiply(Geldbetrag geldbetrag, int faktor)
	{
		
        assert geldbetrag.isMultiplyPossible(faktor) : "Vorbedingung verletzt: Produkt wäre außerhalb des Integer-Bereichs";


		return new Geldbetrag(geldbetrag._eurocent * faktor); 
	}
	
	
	
	
	
	private boolean isMultiplyPossible(int other) {

		return ((long)this._eurocent * (long)other) < Integer.MAX_VALUE;
	}




	@Override
    public boolean equals(Object other)
    {
        return (other instanceof Geldbetrag) && equals((Geldbetrag)other);
    }
    
	
    public boolean equals(Geldbetrag other)
    {
        return (_eurocent == other._eurocent);
    }

    
	@Override
	public int compareTo(Geldbetrag other) {
		
		return _eurocent - other._eurocent;
	}
        
	
}
