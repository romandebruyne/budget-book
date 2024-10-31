import { useState } from "react";
import { AllItemsPage } from "./AllItemsPage";
import { ItemsByCategoryPage } from "./ItemsByCategoryPage";

type Props = { onBack: () => void };

export function NavigationPage(props: Props) {
    const [navigationPageIsOpen, setNavigationPageIsOpen] = useState(true);
    const [allItemsPageIsOpen, setAllItemsPageIsOpen] = useState(false);
    const [itemsByCategoryPageIsOpen, setItemsByCategoryPageIsOpen] = useState(false);

    function handleClickOnShowAllItems() {
        setAllItemsPageIsOpen(true);
        setNavigationPageIsOpen(false);
    }

    function handleClickOnShowItemsByCategory() {
        setItemsByCategoryPageIsOpen(true);
        setNavigationPageIsOpen(false);
    }

    function backFromAllItemsPage() {
        setAllItemsPageIsOpen(false);
        setNavigationPageIsOpen(true);
    }

    function backFromItemsByCategoryPage() {
        setItemsByCategoryPageIsOpen(false);
        setNavigationPageIsOpen(true);
    }
    
    return (
        <>
            { navigationPageIsOpen ?
                <>
                    <h2>Welcome!</h2>
                    <button onClick={ handleClickOnShowAllItems }>Show all items</button><br /><br /> 
                    <button onClick={ handleClickOnShowItemsByCategory }>Show items by category</button><br /><br /> 
                    <button onClick={ props.onBack }>Back</button>
                </> : null }

            { allItemsPageIsOpen ? <AllItemsPage onBack={ backFromAllItemsPage } /> : null }
            { itemsByCategoryPageIsOpen ? <ItemsByCategoryPage onBack={ backFromItemsByCategoryPage } /> : null }
        </>
    )
}