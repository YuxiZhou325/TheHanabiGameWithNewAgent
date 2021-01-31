package com.fossgalaxy.games.fireworks.ai.username.GAEngine_Sample;

public class Individual {


    static  int defaultGChromosomeLength = 10;
    private byte[] chromosomes = new byte[defaultGChromosomeLength];

    //Cache
    private int fitness = 0;

    // Create a random individual
    public void generateIndividual() {
        for (int i = 0; i < size(); i++) {
            byte chromosome = (byte) Math.round(Math.random());
            chromosomes[i] = chromosome;
        }
    }

    // Getters and Setters
    // Create Individuals with different chromosome length
    public static void setDefaultGChromosomeLength(int length) {
        defaultGChromosomeLength = length;
    }

    public byte getChromosome(int index) {
        return chromosomes[index];
    }

    public void setChromosomes(int index, byte value) {
        chromosomes[index] = value;
        fitness = 0;
    }

    // Public Methods
    public int size() {
        return chromosomes.length;
    }

    public int getFitness() {
        if (fitness == 0) {
            fitness = FitnessCalc.getFitness(this);
        }
        return fitness;
    }



    @Override
    public String toString() {
        String chromosomeString = "";
        for (int i = 0; i < size(); i++) {
            chromosomeString += getChromosome(i);
        }
        return chromosomeString;
    }

}
