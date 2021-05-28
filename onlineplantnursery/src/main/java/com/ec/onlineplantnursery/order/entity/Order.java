package com.ec.onlineplantnursery.order.entity;

import java.time.LocalDate;

import com.ec.onlineplantnursery.planter.entity.Planter;

public class Order {
	private Integer bookingOrderId;
	private LocalDate orderDate;
	private String transactionMode;
	private int quantity;
	private double totalCost;
	private Planter planters;
}
