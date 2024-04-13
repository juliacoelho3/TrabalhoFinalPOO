package entities;

import java.time.Instant;
import java.util.Objects;

public class Order {
	
	private Integer id;
	private Instant issueDate;
	private Instant deliveryDate;
	private Double totalValue;
	private String observation;
	private Client client;

	public Order() {
	}

	public Order(Integer id, Instant issueDate, Instant deliveryDate, Double totalValue, String observation) {
		super();
		this.id = id;
		this.issueDate = issueDate;
		this.deliveryDate = deliveryDate;
		this.totalValue = totalValue;
		this.observation = observation;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Instant getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Instant issueDate) {
		this.issueDate = issueDate;
	}

	public Instant getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Instant deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Double getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(Double totalValue) {
		this.totalValue = totalValue;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
