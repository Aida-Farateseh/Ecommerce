import React from "react";
import {Storefront }from "../components/Storefront";




function StorefrontPage(){
    return <div><Storefront onSearch={function(query: string): void {
        throw new Error("Function not implemented.");
    } }/></div>;
}








export default StorefrontPage;