package tddmicroexercises.telemetrysystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tddmicroexercises.telemetrysystem.solution.IReciever;
import tddmicroexercises.telemetrysystem.solution.ISender;
import tddmicroexercises.telemetrysystem.solution.IStatus;
import tddmicroexercises.telemetrysystem.solution.implementation.Connection;
import tddmicroexercises.telemetrysystem.solution.IConnection;
import tddmicroexercises.telemetrysystem.solution.implementation.Reciver;
import tddmicroexercises.telemetrysystem.solution.implementation.Sender;
import tddmicroexercises.telemetrysystem.solution.implementation.Status;

@SpringBootApplication
public class TelemetryDiagnosticControls
{
    public static final String DIAGNOSTIC_MESSAGE = "AT#UD";
    private final String DiagnosticChannelConnectionString = "*111#";
    private final ISender sender;
    private final IReciever reciver;
    private final IConnection connection;
    private final IStatus status;
    private String diagnosticInfo = "";

        @Autowired
        public TelemetryDiagnosticControls(Sender sender,Reciver reciver, Connection connection, Status status)
        {
            this.sender = sender;
            this.reciver = reciver;
            this.connection = connection;
            this.status = status;
        }

        public String getDiagnosticInfo(){
            return diagnosticInfo;
        }

        public void setDiagnosticInfo(String diagnosticInfo){
            this.diagnosticInfo = diagnosticInfo;
        }

        public void checkTransmission() throws Exception
        {
            diagnosticInfo = "";

            status.setOnlineStatus(false);

            int retryLeft = 3;
            while (!status.getOnlineStatus() && retryLeft > 0)
            {
                connection.connect(DiagnosticChannelConnectionString);
                retryLeft -= 1;
            }

            if(!status.getOnlineStatus())
            {
                throw new Exception("Unable to connect.");
            }

            sender.send(DIAGNOSTIC_MESSAGE);
            diagnosticInfo = reciver.receive();
    }
    public static void main(String[] args) {
        SpringApplication.run(TelemetryDiagnosticControls.class, args);
    }
}
