<!--/Jslt tag-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTags" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Mutant evaluation</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!-- Bootstrap -->
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap3-dialog/1.34.7/css/bootstrap-dialog.min.css">
        <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap3-dialog/1.34.7/js/bootstrap-dialog.min.js"></script>

        <!-- JQuery -->
        <script type="text/javascript" src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.2/jquery.min.js"></script>
        <script src="http://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.2/modernizr.js"></script>

        <!-- CodeMirror -->
        <link rel=stylesheet href="scripts/CodeMirror/doc/docs.css"/>
        <link rel="stylesheet" href="scripts/CodeMirror/theme/monokai.css"/>
        <link rel="stylesheet" href="scripts/CodeMirror/addon/hint/show-hint.css"/>
        <link rel="stylesheet" href="scripts/CodeMirror/lib/codemirror.css"/>
        <script src="scripts/CodeMirror/lib/codemirror.js"></script>
        <script src="scripts/CodeMirror/addon/edit/matchbrackets.js"></script>
        <script src="scripts/CodeMirror/addon/hint/show-hint.js"></script>
        <script src="scripts/CodeMirror/addon/edit/closebrackets.js"></script>
        <script src="scripts/CodeMirror/clike.js"></script>
        <link rel="stylesheet" type="text/css" media="screen" href="style/CodeMirror.css"/>

        <!-- Prism -->
        <script language="Javascript" type="text/javascript" src="scripts/prism.js"></script>
        <link rel="stylesheet" type="text/css" media="screen" href="style/prism.css"/>

        <link rel="stylesheet" type="text/css" media="screen" href="style/index.css"/>
        <script language="Javascript" type="text/javascript" src="scripts/index.js"></script>
        <script language="Javascript" type="text/javascript" src="scripts/CallbackCodeFunctions.js"></script>
        <script language="Javascript" type="text/javascript" src="scripts/navCode.js"></script>
        <link rel="stylesheet" type="text/css" media="screen" href="style/navCode.css"/>
        <link rel="stylesheet" type="text/css" media="screen" href="style/Buttons.css"/>
        <link rel="stylesheet" type="text/css" media="screen" href="style/Popup.css"/>

        <link rel="stylesheet" href="style/report/main.css"/>    
        <link rel="stylesheet" href="style/report/source-viewer.css"/>
    </head>

    <body onload="load();" style="display:block;">

        <input type="hidden" value="<c:out value="${mutatedClass}"/>" id="mutClass"/>
        <input type="hidden" value="<c:out value="${loc}"/>" id="loc"/>
        <input type="hidden" value="<c:out value="${projectName}"/>" id="projectName"/>
        <input type="hidden" value="<c:out value="${n}"/>" id="n"/>
        <input type="hidden" value="<c:out value="${name}"/>" id="name"/>
        <input type="hidden" value="<c:out value="${score}"/>" id="score"/>

        <div class="container-fluid text-center" id="main">
            <div class="row content">
                <div class="col-sm-2">

                    <h1>Open packages to view source code</h1>

                    <div class="grey">
                        <!--/Navigation in the code-->
                        <ul class="nav navbar-nav dropdown-menu-left" >                     
                            <myTags:displayPack packs="${project['packs']}" name="${project['name']}" classes="${project['classes']}"/> 
                        </ul>
                    </div>

                </div><!--/sidenav-->

                <div class="col-sm-4 text-left">

                    <h1>Source code</h1>
                    <h3 id="className" style="color:#02A691;"></h3>

                    <!--/Source code-->
                    <c:forEach var="c" items="${cl}">
                        <c:choose>
                            <c:when test="${c['name']==mutatedClass}"> 
                                <div class="language-java" id="<c:out value="${c['name']}"/>" style="display:none;">
                                    <pre class="line-numbers" data-line="<c:out value="${mutatedLine}"/>"> <code><c:forEach var="l" items="${c['code']}">
<c:out value="${l}"/> </c:forEach></code></pre>
                                    </div><!--/language-java-->
                            </c:when>
                            <c:otherwise>
                                <div class="language-java" id="<c:out value="${c['name']}"/>" style="display:none;">
                                    <pre class="line-numbers"> <code><c:forEach var="l" items="${c['code']}">
<c:out value="${l}"/> </c:forEach></code></pre>
                                    </div><!--/language-java-->
                            </c:otherwise>
                        </c:choose>   
                    </c:forEach>

                </div><!--/col-sm-4-->


                <div class="col-sm-4 text-left">

                    <h1>Mutant</h1>
                    <h3>&nbsp;</h3>

                    <!--Mutant source code-->
                    <div id="mutant" class="language-java"> 
                        <pre class="line-numbers" data-line="<c:out value="${mutatedLine}"/>"><code> <c:forEach var="l" items="${mutant}">
