package view;

import dao.*;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Cliente;
import model.Localidade;
import model.Produto;
import model.Venda;

public class FormPdv extends javax.swing.JFrame
{
    private final ClienteDao clienteDao;
    private final ProdutoDao produtoDao;
    private final LocalidadeDao localidadeDao;
    private final VendaDao vendaDao;

    public FormPdv()
    {
        initComponents();

        clienteDao = new ClienteDao();
        produtoDao = new ProdutoDao();
        localidadeDao = new LocalidadeDao();
        vendaDao = new VendaDao();

        PopulateFields();
    }
    
    private void Sell()
    {
        Produto produto = GetSelectedProduct();
        Venda venda = new Venda();
        
        venda.setCodigoCliente(GetSelectedCustomer().getCodigo());
        venda.setCodigoLocal(GetSelectedPlace().getCodigo());
        venda.setCodigoProduto(produto.getCodigo());
        venda.setDataVenda(new Date());
        venda.setQuantidadeVenda(Integer.valueOf(txtQuantidade.getText()));
        venda.setValorTotal(GetTotalWithDiscount());
        
        UpdateProductStock(produto);
        
        vendaDao.Add(venda);
        produtoDao.UpdateStock(produto);
        PopulateFields();
        
        JOptionPane.showMessageDialog(this, "VENDA EFETUADA COM SUCESSO.");
    }
    
    private void UpdateProductStock(Produto produto)
    {
        produto.setQuantidade(produto.getQuantidade() - Integer.valueOf(txtQuantidade.getText()));
    }
    
    private void ValidateSell()
    {
        if (!ValidateFields())
        {
            JOptionPane.showMessageDialog(this, "PREENCHA OS CAMPOS CORRETAMENTE.");
            return;
        }
        
        if (!ValidateProductStock())
        {
            JOptionPane.showMessageDialog(this, 
                    "NÃO TEMOS ESTOQUE DESTE PRODUTO. \n QUANTIDADE ATUAL: " + GetSelectedProduct().getQuantidade());
            return;
        }
        
        Sell();
    }
    
    
    private boolean ValidateFields()
    {
        return !txtQuantidade.getText().isEmpty();
    }
    
    private void UpdateTotalDescription()
    {
        if (!txtQuantidade.getText().isEmpty())
        {
            txtTotal.setText(String.valueOf(GetTotalWithDiscount()));
        }else{
            txtTotal.setText("");
        }
    }
    
    private float GetTotalWithDiscount()
    {
        Produto selectedProduct = GetSelectedProduct();
        
        float total = Integer.valueOf(txtQuantidade.getText()) * selectedProduct.getPrecoUnitario();
        
        if (selectedProduct.getLocal().getCodigo() == GetSelectedPlace().getCodigo())
        {
            total = (float) (total - (total * 0.10));
        }
        
        return total;
    }

    private boolean ValidateProductStock()
    {
        return GetSelectedProduct().getQuantidade() >= Integer.parseInt(txtQuantidade.getText());
    }

    private Cliente GetSelectedCustomer()
    {
        return (Cliente) cboCliente.getModel().getSelectedItem();
    }
    
    private Produto GetSelectedProduct()
    {
        return (Produto) cboProduto.getModel().getSelectedItem();
    }
    
    private Localidade GetSelectedPlace()
    {
        return (Localidade) cboLocalidade.getModel().getSelectedItem();
    }

    private void UpdateProductDescription()
    {
        txtDescricaoProduto.setText(GetSelectedProduct().getDescricao());
    }

    private void PopulateFields()
    {
        cboCliente.setModel(new DefaultComboBoxModel(clienteDao.GetAll().toArray()));
        cboProduto.setModel(new DefaultComboBoxModel(produtoDao.GetAll().toArray()));
        cboLocalidade.setModel(new DefaultComboBoxModel(localidadeDao.GetAll().toArray()));

        UpdateProductDescription();
        
        PopulateTable();
    }
    
