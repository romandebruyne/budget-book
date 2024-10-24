import axios from "axios";

const SERVER_URL = "http://localhost:8080";
const ITEMS_URL = SERVER_URL + "/items";

export type BudgetBookItem = {
    id: number, date: string, description: string, category: string, amount: number
}

export async function getAllItems() {
    const response = await axios.get<BudgetBookItem[]>(ITEMS_URL);
    return response;
}

export async function getItemById(id: number) {
    let FINAL_URL = ITEMS_URL + "/" + id;
    const response = await axios.get<BudgetBookItem>(FINAL_URL);
    return response;
}

export async function createItem(date: string, description: string, category: string, amount: number) {
    let FINAL_URL = ITEMS_URL + "?date=" + date + "&" +
        "description=" + description + "&" +
        "category=" + category + "&" +
        "amount=" + amount;
    
    const response = await axios.post(FINAL_URL);
    return response;
}

export async function editItem(id: number, date: string, description: string, category: string, amount: number) {

    let FINAL_URL = ITEMS_URL + "?id=" + id + "&" +
        "date=" + date + "&" +
        "description=" + description + "&" +
        "category=" + category + "&" +
        "amount=" + amount;

    const response = await axios.put(FINAL_URL);
    return response;
}

