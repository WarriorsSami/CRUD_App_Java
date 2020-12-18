package usermanagement.web;

import usermanagement.dao.UserDAO;
import usermanagement.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

// Servlet Controller for performing HTTP requests related to CRUD operations on table user
@WebServlet(name = "UserServlet", urlPatterns = "/crud")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final UserDAO userDAO;

    public UserServlet () {
        super ();
        this.userDAO = new UserDAO ();
    }

    // Servlet Handler for POST request
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType ("text/plain");
        response.getWriter ().println ("Here is a line from POST!");

        this.doGet (request, response);
    }

    // Servlet Handler for GET request
    protected void doGet (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType ("text/plain");
        response.getWriter ().println ("Here is a line from GET!");

        String action = request.getServletPath ();

        switch (action) {
            case "/new":
                showNewForm (request, response);
                break;
            case "/insert":
                try {
                    insertUser (request, response);
                } catch (SQLException e) {
                    e.printStackTrace ();
                }
                break;
            case "/update":
                try {
                    updateUser (request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "/edit":
                try {
                    showEditForm (request, response);
                } catch (SQLException e) {
                    e.printStackTrace ();
                }
                break;
            case "/delete":
                try {
                    deleteUser (request, response);
                } catch (SQLException e) {
                    e.printStackTrace ();
                }
                break;
            default:
                // handle list
                try {
                    listUsers (request, response);
                } catch (SQLException e) {
                    e.printStackTrace ();
                }
                break;
        }
    }

    private void showNewForm (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher ("/user-form.jsp");
        dispatcher.forward (request, response);
    }

    private void insertUser (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String name = request.getParameter ("name");
        String email = request.getParameter ("email");
        String country = request.getParameter ("country");
        User newUser = new User (name, email, country);
        userDAO.insertUser (newUser);
        response.sendRedirect ("/list");
    }

    private void deleteUser (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        int id = Integer.parseInt (request.getParameter ("id"));
        userDAO.deleteUser (id);
        response.sendRedirect ("/list");
    }

    private void showEditForm (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        int id = Integer.parseInt (request.getParameter ("id"));
        User existingUser = userDAO.selectUser (id);
        RequestDispatcher dispatcher = request.getRequestDispatcher ("/user-form.jsp");
        request.setAttribute ("user", existingUser);
        dispatcher.forward (request, response);
    }

    private void updateUser (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        int id = Integer.parseInt (request.getParameter ("id"));
        String name = request.getParameter ("name");
        String email = request.getParameter ("email");
        String country = request.getParameter ("country");
        User user = new User (id, name, email, country);
        userDAO.updateUser (user);
        response.sendRedirect ("/list");
    }

    private void listUsers (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        List <User> listUser = userDAO.selectAllUsers ();
        for (User elem: listUser)
            System.out.println (elem.getID () + " " + elem.getName () + " " + elem.getEmail () + " " + elem.getCountry ());

        request.setAttribute ("listUser", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher ("/user-list.jsp");
        dispatcher.forward (request, response);
    }
}
