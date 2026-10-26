<%-- 
    Document   : index
    Created on : 25 oct 2026, 10:06:33 p.m.
    Author     : Cristian Jesús Cruz Mendívil ID:267652
--%>

<%@page import="models.hormiga"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
         <link rel="stylesheet"  type="text/css" href="./index.css"/>
    </head>
    <body>
        <h1>Hormigas!</h1>
        <section>
            <h2>Agregar Especie</h2>
            <form action="SvAntPedia" method="POST">
                <div>
                    <label>Nombre</label>
                    <input name="nombre" type="text" required/>
                </div>
                <div>
                    <label>Foto</label>
                    <input name="photo" type="text" required/>
                </div>
                <div>
                    <label>Descripcion</label>
                    <input name="descrip" type="text" required />
                </div>
                <button type="submit" >Enviar</button>
            </form>
        </section>
        
        <section>
            <h2>Especies agregadas</h2>
            <form method="GET" action="SvAntPedia">
                <button type="submit">Refrescar</button>
            </form>
            
            <c:if test="${not empty lista}">
                <h2>Especies</h2>
                <div class="hormigas-container">
                    <c:forEach var="AntP" items="${lista}">
                        <div class="hormiga">

                            <img <img src="${AntP.getPhoto()}" alt="${AntP.getName()}"/>
                            <p>
                                <strong>${AntP.getName()}</strong> 
                                <br/>
                                 <strong>Descripcion:</strong> ${AntP.getDescrip()} 
                            </p>
                        </div>
                    </c:forEach>
                </div>
            </c:if>
            
        </section>
    </body>
</html>
