import axios from "axios";

const SERVER_URL = "http://localhost:8080";
const ITEMS_URL = SERVER_URL + "/items";

export type BudgetBookItem = {
    id: number, date: string, description: string, category: string, role: number
}

export async function getAllItems() {
    const response = await axios.get<BudgetBookItem[]>(ITEMS_URL);
    return response;
}

