<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, MODELS.ficheSalaire, Services.interfacegestionSALAIRE, Services.GestionSalairesv2" %>
<link rel="stylesheet" type="text/css" href="./style/styles.css">

<!DOCTYPE html>
<html>
<head>
    <title>Gestion des Salaires</title>
</head>
<body>
    <h1>Gestion des Salaires</h1>

    <!-- Formulaire pour créer une nouvelle fiche de salaire -->
    <form action="gestionSalaire.jsp" method="post">
        <h3>Créer une Fiche Salaire</h3>
        <label>Matricule Employé:</label><input type="text" name="employeMATRICULE" required><br>
        <label>Date:</label><input type="date" name="dateFiche" required><br>
        <label>Nombre d'Heures:</label><input type="text" name="nbrheures" required><br>
        <label>Montant Brut:</label><input type="text" name="montantBrut" required><br>
        <label>Taux de Taxe (%):</label><input type="text" name="tauxTaxe" required><br>
        <input type="submit" name="action" value="CreerFicheSalaire">
    </form>

    <h3>Liste des Fiches de Salaire</h3>
    <table border="1">
        <tr>
            <th>Numéro Fiche</th>
            <th>Matricule</th>
            <th>Date</th>
            <th>Nombre d'Heures</th>
            <th>Montant Brut</th>
            <th>Taxe (%)</th>
            <th>Actions</th>
        </tr>
        <%
            interfacegestionSALAIRE gestionSalaire = new GestionSalairesv2();
            if (gestionSalaire != null) {
                List<ficheSalaire> fiches = gestionSalaire.listerFichesSalaire();
                for (ficheSalaire fiche : fiches) {
        %>
        <tr>
            <td><%= fiche.getNumFiche() %></td>
            <td><%= fiche.getEmployeMATRICULE() %></td>
            <td><%= fiche.getDateFiche() %></td>
            <td><%= fiche.getNbrheures() %></td>
            <td><%= fiche.getMontantBrut() %></td>
            <td><%= fiche.getTauxTaxe() %></td>
            <td>
                <!-- Modifier une fiche -->
                <form action="gestionSalaire.jsp" method="post" style="display:inline;">
                    <input type="hidden" name="numFiche" value="<%= fiche.getNumFiche() %>">
                    <input type="submit" name="action" value="Modifier">
                </form>

                <!-- Supprimer une fiche -->
                <form action="gestionSalaire.jsp" method="post" style="display:inline;">
                    <input type="hidden" name="numFiche" value="<%= fiche.getNumFiche() %>">
                    <input type="submit" name="action" value="Supprimer">
                </form>

                <!-- Calcul du montant net -->
                <form action="gestionSalaire.jsp" method="post" style="display:inline;">
                    <input type="hidden" name="numFiche" value="<%= fiche.getNumFiche() %>">
                    <input type="submit" name="action" value="CalculerMontantNet">
                </form>
            </td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="7">Aucune fiche de salaire trouvée.</td>
        </tr>
        <% } %>
    </table>

    <%
        String action = request.getParameter("action");

        if ("CreerFicheSalaire".equals(action)) {
            // Créer une fiche
            int matricule = Integer.parseInt(request.getParameter("employeMATRICULE"));
            Date dateFiche = java.sql.Date.valueOf(request.getParameter("dateFiche"));
            double nbrheures = Double.parseDouble(request.getParameter("nbrheures"));
            double montantBrut = Double.parseDouble(request.getParameter("montantBrut"));
            double tauxTaxe = Double.parseDouble(request.getParameter("tauxTaxe"));
            gestionSalaire.creerFicheSalaire(dateFiche, nbrheures, montantBrut, tauxTaxe, matricule);
            response.sendRedirect("gestionSalaire.jsp");

        } else if ("Supprimer".equals(action)) {
            // Supprimer une fiche
            int numFiche = Integer.parseInt(request.getParameter("numFiche"));
            gestionSalaire.supprimerFicheSalaire(numFiche);
            response.sendRedirect("gestionSalaire.jsp");

        } else if ("CalculerMontantNet".equals(action)) {
            // Calculer le montant net d'une fiche
            int numFiche = Integer.parseInt(request.getParameter("numFiche"));
            double montantNet = gestionSalaire.calculerMontantNet(numFiche);
            out.println("<script>alert('Montant Net de la fiche " + numFiche + ": " + montantNet + "');</script>");

        } else if ("Modifier".equals(action)) {
            // Logique de modification ici
            int numFiche = Integer.parseInt(request.getParameter("numFiche"));
            // Exemple : afficher un formulaire pré-rempli
            response.sendRedirect("modifierFicheSalaire.jsp?numFiche=" + numFiche);
        }
    %>
</body>
</html>

