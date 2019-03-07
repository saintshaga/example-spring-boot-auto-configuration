package cn.saintshaga.example.threadstatus;

/*
Waiting situation:
"Thread-1" #11 prio=5 os_prio=0 tid=0x00007f4e2c362800 nid=0x2980 runnable [0x00007f4e183d2000]
   java.lang.Thread.State: RUNNABLE
	at cn.saintshaga.example.threadstatus.WaitingOnObjectWait.run(WaitingOnObjectWait.java:20)
	- locked <0x0000000758255f98> (a cn.saintshaga.example.threadstatus.WaitingOnObjectWait)
	at java.lang.Thread.run(Thread.java:745)

"Thread-0" #10 prio=5 os_prio=0 tid=0x00007f4e2c361000 nid=0x297e in Object.wait() [0x00007f4e184d3000]
   java.lang.Thread.State: WAITING (on object monitor)
	at java.lang.Object.wait(Native Method)
	- waiting on <0x0000000758255f98> (a cn.saintshaga.example.threadstatus.WaitingOnObjectWait)
	at java.lang.Object.wait(Object.java:502)
	at cn.saintshaga.example.threadstatus.WaitingOnObjectWait.run(WaitingOnObjectWait.java:11)
	- locked <0x0000000758255f98> (a cn.saintshaga.example.threadstatus.WaitingOnObjectWait)
	at java.lang.Thread.run(Thread.java:745)

Time waiting situation:
"Thread-1" #11 prio=5 os_prio=0 tid=0x00007fc15035f000 nid=0x2d8c runnable [0x00007fc0f7ffe000]
   java.lang.Thread.State: RUNNABLE
	at cn.saintshaga.example.threadstatus.WaitingOnObjectWait.run(WaitingOnObjectWait.java:41)
	- locked <0x0000000758256370> (a cn.saintshaga.example.threadstatus.WaitingOnObjectWait)
	at java.lang.Thread.run(Thread.java:745)

"Thread-0" #10 prio=5 os_prio=0 tid=0x00007fc15035d800 nid=0x2d8b in Object.wait() [0x00007fc117404000]
   java.lang.Thread.State: TIMED_WAITING (on object monitor)
	at java.lang.Object.wait(Native Method)
	- waiting on <0x0000000758256370> (a cn.saintshaga.example.threadstatus.WaitingOnObjectWait)
	at cn.saintshaga.example.threadstatus.WaitingOnObjectWait.run(WaitingOnObjectWait.java:32)
	- locked <0x0000000758256370> (a cn.saintshaga.example.threadstatus.WaitingOnObjectWait)
	at java.lang.Thread.run(Thread.java:745)

 */
public class WaitingOnObjectWait implements Runnable{
    @Override
    public void run() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + " started");
        synchronized (this) {
            if(thread.getId()%2 == 0) {
                try {
//                    this.wait();
                    this.wait(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(thread.getName() + " comes back");
            } else {
                long start = System.currentTimeMillis();
                long end = System.currentTimeMillis();
                while(end - start < 0.5 * 60 * 1000) {
                    end = System.currentTimeMillis();
                }
//                this.notify();
            }
        }
        System.out.println(Thread.currentThread().getName() + " end");
    }

    public static void main(String[] args) {
        WaitingOnObjectWait waitingOnObjectWait = new WaitingOnObjectWait();
        new Thread(waitingOnObjectWait).start();
        new Thread(waitingOnObjectWait).start();
    }
}
