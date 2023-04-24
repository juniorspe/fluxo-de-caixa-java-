/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.User;
import model.UsuerDAO;
import view.PainelPrincipal;
/**
 *
 * @author junio
 */
public class ControllerUser  {

    /**
     *
     * @param c
     */
    public  void validarUsuario( User c) throws SQLException{
        UsuerDAO DAO = new UsuerDAO();
        String user = c.getUser();
        String password = c.getPassword();
        if(user.length() == 0){
             JOptionPane.showMessageDialog(null, "Favor preencher os campos ");
        }else if(password.length() == 0){
              JOptionPane.showMessageDialog(null, "Favor preencher os campos");
        }else{
             
             ResultSet rs = DAO.altenticarUsuario(c);
            if(rs.next()){
               
            PainelPrincipal panel = new PainelPrincipal();
            panel.setVisible(true);
            
            }else{
            JOptionPane.showMessageDialog(null, "ja nao " +rs);
            }
            
        }             
    } 
    

    
}
