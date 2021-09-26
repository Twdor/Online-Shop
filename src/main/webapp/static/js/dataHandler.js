
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
        return await apiPost("/api/customer-signIn",{"email": `${email}`, "password": `${password}`});
    },
    signUpCustomer: async (name, email, password)=> {
        return await apiPost("/api/customer-signUp", {"name": `${name}`, "email": `${email}`, "password": `${password}`});
    },
    getUserData: async (customerId)=> {
        return await apiGet(`/api/customer-signIn?id=${customerId}`);
    },
    addProductToShoppingCart: async function (customerId, productId) {
        let request = await apiPost("/api/shoppingCart",{"customerId": `${customerId}`, "productId": `${productId}`, "quantity": "1", "option": "add"});
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


