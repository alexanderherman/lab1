// AuditionGroup class creates an array of participants
import java.security.SecureRandom; 			// program uses class SecureRandom

public class AuditionGroup
{
	private Auditioner[] auditioners; 										// array of Auditioner objects
	private int currentAuditioner;											// participant index
	private static final SecureRandom randomIDs = new SecureRandom();		// random numbers
	private static final SecureRandom randomNumbers = new SecureRandom();	// random numbers
	private static final int uniqueIDBoundary = 9999;						// unique ID Limit
	
	public AuditionGroup(int performerCount, int dancerCount, int vocalistCount)
	{
		int groupSize = 0;
		
		// counts of each performer type are added to the group size only if they're non-negative greater than zero values
		if (performerCount > 0) groupSize += performerCount;					
		if (dancerCount > 0) groupSize += dancerCount;
		if (vocalistCount > 0) groupSize += vocalistCount;
		
		// only creates array of participants if group size is positive and less than the ID limits
		if (groupSize > 0 && groupSize <= uniqueIDBoundary)					
		{
			auditioners = new Auditioner[groupSize];
			currentAuditioner = 0;
			
			// adds performers to the array
			for (int count = 1; count <= performerCount; count++)
			{
				if (currentAuditioner < auditioners.length)
				{
					auditioners[currentAuditioner] = new Auditioner(randomIDs.nextInt(uniqueIDBoundary), "performer");
					currentAuditioner++;
				}
			}
			
			// adds dancers to the array
			for (int count = 1; count <= dancerCount; count++)
			{
				if (currentAuditioner < auditioners.length)
				{
					auditioners[currentAuditioner] = new Auditioner(randomIDs.nextInt(uniqueIDBoundary), "dancer");
					currentAuditioner++;
				}
			}
			
			// adds vocalists to the array
			for (int count = 1; count <= vocalistCount; count++)
			{
				if (currentAuditioner < auditioners.length)
				{	
					auditioners[currentAuditioner] = new Auditioner(randomIDs.nextInt(uniqueIDBoundary), "vocalist");
					currentAuditioner++;
				}
			}
			
		}
	}
	
	// method reorganizes group in a random order
	public void randomizeGroup()
	{
		currentAuditioner = 0;
		
		for (int first = 0; first < auditioners.length; first++)
		{
			// select a random number between 0 and one less than the number of participants
			int second = randomNumbers.nextInt(auditioners.length);
			
			// swap current participant with radomly selected one
			Auditioner temp = auditioners[first];
			auditioners[first] = auditioners[second];
			auditioners[second] = temp;
		}
	}
	
	// method to retrieve array of participants
	public Auditioner[] getAuditioners()
	{
		return this.auditioners;		// return value to caller
	}
	
	// method to retrieve individual participant
	public Auditioner getAuditioner(int index)
	{
		Auditioner tempAuditioner;
		
		if (index >= 0 && index < this.auditioners.length)
		{
			tempAuditioner = auditioners[index];
		}
		else
		{
			tempAuditioner = new Auditioner( -1, "unknown");
		}
		return tempAuditioner;
	}
	
	public int getLength()
	{
		return this.auditioners.length;
	}
	
} // end class AuditionGroup