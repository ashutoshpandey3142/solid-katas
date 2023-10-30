package tddmicroexercises.telemetrysystem.solution.implementation;

import tddmicroexercises.telemetrysystem.solution.IStatus;

public class Status implements IStatus
{
    private boolean onlineStatus;
    public boolean getOnlineStatus()
    {
        return onlineStatus;
    }

    public void setOnlineStatus(boolean status){ this.onlineStatus = status; };

}