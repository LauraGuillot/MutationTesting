/**
 * Class Coverage.java
 * -----------------------------------------------------------------------------
 * Author : Laura
 * Last modification : 17/08/2016
 * -----------------------------------------------------------------------------
 * The goal of this class is to automatically compute the code coverage of a unit
 * tests suite on a project
 */
package builder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import unittestrunner.Project;
import util.Cl;
import util.Pack;

/**
 * Class Coverage. Computation of a code coverage
 * @author Laura
 */
public class Coverage {

    /**
     * Computation of the code coverage using Cobertura
     *
     * @param p Project
     * @param loc Location of the application
     * @param name User name
     * @throws java.lang.InterruptedException
     */
    public static void cover(Project p, String loc, String name) throws InterruptedException {

        //Delete the previous report
       // Coverage.deleteReport(p, loc,name);
       
        //Path to the source code
        String path = loc + "projects/" + p.getName() +"/";
        //Path where the report would be saved
        String dest = path + "report";

        //Command 1 : instrumentation of the java class
        // [Directory of the cobertura-instrument.bat] [Path where the instrumentalized classed would be saved] [Path to the source code]
        String cmd1 = loc + "lib/cobertura/cobertura-instrument.bat --destination" + " " + path + "inst " + path + "old/*";

        //Command 2 : Computation of the code coverage by running the tests
        // java -cp [Directory of cobertura.jar][Directory of coberturaa/lib][Directory of junit.jar][Directory of hamcrest.jar] [Path of the instrumentalized classes] [Path to the source code] -Dnet.sourceforge.cobertura.datafile=[path to the cobertura.ser file] org.junit.runner.JUnitCore
        String cmd2 = "java -cp " + loc + "lib/cobertura/cobertura-2.1.1.jar;" + loc + "lib/cobertura/lib/*;" + loc + "lib/junit.jar;" + loc + "lib/hamcrest.jar;" + path + "inst/;" + path + "old/; " + "-Dnet.sourceforge.cobertura.datafile=" + path + "cobertura.ser org.junit.runner.JUnitCore ";

        //Command 3 : Report writting
        // [Directory of the cobertura-report.bat] --for;at html --datafile [Path to the cobertura.ser file] --destination [destination of the report] [path to the src classes] 
        String cmd3 = loc + "lib/cobertura/cobertura-report.bat --format html --datafile " + path + "cobertura.ser --destination " + dest + " " + path + "src";

        try {

            //**** COMMAND 1 ****
            Runtime rt1 = Runtime.getRuntime();
            Process p1 = rt1.exec(cmd1);
            p1.waitFor();

            /* BufferedReader stdInput = new BufferedReader(new InputStreamReader(p1.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(p1.getErrorStream()));

            // read the output from the command
            String l2;
            while ((l2 = stdInput.readLine()) != null) {
                System.out.println(l2);
            }

            // read any errors from the attempted command
            while ((l2 = stdError.readLine()) != null) {
                System.out.println(l2);

            }*/
            //**** COMMAND 2 ****
            ArrayList<String> tests = Coverage.listTests(p);

            for (String s : tests) {
                Runtime rt2 = Runtime.getRuntime();
                Process p2 = rt2.exec(cmd2 + s);
                p2.waitFor();

                /*  BufferedReader stdInput = new BufferedReader(new InputStreamReader(p2.getInputStream()));
                BufferedReader stdError = new BufferedReader(new InputStreamReader(p2.getErrorStream()));

            // read the output from the command
            String l2;
            while ((l2 = stdInput.readLine()) != null) {
                System.out.println(l2);
            }

            // read any errors from the attempted command
            while ((l2 = stdError.readLine()) != null) {
                System.out.println(l2);

            }*/
            }

            //**** COMMAND 3 ****
            Runtime rt3 = Runtime.getRuntime();
            Process p3 = rt3.exec(cmd3);
            p3.waitFor();

        } catch (Exception e) {
            System.out.println(e.toString());
        }
       
    }

