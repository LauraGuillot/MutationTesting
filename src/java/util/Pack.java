/**
 * Class Pack.java
 * -----------------------------------------------------------------------------
 * Author : Laura
 * Last modification : 17/08/2016
 * -----------------------------------------------------------------------------
 * Pack id the representation of a java Package
 */
package util;

import java.util.ArrayList;

/**
 * Class Pack. Representation of a java package
 *
 * @author Laura
 */
public class Pack {

    /**
     * List of directories in the package
     */
    private ArrayList<Pack> packs;

    /**
     * List of classes in the package
     */
    private ArrayList<Cl> classes;

    /**
     * Package name
     */
    private String name;

    /*GETTERS AND SETTERS*/
    /**
     * Get the sub-directories
     *
     * @return Sub-directories list
     */
    public ArrayList<Pack> getPacks() {
        return packs;
    }

    /**
     * Set the sub-directories
     *
     * @param packs Sub-directories list
     */
    public void setPacks(ArrayList<Pack> packs) {
        this.packs = packs;
    }

    /**
     * Get the classes
     *
     * @return List of classes
     */
    public ArrayList<Cl> getClasses() {
        return classes;
    }

    /**
     * Set the classes
     *
     * @param classes List of classes
     */
    public void setClasses(ArrayList<Cl> classes) {
        this.classes = classes;
    }

    /**
     * Get the package name
     *
     * @return Package name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the package name
     *
     * @param name Package name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Constructor
     *
     * @param packs List of the directories in the package
     * @param classes List of the classes in the package
     * @param name Package name
     */
    public Pack(ArrayList<Pack> packs, ArrayList<Cl> classes, String name) {
        this.packs = packs;
        this.classes = classes;
        this.name = name;
    }
}
