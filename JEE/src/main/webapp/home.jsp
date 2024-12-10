<link rel="stylesheet" type="text/css" href="./style/styles.css">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Menu Principal</title>
</head>
<body>
    <h1>Menu Principal</h1>
    <p>Veuillez choisir une option :</p>
    <form action="home.jsp" method="post">
        <button type="submit" name="gestion" value="employes">Gérer les Employés</button>
        <button type="submit" name="gestion" value="salaires">Gérer les Salaires</button>
    </form>

    <%
        String gestion = request.getParameter("gestion");
        if ("employes".equals(gestion)) {
            response.sendRedirect("listerEmp.jsp");
        } else if ("salaires".equals(gestion)) {
            response.sendRedirect("gestionSalaire.jsp");
        }
    %>
</body>
</html>
