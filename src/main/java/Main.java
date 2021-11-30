public class Main {
    public static void main(String[] args) {

        CallCenter callCenter = new CallCenter();

        for (int i = 1; i <= 10; i++) {
            try {
                new Thread(null, callCenter::putPhoneCall, "Абонент " + i).start();
                int waiting = 1000;
                Thread.sleep(waiting);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }

        new  Thread(null, callCenter::takePhoneCall, "Оператор").start();
    }
}
