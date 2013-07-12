package cone;

import java.util.Arrays;
import java.util.Random;

public class Population extends Parameters{

	
	private Genotype[] genotypes;
	private final int nbestgenos, nparentgenos;
    Random random = new Random();

	public Population(int popsize) {

		genotypes = new Genotype[popsize];
		for (int i = 0; i < genotypes.length; ++i)
			genotypes[i] = new Genotype();
		//
		nbestgenos = (int)(popsize * elitePortion);
		nparentgenos = (int) (popsize * parentPortion);
	}
	
	public Population(int popsize, Population copy){
		genotypes = new Genotype[popsize];
		for (int i = 0; i < genotypes.length; ++i)
			genotypes[i] = new Genotype(copy.genotypes()[i]);
		//
		nbestgenos = (int)(popsize * elitePortion);
		nparentgenos = (int) (popsize * parentPortion);
	}
	
	public Genotype[] genotypes(){
		return genotypes;
	}
	
	public Genotype getBestGenotype(){
		Genotype bestG = null;
		for(Genotype g : genotypes){
			if(bestG == null) bestG = g;
			else {
				if(bestG.fitness.getAverage() < g.fitness.getAverage()) bestG = g;
			}
		}
		return bestG;
	}

	public Genotype randomgenotype() {
		return genotypes[random.nextInt(genotypes.length)];
	}

	public void averagefitness() {
		for (Genotype g : genotypes)
			g.fitness.average();
	}

	//returns the average fitness over all genes
	public double getAvgFitness(){
		double avg = 0d;
		for(Genotype g : genotypes)
			avg += g.fitness.getAverage();
		
		return avg / genotypes.length;
	}
	
	public double getBestFitness(){
		double best = 0d;
		for(Genotype g : genotypes){
			double gBest = g.fitness.getBest();
			if(gBest > best) best = gBest;
		}	
		return best;
	}
	
	public int getBestAvs(){
		int bestAvs = 0;
		for(Genotype g : genotypes){
			int gAvs = g.fitness.getAvs();
			if(gAvs > bestAvs) bestAvs = gAvs;
		}
		return bestAvs;
	}
	
	public double getAvgAvs(){
		int totalAvs = 0;
		for(Genotype g : genotypes){
			totalAvs += g.fitness.getAvs();
		}
		return totalAvs / genotypes.length;
	}

	public void sortfitness() {
		Arrays.sort(genotypes);
	}

	public void resetfitness() {
		for (Genotype g : genotypes)
			g.fitness.reset();
	}

	public void recombine() {
		Genotype[] newGenotypes = new Genotype[genotypes.length];
		
		//elite portion survives
		for(int i = 0; i < genotypes.length; i++){
			if(i < nbestgenos)
				newGenotypes[i] = genotypes[genotypes.length-1-i]; //best genes are at the end (ascending sort)
			else {
				int mate1Idx = mate(999);
				int mate2Idx = mate(mate1Idx);
				newGenotypes[i] = new Genotype(genotypes[mate1Idx]);
				newGenotypes[i].recombine(genotypes[mate2Idx]);
			}
		}
		
		genotypes = newGenotypes;
	}


	//mutate every gene
	public void mutate() {
		for (int i = 0; i < genotypes.length; ++i)
			genotypes[i].mutate();
	}
	
	public void burstMutation(){
		for(int i = 0; i < genotypes.length; i++)
			genotypes[i].burstMutate();
	}

	//if idx = 999 -> choose a random index, otherwise choose a random index that is not idx
	private int mate(int idx) {
		int rdm;
		if(idx == 999) 
			rdm = popsize-1 - random.nextInt(nparentgenos-1);
		else {
			do {
				rdm = popsize-1 - random.nextInt(nparentgenos-1);
			} while (rdm == idx);
		}
		return rdm;
	}
}
