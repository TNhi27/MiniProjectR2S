package me.truongta.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.truongta.dao.RoleRepository;
import me.truongta.dto.UserRequest;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id
    String username;
    String password;
    String fullname;

    @Temporal(TemporalType.DATE)
    Date birthday;
    String address;
    String image;
    Boolean active = true;

    @ManyToOne
    @JoinColumn(name = "role")
    // @JsonBackReference
    @JsonManagedReference
    // @JsonIgnore
    Role role;

    public void loadByDTO(UserRequest dto) {

        this.username = dto.getUsername();
        this.password = dto.getPassword();
        this.fullname = dto.getFullname();
        this.birthday = dto.getBirthday();
        this.address = dto.getAddress();
        this.image = dto.getImage();
        this.active = dto.getActive();

    }

}