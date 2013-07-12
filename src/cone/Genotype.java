package cone;

import java.util.Arrays;
import java.util.Random;


public class Genotype extends Parameters {
    public double[] inputWeights, outputWeights;
    private Random random = new Random();
    public final Fitness fitness;

    public Genotype(){
        double weight;
        inputWeights = new double[(ninputs+1)*nhiddens];
        outputWeights = new double[noutputs*nhiddens];
            // fill the input/output weights of the Genotype from a Cauchy-distribution
            for (int i=0; i<inputWeights.length;i++){
                weight = random(0d,1d);
                while(weight < lrWeightRange || weight >upWeightRange) weight = random(0d,1d);
                inputWeights[i] += weight;
            }

            for (int i = 0; i<outputWeights.length; i++){
                weight = random(0d,1d);
                while(weight < lrWeightRange || weight > upWeightRange) weight = random(0d,1d);
                outputWeights[i] += weight;
            }
        fitness = new Fitness();



    }
    public Genotype(Genotype other) {
        inputWeights = Arrays.copyOf(other.inputWeights, other.inputWeights.length);
        outputWeights = Arrays.copyOf(other.outputWeights, other.outputWeights.length);
        //
        fitness = new Fitness();
    }
            //randomizer for the Cauchy distribution
    public double random (double mean, double dev){
          return mean + dev * Math.tan(Math.PI*(random.nextDouble()-0.5));
    }

    /*applies crossover to the weights, requires the list with weights and a 'type' int
    to identify if it concerns input or output
    */

    public void crossover (double[] weights, int type){ //0 for input, 1 for output
        switch(type){
            case 0: int coPoint1 = random.nextInt(inputWeights.length);
                for (int i = coPoint1; i<inputWeights.length; i++){
                    inputWeights[i] = weights[i];

        }
            case 1:  int coPoint2 = random.nextInt(outputWeights.length);
                for (int i = coPoint2; i<outputWeights.length; i++){
                    outputWeights[i] = weights[i];


            }
        }

    }

    public void mutate() {
        mutate(inputWeights);
        mutate(outputWeights);
    }

    public void burstMutate() {
        burstMutate(inputWeights);
        burstMutate(outputWeights);
    }

    public void mutate (double[] arr){
        for (int i =0; i<arr.length; i++){
               if(random.nextDouble() < rate){
                   double noise = random(0d, cauchyScale);
                   while(noise < lrMutationRange || noise > upMutationRange) noise = random(0d,cauchyScale);
                   arr[i] += noise;
            }
        }
    }
    public void burstMutate (double[] arr){
        for (int i = 0; i<arr.length; i++){
            if(random.nextDouble() < burstRate)
                arr[i] += random(0d,cauchyScale);
        }

    }
        public void recombine(Genotype mate){
            crossover(mate.iweights(),0);
            crossover(mate.oweights(),1);
        }

        public double[] iweights() {
            return inputWeights;
        }

        public double[] oweights() {
            return outputWeights;
        }

    }

