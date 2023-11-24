package pe.edu.utp.principal.otro;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
/**
 * Clase TextUTP que proporciona funciones para leer y cargar datos desde un archivo CSV.
 * También incluye funciones para manipular archivos de texto y leer usuarios desde un archivo de texto.
 * Los datos leídos desde el archivo CSV se almacenan en un arreglo de objetos VALORES.
 * Los datos de los usuarios se leen y almacenan en un arreglo de objetos Usuario.
 *
 * @author Cesar Abel Bustamante Inoach
 * @author Fabricio Arturo Elias Gonzales
 * @author Huilla Rosillo Jesús Antonio
 * @author Bocanegra Zurita Roberth Smit
 * @author Ruiz Oblitas Moisés Alejandro
 * @author Angel Arcadio Cespedes Palomino
 */
public class TextUTP {

    /**
     * Enumeración que representa el sistema operativo (Windows o Linux).
     */
    public enum OS {WINDOWS, LINUX}
    /**
     * Arreglo de objetos VALORES que almacena los datos leídos desde el archivo CSV.
     */
    public static  VALORES [] listaValores = new VALORES[0];
    /**
     * Obtiene la lista de valores leídos desde el archivo CSV.
     *
     * @return Arreglo de objetos VALORES.
     */
    public static VALORES[] getListaValores() {
        return listaValores;
    }
    /**
     * Enumeración que representa los meses del año.
     */
    public enum FECHA{ENERO,FEBRERO,MARZO,ABRIL,JUNIO,JULIO,AGOSTO,SEPTIEMBRE,OCTUBRE,NOVIEMBRE,DICIEMBRE}
    //######################################################################################################################################
    //[LINEA 16-LINEA 32] ESTE PEDAZO DE CODIGO SIRVE PARA LEER EL CSV, SOLO LEE, SIM EMBARGO NO LO ALMACENA EN NINGUN LUGAR

