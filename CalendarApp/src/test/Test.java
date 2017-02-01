
package test;

/**
 *
 * @author Faust
 */

/*
    This abstract class will be used by the individual test classes to ensure
    that they all follow the same structure and are easy to use.
*/
public abstract class Test {
    /*
        This method will be called to execute each test class. The int returned 
        will be the number of errors that the test encountered.
    */
    public abstract int run();
}
