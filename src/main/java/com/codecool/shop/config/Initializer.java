package com.codecool.shop.config;

import com.codecool.shop.model.ProductModel;
import com.codecool.shop.model.ProductCategoryModel;
import com.codecool.shop.model.ProductSubcategoryModel;
import com.codecool.shop.model.SupplierModel;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;
import java.sql.SQLException;


@WebListener
public class Initializer extends InitializerFields implements ServletContextListener {


    public Initializer() throws SQLException {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        if (!productService.isInitializer()) {
            addSuppliers();
            addCategories();
            addSubcategories();
            addProducts();
        }
    }

    private void addCategories() {
        televisionsAndAccessories = new ProductCategoryModel("Televisions And Accessories", "Electronics");
        productService.add(televisionsAndAccessories);

        homeAppliances = new ProductCategoryModel("Home Appliances", "Home Appliances");
        productService.add(homeAppliances);

        phonesAndWearables = new ProductCategoryModel("Phones and Wearables", "Electronics");
        productService.add(phonesAndWearables);

        computersAndTablets = new ProductCategoryModel("Computer and Tablets", "Electronics");
        productService.add(computersAndTablets);

        kitchenAppliances = new ProductCategoryModel("Kitchen Appliances", "Electronics");
        productService.add(kitchenAppliances);

        audioAndVideo = new ProductCategoryModel("Audio & Video", "Electronics");
        productService.add(audioAndVideo);

        healthAndFitness = new ProductCategoryModel("Health & Fitness", "Electronics");
        productService.add(healthAndFitness);

        groomingAndPersonalCare = new ProductCategoryModel("Grooming & Personal Care", "Electronics");
        productService.add(groomingAndPersonalCare);

        camerasAndAccessories = new ProductCategoryModel("Cameras & Accessories", "Electronics");
        productService.add(camerasAndAccessories);

        gaming = new ProductCategoryModel("Gaming", "Electronics");
        productService.add(gaming);

    }

    private void addSuppliers() {
        amazon = new SupplierModel("Amazon", "Digital content and services");
        productService.add(amazon);
        lenovo = new SupplierModel("Lenovo", "Computers");
        productService.add(lenovo);
    }

