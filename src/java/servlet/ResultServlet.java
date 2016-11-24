/**
 * Class resultServlet.java
 * -----------------------------------------------------------------------------
 * Author : Laura
 * Last modification : 10/08/2016
 * -----------------------------------------------------------------------------
 * This servlet is used to test the code written by the users. It does
 * compilation and execution, and then returns the output.
 */
package servlet;

import builder.MutationReport;
import builder.TreeBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import unittestrunner.Tester;
import util.Pack;

/**
 * Servlet ResultServlet. Servlet called each time a user wants to execute his
 * test
 *
 * @author Laura
 */
@WebServlet(name = "resultServlet", urlPatterns = {"/resultServlet"})
public class ResultServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request with four parameters : the user source
     * code, the project name, the user name and the location of the application
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Source code of the user
        String testCode = request.getParameter("test");

        //Project name
        String projectName = request.getParameter("projectName");

        //Location of the application
        String loc = request.getParameter("loc");

        //Name of the user
        String name = request.getParameter("name");

        /*GET INFORMATION ABOUT THE PROJECT*/
        //Mutation data
        MutationReport mr = new MutationReport(projectName);
        mr.read(loc);

        //Packages
        TreeBuilder t = new TreeBuilder(projectName, loc);
        ArrayList<Pack> src = t.getSrc();
        ArrayList<Pack> test = t.getTests();

        //Test path
        ArrayList<String> testpack = new ArrayList<String>();
        Pack temp = new Pack(test, null, "");
        String s = t.findClassPack(mr.getTestName(), t.getClasses(temp));
        s = s.substring(projectName.length() + 6, s.length());//Delete the prefix "projectName/test/"
        StringTokenizer st = new StringTokenizer(s, "/");
        while (st.hasMoreTokens()) {
            testpack.add(st.nextToken());
        }

        /*TRY THE USER TEST*/
        //Test Execution
        ArrayList<String> a = Tester.tryToKill(testCode, projectName, src, test, testpack, mr.getTestName(), loc, name);
        //Format the result
        String result = "";
        if (a.size() == 1) {
            result = a.get(0);
        } else {
            for (String z : a) {
                result = result + z + "#";
            }
        }

        /*SEND THE RESPONSE*/
        response.setContentType("text/plain");
        response.getWriter().write(result);
    }

}
