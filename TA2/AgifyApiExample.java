import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

public class AgifyApiExample {
    public static void main(String[] args) {
        // List of 20 names to predict ages
        List<String> names = Arrays.asList(
                "Alice", "alice", "Charlie", "David", "Eva",
                "Frank", "Grace", "Hannah", "Ivan", "Julia",
                "Kevin", "Laura", "Mike", "Nina", "Oscar",
                "Paul", "Quincy", "Rachel", "Steve", "Tina"
        );

        // Create an HttpClient
        HttpClient client = HttpClient.newHttpClient();

        for (String name : names) {
            // Build the request for each name
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.agify.io/?name=" + name))
                    .GET()
                    .build();

            try {
                // Send the request and get the response
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                // Print the response
                System.out.println("Name: " + name + ", Response: " + response.body());
            } catch (Exception e) {
                System.out.println("Error fetching age for name: " + name);
                e.printStackTrace();
            }
        }
    }
}
