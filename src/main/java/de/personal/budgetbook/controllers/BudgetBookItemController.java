package de.personal.budgetbook.controllers;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.personal.budgetbook.objects.BudgetBookItem;
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
	
	@PostMapping("/items")
	public ResponseEntity<BudgetBookItem> createBudgetBookItem(
			@RequestParam(value = "date", defaultValue = "", required = true) String date,
			@RequestParam(value = "description", defaultValue = "", required = true) String description,
			@RequestParam(value = "category", defaultValue = "", required = true) String category,
			@RequestParam(value = "amount", defaultValue = "", required = true) String amount) {
		
		Map<String, String> dataMapping = this.budgetBookItemService.createDataMapping(date,
				description, category, amount);
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
			@RequestParam(value = "date", defaultValue = "", required = true) String date,
			@RequestParam(value = "description", defaultValue = "", required = true) String description,
			@RequestParam(value = "category", defaultValue = "", required = true) String category,
			@RequestParam(value = "amount", defaultValue = "", required = true) String amount) {
		
		Map<String, String> dataMapping = this.budgetBookItemService.createDataMapping(date,
				description, category, amount);
		BudgetBookItem itemToEdit = this.budgetBookItemService.createBudgetBookItemFromDataMapping(dataMapping);
		
		if (itemToEdit != null) {
        	this.logger.info("Item was successfully modified!");
        	return new ResponseEntity<>(this.budgetBookItemRepo.save(itemToEdit), HttpStatus.OK);
        } else {
        	this.logger.error("Item was not modified!");
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}

	@DeleteMapping("/items")
	public ResponseEntity<HttpStatus> deleteBudgetBookItem(@PathVariable String idAsString) {
		long id;
		
		try {
			id = Long.parseLong(idAsString);
		} catch (NumberFormatException nfe) {
			this.logger.error("Invalid item ID!");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		if (this.budgetBookItemRepo.findById(id).orElse(null) != null) {
			this.budgetBookItemRepo.delete(this.budgetBookItemRepo.findById(id).get());
	        this.logger.info("Deletion was successful!");
	        return new ResponseEntity<>(HttpStatus.OK);
        }
    	
		this.logger.warn("Deletion was not successful!");
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
