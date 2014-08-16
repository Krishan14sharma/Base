package krishan.dhancha.event;

public class NetworkStateChanged {

    private boolean mIsInternetConnected;
    private int connectionType;

    public NetworkStateChanged(boolean isInternetConnected,int connectionType) {
        this.mIsInternetConnected = isInternetConnected;
        this.connectionType=connectionType;
    }

    public boolean isInternetConnected() {
        return this.mIsInternetConnected;
    }

    public int getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(int connectionType) {
        this.connectionType = connectionType;
    }
}