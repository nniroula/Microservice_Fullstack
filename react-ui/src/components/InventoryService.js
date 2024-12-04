import axios from 'axios';
import {API_GATEWAY_BASE_URL_INVENTORY} from './Constant';


class InventoryService {
    getAllInventories(){
        return axios.get(API_GATEWAY_BASE_URL_INVENTORY);
    }

    // getOrderBySkuCode(skuCode){
    //     return axios.get(`${ORDER_SERVICE_BASE_URL}/${skuCode}`);
    // }

    // createOrder(order){
    //     return axios.post(ORDER_SERVICE_BASE_URL);
    // }
}
export default new InventoryService();