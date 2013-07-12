package cone;


public class Fitness extends Parameters implements Comparable<Fitness> {

	private double value;
	private double[][] values;
	private int trials, nrGenotypesCombis;
	
	private int avs;


	Fitness() {
		values = new double[maxCombinations][nTrials];
		value = 0d;
		trials = 0;
		nrGenotypesCombis = 0;
		avs = 0;

	}
	
	public void add(int avs){
		this.avs = avs;

	}

	public void add(double ifitness) {
		if(trials == nTrials) {
			trials = 0;
			nrGenotypesCombis++;
		}
		values[nrGenotypesCombis][trials] = ifitness;
		value += ifitness;
		++trials;
	}

	public void average() {
		value /= (trials+(nTrials*nrGenotypesCombis)); //
	}

	//returns the average fitness over all trials/genotypes
	public double getAverage() {
		return value;
	}
	
	//returns the best fitness trial-set
	public double getBest(){
		double best = 0d;
		for(int i = 0; i < values.length; i++){
			double avg = 0d;
			for(int j = 0; j < values[i].length; j++){
				avg += values[i][j];
			}
			avg /= nTrials;
			if(avg > best) best = avg;
		}
		return best;
	}
	
	public void reset() {
		value = 0d;
		trials = 0;
		nrGenotypesCombis = 0;
		avs = 0;
		values = new double[maxCombinations][nTrials];
	}

	public int compareTo(Fitness other) {
		return this.value < other.value? 1: this.value > other.value? -1: 0;
	}
	
	public int getAvs(){
		return avs;
	
}
}
