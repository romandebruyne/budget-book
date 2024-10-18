import { useState } from 'react'
import { StartPage } from './StartPage';
import { NavigationPage } from './NavigationPage';
import './App.css'

function App() {
  const [startPageIsOpen, setStartPageIsOpen] = useState(true);
  const [navigationPageIsOpen, setNavigationPageIsOpen] = useState(false);

  function handleClickOnContinue() {
    setStartPageIsOpen(false);
    setNavigationPageIsOpen(true);
  }

  function backFromNavigationPage() {
    setNavigationPageIsOpen(false);
    setStartPageIsOpen(true);
  }

  return (
    <>
      { /* Start Page */ }
      { startPageIsOpen ? <StartPage onContinue={ handleClickOnContinue } /> : null }

      { /* Navigation Page */ }
      { navigationPageIsOpen ? <NavigationPage onBack={ backFromNavigationPage } /> : null }
    </>
  )
}

export default App
