import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {
    private static final ArrayList<dto> customers = new ArrayList<>();

    // -------------------- GET (get data) --------------------
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/javaeeapp",
                    "root", "1234"
            );

            String query = "SELECT * FROM customer";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet rs = preparedStatement.executeQuery();

            boolean found = false;

            while (rs.next()) {
                found = true;
                String id = rs.getString("id");
                String name = rs.getString("name");

                out.println("<tr><td>" + id + "</td><td>" + name + "</td></tr>");
            }

            if (!found) {
                out.println("<tr><td colspan='2'>No customers found</td></tr>");
            }

            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<tr><td colspan='2'>Error occurred</td></tr>");
        }
    }
//==================get method
//        if (id == null) {
//            System.out.println("Customer get");
//            for (dto c : customers) {
//                resp.setContentType("text/html;charset=UTF-8");
//                resp.getWriter().println("<tr>"+"<td>"+c.getId()+"</td>"+"<td>"+c.getName()+"</td>"+"</tr>");
//            }
//        }else {
//            System.out.println("Customer get");
//            for (dto c : customers) {
//                if (c.getId().equals(id)) {
//                    resp.setContentType("text/html;charset=UTF-8");
//                    resp.getWriter().println("<tr>"+"<td>"+c.getId()+"</td>"+"<td>"+c.getName()+"</td>"+"</tr>");
//                }
//
//            }
//        }
//
//    resp.getWriter().println("Customer not found");
        //}

    // -------------------- POST  (save data) --------------------

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        String id = req.getParameter("id");
        String name = req.getParameter("name");

        if (id == null || name == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.write("{\"message\":\"Missing parameters\"}");
            return;
        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/javaeeapp",
                    "root","1234");
            String query="INSERT INTO customer(id,name) VALUES (?,?)";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,id);
            preparedStatement.setString(2,name);
            int rowInserted=preparedStatement.executeUpdate();
            if (rowInserted>0){
                resp.getWriter().println("Customer saved successfully");

            }else {
                resp.getWriter().println("Customer not saved");
            }
            connection.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        dto customer = new dto(id, name);
//        customers.add(customer);
//
//        for (dto c : customers) {
//            System.out.println("ID: " + c.getId() + ", Name: " + c.getName());
//        }
//        System.out.println("---------------------------------------------");
//
//        resp.setStatus(HttpServletResponse.SC_OK);
//        out.write("{\"message\":\"Customer saved successfully\"}");
    }



    // -------------------- PUT  (update data) --------------------
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("PUT request received...");
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        String id = req.getParameter("id");
        String name = req.getParameter("name");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/javaeeapp",
                    "root", "1234");

            String query = "UPDATE customer SET name=? WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, id);

            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                out.println("Customer updated successfully");
            } else {
                out.println("Customer not found or not updated");
            }

            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

//        if (id == null || name == null) {
//            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            out.write("{\"message\":\"Missing required parameters\"}");
//            return;
//        }
//
//        boolean found = false;
//        for (dto c : customers) {
//            if (c.getId().equals(id)) {
//                c.setName(name);
//                found = true;
//                break;
//            }
//        }
//
//        if (!found) {
//            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
//            out.write("{\"message\":\"Customer not found\"}");
//            return;
//        }
//
//
//        for (dto c : customers) {
//            System.out.println("ID: " + c.getId() + ", Name: " + c.getName());
//        }
//        System.out.println("=======================================");
//
//        resp.setStatus(HttpServletResponse.SC_OK);
//        out.write("{\"message\":\"Customer updated successfully\"}");
//
//    }


    // -------------------- DELETE (delete data)  --------------------
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("DELETE request received...");
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        String id = req.getParameter("id");

        if (id == null || id.trim().isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.write("{\"message\":\"Missing customer ID\"}");
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/javaeeapp",
                    "root", "1234"
            );

            String query = "DELETE FROM customer WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, id); // IMPORTANT!

            int rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {
                resp.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"message\":\"Customer deleted successfully\"}");
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.write("{\"message\":\"Customer not found\"}");
            }

            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.write("{\"message\":\"Error deleting customer\"}");
        }
    }


//        if (id == null || id.trim().isEmpty()) {
//            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            out.write("{\"message\":\"Missing required parameters\"}");
//            return;
//        }
//
//        dto toRemove = null;
//
//        for (dto c : customers) {
//            if (c.getId().equals(id)) {
//                toRemove = c;
//                break;
//            }
//        }
//
//        if (toRemove == null) {
//            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
//            out.write("{\"message\":\"Customer not found\"}");
//            return;
//        }
//
//        customers.remove(toRemove);
//
//        resp.setStatus(HttpServletResponse.SC_OK);
//        out.write("{\"message\":\"Customer deleted successfully\"}");
//    }


}
