package tn.wevioo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="words")
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long wId;
    @Column(length = 500)
    private String contenu;
    @Column(length = 1000)
    private String url;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EPriority priority;
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private Document document;
    public Word(Long wId, String contenu) {
        this.wId = wId;
        this.contenu = contenu;
    }
}
