<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html >
    <head>
        <meta charset="UTF-8">
        <title>Log in</title>
        <link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="style/login.css">
    </head>

    <body>
        <div class="form">
            <div class="tab-content">
               
                <div id="signup">    
                    <h1>Log in </h1>
                    <form action="index.htm" method="post">
                        <div class="error">&nbsp;<c:out value="${error}"/></div></br>
                        <div class="field-wrap">
                            <label>
                                User Name<span class="req">*</span>
                            </label>
                            <input type="text" required autocomplete="off" name="name" />
                        </div>
                       <button type="submit" class="button button-block"/>Get Started</button>
                    </form>   
                </div>

                <div id="login">   
                </div>
                        
            </div><!-- tab-content -->
        </div> <!-- /form -->
        <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
        <script src="scripts/login.js"></script>
    </body>
</html>
