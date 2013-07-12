package cone;



public class Controller extends Parameters {

    public final Neuron[] hNeurons, oNeurons; //arrays of hidden-nodes and output-nodes
    private double[] outputHidden;
    public Genotype currentGenotype;
    int add = 1;

    public Controller(){
        /* initializes the array of hNeurons/oNeurons (with their respective length)
        + initializes array of double[] that will contain the oNeurons of the hNeurons neurons,
        which are the input for the oNeurons neurons.
        */
        hNeurons = new Neuron[nhiddens];
        oNeurons = new Neuron[noutputs];
        outputHidden = new double[nhiddens];



            for(int i=0; i < hNeurons.length; i++){ //initialize the hidden-nodes with weights 0
                double[] weights = new double[ninputs+add]; //add in the bias
                    for(int j=0; j<ninputs; j++){
                        weights[j]=0;

                    }
                hNeurons[i] = new Neuron(weights);
            }
            for(int i=0; i<oNeurons.length; i++){  //initialize the output-nodes with weights 0
                double[] weights = new double[nhiddens];
                for(int j=0; j<nhiddens; j++){
                    weights[j]=0;
                }
                oNeurons[i] = new Neuron(weights);
            }

    }
    public void update(Genotype genotype){
        double[] iweights = genotype.iweights();
        double[] oweights = genotype.oweights();

        currentGenotype = genotype;

        //update the hidden layer neurons
        for(int i = 0; i < hNeurons.length; i++){
            for(int j = 0; j < ninputs+add; j++){

               hNeurons[i].setWeight(j, iweights[j+i*(ninputs+add)]);

            }
        }
        System.out.println("update hidden layers completed");

        //update the output layer neurons
        for(int i = 0; i < oNeurons.length; i++){
            for(int j = 0; j < nhiddens; j++){

                oNeurons[i].setWeight(j, oweights[j+i*nhiddens]);
            }
        }
        System.out.println("update output layers completed");
    }
    private void outputHidden(double [] inputs){
        for (int i = 0;i<hNeurons.length; i++){
            outputHidden[i] = hNeurons[i].getOutput(inputs);
        }
    }
    public double[] getOutputs(double[] inputs){
        double[] outputs = new double[oNeurons.length];
        double[] tempInputs = new double[inputs.length+1];
        tempInputs[0] = 1;
        for(int i = 0; i < inputs.length; i++){
            tempInputs[i+1] = inputs[i];
        }
        inputs = tempInputs;

        outputHidden(inputs);

        for(int i = 0; i < outputs.length; i++){
            outputs[i] = oNeurons[i].getOutput(outputHidden);
        }
        return outputs;
    }
    public int getNinputs(){
        return ninputs;
    }
    public int getNoutputs(){
        return noutputs;
    }
}
