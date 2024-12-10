package controlers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import Entities.credentialsC;
import dao.CredentialsDAO;

/**
 * Servlet implementation class Logincontroler
 */
@WebServlet("/logincontroler")
public class logincontroler extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public logincontroler() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

        String vlogin, vpassword;
        credentialsC c=null;

        vlogin=request.getParameter("username");
        vpassword=request.getParameter("password");

        c=CredentialsDAO.checkcrdentials(vlogin, vpassword);

        if(c==null)
        {
            request.setAttribute("ERROR", "Login ou Mot de passe incorrecte");
            request.getRequestDispatcher("loginpage.jsp").forward(request, response);
        }
        else
        {
            request.getSession().setAttribute("user", c);
            request.getRequestDispatcher("home.jsp").forward(request, response);
        }



    }

}
