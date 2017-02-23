/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.createch.upnp_multiple_lights;

import java.beans.PropertyChangeSupport;
import org.fourthline.cling.binding.annotations.*;


/**
 *
 * @author IDA
 */

@UpnpService(
        serviceId = @UpnpServiceId("Selection"),
        serviceType = @UpnpServiceType(value = "Selection", version = 1)
)


public class Selection {
    
     private final PropertyChangeSupport propertyChangeSupport;

    public Selection() {
        this.propertyChangeSupport = new PropertyChangeSupport(this);
    }

    public PropertyChangeSupport getPropertyChangeSupport() {
        return propertyChangeSupport;
    }
    
    
    /**
     * Variable D'Etat, non ï¿½venemencï¿½e
     * Permet d'envoyer le message de l'ï¿½tat dans lequel la lampe doit ï¿½tre
     */
    @UpnpStateVariable(defaultValue = "AUCUNE", sendEvents = false)
    private Action target = Action.AUCUNE;

    /**
     * Variable d'etat ï¿½venemmencï¿½e
     * Permet de vï¿½rifier si la lampe est bien dans le bon ï¿½tat.
     */
    @UpnpStateVariable(defaultValue = "AUCUNE")
    private Action status = Action.AUCUNE;

    /**
     * receive the next action to perform
     */

    @UpnpAction
    public void setTarget(@UpnpInputArgument(name = "NewTargetValue") String newTargetValue) {
        Action targetOldValue = target;
        Action statusOldValue = status;
        
        Action newActionValue;
        
        switch(newTargetValue) {
            case("DROITE"):
                newActionValue = Action.DROITE;
                break;
            case("GAUCHE"):
                newActionValue = Action.GAUCHE;
                break;
            case("BAS"):
                newActionValue = Action.BAS;
                break;
            case("HAUT"):
                newActionValue = Action.HAUT;
                break;
            case("CENTRE"):
                newActionValue = Action.CENTRE;
                break;
            default:
                newActionValue = Action.AUCUNE;
        }

        status = newActionValue;
        target = status;
        // These have no effect on the UPnP monitoring but it's JavaBean compliant
        getPropertyChangeSupport().firePropertyChange("target", targetOldValue, target);
        getPropertyChangeSupport().firePropertyChange("status", statusOldValue, status);

        // This will send a UPnP event, it's the name of a state variable that sends events
        getPropertyChangeSupport().firePropertyChange("Status", statusOldValue, status);
        
        
        status = Action.AUCUNE;
        target = Action.AUCUNE;
    }

    /**
     * Get Status of the lamp
     * Methode Upnp grace au systï¿½me d'annotation
     * @return Action
     */
    @UpnpAction(out = @UpnpOutputArgument(name = "ResultStatus"))
    public Action getStatus() {
        // Pour ajouter des informations supplï¿½mentaires UPnP en cas d'erreur :
        // throw new ActionException(ErrorCode.ACTION_NOT_AUTHORIZED);
        return status;
    }
    
 
    
    /**
     * Print the version of the code
     * Ceci n'est pas une methode UPnP
     */
    public void printVersion(){
        System.out.println("Version : 1.0");
    }

}
