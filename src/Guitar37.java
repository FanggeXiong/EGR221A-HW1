//This program uses this class to play the guitar in all 37 keys using
//the keyboard and by implementing the Guitar interface.
//Start the program and press any key to play Guitar37
public class Guitar37 implements Guitar {

    //Holds the concert for each note
    private GuitarString[] keyStrings = new GuitarString[37];
    //The list of keyboard keys allowed to be played
    public static final String KEYBOARD =
            "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    //Maps to keyStrings, holds respective frequency for each concert
    private double[] concertFrequency = new double[37];
    //Counts the number of times tic is called
    private int count = 0;

    //Helper method initializes calculated frequencies into the concertFrequency array
    private double[] helperMethod() {
        for (int i = 0; i < KEYBOARD.length(); i++) {
            concertFrequency[i] = 440 * Math.pow(2, (i - 24 + 0.0) / 12);
        }
        return concertFrequency;
    }

    //Constructor uses either the full or simple version of strings
    public Guitar37(boolean useKP) {
        //Helper Method is called to return value of array to this constructor
        helperMethod();
        //Instantiates each value of keyStrings to either KPGuitarString or SimpleGuitarString
        // depending on the value of useKP
        for (int i = 0; i < concertFrequency.length; i++) {
            if (useKP) {
                keyStrings[i] = new KPGuitarString(concertFrequency[i]);
            }
            else {
                keyStrings[i] = new SimpleGuitarString(concertFrequency[i]);
            }
        }
    }

    //Method uses given pitch to choose note to play
    @Override
    public void playNote(int pitch) {
       int index = pitch + 12;
        if (index < 0 || index >= 37) {
            return;
        }
        keyStrings[index].pluck();
    }

    //Method returns whether or not the inputted key is permitted
    @Override
    public boolean hasString(char key) {
        return (KEYBOARD.contains(key + ""));
    }

    //Method uses given key to pluck the corresponding key
    @Override
    public void pluck(char key) {
        if (!KEYBOARD.contains(key + "")) {
            throw new IllegalArgumentException();
        }
        keyStrings[KEYBOARD.indexOf(key)].pluck();
    }

    //Method calculates and returns sample of list of notes played
    @Override
    public double sample() {
        double sum = 0;
        for (GuitarString keys:keyStrings) {
            sum += keys.sample();
        }
        return sum;
    }

    //Method "tics" each concert played
    @Override
    public void tic() {
        for (GuitarString keys:keyStrings) {
            keys.tic();
        }
        count++;
    }

    //Method returns the length of time that tic is called
    @Override
    public int time() {
        return count;
    }
}