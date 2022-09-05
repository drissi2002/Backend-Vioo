package tn.wevioo.models.messages;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class ResponseDocument {
    private String id;
    private String name;
    private String url;
    private String type;
    private String analyse ;
    private Double score;
    private long size;
}
