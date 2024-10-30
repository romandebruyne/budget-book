//import { useState } from "react";
import { deleteItemById } from "./api";

type Props = { idOfSelectedItem: number, onSubmit: (itemWasDeleted: boolean) => void, onBack: () => void }

export function DeleteItemPage(props: Props) {
    //const [deleteItemPageIsOpen, setDeleteItemPageIsOpen] = useState(true);
    
    function handleSubmit(decision: boolean) {
        if (decision) {
            deleteItemById(props.idOfSelectedItem);
            props.onSubmit(true);
        } else {
            props.onSubmit(false);
        }
    }

    return (
        <>
            <h2>Delete item</h2>
            <p>Please confirm deletion:</p>
            <button onClick={ () => handleSubmit(true) }>YES</button>
            <button onClick={ () => handleSubmit(false) }>NO</button>
        </>
    )
}