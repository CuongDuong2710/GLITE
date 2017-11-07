package com.glitellp.libs.utils;

public class InterfaceUtils {
	public interface EventListener {
		void eventCompleted(Object result);
	}

	public static EventListener mListener;

	public void setListener(EventListener listener) {
		mListener = listener;
	}

	public static void sendEvent(Object result) {
		mListener.eventCompleted(result);
	}

}
