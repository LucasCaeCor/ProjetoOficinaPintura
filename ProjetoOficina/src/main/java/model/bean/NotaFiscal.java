package model.bean;

import java.time.LocalDate;
import java.util.ArrayList;

public class NotaFiscal {
    private int id;
    private Cliente cliente;
    private LocalDate dataEmissao;
    private double valorTotal;
    private ArrayList<Servico> servicos; // Adicionar os serviços vinculados à nota

    public NotaFiscal(int id, Cliente cliente, LocalDate dataEmissao, double valorTotal) {
        this.id = id;
        this.cliente = cliente;
        this.dataEmissao = dataEmissao;
        this.valorTotal = valorTotal;
        this.servicos = new ArrayList<>(); // Inicializa a lista de serviços
    }

    // Getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public ArrayList<Servico> getServicos() {
        return servicos;
    }

    public void setServicos(ArrayList<Servico> servicos) {
        this.servicos = servicos;
    }

    // Adicionar um serviço
    public void adicionarServico(Servico servico) {
        this.servicos.add(servico);
    }

    @Override
    public String toString() {
        return "NotaFiscal{" +
                "id=" + id +
                ", cliente=" + cliente +
                ", dataEmissao=" + dataEmissao +
                ", valorTotal=" + valorTotal +
                ", servicos=" + servicos +
                '}';
    }
}
