package adapter.classadpter;

public class Phone {

    public void charging(IVoltage5V iVoltage5V) {
        if (iVoltage5V.output5V() == 5) {
            System.out.println("电压为５伏，可以充电");
        } else if (iVoltage5V.output5V() > 5) {
            System.out.println("电压大于５伏，不能充电");
        }
    }
}
