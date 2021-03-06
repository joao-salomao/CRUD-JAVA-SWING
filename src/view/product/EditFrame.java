/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.product;

import dao.CategorieDAO;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import dao.ProductDAO;
import models.Categorie;
import models.Product;


/**
 *
 * @author João Salomão
 */
public class EditFrame extends javax.swing.JFrame {
    private ArrayList<Product> products;
    private ArrayList<Categorie> categories;
    private Product product;
    private final ListTable table;
    private int index;
    /**
     * Creates new form Edit
     * @param products
     * @param index
     * @param table
     */
    public EditFrame(ArrayList<Product> products, int index, ListTable table) {
        initComponents();
        
        this.setItemListenerToggleButton();
        this.categories = CategorieDAO.index();
        
        this.categories.forEach(c -> {
            this.categoriesComboBox.addItem(c.getName());
        });
        
        this.table = table;
        this.products = products;
        this.index = index;
        
        if (!(index == -1)) {
            this.product = products.get(index);
            this.formLabel.setText("Editar Produto");
            this.nameField.setText(this.product.getDescription());
            this.codeField.setText(this.product.getCode());
            
            if (!this.product.getState()) {
                this.stateButton.setText(this.product.getStateString());
                this.stateButton.setSelected(false);
            }
            this.save.setText("Atualizar");
        } else {
            this.delete.setVisible(false);
            this.formLabel.setText("Cadastrar Novo Produto");
        }
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    private Categorie getSelectedCategorie() {
        int index = this.categoriesComboBox.getSelectedIndex();
        return this.categories.get(index);
    }
    
    private boolean createProduct() {
        String description = this.nameField.getText();
        String code = this.codeField.getText();
        double price = Double.parseDouble(this.priceField.getText());
        boolean state = this.stateButton.getText().equalsIgnoreCase("Ativo");
        Categorie categorie = this.getSelectedCategorie();
        
        Product p = new Product(code, description, price, state, categorie);

        boolean result = ProductDAO.add(p);

        if (result) {
            this.product = p;
            this.products.add(this.product);
            this.table.addProduct(p);
            this.delete.setVisible(true);
            this.save.setText("Atualizar");
        }
        return result;
    }
    
    private boolean updateProduct() {
        String description = this.nameField.getText();
        String code = this.codeField.getText();
        double price = Double.parseDouble(this.priceField.getText());
        boolean state = this.stateButton.getText().equalsIgnoreCase("Ativo");
        Categorie categorie = this.getSelectedCategorie();
        
        this.product.setCode(code);
        this.product.setDescription(description);
        this.product.setPrice(price);
        this.product.setState(state);
        this.product.setCategorie(categorie);
        
        boolean result = ProductDAO.update(product);
        if (result) {
            this.table.updateRow(this.product, this.index);
        }
        return result;
    }
    
    private boolean validateFields() {
        boolean nameIsEmpty = this.nameField.getText().isEmpty();
        boolean codeIsEmpty = this.codeField.getText().isEmpty();
        
        String message;
        if (nameIsEmpty && codeIsEmpty) {
            message = "Os campos NOME e CÓDIGO são obrigatórios";
            JOptionPane.showMessageDialog (this, message);
        } else if (nameIsEmpty) {
            message = "Os campo NOME é obrigatório";
            JOptionPane.showMessageDialog (this, message);
        } else if (codeIsEmpty) {
            message = "Os campo CÓDIGO é obrigatório";
            JOptionPane.showMessageDialog (this, message);
        }
        return !nameIsEmpty && !codeIsEmpty;
    }
    
    private void onSave() {
        if(!this.validateFields()) {
            return;
        }
        
        boolean result;
        String successMessage = null;
        if(this.product == null) {
            result = this.createProduct();
            if (this.product != null) {
                successMessage = "O produto "+this.product.getDescription()+" foi cadastrado com sucesso";
                this.index = this.products.size() - 1;
            }
        } else {
            result = this.updateProduct();
            successMessage = "O produto "+this.product.getDescription()+" foi atualizado com sucesso";
        }
        
        if (result) {
            JOptionPane.showMessageDialog (this, successMessage);
        } else {
            String message = "Algo deu errado, tente novamente";
            JOptionPane.showMessageDialog (this, message);
        }
    }
    
    private void setItemListenerToggleButton() {
        this.stateButton.addItemListener((ItemEvent ev) -> {
            if(ev.getStateChange()==ItemEvent.SELECTED){
                stateButton.setText("Ativo");
            } else if(ev.getStateChange()==ItemEvent.DESELECTED){
                stateButton.setText("Desativado");
            }
        });
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField2 = new javax.swing.JTextField();
        formLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        codeLabel = new javax.swing.JLabel();
        codeField = new javax.swing.JTextField();
        save = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        leave = new javax.swing.JButton();
        priceField = new javax.swing.JTextField();
        priceLabel = new javax.swing.JLabel();
        stateButton = new javax.swing.JToggleButton();
        stateLabel = new javax.swing.JLabel();
        categoriesLabel = new javax.swing.JLabel();
        categoriesComboBox = new javax.swing.JComboBox<>();

        jTextField2.setText("jTextField2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        formLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        formLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        formLabel.setText("NOME BEM GRANDÃO AAAAAA");
        formLabel.setToolTipText("");

        nameLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nameLabel.setText("Nome");

        nameField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        codeLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        codeLabel.setText("Código");

        codeField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        save.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        save.setText("Salvar");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        delete.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        delete.setText("Excluir");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        leave.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        leave.setText("Sair");
        leave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leaveActionPerformed(evt);
            }
        });

