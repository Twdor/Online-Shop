export const htmlTemplates = {
    subcategoryModalContent: 1,
    shoppingCart: 2,
    products: 3,
}

export const htmlFactory=(template)=> {
    switch (template) {
        case htmlTemplates.subcategoryModalContent:
            return subcategoryModalContentBuilder
        case htmlTemplates.shoppingCart:
            return shoppingCartContentBuilder
        case htmlTemplates.products:
            return productsContentBuilder
        default:
            console.error("Undefined template: " + template)
            return () => { return "" }
    }
}

const subcategoryModalContentBuilder=(subcategories)=> {
    return `<div class="transparent">
                ${subcategories.map(subcategory => { 
                    return `<a style="border-color: transparent" id="subcategoryTitle" 
                                data-subcategory-id="${subcategory.id}" 
                                data-products-number="${subcategory.productsNumber}" 
                                data-bs-dismiss="${subcategory.productsNumber !== 0 ? 'modal' : ''}" 
                                class="btn btn-dark transparent">${subcategory.name}
                                <span class="badge bg-light text-dark rounded-pill align-text-bottom">${subcategory.productsNumber}</span>
                            </a><br><br>`;
                }).join('')}
            </div>`;
}

const shoppingCartContentBuilder=(shoppingCart)=> {
    return `<ul class="list-group list-group-xl list-group-flush">
                ${shoppingCart.map(cart => {
                    return `
                            <li style="padding: 30px" class="list-group-item">
                                <div class="row align-items-center">
                                    <div class="col-4">
                                        <!-- Image -->
                                        <a href="/">
                                            <img class="img-fluid" src="/static/img/product_1.jpg" alt="...">
                                        </a>
                                    </div>
                                    <div class="col-8">
                                        <!-- Title -->
                                        <p class="font-size-sm font-weight-bold mb-6">
                                            <a class="text-body" href="#">${cart.name}</a><br>
                                            <span class="text-muted">${cart.price}</span><br>
                                            <span class="text-muted">${'QTY: '+cart.quantity}</span>
                                        </p>
                                        <!--Footer -->
                                        <div class="d-flex align-items-center">
                                            <!-- Select -->
                                            <!-- Remove -->
                                            <a id="cardRemoval" data-cart-id="${cart.id}" class="btn btn-light float-end">&maltese; Remove</a>
                                        </div>
                                    </div>
                                </div>
                            </li>`;
                }).join('')}
            </ul>`
}

const productsContentBuilder=(products)=> {
    return `
            <div style="padding-bottom: 2rem" class="transparent">
                ${products.map(prod => {
                    return `
                            <div class="card transparent">
                                <img class="" src="/static/img/product_1.jpg" alt="" width="400" height="250"/>
                                <div class="card-header transparent">
                                    <h4 class="card-title">${prod.name}</h4>
                                    <p class="card-text">${prod.description}</p>
                                </div>
                                <div class="card-body transparent">
                                    <div class="card-text transparent">
                                        <p class="lead">${prod.price}</p>
                                    </div>
                                    <div class="card-text transparent">
                                        <button id="addToCart" data-product-id="${prod.id}" type="button" class="btn btn-success">Add to cart</button>
                                    </div>
                                </div>
                            </div>`;
                    }).join('')}
            </div>`;
}
