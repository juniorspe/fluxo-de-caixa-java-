/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.Date;

public class Produto {

    private Date dataDeVenda;
    private String nome;
    private double valorEntrada;
    private int margenDeLucro;
    private int quantidade;
    private int quantidadeBanco;
    private String codigo;
    private double total;
    private String horario;

    private ArrayList<Produto> produto; 
    
    public void Produto(String n, double v, int m, int qtd, String c) {
        this.nome = n;
        this.valorEntrada = v;
        this.margenDeLucro = m;
        this.quantidade = qtd;
        this.codigo = c;
    }

    public Date getDataDeVenda() {
        return dataDeVenda;
    }

    public void setDataDeVenda(Date dataDeVenda) {
        this.dataDeVenda = dataDeVenda;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public ArrayList<Produto> getProduto() {
        return produto;
    }

    public void setProduto(ArrayList<Produto> produto) {
        this.produto = produto;
    }

    public int getQuantidadeBanco() {
        return quantidadeBanco;
    }

    public void setQuantidadeBanco(int quantidadeBanco) {
        this.quantidadeBanco = quantidadeBanco;
    }
    private double valorS;

    public double getValorS() {
        return valorS;
    }

    public void setValorS(double valorS) {
        this.valorS = valorS;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String n) {
        this.nome = n;
    }

    public double getValorEntrada() {
        return valorEntrada;
    }

    public void setValorEntrada(double v) {
        this.valorEntrada = v;
    }

    public int getMargenDeLucro() {
        return margenDeLucro;
    }

    public void setMargenDeLucro(int m) {
        this.margenDeLucro = m;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int qtd) {
        this.quantidade = qtd;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String c) {
        this.codigo = c;
    }

    public double calcularValor() {

        return (this.margenDeLucro * this.valorEntrada / 100) + this.valorEntrada;

    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {

        return getNome() + ':' + getCodigo() + ':' + getQuantidade() + ':' + getValorS();

    }

    public double mutiplicar(int quantidade) {

        return quantidade * getValorS();
    }

}
