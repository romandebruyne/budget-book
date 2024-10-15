package de.personal.budgetbook.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

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
	
	public void readBudgetBookDataFromCSV(boolean header) {
		String path = "src/main/resources/fakebudgetdata.csv", line;
		String[] information = new String[6];
		BudgetBookItem tempBudgetBookItem;
		
		try (BufferedReader br = Files.newBufferedReader(Paths.get(path))) {
			if (header) {
				line = br.readLine();
			}
			
			while ((line = br.readLine()) != null) {
				information = line.split(";", 6);
				
				tempBudgetBookItem = new BudgetBookItem(LocalDate.parse(getDateFromGermanDateFormat(information[0])),
						information[3], Category.getEnumFromGermanDescription(information[4]), Double.parseDouble(information[5]));
				
				this.budgetBookItemRepo.save(tempBudgetBookItem);
			}
		
			this.logger.info("Budget data from CSV-file succesfully imported.");
			
		} catch (IOException ioe) {
			this.logger.error("Error during dudget data import from CSV-file!");
		}
	}
	
	private String getDateFromGermanDateFormat(String dateAsString) {
		String[] information = dateAsString.replace("\"", "").split("\\.");
		return information[2] + "-" + information[1] + "-" + information[0];
	}
}
