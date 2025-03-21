package br.inatel.dm112.model;

import java.util.Date;

public class Order {

	public static enum STATUS { FILLED, PENDING, CONFIRMED }

	private Date orderDate;
	private Date deliveryDate;
	private Date issueDate;
    private int number;
    private String cpf;
    private float value;
	private int status;
    public Order() {}

	public Order(int number, String cpf, float value, int status, Date orderDate) {
        this.number = number;
        this.cpf = cpf;
        this.value = value;
		this.status = status;
        this.orderDate = orderDate;
        this.deliveryDate = null;
    }

    public int getNumber() { return number; }
    public void setNumber(int number) { this.number = number; }

    public float getValue() { return value; }
    public void setValue(float value) { this.value = value; }

	public int getStatus() {
		return status;
	}    
	public void setStatus(int status) {
		this.status = status;
	}
    public Date getOrderDate() { return orderDate; }
    public void setOrderDate(Date orderDate) { this.orderDate = orderDate; }

    public Date getDeliveryDate() { return deliveryDate; }
    public void setDeliveryDate(Date deliveryDate) { this.deliveryDate = deliveryDate; }
	public void setCpf(String cpf) { this.cpf = cpf; }
	public String getCpf() { return cpf; }
	
    @Override
    public String toString() {
        return "Order [number=" + number + ", cpf=" + cpf + ", value=" + value + ", status=" + status + "]";
    }
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	
}
