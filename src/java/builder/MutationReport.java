/**
 * Class MutationReport.java
 * -----------------------------------------------------------------------------
 * Author : Laura
 * Last modification : 10/08/2016
 * -----------------------------------------------------------------------------
 * Reading and saving the useful data about the done mutation
 */
package builder;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Class mutation report. Representation of the mutation data
 *
 * @author Laura
 */
public class MutationReport {

    /**
     * Mutated line
     */
    private String line;

    /**
     * Mutated class
     */
    private String mutatedClass;
    /**
     * Unit test class covering the mutated class
     */
    private String testName;

    /**
     * Project name
     */
    private String projectName;

    /**
     * Constructor
     *
     * @param n Project Name
     */
    public MutationReport(String n) {
        this.projectName = n;
    }

    /*GETTERS AND SETTERS*/
    /**
     * Get the mutation line
     *
     * @return Mutation line
     */
    public String getLine() {
        return line;
    }

    /**
     * Set the mutation line
     *
     * @param line Mutation line
     */
    public void setLine(String line) {
        this.line = line;
    }

    /**
     * Get the mutated class name
     *
     * @return Mutated Class Name
     */
    public String getMutatedClass() {
        return mutatedClass;
    }

    /**
     * Set the mutated Class name
     *
     * @param mutatedClass mutated class name
     */
    public void setMutatedClass(String mutatedClass) {
        this.mutatedClass = mutatedClass;
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
     * Get the project name
     *
     * @return Project name
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * Set the project name
     *
     * @param projectName Project name
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * The pieces of information about the mutation are written in a file called
     * MutationReport. This file is located in
     * projects/ProjectName/mutant/MutationReport. It is composed of 3 lines :
     * 1)The mutated class 2) the test name 3) the mutated line
     * @param loc Location of the project
     */
    public void read(String loc) {
        try {
            String s = loc + "projects/" + this.projectName + "/mutant/MutationReport";

            InputStream ips = new FileInputStream(s);
            InputStreamReader ipsr = new InputStreamReader(ips);
            BufferedReader br = new BufferedReader(ipsr);
            this.mutatedClass = br.readLine();
            this.testName = br.readLine();
            this.line = br.readLine();

            br.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

}
