package com.example;

class Test implements Runnable {

	private ThreadLocal<String> integerThreadLocal = new ThreadLocal<>();
	private String threadName;

	public Test(String threadName) {
		this.threadName = threadName;
	}

	@Override
	public void run() {
		integerThreadLocal.set(threadName);
		System.out.println("ThreadName =" + threadName + ", value = " + integerThreadLocal.get());
	}
}

public class ThreadLocalEx {
	public static void main(String[] args) {
		new Thread(new Test("Thread-1")).start();
		new Thread(new Test("Thread-2")).start();
		new Thread(new Test("Thread-3")).start();
		new Thread(new Test("Thread-4")).start();
	}
}
