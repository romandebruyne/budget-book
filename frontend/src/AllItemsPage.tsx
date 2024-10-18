import { useEffect, useState } from "react";
import { DataGrid, GridColDef, GridRowParams } from "@mui/x-data-grid";
import { BudgetBookItem, getAllItems } from "./api";
import "./Datagrid.css";

type Props = { onBack: () => void }

export function AllItemsPage(props: Props) {
    const [allItemsPageIsOpen, setAllItemsPageIsOpen] = useState(true);
    const [items, setItems] = useState<null | BudgetBookItem[]>(null);

    useEffect(() => {
        getAllItems().then(body => setItems(body.data))
    }, [])

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
                    <button onClick={ props.onBack }>Back</button><br /><br />

                    <div>
                        { items !== null ?
                            <div className="whitetable">
                                <DataGrid
                                    rows={ items }
                                    columns={ tableColumns }
                                    initialState={ { pagination: { paginationModel: { page: 0, pageSize: 50 }, }, } }
                                    pageSizeOptions={ [50] } />
                            </div> : null }
                    </div>
                </> : null
            }
        </>
    )
}