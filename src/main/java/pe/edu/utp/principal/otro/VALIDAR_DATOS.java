package pe.edu.utp.principal.otro;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author cesar
 * @version 1.1
 * EN ESTE SECTOR SE VALIDARON LOS DATOS, ESTE SERA UTILIZADO EN LA CLASE TextUTP para verfiicar que los datos cumples con sus tipos de datoa
 * La clase VALIDAR_DATOS contiene diversas validaciones de datos en forma de métodos estáticos que devuelven un valor booleano
 * indicando si los datos cumplen con los formatos establecidos o no.
 * Esta clase utiliza las clases del paquete java.time para manejar fechas y horas, y los formatos de fecha que se utilizan
 * están definidos en constantes como DateTimeFormatter.
 */
public class VALIDAR_DATOS {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final DateTimeFormatter formatter_corte = DateTimeFormatter.ofPattern("yyyyddMM");
    private static final DateTimeFormatter tiem = DateTimeFormatter.ofPattern("HHmmss");

    /**
     * Primera validacion a ID
     * @param id El ID a validar como una cadena.
     * @return true si el ID es un número entero válido y mayor o igual a cero, false de lo contrario.
     */

    public static Boolean ID_VALIDADOR(String id) {
        try {
            int Validador_ID = Integer.parseInt(id);
            return Validador_ID >= 0;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    /**
     * Segunda validacion Fecha_utc
     * @param fecha_utc La fecha en formato "yyyyMMdd" a validar.
     * @return true si la fecha es válida, false de lo contrario.
     */

    public static boolean Validador_fecha_Utc(String fecha_utc){
        try {
            LocalDate Validador_fechautc= LocalDate.parse(fecha_utc,formatter);
            return true;
        }catch (Exception ex ){
            return false;
        }
    }

    /**
     * Tercera Validacion Hora_Utc
     * @param hora_utc La hora en formato "HHmmss" a validar.
     * @return true si la hora es válida, false de lo contrario.
     */

    public static boolean  Validador_Hora_utc (String hora_utc){
        try {
            LocalTime Hora_utc= LocalTime.parse(hora_utc,tiem);
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    /**
     * Cuarta validacion Latitud
     * @param latitud La latitud a validar como una cadena.
     * @return true si la latitud es un número de punto flotante válido y mayor que cero o menor que cero, false de lo contrario.
     */

    public static boolean Validador_Latitud(String latitud){
        try {
            float VALLATITUD = Float.parseFloat(latitud);
            return  VALLATITUD>0 || VALLATITUD<=0;
        }catch (NumberFormatException ex){
            return false;
        }
    }

    /**
     * Quinta validacion Longitud
     * @param longitud La longitud a validar como una cadena.
     * @return true si la longitud es un número de punto flotante válido y mayor o igual que cero o menor o igual que cero, false de lo contrario.
     */

    public static boolean Validador_Longitud(String longitud){
        try {
            float LONGITUD = Float.parseFloat(longitud);
            return  LONGITUD>=0 || LONGITUD<=0;
        }catch (NumberFormatException ex){
            return false;
        }
    }

    /**
     * Sexta validacion Profundidad
     * @param profundidad La profundidad a validar como una cadena.
     * @return true si la profundidad es un número entero válido y mayor o igual a cero, false de lo contrario.
     */

    public static boolean Validador_Profundidad(String profundidad){
        try {
            int PROFUNDIDAD= Integer.parseInt(profundidad);
            return PROFUNDIDAD>=0;
        }catch (Exception ex){
            return false;
        }
    }

    /**
     * Septima validacion Magnitud
     * @param magnitud La magnitud a validar como una cadena.
     * @return true si la magnitud es un número de punto flotante válido y mayor o igual a cero, false de lo contrario.
     */

    public static boolean Validador_Magnitud(String magnitud){
        try {
            float MAGNITUD = Float.parseFloat(magnitud);
            return  MAGNITUD>=0;
        }catch (NumberFormatException ex){
            return false;
        }
    }

    /**
     * Octava validacion Fecha_Corte
     * @param fecha_corte La fecha en formato "yyyyddMM" a validar.
     * @return true si la fecha de corte es válida, false de lo contrario.
     */

    public static boolean Validador_Fecha_Corte (String fecha_corte){
        try {
            LocalDate FECHACORTE= LocalDate.parse(fecha_corte,formatter_corte);
            return true;
        }catch (Exception ex){
            return false;
        }
    }
}