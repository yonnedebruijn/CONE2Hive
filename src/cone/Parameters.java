package cone;

public class Parameters {
    public static final int
    nrPopulations = 3,
    popsize =  20, // population size
    ninputs =  4, // number of input neurons
    nhiddens =  10, // number of hidden neurons
    noutputs =  2, // number of output neurons

    upMutationRange = 1, //upper range the mutation can take
    lrMutationRange = -1, // lower range the mutation can take
    lrWeightRange = -1, // lower weight range of the neurons at initialisation
    upWeightRange = 1, // upper weight range of the neurons at initialisation
    nTrials = 3, // number of trials per gene
    generations = 50, // number of currentGen the program should run
    maxNrPops = 2, // maximum number of populations allowed
    maxCombinations = 40, //number of random combinations are run for every genome
    lifetime = 100;
    public static final double
            elitePortion = 0.20, // elite portion of the population (20%)
            parentPortion = 0.40, // parent portion of the population (40%)
            rate = 0.05, // mutation rate
            burstRate = 0.05, // burst mutation rate
            cauchyScale = 0.3; //cauchy scale for mutation
    public static final boolean bias = true; // boolean if the bias is added or no
    public final static int nr_of_wolfs = 3;
}