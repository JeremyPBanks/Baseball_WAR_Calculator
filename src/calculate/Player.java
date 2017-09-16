package calculate;
import java.io.*;
import java.util.*;


/*
 * This is the class for the Player object that will contain all of the values in the text value (as well as what will possess the final WAR value). It has four overloaded constructors (a pitcher and a 
 * hitter for both 'Basic' and 'Advanced')
 */
public class Player 
{
	public String name;
	public String team;
	public int season,amount, PA, SB, CS, BB, oneB, HBP, IBB, HR, K, IFFB, gs, g;
	public float WinsAboveReplacement, wOBA, UBR, wGDP, fieldingRuns, battingRuns, baseRunningRuns, positionalAdjustment, leagueAdjustment, replacementRuns, runsPerWin, gmLI, IP, lgFIP, pFIP, leverageMultiplierRelievers, WARIP;
	Positions posList;
	boolean isHitter,isBasic;
	
	
	/*
	 * This is the class for the Positions attribute of the Player object that is a linked list containing each position title and their respective inning count at each.
	 */
	class Positions
	{
		String pos;
		float innings;
		Positions next;
		
		
		/*
		 * Constructor for the Positions
		 */
		public Positions(String pos, float innings, Positions next)
		{
			this.pos = pos;
			this.innings = innings;
			this.next = next;
		}
	}
	
	
	/*
	 * Overloaded constructor for 'Basic' hitters
	 */
	public Player(float WAR, String line, boolean isBasic, boolean isHitter) throws IOException 
	{
		this.isBasic = isBasic;
		this.isHitter = isHitter;
		WinsAboveReplacement = WAR;
		StringTokenizer tokenizer;
		try
		{
			tokenizer = new StringTokenizer(line);			
			name = tokenizer.nextToken();
			name = name.concat(" " + tokenizer.nextToken());			
			team = tokenizer.nextToken();
			team = teamNameTwoWords(team, tokenizer);
			season = Integer.parseInt(tokenizer.nextToken());
			battingRuns = Float.parseFloat(tokenizer.nextToken());
			baseRunningRuns = Float.parseFloat(tokenizer.nextToken());
			fieldingRuns = Float.parseFloat(tokenizer.nextToken());
			positionalAdjustment = Float.parseFloat(tokenizer.nextToken());
			leagueAdjustment = Float.parseFloat(tokenizer.nextToken());
			replacementRuns = Float.parseFloat(tokenizer.nextToken());
			runsPerWin = Float.parseFloat(tokenizer.nextToken());
		}
		catch (Exception e)
		{
			System.out.println("Wrong format, please recheck your file then reload the program. Error ~ " + e);
			System.exit(0);
		}
	}
	
	
	/*
	 * Overloaded constructor for 'Basic' pitchers
	 */
	public Player(float WAR, String line, int isBasic) throws IOException 
	{
		isBasic = 1;
		WinsAboveReplacement = WAR;
		StringTokenizer tokenizer;
		try
		{
			tokenizer = new StringTokenizer(line);
			name = tokenizer.nextToken();
			name = name.concat(" " + tokenizer.nextToken());
			team = tokenizer.nextToken();
			team = teamNameTwoWords(team, tokenizer);
			season = Integer.parseInt(tokenizer.nextToken());
			lgFIP = Float.parseFloat(tokenizer.nextToken());
			pFIP = Float.parseFloat(tokenizer.nextToken());
			g = Integer.parseInt(tokenizer.nextToken());
			gs = Integer.parseInt(tokenizer.nextToken());
			IP = Float.parseFloat(tokenizer.nextToken());
			leverageMultiplierRelievers = Float.parseFloat(tokenizer.nextToken());
			WARIP = Float.parseFloat(tokenizer.nextToken());
		}
		catch (Exception e)
		{
			System.out.println("Wrong format, please recheck your file then reload the program. Error ~ " + e);
			System.exit(0);
		}
	}	
	
	
	/*
	 * Overloaded constructor for 'Advanced' hitters
	 */
	public Player(float WAR, String line) throws IOException 
	{
		WinsAboveReplacement = WAR;
		StringTokenizer tokenizer;
		try
		{
			tokenizer = new StringTokenizer(line);
			name = tokenizer.nextToken();
			name = name.concat(" " + tokenizer.nextToken());						
			team = tokenizer.nextToken();
			team = teamNameTwoWords(team, tokenizer);	
			season = Integer.parseInt(tokenizer.nextToken());		
			posList = playerPositions(tokenizer.nextToken());
			amount = countingPositions(posList);
			playerInnings(tokenizer.nextToken(),posList);		
			wOBA = Float.parseFloat(tokenizer.nextToken());
			PA = Integer.parseInt(tokenizer.nextToken());		
			UBR = Float.parseFloat(tokenizer.nextToken());		
			wGDP = Float.parseFloat(tokenizer.nextToken());		
			SB = Integer.parseInt(tokenizer.nextToken());		
			CS = Integer.parseInt(tokenizer.nextToken());
			oneB = Integer.parseInt(tokenizer.nextToken());
			BB = Integer.parseInt(tokenizer.nextToken());
			HBP = Integer.parseInt(tokenizer.nextToken());
			IBB = Integer.parseInt(tokenizer.nextToken());
			fieldingRuns = Float.parseFloat(tokenizer.nextToken());
		}
		catch (Exception e)
		{
			System.out.println("Wrong format, please recheck your file then reload the program. Error ~ " + e);
			System.exit(0);
		}
	}
	
	
	/*
	 * Overloaded constructor for 'Advanced' pitchers
	 */
	public Player(float WAR, String line, boolean isHitter) throws IOException
	{
		WinsAboveReplacement = WAR;
		StringTokenizer tokenizer;
		try
		{
			tokenizer = new StringTokenizer(line);
			name = tokenizer.nextToken();;
			name = name.concat(" " + tokenizer.nextToken());				
			team = tokenizer.nextToken();
			team = teamNameTwoWords(team, tokenizer);	
			season = Integer.parseInt(tokenizer.nextToken());	
			posList = playerPositions(tokenizer.nextToken());
			HR = Integer.parseInt(tokenizer.nextToken());	
			BB = Integer.parseInt(tokenizer.nextToken());					
			HBP = Integer.parseInt(tokenizer.nextToken());					
			K = Integer.parseInt(tokenizer.nextToken());					
			IFFB = Integer.parseInt(tokenizer.nextToken());	
			playerInnings(tokenizer.nextToken(),posList);			
			gs = Integer.parseInt(tokenizer.nextToken());		
			g= Integer.parseInt(tokenizer.nextToken());				
			gmLI = Float.parseFloat(tokenizer.nextToken());
		}
		catch (Exception e)
		{
			System.out.println("Wrong format, please recheck your file then reload the program. Error ~ " + e);
			System.exit(0);
		}
	}
	
	
	/*
	 * Called in the 'Advanced' constructors, this method creates the link list of the positions for the Player object.
	 */
	private Positions playerPositions(String token)
	{
		Positions listHead = null;
		String pos = "";
		boolean thereWasASlash = false;
		
		for (int count = 0; count < token.length(); count++)
		{
			if (token.charAt(count) == '/')
			{
				Positions playerPos = new Positions(pos,0f,null);
				if (thereWasASlash == false)
				{
					listHead = playerPos;
					thereWasASlash = true;
				}
				else
				{
					travel(listHead).next = playerPos;
				}
				pos = "";	
			}
			else
			{
				pos = pos + token.charAt(count);
			}
		}
		Positions playerPos = new Positions(pos,0f,null);
		if (thereWasASlash == true)
		{
			travel(listHead).next = playerPos;
		}
		else
		{
			listHead = playerPos;
		}
		
		return listHead;
	}
	
	
	/*
	 * Called in the 'Advanced' constructors, this method assigns the respective innings to the positions in the link list
	 */
	public void playerInnings(String token, Positions list)
	{
		String num = "";
		for (int count = 0; count < token.length() && list != null; count++)
		{
			if (token.charAt(count) == '/')
			{
				list.innings = Float.parseFloat(num);
				list = list.next;
				num = "";
			}
			else
			{
				num = num + token.charAt(count);
			}
		}
		list.innings = Float.parseFloat(num);		
		return;
	}
	
	
	/*
	 * Travels to the end of a link list
	 */
	private Positions travel(Positions x)
	{
		while (x.next != null)
		{
			x = x.next;		
		}
		return x;
	}
	
	
	/*
	 * Simple recursive method that counts the total number of positions a player has played in a season.
	 */
	private int countingPositions(Positions x)
	{
		if (x == null)
		{
			return 0;
		}
		return 1 + countingPositions(x.next);
	}
	
	
	/*
	 * Checks beginning of team String to see if it refers to Blue Jays, Devil Rays, Red Sox, or White Sox
	 */
	private String teamInputErrorCheck(String team)
	{
		team = team.toLowerCase();
		
		if (team.startsWith("blue"))
		{
			return "Blue Jays";
		}
		else if (team.startsWith("devil"))
		{
			return "Devil Rays";
		}
		else if (team.startsWith("red"))
		{
			return "Red Sox";
		}
		else
		{
			return "White Sox";
		}
	}
	
	
	/*
	 * Checks beginning of team String to see if it refers to Blue Jays, Devil Rays, Red Sox, or White Sox and if not, it simply continues onward.
	 */
	private String teamNameTwoWords(String team, StringTokenizer tokenizer)
	{
		if (team.startsWith("Blue") || team.startsWith("Red") || team.startsWith("Devil") || team.startsWith("White"))
		{
			if (team.length() > 5)
			{
				return teamInputErrorCheck(team);
			}
			else
			{
				return team.concat(" " + tokenizer.nextToken());
			}
		}
		else
		{
			return team;
		}
	}
}
