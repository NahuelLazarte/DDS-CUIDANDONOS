package personas;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Cuidador {
    private boolean respuestaViaje = false;

    public void recibirNotificacion(String s, Viajero viajero) {
        System.out.println(s);
    }

    public boolean solicitarViaje(Viajero viajero) {
        return this.respuestaViaje;
    }

    public void aceptarViaje(){
        this.respuestaViaje = true;
    }

    public void rechazarViaje(){
        this.respuestaViaje = false;
    }
}

