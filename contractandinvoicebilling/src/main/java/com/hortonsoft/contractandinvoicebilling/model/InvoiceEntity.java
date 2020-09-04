package com.hortonsoft.contractandinvoicebilling.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="invoices")
public class InvoiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
        
    @Column(name="invoice_value", precision=12, scale=2)
    private BigDecimal invoiceValue;
    
    @Column(name="contract_id")
    private long contractId;
    
    @Column(name="created")
    private Timestamp created;
    
    @Column(name="void")
    private Boolean voidValue;
      
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    public BigDecimal getInvoiceValue() {
		return invoiceValue;
	}

	public void setInvoiceValue(BigDecimal invoiceValue) {
		this.invoiceValue = invoiceValue;
	}

	public long getContractId() {
		return contractId;
	}

	public void setContractId(long contractId) {
		this.contractId = contractId;
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public Boolean getVoidValue() {
		return voidValue;
	}

	public void setVoidValue(Boolean voidValue) {
		this.voidValue = voidValue;
	}

	@Override
    public String toString() {
        return "InvoiceEntity [id=" + id + ", invoiceValue=" + invoiceValue + 
                ", created=" + created + ", voidValue=" + voidValue   + "]";
    }
}