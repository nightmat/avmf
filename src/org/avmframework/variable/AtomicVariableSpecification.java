package org.avmframework.variable;

public class AtomicVariableSpecification {

    public static final int PRECISION_DEFAULT = 0;
    public static final int STEP_DEFAULT = 1;
    public static final int ACCELERATION_BASE_DEFAULT = 2;

    protected int min = Integer.MIN_VALUE;
    protected int max = Integer.MAX_VALUE;
    protected int precision = PRECISION_DEFAULT;
    protected int step = STEP_DEFAULT;
    protected int accelerationBase = ACCELERATION_BASE_DEFAULT;
    
    public AtomicVariableSpecification() {
    }

    public AtomicVariableSpecification(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public AtomicVariableSpecification(int min, int max, int precision) {
        this(min, max);
        this.precision = precision;
    }

    public AtomicVariableSpecification(int min, int max, int step, int accelerationBase) {
        this(min, max);
        this.step = step;
        this.accelerationBase = accelerationBase;
    }

    public AtomicVariableSpecification(int min, int max, int precision, int step, int accelerationBase) {
        this(min, max, step, precision);
        this.step = step;
        this.accelerationBase = accelerationBase;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public int getPrecision() {
        return precision;
    }

    public int getStep() {
        return step;
    }

    public int getAccelerationBase() {
        return accelerationBase;
    }
}