    /**
     * Method to get the list of the tests paths
     *
     * @param p Project
     * @return List of String, each String is a path to a test class
     */
    public static ArrayList<String> listTests(Project p) {
        ArrayList<String> l = f(p.getTestPacks(), "");
        return l;
    }

    /**
     * Recursive method which, given a list of packages and the path to each of
     * this package, returns a list of test classes paths
     *
     * @param l List of packages
     * @param prec Path of the current directory
     * @return List of String, each String is a path to a test class
     */
    public static ArrayList<String> f(ArrayList<Pack> l, String prec) {
        ArrayList<String> a = new ArrayList<String>();
        if (l != null) {
            for (Pack p : l) {
                ArrayList<String> aa = f(p.getPacks(), prec + "." + p.getName() + ".");
                for (String s : aa) {
                    a.add(s);
                }
                for (Cl cl : p.getClasses()) {
                    String n = prec + p.getName() + "." + cl.getName().substring(0, cl.getName().length() - 5);
                    a.add(n);
                }
            }
        }
        return a;
    }

    /**
     * Get the cover report in html format in an array
     * @param projectName Project name
     * @param loc Location of the project
     * @return List of the report lines
     */
    public static ArrayList<String> getReport(String projectName, String loc) {
        ArrayList<String> l = new ArrayList<String>();
        try {

            //Mutation information
            MutationReport mr = new MutationReport(projectName);
            mr.read(loc);
            String mutatedClass = mr.getMutatedClass();
            mutatedClass = mutatedClass.substring(0, mutatedClass.length() - 5);

            TreeBuilder t = new TreeBuilder(projectName, loc);

            //Packages
            ArrayList<Pack> src = t.getSrc(); //Packages of source code
            ArrayList<Pack> te = t.getTests(); // Packages of unit tests
            ArrayList<Pack> packs = new ArrayList<Pack>(); //All the packages
            for (Pack pa : src) {
                packs.add(pa);
            }
            for (Pack pa : te) {
                packs.add(pa);
            }
            Pack project = new Pack(packs, null, projectName);

            //Class of the project
            ArrayList<Cl> classes = t.getClasses(project);

            //MutatedClass path
            String s = t.findClassPack(mr.getMutatedClass(), classes);
            s = s.substring(projectName.length() + 5, s.length());//Delete the prefix "projectName/src/"
            s = s.replace("/", ".");

            String path = loc + "projects/" +projectName + "/report/" + s + "." + mutatedClass + ".html";

            InputStream ips = new FileInputStream(path);

            InputStreamReader ipsr = new InputStreamReader(ips);
            BufferedReader br = new BufferedReader(ipsr);
            String li = br.readLine();
            while (li != null && !li.equals("<body>")) {
                li = br.readLine();
            }
            while (li != null && !li.equals("</body>")) {
                li = br.readLine();
                l.add(process(li));

            }
            br.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return l;
    }

    /**
     * The goal of this method is to remove the links available in the code
     * coverage report
     *
     * @param s String correspondig to a line of the report
     * @return The line obtained by removing the "href" attribute
     */
    public static String process(String s) {
        StringTokenizer st = new StringTokenizer(s, " ");
        ArrayList<String> l = new ArrayList<String>();
        while (st.hasMoreTokens()) {
            String t = st.nextToken();

            //First case : the String has some href attributes, we remove them
            if (t.length() > 5 && t.substring(0, 5).equals("href=")) {
                StringTokenizer st1 = new StringTokenizer(t, "\"");
                st1.nextToken();
                st1.nextToken();
                while (st1.hasMoreTokens()) {
                    t = st1.nextToken();
                    l.add(t);
                }
                //Case 2 : the String open a pop-up, we delete it
            } else if (t.equals("onclick=\"popupwindow('help.html');")) {

                //Otherwise, we keep the string
            } else {
                l.add(t);
            }
        }

        String b = "";
        for (String a : l) {
            b = b + a + " ";
        }
        return b;
    }

}
