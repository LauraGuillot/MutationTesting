/**
 * Class Project.java
 * -----------------------------------------------------------------------------
 * Author : Laura
 * Last modification : 10/08/2016
 * -----------------------------------------------------------------------------
 * A project is composed of a name and the names of his packages
 */
package unittestrunner;

import java.util.ArrayList;
import util.Pack;

/**
 * Class project. Representation of a project.
 *
 * @author Laura
 */
public class Project {

    /**
     * Name of the project
     */
    private String name;

    /**
     * List of src packages
     */
    private ArrayList<Pack> srcPacks;

    /**
     * List of test packages
     */
    private ArrayList<Pack> testPacks;

    /**
     * Package of the test class
     */
    private ArrayList<String> testPackage;

    /*GETTERS AND SETTERS*/
    /**
     * Get the project name
     *
     * @return Project name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the project name
     *
     * @param name Project name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the source packages
     *
     * @return Source packages list
     */
    public ArrayList<Pack> getSrcPacks() {
        return srcPacks;
    }

    /**
     * Set the source packages
     *
     * @param srcPacks Source packages list
     */
    public void setSrcPacks(ArrayList<Pack> srcPacks) {
        this.srcPacks = srcPacks;
    }

    /**
     * Get the test packages
     *
     * @return Test packages list
     */
    public ArrayList<Pack> getTestPacks() {
        return testPacks;
    }

    /**
     * Set the test packages
     *
     * @param testPacks Test packages list
     */
    public void setTestPacks(ArrayList<Pack> testPacks) {
        this.testPacks = testPacks;
    }

    /**
     * Get the list of path to the test class. For example, if the test class is
     * in test/com/packages1, it will return the list : [test,com,package1]
     *
     * @return Path to the test class
     */
    public ArrayList<String> getTestPackage() {
        return testPackage;
    }

    /**
     * Set the test class path
     *
     * @param testPackage Path
     */
    public void setTestPackage(ArrayList<String> testPackage) {
        this.testPackage = testPackage;
    }

    /**
     * Constructor
     *
     * @param name Name of the project
     * @param srcpacks Source packages
     * @param testpacks Test Packages
     * @param testPackage Name of the test package
     */
    public Project(String name, ArrayList<Pack> srcpacks, ArrayList<Pack> testpacks, ArrayList<String> testPackage) {
        this.name = name;
        this.srcPacks = srcpacks;
        this.testPacks = testpacks;
        this.testPackage = testPackage;
    }

    /**
     * Get the path of the test class
     *
     * @return Path
     */
    public String getTestPath() {
        String tp = "";
        for (String n : this.getTestPackage()) {
            tp = tp + n + "/";
        }
        return tp;
    }

    /**
     * Get the list of all the packages paths
     *
     * @param pk List of packages
     * @return List of path
     */
    public ArrayList<String> getListPackages(ArrayList<Pack> pk) {
        ArrayList<String> paths = new ArrayList<String>();
        for (Pack p : pk) {
            ArrayList<String> l = f(p, "");
            for (String s : l) {
                paths.add(s);
            }
        }
        return paths;
    }

    /**
     * Given a package and the path to this package, get the path of all the
     * sub-packages
     *
     * @param p Package
     * @param prec Path to this package
     * @return
     */
    public ArrayList<String> f(Pack p, String prec) {
        ArrayList<String> paths = new ArrayList<String>();

        if (p.getPacks() != null) {
            for (Pack pa : p.getPacks()) {
                ArrayList<String> l = f(pa, prec + "/" + p.getName());
                for (String s : l) {
                    paths.add(s);
                }
            }
        } else if (p.getClasses() != null) {
            String s = prec + "/" + p.getName() + "/*.java";
            paths.add(s);
        }
        return paths;
    }

}
