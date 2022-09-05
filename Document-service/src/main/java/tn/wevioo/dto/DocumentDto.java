package tn.wevioo.dto;

import lombok.*;

import tn.wevioo.models.Word;
import java.util.LinkedHashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Data
public class DocumentDto {
    private String id;
    private String name;
    private String type;
    private String analyse;
    private Double score;
    private byte[] data;
    private Set<Word> words= new LinkedHashSet<>();
}
