import { menuManager } from "./menuManager.js";
import { customerManager } from "./customerManager.js";

const init=async()=> {
    menuManager.manageMenuEvents().then(() => customerManager.manageLoginsEvents());
    await menuManager.checkIfUserIsLoggedIn();
}

init().then();