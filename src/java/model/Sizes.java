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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author LENOVO
 */
@Entity
@Table(name = "Sizes")
@NamedQueries({
    @NamedQuery(name = "Sizes.findAll", query = "SELECT s FROM Sizes s"),
    @NamedQuery(name = "Sizes.findBySizeId", query = "SELECT s FROM Sizes s WHERE s.sizeId = :sizeId"),
    @NamedQuery(name = "Sizes.findBySizeName", query = "SELECT s FROM Sizes s WHERE s.sizeName = :sizeName")})
public class Sizes implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sizeId")
    private Collection<ProductVariants> productVariantsCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SizeId")
    private Integer sizeId;
    @Basic(optional = false)
    @Column(name = "SizeName")
    private String sizeName;

    public Sizes() {
    }

    public Sizes(Integer sizeId) {
        this.sizeId = sizeId;
    }

    public Sizes(Integer sizeId, String sizeName) {
        this.sizeId = sizeId;
        this.sizeName = sizeName;
    }

    public Integer getSizeId() {
        return sizeId;
    }

    public void setSizeId(Integer sizeId) {
        this.sizeId = sizeId;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sizeId != null ? sizeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sizes)) {
            return false;
        }
        Sizes other = (Sizes) object;
        if ((this.sizeId == null && other.sizeId != null) || (this.sizeId != null && !this.sizeId.equals(other.sizeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Sizes[ sizeId=" + sizeId + " ]";
    }

    public Collection<ProductVariants> getProductVariantsCollection() {
        return productVariantsCollection;
    }

    public void setProductVariantsCollection(Collection<ProductVariants> productVariantsCollection) {
        this.productVariantsCollection = productVariantsCollection;
    }
    
}
