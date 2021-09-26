import { htmlFactory, htmlTemplates } from "./htmlFactory.js";
import { dataHandler } from "./dataHandler.js";
import { domManager } from "./domManager.js";

export const customerManager= {
    manageLoginsEvents: async () => {
        domManager.addEventListener(`#form-login`,'submit', async (event)=> {
            event.preventDefault();
            const invalidCredentials = document.getElementById('invalidCredentials');
            invalidCredentials.hidden = true;

            let email = event.currentTarget.email.value;
            let password = event.currentTarget.password.value;
            const customerData = await dataHandler.signInCustomer(email, password)
            if (customerData.invalidCredentials) {
                invalidCredentials.hidden = false;
            } else {
                document.cookie = `userId=${customerData.id}; flavor=choco; SameSite=None; Secure`;
                event.target.email.value = '';
                event.target.password.value = '';
                location.reload();
            }
        })
        domManager.addEventListener("#form-signUp", "submit",  async (event) => {
            event.preventDefault();
            let alreadyRegister = document.getElementById('alreadyRegister');
            alreadyRegister.hidden = true;

            let fullName = event.currentTarget.fullName.value;
            let email = event.currentTarget.email.value;
            let password = event.currentTarget.password.value;

            const customerData = await dataHandler.signUpCustomer(fullName, email, password);
            if (customerData.emailAlreadyRegister) {
                alreadyRegister.hidden = false;
            } else {
                document.cookie = `userId=${customerData.id}; flavor=choco; SameSite=None; Secure`;
                event.target.fullName.value = '';
                event.target.email.value = '';
                event.target.password.value = '';
                location.reload();
            }
        })
    },
    signOutCustomerEvent: ()=> {
        domManager.addEventListener("#signIn", "click", () => {
            document.cookie = "userId=; expires=Thu, 01 Jan 1977 00:00:00 UTC; path=/;";
            location.reload();
        })
    }
}
export const loadCustomerData=async(customerId, isUser)=> {
    addProductEvents(customerId, isUser);
    await loadUserShoppingCart(customerId);
    if (isUser === 'true') {
        customerManager.signOutCustomerEvent();
    }
}

const addProductEvents=(customerId, isUser)=> {
    const addToCartButtons = document.querySelectorAll("#addToCart");

    for (let btn of addToCartButtons) {
        btn.addEventListener("click", async (event) => {
            event.preventDefault();
            let productId = event.currentTarget.dataset.productId;
            let status = isUser === 'true' ? await dataHandler.addProductToShoppingCart(customerId, productId) : await dataHandler.addGuestProductToShoppingCart(productId);
            if (status) {
                return loadUserShoppingCart(customerId);
            }
        })
    }
}

const removeProductEvents=(customerId)=> {
    const removeCartButtons = document.querySelectorAll("#cardRemoval");

    for (let btn of removeCartButtons) {
        btn.addEventListener("click", async (event) => {
            event.preventDefault();
            let cartId = event.currentTarget.dataset.cartId;
            let status = await dataHandler.removeShoppingCart(cartId);
            if (status){
                return loadUserShoppingCart(customerId);
            }
        })
    }
}

const loadUserShoppingCart= async (customerId)=> {
    const customerCart = await dataHandler.getCustomerShoppingCart(customerId);
    const shoppingCartQuantityLabel = document.getElementById("shoppingCartQuantity");
    document.getElementById("navbarPillCartQuantity").textContent = customerCart.length;
    shoppingCartQuantityLabel.textContent = `Your Cart (${customerCart.length})`;
    domManager.deleteChild("#shoppingCart");

    if (customerCart.length !== 0) {
        const shoppingCartContent = htmlFactory(htmlTemplates.shoppingCart)(customerCart);
        shoppingCartQuantityLabel.textContent = `Your Cart (${customerCart.length})`;
        domManager.addChild("#shoppingCart", shoppingCartContent);
        document.getElementById("totalPriceContainer").hidden = false;

        let totalPrice = 0;
        for (let cart of customerCart) {
            totalPrice = Number(totalPrice + cart.defaultPrice * cart.quantity);
        }
        document.getElementById("totalPrice").textContent = customerCart[0].defaultCurrency + String(totalPrice);
        document.getElementById("checkoutBtn").hidden = false;
        return removeProductEvents(customerId);
    } else {
        domManager.addChild("#shoppingCart", `<h4 style="padding: 14rem 0 0 4rem">Your shopping cart is empty.</h4>`);
        document.getElementById("totalPriceContainer").hidden = true;
        document.getElementById("totalPrice").textContent = " ";
        document.getElementById("checkoutBtn").hidden = true;
    }
}
