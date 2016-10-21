package andrey019.util;


import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ThumbnailTest {

    public static void main(String[] args) {
        //BufferedImage bufferedImage = new BufferedImage();
//        MultipartFile multipartFile;
//        multipartFile.getBytes();
        byte[] arr = new byte[10];
        InputStream inputStream = new ByteArrayInputStream(arr);
        try {
            Thumbnails.of(inputStream)
                    .size(112, 75)
                    .outputFormat("jpg")
                    .toFile("/file.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
