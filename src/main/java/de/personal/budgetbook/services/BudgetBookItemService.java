package de.personal.budgetbook.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.personal.budgetbook.objects.BudgetBookItem;
import de.personal.budgetbook.objects.Category;
import de.personal.budgetbook.repos.BudgetBookItemRepository;

@Service
public class BudgetBookItemService {
	private Logger logger = LoggerFactory.getLogger(BudgetBookItemService.class);
	
	@Autowired
	private BudgetBookItemRepository budgetBookItemRepo;
	
	public void readBudgetBookDataFromCSV(boolean header, char decimalSeperator) {
		String path = "src/main/resources/fakebudgetdata.csv", line;
		String[] information = new String[6];
		BudgetBookItem tempBudgetBookItem;
		NumberFormat numFormat;
		
		if (decimalSeperator == ',') {
			numFormat = NumberFormat.getInstance(Locale.GERMAN);
		} else {
			numFormat = NumberFormat.getInstance(Locale.US);
		}
		
		try (BufferedReader br = Files.newBufferedReader(Paths.get(path))) {
			if (header) {
				line = br.readLine();
			}
			
			while ((line = br.readLine()) != null) {
				information = line.split(";", 6);
				
				try {
					tempBudgetBookItem = new BudgetBookItem(LocalDate.parse(getDateFromGermanDateFormat(information[0])),
							information[3], Category.getEnumFromGermanDescription(information[4]), numFormat.parse(information[5]).doubleValue());
					this.budgetBookItemRepo.save(tempBudgetBookItem);
				} catch (ParseException pe) {
					this.logger.error("Corrupted data in CSV-file!");
				}
			}
		
			this.logger.info("Budget data from CSV-file succesfully imported.");
			
		} catch (IOException ioe) {
			this.logger.error("Error during budget data import from CSV-file!");
		}
	}
	
	private String getDateFromGermanDateFormat(String dateAsString) {
		String[] information = dateAsString.replace("\"", "").split("\\.");
		return information[2] + "-" + information[1] + "-" + information[0];
	}
	
	public Map<String, String> createDataMapping(String date, String description,
			String category, String amount) {
		Map<String, String> mapping = new HashMap<>();

		mapping.put("date", date);
		mapping.put("description", description);
		mapping.put("category", category);
		mapping.put("amount", amount);

		return mapping;
	}
	
	public BudgetBookItem createBudgetBookItemFromDataMapping(Map<String, String> dataMapping) {
		LocalDate date;
		Category category;
		double amount;
		
		if (!isValidDateFormat(dataMapping.get("date"))) {
			this.logger.warn("Invalid date format.");
			return null;
		} else {
			date = LocalDate.parse(dataMapping.get("date"));
		}
		
		if (!isValidCategory(dataMapping.get("category"))) {
			this.logger.warn("Invalid category.");
			return null;
		} else {
			category = Category.getEnumFromDescription(dataMapping.get("category"));
		}
		
		if (!isValidNumberFormat(dataMapping.get("amount"))) {
			this.logger.warn("Invalid number format for amount field.");
			return null;
		} else {
			amount = Double.parseDouble(dataMapping.get("amount"));
		}
		
		return new BudgetBookItem(date, dataMapping.get("description"), category, amount);
	}
	
	public BudgetBookItem updateBudgetBookItemFromDataMapping(long idOfItemToUpdate, Map<String, String> dataMapping) {
		BudgetBookItem itemToUpdate = this.budgetBookItemRepo.findById(idOfItemToUpdate).orElse(null);
		
		if (itemToUpdate == null) {
			this.logger.warn("Item not found.");
			return null;
		}
		
		if (!isValidDateFormat(dataMapping.get("date"))) {
			this.logger.warn("Invalid date format.");
			return null;
		} else {
			itemToUpdate.setDate(LocalDate.parse(dataMapping.get("date")));
		}
		
		itemToUpdate.setDescription(dataMapping.get("description"));
		
		if (!isValidCategory(dataMapping.get("category"))) {
			this.logger.warn("Invalid category.");
			return null;
		} else {
			itemToUpdate.setCategory(Category.getEnumFromDescription(dataMapping.get("category")));
		}
		
		if (!isValidNumberFormat(dataMapping.get("amount"))) {
			this.logger.warn("Invalid number format for amount field.");
			return null;
		} else {
			itemToUpdate.setAmount(Double.parseDouble(dataMapping.get("amount")));
		}
		
		return itemToUpdate;
	}
	
	public boolean isValidDateFormat(String dateAsString) {
		try {
			LocalDate.parse(dateAsString);
			return true;
		} catch (DateTimeParseException dtpe) {
			return false;
		}
	}
	
	public boolean isValidNumberFormat(String numberAsString) {
		try {
			Double.parseDouble(numberAsString);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}
	
	public boolean isValidCategory(String categoryAsString) {
		if (Category.getEnumFromDescription(categoryAsString) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	
}
