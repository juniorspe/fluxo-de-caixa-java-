/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Produto;
import model.ProdutoDAO;

/**
 *
 * @author junio
 */
public class ControllerProduto {

    private ProdutoDAO pd = new ProdutoDAO();

    public void cadastrarProduto(Produto p) {
        java.lang.String codigo = p.getCodigo();
        java.lang.String nome = p.getNome();

        if (codigo.length() == 0) {
            JOptionPane.showMessageDialog(null, "Campo vasio!");
        } else if (nome.length() == 0) {
            JOptionPane.showMessageDialog(null, "Campo vasio!");

        } else {
            ProdutoDAO ptd = new ProdutoDAO();
            ptd.create(p);
        }
    }

    /**
     *
     * @param p
     * @return
     * @throws SQLException
     */
    public Produto bucarProduto(String codigo) throws SQLException {
        return pd.buscarProduto(codigo);
    }

    public double editarQtd(Produto p) {
        p.setQuantidade(0);
        double valo = p.getQuantidade() * p.getValorS();
        return valo;
    }

    /**
     *
     * @param evt
     * @return
     */
    public List<Produto> listarProdutos(ActionEvent evt) {

        return pd.listar(evt);
    }

    public void subtrairDoBanco(List<Produto> produtos) {
        ProdutoDAO dao = new ProdutoDAO();
        dao.subtrairDoBanco(produtos);

    }

    public boolean update(Produto produto) {
        ProdutoDAO dao = new ProdutoDAO();

        return dao.updateData(produto);
    }

    public void cadastrarVenda(ArrayList<Produto> produto) throws Exception{
        ProdutoDAO produtoDao = new ProdutoDAO();
      
        produtoDao.cadastrarVendas(produto);
      
    }

    /**
     *
     * @param evt
     * @return
     */
    public ArrayList<Produto> vendasRendimentos(ActionEvent evt) {
        ProdutoDAO produtoDao = new ProdutoDAO();
        
        return produtoDao.vendaRendimento(evt);
    }

   
    public void fexamento(ActionEvent evt) {
        ProdutoDAO produtoDao = new ProdutoDAO();
        produtoDao.fexamento(evt);
    }

 
    

  

}
