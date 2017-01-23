//this class implements Guitar interface to allow full version of guitar strings
public class Guitar37 implements Guitar {

    //array holds the concert for each note
    private GuitarString[] keyStrings = new GuitarString[37];
    //the list of keyboard keys allowed to be played
    public static final String KEYBOARD =
            "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    //maps to keyStrings, holds respective frequency for each concert
    private double[] concertFrequency = new double[37];
    //counts the number of times tic is called
    private int count = 0;

    //constructor uses either the full or simple version of strings
    public Guitar37(boolean useKP) {
        //possibly make it into helper method???
        for (int i = 0; i < KEYBOARD.length(); i++) {
            concertFrequency[i] = 440 * Math.pow(2, (i-24 + 0.0) / 12);
        }
            //instantiate KPGuitarString and assign it to each element in the array
        for (int i = 0; i < concertFrequency.length; i++) {
            if (useKP) {
                keyStrings[i] = new KPGuitarString(concertFrequency[i]);
            }
            else {
                keyStrings[i] = new SimpleGuitarString(concertFrequency[i]);
            }
        }
    }

    //method uses given pitch to choose note to play
    @Override
    public void playNote(int pitch) {
       int index = pitch + 12;
        if (index < 0 || index >= 37) {
            return;
        }
        keyStrings[index].pluck();
    }

    //method returns whether or not the inputted key is permitted
    @Override
    public boolean hasString(char key) {
        return (KEYBOARD.contains(key + ""));
    }

    //method uses given key to pluck one of notes
    @Override
    public void pluck(char key) {
        if (!KEYBOARD.contains(key + "")) {
            throw new IllegalArgumentException();
        }
        keyStrings[KEYBOARD.indexOf(key)].pluck();
    }

    //method calculates and returns sample of list of notes played
    @Override
    public double sample() {
        double sum = 0;
        for (int i = 0; i < keyStrings.length; i++) {
            sum += keyStrings[i].sample();
        }
        return sum;
    }

    //method tics each concert played
    @Override
    public void tic() {
        for (int i = 0; i < keyStrings.length; i++) {
            keyStrings[i].tic();
        }
        count++;
    }

    //method returns the length of time that tic is called
    @Override
    public int time() {
        return count;
    }
}