    private void PopulateTable()
    {
        List<Venda> vendas = vendaDao.GetAllByCustomerId(GetSelectedCustomer().getCodigo());
        tblVendas.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        DefaultTableModel model = (DefaultTableModel) tblVendas.getModel();
        model.setNumRows(0);
        
        vendas.forEach(sale -> model.addRow(new Object[]
        {
            sale.getProduto().getDescricao(),
            sale.getQuantidadeVenda(),
            sale.getProduto().getPrecoUnitario(),
            sale.getValorTotal()
        }));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cboCliente = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cboLocalidade = new javax.swing.JComboBox<>();
        btnVender = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        cboProduto = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtDescricaoProduto = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblVendas = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtQuantidade = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Caixa Livre");

        jLabel2.setText("Cliente Selecionado");

        cboCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("Local da Venda:");

        cboLocalidade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnVender.setText("Vender");
        btnVender.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnVenderActionPerformed(evt);
            }
        });

        btnExcluir.setText("Excluir");

        jLabel4.setText("Código do Produto:");

        cboProduto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboProduto.addItemListener(new java.awt.event.ItemListener()
        {
            public void itemStateChanged(java.awt.event.ItemEvent evt)
            {
                cboProdutoItemStateChanged(evt);
            }
        });

        jLabel5.setText("Descrição do Produto");

        tblVendas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String []
            {
                "Produto", "Quantidade", "Preço", "Valor Total"
            }
        ));
        jScrollPane1.setViewportView(tblVendas);

        jLabel6.setText("Total da Compra:");

        jButton3.setText("Fechar");

        jLabel7.setText("Quantidade:");

        txtQuantidade.addInputMethodListener(new java.awt.event.InputMethodListener()
        {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt)
            {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt)
            {
                txtQuantidadeInputMethodTextChanged(evt);
            }
        });
        txtQuantidade.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyReleased(java.awt.event.KeyEvent evt)
            {
                txtQuantidadeKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(329, 329, 329)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(321, 321, 321)
                        .addComponent(jButton3)))
                .addContainerGap(316, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboCliente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboProduto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(243, 243, 243)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cboLocalidade, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel7))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnExcluir)
                                            .addComponent(btnVender))))
                                .addGap(96, 96, 96))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtDescricaoProduto, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addGap(52, 52, 52)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboLocalidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnVender, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(cboProduto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnExcluir)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDescricaoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(jButton3)
                .addGap(171, 171, 171))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 631, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboProdutoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboProdutoItemStateChanged
        UpdateProductDescription();
    }//GEN-LAST:event_cboProdutoItemStateChanged

    private void btnVenderActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnVenderActionPerformed
    {//GEN-HEADEREND:event_btnVenderActionPerformed
        ValidateSell();
    }//GEN-LAST:event_btnVenderActionPerformed

    private void txtQuantidadeInputMethodTextChanged(java.awt.event.InputMethodEvent evt)//GEN-FIRST:event_txtQuantidadeInputMethodTextChanged
    {//GEN-HEADEREND:event_txtQuantidadeInputMethodTextChanged

    }//GEN-LAST:event_txtQuantidadeInputMethodTextChanged

    private void txtQuantidadeKeyReleased(java.awt.event.KeyEvent evt)//GEN-FIRST:event_txtQuantidadeKeyReleased
    {//GEN-HEADEREND:event_txtQuantidadeKeyReleased
        UpdateTotalDescription();
    }//GEN-LAST:event_txtQuantidadeKeyReleased

    public static void main(String args[])
    {
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(FormPdv.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() ->
        {
            new FormPdv().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnVender;
    private javax.swing.JComboBox<String> cboCliente;
    private javax.swing.JComboBox<String> cboLocalidade;
    private javax.swing.JComboBox<String> cboProduto;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblVendas;
    private javax.swing.JTextField txtDescricaoProduto;
    private javax.swing.JTextField txtQuantidade;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
