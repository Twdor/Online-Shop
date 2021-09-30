import { dataHandler } from "./dataHandler.js";
import {domManager} from "./domManager.js";


export const sandBoxManager= {
    loadPaymentButtons: async (shoppingCart, totalPrice, customerId, customerType)=> {
        await paypal_sdk.Buttons({
            // Sets up the transaction when a payment button is clicked
            createOrder: (data, actions)=> {
                return actions.order.create({
                    "purchase_units": [{
                        "amount": {
                            "currency_code": shoppingCart[0].productDefaultCurrency,
                            "value": String(totalPrice),
                            "breakdown": {
                                "item_total": {  /* Required when including the `items` array */
                                    "currency_code": shoppingCart[0].productDefaultCurrency,
                                    "value": String(totalPrice)
                                }
                            }
                        },
                        "items": JSON.parse(JSON.stringify(shoppingCart.map((cart)=> {
                            return {
                                        "name": cart.productName,
                                        "unit_amount": {
                                            "currency_code": cart.productDefaultCurrency,
                                            "value": cart.productDefaultPrice
                                            },
                                        "quantity": cart.quantity
                                    }})))
                    }]
                });
            },
            onApprove: (data, actions)=> {
                return actions.order.capture().then(async (orderData)=> {
                    // Successful capture! For dev/demo purposes:
                    console.log('Capture result', orderData);
                    let transaction = orderData.purchase_units[0].payments.captures[0];
                    let orderDate = orderData.update_time;
                    let orderStatus = await dataHandler.addOrder(transaction.id, customerId, customerType, orderDate, transaction.status, totalPrice);
                    if (orderStatus) {
                        document.getElementById("success").hidden = false;
                        document.getElementById("checkoutParent").hidden = true;
                        document.getElementById("closeCheckoutBtn").textContent = "Close";
                        domManager.deleteChild("#paypal-button-container");
                        document.getElementById("navbarPillCartQuantity").textContent = "0";
                        domManager.deleteChild("#shoppingCart");
                        document.getElementById("shoppingCartQuantity").textContent = "Your Cart (0))"
                        domManager.addChild("#shoppingCart", `<h4 style="padding: 14rem 0 0 4rem">Your shopping cart is empty.</h4>`);
                        document.getElementById("totalPriceContainer").hidden = true;
                        document.getElementById("totalPrice").textContent = " ";
                        document.getElementById("checkoutBtn").hidden = true;
                        shoppingCart.map(async cart => await dataHandler.removeShoppingCart(cart.cartId));
                    }
                });
            }
        }).render('#paypal-button-container');
    },
}
