package apps;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;


/*
 * 
 * This class incorporates SQL for database retrieval of league and MLB constants. It simply grabs the stats from the database
 * and returns the specific values needed for calculation.
 * 
 */
public class DataConnector 
{
	private Connection c;
	private Statement s;
	private ResultSet rs;
	private static final String[] arrAL = {"Orioles","Red Sox","White Sox","Indians","Tigers","Astros","Royals","Angels","Twins","Yankees","Athletics","Mariners","Rays","Devil Rays","Rangers","Blue Jays"};
	
	
	/*
	 * Constructor, which includes establishing the actual connection to the database
	 */
	public DataConnector()
	{
		try
		{
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/mlb_war?autoReconnect=true&useSSL=false","root","");
			s = c.createStatement();
		}
		catch(Exception e)
		{
			System.out.println("Database Error: " + e);
		}
	}
	
	
	/*
	 * Grabs the Park Factor from the park_factors table, based off of the inputed player's team and season (year)
	 */
	public float getParkFactor(int season, String team, boolean isHitter)
	{
		float PF = 0;
		
		if(season > 2015)
		{
			season = 2015;
		}
		
		String query = "SELECT factor FROM park_factors WHERE team=\"" + team + "\" AND season=" + season;
		
		try 
		{
			rs = s.executeQuery(query);
			
			if (rs.next())
			{
				PF = rs.getFloat("factor");
			}
		} 
		catch (SQLException e) 
		{
			System.out.println("Error: " + e);
		}
		
		if (isHitter == true)
		{
			return PF;
		}
		else
		{
			PF *= 100;
			return PF;
		}
	}
	
	
	/*
	 * Grabs MLB-wide constants (floats) from the mlb_totals table, based off of the inputed player's season (year)
	 */
	public float getMLBFloats(int season, String type)
	{
		String query = "SELECT " + type + " FROM mlb_totals WHERE season=" + season;
		try
		{
			rs = s.executeQuery(query);
			
			if (rs.next())
			{
				return rs.getFloat(type);
			}
		}
		catch (SQLException e)
		{
			System.out.println("Error: " + e);
		}
		
		return 0f;
	}
	
	
	/*
	 * Grabs MLB-wide constants (integers) from the mlb_totals table, based off of the inputed player's season (year)
	 */
	public int getMLBInts(int season, String type)
	{

		String query = "SELECT " + type + " FROM mlb_totals WHERE season=" + season;
		try
		{
			rs = s.executeQuery(query);
			
			if (rs.next())
			{
				return rs.getInt(type);
			}
		}
		catch (SQLException e)
		{
			System.out.println("Error: " + e);
		}
		
		return 0;
	}
	
	
	/*
	 * Grabs League-wide constants (floats) from the al_totals or nl_totals tables, based off of the inputed player's season (year) and team (which determines the league)
	 */
	public float getLgFloats(int season,String type,String team)
	{
		String lg = alOrNL(team, season);
		String query = "SELECT " + type + " FROM " + lg+ "_totals WHERE season=" + season;
		try
		{
			rs = s.executeQuery(query);
			
			if (rs.next())
			{
				return rs.getFloat(type);
			}
		}
		catch (SQLException e)
		{
			System.out.println("Error: " + e);
		}
		
		return 0f;
	}
	
	
	/*
	 * Grabs League-wide constants (integers) from the al_totals or nl_totals tables, based off of the inputed player's season (year) and team (which determines the league)
	 */
	public int getLgInts(int season,String type,String team)
	{
		String lg = alOrNL(team, season);
		String query = "SELECT " + type + " FROM " + lg+ "_totals WHERE season=" + season;
		try
		{
			rs = s.executeQuery(query);
			
			if (rs.next())
			{
				return rs.getInt(type);
			}
		}
		catch (SQLException e)
		{
			System.out.println("Error: " + e);
		}
		
		return 0;
	}
	
	
	/*
	 * Returns the league associated with the player's team. The Astros moved from the National League (NL) to the American League (AL) in 2013, so that case in particular is handled
	 */
	private String alOrNL(String team, int season)
	{
		for (String t : arrAL)
		{
			if (t.equals(team) && !t.equals("Astros"))
			{
				return "al";
			}
			else if(season >= 2013 && t.equals("Astros"))
			{
				return "al";
			}
		}
		
		return "nl";
	}
}
