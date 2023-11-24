package pe.edu.utp.principal.otro;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Cesar Abel Bustamante Inoach
 */
public class VALORES {
    /**
     *
     * @return Este es un tostring
     */

    @Override
    public String toString() {
        return "VALORES{" +
                "ID=" + ID +
                ", FECHA_UTC=" + FECHA_UTC +
                ", HORA_UTC=" + HORA_UTC +
                ", LATITUD=" + LATITUD +
                ", LONGITUD=" + LONGITUD +
                ", PROFUNDIDAD=" + PROFUNDIDAD +
                ", MADNITUD=" + MADNITUD +
                ", FECHA_CORTE=" + FECHA_CORTE +
                '}';
    }

    /**
     * Constructor Vacio
     */
    public VALORES() {
    }

    /**
     * Constructor completo
     * @param ID
     * @param FECHA_UTC
     * @param HORA_UTC
     * @param LATITUD
     * @param LONGITUD
     * @param PROFUNDIDAD
     * @param MADNITUD
     * @param FECHA_CORTE
     */
    public VALORES(int ID, LocalDate FECHA_UTC, LocalTime HORA_UTC, float LATITUD, float LONGITUD, int PROFUNDIDAD, float MADNITUD, LocalDate FECHA_CORTE) {
        this.ID = ID;
        this.FECHA_UTC = FECHA_UTC;
        this.HORA_UTC = HORA_UTC;
        this.LATITUD = LATITUD;
        this.LONGITUD = LONGITUD;
        this.PROFUNDIDAD = PROFUNDIDAD;
        this.MADNITUD = MADNITUD;
        this.FECHA_CORTE = FECHA_CORTE;
    }

    /**
     *
     * Getters & Setters
     *
     */

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public LocalDate getFECHA_UTC() {
        return FECHA_UTC;
    }

    public void setFECHA_UTC(LocalDate FECHA_UTC) {
        this.FECHA_UTC = FECHA_UTC;
    }

    public LocalTime getHORA_UTC() {
        return HORA_UTC;
    }

    public void setHORA_UTC(LocalTime HORA_UTC) {
        this.HORA_UTC = HORA_UTC;
    }

    public float getLATITUD() {
        return LATITUD;
    }

    public void setLATITUD(float LATITUD) {
        this.LATITUD = LATITUD;
    }

    public float getLONGITUD() {
        return LONGITUD;
    }

    public void setLONGITUD(float LONGITUD) {
        this.LONGITUD = LONGITUD;
    }

    public int getPROFUNDIDAD() {
        return PROFUNDIDAD;
    }

    public void setPROFUNDIDAD(int PROFUNDIDAD) {
        this.PROFUNDIDAD = PROFUNDIDAD;
    }

    public float getMADNITUD() {
        return MADNITUD;
    }

    public void setMADNITUD(float MADNITUD) {
        this.MADNITUD = MADNITUD;
    }

    public LocalDate getFECHA_CORTE() {
        return FECHA_CORTE;
    }

    public void setFECHA_CORTE(LocalDate FECHA_CORTE) {
        this.FECHA_CORTE = FECHA_CORTE;
    }
    /**
     * *Estos son los atributos
     */
    private int ID;
    private LocalDate FECHA_UTC;
    private LocalTime HORA_UTC;
    private float  LATITUD;
    private float  LONGITUD;
    private int  PROFUNDIDAD;
    private float  MADNITUD;
    private LocalDate  FECHA_CORTE;
}
