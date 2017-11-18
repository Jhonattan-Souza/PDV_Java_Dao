package model;

import java.util.Date;

public class Venda {
    private int CodigoCliente;
    private int CodigoProduto;
    private int CodigoLocal;
    private int QuantidadeVenda;
    private float ValorTotal;
    private Date DataVenda;
    private Produto Produto;
    
    public int getCodigoCliente() {
        return CodigoCliente;
    }

    public void setCodigoCliente(int CodigoCliente) {
        this.CodigoCliente = CodigoCliente;
    }

    public int getCodigoProduto() {
        return CodigoProduto;
    }

    public void setCodigoProduto(int CodigoProduto) {
        this.CodigoProduto = CodigoProduto;
    }

    public int getCodigoLocal() {
        return CodigoLocal;
    }

    public void setCodigoLocal(int CodigoLocal) {
        this.CodigoLocal = CodigoLocal;
    }

    public int getQuantidadeVenda() {
        return QuantidadeVenda;
    }

    public void setQuantidadeVenda(int QuantidadeVenda) {
        this.QuantidadeVenda = QuantidadeVenda;
    }

    public float getValorTotal() {
        return ValorTotal;
    }

    public void setValorTotal(float ValorTotal) {
        this.ValorTotal = ValorTotal;
    }

    public Date getDataVenda() {
        return DataVenda;
    }

    public void setDataVenda(Date DataVenda) {
        this.DataVenda = DataVenda;
    }

    public Produto getProduto()
    {
        return Produto;
    }

    public void setProduto(Produto Produto)
    {
        this.Produto = Produto;
    }
}