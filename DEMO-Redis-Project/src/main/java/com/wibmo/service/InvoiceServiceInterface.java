/**
 * 
 */
package com.wibmo.service;

import com.wibmo.entity.Invoice;

import java.util.*;

import org.springframework.stereotype.Service;
/**
 * 
 */
@Service
public interface InvoiceServiceInterface {
	public Invoice saveInvoice(Invoice inv);

    public Invoice updateInvoice(Invoice inv, Integer invId);

    public void deleteInvoice(Integer invId);

    public Invoice getOneInvoice(Integer invId);

    public List<Invoice> getAllInvoices();
}
