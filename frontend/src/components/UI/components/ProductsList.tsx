import React from "react";
import ProductCard, { type Product } from "./ProductCard.tsx";

const productsList: Product[] = [
    {
        id: 1,
        name: "Blå tröja",
        price: 399,
    },
    {
        id: 2,
        name: "Svarta jeans",
        price: 599,
        promotion: {
            id: 10,
            code: "sommar25",
            startDate: "2026-07-01",
            endDate: "2026-07-31",
            discountPercent: 25,
        },
    },
    {
        id: 3,
        name: "Vit skjorta",
        price: 299,
    },
];

function ProductsList() {
    return (
        <div className="product-List">
            {productsList.map((product) => (
                <ProductCard key={product.id} product={product} />
            ))}
        </div>
    );
}

export default ProductsList;