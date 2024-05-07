package utils;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.errors.ApiException;
import com.google.maps.model.*;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import recorrido.Direccion;
import recorrido.Viaje;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Setter
@Getter

public class adapterApi {
    public Distance distancia=null;
    public String tiempo=null;


    public static GeoApiContext buildearApi() {
        String apiKey = "AIzaSyBq1zVwI7EPVl6BXQI8vU4WH5q4n1fjOgc"; // hardcodeo para que no haya problemas entre distintos entornos
        // Lo correcto seria leer la apikey de un archivo
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(apiKey)
                .build();
        return context;
    }


    public static @NotNull List<String> normalizarDatos(@NotNull Viaje viaje) { // esto podria ser responsabilidad de la clase Viaje tambien pero es segun interpretacion, yo considero que entra
        // mejor en el adapter porque justamente se dedica a eso, adaptar la informacion para poder usar la api correctamente
        List<String> direccionNormalizada = new ArrayList<>();
        Direccion origen = viaje.getOrigen();
        Direccion destino = viaje.getDestino();

        direccionNormalizada.add(origen.getCalle() + " " + origen.getAltura() + ", Buenos Aires, Argentina");
        direccionNormalizada.add(destino.getCalle() + " " + destino.getAltura() + ", Buenos Aires, Argentina");

        return direccionNormalizada;
    }

    public void calcularViaje(Viaje viaje) throws IOException, InterruptedException, ApiException {
        List<String> direccionNormalizada = normalizarDatos(viaje);
        String origen = direccionNormalizada.get(0);
        String destino = direccionNormalizada.get(1);

        GeoApiContext context = buildearApi();

        DistanceMatrixApiRequest request = DistanceMatrixApi.newRequest(context)
                .origins(origen)
                .destinations(destino)
                .mode(TravelMode.DRIVING) // Modo de viaje, por ejemplo, en auto
                .units(Unit.METRIC); // Unidades métricas

        DistanceMatrix distanceMatrix = request.await();

        // Obtener la distancia y el tiempo de viaje
        Distance distanciaP = distanceMatrix.rows[0].elements[0].distance; // Distancia en metros
        String tiempoP= distanceMatrix.rows[0].elements[0].duration.humanReadable; // Tiempo de viaje legible para humanos

        this.distancia= distanciaP;
        this.tiempo=tiempoP;


    }

    public String getTiempo() {
        return tiempo;
    }

    public Distance getDistancia() {
        return distancia;
    }
}








