package com.fossgalaxy.games.fireworks.ai.username.GAEngine_Sample;

public class GA {
    public static void main(String[] args) {

        // Set a candidate solution
        FitnessCalc.setSolution("1111100000");

        // Create an initial population
        Population myPop = new Population(10, true);

        // Evolve the population until reach an optimal solution
        int generationCounter = 0;
        while (myPop.getFittest().getFitness() < FitnessCalc.getMaxFitness()) {
            generationCounter++;
            System.out.println("Generation: " + generationCounter + " Fittest: " + myPop.getFittest().getFitness());
            myPop = Algorithm.evolvePopulation(myPop);
        }

        System.out.println("Solution found!");
        System.out.println("Generation: " + generationCounter);
        System.out.println("Genes:");
        System.out.println(myPop.getFittest());

    }
}
