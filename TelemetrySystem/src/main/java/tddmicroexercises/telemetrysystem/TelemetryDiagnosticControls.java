package tddmicroexercises.telemetrysystem;

import org.springframework.beans.factory.annotation.Autowired;
import tddmicroexercises.telemetrysystem.solution.IReciever;
import tddmicroexercises.telemetrysystem.solution.ISender;
import tddmicroexercises.telemetrysystem.solution.IStatus;
import tddmicroexercises.telemetrysystem.solution.IConnection;
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
        public TelemetryDiagnosticControls(ISender sender,IReciever reciver, IConnection connection, IStatus status)
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
}
