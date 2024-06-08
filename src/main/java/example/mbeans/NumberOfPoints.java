package example.mbeans;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;
import java.util.concurrent.atomic.AtomicInteger;

public class NumberOfPoints extends NotificationBroadcasterSupport implements NumberOfPointsMBean{
    private final AtomicInteger allPoints = new AtomicInteger(0);
    private final AtomicInteger missPoints = new AtomicInteger(0);
    private final AtomicInteger consecutiveMisses = new AtomicInteger(0);
    private long sequenceNumber = 1;

    @Override
    public void incrementAllPoints() {
        this.allPoints.incrementAndGet();
    }

    @Override
    public int getAllPoints() {
        return this.allPoints.get();
    }

    @Override
    public void incrementMissPoints() {
        this.missPoints.incrementAndGet();
        this.consecutiveMisses.incrementAndGet();

        if (this.consecutiveMisses.get() == 4) {
            sendNotification(new Notification(
                    "FourMissPoints",
                    this,
                    sequenceNumber++,
                    System.currentTimeMillis(),
                    "Four Consecutive Miss Points ")
            );
            this.resetConsecutiveMisses();
        }
    }

    @Override
    public int getMissPoints() {
        return this.missPoints.get();
    }

    @Override
    public void resetConsecutiveMisses() {
        this.consecutiveMisses.set(0);
    }
}
