package modelo;

import java.net.http.*;
import java.net.URI;
import org.json.JSONArray;
import org.json.JSONObject;

public class Api {

    public static String[] obtenerFrases() throws Exception {
        String apiUrl = "https://zenquotes.io/api/quotes";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JSONArray jsonArray = new JSONArray(response.body());
        String[] frases = new String[5]; // solo queremos las primeras 5

        for (int i = 0; i < 5; i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            frases[i] = obj.getString("q"); // solo extraemos "q"
        }

        return frases;
    }
}

