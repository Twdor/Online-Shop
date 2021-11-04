package com.codecool.shop.config;

import com.codecool.shop.models.ProductCategoryModel;
import com.codecool.shop.models.ProductSubcategoryModel;
import com.codecool.shop.models.SupplierModel;
import com.codecool.shop.service.Service;

import java.sql.SQLException;

public class InitializerFields {
    // true for db persistence , for memory no param
    Service productService = new Service(true);

    //Suppliers
    SupplierModel amazon;
    SupplierModel lenovo;


    //Categories
    ProductCategoryModel televisionsAndAccessories;
    ProductCategoryModel homeAppliances;
    ProductCategoryModel phonesAndWearables;
    ProductCategoryModel computersAndTablets;
    ProductCategoryModel kitchenAppliances;
    ProductCategoryModel audioAndVideo;
    ProductCategoryModel healthAndFitness;
    ProductCategoryModel groomingAndPersonalCare;
    ProductCategoryModel camerasAndAccessories;
    ProductCategoryModel gaming;

    //Subcategories
    ProductSubcategoryModel ledTvS;
    ProductSubcategoryModel fullHdTvs;
    ProductSubcategoryModel smartTvs;
    ProductSubcategoryModel androidSmartTvs;
    ProductSubcategoryModel largeScreenTvs;
    ProductSubcategoryModel mediumScreenTvs;
    ProductSubcategoryModel smallScreenTvs;
    ProductSubcategoryModel gamingTvs;
    ProductSubcategoryModel fullHdProjectors;
    ProductSubcategoryModel tvCablesAndConnectors;
    ProductSubcategoryModel remotes;

    ProductSubcategoryModel washingMachines;
    ProductSubcategoryModel airHumidifiers;
    ProductSubcategoryModel airDehumidifiers;
    ProductSubcategoryModel airCoolers;
    ProductSubcategoryModel fans;
    ProductSubcategoryModel lightStrips;
    ProductSubcategoryModel roomHeaters;
    ProductSubcategoryModel miniFreezeRefrigerators;
    ProductSubcategoryModel tripleDoorRefrigerators;
    ProductSubcategoryModel sideBySideRefrigerators;
    ProductSubcategoryModel wineCoolers;
    ProductSubcategoryModel airConditioners;
    ProductSubcategoryModel irons;
    ProductSubcategoryModel vacuumCleaners;
    ProductSubcategoryModel solarLights;
    ProductSubcategoryModel smartPlugs;

    ProductSubcategoryModel mobilePhones;
    ProductSubcategoryModel androidPhones;
    ProductSubcategoryModel mobileAntivirus;
    ProductSubcategoryModel iPhones;
    ProductSubcategoryModel smartWatches;
    ProductSubcategoryModel screenProtectors;
    ProductSubcategoryModel cordlessPhones;
    ProductSubcategoryModel usbCablesAndConnectors;
    ProductSubcategoryModel earphones;
    ProductSubcategoryModel headPhones;
    ProductSubcategoryModel vrHeadset;
    ProductSubcategoryModel tripods;
    ProductSubcategoryModel smartBands;
    ProductSubcategoryModel landlinePhones;
    ProductSubcategoryModel selfieSticks;

    ProductSubcategoryModel windowsLaptops;
    ProductSubcategoryModel laptops;
    ProductSubcategoryModel macBooks;
    ProductSubcategoryModel androidTablets;
    ProductSubcategoryModel iPads;
    ProductSubcategoryModel iMacs;
    ProductSubcategoryModel gamingLaptops;
    ProductSubcategoryModel desktops;
    ProductSubcategoryModel monitors;
    ProductSubcategoryModel keyboards;
    ProductSubcategoryModel laptopBags;
    ProductSubcategoryModel operatingSystems;
    ProductSubcategoryModel antivirusAndInternetSecurity;
    ProductSubcategoryModel multimediaSpeakers;
    ProductSubcategoryModel networkAdapters;
    ProductSubcategoryModel digitalPads;

    ProductSubcategoryModel dishwashers;
    ProductSubcategoryModel handMixers;
    ProductSubcategoryModel riceCookers;
    ProductSubcategoryModel teaMakers;
    ProductSubcategoryModel soupMakers;
    ProductSubcategoryModel choppers;
    ProductSubcategoryModel watterBottles;
    ProductSubcategoryModel hobs;
    ProductSubcategoryModel juicerAndBlenders;
    ProductSubcategoryModel desertMakers;
    ProductSubcategoryModel gasStoves;
    ProductSubcategoryModel foodProcessors;
    ProductSubcategoryModel storageContainers;
    ProductSubcategoryModel toasters;
    ProductSubcategoryModel ovens;
    ProductSubcategoryModel waterPurifiers;

    ProductSubcategoryModel smartSpeakers;
    ProductSubcategoryModel partySpeakers;
    ProductSubcategoryModel homeTheatres;
    ProductSubcategoryModel audioAmplifiers;
    ProductSubcategoryModel soundBars;
    ProductSubcategoryModel gamingHeadset;
    ProductSubcategoryModel djMachinesAndSpeakers;
    ProductSubcategoryModel earPhones;
    ProductSubcategoryModel karaokeMics;
    ProductSubcategoryModel mediaPlayers;
    ProductSubcategoryModel audiCasesAndCovers;

    ProductSubcategoryModel massagers;
    ProductSubcategoryModel hearingAids;
    ProductSubcategoryModel healthMonitors;
    ProductSubcategoryModel steamVaporizers;
    ProductSubcategoryModel exerciseBikes;
    ProductSubcategoryModel weightsAndScales;
    ProductSubcategoryModel fitnessEquipments;
    ProductSubcategoryModel covid19Essentials;
    ProductSubcategoryModel babyCareProducts;
    ProductSubcategoryModel elderlyCareProducts;
    ProductSubcategoryModel treadmills;

    ProductSubcategoryModel hairCareProducts;
    ProductSubcategoryModel hairDryers;
    ProductSubcategoryModel shavers;
    ProductSubcategoryModel trimmers;
    ProductSubcategoryModel dentalCareProducts;
    ProductSubcategoryModel petCareProducts;
    ProductSubcategoryModel groomingAccessories;

    ProductSubcategoryModel memoryCards;
    ProductSubcategoryModel cameraFilters;
    ProductSubcategoryModel photoPaperSheets;
    ProductSubcategoryModel selfieSticksAndGimbals;
    ProductSubcategoryModel binocular;
    ProductSubcategoryModel telescopes;
    ProductSubcategoryModel digitalPhotoFrames;
    ProductSubcategoryModel DSLRCameras;
    ProductSubcategoryModel instantCameras;
    ProductSubcategoryModel fullFrameCameras;
    ProductSubcategoryModel cameraLensAccessories;

    ProductSubcategoryModel gamingConsoles;
    ProductSubcategoryModel gamingSoftware;
    ProductSubcategoryModel gamingHeadsets;
    ProductSubcategoryModel electronicToys;
    ProductSubcategoryModel educationalToys;
    ProductSubcategoryModel hoverBoards;
    ProductSubcategoryModel remoteControlToys;
    ProductSubcategoryModel robotToys;
    ProductSubcategoryModel pcGames;
    ProductSubcategoryModel gamingMice;
    ProductSubcategoryModel gamingControllers;

    public InitializerFields() throws SQLException {
    }
}