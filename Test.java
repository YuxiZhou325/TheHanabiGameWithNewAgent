package com.fossgalaxy.games.fireworks.ai.username;

import com.fossgalaxy.games.fireworks.GameRunner;
import com.fossgalaxy.games.fireworks.GameStats;
import com.fossgalaxy.games.fireworks.ai.AgentPlayer;
import com.fossgalaxy.games.fireworks.ai.ga.Individual;
import com.fossgalaxy.games.fireworks.players.Player;
import com.fossgalaxy.stats.BasicStats;
import com.fossgalaxy.stats.StatsSummary;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Test {



    public static void main( String[] args )
    {
        int numPlayers = 4;
        int numGames = 10;
        // int[] chromosome = new int[] {26,12,3};// This is a list of rules to be used, in this order.

        Random random = new Random();
        int ruleIndex = random.nextInt();

        Map<Integer, int[]> map = new Hashtable<>();
        map.put(0, new int[]{26,12,3});
        // map.put(2, new int[]{26, 3});
        map.put(1, new int[]{26,20,12,3});
        map.put(2, new int[]{25,12,3,});
        map.put(3, new int[]{26,13,3});
        map.put(4, new int[]{26,12,4});
        map.put(5, new int[]{26,12,5});
        map.put(6, new int[]{26,12,6});
        map.put(7, new int[]{26,12,7});
        map.put(8, new int[]{26,14,3});
        map.put(9, new int[]{26,15,3});
        map.put(10, new int[]{26,16,3});
        map.put(11, new int[]{17,12,3});
        map.put(12, new int[]{18,12,3});
        map.put(13, new int[]{19,12,3});
        map.put(14, new int[]{20,12,3});
        map.put(15, new int[]{21,12,3});
        map.put(16, new int[]{22,12,3});
        map.put(17, new int[]{23,12,3});
        map.put(18, new int[]{24,12,3});
        map.put(19, new int[]{25,12,3});
        map.put(20, new int[]{27,12,3});


        int evals = 0;


        StatsSummary statsSummary;
        try {
            /*statsSummary = evaluateChromosome(map.get("Chromosome 1"), numPlayers,  numGames);
            System.out.println(String.format("Our agent 1: Avg: %f, min: %f, max: %f",
                    statsSummary.getMean(),
                    statsSummary.getMin(),
                    statsSummary.getMax()));

            statsSummary = evaluateChromosome(map.get("Chromosome 2"), numPlayers,  numGames);
            System.out.println(String.format("Our agent 2: Avg: %f, min: %f, max: %f",
                    statsSummary.getMean(),
                    statsSummary.getMin(),
                    statsSummary.getMax()));

            statsSummary = evaluateChromosome(map.get("Chromosome 3"), numPlayers,  numGames);
            System.out.println(String.format("Our agent 3: Avg: %f, min: %f, max: %f",
                    statsSummary.getMean(),
                    statsSummary.getMin(),
                    statsSummary.getMax()));*/

            System.out.printf("test");

            statsSummary = evaluateChromosome(map.get(random.nextInt(map.size())), numPlayers, numGames);
            System.out.println(String.format("This is a random choice and we got Score: %f", statsSummary.getMean()));
            double bestScore = statsSummary.getMean();

            for (int i = 0; i < map.size(); i++) {
                statsSummary = evaluateChromosome(map.get(i), numPlayers, numGames);
                double newScore = statsSummary.getMean();

                if (newScore > bestScore) {
                    // System.out.println(i);
                    bestScore = newScore;
                    System.out.println("The Best Performance Chromosome is: ");
                    System.out.println(Arrays.toString(map.get(i)));
                    System.out.println(String.format("Avg: %f, min: %f, max: %f",
                            statsSummary.getMean(),
                            statsSummary.getMin(),
                            statsSummary.getMax()));
                    System.out.println("\n");
                }

            }
            System.out.println(bestScore);

        } catch (RuleBasedSystemChromosomeEvaluator.InvalidRulesetException e) {
            // This chromosome is invalid
        }

        try (PrintWriter writer = new PrintWriter(new File("test.csv"))) {

            StringBuilder sb = new StringBuilder();
            sb.append("Index");
            sb.append(',');
            sb.append(" Avg Score");
            sb.append(',');
            sb.append(" Mini Score");
            sb.append(',');
            sb.append(" Max Score");
            sb.append('\n');

            for (int i = 0; i < map.size(); i++) {

                statsSummary = evaluateChromosome(map.get(i), numPlayers, numGames);
                sb.append(i);
                sb.append(',');
                sb.append(statsSummary.getMean());
                sb.append(',');
                sb.append(statsSummary.getMin());
                sb.append(',');
                sb.append(statsSummary.getMax());
                sb.append('\n');

                writer.write(sb.toString());

            }



            System.out.println("done!");

        } catch (FileNotFoundException | RuleBasedSystemChromosomeEvaluator.InvalidRulesetException e) {
            System.out.println(e.getMessage());
        }
    }

    public static StatsSummary evaluateChromosome(int[] chromosome, int numPlayers, int numGames) throws RuleBasedSystemChromosomeEvaluator.InvalidRulesetException {
        String agentName = "RuleBasedAgentGivenListOfRulesViaChromosome";

        Random random = new Random();
        StatsSummary statsSummary = new BasicStats();

        for (int i=0; i<numGames; i++) {
            GameRunner runner = new GameRunner("test-game", numPlayers);

            //add your agents to the game
            for (int j=0; j<numPlayers; j++) {
                // the player class keeps track of our state for us...
                Player player = new AgentPlayer(agentName,  new RuleBasedAgentGivenListOfRulesViaChromosome(chromosome));
                runner.addNamedPlayer(agentName, player);
            }
            GameStats stats = runner.playGame(random.nextLong());
            statsSummary.add(stats.score);
            if (stats.disqal!=0) {
                // Our agent was disqualified by the game engine.
                // Hence it appears that this chromosome is not a vlaid rule set.
                throw new RuleBasedSystemChromosomeEvaluator.InvalidRulesetException();
            }
        }
        return statsSummary;
    }

    public static class InvalidRulesetException extends Exception {
    }
}
