/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.product;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import jefinho.products.models.Product;

/**
 *
 * @author João Salomão
 */
public class ListPanel extends javax.swing.JPanel {
    ArrayList<Product> products;
    ArrayList<ProductItem> productItems;
    /**
     * Creates new form ListPanel
     * @param products
     */
    public ListPanel (ArrayList<Product> products) {
        this.productItems = new ArrayList<>();
        this.products = products;
        
        initComponents();
        
        this.addComponents();
    }

    public void refresh() {
        this.productItems = new ArrayList<>();
        this.removeAll();
        this.addComponents();
        this.repaint();
        this.revalidate();
    };
    
    private void addComponents() {
        this.addProductItem();
        this.addCreateNewProductButton();
    }
    
    private void addProductItem() {
        this.products.forEach(p -> {
            ProductItem pi;
            pi = new ProductItem(products, p, this);
            this.productItems.add(pi);
            this.add(pi);
        });
        System.out.println("Adding ProductItem");
    }
    
    private void addCreateNewProductButton() {
        JButton newButton = new JButton("Cadastrar");
        newButton.addActionListener((ActionEvent e) -> { 
            new EditFrame(this.products, -1, this);
        });
        this.add(newButton);
        System.out.println("Adding New Product button");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
