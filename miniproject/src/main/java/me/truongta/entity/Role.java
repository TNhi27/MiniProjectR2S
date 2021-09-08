package me.truongta.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.truongta.dto.RoleRequest;

@Entity
@Table(name = "role")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    String id;
    String role;

    @OneToMany(mappedBy = "role")
    // @JsonManagedReference
    @JsonBackReference
    // @JsonIgnore
    List<Users> users;

   public void loadByDTO(RoleRequest dto) {
        this.id=dto.getId();
        this.role=dto.getRole();
   }
}