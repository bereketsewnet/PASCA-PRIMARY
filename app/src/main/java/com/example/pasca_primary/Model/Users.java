package com.example.pasca_primary.Model;

public class Users {

    public Users() {

    }

    public Users(String username, String imageURL, String id, String status, int usertype , String student_class,String email,String password) {
        this.username = username;
        this.student_class = student_class;
        this.imageURL = imageURL;
        this.id = id;
        this.status = status;
        this.usertype = usertype;
        this.email = email;
        this.password = password;
    }

    private String username,imageURL,id,status,student_class,email,password;
    private int usertype;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getStudent_class(){return student_class;}
    public void setStudent_class(String student_class){this.student_class = student_class;}

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public int getUsertype() {
        return usertype;
    }

    public void setUsertype(int usertype) {
        this.usertype = usertype;
    }
}
