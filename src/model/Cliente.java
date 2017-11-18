package model;

public class Cliente 
{
    private int Codigo;
    private String Nome;
    private int Bonus;
    private String Perfil;
    private String Status;

    public int getCodigo() {
        return Codigo;
    }
    
    public void setCodigo(int Codigo) {
        this.Codigo = Codigo;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public int getBonus() {
        return Bonus;
    }

    public void setBonus(int Bonus) {
        this.Bonus = Bonus;
    }

    public String getPerfil() {
        return Perfil;
    }

    public void setPerfil(String Perfil) {
        this.Perfil = Perfil;
    }

    public String getStatus() {
        return Status;
    }
    
    public void setStatus(String Status) {
        this.Status = Status;
    }

    @Override
    public String toString() {
        return String.valueOf(getCodigo()) + " - " + getNome();
    }
}