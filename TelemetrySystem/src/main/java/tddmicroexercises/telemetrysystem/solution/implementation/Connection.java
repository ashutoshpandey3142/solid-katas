package tddmicroexercises.telemetrysystem.solution.implementation;

import tddmicroexercises.telemetrysystem.solution.IConnection;

import java.util.Random;

public class Connection implements IConnection {
    private final Random connectionEventsSimulator = new Random(42);
    public boolean connect(String telemetryServerConnectionString)
    {
        boolean onlineStatus;
        if (telemetryServerConnectionString == null || "".equals(telemetryServerConnectionString))
        {
            throw new IllegalArgumentException();
        }

        // simulate the operation on a real modem
        boolean success = connectionEventsSimulator.nextInt(10) <= 8;

        onlineStatus = success;
        return onlineStatus;
    }
}
