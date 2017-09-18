# Baseball War Calculator

Welcome to WAR! This is a program I developed in my free time that takes in a multitude of MLB statistics and calculates a player's WAR (Wins Above Replacement) for a specific year. In short, the program reads a player and a handful of his stats from a txt file, then while utilizing elements of SQL (for which I taught myself for this project), it uses those stats in tandem with MLB constants grabbed from a database to help produce the final output. The player objects are stored in a simple hash table (using separate chaining), and can be easily accessed again at the user's demand.

## What is WAR?
From FanGraphs.com:
```
Wins Above Replacement (WAR) is an attempt by the sabermetric baseball community to summarize a player’s total contributions to their team in one statistic. You should always use more than one metric at a time when evaluating players, but WAR is all-inclusive and provides a useful reference point for comparing players. WAR offers an estimate to answer the question, “If this player got injured and their team had to replace them with a freely available minor leaguer or a AAA player from their bench, how much value would the team be losing?” This value is expressed in a wins format, so we could say that Player X is worth +6.3 wins to their team while Player Y is only worth +3.5 wins, which means it is highly likely that Player X has been more valuable than Player Y.
```
In other words, WAR is a measurement of a player's skill with respect to rest of the league.
