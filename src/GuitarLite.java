// This is a sample class that implements the Guitar interface.  It is not well
// documented.

public class GuitarLite implements Guitar {
    private GuitarString stringA;
    private GuitarString stringC;

    // create two guitar strings, for concert A and C
    public GuitarLite(boolean useKP) {
        double CONCERT_A = 440.0;
        double CONCERT_C = CONCERT_A * Math.pow(2, 3.0/12.0);
        if(useKP) {
            stringA = new KPGuitarString(CONCERT_A);
            stringC = new KPGuitarString(CONCERT_C);
        }else{
            stringA = new SimpleGuitarString(CONCERT_A);
            stringC = new SimpleGuitarString(CONCERT_C);
        }
    }

    public void playNote(int pitch) {
        if (pitch == 12) {
            stringA.pluck();        //mapped to keyboard 'v'
        } else if (pitch == 15) {
            stringC.pluck();        //mapped to keyboard 'n'
        }
    }

    public boolean hasString(char string) {
        return (string == 'v' || string == 'n');
    }
    
    public void pluck(char string) {
        if (string == 'v') {
            stringA.pluck();
        } else if (string == 'n') {
            stringC.pluck();
        }
    }

    public double sample() {
        return stringA.sample() + stringC.sample();
    }

    public void tic() {
        stringA.tic();
        stringC.tic();
    }

    public int time() {
        return -1;  // not implemented
    }
}
