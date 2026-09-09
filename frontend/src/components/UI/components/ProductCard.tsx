import React from "react";
import './ProductCard.css';

export type Promotion = {
    id: number;
    code: string;
    startDate: string;
    endDate: string;
    discountPercent: number;
};

export type Product = {
    id: number;
    name: string;
    price: number;
    promotion?: Promotion;
};

type ProductCardProps = {
    product: Product;
};

function ProductCard({ product }: ProductCardProps) {
    const now = new Date();

    const isPromotionActive =
        product.promotion !== undefined &&
        now >= new Date(product.promotion.startDate) &&
        now <= new Date(product.promotion.endDate);

    const discountedPrice = isPromotionActive
        ? product.price * (1 - product.promotion!.discountPercent / 100)
        : product.price;

    return (
        <div className="product-card">
            <div className="product-image"></div>
            <h3 className="product-name">{product.name}</h3>

            {isPromotionActive ? (
                <div className="product-price-wrapper">
                    <p className="product-price-original">{product.price} kr</p>
                    <p className="product-price-discounted">{discountedPrice.toFixed(0)} kr</p>
                </div>
            ) : (
                <p className="product-price">{product.price} kr</p>
            )}
        </div>
    );
}

export default ProductCard;