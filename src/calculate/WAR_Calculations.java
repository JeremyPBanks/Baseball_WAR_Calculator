package calculate;

import java.io.*;

import apps.*;
import calculate.Player.Positions;


/*
 * This is the class where all the calculations occur. The constructor is overloaded (essentially to accommodate 'Basic' and 'Advanced' calculations respectively), and 
 * each create an instance of a Player based off of if they are a pitcher or a hitter.
 * 
 */
public class WAR_Calculations extends WAR_Driver
{
	public static float runSB = 0.2f;
	boolean isHitter,isBasic;
	BufferedReader br;
	
	
	/*
	 * Custom and simple hash table to store players for faster user access to their WAR. It uses separate chaining, so each bucket is a linked list, which handles any collisions.
	 */
	public class hashTableElements extends WAR_Driver
	{
		public Player p;
		public hashTableElements next;
		
		public hashTableElements(Player p, hashTableElements next)
		{
			this.p = p;
			this.next = next;
		}
	}
	
	
	/*
	 * Overloaded constructor for the 'Basic' option
	 */
	public WAR_Calculations(boolean isHitter, BufferedReader br, boolean isBasic) throws IOException
	{
		this.isHitter = isHitter;
		this.br = br;
		this.isBasic = isBasic;
		
		if (isHitter == true)
		{
			for (String line = br.readLine(); line != null; line = br.readLine())
			{
				Player playBasic1 = new Player(0f,line,isBasic,isHitter);
				playBasic1.WinsAboveReplacement = basicWAREquation(playBasic1.battingRuns, playBasic1.baseRunningRuns, playBasic1.fieldingRuns, playBasic1.positionalAdjustment, playBasic1.leagueAdjustment, playBasic1.replacementRuns, playBasic1.runsPerWin);
				int key = playBasic1.name.toLowerCase().charAt(0);
				key -= 97;
				hashTableElements newEntry = new hashTableElements(playBasic1,null);
				updateHashTable(newEntry,key);
			}
		}
		else
		{
			for (String line = br.readLine(); line != null; line = br.readLine())
			{
				Player playBasic2 = new Player(0f,line,1);
				playBasic2.WinsAboveReplacement = basicWARPitchersEquation(playBasic2.lgFIP, playBasic2.pFIP, dynRunsPerWin(playBasic2.IP, playBasic2.g, playBasic2.lgFIP, playBasic2.pFIP), replacementLevelPitcher(playBasic2.gs, playBasic2.g), playBasic2.IP, playBasic2.leverageMultiplierRelievers, playBasic2.WARIP*playBasic2.IP);
				int key = playBasic2.name.toLowerCase().charAt(0);
				key -= 97;
				hashTableElements newEntry = new hashTableElements(playBasic2,null);
				updateHashTable(newEntry,key);
			}
		}
	}
	
	
	/*
	 * Overloaded constructor for the 'Advanced' option
	 */
	public WAR_Calculations(boolean isHitter, BufferedReader br) throws IOException
	{
		this.isHitter = isHitter;
		this.br = br;
		
		if (isHitter == true)
		{
			for (String line = br.readLine(); line != null; line = br.readLine())
			{
				Player playAdvanced1 = new Player(0f,line);
				playAdvanced1.WinsAboveReplacement = warEquation(playAdvanced1,isHitter);
				int key = playAdvanced1.name.toLowerCase().charAt(0);
				key -= 97;
				hashTableElements newEntry = new hashTableElements(playAdvanced1,null);
				updateHashTable(newEntry,key);
			}
		}
		else
		{
			for (String line = br.readLine(); line != null; line = br.readLine())
			{
				Player playAdvanced2 = new Player(0f,line,isHitter);
				playAdvanced2.WinsAboveReplacement = warEquation(playAdvanced2,isHitter);
				int key = playAdvanced2.name.toLowerCase().charAt(0);
				key -= 97;
				hashTableElements newEntry = new hashTableElements(playAdvanced2,null);
				updateHashTable(newEntry,key);
			}
		}
	}
	
	
	/*
	 * This method acts as a sort of hub for the advanced calculations. The most significant variables used to finalize the WAR stat are returned from 
	 * other branching methods, using references to the database or the current Player object as necessary.
	 */
	public float warEquation(Player p, boolean isHitter)
	{
		DataConnector data = new DataConnector();
		
		if (isHitter == true)
		{
			float battingRuns = battingRuns(p.wOBA,data.getMLBFloats(p.season,"wOBA"),data.getMLBFloats(p.season,"wOBAscale"),p.PA,data.getMLBFloats(p.season,"rPerPA"),data.getParkFactor(p.season, p.team, isHitter),data.getLgInts(p.season,"PA",p.team),data.getLgFloats(p.season,"wRC",p.team));
			float baseRunningRuns = baseRunningRuns(p.UBR,p.wGDP,data.getMLBFloats(p.season, "runCS"),data.getMLBInts(p.season,"SB"),runSB,data.getMLBInts(p.season,"CS"),data.getMLBInts(p.season,"oneB"),data.getMLBInts(p.season,"BB"),data.getMLBInts(p.season,"HBP"),data.getMLBInts(p.season,"IBB"),p.SB,p.CS,p.oneB,p.BB,p.HBP,p.IBB);
			float fieldingRuns = p.fieldingRuns;
			float positionalAdjustment = posAdj(p.amount, p, p.posList);
			float leagueAdjustment = leagueAdj(data.getLgFloats(p.season,"battingRuns",p.team),data.getLgFloats(p.season,"baseRunningRuns",p.team),data.getLgFloats(p.season,"fieldingRuns",p.team),data.getLgFloats(p.season,"positionalAdjustment",p.team),data.getLgInts(p.season,"PA",p.team),p.PA);
			float replacementLevelRuns = replacementLevelRuns(data.getMLBInts(p.season,"games"),data.getMLBFloats(p.season,"runsPerWin"),data.getMLBInts(p.season,"PA"),p.PA);
			float runsPerWin = data.getMLBFloats(p.season,"runsPerWin");
			
			return basicWAREquation(battingRuns, baseRunningRuns, fieldingRuns, positionalAdjustment, leagueAdjustment, replacementLevelRuns, runsPerWin);
		}
		else
		{
			float pFIPR9 = fieldingIndependentPitchingPlayer(data.getMLBFloats(p.season,"ERA"),data.getMLBInts(p.season,"HR"),data.getMLBInts(p.season,"BB"),data.getMLBInts(p.season,"HBP"),data.getMLBInts(p.season,"K"),data.getMLBInts(p.season,"IFFB"),data.getMLBFloats(p.season,"IP"),p.HR,p.BB,p.HBP,p.K,p.IFFB,p.posList.innings,data.getMLBFloats(p.season,"RA9"),data.getParkFactor(p.season,p.team,isHitter));
			float lgFIPR9 = fieldingIndependentPitchingLg(data.getLgInts(p.season,"HR",p.team),data.getLgInts(p.season,"BB",p.team),data.getLgInts(p.season,"HBP",p.team),data.getLgInts(p.season,"K",p.team),data.getLgInts(p.season,"IFFB",p.team),data.getLgFloats(p.season,"IP",p.team),data.getMLBFloats(p.season,"ERA"),data.getMLBInts(p.season,"HR"),data.getMLBInts(p.season,"BB"),data.getMLBInts(p.season,"HBP"),data.getMLBInts(p.season,"K"),data.getMLBInts(p.season,"IFFB"),data.getMLBFloats(p.season,"IP"),data.getMLBFloats(p.season,"RA9"));
			float RAAP9 = lgFIPR9 - pFIPR9;
			float wPGAA = winsPerGameAboveAverage(p.posList.innings,p.g,lgFIPR9,pFIPR9,RAAP9);
			float wPGAR = winsPerGameAboveReplacement(p.gs,p.g,wPGAA);
			float tempWAR = tempWAR(wPGAR,p.posList.innings);
			float leverageMultiplierForRP = 1f;			
			if (p.posList.pos.equals("RP"))
			{
				leverageMultiplierForRP = leverageMultiplierForRP(p.gmLI);
			}
			
			return (tempWAR*leverageMultiplierForRP) + (-0.000682902f * p.posList.innings);
		}
	}
	
	
	/*
	 * The WAR equation for hitters in its most basic form. The 'Basic' option directly goes here, while the 'Advanced' option also calls this method
	 * in the end after all the needed variables are calculated individually.
	 */
	public float basicWAREquation(float battingRuns, float baseRunningRuns, float fieldingRuns, float positionalAdjustment, float leagueAdjustment, float replacementLevelRuns, float runsPerWin)
	{
		return ((battingRuns + baseRunningRuns + fieldingRuns + positionalAdjustment + leagueAdjustment + replacementLevelRuns) / (runsPerWin));
	}
	
	
	/*
	 * The Batting Runs stat is calculated here. Full name of variables used: Weighted Runs Above Average (wOBA), Weighted On-Base Percentage (wOBA), 
	 * Weighted Runs Above Average scale constant (wOBAscale), Plate Appearances (PA), Runs Per Plate Appearances (RperPA), Weighted Runs Created (wRC), Weighted Runs Above Average (wRAA)
	 */
	private float battingRuns(float wOBA, float mlbwOBA, float wOBAscale, int PA, float mlbRperPA, float PF, int lgPA, float wRClgNLorAL)
	{
		float wRAA = (((wOBA - mlbwOBA)/wOBAscale) * PA); //
		float bR = wRAA + ((mlbRperPA - (PF * mlbRperPA)) * PA) + ((mlbRperPA - (wRClgNLorAL/lgPA)) * PA);
		
		return bR;
	}
	
	
	/*
	 * The Base Running Runs stat is calculated here. Full name of variables used:  Ultimate Base Running (UBR), Weighted Stolen Base Runs (wSB), weighted Grounded into Double Play Runs (wGDP),
	 * Stolen Bases (SB), run value of a caught stealing (runCS), Caught Stealing (CS), Singles (1B/oneB), Base on Balls [aka Walks] (BB), Hit By Pitches (HBP),
	 * Intentional Base on Balls (IBB).
	 */
	private float baseRunningRuns(float UBR, float wGDP, float runCS, int mlbSB, float runSB, int mlbCS, int mlb1B, int mlbBB, int mlbHBP, int mlbIBB, int SB, int CS, int oneB, int BB, int HBP, int IBB)
	{
		float lgwSB = (mlbSB * runSB + mlbCS * runCS)/(mlb1B + mlbBB + mlbHBP - mlbIBB);
		float wSB = SB * runSB + CS * runCS - lgwSB * (oneB + BB + HBP - IBB);
		
		return UBR + wGDP + wSB;
	}
	
	
	/*
	 * The Positional Adjustment stat is calculated here. It is a recursive method that uses the player's link list of positions to add up each iteration of the equation.
	 */
	private float posAdj(int numOfPositions, Player p, Positions ptr)
	{
		if (numOfPositions == 0 || ptr == null)
		{
			return 0f;
		}

		return ((((p.posList.innings/9)/162) * posAdjConstants(p.posList.pos)) + posAdj(numOfPositions--,p,ptr.next));
	}
	
	
	/*
	 * The League Adjustment stat is calculated here. This will add between 0 and 5 runs to a players value per season based on their league and plate appearances.
	 */
	private float leagueAdj(float lgBattingRuns, float lgBaseRunningRuns, float lgFieldingRuns, float lgPositionalAdjustment, int lgPA, int PA) 
	{
		return ((-1)*(lgBattingRuns + lgBaseRunningRuns + lgFieldingRuns + lgPositionalAdjustment) / lgPA)*PA;
	}
	
	
	/*
	 * The Replacement Level Runs stat is calculated here. "mlbGames" refers to the total number of baseball games played in a year across both leagues.
	 */
	private float replacementLevelRuns(int mlbGames, float runsPerWin, int mlbPA, int PA)
	{
		return (float)((570 * (mlbGames/2430)) * (runsPerWin/mlbPA) * PA);
	}
	
	
	/*
	 * A switch statement that returns the constants relative to the respective positionsl, called in the method for Positional Adjustment.
	 */
	private float posAdjConstants(String pos)
	{
		switch (pos)
		{
			case "C":
				return 12.5f;
			case "1B":
				return -12.5f;
			case "2B":
				return 2.5f;
			case "3B":
				return 2.5f;
			case "SS":
				return 7.5f;
			case "LF":
				return -7.5f;
			case "CF":
				return 2.5f;
			case "RF":
				return -7.5f;
			case "DH":
				return -17.5f;
			default:
				return 1f;
		}
	}
	
	
	/*
	 *  The most basic WAR equation for pitchers in its most basic form. The 'Basic' option directly goes here.
	 */
	public float basicWARPitchersEquation(float lgFIP, float pFIP, float playerRunsPerWin, float replacementLevel, float IP, float leverageMultiplierRelievers, float lgCorrection)
	{
		return (((((lgFIP - pFIP) / playerRunsPerWin) + replacementLevel) * (IP/9)) * leverageMultiplierRelievers) + lgCorrection;
	}
	
	
	/*
	 * The Fielding Independent Pitching (FIP) stat is calculated here. For the purposes of the accuracy of WAR, pFIP is then put on the same scale as Runs Allowed Per 9 (RA9).
	 * The ending result is pFIP, with infield flies (which is on an RA9 scale), or simply FIPR9. It returns the pFIPR9 stat, which is FIPR9 adjusted for the pitcher's
	 * home park. Full name of variables used: Earned Run Average (ERA), Home Runs (HR), Base on Balls (BB), Hit By Pitches (HBP), Strikeouts (K), Infield Fly Balls (IFFB),
	 * Innings Pitched (IP).
	 */
	private float fieldingIndependentPitchingPlayer(float mlbERA, int mlbHR, int mlbBB, int mlbHBP, int mlbK, int mlbIFFB, float mlbIP, int HR, int BB, int HBP, int K, int IFFB, float IP, float mlbRA9,float PF)
	{
		float ifFIPConstant = ifFIPConstant(mlbERA, mlbHR, mlbBB, mlbHBP, mlbK, mlbIFFB, mlbIP);
		float ifFIP =  (float)(((13 * HR) + (3 * (BB + HBP)) - (2*(K + IFFB))) / IP + ifFIPConstant);
		
		return (ifFIP + adjustmentForPitchers(mlbRA9,mlbERA))/(PF/100);
	}
	
	
	/*
	 * The Fielding Independent Pitching League (FIP) stat is calculated here. It is a similar process, but based upon the player's specific league (as in, AL or NL).
	 */
	private float fieldingIndependentPitchingLg(int lgHR, int lgBB, int lgHBP, int lgK, int lgIFFB, float lgIP,float mlbERA, int mlbHR, int mlbBB, int mlbHBP, int mlbK, int mlbIFFB, float mlbIP, float mlbRA9)
	{
		return (((13 * lgHR) + (3 * (lgBB + lgHBP)) - (2 * (lgK + lgIFFB))) / lgIP + ifFIPConstant(mlbERA, mlbHR, mlbBB, mlbHBP, mlbK, mlbIFFB, mlbIP)) + adjustmentForPitchers(mlbRA9,mlbERA);
	}
	
	
	/*
	 * A constant required for FIP calculations based upon MLB stats for a given year. It notably treats infield fly balls (IFFB) as strikeouts to further the accuracy of WAR
	 */
	private float ifFIPConstant(float mlbERA, int mlbHR, int mlbBB, int mlbHBP, int mlbK, int mlbIFFB, float mlbIP)
	{
		return mlbERA - (((13 * mlbHR) + (3 * (mlbBB + mlbHBP)) - (2 * (mlbK + mlbIFFB))) / mlbIP);
	}
	
	
	/*
	 * The simple formula that puts FIP on the same scale as RA9.
	 */
	private float adjustmentForPitchers(float mlbRA9, float mlbERA)
	{
		return mlbRA9 - mlbERA;
	}
	
	
	/*
	 * The Wins Per Game Above Average (wPGAA) stat is calculated here. It is simply Runs Above Average Per 9 (RAAP9) divided by Dynamic Runs Per Win (dRPW).
	 */
	private float winsPerGameAboveAverage(float IP, int g, float lgFIPR9, float pFIPR9, float RAAP9)
	{
		float dRPW = dynRunsPerWin(IP, g, lgFIPR9, pFIPR9);
		return RAAP9/dRPW;
	}
	
	
	/*
	 * Creates a dynamic Runs Per Win (RPW) value based on the pitchers innings per game and pFIPR9 to include how pitchers directly influence their run environment based on how well they pitch. 
	 */
	private float dynRunsPerWin(float IP, int g, float lgFIPR9, float pFIPR9)
	{
		return (float) ((((((18 - IP/g)*(lgFIPR9)) + ((IP/g)*pFIPR9)) / 18) + 2) * 1.5);
	}
	
	
	/*
	 * Calculates the Wins Per Game Above Replacement stat to scale wPGAA to innings pitched. 
	 */
	private float winsPerGameAboveReplacement(int gs, int g, float wPGAA)
	{
		float replacementLevelPitcher = replacementLevelPitcher(gs, g);
		
		return replacementLevelPitcher + wPGAA;
	}
	
	
	/*
	 * Called in the "winsPerGameAboveReplacement" method, it determines the difference between an average pitcher and a replacement level pitcher (with respect to both SP and RP).
	 */
	private float replacementLevelPitcher(int gs, int g)
	{
		return (float) ((float) 0.03*(1 - gs/g) + 0.12*(gs/g));
	}
	
	
	/*
	 * The tentative pitcher WAR value before some adjustments are accounted for
	 */
	private float tempWAR(float wPGAR, float IP)
	{
		return wPGAR * (IP/9);
	}
	
	
	/*
	 * An adjustment made for Relief Pitchers (RP). This will always be added, but it will negligible (since it will always equal 1) for Starting Pitchers (SP).
	 */
	private float leverageMultiplierForRP(float gmLI)
	{
		return (1 + gmLI) / 2;
	}
	
	
	/*
	 * The insertion of the Player object into the hash table. It first checks to see if the Player at the specified year has already been inserted. If not, it is placed at the end of the
	 * bucket's linked list.
	 */
	public void updateHashTable(hashTableElements newEntry, int key)
	{
		if (hashTable[key] == null)
		{
			hashTable[key] = newEntry;
			System.out.println(newEntry.p.name + " " + newEntry.p.season + " Stored");
		}
		else
		{
			hashTableElements ptr = hashTable[key];
			while(ptr != null)
			{
				if (ptr.p.name.equals(newEntry.p.name) && ptr.p.season == newEntry.p.season)
				{
					//Duplicate, so we skip over it
					break;
				}

				if (ptr.next != null)
				{
					ptr = ptr.next;
				}
				else
				{
					ptr.next = newEntry;
					System.out.println(newEntry.p.name + " " + newEntry.p.season + " Stored");
					break;
				}
			}
		}
	}
}
