package com.hortonsoft.contractandinvoicebilling.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="contracts")
public class ContractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="description")
    private String description;
    
    @Column(name="contract_value", precision=12, scale=2)
    private BigDecimal contractValue;
    
    @Column(name="remaining_value", precision=12, scale=2)
    private BigDecimal remainingValue;
    
    @Column(name="client_user_id")
    private long clientUserId;
    
    @Column(name="vendor_user_id")
    private long vendorUserId;
    
    @OneToMany
    @JoinColumn(name="contract_id")
    private List<InvoiceEntity> invoices = new ArrayList<InvoiceEntity>();
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getContractValue() {
		return contractValue;
	}

	public void setContractValue(BigDecimal contractValue) {
		this.contractValue = contractValue;
	}

	public BigDecimal getRemainingValue() {
		return remainingValue;
	}

	public void setRemainingValue(BigDecimal remainingValue) {
		this.remainingValue = remainingValue;
	}

	public long getClientUserId() {
		return clientUserId;
	}

	public void setClientUserId(long clientUserId) {
		this.clientUserId = clientUserId;
	}

	public long getVendorUserId() {
		return vendorUserId;
	}

	public void setVendorUserId(long vendorUserId) {
		this.vendorUserId = vendorUserId;
	}

	public List<InvoiceEntity> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<InvoiceEntity> invoices) {
		this.invoices = invoices;
	}

	@Override
    public String toString() {
        return "EmployeeEntity [id=" + id + ", description=" + description + 
                ", contractValue=" + contractValue + ", clientUserId=" + clientUserId   + "]";
    }
}