/**
 * Class Compiler.java
 * -----------------------------------------------------------------------------
 * Author : Laura
 * Last modification : 10/08/2016
 * -----------------------------------------------------------------------------
 * The goal of this class is to automatically run a unit test on a project and retrieve the output.
 */
package unittestrunner;

import java.io.IOException;
import java.io.*;
import java.util.ArrayList;

/**
 * Class compiler. Code compilation.
 *
 * @author Laura
 */
public class Compiler {

    /**
     * Project to test (This object contains the name of the project and the
     * names of the packages)
     */
    private Project p;

    /**
     * Is it a mutant or the original project?
     *
     */
    private String dir;

    /**
     * Name of the test to run
     */
    private String testName;

    /*
     * GETTERS AND SETTERS
     */
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
     * Get the test name
     *
     * @return Test name
     */
    public String getTestName() {
        return testName;
    }

    /**
     * Set the test name
     *
     * @param testName Test name
     */
    public void setTestName(String testName) {
        this.testName = testName;
    }

    /**
     * Get the type of the project "Mutant" or "Base" (for the original program)
     *
     * @return Type of the project
     */
    public String getDir() {
        return dir;
    }

    /**
     * Set the type of the project "Mutant" or "Base" (for the original program)
     *
     * @param dir Type of the project
     */
    public void setDir(String dir) {
        this.dir = dir;
    }

    /**
     * Constructor
     *
     * @param p Project
     * @param pathUnitTest Name of the test class
     * @param dir Type of the project : "Mutant" or "Base" (for the original
     * program)
     */
    public Compiler(Project p, String pathUnitTest, String dir) {
        this.p = p;
        this.testName = pathUnitTest;
        this.dir = dir;
    }

    /**
     * Execution of the test class and saving of the output
     *
     * @param loc Location of the application
     * @param name Name of the user
     * @return List of the output lines
     * @throws IOException
     */
    public ArrayList<String> compileNewTest(String loc, String name) throws IOException {

        ArrayList<String> a = new ArrayList<String>();

        try {

            //Path to the test path
            String tpath = loc + "data/" + name + "/" + p.getName() + "/" + this.dir + "/new/test/" + p.getTestPath() + this.testName;
            // Path to the source code
            String srcDir = loc + "data/" + name + "/" + p.getName() + "/" + this.dir + "/new/src/";

            //Test class compilation
            //Command : javac -cp [junit.jar];[Path to the source code] [testClass]
            String command = "javac -classpath " + loc + "lib/junit.jar;" + srcDir + " " + tpath;

            Runtime rt2 = Runtime.getRuntime();
            Process p2 = rt2.exec(command);

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p2.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(p2.getErrorStream()));

            // read the output from the command
            String l2;
            while ((l2 = stdInput.readLine()) != null) {
                System.out.println(l2);
            }

            // read any errors from the attempted command
            while ((l2 = stdError.readLine()) != null) {
                System.out.println(l2);
                a.add(l2);
            }
            p2.waitFor();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return a;
    }

    /**
     * Compile the original source code (it is useful to run the code coverage)
     *
     * @param loc Location of the application
     * @param name Name of the user
     * @throws IOException
     */
    public void compileSrc(String loc, String name) throws IOException {
        try {

            /*COMPILATION OF THE NEW SRC DIRECTORY*/
            String path = loc + "data/" + name + "/" + p.getName() + "/" + this.dir + "/new/src/";
            ArrayList<String> lp = this.p.getListPackages(p.getSrcPacks());

            for (String s : lp) {
                String src = path + s;
                String command = "javac " + src;
                Runtime rt = Runtime.getRuntime();
                Process pr = rt.exec(command);
                pr.waitFor();

                BufferedReader stdInput = new BufferedReader(new InputStreamReader(pr.getInputStream()));
                BufferedReader stdError = new BufferedReader(new InputStreamReader(pr.getErrorStream()));

                // read the output from the command
                String l;
                while ((l = stdInput.readLine()) != null) {
                    // System.out.println(s);
                }

                // read any errors from the attempted command
                while ((l = stdError.readLine()) != null) {
                    // System.out.println(s);

                }
            }

            /*COMPILATION OF THE OLD SRC DIRECTORY*/
            String path1 = loc + "data/" + name + "/" + p.getName() + "/Base/old/src/";
            ArrayList<String> lp1 = this.p.getListPackages(p.getSrcPacks());

            for (String s : lp1) {
                String src = path1 + s;
                String command = "javac " + src;
                Runtime rt = Runtime.getRuntime();
                rt.exec(command);
            }

            /*COMPILATION OF THE TESTS DIRECTORIES*/
            String tpath = loc + "data/" + name + "/" + p.getName() + "/Base/old/test/";

            ArrayList<String> lt = this.p.getListPackages(p.getTestPacks());

            for (String s : lt) {
                String a = tpath + s.substring(1, s.length());
                String srcDir = loc + "data/" + name + "/" + p.getName() + "/Base/old/src/";
                String command2 = "javac -cp " + loc + "lib/junit.jar;" + srcDir + " " + a;
                Runtime rt2 = Runtime.getRuntime();
                rt2.exec(command2);
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }
}
