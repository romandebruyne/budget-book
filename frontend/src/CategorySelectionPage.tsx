import { useEffect, useState } from "react";
import { getAllCategories } from "./api";

type Props = { onSelect: (type: string) => void };

export function CategorySelectionPage(props: Props) {
    const [allCategories, setAllCategories] = useState<null | string[]>(null);
    const [category, setCategory] = useState("unknown");

    useEffect(() => {
        getAllCategories().then(body => setAllCategories(body.data))
    }, [])

    return (
        <div>
            <h2>Category Selection</h2>
            <p>Select category via click on text. Submit decision button below.</p>
            <ul>
                { allCategories !== null ?
                    allCategories.map((category) => (
                        <li onClick={ () => setCategory(category) }>{ category }</li>
                    )) : null
                }
            </ul>
            
            <button onClick={ () => props.onSelect(category) }>{ category } is selected.</button>
        </div>
    )
}