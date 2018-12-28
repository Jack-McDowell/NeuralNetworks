package neuralnetwork;

public interface ActivationFunction {
    double activate(double inSum);
    double derivative(double input);
}
