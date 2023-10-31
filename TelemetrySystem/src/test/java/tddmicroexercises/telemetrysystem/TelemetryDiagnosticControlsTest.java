package tddmicroexercises.telemetrysystem;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tddmicroexercises.telemetrysystem.solution.IConnection;
import tddmicroexercises.telemetrysystem.solution.IReciever;
import tddmicroexercises.telemetrysystem.solution.ISender;
import tddmicroexercises.telemetrysystem.solution.IStatus;

import static org.mockito.Mockito.*;

public class TelemetryDiagnosticControlsTest {

    @Mock
    private ISender sender;
    
    @Mock
    private IReciever receiver;
    
    @Mock
    private IConnection connection;
    
    @Mock
    private IStatus status;

    private TelemetryDiagnosticControls telemetryDiagnosticControls;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        telemetryDiagnosticControls = new TelemetryDiagnosticControls(sender, receiver, connection, status);
    }

    @Test
    public void checkTransmission_OnlineStatusIsTrue() throws Exception {

        when(status.getOnlineStatus()).thenReturn(true);

        telemetryDiagnosticControls.checkTransmission();

        verify(connection, never()).connect(anyString());
        verify(sender).send(TelemetryDiagnosticControls.DIAGNOSTIC_MESSAGE);
        verify(receiver).receive();
    }

    @Test(expected = Exception.class)
    public void checkTransmission_FailedToConnect() throws Exception {
        String DiagnosticChannelConnectionString = "*111#";
        when(status.getOnlineStatus()).thenReturn(false);
        when(connection.connect(DiagnosticChannelConnectionString))
            .thenThrow(new Exception("Unable to connect."));


        telemetryDiagnosticControls.checkTransmission();
    }
}
