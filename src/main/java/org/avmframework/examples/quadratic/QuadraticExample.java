package org.avmframework.examples.quadratic;

import org.apache.commons.math3.random.MersenneTwister;
import org.apache.commons.math3.random.RandomGenerator;
import org.avmframework.AVM;
import org.avmframework.Monitor;
import org.avmframework.TerminationPolicy;
import org.avmframework.initialization.RandomInitializer;
import org.avmframework.localsearch.IteratedPatternSearch;
import org.avmframework.localsearch.LocalSearch;
import org.avmframework.Vector;
import org.avmframework.objective.NumericObjectiveValue;
import org.avmframework.objective.ObjectiveFunction;
import org.avmframework.objective.ObjectiveValue;
import org.avmframework.variable.FloatingPointVariable;

public class QuadraticExample  {

    static final int A = 4, B = 10, C = 6;

    static final int PRECISION = 1;
    static final double INITIAL_VALUE = 0.0, MIN = -100.0, MAX = 100.0;

    static final LocalSearch LOCAL_SEARCH = new IteratedPatternSearch();

    static final int MAX_EVALUATIONS = 100;
    static final TerminationPolicy TERMINATION_POLICY = TerminationPolicy.maxEvaluations(MAX_EVALUATIONS);

    public static void main(String[] args) {

        // define the objective function
        ObjectiveFunction objFun = new ObjectiveFunction() {
            @Override
            protected ObjectiveValue computeObjectiveValue(Vector vector) {
                double x = ((FloatingPointVariable) vector.getVariable(0)).getValueAsDouble();
                double y = (A * x * x) + (B * x) + C;
                double distance = Math.abs(y);
                return NumericObjectiveValue.LowerIsBetterObjectiveValue(distance, 0);
            }
        };

        // set up the vector to be optimized
        Vector vector = new Vector();
        vector.addVariable(new FloatingPointVariable(INITIAL_VALUE, PRECISION, MIN, MAX));

        // set up the random generator
        RandomGenerator rg = new MersenneTwister();

        // set up the initializer
        RandomInitializer ri = new RandomInitializer(rg);

        // set up the AVM
        AVM avm = new AVM(LOCAL_SEARCH, TERMINATION_POLICY, ri);

        // perform the search
        Monitor monitor = avm.search(vector, objFun);

        // output the results
        System.out.println("Best solution: " + monitor.getBestVector().getVariable(0));
        System.out.println("Best objective value: " + monitor.getBestObjVal());
        System.out.println("Number of objective function evaluations: " + monitor.getNumEvaluations());
    }
}