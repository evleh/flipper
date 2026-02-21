package at.technikum.mediator;

import at.technikum.elements.FlipperElement;

public interface Mediator {
    public void makeNotification(FlipperElement sender, String event);
}
