/**
 * Class LoginController.java
 * -----------------------------------------------------------------------------
 * Author : Laura
 * Last modification : 17/08/2016
 * -----------------------------------------------------------------------------
 * Controller for calling the view login.jsp
 */
package controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * LoginController. Managing the login view.
 *
 * @author Laura
 */
@Controller
public class LoginController {

    /**
     * Method GET. This method is called to display the login view. It is called
     * with a parameter "status" which says if the user has attempted to log in
     * with a name already taken (status = false) or not (status = true). It
     * returns the view with the parameter "error" which is empty except if the
     * status is false.
     *
     * @param request Servlet request
     * @param response Servlet response
     * @return View login
     * @throws IOException
     * @throws InterruptedException
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView methodGet(HttpServletRequest request, HttpServletResponse response) throws IOException, InterruptedException {
        ModelAndView r = new ModelAndView("login");

        String status = request.getParameter("status");

        if (status.equals("false")) {
            r.addObject("error", "This name is already used.");
        } else {
            r.addObject("error", "");
        }

        return r;

    }
}
