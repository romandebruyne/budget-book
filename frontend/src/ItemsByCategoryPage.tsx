import { useState } from "react";
import { DataGrid, GridColDef } from "@mui/x-data-grid";
import { BudgetBookItem, getItemsByCategory } from "./api";
import { CategorySelectionPage } from "./CategorySelectionPage";
import "./Datagrid.css";
import "./AllItemsPage.css";

type Props = { onBack: () => void }

export function ItemsByCategoryPage(props: Props) {
    const [itemsByCategoryPageIsOpen, setItemsByCategoryPageIsOpen] = useState(true);
    const [categorySelectionIsOpen, setCategorySelectionIsOpen] = useState(false);
    const [category, setCategory] = useState("unknown");
    const [items, setItems] = useState<null | BudgetBookItem[]>(null);

    function showItemsByCategory() {
        getItemsByCategory(category).then(body => setItems(body.data));
    }

    function handleClickOnCategorySelection() {
        setCategorySelectionIsOpen(true);
        setItemsByCategoryPageIsOpen(false);
    }

    function handleCategorySelection(selectedCategory: string) {
        setCategory(selectedCategory);
        setCategorySelectionIsOpen(false);
        setItemsByCategoryPageIsOpen(true);
    }

    function handleClickOnReset() {
        setCategory("unknown");
        setItems([]);
    }

    const tableColumns: GridColDef[] = [
        { field: 'id', headerName: 'ID', width: 80 },
        { field: 'date', headerName: 'Date', width: 100 },
        { field: 'description', headerName: 'Description', width: 400 },
        { field: 'category', headerName: 'Category', width: 200 },
        { field: 'amount', headerName: 'Amount', width: 100 }
    ];

    return (
        <>
            { itemsByCategoryPageIsOpen ? 
                <>
                    <h2>Items by Category</h2>
                    <div className="box">
                        <button onClick={ handleClickOnCategorySelection }>{ category }</button>
                        <button onClick={ handleClickOnReset }>Reset</button>
                        <button
                            disabled={ category === "" }
                            onClick={ showItemsByCategory }>Show items</button>
                        <button onClick={ props.onBack }>Back</button><br /><br />
                    </div>

                    <div>
                        { items !== null ? 
                            <div className="whitetable">
                                <DataGrid
                                    rows={ items }
                                    columns={ tableColumns }
                                    initialState={{ pagination: { paginationModel: { page: 0, pageSize: 10 }, }, }}
                                    pageSizeOptions={[10]}
                                />
                            </div> : null }
                    </div>

                    <div>
                        <p>Select category via click on first button.</p>
                    </div>
                </>
            : null }

            { categorySelectionIsOpen ? <CategorySelectionPage onSelect={ handleCategorySelection } /> : null }
        </>
    )
}