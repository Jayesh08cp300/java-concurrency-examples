package com.example;

class RunnableThread implements Runnable {

	private Thread t;
	private String threadName;

	public RunnableThread(String name) {
		threadName = name;
		System.out.println("Creating " + threadName);
	}

	@Override
	public void run() {
		System.out.println("Running " + threadName);
		try {

			synchronized (Class.class) {
				for (int i = 4; i > 0; i--) {
					System.out.println("Thread:: " + threadName + ", " + i);

					// Let the thread sleep for a while.
					Thread.sleep(1000);
				}
			}

			synchronized (this) {
				for (int i = 4; i > 0; i--) {
					System.out.println("Thread: " + threadName + ", " + i);

					// Let the thread sleep for a while.
					Thread.sleep(1000);
				}
			}



		} catch (InterruptedException e) {
			System.out.println("Thread " + threadName + " interrupted.");
		} System.out.println("Thread " + threadName + " exiting.");
	}

	public void start() {
		System.out.println("Starting " + threadName);

		if (t == null) {
			t = new Thread(this, threadName);
			t.start();
		}
	}
}

public class RunnableDemo {
	public static void main(String[] args) {
		RunnableThread R1 = new RunnableThread("Thread-1");
		R1.start();

		RunnableThread R2 = new RunnableThread("Thread-2");
		R2.start();
	}
}
