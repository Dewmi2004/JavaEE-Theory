import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {

    // -------------------- GET (get data) --------------------
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

            resp.setContentType("application/json");
            PrintWriter out = resp.getWriter();

            String id = req.getParameter("id");

            if (id == null || id.trim().isEmpty()) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.write("{\"message\":\"Customer ID is required\"}");
                return;
            }

            dto customer = new dto("C001", "Shani");

            if (!id.equals(customer.getId())) {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.write("{\"message\":\"Customer not found\"}");
                return;
            }

            String json = String.format(
                    "{\"id\":\"%s\",\"name\":\"%s\"}",
                    customer.getId(),
                    customer.getName()
            );

            resp.setStatus(HttpServletResponse.SC_OK);
            out.write(json);
        }



    // -------------------- POST (save data) --------------------
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        String id = req.getParameter("id");
        String name = req.getParameter("name");

        if (id == null || name == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.write("{\"message\":\"Missing required parameters\"}");
            return;
        }

        dto customer = new dto(id, name);
        customer.setId("C001");
        customer.setName("Shani");

        System.out.println("Customer Saved: " + customer.getId() + ", " + customer.getName());

        resp.setStatus(HttpServletResponse.SC_OK);
        out.write("{\"message\":\"Customer saved successfully\"}");
    }


    // -------------------- PUT  (update data) --------------------
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("PUT request received...");
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        if (id == null || name == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.write("{\"message\":\"Missing required parameters\"}");
            return;
        }
        dto customer = new dto(id, name);
        customer.setId("C002");
        customer.setName("nimal");
        System.out.println("Customer Updated: " + customer.getId() + ", " + customer.getName());
        resp.setStatus(HttpServletResponse.SC_OK);
        out.write("{\"message\":\"Customer updated successfully\"}");

    }


    // -------------------- DELETE (delete data)  --------------------
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("DELETE request received...");
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        String id = req.getParameter("id");
        if (id == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.write("{\"message\":\"Missing required parameters\"}");
            return;
        }
        dto customer = new dto(id, id);
        customer.setId("C003");
        customer.setName("nimal");
        System.out.println("Customer Deleted: " + customer.getId() + ", " + customer.getName());
        resp.setStatus(HttpServletResponse.SC_OK);
        out.write("{\"message\":\"Customer deleted successfully\"}");

    }

}
