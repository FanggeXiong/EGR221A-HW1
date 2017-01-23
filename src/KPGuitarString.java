import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

//This class allows the notes of each guitar string oscillate in given frequency to the appropriate sound
public class KPGuitarString implements GuitarString{

    //the size of the ring buffer
    private int N = 0;
    //constant value of the sample rate
    public static final double SRATE = StdAudio.SAMPLE_RATE;
    //the constant energy decay rate when note is played
    public static final double ENERGYRATE = 0.996;
    //ring buffer of guitar that filters notes
    private Queue<Double> ringBuffer = new LinkedList<>();

   //constructs guitar string of given frequency
    public KPGuitarString(double frequency) {

        N = Math.round((float)(SRATE / frequency));
        if (frequency <= 0 || N < 2) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < N; i++) {
            ringBuffer.add(0.0);
        }
    }

    //constructs guitar string and initializes contents of ring buffer to values in array
    //used only for testing purposes
    public KPGuitarString(double[] init) {
        if (init.length< 2) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < init.length; i++) {
            ringBuffer.add(init[i]);
        }
    }

    //replaces the N elements in ring buffer w/ random N values between -.5 and 5
    @Override
    public void pluck(){
        Random rand = new Random();
        for (int i = 0; i < ringBuffer.size(); i++) {
            ringBuffer.remove();
            ringBuffer.add(rand.nextDouble() - .5);
        }
    }

    //creates new element at end of ring buffer due to energy decay rate
    @Override
    public void tic(){
        double a = ringBuffer.remove();
        double b = ringBuffer.peek();
        ringBuffer.add(ENERGYRATE * .5 * (a + b));
    }

    //returns the current sample of ring buffer
    @Override
    public double sample(){
        return ringBuffer.peek();
    }
}