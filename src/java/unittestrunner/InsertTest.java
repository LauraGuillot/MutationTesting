/**
 * Class InsertTest.java
 * -----------------------------------------------------------------------------
 * Author : Laura
 * Last modification : 10/08/2016
 * -----------------------------------------------------------------------------
 * This class is used to add a test method to a test class
 */
package unittestrunner;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class InserTest. Insertion of a unit test in a class
 *
 * @author Laura
 */
public class InsertTest {

    /**
     * Project (This object contains the name of the project and the names of
     * the packages)
     */
    private Project p;

    /**
     * Code of the new test
     */
    private String newTest;

    /**
     * Name of the test class
     */
    private String testClassName;

    /**
     * Constructor
     *
     * @param p Project
     * @param newTest Test Code
     * @param pathUnitTest Test class name
     */
    public InsertTest(Project p, String newTest, String pathUnitTest) {
        this.p = p;
        this.newTest = newTest;
        this.testClassName = pathUnitTest;
    }

    /*GETTERS AND SETTERS*/
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
     * Get the new test code
     *
     * @return Test code
     */
    public String getNewTest() {
        return newTest;
    }

    /**
     * Set the new test code
     *
     * @param newTest New test code
     */
    public void setNewTest(String newTest) {
        this.newTest = newTest;
    }

    /**
     * Get the test class name
     *
     * @return Test class name
     */
    public String getTestClassName() {
        return testClassName;
    }

    /**
     * Set the test class name
     *
     * @param testClassName Test class name
     */
    public void setTestClassName(String testClassName) {
        this.testClassName = testClassName;
    }

    /**
     * Read and save a java file in a list
     *
     * @param loc Location of the application
     * @param name User name
     * @return List of the file lines
     */
    public ArrayList<String> readClass(String loc, String name) {
        ArrayList<String> l = new ArrayList<String>();
        try {
            String tp = p.getTestPath();

            InputStream ips = new FileInputStream(loc + "data" + "/" + name + "/" + p.getName() + "/" + "Base" + "/old/test/" + tp + this.testClassName);

            InputStreamReader ipsr = new InputStreamReader(ips);
            BufferedReader br = new BufferedReader(ipsr);
            String li = br.readLine();
            while (li != null) {
                l.add(li);
                li = br.readLine();
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return l;
    }

    /**
     * Add a new unit test to a java file
     *
     * @param l list of the file lines
     * @return new list of the file lines
     */
    public ArrayList<String> addNewTest(ArrayList<String> l) {
        l.set(l.size() - 1, newTest);
        l.add("}");
        return l;
    }

    /**
     * Save the new unit tests file
     *
     * @param l list of the lines of the file
     * @param loc Location of the application
     * @param name User name
     */
    public void write(ArrayList<String> l, String loc, String name) {
        String tp = p.getTestPath();
        String path1 = loc + "data" + "/" + name + "/" + p.getName() + "/" + "Base" + "/new/test/" + tp + this.testClassName;
        String path2 = loc + "data" + "/" + name + "/" + p.getName() + "/" + "Mutant" + "/new/test/" + tp + this.testClassName;
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path1));
            for (String s : l) {
                writer.write(s);
                writer.newLine();
            }
            writer.close();

            BufferedWriter writer1 = new BufferedWriter(new FileWriter(path2));
            for (String s : l) {
                writer1.write(s);
                writer1.newLine();
            }
            writer1.close();

        } catch (IOException ex) {
            Logger.getLogger("").log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Global method to add a new unit test
     *
     * @param loc Location of the application
     * @param name User name
     */
    public void main(String loc, String name) {
        ArrayList<String> l = this.readClass(loc, name);
        l = this.addNewTest(l);
        this.write(l, loc, name);
    }
}
