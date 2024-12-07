package model.bean;

public class Servico {
    private int id;
    private String nome;
    private String descricao;
    private double preco;
    private int funcionariosId;  // Novo campo

    // Construtores, getters e setters

    public Servico(int id, String nome, String descricao, double preco, int funcionariosId) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.funcionariosId = funcionariosId;
    }

    public Servico(int aInt, String string, String string0, double aDouble) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getFuncionariosId() {
        return funcionariosId;
    }

    public void setFuncionariosId(int funcionariosId) {
        this.funcionariosId = funcionariosId;
    }
}
