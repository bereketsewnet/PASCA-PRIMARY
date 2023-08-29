package com.example.pasca_primary.Model;

public class UserTypeStore {

    int usertype;

    public UserTypeStore(int usertype) {
        this.usertype = usertype;

    }

    public UserTypeStore(){
    }

    public int getUsertype() {
        return usertype;
    }

    public void setUsertype(int usertype) {
        this.usertype = usertype;
    }
}
