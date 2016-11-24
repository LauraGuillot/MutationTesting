/**
 * Class Tester.java
 * -----------------------------------------------------------------------------
 * Author : Laura
 * Last modification : 10/08/2016
 * -----------------------------------------------------------------------------
 *
 */
package unittestrunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import util.Pack;

/**
 * Class Tester. Test if a unit test kills a mutant or not.
 *
 * @author Laura
 */
public class Tester {

    /**
     * This method : 1) adds a new unit test to the tests 2) compiles the new
     * test classes 3) Execute the tests 4)Remove the .class to enable a new
     * compilation
     *
     * @param test Code of the new test to add
     * @param projectName Name of the project
     * @param srcpacks
     * @param testpacks
     * @param testpack Name of the packages containing the test
     * @param testClass Name of the test class in which we will add the new test
     * @param loc Location of the application
     * @param name User name
     * @return Result of the compilation and the execution
     * @throws IOException
     */
    public static ArrayList<String> tryToKill(String test, String projectName, ArrayList<Pack> srcpacks, ArrayList<Pack> testpacks, ArrayList<String> testpack, String testClass, String loc, String name) throws IOException {
        long debut = System.currentTimeMillis();
        ArrayList<String> results = new ArrayList<String>();

        /*--------------ADD A NEW UNIT TEST -----------*/
        Project p = new Project(projectName, srcpacks, testpacks, testpack);
        InsertTest i = new InsertTest(p, test, testClass);
        i.main(loc, name);

        /*---------- COMPILING THE NEW TESTS-----------*/
        Compiler c = new Compiler(p, i.getTestClassName(), "Base");
        ArrayList<String> a = c.compileNewTest(loc, name);

        if (a.isEmpty()) {
            //System.out.println("Compilation successful!");
            Compiler c1 = new Compiler(p, i.getTestClassName(), "Mutant");
            c1.compileNewTest(loc, name);

        } else {
            results = addCompErrors(a);
        }

        /*------------ EXECUTION OF THE TESTS ------------------*/
        if (a.isEmpty()) {
            Executor e = new Executor(p, i.getTestClassName(), "Base");
            String r1 = e.execute(loc, name);

            if (r1.equals("success")) {
                Executor e2 = new Executor(p, i.getTestClassName(), "mutant");
                String r2 = e2.execute(loc, name);
                if (r2.equals("fail")) {
                    results.add("success");
                } else {
                    results.add("fail1");
                }
            } else {
                results.add("fail2");
            }
        }

        System.out.println(System.currentTimeMillis() - debut);

        return results;
    }

    /**
     * Formatting the output errors
     *
     * @param a List of errors
     * @return List of readable errors
     */
    public static ArrayList<String> addCompErrors(ArrayList<String> a) {
        ArrayList<String> r = new ArrayList<String>();

        int n = a.size() - 1;
        int k = 0;

        //For each line
        while (k < n) {
            String s1 = a.get(k);

            //If the error is like : [C:\error location]:[error line]: error: [error type]
            //Just keep the error type
            if (s1.startsWith("C:")) {
                StringTokenizer st = new StringTokenizer(s1, ":");
                st.nextToken();
                st.nextToken();
                st.nextToken();
                st.nextToken();
                s1 = st.nextToken();
            }

            //If the error is like : "        ^        "
            //Replace each space by "&nbsp;"
            int n2 = s1.indexOf("^");
            int n1 = 0;
            for (int i = 0; i < s1.length(); i++) {
                if (s1.charAt(i) == ' ') {
                    n1++;
                }
            }
            if (n1 + 1 == s1.length()) {
                s1 = "";
                for (int i = 0; i < n2; i++) {
                    s1 += "&nbsp;";
                }
                s1 += "^";
                for (int i = n2 + 1; i < n1 + 1; i++) {
                    s1 += "&nbsp;";
                }
            }

            r.add(s1);
            k++;
        }

        return r;
    }
}
