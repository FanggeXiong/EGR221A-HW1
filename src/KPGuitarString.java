//Hannah Bernal

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

//This class allows the notes of each guitar string oscillate in given frequency to the appropriate sound

public class KPGuitarString implements GuitarString{

    //The size of the ring buffer
    private int N = 0;
    //Constant value of the sample rate
    private static final double SRATE = StdAudio.SAMPLE_RATE;
    //The constant energy decay rate when note is played
    private static final double ENERGYRATE = 0.996;
    //Ring buffer of guitar that filters notes
    private Queue<Double> ringBuffer = new LinkedList<>();

   //Constructs guitar string from the given frequency
    public KPGuitarString(double frequency) {
        N = Math.round((float)(SRATE / frequency));
        if (frequency <= 0 || N < 2) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < N; i++) {
            ringBuffer.add(0.0);
        }
    }

    //Constructs guitar string and inserts values into the ring buffer
    //Used only for testing purposes
    public KPGuitarString(double[] init) {
        if (init.length < 2) {
            throw new IllegalArgumentException();
        }
        for (double num: init) {
            ringBuffer.add(num);
        }
    }

    //Replaces the values of the ring buffer with random values -.5 to .5
    @Override
    public void pluck(){
        Random rand = new Random();
        for (int i = 0; i < ringBuffer.size(); i++) {
            ringBuffer.remove();
            ringBuffer.add(rand.nextDouble() - .5);
        }
    }

    //Deletes the first sample from the ring buffer and adds the average of
    //the first two samples scaled by the energy decay factor
    @Override
    public void tic(){
        ringBuffer.add(ENERGYRATE * .5 * (ringBuffer.remove() + ringBuffer.peek()));
    }

    //Returns the current sample of ring buffer
    @Override
    public double sample(){
        return ringBuffer.peek();
    }
}