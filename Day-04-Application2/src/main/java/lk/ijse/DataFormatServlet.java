package lk.ijse;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
@WebServlet(urlPatterns = "/data-formats/*")
@MultipartConfig
public class DataFormatServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        //check the content type
//        String contentType = req.getContentType();
//        System.out.println("contentType:"+contentType);

//        //1.query parameters
//        System.out.println(req.getParameter("id"));
//        System.out.println(req.getParameter("name"));

//        //2.x-www-from-urlencoded
//        System.out.println(req.getParameter("id"));
//        System.out.println(req.getParameter("name"));

        //3.path variables
        System.out.println(req.getPathInfo());
        System.out.println(req.getPathInfo().substring(1));


//        //4.form-data
//        System.out.println(req.getParameter("id"));
//        System.out.println(req.getParameter("name"));
//        //4.2 image name share
//        Part image = req.getPart("image");
//        System.out.println(image.getSubmittedFileName());
//
//        //4.3 create file in resourses
//        File file = new File("C:\\Users\\USER\\Documents\\2nd sem Projects\\AAD\\javaEE\\JavaEE-Theory\\Day-04-Application2\\src\\main\\resources\\images\\");
//        if(!file.exists()){
//            file.mkdir();
//
//        }
//      //save the file
//        String path = file.getAbsolutePath()+File.separator+image.getSubmittedFileName();
//        image.write(path);
    }
}
