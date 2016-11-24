<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Bootstrap -->
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

        <link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="style/login.css">

        <title>Final</title>
    </head>
    <body>
        <div class="form">
            <div class="tab-content">
                <div id="star"></div>
                    <h1>Thank you <c:out value="${name}"/>!</h1>
                    <h1>Your score is <font style="color:#03CDB2"><c:out value="${score}"/>/10</font></h1>
                    <br><br>
                    <a class="replay" onclick="window.location.href = 'login.htm?status=true'"> <span class="glyphicon glyphicon-repeat"></span>&nbsp;&nbsp;Play again</button></a>
                  <br>
            </div>
        </div>
    </body>
</html>
