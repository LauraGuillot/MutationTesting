/**
 * Class TreeBuilder.java
 * -----------------------------------------------------------------------------
 * Author : Laura
 * Last modification : 10/08/2016
 * -----------------------------------------------------------------------------
 * Given a project, extract the arborescence of the project with the different
 * packages and classes.
 */
package builder;

import java.io.File;
import java.util.ArrayList;
import util.Cl;
import util.Pack;

/**
 * Class TreeBuilder. Arborescence of a project
 * @author Laura
 */
public class TreeBuilder {

    /**
     * Project Name
     */
    private String projectName;

    /**
     * Location of the application
     */
    private String origin;

    /* GETTERS AND SETTERS */
    
    /**
     * Get the project Name
     * @return Project Name
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * Set the project Name
     * @param projectName Projet Name
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * Get the original Path
     * @return path
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * Set the original path
     * @param origin Original path
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    /**
     * Constructor
     *
     * @param projectName Project Name
     * @param origin Location of the application
     */
    public TreeBuilder(String projectName, String origin) {
        this.projectName = projectName;
        this.origin = origin;
    }

    /**
     * Given a path, get the files and the folders in the current folder
     *
     * @param path Path
     * @param dir List of directories
     * @param l List of classes
     */
    public void getSub(String path, ArrayList<String> dir, ArrayList<String> l) {
        File file = new File(path);
        File[] files = file.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    dir.add(f.getName());
                } else {
                    String s = f.getName();
                    if(s.substring(s.length()-4,s.length()).equals("java")){
                    l.add(f.getName());}
                }
            }
        }
    }

    /**
     * Given the name of a folder and the path to it, list the classes and the
     * subfolders in it and create a Pack entity.
     *
     * @param name Folder Name
     * @param prec Path to the folder
     * @return Package
     */
    public Pack constructPack(String name, String prec) {

        ArrayList<String> dir = new ArrayList<String>();
        ArrayList<String> files = new ArrayList<String>();
        this.getSub(prec + "/" + name, dir, files);

        //Packages list
        ArrayList<Pack> packs;
        if (dir.isEmpty()) {
            packs = null;
        } else {
            packs = new ArrayList<Pack>();
            for (String d : dir) {
                Pack p = this.constructPack(d, prec + "/" + name);
                packs.add(p);
            }
        }

        //Classes list
        ArrayList<Cl> cl;
        if (files.isEmpty()) {
            cl = null;
        } else {
            cl = new ArrayList<Cl>();
            for (String f : files) {
                String s = prec + "/" + name;
                String pref = origin + "/projects/";
                s = s.substring(pref.length() - 1, s.length());

                Cl c = new Cl(f, Cl.readCode(prec + "/" + name + "/" + f), s);
                cl.add(c);
            }
        }

        return new Pack(packs, cl, name);
    }

    /**
     * Get the sources code packages
     *
     * @return List of source code packages
     */
    public ArrayList<Pack> getSrc() {
        Pack p = this.constructPack("src", this.origin + "projects/" + this.projectName);
        return p.getPacks();
    }

    /**
     * Get the test packages
     *
     * @return List of test packages
     */
    public ArrayList<Pack> getTests() {
        Pack p = this.constructPack("test", this.origin + "projects/" + this.projectName);
        return p.getPacks();
    }

    /**
     * Get all the classes of the project
     *
     * @param p Package
     * @return List of classes
     */
    public ArrayList<Cl> getClasses(Pack p) {
        ArrayList<Cl> cl = new ArrayList<Cl>();

        if (p.getPacks() != null) {
            for (Pack pa : p.getPacks()) {
                ArrayList<Cl> c = getClasses(pa);
                if (c != null) {
                    for (Cl cla : c) {
                        cl.add(cla);
                    }
                }
            }
        }

        if (p.getClasses() != null) {
            for (Cl cla : p.getClasses()) {
                cl.add(cla);
            }
        }

        return cl;
    }

    /**
     * Given a list of classes, find the path of one class
     *
     * @param n Class name
     * @param cl List of Classes
     * @return Path to the given class
     */
    public String findClassPack(String n, ArrayList<Cl> cl) {
        Cl c = null;
        for (Cl cla : cl) {
            if (cla.getName().equals(n)) {
                c = cla;
            }
        }
        return c.getPath();
    }

}
