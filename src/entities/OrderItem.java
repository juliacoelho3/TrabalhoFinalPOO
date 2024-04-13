package entities;

import java.util.Objects;

public class OrderItem {

	private Integer id;
	private Double unitValue;
	private Integer quantity;
	private Double descountValue;
	
	public OrderItem() {
	}

	public OrderItem(Integer id, Double unitValue, Integer quantity, Double descountValue) {
		super();
		this.id = id;
		this.unitValue = unitValue;
		this.quantity = quantity;
		this.descountValue = descountValue;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getUnitValue() {
		return unitValue;
	}

	public void setUnitValue(Double unitValue) {
		this.unitValue = unitValue;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getDescountValue() {
		return descountValue;
	}

	public void setDescountValue(Double descountValue) {
		this.descountValue = descountValue;
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
		OrderItem other = (OrderItem) obj;
		return Objects.equals(id, other.id);
	}
}
