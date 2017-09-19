# Baseball War Calculator

Welcome to WAR! This is a program I developed in my free time that takes in a multitude of MLB statistics and calculates a player's WAR (Wins Above Replacement) for a specific year. In short, the program reads a player and a handful of his stats from a txt file, then while utilizing elements of SQL (for which I taught myself for this project), it uses those stats in tandem with MLB constants grabbed from a database to help produce the final output. The player objects are stored in a simple hash table (using separate chaining), and can be easily accessed again at the user's demand.

## What is WAR?
From FanGraphs.com:

"Wins Above Replacement (WAR) is an attempt by the sabermetric baseball community to summarize a player’s total contributions to their team in one statistic. You should always use more than one metric at a time when evaluating players, but WAR is all-inclusive and provides a useful reference point for comparing players. WAR offers an estimate to answer the question, “If this player got injured and their team had to replace them with a freely available minor leaguer or a AAA player from their bench, how much value would the team be losing?” This value is expressed in a wins format, so we could say that Player X is worth +6.3 wins to their team while Player Y is only worth +3.5 wins, which means it is highly likely that Player X has been more valuable than Player Y."

In other words, WAR is a measurement of a player's skill with respect to rest of the league. The higher the WAR, the better the player was that year. A WAR is determined based upon the performance of the rest of the league, so it would be more difficult to attain a higher WAR in the early 2000s as a power hitter rather than the 1920s. Since statistics like home runs were siginficantly up in those years, a player hitting 40 homeruns in that era meant way less than when Babe Ruth did it.

## Getting Started

All of the necessary code and revolving files are included in the repository. This project is primarily made with Java with some SQL used for databases, so one important feature to have is the MySQL JAR file which allows this project to connect to the database. Without it, the project will not run. For ease of access, I have included the JAR file in the "lib" folder in the repository, but if you would like to download it from the source, proceed here: https://dev.mysql.com/downloads/connector/j/5.1.html

## Input

This program reads its inputs from a txt file. The txt files require all of the statistics to be in a specific order, so I have included templates and some examples. It is important to note that there are two different styles of calculations ("Basic" and "Advanced"), and each of those have two different kinds of WAR to calculate ("Hitter" or "Pitcher"), for a total of four different txt file alterations. As I said before, I have already provided a template for all four of these in the repository, but I'll include the general outline for all of them here as well.

~ Basic Hitter:
```
FirstName LastName team season battingRuns baseRunningRuns fieldingRuns positionalAdjustment leagueAdjustment replacementRuns runsPerWin
```
~ Basic Pitcher:
```
FirstName LastName team season leagueFIP FIP g gs IP leverageMultiplierForRP WARIP
```
~ Advanced Hitter:
```
FirstName LastName TeamName(no city) year Pos#1/Pos#2 Innings1/Innings2 wOBA PA UBR wGDP SB CS 1B BB HBP IBB FieldingRuns
```
~ Advanced Pitcher
```
FirstName LastName TeamName(no city) Year SPorRP HR BB HBP K IFFB IP gs g gmLI(1 for SP)
```
