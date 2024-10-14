package de.personal.budgetbook.objects;

import java.time.LocalDate;

public class BudgetBookItem {
	
	private long id;
	private LocalDate date;
	private String description;
	private Category category;
	private double amount;
	
	public BudgetBookItem(long id, LocalDate date, String description, Category category, double amount) {
		this.id = id;
		this.date = date;
		this.description = description;
		this.category = category;
		this.amount = amount;
	}
	
	public long getId() {
		return this.id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public LocalDate getDate() {
		return this.date;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Category getCategory() {
		return this.category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public double getAmount() {
		return this.amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
}
