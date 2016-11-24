/**
 * Class Executor.java
 * -----------------------------------------------------------------------------
 * Author : Laura
 * Last modification : 10/08/2016
 * -----------------------------------------------------------------------------
 * Class for executing a unit test class
 */
package unittestrunner;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Class executor. Test running.
 *
 * @author Laura
 */
public class Executor {

    /**
     * Project
     */
    private Project p;
    /**
     * Name of a test class
     */
    private String testName;
    /**
     * Is it a mutant or the original project?
     */
    private String dir;

    /**
     * Constructor
     *
     * @param p Project
     * @param pathUnitTest Test class
     * @param dir Type of the project : "Mutant" or "Base" (for the original
     * program)
     */
    public Executor(Project p, String pathUnitTest, String dir) {
        this.p = p;
        this.testName = pathUnitTest;
        this.dir = dir;
    }

    /*SETTERS AND GETTERS*/
    /**
     * Get the project
     *
     * @return Project
     */
    public Project getP() {
        return p;
    }

    /**
     * Set the project
     *
     * @param p Project
     */
    public void setP(Project p) {
        this.p = p;
    }

    /**
     * Get the test class name
     *
     * @return Test class name
     */
    public String getTestName() {
        return testName;
    }

    /**
     * Set the test class name
     *
     * @param testName Test class name
     */
    public void setTestName(String testName) {
        this.testName = testName;
    }

    /**
     * Get the type of the project : "Mutant" or "Base" (for the original program)
     *
     * @return Type of the project
     */
    public String getDir() {
        return dir;
    }

    /**
     * Set the type of the project : "Mutant" or "Base" (for the original
     * program)
     *
     * @param dir Type of the project
     */
    public void setDir(String dir) {
        this.dir = dir;
    }

    /**
     * Execution of a unit tests class
     *
     * @param loc Location of the application
     * @param name User name
     * @return String "fail" (if the test doesn't pass) or "success" (if the
     * test passes)
     */
    public String execute(String loc, String name) {
        String r;

        ArrayList<String> a = new ArrayList<String>();

        try {

            //Build the test path
            String tp = "";
            for (String n : p.getTestPackage()) {
                tp = tp + n + ".";
            }

            String path = loc + "data/" + name + "/" + p.getName() + "/" + this.dir + "/new/";
            String src = path + "src/";
            String test = path + "test/";
            String file = tp + this.testName.substring(0, this.testName.length() - 5);

            //Execution
            // Command : java -cp [junit.jar][hamcrest.jar][Path to the src code][Path to the tests] org.junit.runner.JUnitCode [Test Class]
            String command = "java -cp " + loc + "lib/junit.jar;" + loc + "lib/hamcrest.jar;" + src + ";" + test + " org.junit.runner.JUnitCore " + file;

            Runtime rt = Runtime.getRuntime();
            Process pr = rt.exec(command);

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            //  BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

            // read the output from the command
            String s;
            while ((s = stdInput.readLine()) != null) {
                //System.out.println(s);
                a.add(s);
            }

            /*// read any errors from the attempted command
            while ((s = stdError.readLine()) != null) {
                // System.out.println(s);
            }*/
            pr.waitFor();
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
        r = a.get(a.size() - 3);

        if (r.equalsIgnoreCase("FAILURES!!!")) {
            r = "fail";
        } else {
            r = "success";
        }
        return r;
    }
}
