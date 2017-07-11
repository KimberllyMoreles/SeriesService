package controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class API {
    
    protected String projectPath = "/home/aluno/NetBeansProjects/SerieService/src/main/webapp/";
    
   
    public String storeImage(@FormParam("image") String image) {
        try {
            String imageCode = image;
            String base64Image = imageCode.split(",")[1];
            byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image);
            BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageBytes));
            String imageNewName = System.currentTimeMillis() + ".jpg";
            File imageFile = new File(this.projectPath + "uploads/" + imageNewName);
            ImageIO.write(bufferedImage, "jpg", imageFile);
            return imageNewName;
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            return "";
        }
    }
}
