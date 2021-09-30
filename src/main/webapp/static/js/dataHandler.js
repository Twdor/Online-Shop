
export const dataHandler = {
    getProducts: async (subcategoryId)=> {
        return await apiGet(`/api/products?subcategoryId=${subcategoryId}`);
    },
    getCustomerShoppingCart: async (customerId)=> {
        return await apiGet(`/api/shoppingCart?id=${customerId}`);
    },
    getProductSubcategoriesByCategoryId: async (id)=> {
        return await apiGet(`/api/productSubcategory?id=${id}`);
    },
    signInCustomer: async (email, password)=> {
        return await apiPost("/api/user-signIn",{"email": `${email}`, "password": `${password}`});
    },
    signUpCustomer: async (name, email, password)=> {
        return await apiPost("/api/user-signUp", {"name": `${name}`, "email": `${email}`, "password": `${password}`});
    },
    getUserData: async (userId)=> {
        return await apiGet(`/api/user?id=${userId}`);
    },
    updateUserInfo: async function (id, name, email, phoneNumber, country, state, city, zipcode, address) {
        let request = await apiPost("/api/user",{
            "id": `${id}`,
            "name": `${name}`,
            "email": `${email}`,
            "phoneNumber": `${phoneNumber}`,
            "country": `${country}`,
            "state": `${state}`,
            "city": `${city}`,
            "zipcode": `${zipcode}`,
            "address": `${address}`});
        if (request) {
            return request;
        }
        return false;
    },
    addProductToShoppingCart: async function (customerId, productId) {
        let request = await apiPost("/api/shoppingCart",{"customerId": `${customerId}`, "productId": `${productId}`, "quantity": "1", "option": "add to user"});
        if (request) {
            return request;
        }
        return false;
    },
    addGuestProductToShoppingCart: async function (productId) {
        let request = await apiPost("/api/shoppingCart",{"productId": `${productId}`, "quantity": "1", "option": "add to guest"});
        if (request) {
            return request;
        }
        return false;
    },
    removeShoppingCart: async function (cartId) {
        let request = await apiPost("/api/shoppingCart",{"id": `${cartId}`, "quantity": "0", "option": "remove"});
        if (request) {
            return request;
        }
        return false;
    },
    addOrder: async function (orderId, customerId, customerType, orderDate, orderStatus, totalPrice) {
        let request = await apiPost("/api/order",
            {
                "orderId": `${orderId}`,
                "customerId": `${customerId}`,
                "customerType": `${customerType}`,
                "orderDate": `${orderDate}`,
                "orderStatus": `${orderStatus}`,
                "totalPrice": `${totalPrice}`
            });
        if (request) {
            return request;
        }
        return false;
    },
    getUserOrders: async (userId)=> {
        return await apiGet(`/api/order?userId=${userId}`);
    },
    getCountryAndStates: async ()=> {
        return await apiOptions("/");
    },
};

const apiGet= async (url)=> {
    const response = await fetch(url);
    if (response.ok) {
        return response.json();
    } else {
        alert("There was a problem! Please Try again later");
        console.log("No response!");
    }
}

async function apiPost(url, payload) {
    const response = await fetch(url, {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(payload),
    })
    if (response.ok) {
        return await response.json();
    } else {
        alert("There was a problem! Please Try again later");
        console.log("No response!");
    }
}

const apiOptions= async(url)=>{
    const response = await fetch(url, {
        method: 'OPTIONS',
        headers: {'Content-Type': 'application/json'}
    });
    if (response.ok) {
        return response.json();
    } else {
        alert("There was a problem! Please Try again later");
        console.log("No response!");
    }
}

