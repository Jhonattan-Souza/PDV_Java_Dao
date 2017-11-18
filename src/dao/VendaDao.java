package dao;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Venda;

public class VendaDao {
    private final ProdutoDao produtoDao;
    
    public VendaDao()
    {
        produtoDao = new ProdutoDao();
    }
    
    public void Add(Venda venda) {
        try(DbConnection con = new DbConnection())
        {
            String query = "INSERT INTO Venda (Codigo_Cliente, Codigo_Produto, Codigo_Local, Quantidade_Venda, Valor_Total, Data_Venda) ";
            query += String.format("VALUES(%s, %s, %s, %s, %s, '%s')",
                    venda.getCodigoCliente(),
                    venda.getCodigoProduto(),
                    venda.getCodigoLocal(), 
                    venda.getQuantidadeVenda(), 
                    venda.getValorTotal(), 
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(venda.getDataVenda()));
            
            con.getStatement().executeUpdate(query);
        } catch (Exception ex) {
            Logger.getLogger(VendaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Venda> GetAllByCustomerId(int customerId)
    {
        List<Venda> vendas = new ArrayList<>();
        String query = 
            "SELECT Codigo_Produto, Quantidade_Venda, Valor_Total FROM Venda WHERE Codigo_Cliente = " + customerId;
        
        try(DbConnection con = new DbConnection())
        {
            ResultSet rs = con.getStatement().executeQuery(query);
            
            while (rs.next())
            {
                Venda venda = new Venda();
                venda.setCodigoProduto(rs.getInt("Codigo_Produto"));
                venda.setQuantidadeVenda(rs.getInt("Quantidade_Venda"));
                venda.setValorTotal(rs.getFloat("Valor_Total"));
                
                venda.setProduto(produtoDao.GetById(rs.getInt("Codigo_Produto")));
                
                vendas.add(venda);
            }
            
        } catch (Exception ex) {
            Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return vendas;
    }
}