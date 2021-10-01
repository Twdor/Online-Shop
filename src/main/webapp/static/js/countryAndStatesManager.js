import { dataHandler } from "./dataHandler.js";

export let allCountries = null;
export let allStates = null;

const manageCountryAndStates=async (userCountry, userState)=> {

    if (allCountries === null) {
        const data = await dataHandler.getCountryAndStates();
        allCountries = data['country'];
        allStates = data['states'];
    }

    let option =  '';
    option += '<option value="">Choose...</option>';
    for(let countryCode in allCountries){
        let selected = (allCountries[countryCode] === userCountry) ? ' selected' : '';
        option += '<option value="'+countryCode+'"'+selected+'>'+allCountries[countryCode]+'</option>';
    }

    document.getElementById('country').innerHTML = option;

    const textBox = '<label for="state" class="form-label">State</label>' +
                    '<input type="text" class="form-control" id="state" required>' +
                    '<div class="invalid-feedback">Please provide a valid state.</div>';
    let stateSelectParent = document.getElementById("stateSelectParent");

    const create_states_dropdown=(userState)=> {
        let countryCode = document.getElementById("country").value;
        let states = allStates[countryCode];
        if(!states){
            stateSelectParent.innerHTML = textBox;
            return;
        }
        let option = '';
        if (states.length > 0) {
            option = '<label for="state" class="form-label">State</label>' +
                     '<select class="form-select" id="state" required="">\n' +
                     '<option value="">Choose...</option>';
            for (let i = 0; i < states.length; i++) {
                let selected = userState && states[i].name === userState? ' selected' : '';

                option += '<option value="'+states[i].code+'"'+selected+'>'+states[i].name+'</option>';
            }
            option += '</select><div class="invalid-feedback">Please provide a valid state.</div>';
        } else {
            option = textBox
        }
        stateSelectParent.innerHTML = option;
    }
    const countrySelect = document.getElementById("country");
    countrySelect.addEventListener('change', create_states_dropdown);
    create_states_dropdown(userState);
}

export const addCountryAndStateEvent=(userCountry, userState)=>{
    document.getElementById("billingInfo").addEventListener("click", () =>{
        manageCountryAndStates(userCountry, userState).then();
    })
}

