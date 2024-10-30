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
    const response = await axios.get<BudgetBookItem>(ITEMS_URL + "/" + id);
    return response;
}

export async function createItem(date: string, description: string, category: string, amount: number) {
    const data = {
        "date": date,
        "description": description,
        "category": category,
        amount: amount
    }
    
    const response = await axios.post(ITEMS_URL, data);
    return response;
}

export async function editItem(id: number, date: string, description: string, category: string, amount: number) {
    const data = {
        "id": id,
        "date": date,
        "description": description,
        "category": category,
        amount: amount
    }

    const response = await axios.put(ITEMS_URL, data);
    return response;
}

export async function deleteItemById(id: number) {
    const response = await axios.delete<BudgetBookItem>(ITEMS_URL + "/" + id);
    return response;
}

