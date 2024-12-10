<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, MODELS.employe, Services.interfacegestionEMPL, Services.GestionEmplv2" %>
<link rel="stylesheet" type="text/css" href="./style/styles.css">

<!DOCTYPE html>
<html>
<head>
    <title>Gestion des Employés</title>
</head>
<body>
    <h1>Gestion des Employés</h1>

    <form action="listerEmp.jsp" method="post">
        <h3>Ajouter un Employé</h3>
        <label>Matricule:</label><input type="text" name="employeMATRICULE" required><br>
        <label>Nom:</label><input type="text" name="nom" required><br>
        <label>Prénom:</label><input type="text" name="prenom" required><br>
        <label>Adresse:</label><input type="text" name="adresse" required><br>
        <input type="submit" name="action" value="AjouterEmploye">
    </form>

    <h3>Liste des Employés</h3>
    <table border="1">
        <tr>
            <th>Matricule</th>
            <th>Nom</th>
            <th>Prénom</th>
            <th>Adresse</th>
            <th>Actions</th>
        </tr>
        <%
            GestionEmplv2 gestionEMPL = new GestionEmplv2();
            List<employe> employes = gestionEMPL.afficherEmployes();
            if (employes != null && !employes.isEmpty()) {
                for (employe emp : employes) {
        %>
        <tr>
            <td><%= emp.getEmployeMATRICULE() %></td>
            <td><%= emp.getNom() %></td>
            <td><%= emp.getPrenom() %></td>
            <td><%= emp.getAdresse() %></td>
            <td>
                <!-- Boutons pour modifier et supprimer -->
                <form action="listerEmp.jsp" method="post" style="display:inline;">
                    <input type="hidden" name="employeMATRICULE" value="<%= emp.getEmployeMATRICULE() %>">
                    <input type="submit" name="action" value="Modifier">
                </form>
                <form action="listerEmp.jsp" method="post" style="display:inline;">
                    <input type="hidden" name="employeMATRICULE" value="<%= emp.getEmployeMATRICULE() %>">
                    <input type="submit" name="action" value="Supprimer">
                </form>
            </td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="5">Aucun employé trouvé.</td>
        </tr>
        <% } %>
    </table>

    <%
        String action = request.getParameter("action");
        if ("AjouterEmploye".equals(action)) {
            // Ajouter un employé
            int matricule = Integer.parseInt(request.getParameter("employeMATRICULE"));
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String adresse = request.getParameter("adresse");
            employe newEmp = new employe(matricule, nom, prenom, adresse);
            gestionEMPL.ajouterEmploye(newEmp);
            response.sendRedirect("listerEmp.jsp");
        } else if ("Supprimer".equals(action)) {
            // Supprimer un employé
            int matricule = Integer.parseInt(request.getParameter("employeMATRICULE"));
            gestionEMPL.supprimerEmploye(matricule);
            response.sendRedirect("listerEmp.jsp");
        } else if ("Modifier".equals(action)) {
            // Modifier un employé
            int matricule = Integer.parseInt(request.getParameter("employeMATRICULE"));
            employe emp = gestionEMPL.rechercherEmployeParMatricule(matricule);
            if (emp != null) {
    %>
    <h3>Modifier l'Employé</h3>
    <form action="listerEmp.jsp" method="post">
        <input type="hidden" name="employeMATRICULE" value="<%= emp.getEmployeMATRICULE() %>">
        <label>Nom:</label><input type="text" name="nom" value="<%= emp.getNom() %>" required><br>
        <label>Prénom:</label><input type="text" name="prenom" value="<%= emp.getPrenom() %>" required><br>
        <label>Adresse:</label><input type="text" name="adresse" value="<%= emp.getAdresse() %>" required><br>
        <input type="submit" name="action" value="Enregistrer">
    </form>
    <%
            }
        } else if ("Enregistrer".equals(action)) {
            // Enregistrer les modifications
            int matricule = Integer.parseInt(request.getParameter("employeMATRICULE"));
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String adresse = request.getParameter("adresse");
            employe updatedEmp = new employe(matricule, nom, prenom, adresse);
            gestionEMPL.supprimerEmploye(matricule); // Supprimer l'ancien enregistrement
            gestionEMPL.ajouterEmploye(updatedEmp); // Ajouter la mise à jour
            response.sendRedirect("listerEmp.jsp");
        }
    %>
</body>
</html>
