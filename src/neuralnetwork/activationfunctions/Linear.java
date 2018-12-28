package neuralnetwork.activationfunctions;

import neuralnetwork.ActivationFunction;

public class Linear implements ActivationFunction {
    public double activate(double d){
        return d;
    }
    public double derivative(double d){
        return 1;
    }
}
