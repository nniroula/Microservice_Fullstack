/* Back-End API Endpoints */

/* Product service endpoints */
// const PRODUCT_SERVICE_BASE_URL = "http://localhost:8081/api/products";
const API_GATEWAY_BASE_URL_PRODUCT = "http://localhost:8765/api/products";
// const PRODUCT_URL = `${BASE_URL}/products`;


/* Order sevice endpoints */
const API_GATEWAY_BASE_URL_ORDER = "http://localhost:8765/api/orders";
// const ORDER_SERVICE_BASE_URL = "http://localhost:8082/api/orders"


/* Inventory service endpoints */
const API_GATEWAY_BASE_URL_INVENTORY = "http://localhost:8765/api/inventory";
// const INVENTORY_SERVICE_BASE_URL = "http://localhost:8083/api/inventory"


export {
    API_GATEWAY_BASE_URL_PRODUCT,
    API_GATEWAY_BASE_URL_ORDER,
    API_GATEWAY_BASE_URL_INVENTORY
};