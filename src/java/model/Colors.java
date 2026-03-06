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
@Table(name = "Colors")
@NamedQueries({
    @NamedQuery(name = "Colors.findAll", query = "SELECT c FROM Colors c"),
    @NamedQuery(name = "Colors.findByColorId", query = "SELECT c FROM Colors c WHERE c.colorId = :colorId"),
    @NamedQuery(name = "Colors.findByColorName", query = "SELECT c FROM Colors c WHERE c.colorName = :colorName")})
public class Colors implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "colorId")
    private Collection<ProductVariants> productVariantsCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ColorId")
    private Integer colorId;
    @Basic(optional = false)
    @Column(name = "ColorName")
    private String colorName;

    public Colors() {
    }

    public Colors(Integer colorId) {
        this.colorId = colorId;
    }

    public Colors(Integer colorId, String colorName) {
        this.colorId = colorId;
        this.colorName = colorName;
    }

    public Integer getColorId() {
        return colorId;
    }

    public void setColorId(Integer colorId) {
        this.colorId = colorId;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (colorId != null ? colorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Colors)) {
            return false;
        }
        Colors other = (Colors) object;
        if ((this.colorId == null && other.colorId != null) || (this.colorId != null && !this.colorId.equals(other.colorId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Colors[ colorId=" + colorId + " ]";
    }

    public Collection<ProductVariants> getProductVariantsCollection() {
        return productVariantsCollection;
    }

    public void setProductVariantsCollection(Collection<ProductVariants> productVariantsCollection) {
        this.productVariantsCollection = productVariantsCollection;
    }
    
}
