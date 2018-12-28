package neuralnetwork.neurons;

import neuralnetwork.ActivationFunction;
import neuralnetwork.Connection;
import neuralnetwork.Layer;

import java.util.ArrayList;

public class Neuron {
    ArrayList<Connection> inputs;
    ArrayList<Connection> outputs;
    ActivationFunction function;
    double value = 0;
    double activeValue = 0;
    public void activate(){
        activeValue = function.activate(value);
        for(Connection n : outputs)
            n.feedForward(activeValue);
    }
    public void feedForward(double v){
        value += v;
    }
    public void feedBack(double val){
        double derivative = function.derivative(value);
        for(Connection n : inputs){
            n.feedBack(derivative * val);
        }
    }
    public void connectTo(Layer l){
        for(Neuron n : l.getNeurons()){
            Connection c = new Connection(this, n);
            outputs.add(c);
            n.inputs.add(c);
        }
    }
}
