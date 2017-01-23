// Stuart Reges
// This program constructs a Guitar object that it allows the user to play.

import java.util.Scanner;

/** This class has a main method that lets user select the guitar type and play music using the guitar
 */
public class GuitarHero {
    public static void main(String[] args) {
        Guitar g = selectGuitar();
        play(g);
    }

    //Asks user input to select the Guitar type
    public static Guitar selectGuitar(){
        System.out.println("Which Guitar you want to use?");
        System.out.print("Type \"A\" or \"a\" for GuitarLite, or type any other key for Guitar37 : ");
        Scanner s = new Scanner(System.in);
        String userInput = s.next();

        if (userInput.equalsIgnoreCase("a")) {
            System.out.println("Using GuitarLite ... only \"v\" and \"n\" are valid key to play the sound.");
            return new GuitarLite(true);

        } else {
            System.out.println("Using Guitar37 ... You can use below keys to play the sound.");
            System.out.println("\"q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' \"");
            return new Guitar37(true);
        }
    }

    //Plays tunes given the Guitar, where tunes are keyboard input from user
    public static void play(Guitar g) {
        // this is an infinite loop--user must quit the application
        for (; ; ) {
            // check if the user has typed a key; if so, process it   
            if (StdDraw.hasNextKeyTyped()) {
                char key = Character.toLowerCase(StdDraw.nextKeyTyped());
                if (g.hasString(key)) {
                    g.pluck(key);
                } else {
                    System.out.println("bad key: " + key);
                }
            }
            // send the result to the sound card
            StdAudio.play(g.sample());
            g.tic();
        }
    }
}
