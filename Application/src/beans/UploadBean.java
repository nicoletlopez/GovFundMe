package beans;
import java.io.File;
import javax.servlet.http.Part;
public class UploadBean {
    public String doUpload(Part image) {
        String path= System.getProperty("user.home")+File.separator+"upload"+File.separator;
        try {

            File f = new File(path + getSubmittedFileName(image));
            f.createNewFile();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return getSubmittedFileName(image);
    }
    private static String getSubmittedFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }
}