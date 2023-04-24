/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author junio
 */
public class User {
    private String user;
    private String password;

    public void User(String u, String p) {
        this.user = u;
        this.password = p;
    }

    


    public String getUser() {
        return user;
    }

    public void setUser(String u) {
        this.user = u;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String p) {
        this.password = p;
    }

   
}
