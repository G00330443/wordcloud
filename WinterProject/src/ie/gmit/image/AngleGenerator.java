package ie.gmit.image;

import java.util.Random;


//Ω«∂»…Ë÷√
public class AngleGenerator {

    private static final Random RANDOM = new Random();

    private final int steps;

    private final double[] thetas;

    private int next = 0;

    public AngleGenerator() {
        steps = 3;
        thetas = calculateThetas(-90, 90);
    }

    public AngleGenerator(double angle, double angle1, int steps) {
    	System.out.println(angle+"------------"+angle1);
        this.steps = steps;
        thetas = calculateThetas(angle, angle1);
    }
    
    public double next() {
        return thetas[next++ % steps];
    }

    public double randomNext() {
        return thetas[RANDOM.nextInt(steps)];
    }

    
    private double[] calculateThetas(final double angle, final double angle2) {
        final double stepSize = (angle - angle2) / (steps - 1);
        final double[] thetas = new double[steps];
        for(int i = 0; i < steps; i++) {
            thetas[i] = degreesToRadians(angle2 + (i * stepSize));
        }
        return thetas;
    }

    private double degreesToRadians(final double degrees) {
        return Math.PI * degrees / 180.0;
    }

}