        priceField.setText("0");

        priceLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        priceLabel.setText("Preço");

        stateButton.setSelected(true);
        stateButton.setText("Ativo");
        stateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stateButtonActionPerformed(evt);
            }
        });

        stateLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        stateLabel.setText("Estado");

        categoriesLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        categoriesLabel.setText("Categoria");

        categoriesComboBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(formLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(8, 8, 8)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(nameLabel)
                                        .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(leave)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(delete)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(save))
                                        .addComponent(stateLabel)
                                        .addComponent(categoriesLabel)
                                        .addComponent(stateButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(categoriesComboBox, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(codeLabel)
                                    .addComponent(priceLabel)
                                    .addComponent(priceField, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(codeField, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 14, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(formLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(nameLabel)
                .addGap(11, 11, 11)
                .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codeLabel)
                    .addComponent(categoriesLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(categoriesComboBox)
                    .addComponent(codeField, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(priceLabel)
                    .addComponent(stateLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(priceField, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(save)
                    .addComponent(delete)
                    .addComponent(leave))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        this.onSave();
    }//GEN-LAST:event_saveActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        // TODO add your handling code here:
        boolean result = ProductDAO.delete(this.product);
        if (result) {
            String message = "O produto "+this.product.getDescription()+" foi deletado com sucesso.";
            JOptionPane.showMessageDialog (this, message);
            this.products.remove(this.product);
            this.table.removeProduct(this.index);
            this.dispose();
        } else {
            String message = "Algo deu errado, tente novamente";
            JOptionPane.showMessageDialog (this, message);
        }
    }//GEN-LAST:event_deleteActionPerformed

    private void leaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leaveActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_leaveActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        System.out.println("Fechando janela");
    }//GEN-LAST:event_formWindowClosing

    private void stateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stateButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stateButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> categoriesComboBox;
    private javax.swing.JLabel categoriesLabel;
    private javax.swing.JTextField codeField;
    private javax.swing.JLabel codeLabel;
    private javax.swing.JButton delete;
    private javax.swing.JLabel formLabel;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JButton leave;
    private javax.swing.JTextField nameField;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField priceField;
    private javax.swing.JLabel priceLabel;
    private javax.swing.JButton save;
    private javax.swing.JToggleButton stateButton;
    private javax.swing.JLabel stateLabel;
    // End of variables declaration//GEN-END:variables
}
