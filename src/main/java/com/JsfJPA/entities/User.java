package com.JsfJPA.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = "User.all", query = "SELECT us FROM User us ORDER BY us.id"),
        @NamedQuery(name = "User.byUsername", query = "SELECT us FROM User us WHERE us.username = :username")
})
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "user_password", nullable = false)
    private String password;

    @Column(name = "user_group", nullable = false)
    private String group;

    public User() {
    }

    public User(String name, String username, String password, String group) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.group = group;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getGroup() {
        return group;
    }

    public Set<String> getGroups() {
        // Split the group string into individual groups and add them to a Set
        Set<String> groupSet = new HashSet<>();
        if (group != null && !group.isEmpty()) {
            String[] groupsArray = group.split(",");
            for (String group : groupsArray) {
                groupSet.add(group.trim());
            }
        }
        return groupSet;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}