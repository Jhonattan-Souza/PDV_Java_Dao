package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Produto;

public class ProdutoDao {
    private final LocalidadeDao localidadeDao;
    
    public ProdutoDao()
    {
        localidadeDao = new LocalidadeDao();
    }
    
    public void UpdateStock(Produto produto)
    {
        try(DbConnection con = new DbConnection())
        {
            String query = "UPDATE Produto SET Quantidade_Em_Estoque = %s WHERE Codigo = %s";
            query = String.format(query, produto.getQuantidade(), produto.getCodigo());
            
            con.getStatement().executeUpdate(query);
        } catch (Exception ex) {
            Logger.getLogger(VendaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Produto GetById(int Id)
    {
        Produto produto = new Produto();
        String query = "SELECT * FROM Produto WHERE Codigo = " + Id;

        try (DbConnection con = new DbConnection())
        {
            ResultSet rs = con.getStatement().executeQuery(query);

            if (rs.next())
            {
                produto.setCodigo(rs.getInt("Codigo"));
                produto.setCodigoLocal(rs.getInt("Codigo_Local"));
                produto.setDescricao(rs.getString("Descricao"));
                produto.setPrecoUnitario(rs.getFloat("Preco_Unitario"));
                produto.setQuantidade(rs.getInt("Quantidade_Em_Estoque"));
                
                produto.setLocal(localidadeDao.GetById(rs.getInt("Codigo_Local")));
            }
        } catch (Exception ex)
        {
            Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return produto;
    }
    
    public List<Produto> GetAll() 
    {
        List<Produto> produtos = new ArrayList<>();
        String query = "SELECT * FROM Produto";
        
        try(DbConnection con = new DbConnection())
        {
            ResultSet rs = con.getStatement().executeQuery(query);
            
            while (rs.next())
            {
                Produto produto = new Produto();
                produto.setCodigo(rs.getInt("Codigo"));
                produto.setCodigoLocal(rs.getInt("Codigo_Local"));
                produto.setDescricao(rs.getString("Descricao"));
                produto.setPrecoUnitario(rs.getFloat("Preco_Unitario"));
                produto.setQuantidade(rs.getInt("Quantidade_Em_Estoque"));
                
                produto.setLocal(localidadeDao.GetById(rs.getInt("Codigo_Local")));
                
                produtos.add(produto);
            }
            
        } catch (Exception ex) {
            Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return produtos;
    }
}