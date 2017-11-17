package servlets;

import services.Column;
import services.Dispatcher;
import services.Row;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/table")
public class TableServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String databaseName = req.getParameter("database");
        Dispatcher.use_database(new String[]{databaseName});
        Dispatcher.use_table(req.getParameter("command").split(" "));
        PrintWriter pw = resp.getWriter();
        for(Column column: Dispatcher.getCurrentTable().getColumns()){
            pw.append(column.getName()+ " ");
        }
        pw.append("\n");
        for(Row row: Dispatcher.getCurrentTable().getRows()){
            pw.append(row.toString()+"\n");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String databaseName = req.getParameter("database");
        Dispatcher.use_database(new String[]{databaseName});
        Dispatcher.create_table(req.getParameter("command").split(" "));
        resp.getWriter().append("creating table");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String databaseName = req.getParameter("database");
        Dispatcher.use_database(new String[]{databaseName});
        String tableName = req.getParameter("table");
        Dispatcher.setCurrentTable(Dispatcher.getCurrentDatabase().getTableByName(tableName));
        String editType = req.getParameter("type");

        String[] commandLine = req.getParameter("command").split(" ");
        switch (editType){
            case ("add"):
                Dispatcher.add_row(commandLine);
                break;
            case ("rename_column"):
                Dispatcher.rename_column_name(commandLine);
                break;
            case ("remove_rows"):
                Dispatcher.remove_copy_from_table(commandLine);
                break;
            case ("edit_row"):
                Dispatcher.edit_row(commandLine);
                break;
            default:
                resp.sendError(405);
                break;
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Dispatcher.delete_table(req.getParameter("command").split(" "));
        resp.getWriter().append("delete table");
    }
}
