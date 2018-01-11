// CardinalToOrdinal class returns ordinal string of integer

public class CardinalIntegerToOrdinalString
{
	private String cardinalValue;

	// returns the Ordinal string of a number (1st, 2nd, 3rd, etc.)
	public CardinalIntegerToOrdinalString(int numberToConvert)
	{
	   this.cardinalValue = "";
		
	   String numberString = Integer.toString(numberToConvert);
	   String rightmostDigit = numberString.substring(numberString.length() - 1);
	   String lastTwoDigits = "";

	   if (numberString.length() > 1)
		  lastTwoDigits = numberString.substring(numberString.length() - 2);
	   
	   if ( lastTwoDigits.equals("11") || lastTwoDigits.equals("12") || lastTwoDigits.equals("13") )
	   {
		  this.cardinalValue = numberString + "th";
	   }
	   else
	   {
		  switch (rightmostDigit)
		  {
			 case "1":
				this.cardinalValue = numberString + "st";
				break;
			 case "2":
				this.cardinalValue = numberString + "nd";
				break;
			 case "3":
				this.cardinalValue = numberString + "rd";
				break;
			 default:
				this.cardinalValue = numberString + "th";
		  }
	   }
	} 
	
	public void setCardinalValue(String cardinalValue)
	{
		this.cardinalValue = cardinalValue;		// store the value
	}
	
	public String getCardinalValue()
	{
		return this.cardinalValue;
	}
	
} // end class CardinalIntegerToOrdinalString