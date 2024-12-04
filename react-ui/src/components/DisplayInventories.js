import React, { useEffect, useState } from 'react';
import InventoryService from './InventoryService';
import 'bootstrap/dist/css/bootstrap.min.css';

const DisplayInventories = () => {
    const [inventories, setInventories] = useState([]);

    useEffect(() => {
        InventoryService.getAllInventories().then((response) => {
            setInventories(response.data);
        });
    }, []);

    const items = [];
    for(let product in inventories){
        items.push(inventories[product]);
    }

    return (
        <div className="container mt-5">
            <h2 class="bg-warning">Inventories Table </h2>

            <table class="table table-striped">
                <thead >
                    <tr class="bg-warning">
                        <th scope="col" class="bg-success">SKU Code</th>
                        <th scope="col" class="bg-success">Quantity</th>

                        <th scope="col" class="bg-success">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <tr >
                        <td> {items.map(inventory => <tr> {inventory.skuCode} </tr>)} </td> 
                        <td > {items.map(inventory =>  <tr>{inventory.quantity} </tr>)} </td> 
                        <td >{inventories.map(inventory => <tr><a href="">Delete</a></tr>)}</td> 
                    </tr>
                </tbody>
            </table>
            </div>
    );
};

export default DisplayInventories;