package cone;


public class Neuron{

    private double[] weights;

    public Neuron(double[] geneWeights){
        weights = new double[geneWeights.length];
        for(int i = 0; i < geneWeights.length; i++){
            weights[i] = geneWeights[i];
        }
    }

    public double getOutput(double[] inputs){
        double sum = 0;
        for(int i = 0; i < inputs.length; i++){
            sum =+ weights[i] * inputs[i];
        }
        return (1/(1 + Math.exp(-sum)));
    }

    public double getWeight(int index){
        return weights[index];
    }

    public void setWeight(int index, double weight){
        weights[index] = weight;
    }

    public void setWeights(double[] weights) {
        this.weights = weights;
    }

}
