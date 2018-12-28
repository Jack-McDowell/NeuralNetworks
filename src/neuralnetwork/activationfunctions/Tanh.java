package neuralnetwork.activationfunctions;

import neuralnetwork.ActivationFunction;

public class Tanh extends LookupTable implements ActivationFunction {
    public Tanh(){
        super(v -> Math.tanh(v), -10, 10, 1025);
    }
    @Override public double activate(double d){
        return lookup(d);
    }
    @Override public double derivative(double d){
        double val = lookup(d);
        return (1.0 - val * val);
    }
}
