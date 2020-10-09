package com.rest.impl.dao.post;

import com.rest.impl.dao.UserDao;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "posts")
public class PostDao {
    private @Id @GeneratedValue(strategy=GenerationType.IDENTITY) Integer id;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "b_id", nullable = false)
    private UserDao user;


    public PostDao() {
    }

    public PostDao(String description) {
        this.description = description;
    }

//    public Integer getId() {
//        return id;
//    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserDao getUser() {
        return user;
    }

    public void setUser(UserDao user) {
        this.user = user;
    }
}
