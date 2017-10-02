package web;
import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/UploadServlet")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
                 maxFileSize=1024*1024*10,      // 10MB
                 maxRequestSize=1024*1024*50)   // 50MB
public class UploadServlet extends HttpServlet {
    /**
     * Name of the directory where uploaded files will be saved, relative to
     * the web application directory.
     */
    public static final String SAVE_DIR = "/tmp/uploadFiles/";
    public static final String SRC_DIR = "source";
    public static final String IMG_DIR = "images";
    
     
    /**
     * handles file upload
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
         
        // creates the save directory if it does not exists
        File fileSaveDir = new File(UploadServlet.SAVE_DIR);
        File srcDir = new File(UploadServlet.SAVE_DIR + UploadServlet.SRC_DIR);
        if(!srcDir.exists()){
        	srcDir.mkdirs();
        }
        File imgDir = new File(UploadServlet.SAVE_DIR + UploadServlet.IMG_DIR + File.separator);
        if(!imgDir.exists()){
        	imgDir.mkdirs();
        }
        System.out.println(fileSaveDir.getAbsolutePath());
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
        String fileName = null;
        String filePath = null;
        for (Part part : request.getParts()) {
             fileName = extractFileName(part);
            // refines the fileName in case it is an absolute path
            fileName = new File(fileName).getName();
            filePath = UploadServlet.SAVE_DIR + UploadServlet.SRC_DIR + File.separator+ fileName;
            part.write(filePath);
        }
        request.setAttribute("message", "Upload has been done successfully!");
        String destination = UploadServlet.SAVE_DIR + UploadServlet.IMG_DIR + File.separator;
        File destFile = new File(destination);
        if(!destFile.exists())
        	destFile.mkdirs();
        GetImages.selectPDF(filePath, destination);
        System.out.println("Upload successful");
        getServletContext().getRequestDispatcher("/sample.html").forward(
                request, response);
    }
    /**
     * Extracts file name from HTTP header content-disposition
     */
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }
}