package com.rest.impl.dao.group;

import com.rest.impl.dao.UserDao;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "groups")
public class GroupDao {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Integer id;
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "groups_users",
            joinColumns = {@JoinColumn(name = "group_id")},
            inverseJoinColumns = {@JoinColumn (name = "user_id")}
    )
    private List<UserDao> users;

    public GroupDao() {
    }

    public GroupDao(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserDao> getUsers() {
        return users;
    }

    public void setUsers(List<UserDao> users) {
        this.users = users;
    }

    public void addUser(UserDao user) { this.users.add(user); }
}
