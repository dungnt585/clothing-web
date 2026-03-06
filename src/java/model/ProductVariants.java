/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author LENOVO
 */
@Entity
@Table(name = "ProductVariants")
@NamedQueries({
    @NamedQuery(name = "ProductVariants.findAll", query = "SELECT p FROM ProductVariants p"),
    @NamedQuery(name = "ProductVariants.findByVariantId", query = "SELECT p FROM ProductVariants p WHERE p.variantId = :variantId"),
    @NamedQuery(name = "ProductVariants.findByStock", query = "SELECT p FROM ProductVariants p WHERE p.stock = :stock")})
public class ProductVariants implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "variantId")
    private Collection<OrderDetail> orderDetailCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "VariantId")
    private Integer variantId;
    @Column(name = "Stock")
    private Integer stock;
    @JoinColumn(name = "ColorId", referencedColumnName = "ColorId")
    @ManyToOne(optional = false)
    private Colors colorId;
    @JoinColumn(name = "ProductId", referencedColumnName = "ProductId")
    @ManyToOne(optional = false)
    private Product productId;
    @JoinColumn(name = "SizeId", referencedColumnName = "SizeId")
    @ManyToOne(optional = false)
    private Sizes sizeId;

    public ProductVariants() {
    }

    public ProductVariants(Integer variantId) {
        this.variantId = variantId;
    }

    public Integer getVariantId() {
        return variantId;
    }

    public void setVariantId(Integer variantId) {
        this.variantId = variantId;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Colors getColorId() {
        return colorId;
    }

    public void setColorId(Colors colorId) {
        this.colorId = colorId;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public Sizes getSizeId() {
        return sizeId;
    }

    public void setSizeId(Sizes sizeId) {
        this.sizeId = sizeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (variantId != null ? variantId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductVariants)) {
            return false;
        }
        ProductVariants other = (ProductVariants) object;
        if ((this.variantId == null && other.variantId != null) || (this.variantId != null && !this.variantId.equals(other.variantId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.ProductVariants[ variantId=" + variantId + " ]";
    }

    public Collection<OrderDetail> getOrderDetailCollection() {
        return orderDetailCollection;
    }

    public void setOrderDetailCollection(Collection<OrderDetail> orderDetailCollection) {
        this.orderDetailCollection = orderDetailCollection;
    }
    
}
