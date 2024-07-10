import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestClient1 {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Runnable task1 = () -> {
            // Send request 1
            sendRequest("http://localhost:8080/myfirst/additem", "param1=value1&param2=value2");
        };

        Runnable task2 = () -> {
            // Send request 2
            sendRequest("http://localhost:8080/myfirst/additem", "param3=value3&param4=value4");
        };
        Runnable task3 = () -> {
            // Send request 3
            sendRequest("http://localhost:8080/myfirst/additem", "param4=value4&param5=value5");
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
            connection.getOutputStream().write(params.getBytes());
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// public class TestClient2 {
//     public static void main(String[] args) {
//         ExecutorService executor = Executors.newFixedThreadPool(2);

//         Runnable task1 = () -> {
//             // Send request 3
//             sendRequest("http://localhost:8080/myfirst/additem.html", "param5=value5&param6=value6");
//         };

//         Runnable task2 = () -> {
//             // Send request 4
//             sendRequest("http://localhost:8080/myfirst/additem.html", "param7=value7&param8=value8");
//         };

//         executor.submit(task1);
//         executor.submit(task2);

//         executor.shutdown();
//     }

//     private static void sendRequest(String urlString, String params) {
//         try {
//             URL url = new URL(urlString);
//             HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//             connection.setRequestMethod("POST");
//             connection.setDoOutput(true);
//             connection.getOutputStream().write(params.getBytes());
//             int responseCode = connection.getResponseCode();
//             System.out.println("Response Code: " + responseCode);
//             connection.disconnect();
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }
// }
