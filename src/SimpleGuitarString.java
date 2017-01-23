// This class is used for debugging the Guitar37 class.  It is not an example
// to be emulated.  When a string is plucked, it is set to the integer part of
// the string's frequency plus 0.25.  It goes down by 10 each time tic is
// called until it becomes less than or equal to 10 when it is set to 0.

public class SimpleGuitarString implements GuitarString {
    double value;
    double freq;

    public SimpleGuitarString(double frequency) {
        freq = frequency;
    }

    @Override
    public void pluck() {
        value = (int) freq + 0.25;
    }

    @Override
    public void tic() {
        if (value <= 10) {
            value = 0.0;
        } else {
            value = value - 10;
        }
    }

    @Override
    public double sample() {
        return value;
    }
}
