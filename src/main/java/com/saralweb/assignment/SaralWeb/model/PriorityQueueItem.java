package com.saralweb.assignment.SaralWeb.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tbl_item")
public class PriorityQueueItem {
	
	@Id
	@Column(name="item_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name="item_value")
	private String value;
	
	@Column(name="item_priority")
	private Integer priority;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public PriorityQueueItem() {}
	
	public PriorityQueueItem(String value, Integer priority) {
		super();
		this.value = value;
		this.priority = priority;
	}

	@Override
	public String toString() {
		return "PriorityQueueItem [id=" + id + ", Value=" + value + ", priority=" + priority + "]";
	}

	
	
	
	
}
