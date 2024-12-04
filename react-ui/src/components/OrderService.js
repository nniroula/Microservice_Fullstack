import axios from 'axios';
import {API_GATEWAY_BASE_URL_ORDER} from './Constant';


class OrderService {
    getAllOrders(){
        return axios.get(API_GATEWAY_BASE_URL_ORDER);
    }

    // getOrderBySkuCode(skuCode){
    //     return axios.get(`${ORDER_SERVICE_BASE_URL}/${skuCode}`);
    // }

    // createOrder(order){
    //     return axios.post(ORDER_SERVICE_BASE_URL);
    // }
}
export default new OrderService();