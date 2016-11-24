/**
 * Class IndexController.java
 * -----------------------------------------------------------------------------
 * Author : Laura
 * Last modification : 17/08/2016
 * -----------------------------------------------------------------------------
 * Controller for calling the view index.jsp
 */
package controller;

import Manager.Manager;
import Manager.ManagerImpl;
import builder.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import unittestrunner.*;
import util.*;

/**
 * IndexController. Managing the view index.jsp
 *
 * @author Laura
 */
@Controller
public class IndexController {

    /**
     * POST method. This method is called each time a user log in. If the
     * username written is already taken, it returns the login view otherwise,
     * it called the get method.
     *
     * @param n User name
     * @param response Servlet Response
     * @return View
     */
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView identify(@RequestParam("name") String n, HttpServletResponse response) {

        Manager m = ManagerImpl.getInstance();
        Boolean b = m.add(n);
        System.out.println(b);

        ModelAndView returned;
        if (b) {
            returned = new ModelAndView("redirect:index.htm?n=1");
            returned.addObject("name", n);
            returned.addObject("score", 0);

            //Creation of a folder for this user 
            
            // String loc = "/home/laura/";
            String loc = "C:/Users/Laura/Desktop/Mutation/";
            
            loc = loc + "data/" + n;
            new File(loc).mkdir();

        } else {
            returned = new ModelAndView("redirect:login.htm?status=false");
        }

        return returned;
    }

    /**
     * Method GET. This method is called for each evaluation task, that is to
     * say for each mutant. Given the name of the user and the name of the
     * project, it retrieves pieces of information for the game as the code, the
     * mutation report or the coverage report.
     *
     * @param request Servlet request
     * @param response Servlet response
     * @return View
     * @throws IOException
     * @throws InterruptedException
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView methodGet(HttpServletRequest request, HttpServletResponse response) throws IOException, InterruptedException {
        ModelAndView r = new ModelAndView("index");

        //Name
        String name = request.getParameter("name");
        r.addObject("name", name);

        //Projects list
        ArrayList<String> l = new ArrayList<String>();
        l.add("Project1");
        l.add("Project2");
        l.add("Project3");
        l.add("Project4");
        l.add("Project5");
        l.add("Project6");
        l.add("Project7");
        l.add("Project8");
        l.add("Project9");
        l.add("Project10");

        //Answers
        ArrayList<String> a = new ArrayList<String>();
        /*1*/
        a.add("K");
        /*2*/
        a.add("K");
        /*3*/
        a.add("K");
        /*4*/
        a.add("E");
        /*5*/
        a.add("K");
        /*6*/
        a.add("E");
        /*7*/
        a.add("E");
        /*8*/
        a.add("K");
        /*9*/
        a.add("K");
        /*10*/
        a.add("K");

        //Index
        String sn = request.getParameter("n");
        int n = Integer.parseInt(sn);

        Manager m = ManagerImpl.getInstance();

        if (n > 1) {
            int i = n - 2;
            boolean bb = m.saveAnswer(name, i + 1, request.getParameter("an"));
            if (bb && a.get(i).equals(request.getParameter("an"))) {
                m.incrScore(name);
            }
        }

        r.addObject("score", m.findUser(name).getScore());

        if (n < 11) {
            n++;
            r.addObject("n", n);

            //Project name
            String projectName = l.get(n - 2);
            r.addObject("projectName", projectName);

            //Location of the application
           
            // String loc = "/home/laura/";
            String loc = "C:/Users/Laura/Desktop/Mutation/";
            r.addObject("loc", loc);

            //Mutation data
            MutationReport mr = new MutationReport(projectName);
            mr.read(loc);
            r.addObject("mutatedClass", mr.getMutatedClass());
            r.addObject("mutatedLine", mr.getLine());

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
            r.addObject("project", project);

            //Classes of the project
            ArrayList<Cl> classes = t.getClasses(project);
            r.addObject("cl", classes);

            //Preparation of the required folders
            DataConstruct dc = new DataConstruct(projectName, classes, src, te, loc, name);
            try {
                dc.createDir();

            } catch (IOException ex) {
                Logger.getLogger(IndexController.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

            Project p = new Project(projectName, src, te, null);

            //Compilation of the source code of Base
            unittestrunner.Compiler cmp = new unittestrunner.Compiler(p, "TriangleTest.java", "Base");
            cmp.compileSrc(loc, name);

            //Compilation of the source code of Mutant
            unittestrunner.Compiler cmp1 = new unittestrunner.Compiler(p, "TriangleTest.java", "Mutant");
            cmp1.compileSrc(loc, name);

            //Code coverage computation
            /*Coverage.cover(p, loc, name);*/
            //MutatedClass path
            String s = t.findClassPack(mr.getMutatedClass(), classes);
            s = s.substring(projectName.length() + 5, s.length());//Delete the prefix "projectName/src/"
            s = projectName + "/" + s;
            r.addObject("path", s);

            //Mutant
            s = s.substring(projectName.length() + 1, s.length());
            String dir1 = loc + "projects/" + projectName + "/mutant/" + mr.getMutatedClass();
            String dest1 = loc + "data/" + name + "/" + projectName + "/Mutant/old/src/" + s + "/" + mr.getMutatedClass();
            String dest2 = loc + "data/" + name + "/" + projectName + "/Mutant/new/src/" + s + "/" + mr.getMutatedClass();
            try {
                dc.copyPaste(dir1, dest1);
                dc.copyPaste(dir1, dest2);

            } catch (IOException ex) {
                Logger.getLogger(IndexController.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            r.addObject("mutant", Cl.readCode(loc + "projects/" + projectName + "/mutant/" + mr.getMutatedClass()));

            //Code coverage
            ArrayList<String> cov = Coverage.getReport(projectName, loc);
            r.addObject("rep", cov);

            return r;

        } else {
            ModelAndView re = new ModelAndView("end");
            re.addObject("name", name);
            re.addObject("score", m.findUser(name).getScore());
            return re;
        }
    }
}
