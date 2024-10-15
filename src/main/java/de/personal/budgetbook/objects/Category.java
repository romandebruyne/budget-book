package de.personal.budgetbook.objects;

public enum Category {
	ALCOHOL("Alcohol, Party etc.", "Alkohol, Party etc."),
	PURCHASES_BOOKS("Purchases: Books", "Anschaffungen: Bücher"),
	PURCHASES_ELECTRONIS("Purchases: Electronics", "Anschaffungen: Elektronik"),
	PURCHASES_CLOTHING("Purchases: Clothing", "Anschaffungen: Kleidung"),
	PURCHASES_OTHERS("Purchases: Others", "Anschaffungen: sonstiges"),
	GROCERIES("Groceries", "Einkauf/Essen"),
	INCOME("Income", "Einnahme"),
	LEISURE_FOOTBALL("Leisure: Football", "Freizeit: Kicken"),
	LEISURE_OTHERS("Leisure: Other", "Freizeit: sonstiges"),
	LEISURE_VACATION("Leisure: Vacation", "Freizeit: Urlaub"),
	PARENTS("For/From parents", "Für/Von Eltern"),
	DONATIONS("Donations", "Spenden"),
	CAR_SPARE_PARTS("Car: Spare parts etc.", "KfZ: Ersatzteile etc."),
	CAR_TAX_INSURANCE("Car: Tax/Insurance", "KfZ: Steuer/Versicherung"),
	CAR_FUEL("Car: Fuel", "KfZ: Tanken"),
	OTHERS("Others", "Sonstiges"),
	ASSETS_INCOME("Assets: Profit", "Vermögen: Erträge"),
	ASSETS_FEES("Assets: Order fees", "Vermögen: Ordergebühr"),
	ASSETS_TAXES("Assets: Taxes", "Vermögen: Steuern"),
	ASSETS_OTHERS("Assets: Others", "Vermögen: sonstiges"),
	INSURANCES("Insurances", "Versicherung"),
	APARTMENT_PURCHASES("Apartment: Purchases", "Wohnung: Anschaffungen"),
	APARTMENT_RENT("Apartment: Rent", "Wohnung: Miete"),
	APARTMENT_UTIL("Apartment: Utilities", "Wohnung: Nebenkosten"),
	CIGARTTES("Cigarettes", "Zigaretten");
	
	private String englishDescription;
	private String germanDescription;
	
	private Category(String englishDescription, String germanDescription) {
		this.englishDescription = englishDescription;
		this.germanDescription = germanDescription;
		
	}
	
	public String getEnglishDescription() {
		return this.englishDescription;
	}
	
	public String getGermanDescription() {
		return this.germanDescription;
	}
	
	public static Category getEnumFromGermanDescription(String text) {
        for (Category c : Category.values()) {
            if (c.getGermanDescription().equalsIgnoreCase(text)) {
                return c;
            }
        }
        
        return null;
    }
}