<c:out value="${l}"/></c:forEach></code></pre>
                        </div><!--/mutant-->

                    </div><!--/col-sm-4-->

                    <!--Pieces of information about the mutant-->
                    <div class="col-sm-2">  

                        <div id="cpt">
                            <center> <h1 class="cpt">
                                    Mutant : <c:out value="${n-1}"/> / 10
                                <br> 
                                Score : <c:out value="${score}"/>
                            </h1></center>
                    </div>



                    <div class="grey" style="padding-left:8px;padding-right: 8px;">

                        <center>
                            <h1 class="subtitle">Mutation location</h1>  
                        </center>

                        <div>
                            <font style="font-weight:600">Package</font> : <c:out value="${path}"/><br>
                            <font style="font-weight:600">Mutated class</font> : <c:out value="${mutatedClass}"/><br>
                            <font style="color:#DBBE03;font-weight:600;">LINE : <c:out value="${mutatedLine}"/> </font>
                            <br><br>
                            <center>
                                <button id="displayClass" class="button round" onclick="displayCode('<c:out value="${mutatedClass}"/>');"><span class="glyphicon glyphicon-search"></span></button><h6 style="display:inline">&nbsp;&nbsp;SHOW ORIGINAL CLASS</h6>
                            </center>
                        </div>

                        <hr align="center" width="80%" size="2">

                        <center>
                            <h1 class="subtitle">Code coverage</h1>
                        </center>

                        <!-- Trigger the modal with a button -->
                        <center>
                            <button id="reportLink" class="button round" data-toggle="modal" data-target="#modalReport"><i style="font-size:20px" class="fa">&#xf06e;</i></button><h6 style="display:inline">&nbsp;&nbsp;VIEW COVERAGE REPORT </h6>       
                        </center>
                        <!-- Modal -->
                        <div class="modal fade" id="modalReport" role="dialog">
                            <div class="modal-dialog">

                                <!-- Modal content-->
                                <div class="modal-content">
                                    <div class="modal-header" id="reportHeader">
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <h4 class="modal-title">Coverage report</h4>
                                    </div>
                                    <div class="modal-body" id="modalReportBody">
                                        <!-- Package report-->
                                        <br>
                                        <center><div id="rep" style="width:80%;">
                                                <c:forEach var="l" items="${rep}">
                                                    <c:set var="x" value="${l}"/>
                                                    <%
                                                        String resp = (String) pageContext.getAttribute("x");
                                                        out.println(resp);
                                                    %>  

                                                </c:forEach>
                                            </div></center>

                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div><!--/col-sm-2 sidenav-->

                <!--Unit test area-->
                <div id="unitTest" class="col-sm-6 text-left">
                    <h1>Write a test to kill the mutant!</h1>
                    <textarea id="java-code"> @Test(timeout=3000)
public void yourTest() {
    
} </textarea>
                    <!--CodeMirror initialization-->
                    <script>
                        var editor = CodeMirror.fromTextArea(document.getElementById("java-code"), {
                            lineNumbers: true,
                            matchBrackets: true,
                            mode: "text/x-java",
                            theme: "monokai",
                            autoCloseBrackets: true,
                            showCursorWhenSelecting: true
                        });
                    </script>
                </div><!--/col-sm-6 unitTest-->

                <!-- Output area-->
                <div class="col-sm-6 text-left">
                    <h1>&nbsp;</h1>
                    <div id="compilationResult" class="result">
                        Output
                    </div>
                    <div id="output" class="result">
                    </div>
                    <button id="run" class="button choice"  onclick="tryTest();">RUN TEST</button>
                    <button id="runLoading" style="display:none;cursor:not-allowed;background-color: #DBBE03" class="button choice" disabled><span class="glyphicon glyphicon-refresh glyphicon-refresh-animate"></span>&nbsp;&nbsp;PROCESSING</button>
                    <button id="equi" class="button choice" onclick="equi();" >EQUIVALENT MUTANT</button>
                </div><!--/col-sm-6-->

            </div><!--/row-content-->

            <!-- Pop-up-->
            <div class="modal fade">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                            <h4 class="modal-title"></h4>
                        </div>
                        <div class="modal-body">
                            <p></p>
                        </div>
                        <div class="modal-footer">
                        </div>
                    </div><!-- /modal-content -->
                </div><!-- /modal-dialog -->
            </div><!-- /modal -->       
        </div>
        <div id="load" class="se-pre-con" style="display:none;">
            <div class="loading"> Loading the next mutant ...</div>
            <center><div class="icon-load"></div></center>           
        </div>
    </body>
</html>

