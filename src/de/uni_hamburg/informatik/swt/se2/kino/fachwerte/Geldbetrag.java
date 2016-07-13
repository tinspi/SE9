package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

/** 
 * Implementiert den Fachwert Geldbetrag. 
 * @author tinspi
 *
 */

public final class Geldbetrag implements Comparable<Geldbetrag>
{

	private final int _eurocent;
	private final static String _betragMitNachkommastelle = "\\d{1,7},\\d{0,2}";
	private final static String _betrag = "\\d{1,7}";
	
	
	/**
	 * Initialisiert ein neues Exemplar eines Geldbetrags mittels Int 
	 * @param eurocent Der Betrag in cent. 
	 * 
	 * @require inputZahl >= 0
	 */
	public Geldbetrag(int inputZahl)
	{
		
		assert validate(inputZahl) : "Vorbedingung verletzt: validate inputZahl";
		
		_eurocent = inputZahl; 
	}
		
	/**
	 * Prüft ob eine Zahl >= 0 ist.
	 * @param inputZahl
	 * @return
	 */
	public boolean validate(int inputZahl) {
		return inputZahl >= 0;
	}

	
	
	/**
	 * Initialisiert ein neues Exemplar eines Geldbetrags mittels String
	 * @param inputString der übergebene String
	 * @require validate(inputString) 
	 */
	
	public Geldbetrag(String inputString)
	{
		 
        // assert validate(inputString) : "Vorbedingung verletzt: validate inputString";

		
		if (inputString.matches(_betragMitNachkommastelle))
	        {
	            double dezimal = Double.parseDouble(inputString.replace(",", "."));
	            _eurocent = (int)(Math.ceil(dezimal*100));
	        }
	        else if (inputString.matches(_betrag))
	        {
	            _eurocent = Integer.parseInt(inputString)*100;
	        }
	        else 
	        {
	            _eurocent = 0;
	        }
	}
	
	/**
	 * Prüft ob der eingegebene String dem Pattern entspricht
	 * @param inputString
	 * @return
	 */
	public boolean validate(String inputString) {
		
		return inputString.matches(_betragMitNachkommastelle) || inputString.matches(_betrag);
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
		int euro = getEuro();
		int cent = getCent();
		
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
	 * 
	 * @require isAdditionPossible()
	 * 
	 * @return das Ergebnis der Addition in Eurocent
	 */
	
	public static Geldbetrag add(Geldbetrag sum1, Geldbetrag sum2)
	{
		assert sum1.isAdditionPossible(sum2) : "Vorbedingung verletzt: Summe wäre größer als Integer.MAX_VALUE";
		
		return new Geldbetrag(sum1._eurocent + sum2._eurocent);
	}
	
	/**
     * Addieren von zwei Geldbeträgen
     * @param other
     * 
     * @require isAdditionPossible()
     * 
     * @return das Ergebnis der Addition in Eurocent
     */
	public Geldbetrag add(Geldbetrag other)
    {
        assert this.isAdditionPossible(other) : "Vorbedingung verletzt: Summe wäre größer als Integer.MAX_VALUE";
        
        return new Geldbetrag(this._eurocent + other.getEurocent());
    }
	
	
	/**
	 * Prüft ob Addition möglich ist
	 * @param other
	 * @return
	 */
	public boolean isAdditionPossible(Geldbetrag other) {
		
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
	
	public Geldbetrag sub(Geldbetrag other)
	{
	    if (this.compareTo(other) < 0)
	    {
	        return new Geldbetrag(other.getEurocent() - this._eurocent);
	    }
	    else
	    {
	        return new Geldbetrag(this.getEurocent() - other.getEurocent());
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
	
	/**
     * Multipliziert den Geldbetrag mit einem Faktor 
     * @param faktor
     * @require isMultiplyPossible()
     * @return das Ergebnis der Multiplikation in Eurocent
     */
	public Geldbetrag multiply(int faktor)
    {
        assert this.isMultiplyPossible(faktor) : "Vorbedingung verletzt: Produkt wäre außerhalb des Integer-Bereichs";

        return new Geldbetrag(_eurocent * faktor); 
    }
	
	
	/**
     * Prüft ob Multiplizieren möglich ist.
     * @retrun 
     */
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
