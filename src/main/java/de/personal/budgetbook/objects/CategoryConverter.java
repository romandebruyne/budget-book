package de.personal.budgetbook.objects;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CategoryConverter implements AttributeConverter<Category, String> {

	@Override
	public String convertToDatabaseColumn(Category category) {
		switch (category) {
		case ALCOHOL:
			return Category.ALCOHOL.getEnglishDescription();
		case APARTMENT_PURCHASES:
			return Category.APARTMENT_PURCHASES.getEnglishDescription();
		case APARTMENT_RENT:
			return Category.APARTMENT_RENT.getEnglishDescription();
		case APARTMENT_UTIL:
			return Category.APARTMENT_UTIL.getEnglishDescription();
		case ASSETS_FEES:
			return Category.ASSETS_FEES.getEnglishDescription();
		case ASSETS_INCOME:
			return Category.ASSETS_INCOME.getEnglishDescription();
		case ASSETS_OTHERS:
			return Category.ASSETS_OTHERS.getEnglishDescription();
		case ASSETS_TAXES:
			return Category.ASSETS_TAXES.getEnglishDescription();
		case CAR_FUEL:
			return Category.CAR_FUEL.getEnglishDescription();
		case CAR_SPARE_PARTS:
			return Category.CAR_SPARE_PARTS.getEnglishDescription();
		case CAR_TAX_INSURANCE:
			return Category.CAR_TAX_INSURANCE.getEnglishDescription();
		case CIGARTTES:
			return Category.CIGARTTES.getEnglishDescription();
		case DONATIONS:
			return Category.DONATIONS.getEnglishDescription();
		case GROCERIES:
			return Category.GROCERIES.getEnglishDescription();
		case INCOME:
			return Category.INCOME.getEnglishDescription();
		case INSURANCES:
			return Category.INSURANCES.getEnglishDescription();
		case LEISURE_FOOTBALL:
			return Category.LEISURE_FOOTBALL.getEnglishDescription();
		case LEISURE_OTHERS:
			return Category.LEISURE_OTHERS.getEnglishDescription();
		case LEISURE_VACATION:
			return Category.LEISURE_VACATION.getEnglishDescription();
		case OTHERS:
			return Category.OTHERS.getEnglishDescription();
		case PARENTS:
			return Category.PARENTS.getEnglishDescription();
		case PURCHASES_BOOKS:
			return Category.PURCHASES_BOOKS.getEnglishDescription();
		case PURCHASES_CLOTHING:
			return Category.PURCHASES_CLOTHING.getEnglishDescription();
		case PURCHASES_ELECTRONIS:
			return Category.PURCHASES_ELECTRONIS.getEnglishDescription();
		case PURCHASES_OTHERS:
			return Category.PURCHASES_OTHERS.getEnglishDescription();
		default:
			return Category.UNKNOWN.getEnglishDescription();
		}
	}

	@Override
	public Category convertToEntityAttribute(String dbData) {
		switch (dbData) {
		case "Alcohol":
			return Category.ALCOHOL;
		case "Apartment: Purchases":
			return Category.APARTMENT_PURCHASES;
		case "Apartment: Rent":
			return Category.APARTMENT_RENT;
		case "Apartment: Utilities":
			return Category.APARTMENT_UTIL;
		case "Assets: Profit":
			return Category.ASSETS_FEES;
		case "Assets: Income":
			return Category.ASSETS_INCOME;
		case "Assets: Others":
			return Category.ASSETS_OTHERS;
		case "Assets: Taxes":
			return Category.ASSETS_TAXES;
		case "Car: Fuel":
			return Category.CAR_FUEL;
		case "Car: Spare parts etc.":
			return Category.CAR_SPARE_PARTS;
		case "Car: Tax/Insurance":
			return Category.CAR_TAX_INSURANCE;
		case "Cigarettes":
			return Category.CIGARTTES;
		case "Donations":
			return Category.DONATIONS;
		case "Groceries":
			return Category.GROCERIES;
		case "Income":
			return Category.INCOME;
		case "Insurances":
			return Category.INSURANCES;
		case "Leisure: Football":
			return Category.LEISURE_FOOTBALL;
		case "Leisure: Others":
			return Category.LEISURE_OTHERS;
		case "Leisure: Vacation":
			return Category.LEISURE_VACATION;
		case "Others":
			return Category.OTHERS;
		case "Parents":
			return Category.PARENTS;
		case "Purchases: Books":
			return Category.PURCHASES_BOOKS;
		case "Purchases: Clothing":
			return Category.PURCHASES_CLOTHING;
		case "Purchases: Electronics":
			return Category.PURCHASES_ELECTRONIS;
		case "Purchases: Others":
			return Category.PURCHASES_OTHERS;
		default:
			return Category.UNKNOWN;
		}
	}
}