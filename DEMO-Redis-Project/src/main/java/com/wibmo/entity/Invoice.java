/**
 * 
 */
package com.wibmo.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Invoice implements Serializable{

    private static final long serialVersionUID = -4439114469417994311L;

    @Id
    @GeneratedValue
    @Column(name="invId")
    private Integer invId;
    @Column(name="invName")
    private String invName;
    @Column(name="invAmount")
    private Double invAmount;
    public Integer getInvId() {
		return invId;
	}
	public void setInvId(Integer invId) {
		this.invId = invId;
	}
	public String getInvName() {
		return invName;
	}
	public void setInvName(String invName) {
		this.invName = invName;
	}
	public Double getInvAmount() {
		return invAmount;
	}
	public void setInvAmount(Double invAmount) {
		this.invAmount = invAmount;
	}
	
}