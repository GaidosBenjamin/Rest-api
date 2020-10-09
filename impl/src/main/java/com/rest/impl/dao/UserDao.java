package com.rest.impl.dao;

import com.rest.impl.dao.address.AddressDao;
import com.rest.impl.dao.group.GroupDao;
import com.rest.impl.dao.post.PostDao;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserDao {
    private @Id
    @GeneratedValue
    Integer id;
    private String name;
    private String age;

    @OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
    private List<PostDao> posts;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private AddressDao address;

    @ManyToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<GroupDao> groups;

    public UserDao() {
    }

    public UserDao(String name, String age) {
        this.name = name;
        this.age = age;
    }

    /*public Integer getId() { return id; }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public List<PostDao> getPosts() {
        return posts;
    }

    public void setPosts(List<PostDao> posts) {
        this.posts = posts;
    }

    public AddressDao getAddress() {
        return address;
    }

    public void setAddress(AddressDao address) {
        this.address = address;
    }

    public List<GroupDao> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupDao> groups) {
        this.groups = groups;
    }

    public void addGroup(GroupDao group) { this.groups.add(group); }
}
