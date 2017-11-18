package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cliente;

public class ClienteDao {
    public List<Cliente> GetAll()
    {
        List<Cliente> clientes = new ArrayList<>();
        String query = "SELECT * FROM Cliente";
        
        try(DbConnection con = new DbConnection())
        {
            ResultSet rs = con.getStatement().executeQuery(query);
            
            while (rs.next())
            {
                Cliente cliente = new Cliente();
                cliente.setCodigo(rs.getInt("Codigo"));
                cliente.setNome(rs.getString("Nome"));
                cliente.setBonus(rs.getInt("Bonus"));
                cliente.setPerfil(rs.getString("Perfil"));
                cliente.setStatus(rs.getString("Status"));
                
                clientes.add(cliente);
            }
            
        } catch (Exception ex) {
            Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return clientes;
    }
}