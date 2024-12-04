import React, { useEffect, useState } from 'react';
import OrderService from './OrderService';
import 'bootstrap/dist/css/bootstrap.min.css';

const DisplayOrders = () => {
    const [orders, setOrders] = useState([]);

    useEffect(() => {
        OrderService.getAllOrders().then((response) => {
            setOrders(response.data);
        });
    }, []);

    const items = [];
    for(let order in orders){
        items.push(orders[order]);
    }

    return (
        <div className="container mt-5">
            <h2 class="bg-warning">Orders Table </h2>

            <table class="table table-striped">
                <thead >
                    <tr class="bg-warning">
                        <th scope="col" class="bg-success">Name</th>
                        <th scope="col" class="bg-success">Description</th>
                        <th scope="col" class="bg-success">Sku Code</th>
                        <th scope="col" class="bg-success">Unit Price</th>
                        <th scope="col" class="bg-success">Quantity</th>
                        <th scope="col" class="bg-success">Total Price</th>
                        <th scope="col" class="bg-success">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <tr >
                        <td> {items.map(order => <tr> {order.productName} </tr>)} </td> 
                        <td > {items.map(order =>  <tr>{order.productDescription} </tr>)} </td> 
                        <td >{items.map(order => <tr>{order.skuCode}</tr>)}</td> 
                        <td >{items.map(order => <tr>{order.unitPrice}</tr>)}</td> 
                        <td >{items.map(order => <tr>{order.quantity}</tr>)}</td> 
                        <td >{items.map(order => <tr>{order.totalPrice}</tr>)}</td> 
                        
                        <td >{orders.map(order => <tr><a href="">Add</a></tr>)}</td> 
            
                    </tr>
                </tbody>
            </table>
            </div>
    );
};

export default DisplayOrders;