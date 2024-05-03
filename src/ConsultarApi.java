import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultarApi {
    public String consulta(String origen, String cambio) throws IOException, InterruptedException {
        String apiKey="177af6b449c39510e2557f6e";
        String monedaOrigen=origen;
        String monedaCambio=cambio;
        String direccion="https://v6.exchangerate-api.com/v6/"+apiKey+"/pair/"+monedaOrigen+"/"+monedaCambio;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(direccion))
                .build();

        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        return json;
    }
}
