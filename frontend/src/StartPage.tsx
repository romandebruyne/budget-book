type Props = { onContinue: () => void }

export function StartPage(props: Props) {
    return (
        <>
            <h1 className="press">Budget Book</h1>
            <h2 className="press">Track your expenses and income</h2>
            <div className="card">
                <button onClick={ props.onContinue }>
                    Continue
                </button><br /><br />
            </div>
            <p className="press">
                Continue via click on button.
            </p>
        </>
    )
}