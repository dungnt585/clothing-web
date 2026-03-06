/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author LENOVO
 */
@Entity
@Table(name = "Order")
@NamedQueries({
 @NamedQuery(name = "OrderClothing.findAll", query = "SELECT o FROM OrderClothing o"),
        @NamedQuery(name = "OrderClothing.findByOrderId", query = "SELECT o FROM OrderClothing o WHERE o.orderId = :orderId"),
        @NamedQuery(name = "OrderClothing.findByOrderDate", query = "SELECT o FROM OrderClothing o WHERE o.orderDate = :orderDate"),
        @NamedQuery(name = "OrderClothing.findByTotal", query = "SELECT o FROM OrderClothing o WHERE o.total = :total"),
        @NamedQuery(name = "OrderClothing.findByStatus", query = "SELECT o FROM OrderClothing o WHERE o.status = :status"),
        @NamedQuery(name = "OrderClothing.findByPaymentMethod", query = "SELECT o FROM OrderClothing o WHERE o.paymentMethod = :paymentMethod")
    })
public class OrderClothing implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderId")
    private Collection<OrderDetail> orderDetailCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "OrderId")
    private Integer orderId;
    @Column(name = "OrderDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Total")
    private BigDecimal total;
    @Basic(optional = false)
    @Column(name = "Status")
    private String status;
    @Basic(optional = false)
    @Column(name = "PaymentMethod")
    private String paymentMethod;
    @JoinColumn(name = "UserId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users userId;

    public OrderClothing() {
    }

    public OrderClothing(Integer orderId) {
        this.orderId = orderId;
    }

    public OrderClothing(Integer orderId, String status, String paymentMethod) {
        this.orderId = orderId;
        this.status = status;
        this.paymentMethod = paymentMethod;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderId != null ? orderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderClothing)) {
            return false;
        }
        OrderClothing other = (OrderClothing) object;
        if ((this.orderId == null && other.orderId != null) || (this.orderId != null && !this.orderId.equals(other.orderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Order1[ orderId=" + orderId + " ]";
    }

    public Collection<OrderDetail> getOrderDetailCollection() {
        return orderDetailCollection;
    }

    public void setOrderDetailCollection(Collection<OrderDetail> orderDetailCollection) {
        this.orderDetailCollection = orderDetailCollection;
    }
    
}
