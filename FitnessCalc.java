package com.fossgalaxy.games.fireworks.ai.username.GAEngine_Sample;

public class FitnessCalc {

    static byte[] solution = new byte[10];

    /* Public Methods */
    // Set a candidate solution as a byte array
    public static void setSolution(byte[] newSolution) {
        solution = newSolution;
    }

    // Set our candidate solution with string of 0s and 1s
    static void setSolution(String newSolution) {
        solution = new byte[newSolution.length()];

        // Loop through each character of the string adn save it in the byte array
        for (int i = 0; i < newSolution.length(); i++) {
            String character = newSolution.substring(i, i+1);
            if (character.contains("0") || character.contains("1")) {
                solution[i] = Byte.parseByte(character);
            } else {
                solution[i] = 0;
            }
        }
    }

    // Calculate individuals fitness by comparing it to our candidates solution
    static int getFitness(Individual individual) {
        int fitness = 0;

        // Loop through the individuals chromosomes and compare them to the candidates
        for (int i = 0; i < individual.size() && i < solution.length; i++) {
            if (individual.getChromosome(i) == solution[i]) {
                fitness++;
            }
        }
        return fitness;
    }

    // Get optimum fitness
    static int getMaxFitness() {
        int maxFitness = solution.length;
        return maxFitness;
    }

}
