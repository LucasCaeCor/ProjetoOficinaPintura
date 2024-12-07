/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.bean;

/**
 *
 * @author lucas
 */

public class Cliente {
    private int id;
    private String nome;
    private String telefone;
    private String email;
    private String endereco;

    // Construtor sem parâmetros (necessário para criar um objeto sem dados)
    public Cliente() {
        // Você pode inicializar os atributos com valores padrão, se necessário
        this.id = 0; // Valor padrão para o id
        this.nome = "";
        this.telefone = "";
        this.email = "";
        this.endereco = "";
    }

    // Construtor com todos os campos (incluindo o ID)
    public Cliente(int id, String nome, String telefone, String email, String endereco) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
    }

    // Construtor sem o ID (útil para novos clientes, onde o ID é gerado pelo banco de dados)
    public Cliente(String nome, String telefone, String email, String endereco) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
    }

    // Getters e setters
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Cliente{id=" + id + ", nome='" + nome + "', telefone='" + telefone + "', email='" + email + "', endereco='" + endereco + "'}";
    }
}