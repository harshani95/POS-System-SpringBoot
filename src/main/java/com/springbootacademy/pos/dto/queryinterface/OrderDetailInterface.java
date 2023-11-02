package com.springbootacademy.pos.dto.queryinterface;

import java.util.ArrayList;
import java.util.Date;

public interface OrderDetailInterface {
    String getCustomerName();
    String getCustomerAddress();
    ArrayList getContactNumber();
    Date getDate();
    Double getTotal();
}
