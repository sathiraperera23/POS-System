import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ReportTestClient {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Runnable task1 = () -> {
            // Send request 1
            sendRequest("http://localhost:8080/myfirst/report", "reportNumber=1");
        };

        Runnable task2 = () -> {
            // Send request 2
            sendRequest("http://localhost:8080/myfirst/report", "reportNumber=2");
        };

        Runnable task3 = () -> {
            // Send request 2
            sendRequest("http://localhost:8080/myfirst/report", "reportNumber=3");
        };

        executor.submit(task1);
        executor.submit(task2);
        executor.submit(task3);


        executor.shutdown();
    }

    private static void sendRequest(String urlString, String params) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length", String.valueOf(params.length()));

            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(params.getBytes());
            outputStream.flush();
            outputStream.close();

            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
