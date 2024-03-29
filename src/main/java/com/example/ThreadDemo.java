package com.example;

class ThreadClass extends Thread {

	private Thread t;
	private String threadName;

	public ThreadClass(String name) {
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
		}
		System.out.println("Thread " + threadName + " exiting.");
	}

	public void start() {
		System.out.println("Starting " + threadName);

		if (t == null) {
			t = new Thread(this, threadName);
			t.start();
		}
	}
}

public class ThreadDemo {
	public static void main(String[] args) {
		ThreadClass R1 = new ThreadClass("Thread-1");
		R1.start();

		ThreadClass R2 = new ThreadClass("Thread-2");
		R2.start();

		ThreadClass R3 = new ThreadClass("Thread-3");
		R3.run();
	}
}