    /**
     * Lee el archivo CSV ubicado en "./src/main/resources/SismosPeru.csv"
     * e imprime sus contenidos línea por línea.
     *
     * @throws IOException si ocurre un error de lectura o ubicación del archivo.
     */
    public static void LeerCSV() throws IOException {
        String llamada_general = "./src/main/resources/SismosPeru.csv";
        try {
            BufferedReader buffeo_archivos_csv = new BufferedReader(new FileReader(llamada_general));
        String linea_por_linea;
        while ((linea_por_linea = buffeo_archivos_csv.readLine()) != null) {
            String [] partes_codigo =linea_por_linea.split(",");
            StringBuilder fila = new StringBuilder();
            for (String leer_cada_uno : partes_codigo) {
                fila.append(leer_cada_uno).append(" ");
            }
            System.out.println(fila.toString());
        }
    }catch (IOException ex){
            System.out.println("El archivo posiblemente no esta bien ubicado: "+ex.getMessage());
        }
    }
    //######################################################################################################################################
    //  continuacion todo este codigp servira para cargar los datos
    // [linea 36 - linea 66]  Este clase CARGA_DATOS, lo que hace es almacenar los datos en diferentes arrays
    /**
     * Carga los datos desde el archivo CSV ubicado en "./src/main/resources/SismosPeru.csv"
     * y almacena los datos en un arreglo de objetos VALORES.
     * El método realiza validaciones en los datos leídos y omite las líneas que no cumplan con las especificaciones.
     *
     * @throws IOException si ocurre un error de lectura o ubicación del archivo.
     */
    public static void CARGAR_DATOS()throws IOException{
        String llamada_general = "./src/main/resources/SismosPeru.csv";
        try {

            BufferedReader buffeo_archivos_csv = new BufferedReader(new FileReader(llamada_general));
            String line ;
            buffeo_archivos_csv.readLine();
            DateTimeFormatter formatter_utc = DateTimeFormatter.ofPattern("yyyyMMdd");
            DateTimeFormatter formatter_corte = DateTimeFormatter.ofPattern("yyyyddMM");
            DateTimeFormatter tiem = DateTimeFormatter.ofPattern("HHmmss");
            VALORES [] nueva_lista = new VALORES[0];
            while ((line= buffeo_archivos_csv.readLine())!=null){
                String [] dividido =line.split(",");

                if (dividido.length < 7){
                    System.out.println("Hola aña");
                    continue;
                }

                int ID = Integer.parseInt(dividido[0]);
                String  fecha_utc =dividido[1];
                String hora_utc = dividido[2];
                float latitud=Float.parseFloat(dividido[3]);
                float longitud=Float.parseFloat(dividido[4]);
                int profundidad = Integer.parseInt(dividido[5]);
                float magnitud=Float.parseFloat(dividido[6]);
                String fecha_corte =dividido[7];
                //Estos son los formatos de fecha
                LocalDate date = LocalDate.parse(fecha_utc,formatter_utc);
                LocalTime tiemp = LocalTime.parse(hora_utc,tiem);
                LocalDate corte =LocalDate.parse(fecha_corte,formatter_corte);

                //En este codigo se prsentan las validaciones, dara continue si no cumple con las especificaciones
                if (!VALIDAR_DATOS.ID_VALIDADOR(dividido[0])){
                    Metodos_Staticos.AGREGAR_REGISTROS("Error no cumple con el formato entero: "+dividido[0]);
                    continue;
                }
                if (!VALIDAR_DATOS.Validador_fecha_Utc(dividido[1])){
                    Metodos_Staticos.AGREGAR_REGISTROS("Error no cumple con el formato fecha_utc: "+dividido[1]);
                    continue;
                }
                if (!VALIDAR_DATOS.Validador_Hora_utc(dividido[2])){
                    Metodos_Staticos.AGREGAR_REGISTROS("Error no cumple con el formato hora_utc: "+ dividido[2]);
                    continue;
                }
                if (!VALIDAR_DATOS.Validador_Latitud(dividido[3])){
                    Metodos_Staticos.AGREGAR_REGISTROS("No cumple con el formato float latitud: "+dividido[3]);
                    continue;
                }
                if (!VALIDAR_DATOS.Validador_Longitud(dividido[4])){
                    Metodos_Staticos.AGREGAR_REGISTROS("No cumple con el formato float longitud: "+dividido[4]);
                    continue;
                }
                if (!VALIDAR_DATOS.Validador_Profundidad(dividido[5])){
                    Metodos_Staticos.AGREGAR_REGISTROS("No cumple con el formato entero profundidad: "+dividido[5]);
                    continue;
                }
                if (!VALIDAR_DATOS.Validador_Magnitud(dividido[6])){
                    Metodos_Staticos.AGREGAR_REGISTROS("No cumple con el formato flotante magnitud: "+dividido[6]);
                    continue;
                }
                if (!VALIDAR_DATOS.Validador_Fecha_Corte(dividido[7])) {
                    Metodos_Staticos.AGREGAR_REGISTROS("No cumple con el formato fecha_corte: "+dividido[7]);
                    continue;
                }
                VALORES v1 = new VALORES(ID,date,tiemp,latitud,longitud,profundidad,magnitud,corte);
                nueva_lista=Arrays.copyOf(nueva_lista,nueva_lista.length+1);
                nueva_lista[nueva_lista.length-1]=v1;
            }
            TextUTP.listaValores = nueva_lista;
        }catch (IOException ex){
           Metodos_Staticos.AGREGAR_REGISTROS("ERROR: "+ex.getMessage());
        }catch (NumberFormatException es){
            Metodos_Staticos.AGREGAR_REGISTROS("Error: "+es.getMessage());
        }catch (Exception es) {
           Metodos_Staticos.AGREGAR_REGISTROS("Error al intentar formatear las fechas: " + es.getMessage());
        }
    }

    /**
     * @author JesusHuilla
     */
    /**
     * Lee y carga los datos de usuarios desde el archivo "usuarios.txt".
     * Los usuarios se almacenan en un arreglo de objetos Usuario.
     *
     * @return Un arreglo de objetos Usuario con los datos leídos desde el archivo.
     * @throws RuntimeException si ocurre un error de lectura o ubicación del archivo.
     */

