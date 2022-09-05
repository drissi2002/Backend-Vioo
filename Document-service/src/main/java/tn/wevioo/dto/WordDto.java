package tn.wevioo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.wevioo.models.Document;
import tn.wevioo.models.EPriority;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WordDto {
   private Long wId;
   private String contenu;
   private String url;
   private EPriority priority;
   private Document document;
}
