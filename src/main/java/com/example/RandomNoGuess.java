package com.example;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

class RandomGuesser {
	private int randomNo;
	private Map<Integer, Integer> map = new HashMap<>();

	public RandomGuesser(int randomNo) {
		this.randomNo = randomNo;
	}

	public synchronized boolean guessNo(int guessNo, int waitFor) throws InterruptedException {
		Thread.sleep(waitFor);
		if (randomNo == guessNo) {
			return true;
		}
		setMap(guessNo);
		return false;
	}

	public void printMap() {
		for (Integer key : map.keySet()) {
			System.out.println(key + "->" + map.get(key));
		}
	}

	public void setMap(int i) {
		Integer j = map.get(i);
		if (null == j) {
			j = 0;
		}
		map.put(i, j + 1);
	}
}

class Player extends Thread {
	private String name;
	private RandomGuesser randomGuesser;

	private int waitFor;

	private int attempts;

	public Player(String name, RandomGuesser randomGuesser, int waitFor) {
		this.randomGuesser = randomGuesser;
		this.name = name;
		this.waitFor = waitFor;
		attempts = 0;
	}

	@Override
	public void run() {
		while (true) {

			int randomNo = ThreadLocalRandom.current()
					.nextInt(100);

			try {
				if (randomGuesser.guessNo(randomNo, waitFor)) {
					System.out.println(name + " -> I got It -> " + randomNo);
					break;
				} else {
					System.out.println(name + " -> I guessed It -> " + randomNo + ", attempts -> " + (attempts++));
				}
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}
}

public class RandomNoGuess {
	public static void main(String[] args) throws InterruptedException {
		RandomGuesser randomGuesser = new RandomGuesser(51);

		Player p1 = new Player("Jayesh", randomGuesser, 500);
		Player p2 = new Player("Amit", randomGuesser, 500);
		Player p3 = new Player("Kiran", randomGuesser, 500);

		p1.start();
		p2.start();
		p3.start();

		p1.join();
		p2.join();
		p3.join();

		randomGuesser.printMap();
	}
}
