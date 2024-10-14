package de.personal.budgetbook.objects;

public enum Category {
	ALCOHOL("Alcohol, Party etc."),
	PURCHASES_BOOKS("Purchases: Books"),
	PURCHASES_ELECTRONIS("Purchases: Electronics"),
	PURCHASES_CLOTHING("Purchases: Clothing"),
	PURCHASES_OTHERS("Purchases: Others"),
	GROCERIES("Groceries"),
	INCOME("Income"),
	LEISURE_FOOTBALL("Leisure: Football"),
	LEISURE_OTHERS("Leisure: Other"),
	LEISURE_VACATION("Leisure: Vacation"),
	PARENTS("For/From parents"),
	DONATIONS("Donation"),
	CAR_SPARE_PARTS("Car: Spare parts etc."),
	CAR_TAX_INSURANCE("Car: Tax/Insurance"),
	CAR_FUEL("Car: Fuel"),
	OTHERS("Others"),
	ASSETS_INCOME("Assets: Profit"),
	ASSETS_FEES("Assets: Order fees"),
	ASSETS_TAXES("Assets: Taxes"),
	ASSETS_OTHERS("Assets: Others"),
	INSURANCES("Insurances"),
	APARTMENT_PURCHASES("Apartment: Purchases"),
	APARTMENT_RENT("Apartment: Rent"),
	APARTMENT_UTIL("Apartment: Utilities"),
	CIGARTTES("Cigarettes");
	
	private String englishDescription;
	
	private Category(String englishDescription) {
		this.englishDescription = englishDescription;
	}
	
	public String getEnglishDescription() {
		return this.englishDescription;
	}

}
