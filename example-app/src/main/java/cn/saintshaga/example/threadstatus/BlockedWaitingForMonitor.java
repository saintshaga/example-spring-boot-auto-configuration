package cn.saintshaga.example.threadstatus;

/**
 *
 "Thread-1" #11 prio=5 os_prio=0 tid=0x00007fdccc35f000 nid=0x18a3 waiting for monitor entry [0x00007fdcb54b9000]
 java.lang.Thread.State: BLOCKED (on object monitor)
 at cn.saintshaga.example.threadstatus.BlockedWaitingForMonitor.run(BlockedWaitingForMonitor.java:9)
 - waiting to lock <0x0000000758256370> (a cn.saintshaga.example.threadstatus.BlockedWaitingForMonitor)
 at java.lang.Thread.run(Thread.java:745)

 "Thread-0" #10 prio=5 os_prio=0 tid=0x00007fdccc35e000 nid=0x18a2 runnable [0x00007fdcb57d1000]
 java.lang.Thread.State: RUNNABLE
 at cn.saintshaga.example.threadstatus.BlockedWaitingForMonitor.run(BlockedWaitingForMonitor.java:12)
 - locked <0x0000000758256370> (a cn.saintshaga.example.threadstatus.BlockedWaitingForMonitor)
 at java.lang.Thread.run(Thread.java:745)

 */
public class BlockedWaitingForMonitor implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " started:");
        synchronized (this) {
            long start = System.currentTimeMillis();
            long end = System.currentTimeMillis();
            while(end - start < 0.5 * 60 * 1000) {
                end = System.currentTimeMillis();
            }
        }
        System.out.println(Thread.currentThread().getName() + " end");
    }

    public static void main(String[] args) {
        BlockedWaitingForMonitor blockedWaitingForMonitor = new BlockedWaitingForMonitor();
        System.out.println(blockedWaitingForMonitor);
        new Thread(blockedWaitingForMonitor).start();
        new Thread(blockedWaitingForMonitor).start();
    }
}
