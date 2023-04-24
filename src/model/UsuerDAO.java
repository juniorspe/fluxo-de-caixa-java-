
package model;


import java.sql.PreparedStatement;
import static connection.ConnectionFactory.getConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

/**
 *
 * @author junio
 */
public class UsuerDAO {
    
    Connection con = getConnection();
    PreparedStatement stmt = null;
    
    public ResultSet altenticarUsuario(User u) {
        try {
            stmt = con.prepareStatement("select * from user where user = ? and password = ? ");
            stmt.setString(1, u.getUser());
            stmt.setString(2, u.getPassword());            
            ResultSet rs = stmt.executeQuery();
            
            JOptionPane.showMessageDialog(null, "Logado com sucesso!");
            return rs;
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Usuario:" + ex);
            return null;
        }
        
    }
    
}
