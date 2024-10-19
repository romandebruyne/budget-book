import { useEffect, useState } from "react";
import { BudgetBookItem, editItem, getItemById } from "./api.ts";

type Props = { idOfSelectedItem: number, onSubmit: (itemDetailsWereEdited: boolean) => void, onBack: () => void };

export function EditItemPage(props: Props) {
    const [editItemPageIsOpen, setEditItemPageIsOpen] = useState(true);
    const [errorOccurredWarning, setErrorOccurredWarning] = useState(false);
    const [currentItem, setCurrentItem] = useState<null | BudgetBookItem>(null);
    const [id, setId] = useState(0);
    const [date, setDate] = useState("");
    const [description, setDescription] = useState("");
    const [category, setCategory] = useState("");
    const [amount, setAmount] = useState(0);

    useEffect(() => {
        getItemById(props.idOfSelectedItem).then(body => setCurrentItem(body.data));
    }, [])

    function handleGetCurrentValues() {
        if (currentItem !== null) {
            setId(currentItem.id);
            setDate(currentItem.date);
            setDescription(currentItem.description);
            setCategory(currentItem.category);
            setAmount(currentItem.amount);
        }
    }

    function handleErrorOccurred() {
        setEditItemPageIsOpen(false);
        setErrorOccurredWarning(true);
    }

    function showErrorOccurredWarning() {
        return (
            <div>
                <p>An error occurred, please try again!</p>
                <button onClick={ backFromError }>Back</button>
            </div>
        )
    }

    function handleSubmit() {
        editItem(id, date, description, category, amount).catch(handleErrorOccurred);
        props.onSubmit(true);
    }

    function backFromError() {
        setErrorOccurredWarning(false);
        setEditItemPageIsOpen(true);
    }

    return (
        <>
            { editItemPageIsOpen ?
                <>
                    <h2>Edit item</h2>
                    <p>Mandatory fields</p>
                    <input type="text" placeholder="Date (YYYY-MM-DD)" value={ date }
                        onChange={ event => setDate(event.target.value)} /><br />
                    <input type="text" placeholder="Description" value={ description }
                        onChange={ event => setDescription(event.target.value) } /><br />
                    <input type="text" placeholder="Category" value={ category }
                        onChange={ event => setCategory(event.target.value) } /><br />
                    <input type="number" placeholder="Amount (00.00)" value={ amount }
                        onChange={ event => setAmount(event.target.valueAsNumber) } /><br /><br />
                    
                    <button onClick={ handleGetCurrentValues }>Show current data</button><br /><br />
                    <button
                        disabled={ date === "" || description === "" || category === "" || amount.toString().length === 0 }
                        onClick={ handleSubmit }>Submit</button><br /><br />
                    <button onClick={ props.onBack }>Back</button>
                </> : null 
            }

            { errorOccurredWarning ? showErrorOccurredWarning() : null }
        </>
    )
    
}