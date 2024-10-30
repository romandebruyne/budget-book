import { useEffect, useState } from "react";
import { getItemById } from "./api";
import { EditItemPage } from "./EditItemPage";
import { DeleteItemPage } from "./DeleteItemPage";
import "./SimpleTable.css";

type Props = { idOfSelectedItem: number; onBack: () => void };

export function ItemDetailsPage(props: Props) {
    const [itemDetailsPageIsOpen, setItemDetailsPageIsOpen] = useState(true);
    const [editItemPageIsOpen, setEditItemPageIsOpen] = useState(false);
    const [deleteItemPageIsOpen, setDeleteItemPageIsOpen] = useState(false);
    const [id, setId] = useState(0);
    const [date, setDate] = useState("");
    const [description, setDescription] = useState("");
    const [category, setCategory] = useState("");
    const [amount, setAmount] = useState(0);

    useEffect(() => {
        getItemById(props.idOfSelectedItem).then(body => {
            if (body.data) {
                setId(body.data.id);
                setDate(body.data.date);
                setDescription(body.data.description);
                setCategory(body.data.category);
                setAmount(body.data.amount);
            }
        })
    }, [])

    function handleClickOnEditItemDetails() {
        setEditItemPageIsOpen(true);
        setItemDetailsPageIsOpen(false);
    }

    function handleClickOnDeleteItem() {
        setDeleteItemPageIsOpen(true);
        setItemDetailsPageIsOpen(false);
    }

    function handleItemDetailsEdit(itemDetailsWereEdited: boolean) {
        setEditItemPageIsOpen(false);
        setItemDetailsPageIsOpen(itemDetailsWereEdited);
    }

    function handleItemDeletion(itemWasDeleted: boolean) {
        setDeleteItemPageIsOpen(false);
        setItemDetailsPageIsOpen(!itemWasDeleted);
    }

    function backFromEditItemPage() {
        setEditItemPageIsOpen(false);
        setItemDetailsPageIsOpen(true);
    }

    function backFromDeleteItemPage() {
        setDeleteItemPageIsOpen(false);
        setItemDetailsPageIsOpen(true);
    }

    return (
        <>
            { itemDetailsPageIsOpen ?
                <>
                    <h2>Item details</h2>
                    <table className="simpletable">
                        <tr>
                            <th>Characteristic</th>
                            <th>Value</th>
                        </tr>
                        <tr>
                            <td>ID</td>
                            <td>{ id }</td>
                        </tr>       
                        <tr>
                            <td>Date</td>
                            <td>{ date }</td>
                        </tr>             
                        <tr>
                            <td>Description</td>
                            <td>{ description }</td>
                        </tr>       
                        <tr>
                            <td>Category</td>
                            <td>{ category }</td>
                        </tr>       
                        <tr>
                            <td>Amount</td>
                            <td>{ amount }</td>
                        </tr>       
                        <tr>
                            <td>Comment</td>
                            <td>{ "" }</td>
                        </tr>       
                    </table><br />

                    <button onClick={ handleClickOnEditItemDetails }>Edit item details</button><br /><br />
                    <button onClick={ handleClickOnDeleteItem }>Delete item</button><br /><br />
                    <button onClick={ props.onBack }>Back</button>
                </> : null
            }

            { !itemDetailsPageIsOpen && !deleteItemPageIsOpen && !editItemPageIsOpen ? 
                <>
                    <h2>Item was deleted!</h2>
                    <p>Return via 'Continue' button.</p>
                    <button onClick={ props.onBack }>Continue</button>
                </> : null
            }
            
            { editItemPageIsOpen ? <EditItemPage idOfSelectedItem={ id } onSubmit={ handleItemDetailsEdit } onBack={ backFromEditItemPage } /> : null }
            { deleteItemPageIsOpen ? <DeleteItemPage idOfSelectedItem={ id } onSubmit={ handleItemDeletion } onBack={ backFromDeleteItemPage } /> : null }            
        </>
    )

}