package java.concurrency.practice.ten.one;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.concurrency.practice.common.annotation.GuardedBy;
import java.concurrency.practice.four.three.Point;
import java.util.HashSet;
import java.util.Set;

/**
 * Lock-ordering deadlock between cooperating objects. Don’t do this.
 */
// Warning: deadlock-prone!
public class Taxi {
    @GuardedBy("this")
    private Point location, destination;
    private final Dispatcher dispatcher;

    public Taxi(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public synchronized Point getLocation() {
        return location;
    }

    public synchronized void setLocation(Point location) {
        this.location = location;
        if (location.equals(destination))
            dispatcher.notifyAvailable(this);
    }

    class Dispatcher {
        @GuardedBy("this")
        private final Set<Taxi> taxis;
        @GuardedBy("this")
        private final Set<Taxi> availableTaxis;

        public Dispatcher() {
            taxis = new HashSet<Taxi>();
            availableTaxis = new HashSet<Taxi>();
        }

        public synchronized void notifyAvailable(Taxi taxi) {
            availableTaxis.add(taxi);
        }

        public synchronized Image getImage() {
            Image image = new Image() {
                /**
                 * Determines the width of the image. If the width is not yet known,
                 * this method returns <code>-1</code> and the specified
                 * <code>ImageObserver</code> object is notified later.
                 *
                 * @param observer an object waiting for the image to be loaded.
                 * @return the width of this image, or <code>-1</code>
                 * if the width is not yet known.
                 * @see Image#getHeight
                 * @see ImageObserver
                 */
                @Override
                public int getWidth(ImageObserver observer) {
                    return 0;
                }

                /**
                 * Determines the height of the image. If the height is not yet known,
                 * this method returns <code>-1</code> and the specified
                 * <code>ImageObserver</code> object is notified later.
                 *
                 * @param observer an object waiting for the image to be loaded.
                 * @return the height of this image, or <code>-1</code>
                 * if the height is not yet known.
                 * @see Image#getWidth
                 * @see ImageObserver
                 */
                @Override
                public int getHeight(ImageObserver observer) {
                    return 0;
                }

                /**
                 * Gets the object that produces the pixels for the image.
                 * This method is called by the image filtering classes and by
                 * methods that perform image conversion and scaling.
                 *
                 * @return the image producer that produces the pixels
                 * for this image.
                 * @see ImageProducer
                 */
                @Override
                public ImageProducer getSource() {
                    return null;
                }

                /**
                 * Creates a graphics context for drawing to an off-screen image.
                 * This method can only be called for off-screen images.
                 *
                 * @return a graphics context to draw to the off-screen image.
                 * @throws UnsupportedOperationException if called for a
                 *                                       non-off-screen image.
                 * @see Graphics
                 * @see Component#createImage(int, int)
                 */
                @Override
                public Graphics getGraphics() {
                    return null;
                }

                /**
                 * Gets a property of this image by name.
                 * <p>
                 * Individual property names are defined by the various image
                 * formats. If a property is not defined for a particular image, this
                 * method returns the <code>UndefinedProperty</code> object.
                 * <p>
                 * If the properties for this image are not yet known, this method
                 * returns <code>null</code>, and the <code>ImageObserver</code>
                 * object is notified later.
                 * <p>
                 * The property name <code>"comment"</code> should be used to store
                 * an optional comment which can be presented to the application as a
                 * description of the image, its source, or its author.
                 *
                 * @param name     a property name.
                 * @param observer an object waiting for this image to be loaded.
                 * @return the value of the named property.
                 * @throws NullPointerException if the property name is null.
                 * @see ImageObserver
                 * @see Image#UndefinedProperty
                 */
                @Override
                public Object getProperty(String name, ImageObserver observer) {
                    return null;
                }
            };
            for (Taxi t : taxis)
                image.drawMarker(t.getLocation());
            return image;
        }
    }

}

