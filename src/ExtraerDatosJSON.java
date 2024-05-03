import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ExtraerDatosJSON {
    public double obtenerValorMoneda(String json,String monedaCambio){
        Gson gson=new Gson();
        DatosJSON datos = gson.fromJson(json, DatosJSON.class);
        return datos.conversion_rate();
    }
}
