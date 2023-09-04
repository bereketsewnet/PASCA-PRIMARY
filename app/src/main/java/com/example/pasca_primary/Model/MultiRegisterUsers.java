package com.example.pasca_primary.Model;

public class MultiRegisterUsers {

   String name,email,studentClass,studentSex;
   Long password;
   String usertype;
   public MultiRegisterUsers(String name,String email,Long password,String studentClass,String studentSex, String userype){
       this.name = name;
       this.email = email;
       this.password = password;
       this.studentClass = studentClass;
       this.studentSex = studentSex;
       this.usertype = userype;
   }
   public MultiRegisterUsers(){

   }

   public String getName(){
       return name;
   }

   public void setName(String name){
       this.name = name;
   }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public Long getPassword(){
        return password;
    }

    public void setPassword(Long password){
        this.password = password;
    }

    public String getStudentClass(){
        return studentClass;
    }

    public void setStudentClass(String studentClass){
        this.studentClass = studentClass;
    }

    public String getStudentSex(){
        return studentSex;
    }

    public void setStudentSex(String studentSex){
        this.studentSex = studentSex;
    }

    public String getUsertype(){
       return usertype;
    }

    public void setUsertype(String usertype){
       this.usertype = usertype;
    }
}
