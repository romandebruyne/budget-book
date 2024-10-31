package de.personal.budgetbook.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.personal.budgetbook.objects.BudgetBookItem;
import de.personal.budgetbook.objects.Category;
import de.personal.budgetbook.repos.BudgetBookItemRepository;
import de.personal.budgetbook.services.BudgetBookItemService;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class BudgetBookItemController {
	private Logger logger = LoggerFactory.getLogger(BudgetBookItemController.class);
	
	@Autowired
	private BudgetBookItemRepository budgetBookItemRepo;
	
	@Autowired
	private BudgetBookItemService budgetBookItemService;
	
	@GetMapping("/items")
	public List<BudgetBookItem> getAllBudgetBookItems() {
		return this.budgetBookItemRepo.findAll();
	}
	
	@GetMapping("/items/{id}")
	public BudgetBookItem getBudgetBookItemById(@PathVariable long id) {
		return this.budgetBookItemRepo.findById(id).orElse(null);
	}
	
	@GetMapping("/items/categories")
	public List<Category> getAllCategories() {
		return new ArrayList<>(Arrays.asList(Category.values()));
	}
		
	@GetMapping("/items/categories/{category}")
	public List<BudgetBookItem> getBudgetBookItemsByCategory(@PathVariable String category) {
		return this.budgetBookItemRepo.findAllByCategory(Category.getEnumFromDescription(category));
	}
	
	@PostMapping("/items")
	public ResponseEntity<BudgetBookItem> createBudgetBookItem(
			@RequestBody Map<String, String> dataMapping) {
		
		BudgetBookItem itemToCreate = this.budgetBookItemService.createBudgetBookItemFromDataMapping(dataMapping);
		
        if (itemToCreate != null) {
        	this.logger.info("Item was successfully created!");
        	return new ResponseEntity<>(this.budgetBookItemRepo.save(itemToCreate), HttpStatus.OK);
        } else {
        	this.logger.error("Item was not created!");
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@PutMapping("/items")
	public ResponseEntity<BudgetBookItem> editBudgetBookItem(
			@RequestBody Map<String, String> dataMapping) {

		BudgetBookItem itemToEdit = this.budgetBookItemService.updateBudgetBookItemFromDataMapping(
				Long.parseLong(dataMapping.get("id")), dataMapping);
		
		if (itemToEdit != null) {
        	this.logger.info("Item was successfully modified!");
        	return new ResponseEntity<>(this.budgetBookItemRepo.save(itemToEdit), HttpStatus.OK);
        } else {
        	this.logger.error("Item was not modified!");
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}

	@DeleteMapping("/items/{id}")
	public ResponseEntity<HttpStatus> deleteBudgetBookItem(@PathVariable long id) {
		if (this.budgetBookItemRepo.findById(id).orElse(null) != null) {
			this.budgetBookItemRepo.delete(this.budgetBookItemRepo.findById(id).get());
	        this.logger.info("Deletion was successful!");
	        return new ResponseEntity<>(HttpStatus.OK);
        }
    	
		this.logger.warn("Deletion was not successful!");
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
