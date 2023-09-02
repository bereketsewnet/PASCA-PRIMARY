package com.example.pasca_primary.Model;

public class MultiRegisterUsers {

   String name,email,password,studentClass,studentSex;
   int usertype;
   public MultiRegisterUsers(String name,String email,String password,String studentClass,String studentSex, int userype){
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

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
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

    public int getUsertype(){
       return usertype;
    }

    public void setUsertype(int usertype){
       this.usertype = usertype;
    }
}
