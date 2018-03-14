package com.theater.process;

import com.theater.model.SeatingAndCustomerRequest;

public interface ProcessPreSalesRequests {
	SeatingAndCustomerRequest extractInfo(String source);
}
