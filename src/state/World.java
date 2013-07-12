package state;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cone.FitnessSystem;
import cone.Parameters;
import simulatie.Simulator;


public class World extends Parameters
{
    final Random random;
    final public Dimensions dimensions;
    final public double agentSize;
    final public List<Wolf> wolfs = new ArrayList<Wolf>();
    public Sheep sheep;
    public int iteration;
    public int currentGen;
    public int trials;
    private FitnessSystem fitnessSystem;

    public World(Random random, Dimensions dimensions,double agentSize)
    {
        this.random = random;
        this.dimensions = dimensions;
        this.agentSize = agentSize;
        iteration=0;
        fitnessSystem = new FitnessSystem();

        // initialize foes
        initSheep();
        initWolf();
    }

    public Coordinate rndCoord()
    {
        return new Coordinate(random, dimensions);
    }

    public double rndDirection()
    {
        return random.nextDouble() * Math.PI * 2;
    }

    public void initWolf(){
    wolfs.clear();
    for(int i = 0; i < nr_of_wolfs; i++)
        wolfs.add(new Wolf(rndCoord(), rndDirection(), 1, this));

}
    public void setLife(){
        for (Wolf w: wolfs){
            w.init();
        }
        sheep.init();
    }
    public void initSheep(){
       sheep = new Sheep(rndCoord(), rndDirection(),0, this);
    }
    public void reset(){
        iteration=0;
        initWolf();
        initSheep();
        }

    public void start(){

        for (long li = 0; li < lifetime; li++) {

            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            if(sheep.captured())  break;
            for (Wolf w : wolfs)
                    w.action();
            iteration++;
        }

        double fitness = getFitness();
        System.out.println("Fitness:" + fitness);
        for(Wolf w : wolfs){
            w.controller.currentGenotype.fitness.add(fitness);
    }
        fitnessSystem.add(fitness);

    }

    public FitnessSystem getFitnessSystem(){
            return fitnessSystem;
        }
    public void resetFitnessSystem(){
        fitnessSystem = new FitnessSystem();
    }
    public double getFitness(){
       return 1  / iteration;


    }
    }



