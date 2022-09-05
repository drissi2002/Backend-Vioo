package tn.wevioo.dto.modelDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.wevioo.models.Role;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {

        private Long id;
        private String username;
        private String email;
        private String password;
        private Set<Role> roles = new HashSet<>();
}
