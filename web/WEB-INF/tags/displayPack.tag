<!--ATTRIBUTES-->
<!--List of packages-->
<%@ attribute name="packs" required="true" type="java.util.ArrayList"%>
<!--List of classes-->
<%@ attribute name="classes" required="true" type="java.util.ArrayList"%>
<!--Name of the package-->
<%@ attribute name="name" required="true"%>

<!--JSLT TAGS-->
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<li class="dropdown" ><a class="dropdown-toggle folder" data-toggle="dropdown" href="#" onclick="showHide(this)";><span class="glyphicon glyphicon-folder-open" style="font-size: 10pt"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${name}"/>&nbsp;<span class="caret"></span></a>
    <ul style="display:block;"> 
        <c:choose>
            <c:when test="${packs!=null}"> 
                <c:forEach var="p" items="${packs}">
                    <myTags:displayPack packs="${p['packs']}" name="${p['name']}" classes="${p['classes']}"/> 
                </c:forEach>
            </c:when>
        </c:choose>
        <c:choose>
            <c:when test="${classes!=null}"> 
                <c:forEach var="c" items="${classes}">
                    <li><a class ="file" href="#" onclick="displayCode('<c:out value="${c['name']}"/>');"><c:out value="${c['name']}"/></a></li>
                    </c:forEach>
                </c:when> 
            </c:choose>
    </ul>
</li>
