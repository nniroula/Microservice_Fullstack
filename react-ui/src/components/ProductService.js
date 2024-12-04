import axios from 'axios';
import { API_GATEWAY_BASE_URL_PRODUCT } from './Constant';

/* Invokes backend api endpoints */
class ProdcutService{
    getAllProducts(){
        return axios.get(API_GATEWAY_BASE_URL_PRODUCT);
    }

    // getProductById(productId){
    //     return axios.get(`${API_GATEWAY_BASE_URL_PRODUCT}/${productId}`);
    // }

    // createProduct(product){
    //     return axios.post(API_GATEWAY_BASE_URL_PRODUCT);
    // }
}

export default new ProdcutService();