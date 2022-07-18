package competition.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
public class User extends AuditModel implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "userSeq", sequenceName = "user_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "userSeq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false, columnDefinition = "varchar(50)")
    private String username;

    @Column(name = "password", nullable = false, columnDefinition = "varchar(50)")
    private String password;

    @Column(name = "name", nullable = false, columnDefinition = "varchar(255)")
    private String name;

    @ElementCollection
    @CollectionTable(name = "user_permission", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "permission", nullable = false)
    Set<String> permissions = new HashSet<>();


    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }
}
