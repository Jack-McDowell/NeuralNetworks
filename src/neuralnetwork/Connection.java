package neuralnetwork;

import neuralnetwork.neurons.Neuron;

public class Connection {
    Neuron start;
    Neuron end;
    double lastActive;
    double weight;
    double dEdW;
    public Connection(Neuron start, Neuron end){
        weight = Math.random() - 0.5;
        this.start = start;
        this.end = end;
    }
    public void feedBack(double v){
        dEdW += v * lastActive;
        start.feedBack(v * weight);
    }
    public void feedForward(double v){
        end.feedForward((lastActive = v) * weight);
    }
}
