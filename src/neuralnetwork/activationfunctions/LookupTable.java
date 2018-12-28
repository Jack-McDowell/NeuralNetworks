package neuralnetwork.activationfunctions;

import java.util.function.Function;

public class LookupTable {
    private Function<Double, Double> func;
    private double min, max;
    private int steps;

    private double[] map;

    private double minV, maxV;

    /**
     * Creates a lookup table for given function func. `steps` evenly spaced values starting at `min`
     * and ending at `max` will be calculated, and these will be used for lookups. For higher accuracy,
     * increase `steps`. For faster initialization and lower memory usage, decrease `steps`. By default,
     * values outside the range [`min`, `max`] will map to `min` or `max`, depending on which is closer.
     * @param func The function to make a lookup table for
     * @param min The minimum value in the lookup table
     * @param max The maximum value in the lookup table
     * @param steps The number of values in the lookup table. Must be >= 2
     */
    public LookupTable(Function<Double, Double> func, double min, double max, int steps){
        this.func = func;
        this.min = min;
        this.max = max;
        this.steps = steps;

        map = new double[steps];

        for(int i = 0; i < steps; i++){
            double inValue = min + (((double)i) / (steps - 1)) * (max - min);
            map[i] = func.apply(inValue);
        }

        minV = func.apply(min);
        maxV = func.apply(max);
    }

    /**
     * If `val` is in the range of the lookup table, it finds the two nearest points and performs a linear
     * interpolation of the values. If `val` is greater than `max`, this returns `func(max)`. Otherwise,
     * `func(min)` will be returned. These values can be changed with `setMinValue` and `setMaxValue`
     * @param val The input value for the function
     * @return The estimated output value of the function
     */
    public double lookup(double val){
        if(val > max)
            return maxV;
        else if(val < min)
            return minV;
        else {
            double stepNum = (val - min) / (max - min) * (steps - 1);
            //Guaranteed to be in range [0, steps]

            int lowerValue = (int) stepNum;
            int upperValue = (int) stepNum + 1;

            double r = stepNum - lowerValue;

            return map[lowerValue] * (1 - r) + map[upperValue] * r;
        }
    }

    /**
     * Returns the exact value of the function at a given point
     * @param val The input value for the function
     * @return The exact output value of the function
     */
    public double calcExat(double val){
        return func.apply(val);
    }

    /**
     * Sets the value returned if the input value is below the minimum value in the lookup table
     * @param minV The value to be returned in this case
     */
    public void setMinValue(double minV){
        this.minV = minV;
    }

    /**
     * Sets the value returned if the input value is above the maximum value in the lookup table
     * @param maxV The value to be returned in this case
     */
    public void setMaxValue(double maxV){
        this.maxV = maxV;
    }
}
