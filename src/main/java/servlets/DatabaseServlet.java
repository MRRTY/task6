package servlets;

import services.Dispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/database")
public class DatabaseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Dispatcher.use_database(req.getParameter("command").split(" "));
        resp.getWriter().append("using database");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Dispatcher.create_database(req.getParameter("command").split(" "));
        resp.getWriter().append("creating database");
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Dispatcher.delete_database(req.getParameter("command").split(" "));
        resp.getWriter().append("removing database");
    }
}
