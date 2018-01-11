// create array of participants in a random order, announces each participant, then judges their performances
import java.util.Scanner;					// program uses class Scanner
import java.security.SecureRandom; 			// program uses class SecureRandom

public class JudgeAuditions
{
	// execute application
	public static void main(String[] args) throws InterruptedException
	{
		int performerCount = 0;	//4
		int dancerCount = 0;	//2
		int vocalistCount = 8;	//1
		int randdomNumber;
		
		// creates an array of participants given the counts set above
		AuditionGroup participants = new AuditionGroup(performerCount, dancerCount, vocalistCount);
		
		Auditioner currentAuditioner;
		
		Scanner input = new Scanner(System.in);
		SecureRandom randomNumbers = new SecureRandom();
		
		if (participants.getLength() == 0)
		{
			System.out.println("Auditions were cancelled due to no participation.");	// will exit if participant count is somehow 0
		}
		else
		{
			participants.randomizeGroup();	// reorganizes participant array in a random order
			System.out.printf("%n%s%n", "Press enter after each prompt to continue.");	// prompt to user advising how to proceed
			input.nextLine();	// waits for user to press enter
			System.out.printf("There are %d%s%n", participants.getLength(), " participants.");	// announces the number of participants
			input.nextLine();
			
			for (int count = 0;  count < participants.getLength(); count++)		// cycles through participant array
			{

				String ordinalString = new CardinalIntegerToOrdinalString(count + 1).getCardinalValue();
				currentAuditioner = participants.getAuditioner(count);	
				System.out.printf( "The %s%s%s", ordinalString, " participant is a ", currentAuditioner.getPerformanceType() );	// announces participant type
				input.nextLine();
				
				if ( currentAuditioner.getPerformanceType().equals("vocalist") )	// vocalist condition allows user to adjust volume
				{
					System.out.print("Would you like to request they adjust their volume (y/n)? ");	// first asks if they wan't to adjust the volume
					String adjustVolume = input.nextLine();
					if ( adjustVolume.length() == 0)
					{
						System.out.print("No volume adjustments were requested.");
						input.nextLine();
					}
					else
					{
						if ( adjustVolume.substring(0, 1).equalsIgnoreCase("Y") )	// if first character the user types is a y will then prompt for volume input
						{
							System.out.print("Please enter a volume (whole number between 1 to 10): ");		// prompts for a volume value between 1 and 10
							String volumeValue = input.nextLine();
							if ( volumeIsValid(volumeValue) )	// if value is between 1 and 10 (inclusive) then will adjust the vocalist's volume
							{
								int i = (int) Math.round( Double.parseDouble(volumeValue) );
								currentAuditioner.setVolume(i);
								System.out.print("Participant was asked to adjust their volume to " + Integer.toString(i) );
								input.nextLine();
							}
							else	// any other value will result in no adjustment in volume
							{
								System.out.print("No volume adjustments were requested as input was invalid.");
								input.nextLine();
							}
						}
						else	// if first character the user types is anything other than y then no volume adjustments will be made
						{
							System.out.print("No volume adjustments were requested.");
							input.nextLine();
						}
					}
				}
				
				System.out.printf("The %s%s", ordinalString, " participant announces themself... ");	// prepares user for participant's announcement
				input.nextLine();
				System.out.print(toAnnounce(currentAuditioner));	// participant's announcement
				input.nextLine();
				
				// gameplay and judging to be updated below, currently adds a blank line between each participant for legibility
				// current judging is just for fun
				System.out.print("They perform");	// participant performs
				input.nextLine();
				randdomNumber = randomNumbers.nextInt(18);	// random number determines results of judging
				System.out.print("They have been judged.");	// announces judging is complete
				input.nextLine();
				if (randdomNumber % 3 == 0)			// 1 in 3 chance each participant may cry after being judged
				{
					System.out.print("Some crying may have been involved.");
					input.nextLine();
					if (randdomNumber % 2 == 0)		// 1 in 2 chance each of the crying participants may plead for a different judgment
					{
						System.out.printf("The %s%s", ordinalString, " participant pleads for mercy quoting some trivial life obstacle they've had to overcome which no one cares about.");
						input.nextLine();
						if (randdomNumber % 4 == 0) // only two chances this will work (when the randomNumber = 0 or 12), 1/3 chance for the criers, 1/9 chance overall
						{
							System.out.printf("The judges are moved by the %s%s", ordinalString," participant's pleads of mercy.");
							input.nextLine();
							System.out.println("They're going to Branson!!!  (the Hollywood of Missouri)");
							input.nextLine();
						}
						else
						{
							System.out.printf("The %s%s%n", ordinalString, " participant's shameless groveling is ineffective.");
							input.nextLine();
						}
					}
					else
					{
						System.out.println();
					}
				}
				else
				{
					System.out.println();
				}

				//System.out.printf("They have been judged%s%n", (randomNumbers.nextInt(3) % 3 == 0) ? " (some crying may have been involved)." : ".");	
			}
		}
		
	}
	
	// determines if user input for a vocalist's volume is valid
	private static boolean volumeIsValid(String value)
	{
		int i = 0;
		try	
		{
			i = (int) Math.round(Double.parseDouble(value));
		}
		
		catch(NumberFormatException e)	// catches errors due to non-integer values being entered
		{
			return false;
		}
		
		if ( i > 0 && i <= 10 )			// checks if numbered entered is between 1 and 10 (inclusive)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	// compiles string representing how the participant will announce themselves
	private static String toAnnounce(Auditioner currentAuditioner)
	{
		String uID = Integer.toString(currentAuditioner.getUnionID());
		
		switch (currentAuditioner.getPerformanceType())
		{
			case "performer":
				return "\"" + uID + " - performer\"";
				
			case "dancer":
				return "\"" + currentAuditioner.getDanceStyle() + " - " + uID + " - dancer\"";
				
			case "vocalist":
				return "\"" + "I sing in the key of - " + currentAuditioner.getMusicalKey() + 
					(currentAuditioner.getVolume() == 0 ? "" : " - at the volume " + Integer.toString( currentAuditioner.getVolume() )) + " - " + uID + "\"";
				
			default:
				return "\"I don't know what I'm doing here, but I can turn my eyelids inside out. See?\"";	// should not be able to reach the default option

		} // end switch
	}
	
} // end class JudgeAuditions