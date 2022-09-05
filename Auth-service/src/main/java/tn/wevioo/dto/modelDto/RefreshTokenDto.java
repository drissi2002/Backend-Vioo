package tn.wevioo.dto.modelDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.wevioo.models.User;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RefreshTokenDto {

    private long id;
    private User user;
    private String token;
    private Instant expiryDate;
}
