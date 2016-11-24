/**
 * Class Cl.java
 * -----------------------------------------------------------------------------
 * Author : Laura
 * Last modification : 17/08/2016
 * -----------------------------------------------------------------------------
 * Cl is the representation of a java class
 */
package util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Class Cl. Representation of a java class
 *
 * @author Laura
 */
public class Cl {

    /**
     * Name of the class
     */
    private String name;

    /**
     * List of the code lines
     */
    private ArrayList<String> code;

    /**
     * Path of the class
     */
    private String path;

    /**
     * Get the class name
     *
     * @return class name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the class name
     *
     * @param name Class name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the code
     *
     * @return Code lines
     */
    public ArrayList<String> getCode() {
        return code;
    }

    /**
     * Set the code
     *
     * @param code Code lines
     */
    public void setCode(ArrayList<String> code) {
        this.code = code;
    }

    /**
     * Get the path
     *
     * @return Path
     */
    public String getPath() {
        return path;
    }

    /**
     * Set the path
     *
     * @param path Path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Constructor
     *
     * @param name Name of the class
     * @param code Code lines
     * @param path Path to this class
     */
    public Cl(String name, ArrayList<String> code, String path) {
        this.name = name;
        this.code = code;
        this.path = path;
    }

    /**
     * Read the code form the file
     *
     * @param path Path to the class file
     * @return Code lines
     */
    public static ArrayList<String> readCode(String path) {
        ArrayList<String> l = new ArrayList<String>();
        try {
            InputStream ips = new FileInputStream(path);
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
}
