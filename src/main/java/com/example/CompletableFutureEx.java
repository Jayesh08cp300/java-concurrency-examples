package com.example;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureEx {

	public static void main(String[] args) {
		// Create a CompletableFuture that will complete with a result
		CompletableFuture<String> future1 = CompletableFuture.completedFuture("Hello");

		// Create a CompletableFuture that will complete asynchronously
		CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
			try {
				// Simulate a time-consuming task
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "World";
		});

		// Combine the results of the two CompletableFutures
		CompletableFuture<String> combinedFuture = future1.thenCombine(future2, (result1, result2) -> result1 + " " + result2);

		// Block and wait for the combined future to complete
		try {
			System.out.println("Hi1");
			String combinedResult = combinedFuture.get();
			System.out.println("Hi2");
			System.out.println(combinedResult); // Output: Hello World
			System.out.println("Hi3");
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
}
