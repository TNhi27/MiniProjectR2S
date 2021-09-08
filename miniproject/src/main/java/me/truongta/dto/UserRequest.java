package me.truongta.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.truongta.dao.RoleRepository;
import me.truongta.entity.Role;
import me.truongta.entity.Users;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRequest {

    String username;
    String fullname;
    String password;
    Date birthday;
    String address;
    String image;
    Boolean active = true;
    String role;

    public void loadByEntity(Users entity) {
        this.username = entity.getUsername();
        this.password = entity.getPassword();
        this.fullname = entity.getFullname();
        this.birthday = entity.getBirthday();
        this.address = entity.getAddress();
        this.image = entity.getImage();
        this.active = entity.getActive();
        this.role = entity.getRole().getId();

    }

}