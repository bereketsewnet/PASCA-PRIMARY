package com.example.pasca_primary.Model;

public class ChangeProfilePass {
    String password;

    public ChangeProfilePass(String password) {
        this.password = password;

    }

    public ChangeProfilePass(){
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
