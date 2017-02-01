
package test;

/**
 *
 * @author Faust
 */

/* 
    This is the test harness for the calendar app this file will be executed to
    check for errors while updating and changing the project. This harrness will
    create other test objects that will test more specific files.
*/
public class TestHarness {
    private int totalErrorCount = 0;
    
    public TestHarness() {
        //System.out.println("Creating the TestHarness.");
        run();
    }
    
    private void run() {
        System.out.println("Running the Test Harness.");
        SampleTest sampleTest = new SampleTest();
        totalErrorCount = totalErrorCount + sampleTest.run();
        System.out.println("TestHarness encountered: " + totalErrorCount +
                " total errors.");    
    }
}
