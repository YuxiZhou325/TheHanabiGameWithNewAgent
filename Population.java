package com.fossgalaxy.games.fireworks.ai.username.GAEngine_Sample;

public class Population {
    Individual[] individuals;

    /* Constructors */

    // Create a population
    public Population(int populationSize, boolean initialize) {
        individuals = new Individual[populationSize];
        // Initialize population
        if (initialize) {
            // Loop and create individuals
            for (int i = 0; i < size(); i++){
                Individual newInidividual = new Individual();
                newInidividual.generateIndividual();
                saveIndividual(i, newInidividual);
            }
        }
    }

    /* Getters */
    public Individual getIndividual(int index) {
        return individuals[index];
    }

    public Individual getFittest() {
        Individual fittest = individuals[0];
        // Loop through the individuals to find fittest
        for (int i = 0; i < size(); i++) {
            if (fittest.getFitness() <= getIndividual(i).getFitness()) {
                fittest = getIndividual(i);
            }
        }
        return fittest;
    }


    /* Public Methods */
    // Get population size
    public int size() {
        return individuals.length;
    }
    // Save individual
    public void saveIndividual(int index, Individual indiv) {
        individuals[index] = indiv;
    }
}
