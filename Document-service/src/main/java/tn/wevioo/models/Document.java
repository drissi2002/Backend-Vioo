package tn.wevioo.models;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="documents")
public class Document {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String name;
    @Column(length = 5000)
    private String type;
    private String analyse ;
    private Double score;
    @Lob
    private byte[] data;
    @OneToMany(mappedBy = "document",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Word> words= new LinkedHashSet<>();

    public Document(String name, String type,String analyse, Double score,byte[] data) {
        this.name = name;
        this.type = type;
        this.analyse = analyse ;
        this.score = score ;
        this.data = data;
    }

    public Document(String name, String type,String analyse, Double score) {
    }
    public Document(String name, String type,String analyse, Double score ,Set<Word> words) {
    }
}

/**
In the code above, data is annotated by @Lob annotation.
LOB is datatype for storing large object data.
There’re two kinds of LOB: BLOB and CLOB:

- BLOB is for storing binary data
- CLOB is for storing text data
*/