package beans;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
public class UploadBean {
    public String doUpload(Part image) {
        String path= System.getProperty("user.home")+File.separator+"upload"+File.separator;
        try {
            InputStream in = image.getInputStream();

            File f = new File(path + getSubmittedFileName(image));
            f.createNewFile();
            FileOutputStream out = new FileOutputStream(f);

            byte[] buffer = new byte[1024];
            int length;

            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
            out.close();
            in.close();
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("path", f.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return getSubmittedFileName(image);
    }

    public String doUploadProfilePic(Part image) {
        String path= System.getProperty("user.home")+File.separator+"upload/profile"+File.separator;
        try {
            InputStream in = image.getInputStream();

            File f = new File(path + getSubmittedFileName(image));
            f.createNewFile();
            FileOutputStream out = new FileOutputStream(f);

            byte[] buffer = new byte[1024];
            int length;

            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
            out.close();
            in.close();
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("path", f.getAbsolutePath());
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