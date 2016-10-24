package SendTables;



import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 * Created by eliwa on 10/9/2016.
 */
public class SendNetworkTables {

    NetworkTable table;

    public void init(String IP) {
        NetworkTable.setClientMode();
        NetworkTable.setIPAddress(IP);
        table = NetworkTable.getTable("datatable");

    }

    public void SendInfo(double X, double Y) {
        table.putNumber("Change X: ", X);
        table.putNumber("Change Y: ", Y);
    }

    public boolean GetActivate() {
     boolean activate = table.getBoolean("Activate", false);
        return activate;
    }

}


