package balaji.hibernate.file;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    @Lob
    private byte[] image;

}
