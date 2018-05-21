package com.gznytm.main;

import java.awt.AWTEvent;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;

class MyAWTEventListener implements AWTEventListener {
	private boolean controlPressed = false;
	private boolean cPressed = false;

	@Override
	public void eventDispatched(AWTEvent event) {
//		System.out.println("eventDispatched");
		if (event.getClass() == KeyEvent.class) {
			//被处理的事件是键盘事件.
			KeyEvent keyEvent = (KeyEvent) event;
			if (keyEvent.getID() == KeyEvent.KEY_PRESSED) {
				keyPressed(keyEvent);
			} else if (keyEvent.getID() == KeyEvent.KEY_RELEASED) {
				keyReleased(keyEvent);
			}
		}
	}

	private void keyPressed(KeyEvent event) {
//		System.out.println("keyPressed");
		if (event.getKeyCode() == KeyEvent.VK_CONTROL) {
			controlPressed = true;
		} else if (event.getKeyCode() == KeyEvent.VK_C) {
			cPressed = true;
		} 

		if (controlPressed && cPressed) {
			// 当Ctr + C 被按下时, 进行相应的处理.
			System.out.println("Ctr + C");
		}
	}

	private void keyReleased(KeyEvent event) {
//		System.out.println("keyReleased");
		if (event.getKeyCode() == KeyEvent.VK_CONTROL) {
			controlPressed = false;
		} else if (event.getKeyCode() == KeyEvent.VK_C) {
			cPressed = false;
		}
	}
}