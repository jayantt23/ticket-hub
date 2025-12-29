package com.jayant.booking_service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrencyTest {

    private static final String JWT_TOKEN = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiVVNFUiIsInVzZXJJZCI6Niwic3ViIjoidGVzdFVzZXJAZW1haWwuY29tIiwiaWF0IjoxNzY2OTk0NjcxLCJleHAiOjE3NjcwODEwNzF9._8mE0yKcX_zl9vZhPOxESKR3kGY8s1Alyl_P4aw3_kM";

    private static final String URL = "http://localhost:8080/bookings";

    // The Payload: Trying to book Show 1, Seat A10
    private static final String JSON_BODY = """
        {
            "showId": 1,
            "seats": ["A11", "A12"]
        }
    """;

    public static void main(String[] args) throws InterruptedException {
        int numberOfThreads = 50;
        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);

        CountDownLatch latch = new CountDownLatch(1);

        AtomicInteger successCount = new AtomicInteger(0);
        AtomicInteger failCount = new AtomicInteger(0);

        System.out.println("🚀 Preparing " + numberOfThreads + " concurrent requests...");

        for (int i = 0; i < numberOfThreads; i++) {
            executor.submit(() -> {
                try {
                    latch.await(); // Wait for the gun shot
                    sendRequest(successCount, failCount);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        Thread.sleep(1000);
        System.out.println("🔫 FIRE!");
        latch.countDown();

        executor.shutdown();
        Thread.sleep(5000);

        System.out.println("\n--- 🏁 TEST RESULTS ---");
        System.out.println("✅ Successful Bookings: " + successCount.get());
        System.out.println("❌ Failed/Blocked:      " + failCount.get());

        if (successCount.get() == 1 && failCount.get() == (numberOfThreads - 1)) {
            System.out.println("🏆 CONCURRENCY TEST PASSED: Locking is working perfectly!");
        } else {
            System.out.println("⚠️ TEST FAILED: Locking logic might be broken.");
        }
    }

    private static void sendRequest(AtomicInteger success, AtomicInteger fail) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URL))
                    .header("Content-Type", "application/json")
                    .header("Authorization", JWT_TOKEN) // Add Token header
                    .POST(HttpRequest.BodyPublishers.ofString(JSON_BODY))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Thread " + Thread.currentThread().getId() + " -> Status: " + response.statusCode());

            if (response.statusCode() == 200 || response.statusCode() == 201) {
                success.incrementAndGet();
            } else {
                fail.incrementAndGet();
            }
        } catch (Exception e) {
            fail.incrementAndGet();
            System.err.println("Request Error: " + e.getMessage());
        }
    }
}