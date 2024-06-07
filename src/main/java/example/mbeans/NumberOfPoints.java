package example.mbeans;

import javax.management.NotificationBroadcasterSupport;
import java.util.concurrent.atomic.AtomicInteger;

public class NumberOfPoints extends NotificationBroadcasterSupport implements NumberOfPointsMBean{
    private final AtomicInteger allPoints = new AtomicInteger(0);
    private final AtomicInteger missingPoints = new AtomicInteger(0);

    @Override
    public int getAllPoints() {
        return this.allPoints.get();
    }

    @Override
    public void incrementAllPoints() {
        this.allPoints.incrementAndGet();
    }

    @Override
    public int getMissingPoints() {
        return this.missingPoints.get();
    }

    @Override
    public void incrementMissingPoints() {
        this.missingPoints.incrementAndGet();
    }
}
