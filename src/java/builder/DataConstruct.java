/**
 * Class DataConstruct.java
 * -----------------------------------------------------------------------------
 * Author : Laura
 * Last modification : 10/08/2016
 * -----------------------------------------------------------------------------
 * Given a project and its architecture, this class builds all the directories
 * needed by the application
 */
package builder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import util.Cl;
import util.Pack;

/**
 * Class DataConstruct. Building of directories
 *
 * @author Laura
 */
public class DataConstruct {

    /**
     * Project Name
     */
    private String projectName;
    /**
     * Path to the project
     */
    private String origin;
    /**
     * List of the classes
     */
    private ArrayList<Cl> classes;
    /**
     * List of the source code packages
     */
    private ArrayList<Pack> srcPack;
    /**
     * List of the test packages
     */
    private ArrayList<Pack> testPack;

    /**
     * Name of the user
     */
    private String name;

    /*GETTERS AND SETTERS*/
    /**
     * Get the project Name
     *
     * @return Project Name
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * Set the project Name
     *
     * @param projectName Project Name
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * Get the original path
     * @return Path
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * Set the original path
     * @param origin Path
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    /**
     * Get the classes
     * @return List of Classes
     */
    public ArrayList<Cl> getClasses() {
        return classes;
    }

    /**
     * Set the classes
     * @param classes List of classes
     */
    public void setClasses(ArrayList<Cl> classes) {
        this.classes = classes;
    }

    /**
     * Get the source packages
     * @return List of packages
     */
    public ArrayList<Pack> getSrcPack() {
        return srcPack;
    }

    /**
     * Set the source package
     * @param srcPack List of packages
     */
    public void setSrcPack(ArrayList<Pack> srcPack) {
        this.srcPack = srcPack;
    }

    /**
     * Get the test packages
     * @return List of packages
     */
    public ArrayList<Pack> getTestPack() {
        return testPack;
    }

    /**
     * Set the test packages
     * @param testPack List of packages
     */
    public void setTestPack(ArrayList<Pack> testPack) {
        this.testPack = testPack;
    }

    /**
     * Get the user name
     * @return User name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the user name
     * @param name User name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Constructor
     *
     * @param projectName Project name
     * @param classes List of classes
     * @param srcPack List of source code packages
     * @param testPack List of test packages
     * @param origin Location of the project
     * @param name Name of the user
     */
    public DataConstruct(String projectName, ArrayList<Cl> classes, ArrayList<Pack> srcPack, ArrayList<Pack> testPack, String origin, String name) {
        this.projectName = projectName;
        this.origin = origin;
        this.classes = classes;
        this.srcPack = srcPack;
        this.testPack = testPack;
        this.name = name;
    }

