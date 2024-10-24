import { useState } from "react";
import { createItem } from "./api";

type Props = { onSubmit: (itemWasEdited: boolean) => void, onBack: () => void };

export function CreateItemPage(props: Props) {
    const [createItemPageIsOpen, setCreateItemPageIsOpen] = useState(true);
    const [errorOccurredWarning, setErrorOccurredWarning] = useState(false);

    const [date, setDate] = useState("");
    const [description, setDescription] = useState("");
    const [category, setCategory] = useState("");
    const [amount, setAmount] = useState(0);

    function handleItemCreation() {
        try {
            createItem(date, description, category, amount);
            props.onSubmit(true);
        } catch {
            handleErrorOccurred();
        }
    }

    function showErrorOccurredWarning() {
        return (
            <div>
                <p>Error occurred, please try again!</p>
                <button onClick={ backFromError }>Back</button>
            </div>
        )
    }

    function handleErrorOccurred() {
        setCreateItemPageIsOpen(false);
        setErrorOccurredWarning(true);
    }

    function backFromError() {
        setErrorOccurredWarning(false);
        setCreateItemPageIsOpen(true);
    }

    return (
        <>
            { createItemPageIsOpen ?
                <>
                <h2>Create item</h2>
                <p>Mandatory fields</p>
                <input type="text" placeholder="Date (YYYY-MM-DD)" value={ date }
                    onChange={ event => setDate(event.target.value) } /><br />
                <input type="text" placeholder="Description" value={ description }
                    onChange={ event => setDescription(event.target.value) } /><br />
                <input type="text" placeholder="Category" value={ category }
                    onChange={ event => setCategory(event.target.value) } /><br />
                <input type="number" placeholder="Amount" value={ amount }
                    onChange={ event => setAmount(event.target.valueAsNumber) } /><br />

                <button
                    disabled={ date === "" || description === "" || category === "" || amount.toString().length === 0 }
                    onClick={ handleItemCreation }
                >Submit
                </button>
                <button onClick={ props.onBack }>Back</button>
            </> : null }

            { errorOccurredWarning ? showErrorOccurredWarning() : null }
        </>
    )
}