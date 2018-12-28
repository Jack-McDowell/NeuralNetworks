package neuralnetwork.activationfunctions;

import neuralnetwork.ActivationFunction;

public class Sigmoid extends LookupTable implements ActivationFunction {
    public Sigmoid(){
        super(v -> 1.0 / (1 + Math.exp(-v)), -10, 10, 1025);
    }
    @Override public double activate(double d){
        return lookup(d);
    }
    @Override public double derivative(double d){
        double val = lookup(d);
        return val * (1.0 - val);
    }
}
