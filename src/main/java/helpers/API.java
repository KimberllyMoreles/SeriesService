package helpers;

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

@Path("/api")
public class API {
    
    protected String projectPath = "/home/diego/NetBeansProjects/api-images/src/main/webapp/";
    
    @POST
    @Path("/image")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean storeImage(@FormParam("image") String image) {
        try {
            String imageCode = image;
            String base64Image = imageCode.split(",")[1];
            byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image);
            BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageBytes));
            String imageNewName = System.currentTimeMillis() + ".jpg";
            File imageFile = new File(this.projectPath + "uploads/" + imageNewName);
            ImageIO.write(bufferedImage, "jpg", imageFile);
            return true;
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
    }

    @GET
    @Path("/image/{name}")
    @Produces("image/jpg")
    public Response getImage(@DefaultValue("0") @PathParam("name") String name) throws IOException {

        BufferedImage image = ImageIO.read(new File(this.projectPath + "uploads/" + name));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", baos);
        byte[] imageData = baos.toByteArray();
        return Response.ok(new ByteArrayInputStream(imageData)).build();

    }

}
