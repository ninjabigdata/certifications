package balaji.hibernate.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Paths;

@Service
public class ImageService {

    @Autowired
    private ImageRepo repo;

    public Image save(Image image) {
        return repo.save(image);
    }

    @PostConstruct
    public void test() throws Exception {
        System.out.println("From ImageService - BLOB");

        Image image = new Image();
        image.setName("image.jpg");

        byte[] content = null;

        try (InputStream fileInputStream = this.getClass().getClassLoader().getResourceAsStream("image.jpg")) {
            content = new byte[fileInputStream.available()];
            fileInputStream.read(content);
        }

        image.setImage(content);

        System.out.println("Image saved is " + save(image));
    }

}
