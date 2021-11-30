import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.SynchronousQueue;

public class CallCenter {

    // final private SynchronousQueue<PhoneCall> calls = new SynchronousQueue<>();
    final private LinkedTransferQueue<PhoneCall> calls = new LinkedTransferQueue<>();
    private volatile int countCall = 0;

    public void putPhoneCall() {
        countCall++;
        System.out.println("* Входящий звонок-" + countCall + " от " + Thread.currentThread().getName());
        waiting();
        calls.put(new PhoneCall("входящий звонок-" + countCall));
    }

    public void waiting() {
        try {
            int waiting = 500;
            Thread.sleep(waiting);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void takePhoneCall() {
        try {
            while (!calls.isEmpty()) {
                waiting();
                System.out.println(Thread.currentThread().getName() + " принял " + calls.take());
                waiting();
            }
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }
}
