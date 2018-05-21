package com.gznytm.main;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Snap extends ComponentAdapter {

	private boolean locked = false;
	private int snap_distance = 50;

	@Override
	public void componentMoved(ComponentEvent evt) {
		if (locked)
			return;
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		int nx = evt.getComponent().getX();
		int ny = evt.getComponent().getY();
		if (ny < 0 + snap_distance) { // top
			ny = 0;
		}
		
		if (nx < 0 + snap_distance) { // left
			nx = 0;
		}
		// right
		if (nx > size.getWidth() - evt.getComponent().getWidth()
				- snap_distance) {
			nx = (int) size.getWidth() - evt.getComponent().getWidth();
		}
		// bottom
		if (ny > size.getHeight() - evt.getComponent().getHeight()
				- snap_distance) {
			ny = (int) size.getHeight() - evt.getComponent().getHeight();
		}
		// make sure we don't get into a recursive loop when the
		// set location generates more events
		locked = true;
		evt.getComponent().setLocation(nx, ny);
		locked = false;
	}
}