package cn.saintshaga.example.threadstatus;

import lombok.extern.slf4j.Slf4j;

/*
"Thread-1" #11 prio=5 os_prio=0 tid=0x00007fa11c39f800 nid=0x4951 waiting for monitor entry [0x00007fa100319000]
   java.lang.Thread.State: BLOCKED (on object monitor)
	at cn.saintshaga.example.threadstatus.DeadLock.run(DeadLock.java:26)
	- waiting to lock <0x00000007588af330> (a java.lang.Object)
	- locked <0x00000007588af340> (a java.lang.Object)
	at java.lang.Thread.run(Thread.java:745)

"Thread-0" #10 prio=5 os_prio=0 tid=0x00007fa11c3a2800 nid=0x4950 waiting for monitor entry [0x00007fa10041a000]
   java.lang.Thread.State: BLOCKED (on object monitor)
	at cn.saintshaga.example.threadstatus.DeadLock.run(DeadLock.java:19)
	- waiting to lock <0x00000007588af340> (a java.lang.Object)
	- locked <0x00000007588af330> (a java.lang.Object)
	at java.lang.Thread.run(Thread.java:745)

=============================
"Thread-1":
  waiting to lock monitor 0x00007fa0e40038d8 (object 0x00000007588af330, a java.lang.Object),
  which is held by "Thread-0"
"Thread-0":
  waiting to lock monitor 0x00007fa0e4005ea8 (object 0x00000007588af340, a java.lang.Object),
  which is held by "Thread-1"

 */

@Slf4j
public class DeadLock implements Runnable {

    private Object o1 = new Object();
    private Object o2 = new Object();

    @Override
    public void run() {
        Thread thread = Thread.currentThread();
        log.info("Start {}", thread.getName());
        if(thread.getId() % 2 == 0) {
            synchronized (o1) {
                doSomething(1000);
                synchronized (o2) {
                    doSomething(2000);
                }
            }
        } else {
            synchronized (o2) {
                doSomething(1000);
                synchronized (o1) {
                    doSomething(2000);
                }
            }
        }
    }

    private void doSomething(long miliSeconds) {
        try {
            Thread.sleep(miliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DeadLock lock = new DeadLock();
        new Thread(lock).start();
        new Thread(lock).start();
    }
}
