/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banksystem;

/**
 *
 * @author Acer
 */
public class User {
    
    public static String username;
    public static String password;

    public User(String username, String password){
        
        this.username = username;
        this.password = password;

    }

    public static
    String getUsername() {
        return username;
    }

    public static
    String getPassword() {
        return password;
    }

    public static
    void setUsername(String username) {
        User.username = username;
    }

    public static
    void setPassword(String password) {
        User.password = password;
    }

    

}
