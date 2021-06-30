package com.example.demo.Vehicle.Verbindung;
import com.example.demo.Vehicle.VehicleModel.Vehicle;
import org.json.JSONObject;
import java.net.URI;
import java.net.http.HttpClient;
import static java.net.http.HttpRequest.*;
import static java.net.http.HttpResponse.BodyHandlers.*;

public class Connection {

    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

    public static Vehicle reg(int position) throws Exception {
        var request = newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8080/addVehicle/" + position))
                .build();

        var response = httpClient.send(request, ofString());

        var jObject = new JSONObject(response.body());

        Vehicle vehicle = new Vehicle(
                jObject.getInt("id"),
                jObject.getInt("frontID"),
                jObject.getInt("backID"),
                jObject.getInt("vehicleSpeed"),
                jObject.getBoolean("isMaster")
        );
        return vehicle;
    }

    public static void con (int id) throws Exception {
      var  request = newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8080/Verbunden/" + id))
                .build();

        var response = httpClient.send(request, ofString());

    }

}