    public static Usuario[] listaUsuarios(){
        String ruta = TextUTP.class.getClassLoader().getResource("usuarios.txt").getPath();
        Usuario[] usuarios = new Usuario[1];
        try {
            FileReader fr = new FileReader(ruta);
            BufferedReader bf = new BufferedReader(fr);

            String linea;
            int i = 0;
            while ((linea = bf.readLine()) != null){
                if (i > 0){
                    String[] campos = linea.split(",");
                    String username = campos[0];
                    String password = campos[1];
                    Usuario usuario = new Usuario(username, password);
                    usuarios[i-1] = usuario;
                }
                i++;
            }

        } catch (FileNotFoundException e) {
            Metodos_Staticos.AGREGAR_REGISTROS(String.valueOf(new RuntimeException(e)));
        } catch (IOException e) {

            Metodos_Staticos.AGREGAR_REGISTROS(String.valueOf(new RuntimeException(e)));
        }
        return usuarios;
    }
    /**
     *
     */
    //Segundo reporte html

    //Llamar a los datos para imprimir

    public static String read(String filename) throws IOException {
        try(BufferedInputStream in = new BufferedInputStream(
                new FileInputStream(filename))
        ){
            String data = new String(in.readAllBytes(), StandardCharsets.UTF_8);
            return data;
        } catch (IOException e) {
            throw e;
        }
    }


    public static List<String> readlines(String filename, TextUTP.OS os) throws IOException {
        String delim = (os == OS.WINDOWS) ? "\r\n" : "\n";
        String data = read(filename);
        List<String> res = new LinkedList<>();
        if (data.length() > 0){
            res = Arrays.asList(data.split(delim));
        }
        return res;
    }


    public static List<String> readlines(String filename) throws IOException {
        return readlines(filename, OS.LINUX);
    }

    public static String[] readlinesAsArray(String filename, TextUTP.OS os) throws IOException {
        String delim = (os == OS.WINDOWS) ? "\r\n" : "\n";
        String data = read(filename);
        String[] res = new String[]{};
        if (data.length() > 0){
            res = data.split(delim);
        }
        return res;
    }

    public static String[] readlinesAsArray(String filename) throws IOException {
        return readlinesAsArray(filename,OS.LINUX);
    }

    private static void writeText(byte[] data, String filename)
            throws IOException{
        try(BufferedOutputStream out = new BufferedOutputStream(
                new FileOutputStream(filename,true))
        ){
            out.write(data);
        } catch (IOException e) {
            throw e;
        }
    }

    public static void append(String data, String filename) throws IOException {
        writeText(data.getBytes(), filename);
    }

    public static void append(String[] data, String filename, boolean withNewLine, TextUTP.OS os) throws IOException {
        String delim = (os == OS.WINDOWS) ? "\r\n" : "\n";
        StringBuilder sb = new StringBuilder();
        for (String item : data) {
            if (withNewLine == true) {
                sb.append(item + delim);
            }else{
                sb.append(item);
            }
        }
        writeText(sb.toString().getBytes(), filename);
    }

    public static void append(String[] data, String filename, boolean withNewLine) throws IOException {
        append(data, filename, withNewLine, OS.LINUX);
    }

    public static void append(String[] data, String filename) throws IOException {
        append(data, filename, true);
    }
    public static void append(List<String> data, String filename, boolean withNewLine, TextUTP.OS os) throws IOException {
        String delim = (os == OS.WINDOWS) ? "\r\n" : "\n";
        StringBuilder sb = new StringBuilder();
        for (String item : data) {
            if (withNewLine == true) {
                sb.append(item + delim);
            }else{
                sb.append(item);
            }
        }
        writeText(sb.toString().getBytes(), filename);
    }

    public static void append(List<String> data, String filename, boolean withNewLine) throws IOException {
        append(data, filename, withNewLine, OS.LINUX);
    }
}