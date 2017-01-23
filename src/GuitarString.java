/**
 * Interface for GuitarString
 * @Author Dr. Mikyung Han
 * @Date 1/18/2017
 */
public interface GuitarString {
    //plucks a string
    void pluck();

    //advances time to 1 tic
    void tic();

    //get the current sample
    double sample();
}
