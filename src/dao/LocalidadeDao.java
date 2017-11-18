package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Localidade;

public class LocalidadeDao
{
    public Localidade GetById(int Id)
    {
        Localidade localidade = new Localidade();
        String query = "SELECT * FROM Localidade WHERE Codigo = " + Id;

        try (DbConnection con = new DbConnection())
        {
            ResultSet rs = con.getStatement().executeQuery(query);

            if (rs.next())
            {
                localidade.setCodigo(rs.getInt("Codigo"));
                localidade.setNome(rs.getString("Nome"));
                localidade.setEndereco(rs.getString("Endereco"));
                localidade.setTelefone(rs.getString("Telefone"));
            }
        } catch (Exception ex)
        {
            Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return localidade;
    }

    public List<Localidade> GetAll()
    {
        List<Localidade> localidades = new ArrayList<>();
        String query = "SELECT * FROM Localidade";

        try (DbConnection con = new DbConnection())
        {
            ResultSet rs = con.getStatement().executeQuery(query);

            while (rs.next())
            {
                Localidade localidade = new Localidade();
                localidade.setCodigo(rs.getInt("Codigo"));
                localidade.setNome(rs.getString("Nome"));
                localidade.setEndereco(rs.getString("Endereco"));
                localidade.setTelefone(rs.getString("Telefone"));

                localidades.add(localidade);
            }

        } catch (Exception ex)
        {
            Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return localidades;
    }
}