    /**
     * Creation of the arborescence needed by this application (= Building of
     * the data directory)
     *
     * @throws IOException
     */
    public void createDir() throws IOException {

        //Creation of a directory for the project
        new File(this.origin + "data/" + this.name + "/" + this.projectName).mkdir();

        //Creation of two subdirectories : Base, for the original source code and
        //Mutant, for the mutated code
        new File(this.origin + "data/" + this.name + "/" + this.projectName + "/Base").mkdir();
        new File(this.origin + "data/" + this.name + "/" + this.projectName + "/Mutant").mkdir();

        //In each subdirectories, creation of two directories:
        // old : for the original code
        // new : for the code with the unit test written by the user
        new File(this.origin + "data/" + this.name + "/" + this.projectName + "/Base/old").mkdir();
        new File(this.origin + "data/" + this.name + "/" + this.projectName + "/Mutant/old").mkdir();
        new File(this.origin + "data/" + this.name + "/" + this.projectName + "/Base/new").mkdir();
        new File(this.origin + "data/" + this.name + "/" + this.projectName + "/Mutant/new").mkdir();

        //In each subdirectories, creation of two folder src and test
        new File(this.origin + "data/" + this.name + "/" + this.projectName + "/Base/old/src").mkdir();
        new File(this.origin + "data/" + this.name + "/" + this.projectName + "/Mutant/old/src").mkdir();
        new File(this.origin + "data/" + this.name + "/" + this.projectName + "/Base/new/src").mkdir();
        new File(this.origin + "data/" + this.name + "/" + this.projectName + "/Mutant/new/src").mkdir();
        new File(this.origin + "data/" + this.name + "/" + this.projectName + "/Base/old/test").mkdir();
        new File(this.origin + "data/" + this.name + "/" + this.projectName + "/Mutant/old/test").mkdir();
        new File(this.origin + "data/" + this.name + "/" + this.projectName + "/Base/new/test").mkdir();
        new File(this.origin + "data/" + this.name + "/" + this.projectName + "/Mutant/new/test").mkdir();

        //Creation of a folder for each source code package
        //(in each "src" directory)
        for (Pack p : this.srcPack) {
            this.createPack(p, this.origin + "data/" + this.name + "/" + this.projectName + "/Base/old/src");
            this.createPack(p, this.origin + "data/" + this.name + "/" + this.projectName + "/Mutant/old/src");
            this.createPack(p, this.origin + "data/" + this.name + "/" + this.projectName + "/Base/new/src");
            this.createPack(p, this.origin + "data/" + this.name + "/" + this.projectName + "/Mutant/new/src");
        }

        //Creation of a folder for each test package
        //(in each "test" directory)
        for (Pack p : this.testPack) {
            this.createPack(p, this.origin + "data/" + this.name + "/" + this.projectName + "/Base/old/test");
            this.createPack(p, this.origin + "data/" + this.name + "/" + this.projectName + "/Mutant/old/test");
            this.createPack(p, this.origin + "data/" + this.name + "/" + this.projectName + "/Base/new/test");
            this.createPack(p, this.origin + "data/" + this.name + "/" + this.projectName + "/Mutant/new/test");
        }

        // Copy/paste of each class in the right package
        for (Cl c : classes) {
            String s = c.getPath();
            s = s.substring(this.projectName.length() + 1, s.length());

            /*.JAVA*/
            String src = this.origin + "projects/" + this.projectName + "/" + s + "/" + c.getName();
            String dest = this.origin + "data/" + this.name + "/" + this.projectName + "/Base/old/" + s + "/" + c.getName();
            copyPaste(src, dest);

            dest = this.origin + "data/" + this.name + "/" + this.projectName + "/Base/new/" + s + "/" + c.getName();
            copyPaste(src, dest);

            dest = this.origin + "data/" + this.name + "/" + this.projectName + "/Mutant/old/" + s + "/" + c.getName();
            copyPaste(src, dest);

            dest = this.origin + "data/" + this.name + "/" + this.projectName + "/Mutant/new/" + s + "/" + c.getName();
            copyPaste(src, dest);
            
            /*.CLASS*/
           /* src = this.origin + "projects/" + this.projectName + "/" + s + "/" + c.getName().substring(0,c.getName().length()-4)+"class";
            dest = this.origin + "data/" + this.name + "/" + this.projectName + "/Base/old/" + s + "/" + c.getName().substring(0,c.getName().length()-4)+"class";
            copyPaste(src, dest);

            dest = this.origin + "data/" + this.name + "/" + this.projectName + "/Base/new/" + s + "/" + c.getName().substring(0,c.getName().length()-4)+"class";
            copyPaste(src, dest);

            dest = this.origin + "data/" + this.name + "/" + this.projectName + "/Mutant/old/" + s + "/" + c.getName().substring(0,c.getName().length()-4)+"class";
            copyPaste(src, dest);

            dest = this.origin + "data/" + this.name + "/" + this.projectName + "/Mutant/new/" + s + "/" + c.getName().substring(0,c.getName().length()-4)+"class";
            copyPaste(src, dest);*/
        }

    }

    /**
     * Copy and paste a directory into another directory
     *
     * @param dir1 Directory to copy
     * @param dir2 Directory to paste
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void copyPaste(String dir1, String dir2) throws FileNotFoundException, IOException {
        InputStream input = new FileInputStream(dir1);
        OutputStream output = new FileOutputStream(dir2);
        IOUtils.copy(input, output);
    }

    /**
     * Given a list of packages and the current path, creation of a folder for
     * each package
     *
     * @param p List of packages
     * @param path Current path
     */
    public void createPack(Pack p, String path) {
        new File(path + "/" + p.getName()).mkdir();
        if (p.getPacks() != null) {
            for (Pack pa : p.getPacks()) {
                createPack(pa, path + "/" + p.getName()); //Recursion : creation of the sub-directories of each package
            }
        }
    }
}
