/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.product;

import java.awt.GridLayout;
import java.util.ArrayList;
import jefinho.products.models.Product;

/**
 *
 * @author João Salomão
 */
public class List extends javax.swing.JFrame {
    ArrayList<Product> products;
    ArrayList<ProductItem> productItems;
    /**
     * Creates new form List
     * @param products
     */
    public List(ArrayList<Product> products) {
        this.productItems = new ArrayList<>();
        this.products = products;
        initComponents();
        
        this.setLayout(new GridLayout(this.products.size(), 1));
        
        this.addProductItem();
    }
    
    public void refresh() {
        this.removeAll();
        this.productItems = new ArrayList<>();
        this.addProductItem();
    };
    
    public void addProductItem() {
        this.products.forEach(p -> {
            ProductItem pi;
            pi = new ProductItem(products, p, this);
            this.productItems.add(pi);
            this.add(pi);
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 548, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 462, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
