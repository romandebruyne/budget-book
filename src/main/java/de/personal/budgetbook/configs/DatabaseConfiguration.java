package de.personal.budgetbook.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import de.personal.budgetbook.repos.BudgetBookItemRepository;
import de.personal.budgetbook.services.BudgetBookItemService;
import jakarta.annotation.PostConstruct;

@Configuration
public class DatabaseConfiguration {
	@Autowired
	private BudgetBookItemRepository budgetBookItemRepo;
	
	@Autowired
	private BudgetBookItemService bugetBookItemService;
	
	@PostConstruct
	public void setupDatabase() {
		if (this.budgetBookItemRepo.findAll().isEmpty()) {
			this.bugetBookItemService.readBudgetBookDataFromCSV(true, ',');
		}
	}
}
