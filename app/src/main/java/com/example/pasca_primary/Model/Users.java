package com.example.pasca_primary.Model;

public class Users {


    String username, imageURL, id, status,type;

    public Users(String username, String imageURL, String id, String status, String type) {
        this.username = username;
        this.type = type;
        this.imageURL = imageURL;
        this.id = id;
        this.status = status;
    }

    public Users() {
    }


    public String getUsername() {
        return username;
    }
    public String getType() {
        return type;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setType(String type) {
        this.type = type;
    }

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
}
