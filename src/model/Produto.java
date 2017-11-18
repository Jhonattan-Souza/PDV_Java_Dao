package model;

public class Produto {
    private int Codigo;
    private int CodigoLocal;
    private Localidade Local;
    private String Descricao;
    private int Quantidade;
    private float PrecoUnitario;
    
    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int Codigo) {
        this.Codigo = Codigo;
    }

    public int getCodigoLocal() {
        return CodigoLocal;
    }

    public void setCodigoLocal(int CodigoLocal) {
        this.CodigoLocal = CodigoLocal;
    }

    public Localidade getLocal() {
        return Local;
    }

    public void setLocal(Localidade Local) {
        this.Local = Local;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }

    public int getQuantidade() {
        return Quantidade;
    }

    public void setQuantidade(int Quantidade) {
        this.Quantidade = Quantidade;
    }

    public float getPrecoUnitario() {
        return PrecoUnitario;
    }

    public void setPrecoUnitario(float PrecoUnitario) {
        this.PrecoUnitario = PrecoUnitario;
    }
    
    @Override
    public String toString() {
        return String.valueOf(getCodigo()) + " - " + getDescricao();
    }
}