package java.concurrency.practice.ten.one;

import java.awt.*;
import java.concurrency.practice.common.annotation.GuardedBy;
import java.concurrency.practice.common.annotation.ThreadSafe;
import java.concurrency.practice.four.three.Point;
import java.util.HashSet;
import java.util.Set;

/**
 * Using open calls to avoiding deadlock between cooperating objects.
 */
public class Taxi2 {
    @ThreadSafe
    class Taxi {
        @GuardedBy("this")
        private Point location, destination;
        private final Dispatcher dispatcher;

        Taxi(Point destination, Dispatcher dispatcher) {
            this.destination = destination;
            this.dispatcher = dispatcher;
        }

        public synchronized Point getLocation() {
            return location;
        }

        public void setLocation(Point location) {
            boolean reachedDestination;
            synchronized (this) {
                this.location = location;
                reachedDestination = location.equals(destination);
            }
            if (reachedDestination)
                dispatcher.notifyAvailable(this);
        }
    }

    @ThreadSafe
    class Dispatcher {
        @GuardedBy("this")
        private final Set<Taxi> taxis;
        @GuardedBy("this")
        private final Set<Taxi> availableTaxis;

        public synchronized void notifyAvailable(Taxi taxi) {
            availableTaxis.add(taxi);
        }

        public Image getImage() {
            Set<Taxi> copy;
            synchronized (this) {
                copy = new HashSet<Taxi>(taxis);
            }
            Image image = new Image();
            for (Taxi t : copy)
                image.drawMarker(t.getLocation());
            return image;
        }
    }
}
