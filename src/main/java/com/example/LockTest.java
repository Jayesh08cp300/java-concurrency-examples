package com.example;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class PrintDemo {
	private final Lock queueLock = new ReentrantLock();

	public void print() {
		queueLock.lock();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		try {
			Long duration = (long) (Math.random() * 10000);
			System.out.println(Thread.currentThread()
					.getName() + "  Time Taken " + (duration / 1000) + " seconds.");
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			System.out.printf("%s printed the document successfully.\n", Thread.currentThread()
					.getName());
			queueLock.unlock();
		}
	}
}

class ThreadsDemo extends Thread {
	PrintDemo printDemo;

	ThreadsDemo(String name, PrintDemo printDemo) {
		super(name);
		this.printDemo = printDemo;
	}

	@Override
	public void run() {
		System.out.printf("%s starts printing a document\n", Thread.currentThread()
				.getName());
		printDemo.print();
	}
}

public class LockTest {

	public static void main(String args[]) {
		PrintDemo PD = new PrintDemo();

		ThreadsDemo t1 = new ThreadsDemo("Thread - 1 ", PD);
		ThreadsDemo t2 = new ThreadsDemo("Thread - 2 ", PD);
		ThreadsDemo t3 = new ThreadsDemo("Thread - 3 ", PD);
		ThreadsDemo t4 = new ThreadsDemo("Thread - 4 ", PD);

		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}
}