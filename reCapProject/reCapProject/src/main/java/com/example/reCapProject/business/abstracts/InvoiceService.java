package com.example.reCapProject.business.abstracts;

import java.util.Date;
import java.util.List;

import com.example.reCapProject.core.utilities.result.DataResult;
import com.example.reCapProject.core.utilities.result.Result;
import com.example.reCapProject.entities.concretes.Invoice;
import com.example.reCapProject.entities.dtos.InvoiceDto;
import com.example.reCapProject.entities.request.invoice.CreateInvoiceRequest;
import com.example.reCapProject.entities.request.invoice.DeleteInvoiceRequest;
import com.example.reCapProject.entities.request.invoice.UpdateInvoiceRequest;

public interface InvoiceService {
	
	Result add(CreateInvoiceRequest createInvoiceRequest);
	
	Result delete(DeleteInvoiceRequest deleteInvoiceRequest);
	
	Result update(UpdateInvoiceRequest updaInvoiceRequest);
	
	
	DataResult<List<InvoiceDto>> getAll();
	DataResult<List<InvoiceDto>> getByCustomer_Id(int customerId);
	DataResult<List<Invoice>> getByInvoicesAllDate(Date startDate, Date endDate);
	
	
	

}