    private void addSubcategories() {
        ledTvS = new ProductSubcategoryModel("LED TVs", televisionsAndAccessories);
        productService.add(ledTvS);

        fullHdTvs = new ProductSubcategoryModel("Full HD TVs", televisionsAndAccessories);
        productService.add(fullHdTvs);

        smartTvs = new ProductSubcategoryModel("Smart TVs", televisionsAndAccessories);
        productService.add(smartTvs);

        androidSmartTvs = new ProductSubcategoryModel("Android Smart TVs", televisionsAndAccessories);
        productService.add(androidSmartTvs);

        largeScreenTvs = new ProductSubcategoryModel("Large Screen TVs", televisionsAndAccessories);
        productService.add(largeScreenTvs);

        mediumScreenTvs = new ProductSubcategoryModel("Medium Screen TVs", televisionsAndAccessories);
        productService.add(mediumScreenTvs);

        smallScreenTvs = new ProductSubcategoryModel("Small Screen TVs", televisionsAndAccessories);
        productService.add(smallScreenTvs);

        gamingTvs = new ProductSubcategoryModel("Gaming TVs", televisionsAndAccessories);
        productService.add(gamingTvs);

        fullHdProjectors = new ProductSubcategoryModel("Full HD Projectors", televisionsAndAccessories);
        productService.add(fullHdProjectors);

        tvCablesAndConnectors = new ProductSubcategoryModel("TV Cables and Connectors", televisionsAndAccessories);
        productService.add(tvCablesAndConnectors);

        remotes = new ProductSubcategoryModel("Remotes", televisionsAndAccessories);
        productService.add(remotes);


        washingMachines = new ProductSubcategoryModel("Washing Machines", homeAppliances);
        productService.add(washingMachines);

        airHumidifiers = new ProductSubcategoryModel("Air Humidifiers", homeAppliances);
        productService.add(airHumidifiers);

        airDehumidifiers = new ProductSubcategoryModel("Air Dehumidifiers", homeAppliances);
        productService.add(airDehumidifiers);

        airCoolers = new ProductSubcategoryModel("Air Coolers", homeAppliances);
        productService.add(airCoolers);

        fans = new ProductSubcategoryModel("Fans", homeAppliances);
        productService.add(fans);

        lightStrips = new ProductSubcategoryModel("LightStrips", homeAppliances);
        productService.add(lightStrips);

        roomHeaters = new ProductSubcategoryModel("Room Heaters", homeAppliances);
        productService.add(roomHeaters);

        miniFreezeRefrigerators = new ProductSubcategoryModel("Mini Freeze Refrigerators", homeAppliances);
        productService.add(miniFreezeRefrigerators);

        tripleDoorRefrigerators = new ProductSubcategoryModel("Triple Door Refrigerators", homeAppliances);
        productService.add(tripleDoorRefrigerators);

        sideBySideRefrigerators = new ProductSubcategoryModel("Side by Side Refrigerators", homeAppliances);
        productService.add(sideBySideRefrigerators);

        wineCoolers = new ProductSubcategoryModel("Wine Coolers", homeAppliances);
        productService.add(wineCoolers);

        airConditioners = new ProductSubcategoryModel("Air Conditioners", homeAppliances);
        productService.add(airConditioners);

        irons = new ProductSubcategoryModel("Irons", homeAppliances);
        productService.add(irons);

        vacuumCleaners = new ProductSubcategoryModel("Vacuum Cleaners", homeAppliances);
        productService.add(vacuumCleaners);

        solarLights = new ProductSubcategoryModel("Solar Lights", homeAppliances);
        productService.add(solarLights);

        smartPlugs = new ProductSubcategoryModel("Smart Plugs", homeAppliances);
        productService.add(smartPlugs);


        mobilePhones = new ProductSubcategoryModel("Mobile Phones", phonesAndWearables);
        productService.add(mobilePhones);

        androidPhones = new ProductSubcategoryModel("Android Phones", phonesAndWearables);
        productService.add(androidPhones);

        mobileAntivirus = new ProductSubcategoryModel("Mobile Antivirus", phonesAndWearables);
        productService.add(mobileAntivirus);

        iPhones = new ProductSubcategoryModel("iPhones", phonesAndWearables);
        productService.add(iPhones);

        smartWatches = new ProductSubcategoryModel("Smart Watches", phonesAndWearables);
        productService.add(smartWatches);

        screenProtectors = new ProductSubcategoryModel("Screen Protectors", phonesAndWearables);
        productService.add(screenProtectors);

        cordlessPhones = new ProductSubcategoryModel("Cordless Phones", phonesAndWearables);
        productService.add(cordlessPhones);

        usbCablesAndConnectors = new ProductSubcategoryModel("USB Cables & Connectors", phonesAndWearables);
        productService.add(usbCablesAndConnectors);

        earphones = new ProductSubcategoryModel("Earphones", phonesAndWearables);
        productService.add(earphones);

        headPhones = new ProductSubcategoryModel("HeadPhones", phonesAndWearables);
        productService.add(headPhones);

        vrHeadset = new ProductSubcategoryModel("VR Headset", phonesAndWearables);
        productService.add(vrHeadset);

        tripods = new ProductSubcategoryModel("Tripods", phonesAndWearables);
        productService.add(tripods);

        smartBands = new ProductSubcategoryModel("Smart Bands", phonesAndWearables);
        productService.add(smartBands);

        landlinePhones = new ProductSubcategoryModel("Landline Phones", phonesAndWearables);
        productService.add(landlinePhones);

        selfieSticks = new ProductSubcategoryModel("Selfie Sticks", phonesAndWearables);
        productService.add(selfieSticks);


        windowsLaptops = new ProductSubcategoryModel("Windows Laptops", computersAndTablets);
        productService.add(windowsLaptops);

        laptops = new ProductSubcategoryModel("Laptops", computersAndTablets);
        productService.add(laptops);

        macBooks = new ProductSubcategoryModel("MacBooks", computersAndTablets);
        productService.add(macBooks);

        androidTablets = new ProductSubcategoryModel("Android Tablets", computersAndTablets);
        productService.add(androidTablets);

        iPads = new ProductSubcategoryModel("iPads", computersAndTablets);
        productService.add(iPads);

        iMacs = new ProductSubcategoryModel("iMacs", computersAndTablets);
        productService.add(iMacs);

        gamingLaptops = new ProductSubcategoryModel("Gaming Laptops", computersAndTablets);
        productService.add(gamingLaptops);

        desktops = new ProductSubcategoryModel("Desktops", computersAndTablets);
        productService.add(desktops);

        monitors = new ProductSubcategoryModel("Monitors", computersAndTablets);
        productService.add(monitors);

        keyboards = new ProductSubcategoryModel("Keyboards", computersAndTablets);
        productService.add(keyboards);

        laptopBags = new ProductSubcategoryModel("Laptop Bags", computersAndTablets);
        productService.add(laptopBags);

        operatingSystems = new ProductSubcategoryModel("Operating Systems", computersAndTablets);
        productService.add(operatingSystems);

        antivirusAndInternetSecurity = new ProductSubcategoryModel("Antivirus & Internet Security", computersAndTablets);
        productService.add(antivirusAndInternetSecurity);

        multimediaSpeakers = new ProductSubcategoryModel("Multimedia Speakers", computersAndTablets);
        productService.add(multimediaSpeakers);

        networkAdapters = new ProductSubcategoryModel("Network Adapters", computersAndTablets);
        productService.add(networkAdapters);

        digitalPads = new ProductSubcategoryModel("Digital Pads", computersAndTablets);
        productService.add(digitalPads);


        dishwashers = new ProductSubcategoryModel("Dishwashers", kitchenAppliances);
        productService.add(dishwashers);

        handMixers = new ProductSubcategoryModel("Hand Mixers", kitchenAppliances);
        productService.add(handMixers);

        riceCookers = new ProductSubcategoryModel("Rice Cookers", kitchenAppliances);
        productService.add(riceCookers);

        teaMakers = new ProductSubcategoryModel("Tea Makers", kitchenAppliances);
        productService.add(teaMakers);

        soupMakers = new ProductSubcategoryModel("Soup Makers", kitchenAppliances);
        productService.add(soupMakers);

        choppers = new ProductSubcategoryModel("Choppers", kitchenAppliances);
        productService.add(choppers);

        watterBottles = new ProductSubcategoryModel("Watter Bottles", kitchenAppliances);
        productService.add(watterBottles);

        hobs = new ProductSubcategoryModel("Hobs", kitchenAppliances);
        productService.add(hobs);

        juicerAndBlenders = new ProductSubcategoryModel("Juicer & Blenders", kitchenAppliances);
        productService.add(juicerAndBlenders);

        desertMakers = new ProductSubcategoryModel("Desert Makers", kitchenAppliances);
        productService.add(desertMakers);

        gasStoves = new ProductSubcategoryModel("Gas Stoves", kitchenAppliances);
        productService.add(gasStoves);

        foodProcessors = new ProductSubcategoryModel("Food Processors", kitchenAppliances);
        productService.add(foodProcessors);

        storageContainers = new ProductSubcategoryModel("Storage Containers", kitchenAppliances);
        productService.add(storageContainers);

        toasters = new ProductSubcategoryModel("Toasters", kitchenAppliances);
        productService.add(toasters);

        ovens = new ProductSubcategoryModel("Ovens", kitchenAppliances);
        productService.add(ovens);

        waterPurifiers = new ProductSubcategoryModel("Water Purifiers", kitchenAppliances);
        productService.add(waterPurifiers);


        smartSpeakers = new ProductSubcategoryModel("Smart Speakers", audioAndVideo);
        productService.add(smartSpeakers);

        partySpeakers = new ProductSubcategoryModel("Party Speakers", audioAndVideo);
        productService.add(partySpeakers);

        homeTheatres = new ProductSubcategoryModel("Home Theatres", audioAndVideo);
        productService.add(homeTheatres);

        audioAmplifiers = new ProductSubcategoryModel("Audio Amplifiers", audioAndVideo);
        productService.add(audioAmplifiers);

        soundBars = new ProductSubcategoryModel("Sound Bars", audioAndVideo);
        productService.add(soundBars);

        gamingHeadset = new ProductSubcategoryModel("Gaming Headset", audioAndVideo);
        productService.add(gamingHeadset);

        djMachinesAndSpeakers = new ProductSubcategoryModel("Dj Machines & Speakers", audioAndVideo);
        productService.add(djMachinesAndSpeakers);

        earPhones = new ProductSubcategoryModel("Ear Phones", audioAndVideo);
        productService.add(earPhones);

        karaokeMics = new ProductSubcategoryModel("Karaoke Mics", audioAndVideo);
        productService.add(karaokeMics);

        mediaPlayers = new ProductSubcategoryModel("Media Players", audioAndVideo);
        productService.add(mediaPlayers);

        audiCasesAndCovers = new ProductSubcategoryModel("Audi Cases & Covers", audioAndVideo);
        productService.add(audiCasesAndCovers);


        massagers = new ProductSubcategoryModel("Massagers", healthAndFitness);
        productService.add(massagers);

        hearingAids = new ProductSubcategoryModel("Hearing Aids", healthAndFitness);
        productService.add(hearingAids);

        healthMonitors = new ProductSubcategoryModel("Health Monitors", healthAndFitness);
        productService.add(healthMonitors);

        steamVaporizers = new ProductSubcategoryModel("Steam Vaporizers", healthAndFitness);
        productService.add(steamVaporizers);

        exerciseBikes = new ProductSubcategoryModel("Exercise Bikes", healthAndFitness);
        productService.add(exerciseBikes);

        weightsAndScales = new ProductSubcategoryModel("Weights & Scales", healthAndFitness);
        productService.add(weightsAndScales);

        fitnessEquipments = new ProductSubcategoryModel("Fitness Equipments", healthAndFitness);
        productService.add(fitnessEquipments);

        covid19Essentials = new ProductSubcategoryModel("COVID 19 Essentials", healthAndFitness);
        productService.add(covid19Essentials);

        babyCareProducts = new ProductSubcategoryModel("Baby Care Products", healthAndFitness);
        productService.add(babyCareProducts);

        elderlyCareProducts = new ProductSubcategoryModel("Elderly Care Products", healthAndFitness);
        productService.add(elderlyCareProducts);

        treadmills = new ProductSubcategoryModel("Treadmills", healthAndFitness);
        productService.add(treadmills);


        hairCareProducts = new ProductSubcategoryModel("Hair Care Products", groomingAndPersonalCare);
        productService.add(hairCareProducts);

        hairDryers = new ProductSubcategoryModel("Hair Dryers", groomingAndPersonalCare);
        productService.add(hairDryers);

        shavers = new ProductSubcategoryModel("Shavers", groomingAndPersonalCare);
        productService.add(shavers);

        trimmers = new ProductSubcategoryModel("Trimmers", groomingAndPersonalCare);
        productService.add(trimmers);

        dentalCareProducts = new ProductSubcategoryModel("Dental Care Products", groomingAndPersonalCare);
        productService.add(dentalCareProducts);

        petCareProducts = new ProductSubcategoryModel("Pet Care Products", groomingAndPersonalCare);
        productService.add(petCareProducts);

        groomingAccessories = new ProductSubcategoryModel("Grooming Accessories", groomingAndPersonalCare);
        productService.add(groomingAccessories);


        memoryCards = new ProductSubcategoryModel("Memory Cards", camerasAndAccessories);
        productService.add(memoryCards);

        cameraFilters = new ProductSubcategoryModel("Camera Filters", camerasAndAccessories);
        productService.add(cameraFilters);

        photoPaperSheets = new ProductSubcategoryModel("Photo Paper Sheets", camerasAndAccessories);
        productService.add(photoPaperSheets);

        selfieSticksAndGimbals = new ProductSubcategoryModel("Selfie Sticks & Gimbals", camerasAndAccessories);
        productService.add(selfieSticksAndGimbals);

        binocular = new ProductSubcategoryModel("Binocular", camerasAndAccessories);
        productService.add(binocular);

        telescopes = new ProductSubcategoryModel("Telescopes", camerasAndAccessories);
        productService.add(telescopes);

        digitalPhotoFrames = new ProductSubcategoryModel("Digital Photo Frames", camerasAndAccessories);
        productService.add(digitalPhotoFrames);

        DSLRCameras = new ProductSubcategoryModel("DSLR Cameras", camerasAndAccessories);
        productService.add(DSLRCameras);

        instantCameras = new ProductSubcategoryModel("Instant Cameras", camerasAndAccessories);
        productService.add(instantCameras);

        fullFrameCameras = new ProductSubcategoryModel("Full Frame Cameras", camerasAndAccessories);
        productService.add(fullFrameCameras);

        cameraLensAccessories = new ProductSubcategoryModel("Camera Lens Accessories", camerasAndAccessories);
        productService.add(cameraLensAccessories);


        gamingConsoles = new ProductSubcategoryModel("Gaming Consoles", gaming);
        productService.add(gamingConsoles);

        gamingSoftware = new ProductSubcategoryModel("Gaming Software", gaming);
        productService.add(gamingSoftware);

        gamingHeadsets = new ProductSubcategoryModel("Gaming Headsets", gaming);
        productService.add(gamingHeadsets);

        gamingControllers = new ProductSubcategoryModel("Gaming Controllers", gaming);
        productService.add(gamingControllers);

        gamingMice = new ProductSubcategoryModel("Gaming Mice", gaming);
        productService.add(gamingMice);

        electronicToys = new ProductSubcategoryModel("Electronic Toys", gaming);
        productService.add(electronicToys);

        educationalToys = new ProductSubcategoryModel("Educational Toys", gaming);
        productService.add(educationalToys);

        hoverBoards = new ProductSubcategoryModel("hoverBoards", gaming);
        productService.add(hoverBoards);

        remoteControlToys = new ProductSubcategoryModel("Remote Control Toys", gaming);
        productService.add(remoteControlToys);

        robotToys = new ProductSubcategoryModel("Robot Toys", gaming);
        productService.add(robotToys);

        pcGames = new ProductSubcategoryModel("PC Games", gaming);
        productService.add(pcGames);

    }

    private void addProducts() {
        productService.add(new ProductModel("Amazon Fire", new BigDecimal("49.9"), "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", androidTablets, amazon));
        productService.add(new ProductModel("Lenovo IdeaPad Miix 700", new BigDecimal("479"), "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", androidTablets, lenovo));
        productService.add(new ProductModel("Amazon Fire HD 8", new BigDecimal("89"), "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", androidTablets, amazon));

        productService.add(new ProductModel("Samsung Tv", new BigDecimal("1249.9"), "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", ledTvS, amazon));
        productService.add(new ProductModel("Toshiba Tv", new BigDecimal("779"), "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", ledTvS, lenovo));
        productService.add(new ProductModel("Led Tv", new BigDecimal("189"), "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", ledTvS, amazon));

        productService.add(new ProductModel("Fifa 2021", new BigDecimal("29.9"), "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", pcGames, amazon));
        productService.add(new ProductModel("Captain Hook 2000", new BigDecimal("79"), "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", pcGames, lenovo));
        productService.add(new ProductModel("Fort Knight", new BigDecimal("19"), "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", pcGames, amazon));
    }
}
