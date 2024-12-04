import React, { useEffect, useState } from 'react';
import ProductService from './ProductService';
import 'bootstrap/dist/css/bootstrap.min.css';

const DisplayProducts = () => {
    const [products, setProducts] = useState([]);

    useEffect(() => {
        ProductService.getAllProducts().then((response) => {
            setProducts(response.data);
        });
    }, []);

    const items = [];
    for(let product in products){
        items.push(products[product]);
    }

    return (
        <div className="container mt-5">
            <h2 class="bg-warning">Products Table </h2>

            <table class="table table-striped">
                <thead >
                    <tr class="bg-warning">
                        <th scope="col" class="bg-success">Name</th>
                        <th scope="col" class="bg-success">Description</th>
                        <th scope="col" class="bg-success">Sku Code</th>
                        <th scope="col" class="bg-success">Price</th>
                        <th scope="col" class="bg-success">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <tr >
                        <td> {items.map(product => <tr> {product.name} </tr>)} </td> 
                        <td > {items.map(product =>  <tr>{product.description} </tr>)} </td> 
                        <td >{items.map(product => <tr>{product.skuCode}</tr>)}</td> 
                        <td >{products.map(product => <tr>{product.price}</tr>)}</td> 
                        <td >{products.map(product => <tr><a href="">Add</a></tr>)}</td> 
                    </tr>
                </tbody>
            </table>
            </div>
    );
};

export default DisplayProducts;