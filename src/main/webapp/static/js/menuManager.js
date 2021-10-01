import { htmlFactory, htmlTemplates } from "./htmlFactory.js";
import { dataHandler } from "./dataHandler.js";
import { domManager } from "./domManager.js";
import { loadCustomerData } from "./customerManager.js";

export const menuManager = {
    manageMenuEvents: async () => {
        const subcategory = document.getElementById("subcategoryModal");
        const category = document.querySelectorAll("#category");

        for (let cat of category) {
            cat.onmouseover = async (e) => {
                let categoryId = e.currentTarget.dataset.categoryId;
                const data = await dataHandler.getProductSubcategoriesByCategoryId(categoryId);

                domManager.deleteChild("#subcategoryModalBody");
                const content = htmlFactory(htmlTemplates.subcategoryModalContent)(data);
                domManager.addChild("#subcategoryModalBody", content);

                subcategory.hidden = false;
                menuManager.hideSubcategoryOnClickOutside(subcategory);

                const subcategoryTitle = document.querySelectorAll("#subcategoryTitle");
                for (let title of subcategoryTitle) {
                    title.addEventListener("click", async (event) =>{
                        event.preventDefault();

                        if (event.currentTarget.dataset.productsNumber !== "0") {
                            let subcategoryId = event.currentTarget.dataset.subcategoryId;
                            const products = await dataHandler.getProducts(subcategoryId);
                            const productsContent = htmlFactory(htmlTemplates.products)(products);

                            document.getElementById("subcategoryMainTitle").textContent = event.target.dataset.subcategoryTitle;
                            domManager.deleteChild("#productsParent");
                            domManager.addChild("#productsParent", productsContent);
                        }
                    })
                }

            }
        }
    },
    hideSubcategoryOnClickOutside: (element)=> {
        const outsideClickListener = event => {
            if (event.target.id !=="category" && event.target.dataset.productsNumber !== "0" && isVisible(element)) {
                element.hidden = true;
                removeClickListener()
            }
        }

        const removeClickListener = () => {
            document.removeEventListener('click', outsideClickListener)
        }

        const isVisible = elem => !!elem && !!( elem.offsetWidth || elem.offsetHeight || elem.getClientRects().length )

        document.addEventListener('click', outsideClickListener)
    },
    checkIfUserIsLoggedIn: async ()=> {
        let customer = document.getElementById("customer");
        let customerId = customer.dataset.customerId;
        let userCountry = customer.dataset.userCountry;
        let userState = customer.dataset.state;
        await loadCustomerData(customerId, userCountry, userState);
    },
}
