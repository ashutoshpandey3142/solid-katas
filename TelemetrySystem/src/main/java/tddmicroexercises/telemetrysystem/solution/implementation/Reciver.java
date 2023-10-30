package tddmicroexercises.telemetrysystem.solution.implementation;

import tddmicroexercises.telemetrysystem.solution.IReciever;

import java.util.Random;

public class Reciver implements IReciever {
    public static final String DIAGNOSTIC_MESSAGE = "AT#UD";
    private String diagnosticMessageResult = "";

    private final Random connectionEventsSimulator = new Random(42);
    public String receive()
    {
        String message;

        if (diagnosticMessageResult == null || "".equals(diagnosticMessageResult))
        {
            // simulate a received message (just for illustration - not needed for this exercise)
            message = "";
            int messageLength = connectionEventsSimulator.nextInt(50) + 60;
            for(int i = messageLength; i >=0; --i)
            {
                message += (char)connectionEventsSimulator.nextInt(40) + 86;
            }

        }
        else
        {
            message = diagnosticMessageResult;
            diagnosticMessageResult = "";
        }

        return message;
    }
}
