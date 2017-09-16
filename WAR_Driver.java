package apps;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

import calculate.*;
import calculate.WAR_Calculations.hashTableElements;


/*
 * Driver for the program, which handles all user inputs as well as grabs the txt files requested by the user
 * 
 */
public class WAR_Driver
{
	public static hashTableElements hashTable[] = new hashTableElements[26];
	static BufferedReader br,br2;	
	public static String userInput() 
	throws IOException
	{
		return br.readLine();
	}
	
	
	/*
	 * Input loop for when the user is in the "basic or advanced" section of the menu. This is also where the program creates new instances of calculations,
	 * leading to the rest of the code.
	 * 
	 */
	public static void basicOrAdvanced(boolean isHitter) throws IOException
	{
		String answer = "";
		boolean isBasic = true;
		
		do
		{
			answer = userInput();
			answer = answer.toLowerCase();
			
			if(answer.equals("advanced") || answer.equals("basic"))
			{
				System.out.println("Please enter the file name [txt]");
				boolean valid = false;
				
				do
				{
					String file = userInput();
					try
					{
						br2 = new BufferedReader(new FileReader(file));
					}
					catch (java.io.FileNotFoundException e)
					{
						System.out.println("Error: " + e.getMessage() + "\n Please enter a valid file name [txt]");
						continue;
					}
					
					valid = true;
				}while(valid == false);
				
				if (answer.equals("advanced"))
				{
					WAR_Calculations x = new WAR_Calculations(isHitter,br2);
				}
				else
				{
					WAR_Calculations x = new WAR_Calculations(isHitter,br2,isBasic);
				}
				
				break;
			}
			else
			{
				System.out.println("Incorrect Input; try again [Just type \"Basic\" or \"Advanced\"]");
			}
		}while(!(answer.equals("basic")) || !(answer.equals("advanced")));
	}
	
	
	/*
	 * Part of the 'Display' section of the menu, which essentially displays the requested player's WAR from a specific year. This is done by
	 * using the first character in the player's name as a key (which is also how it was stored) to search the hash table.
	 */
	public static void getWAR() throws IOException
	{
		System.out.println("Which player would you like to see the WAR of (or would you like to return)? [name year/Return]");
		
		String type = "";
		String name = "";
		int year = 0;
		type = userInput();
		while(!type.equals("return"))
		{
			int key = type.charAt(0);
			key -= 65;
			System.out.println("key: " + key);
			try
			{
				StringTokenizer st = new StringTokenizer(type);
				name = st.nextToken();
				name = name + " " + st.nextToken();
				year = Integer.parseInt(st.nextToken());
				System.out.println("year: " + year);
				Player isPresent = searchHashTable(key,name,year);
				System.out.println(isPresent.name + "\'s WAR in " + isPresent.season + ": " + isPresent.WinsAboveReplacement);
				
				System.out.println("Which player would you like to see the WAR of (or would you like to return)? [name year/Return]");
			}
			catch (Exception e)
			{
				System.out.println("The player you entered has not been entered. Check spelling/year and try again, or type \"Return\" to go back");
			}
			type = userInput();
		}
	}
	
	
	/*
	 * The actual search through the hash table. Since we have the key going into the method, we can access the bucket in O(1). However, since our hash table has separate chaining, searching
	 * through the bucket could be O(n) at worst case.
	 * 
	 * The Player object is returned if it is found, but otherwise null is returned.
	 */
	public static Player searchHashTable(int key, String typedName, int year)
	{
		if (hashTable[key] == null)
		{
			return null;
		}
		else
		{
			hashTableElements ptr = hashTable[key];

			while(ptr != null)
			{
				if (ptr.p.name.equals(typedName) && year == ptr.p.season)
				{
					return ptr.p;
				}
				else
				{
					ptr = ptr.next;
				}
			}		
			return null;
		}
	}
	
	
	/*
	 * Initializes all buckets to null at the start.
	 */
	public static void setTable()
	{
		for (int i = 0; i < 26; i++)
		{
			hashTable[i] = null;
		}
	}

	/*
	 * Main method, housing the beginning input loops
	 */
	public static void main(String[] args) throws IOException
	{
		br = new BufferedReader(new InputStreamReader(System.in));
		String type = "";
		setTable();
		boolean isHitter = false;
		
		System.out.println("Welcome to WAR! Pitcher or Hitter?");
		
		do
		{
			type = userInput();
			type = type.toLowerCase();
			
			if (type.equals("hitter"))
			{
				System.out.println("Would you like to do a basic or advanced calculation?");
				isHitter = true;
				basicOrAdvanced(isHitter);
				
				System.out.println("Would you like to input another calculation file, display a player's WAR, or exit the program? [Input/Display/Exit]");
				do
				{
					type = userInput();
					type = type.toLowerCase();
					
					if (type.equals("input"))
					{
						System.out.println("Welcome back to WAR! Pitcher or Hitter?");
						break;
					}
					else if (type.equals("exit"))
					{
						System.exit(0);
					}
					else if (type.equals("display"))
					{
						getWAR();
						System.out.println("Would you like to input another calculation file, display a player's WAR, or exit the program? [Input/Display/Exit]");
					}
					else
					{
						System.out.println("Incorrect Input; try again [Just type \"Input\", \"Display\", or \"Exit\"]");
						continue;
					}
				}while(!(type.equals("input")|| type.equals("display")) || !(type.equals("exit")));
			}
			else if (type.equals("pitcher"))
			{
				System.out.println("Would you like to do a basic or advanced calculation?");
				isHitter = false;
				basicOrAdvanced(isHitter);
				
				System.out.println("Would you like to input another calculation file, display a player's WAR, or exit the program? [Input/Display/Exit]");
				do
				{
					type = userInput();
					type = type.toLowerCase();
					
					if (type.equals("input"))
					{
						System.out.println("Welcome back to WAR! Pitcher or Hitter?");
						break;
					}
					else if (type.equals("exit"))
					{
						System.exit(0);
					}
					else if (type.equals("display"))
					{
						getWAR();
						System.out.println("Would you like to input another calculation file, display a player's WAR, or exit the program? [Input/Display/Exit]");
					}
					else
					{
						System.out.println("Incorrect Input; try again [Just type \"Input\", \"Display\", or \"Exit\"");
						continue;
					}
				}while(!(type.equals("input")|| type.equals("display")) || !(type.equals("exit")));
			}
			else
			{
				System.out.println("Incorrect Input; try again [Just type \"Hitter\" or \"Pitcher\"]");
			}
		}while(!(type.equals("hitter")) || !(type.equals("pitcher")));	
	}
}
