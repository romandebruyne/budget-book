import { useEffect, useState } from "react";
import { DataGrid, GridColDef, GridRowParams } from "@mui/x-data-grid";
import { BudgetBookItem, getAllItems } from "./api";
import { ItemDetailsPage } from "./ItemDetailsPage";
import { CreateItemPage } from "./CreateItemPage";
import "./Datagrid.css";
import "./AllItemsPage.css";

type Props = { onBack: () => void }

export function AllItemsPage(props: Props) {
    const [allItemsPageIsOpen, setAllItemsPageIsOpen] = useState(true);
    const [createItemPageIsOpen, setCreateItemPageIsOpen] = useState(false);
    const [items, setItems] = useState<null | BudgetBookItem[]>(null);
    const [idOfSelectedItem, setIdOfSelectedItem] = useState(-1);

    useEffect(() => {
        getAllItems().then(body => setItems(body.data))
    }, [])

    function handleClickOnRow(params: GridRowParams) {
        setAllItemsPageIsOpen(false);
        setIdOfSelectedItem(params.row.id);
    }

    function handleClickOnCreateItem() {
        setCreateItemPageIsOpen(true);
        setAllItemsPageIsOpen(false);
    }

    function handleItemCreation(itemWasCreated: boolean) {
        setCreateItemPageIsOpen(false);
        setAllItemsPageIsOpen(itemWasCreated);
    }

    function backFromItemDetailPage() {
        setIdOfSelectedItem(-1);
        setAllItemsPageIsOpen(true);
    }

    function backFromCreateItemPage() {
        setCreateItemPageIsOpen(false);
        setAllItemsPageIsOpen(true);
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
            { allItemsPageIsOpen ?
                <>
                    <h2>All items</h2>
                    <div className="box">
                        <button onClick={ handleClickOnCreateItem }>Create item</button>
                        <button onClick={ props.onBack }>Back</button><br /><br />
                    </div>
                    
                    <div>
                        { items !== null ?
                            <div className="whitetable">
                                <DataGrid
                                    onRowClick={ handleClickOnRow }
                                    rows={ items }
                                    columns={ tableColumns }
                                    initialState={ { pagination: { paginationModel: { page: 0, pageSize: 50 }, }, } }
                                    pageSizeOptions={ [50] } />
                            </div> : null }
                    </div>
                </> : null
            }

            { idOfSelectedItem !== -1 ? <ItemDetailsPage idOfSelectedItem={ idOfSelectedItem } onBack={ backFromItemDetailPage } /> : null }

            { createItemPageIsOpen ? <CreateItemPage onSubmit={ handleItemCreation } onBack={ backFromCreateItemPage } /> : null }
        </>
    )
}