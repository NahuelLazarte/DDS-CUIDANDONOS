package personas;
import lombok.Getter;
import lombok.Setter;
import personas.Cuidador;
import utils.sexo;
import incidentes.Incidente;

import java.util.List;

@Setter
@Getter
public class Viajero {
    private String nombre;
    private String apellido;
    private Integer edad;
    private sexo sexo;
    private Boolean notificacionesActivadas;
    private Incidente reaccionarIncidente;
    public List<Cuidador> cuidadores;


    public void RealizarViaje(String origen, String Destino){
        Viaje nuevoViaje= new Viaje (origen, destino)

    }


}
