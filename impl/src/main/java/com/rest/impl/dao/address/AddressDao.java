package com.rest.impl.dao.address;

import com.rest.impl.dao.UserDao;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class  AddressDao {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Integer id;
    private String address;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserDao user;

    public AddressDao() {
    }

    public AddressDao(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    //public UserDao getUser() {
    //    return user;
    //}

    public void setUser(UserDao user) {
        this.user = user;
    }
}
