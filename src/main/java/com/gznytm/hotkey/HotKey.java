package com.gznytm.hotkey;

import com.gznytm.main.MainFrame;
import com.melloware.jintellitype.HotkeyListener;
import com.melloware.jintellitype.JIntellitype;

public class HotKey {

	public static void hotKey() {
		JIntellitype.getInstance().registerHotKey(88, JIntellitype.MOD_ALT,(int) 'V');
		JIntellitype.getInstance().addHotKeyListener(new HotkeyListener() {
			public void onHotKey(int key) {
				if (key == 88) { // 你要做的事
					MainFrame.getInstance().setVisible(true);
				}
			}
		});
		JIntellitype.getInstance().registerHotKey(100, JIntellitype.MOD_ALT,
				(int) 'C');
		JIntellitype.getInstance().addHotKeyListener(new HotkeyListener() {
			public void onHotKey(int key) {
				if (key == 100) { // 你要做的事
					MainFrame.getInstance().dispose();
				}
			}
		});
	}

}
