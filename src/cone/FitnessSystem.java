package cone;


import java.util.ArrayList;

public class FitnessSystem extends Parameters {
	private double value = 0d;
	private ArrayList<double[]> values = new ArrayList<double[]> ();
	private double[] trialFitness = new double[nTrials];
	private int trials = 0;
	private int numberOfRuns = 0;

	public FitnessSystem(){
	}
	
	public void add(double ifitness) {
		if(trials == nTrials) {
			values.add(trialFitness);
			trials = 0;
			trialFitness = new double[nTrials];
			numberOfRuns++;
		}
		trialFitness[trials] = ifitness;
		value += ifitness;
		++trials;
	}
	
	public void average() {
		value /= (trials+(nTrials*numberOfRuns));
	}

	//returns the average fitness over all trials/genotypes
	public double getAverage() {
		average();
		return value;
	}
	
	//returns the best fitness trial-set
	public double getBest(){
		double best = 0d;
		for(int i = 0; i < values.size(); i++){
			double avg = 0d;
			for(int j = 0; j < values.get(i).length; j++){
				avg += values.get(i)[j];
			}
			avg /= nTrials;
			if(avg > best) best = avg;
		}
		return best;
	}
	
	public void reset() {
		value = 0d;
		trials = 0;
		values = new ArrayList<double[]> ();
		trialFitness = new double[nTrials];
		numberOfRuns = 0;
	}
	
}
