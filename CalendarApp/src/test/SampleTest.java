
package test;

/**
 *
 * @author Faust
 */
class SampleTest extends Test{
    
    public SampleTest() {
        //System.out.println("Creating the SampleTest.");
    }

    @Override
    public int run() {
        //System.out.println("Running the SampleTest.");
        int errorCount = 0;
        System.out.println("SampleTest encountered: " + errorCount + " errors.");
        return errorCount;
    }
    
}
