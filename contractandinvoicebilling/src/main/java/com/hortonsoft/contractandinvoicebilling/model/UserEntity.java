package com.hortonsoft.contractandinvoicebilling.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="client_user_id")
    private long clientUserId;
    
    @Column(name="vendor_user_id")
    private long vendorUserId;
    
    @Column(name="user_name")
    private String userName;
    
    @Column(name="contract_id")
    private long contractId;
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public long getContractId() {
		return contractId;
	}

	public void setContractId(long contractId) {
		this.contractId = contractId;
	}

	@Override
    public String toString() {
        return "EmployeeEntity [id=" + id + ", clientUserId=" + clientUserId + 
                ", vendorUserId=" + vendorUserId + ", userName=" + userName   + ", contractId=" + contractId + "]";
    }
}