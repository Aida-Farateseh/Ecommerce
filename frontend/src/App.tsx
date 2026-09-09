import {BrowserRouter, Routes, Route} from 'react-router-dom';
import StorefrontPage from "./components/UI/Pages/StorefrontPage";
import React from "react";

function App(){
    return (
        <BrowserRouter>
            <Routes>
              <Route path="/" Component={StorefrontPage} />
            </Routes>
        </BrowserRouter>
    );
}
export default App;