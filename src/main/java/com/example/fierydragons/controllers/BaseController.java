package com.example.fierydragons.controllers;

import com.example.fierydragons.utils.SwitchScene;

/**
 * Abstract class  BaseController which serves as a base for other controllers in the application.
 * @author: Taken from Jaden's Sprint 2
 */
public abstract class BaseController {
    // Declare a protected instance of SwitchScene. This allows derived controllers to use it for switching scenes.
    protected SwitchScene switcher = new SwitchScene();

    // Declare a protected Class object to hold the class type of the instance.
    protected Class<?> classInstance;

    /**
     * Constructor for BaseController class
     */
    protected BaseController() {
        // Set the classInstance to the class type of the instance.
        this.classInstance = this.getClass();
    }
}
