package simulatie;

import state.World;
import cone.*;
import state.*;
import java.util.ArrayList;
import java.util.Random;

public class Simulator extends Parameters {
    public World world;
    private ArrayList<Population> pops;
    private boolean popAdded;
    private Population currentPopEvaluated;

    public Simulator(World world) {
        this.world = world;
        pops = new ArrayList<Population>();
        for (int i = 0; i < nrPopulations; i++) {
            pops.add(new Population(popsize));

        }
        popAdded = false;
    }

    //returns the number of populations/agents
    public int nrPopulations() {
        return pops.size();
    }

    //adds a new population/agent
    //SOLUTION: add int i that will tell which agent it is -> which population has to be currentPopEvaluated!
    public void addPopulation(int popIndex) {
        if (maxNrPops > pops.size() && popAdded == false) {
            pops.add(new Population(popsize, pops.get(popIndex)));
            pops.get(pops.size() - 1).burstMutation();
            popAdded = true;
        }
    }

    public void start() {

        for (long g = 0; g < generations; g++) {
            if(g==0) world.setLife();
            Genotype[][] combiMatrix = combiMatrix();
            for (int c = 0; c < combiMatrix[0].length; c++) {
                world.trials = 0;
                currentPopEvaluated = pops.get(0);
                for (int t = 0; t < nTrials; t++) {
                    int nrAg = 0;
                    for (Wolf w : world.wolfs){
                        w.controllerUpdate(combiMatrix[nrAg][c]);
                        nrAg++; // end of wolf update loop, adds the genes into the controller
                    }
                        world.start(); //start the world


                    world.reset(); //reset the world
                    world.trials++;
                } // end of nTrials loop
            } // end of combiMatrix loop
            world.currentGen++;
                for (Population p : pops)
                    p.resetfitness();

            for (Population p : pops) {
                p.averagefitness();
                p.sortfitness();
            }
            world.getFitnessSystem().average();
            Genotype[] genotypes = getBestGenotypes();
            world.resetFitnessSystem();

            for(Population p : pops){
                p.recombine();
                p.mutate();
                p.resetfitness();


        }

    } // end of generations loop
    }

        private Genotype[][] combiMatrix(){
            Random rand = new Random();
            int index = 0;

            int totalCombinations = popsize * maxCombinations;
            if (Math.pow((double) popsize, (double) pops.size()) < totalCombinations)
                totalCombinations = (int) Math.pow((double) popsize, (double) pops.size());

            int combinations = totalCombinations / popsize;

            Genotype[][] combi = new Genotype[pops.size()][totalCombinations];
            for (int p = 0; p < pops.size(); p++) {
                for (int s = 0; s < popsize; s++) {
                    for (int c = 0; c < combinations; c++) {
                        //first population just fill the matrix from 0-combinations*popsize
                        if (p == 0) {
                            combi[p][s * combinations + c] = pops.get(p).genotypes()[s];
                        } else {
                            //fill the matrix randomly, making the combinations random
                            index = rand.nextInt(popsize * combinations);
                            while (combi[p][index] != null) index = rand.nextInt(popsize * combinations);
                            combi[p][index] = pops.get(p).genotypes()[s];
                        }
                    }
                }
            }


            return combi;

        }

    private int getIndex(int k, int i, Population p) {
        int index = 0;
        if (i == 0) index = k - ((int) Math.floor((k - 1) / p.genotypes().length) * p.genotypes().length);
        else {
            k = (int) Math.ceil(k / (Math.pow(p.genotypes().length, i)));
            index = getIndex(k, 0, p);
        }
        return index;
    }

    private Genotype[] getBestGenotypes() {
        Genotype[] genotypes = new Genotype[pops.size()];
        int i = 0;
        for (Population p : pops) {
            genotypes[i] = p.getBestGenotype();
            i++;
        }
        return genotypes;
    }
        }




