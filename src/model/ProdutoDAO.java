package model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import static connection.ConnectionFactory.closeConnection;
import static connection.ConnectionFactory.getConnection;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Logger.getLogger;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 *
 * @author junio
 */
public class ProdutoDAO {   //SELECT id, nome, quantidade, valorS,  codigoBarras FROM produto WHERE codigoBarras LIKE  ?

    private ArrayList<Produto> produtos;
    private final String insert = "INSERT INTO produto(nome,valorEntrada,margemDeLucro,quantidade,valorS,codigoBarras)VALUES(?,?,?,?,?,?)";

    public void create(Produto p) {
        Connection con = getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(insert);
            stmt.setString(1, p.getNome());
            stmt.setDouble(2, p.getValorEntrada());
            stmt.setInt(3, p.getMargenDeLucro());
            stmt.setInt(4, p.getQuantidade());
            stmt.setDouble(5, p.calcularValor());
            stmt.setString(6, p.getCodigo());
            stmt.executeUpdate();
            showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            getLogger(ProdutoDAO.class.getName()).log(SEVERE, null, ex);
        } finally {
            closeConnection(con, stmt);
        }
    }
    /**
     *
     * @param p
     * @return
     * @throws SQLException
     */
    public Produto buscarProduto(String codigo) throws SQLException {
        Connection con = getConnection();
        PreparedStatement stmt;
        stmt = null;
        ResultSet rs = null;
        try {
            Produto produto = null;
            stmt = con.prepareStatement(" SELECT * FROM produto WHERE codigoBarras = ?");
            stmt.setString(1, codigo);
            rs = stmt.executeQuery();
            produtos = null;
            while (rs.next()) {
                produto = new Produto();
                produto.setNome(rs.getString("nome"));
                produto.setCodigo(rs.getString("codigoBarras"));
                produto.setQuantidadeBanco(rs.getInt("quantidade"));
                produto.setValorS(rs.getDouble("valorS"));
                produto.setValorEntrada(rs.getDouble("valorEntrada"));
                if (rs.getInt("quantidade") <= 0) {
                    produto = null;
                    JOptionPane.showMessageDialog(null, "Não temos o produto na platileira!");
                } else if (rs.getInt("quantidade") <= 5) {
                    JOptionPane.showMessageDialog(null, "Adicionar a lista de compras!");
                }
            }
            return produto;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "erro:" + ex);
            return null;
        } finally {
            closeConnection(con, stmt);
        }
    }

    /**
     *
     * @param evt
     * @return
     */
    public List<Produto> listar(ActionEvent evt) {
        List<Produto> produtos = new ArrayList<>();
        Connection con = getConnection();
        PreparedStatement stmt;
        stmt = null;
        ResultSet rs = null;
        try {
            Produto produto = null;
            stmt = con.prepareStatement(" SELECT * FROM produto LIMIT 20 ");
            rs = stmt.executeQuery();
            //JOptionPane.showMessageDialog(null, "deu certo!");
            while (rs.next()) {
                produto = new Produto();
                produto.setNome(rs.getString("nome"));
                produto.setCodigo(rs.getString("codigoBarras"));
                produto.setQuantidadeBanco(rs.getInt("quantidade"));
                produto.setValorS(rs.getDouble("valorS"));
                produtos.add(produto);
            }
            return produtos;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "erro:" + ex);
            return null;
        } finally {
            closeConnection(con, stmt);
        }
    }

    public void subtrairDoBanco(List<Produto> produtos) {
        Connection con = getConnection();
        PreparedStatement stmt;
        stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE produto set  quantidade = ? WHERE codigoBarras = ?");
            for (Produto produto : produtos) {
                stmt.setString(2, produto.getCodigo());
                stmt.setInt(1, produto.getQuantidadeBanco() - produto.getQuantidade());
                //JOptionPane.showMessageDialog(null, "deu certo!");
                stmt.executeUpdate();
            }
            JOptionPane.showMessageDialog(null, "deu certo!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "erro:" + ex);
        } finally {
            closeConnection(con, stmt);
        }
    }
    public boolean updateData(Produto produto) {
        Connection con = getConnection();
        PreparedStatement stmt = null;
        //stmt.setString(7, produto.getCodigo());
        try {
            stmt = con.prepareStatement("UPDATE produto set nome = ?, valorEntrada = ?, margemDeLucro = ?, quantidade = ?, valorS = ?, codigoBarras = ?  WHERE codigoBarras = ? ");
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getValorEntrada());
            stmt.setInt(3, produto.getMargenDeLucro());
            stmt.setInt(4, produto.getQuantidade());
            stmt.setDouble(5, produto.calcularValor());
            stmt.setString(6, produto.getCodigo());
            stmt.setString(7, produto.getCodigo());
            stmt.executeUpdate();
            showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            getLogger(ProdutoDAO.class.getName()).log(SEVERE, null, ex);
        } finally {
            closeConnection(con, stmt);
        }
        return true;
    }
    /**
     *
     * @param produtos
     */
    public void cadastrarVendas(ArrayList<Produto> produtos) throws Exception{
        Connection con = getConnection();
        PreparedStatement stmt = null;
        try {
            for (Produto produto : produtos) {
                stmt = con.prepareStatement("INSERT INTO vendas(nome,valorEntrada,quantidade,valorS,codigoBarras,horas,total)VALUES(?,?,?,?,?,?,?)");
                stmt.setString(1, produto.getNome());
                stmt.setDouble(2, produto.getValorEntrada());
                stmt.setInt(3, produto.getQuantidade());
                stmt.setDouble(4, produto.getValorS());
                stmt.setString(5, produto.getCodigo());
                stmt.setString(6, produto.getHorario()+ "");
                stmt.setDouble(7, produto.getTotal());
                stmt.executeUpdate();
            }
            showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            getLogger(ProdutoDAO.class.getName()).log(SEVERE, null, ex);
        } finally {
            closeConnection(con, stmt);
        }
    }
    public ArrayList<Produto> vendaRendimento(ActionEvent evt) {
         List<Produto> produtos = new ArrayList<>();
        Connection con = getConnection();
        PreparedStatement stmt;
        stmt = null;
        ResultSet rs = null;
        try {
            Produto produto = null;
            stmt = con.prepareStatement(" SELECT * FROM vendas");
            rs = stmt.executeQuery();
            //JOptionPane.showMessageDialog(null, "deu certo!");
            while (rs.next()) {

                produto = new Produto();
                produto.setValorEntrada(rs.getDouble("valorEntrada"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setValorS(rs.getDouble("valorS"));
                produto.setHorario(rs.getString("horas"));
                produtos.add(produto);
            }
            return (ArrayList<Produto>) produtos;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "erro:" + ex);
            return null;
        } finally {
            closeConnection(con, stmt);
        }
    }
    public void fexamento(ActionEvent evt) {
        Connection con = getConnection();
        PreparedStatement stmt;
        stmt = null;
        ResultSet v = null;
        try {
             stmt = con.prepareStatement(" truncate table mercado.vendas");
             stmt.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }finally{
            closeConnection(con, stmt, v);
        }
    }

}
