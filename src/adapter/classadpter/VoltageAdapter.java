package adapter.classadpter;

public class VoltageAdapter extends Voltage220V implements IVoltage5V {

    @Override
    public int output5V() {
        int srcV = output220V();
        int dst = srcV / 44;
        return dst;
    }
}
