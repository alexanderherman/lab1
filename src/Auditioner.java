// Auditioner class representing an individual auditioning
import java.security.SecureRandom; 			// program uses class SecureRandom

public class Auditioner
{
	private final int unionID;				// unique ID
	private final String performanceType;	// type of performance
	private final String danceStyle;		// syle of dance if a dancer
	private final String musicalKey;		// key if a vocalist
	private int volume;						// volume, if provided, of a vocalist
	
	// two-argument constructor initializes ID, performanceType, style, key, and volume if applicable
	public Auditioner(int unionID, String performanceType)
	{
		// array of various dance styles
		String[] danceStyles = { "ballet", "belly", "swing", "folk", "modern", "tap", "hip-hop", "east coast swing", "tango", "waltz", "salsa", "samba", "jive", "charleston", 
			"quickstep", "boogie-woogie", "house", "cha-cha", "contra", "country", "latin", "contemporary", "kathak", "hustle", "flamenco", "bolero", "mambo", "jazz", "rhumba",
			"viennese waltz", "argentine tango", "lambada", "polka", "the Carlton" };
		// array of musical keys
		String[] musicalKeys = { "A", "B", "C", "D", "E", "F", "G" };
		SecureRandom randomNumbers = new SecureRandom();
		int randomValue;
		
		this.unionID = unionID;
		this.volume = 0;
		
		switch (performanceType)
		{
			default:															// defaults to performer in the case of unexpected arguments
				this.performanceType = "performer";
				this.danceStyle = "";
				this.musicalKey = "";
				break;
			
			case "dancer":
				this.performanceType = performanceType;
				randomValue = randomNumbers.nextInt(danceStyles.length);		// random number w/in the boundaries of the array of dance styles
				this.danceStyle = danceStyles[randomValue];						// randomly assigns a dance style
				this.musicalKey = "";
				break;
			
			case "vocalist":
				this.performanceType = performanceType;
				randomValue = randomNumbers.nextInt(musicalKeys.length);		// random number w/in the boundaries of the array of musical keys
				this.musicalKey = musicalKeys[randomValue];						// randomly assigns a musical key
				this.danceStyle = "";
				break;
				
			case "unknown":														// unkown performance should not be assigned
				this.performanceType = "unknown";
				this.musicalKey = "";						
				this.danceStyle = "";
				break;
				
		} // end switch
	}
	
	// method to retrieve the unionID from the object
	public int getUnionID()
	{
		return this.unionID;			// return value to caller
	}
	
	// method to retrieve the performanceType from the object
	public String getPerformanceType()
	{
		return this.performanceType;	// return value to caller
	}

	// method to retrieve the danceStyle from the object
	public String getDanceStyle()
	{
		return this.danceStyle;			// return value to caller
	}
	
	// method to retrieve the musicalKey from the object
	public String getMusicalKey()
	{
		return this.musicalKey;			// return value to caller
	}
	
	// method to set the volume of vocalist
	public void setVolume(int volumeValue)
	{
		this.volume = volumeValue;		// store the value
	}
	
	// method to retrieve the volume from the object
	public int getVolume()
	{
		return this.volume;				// return value to caller
	}
	
} // end class Auditioner