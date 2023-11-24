package pe.edu.utp.principal.otro;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static pe.edu.utp.principal.otro.TextUTP.listaUsuarios;
public class Reportes {
    public static void LLAMADA_MENU() throws IOException {
        Scanner v = new Scanner(System.in);
        ///EXPORTACION ex = new EXPORTACION();
        /**
         * @author JesusHuilla
         * Este es un metodo que verifica el nombre de usuario y contraseña
         */
        String usuario;{
            int intentos = 0;
            do {
                System.out.print("Ingrese usuario: ");
                usuario = v.nextLine();
                System.out.print("Ingrese contraseña: ");
                String password = v.nextLine();
                /**
                 * La funcion de este for es recorrer listaUsuarios
                 */
                for (Usuario item : listaUsuarios()) {
                    if (usuario.equals(item.getUsername()) && password.equals(item.getPassword())) {
                        intentos = -1;
                        break;
                    }
                }

                if (intentos >= 0) {
                    /**
                     * Si es que no valida saltara este menu, avisando los intentos disponibles
                     */
                    intentos++;
                    System.out.println("--------------------------------------------------------");
                    System.out.println("Credenciales Incorrectas - queda " + (3 - intentos) + " intentos");
                    System.out.println("--------------------------------------------------------");

                    if (intentos == 3) {
                        System.out.println("Cerrando el Sistema");
                        System.exit(0);
                    }
                }

            } while (intentos != -1);
        }

        /**
         * Las variable de tipo Strings como las son exportar, reporte_concluido, menu_opcionA
         * menu_opcionB, menu_opcionC, menu_opcionD, menu_opcionE, menu, serviran para ejecutar
         */

        String exportar= """
                ##############################################################################
                # DESEA EXPORTAR EL ARCHIVO A DOCUMENTO TXT [SI O NO]                        #
                # [1] PRESIONA PARA EXPORTAR.                                                #
                # [2] SALIR DEL PROGRAMA SIN EXPORTAR.                                       # 
                ##############################################################################                
                """;
        String reporte_concluido= """
                ##############################################################################
                # EXPORTACION COMPLETA PUEDE REVISAR EN SU ESCRITORIO EL DOCUMENTO           #
                ##############################################################################                
                """;
        int exportar_opcion =0;
        String menu_opcionA = """
                            ##################################################################################################                
                            #  TABLA CON EL NUMERO DE EVENTOS SISMICOS POR AÑO DADO EL AÑO %S HASTA %s                  #
                            ##################################################################################################
                            """;
        String menu_opcionB = """
                            ##################################################################################################                
                            #  TABLA CON EL NUMERO DE EVENTOS SISMICOS POR MES DADOS EN EL AÑO: %s                        #
                            ##################################################################################################
                            """;
        String menu_opcionC = """
                            ##################################################################################################                
                            #  TABLA CON EL NUMERO DE EVENTOS SISMICOS POR MES DADOS UN RANGO DE MAGNITUD %.2f hasta %.2f Y AÑO %s                        #
                            ##################################################################################################
                            """;
        String menu_opcionD = """
                            ##################################################################################################                
                            #  TABLA PERSONALIZADA PARA EL USUARIO                                                           #
                            ##################################################################################################
                            """;
        String menu_opcionE = """
                            ##################################################################################################                
                            #  TABLA PERSONALIZADA CON GRAFICO ESTADISTICO                                        #
                            ##################################################################################################
                            """;
        String menu = """
                ##################################
                        MENU DE OPCIONES
                ##################################
                * [1] Leer datos
                * [2] Cargar datos
                * [3] Salir del programa
                ##################################
                """;
        int opcion =0;
        String OPCION_ESTADISTICO = """
                    ##################################################################################################
                    #         CARGA DE DATOS CON EXITO, AHORA PUEDE CONSULTAR LAS DIFERENTES OPCIONES DESEADAS      #              
                    ##################################################################################################
                                    
                                    
                    ##################################################################################################
                    #                 Opciones de Procesamiento Estdistico                                           # 
                    ##################################################################################################
                    # Presiona la tecla [A]. Tabla con el número de eventos sísmicos por año dado un rango de años.  #
                    #                                                                                                #
                    # Presiona la tecla [B]. Tabla con el número de eventos sísmicos por mes dado un año.            #
                    #                                                                                                #
                    # Presiona la tecla [C]. Tabla con el número de eventos sísmicos por mes dados un rango de       #   
                    # magnitudes y un año.                                                                           #
                    #                                                                                                #
                    # Presiona la tecla [D]. Tabla con un reporte personalizado a criterio del grupo                 #
                    #                                                                                                #
                    # Presiona [X] para salir                                                                        #
                    ##################################################################################################
                    #                 Ingresa la opcion deseada:                    
                    ##################################################################################################                
                    """;

        String menu_opciones = "";
        do {
            System.out.println(menu);
            System.out.println("Ingresa la opcion deseada: ");
            opcion=v.nextInt();v.nextLine();
            if(opcion==1){
                TextUTP.LeerCSV();
            }
            if(opcion==2){
                /**
                 * Si esta opcion es seleccionada, cargara datos y pedira al usuario por consola, las diferentes opciones a realizar
                 *TextUTP.CARGAR_DATOS(); (LLama a la clase TextUTP y al metodo CARGAR_DATOS())
                 */
                TextUTP.CARGAR_DATOS();

                VALORES[] DATA_CARGADA=TextUTP.getListaValores(); /**Esta opcion llama a los valores guardados en el objeto VALORES**/
                System.out.println(OPCION_ESTADISTICO);
                System.out.println("Ingresa tu opcion: ");
                menu_opciones=v.nextLine();
                /**
                 * Esta opcion lo que hace es elegir que tipo de registro deseas hacer
                 */

                if (menu_opciones.equalsIgnoreCase("a")){ /**Tabla con el número de eventos sísmicos por año dado un rango de años.**/
                    /***
                     * Le pide al usuario por consola que ingrese dos fechas
                     */
                    System.out.println("[1960 - 2021]  Introduce el primer año:  ");
                    int año_rango_1=v.nextInt();v.nextLine();
                    System.out.println("[1960 - 2021] Introduce el ultimo año:  ");
                    int año_rango_2=v.nextInt();v.nextLine();
                    System.out.printf(menu_opcionA,año_rango_1,año_rango_2);
                    if (año_rango_1 < 1960 || año_rango_2 > 2021 || año_rango_1 > año_rango_2) {
                        System.out.println("Rango de años inválido. Asegúrate de ingresar años válidos entre 1960 y 2021.");
                    } else {}
                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
                        int[] anios_cantidad = new int[año_rango_2 - año_rango_1 + 1];
                        int can = 0;

                        for (VALORES iterador : DATA_CARGADA) {
                            int anioEvento = iterador.getFECHA_UTC().getYear();
                            if (anioEvento >= año_rango_1 && anioEvento <= año_rango_2) {
                                int indexAnio = anioEvento - año_rango_1;
                                anios_cantidad[indexAnio]++;
                                can++;
                            }
                        }
                        System.out.println(" Año       Frecuencia de sismos ");
                        for (int i = 0; i < anios_cantidad.length; i++) {
                            int eventosEnAnio = anios_cantidad[i];
                            System.out.printf(" %d      %d%n", año_rango_1 + i, eventosEnAnio);
                        }
                        System.out.println("#######################");
                        System.out.printf(" Total De sismos registrados desde %s hasta %s:    %d%n",año_rango_1,año_rango_2, can);
                        System.out.println();

                        int opcion_expo=0;
                        do {
                            System.out.println(exportar);
                            opcion_expo = v.nextInt();v.nextLine();
                            /**
                             * @author FabricioElias
                             */
                            if (opcion_expo==1){
                                System.out.println(reporte_concluido);

                                String path = "./src/main/resources/Copiado.txt";
                                try {
                                    FileWriter archivo = new FileWriter(path);
                                    PrintWriter writer = new PrintWriter(archivo);

                                    writer.println("##################################################################################################");
                                    writer.printf("         TABLA CON EL NUMERO DE EVENTOS SISMICOS POR AÑO DADO EL AÑO %S HASTA %s%n", año_rango_1, año_rango_2);
                                    writer.println("##################################################################################################");

                                    writer.println(" Año       Frecuencia de sismos ");
                                    for (int i = 0; i < anios_cantidad.length; i++) {
                                        int eventosEnAnio = anios_cantidad[i];
                                        writer.printf(" %d      %d%n", año_rango_1 + i, eventosEnAnio);
                                    }
                                    writer.println("#######################");
                                    writer.printf("Total De sismos registrados desde %s hasta %s: %d%n", año_rango_1, año_rango_2, can);
                                    writer.println();
                                    writer.close();
                                    System.out.println("Texto exportado correctamente al archivo: " + path);

                                }catch (Exception es){
                                    Metodos_Staticos.AGREGAR_REGISTROS(es.getMessage());
                                }
                                opcion_expo=2;
                            }
                        }while (!(opcion_expo==2));
                    }catch (Exception es){
                        Metodos_Staticos.AGREGAR_REGISTROS(es.getMessage());
                    }
                }
                if (menu_opciones.equalsIgnoreCase("b")){
                    System.out.println("Introduce el año deseado: ");
                    int año1 = v.nextInt();v.nextLine();
                    System.out.printf(menu_opcionB,año1);
                    try {
                        String fecha_elegida=Integer.toString(año1);
                        DateTimeFormatter formatter =DateTimeFormatter.ofPattern("yyyy");
                        DateTimeFormatter formatterMonth = DateTimeFormatter.ofPattern("       MMMM");
                        int [] meses_cantidad =new int [12];
                        System.out.println();int can=0;
                        for (VALORES iterador : DATA_CARGADA) {
                            if(iterador.getFECHA_UTC().format(formatter).equals(fecha_elegida)){
                                int nombreMesYAnio = iterador.getFECHA_UTC().getMonthValue();
                                can++;
                                meses_cantidad[nombreMesYAnio -1]++;
                            }
                        }

                        String [] MESES = {"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Dciembre"};
                        String [] numero_mes={"1","2","3","4","5","6","7","8","9","10","11","12"};
                        System.out.println(" Nro    Mes   Frecuencia   Porcentaje ");
                        for (int i = 0; i < meses_cantidad.length; i++) {
                            if(meses_cantidad[i]>0){
                                String MesSeleccionado =MESES[i];
                                String nro=numero_mes[i];
                                int vecesRepetidas =meses_cantidad[i];
                                double porcentaje = (double) vecesRepetidas / can * 100;
                                System.out.printf(" %-5s%-12s%8d%7.2f%% \n",nro,MesSeleccionado,vecesRepetidas,porcentaje);
                            }
                        }
                        System.out.println();
                        int opcion_expo=0;
                        do {
                            System.out.println(exportar);
                            opcion_expo = v.nextInt();v.nextLine();
                            if (opcion_expo==1){
                                System.out.println(reporte_concluido);
                                /**
                                 * @author FabricioElias
                                 */
                                    String path = "./src/main/resources/REPORTE2.txt";

                                    StringBuilder DATOS_EXPORTAR = new StringBuilder();
                                    DATOS_EXPORTAR.append("##################################################################################################\n");
                                    DATOS_EXPORTAR.append(String.format(" TABLA CON EL NUMERO DE EVENTOS SISMICOS POR MES DADOS EN EL AÑO: %s%n",año1));
                                    DATOS_EXPORTAR.append("##################################################################################################\n");
                                    DATOS_EXPORTAR.append(" Nro    Mes   Frecuencia   Porcentaje \n");
                                    for (int i = 0; i < meses_cantidad.length; i++) {
                                        if(meses_cantidad[i]>0){
                                            String MesSeleccionado =MESES[i];
                                            String nro=numero_mes[i];
                                            int vecesRepetidas =meses_cantidad[i];
                                            double porcentaje = (double) vecesRepetidas / can * 100;
                                            DATOS_EXPORTAR.append(String.format(" %-5s%-12s%8d%7.2f%% \n",nro,MesSeleccionado,vecesRepetidas,porcentaje));
                                            opcion_expo=2;
                                        }
                                    }
                                    Metodos_Staticos.GUARDAR_ARCHIVOS(path,DATOS_EXPORTAR.toString());
                                    System.out.println("#########################################################################");
                                    System.out.println("#   Texto exportado correctamente al archivo: " + path);
                                    System.out.println("#########################################################################\n");
                                    /**
                                     *
                                     */
                            }else {
                                System.out.println("Saliendo del programa");
                                opcion_expo=2;
                            }
                        }while (!(opcion_expo==2));
                    }catch (Exception es ){
                        Metodos_Staticos.AGREGAR_REGISTROS(es.getMessage());
                    }
                }
                if (menu_opciones.equalsIgnoreCase("c")){
                    System.out.println("Introduce el año deseado: ");
                    String año = v.nextLine();
                    System.out.println("[0] Introduce el valor mínimo de magnitud: ");
                    float minMagnitud = v.nextFloat();v.nextLine();
                    System.out.println("[7.5] Introduce el valor máximo de magnitud: ");
                    float maxMagnitud = v.nextFloat();v.nextLine();
                    System.out.printf(menu_opcionC,minMagnitud,maxMagnitud,año);

                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
                        int[] meses_cantidad = new int[12];
                        int can = 0;

                        for (VALORES iterador : DATA_CARGADA) {
                            int anioEvento = iterador.getFECHA_UTC().getYear();
                            float magnitudEvento = iterador.getMADNITUD();
                            if (anioEvento == Integer.parseInt(año) && magnitudEvento >= minMagnitud && magnitudEvento <= maxMagnitud) {
                                int nombreMesYAnio = iterador.getFECHA_UTC().getMonthValue();
                                can++;
                                meses_cantidad[nombreMesYAnio - 1]++;
                            }
                        }
                        String[] MESES = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
                        String[] numero_mes = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};

                        System.out.println(" Nro    Mes         Frecuencia     Porcentaje ");

                        for (int i = 0; i < meses_cantidad.length; i++) {
                            if (meses_cantidad[i] > 0) {
                                String MesSeleccionado = MESES[i];
                                String nro = numero_mes[i];
                                int vecesRepetidas = meses_cantidad[i];
                                double porcentaje = (double) vecesRepetidas / can * 100;
                                System.out.printf(" %-5s%-12s%8d%7.2f%% \n", nro, MesSeleccionado, vecesRepetidas, porcentaje);
                            }
                        }
                        System.out.println();
                        int opcion_expo=0;
                        do {
                            System.out.println(exportar);
                            opcion_expo = v.nextInt();v.nextLine();
                            if (opcion_expo==1){
                                String path = "./src/main/resources/REPORTE3.txt";
                                StringBuilder DATOS_EXPORTAR2 = new StringBuilder();
                                    DATOS_EXPORTAR2.append("##################################################################################################\n");
                                    DATOS_EXPORTAR2.append(String.format("TABLA CON EL NUMERO DE EVENTOS SISMICOS POR MES DADOS UN RANGO DE MAGNITUD %.2f hasta %.2f, AÑO %s \n",minMagnitud,maxMagnitud,año));
                                    DATOS_EXPORTAR2.append("\n##################################################################################################\n");
                                    DATOS_EXPORTAR2.append(" Nro    Mes         Frecuencia     Porcentaje \n");
                                    for (int i = 0; i < meses_cantidad.length; i++) {
                                        if (meses_cantidad[i] > 0) {
                                            String MesSeleccionado = MESES[i];
                                            String nro = numero_mes[i];
                                            int vecesRepetidas = meses_cantidad[i];
                                            double porcentaje = (double) vecesRepetidas / can * 100;
                                            DATOS_EXPORTAR2.append(String.format(" %-5s%-12s%8d%7.2f%% \n", nro, MesSeleccionado, vecesRepetidas, porcentaje));
                                        }
                                        opcion_expo=2;
                                        Metodos_Staticos.GUARDAR_ARCHIVOS(path,DATOS_EXPORTAR2.toString());
                                    }
                                    System.out.println("#########################################################################");
                                    System.out.println("#   Texto exportado correctamente al archivo: " + path);
                                    System.out.println("#########################################################################\n");
                            }else {
                                System.out.println("Saliendo del programa");
                                opcion_expo=2;
                            }
                        }while (!(opcion_expo==2));
                    } catch (Exception es) {
                        System.out.println(es.getMessage());
                    }
                }
                if (menu_opciones.equalsIgnoreCase("d")){
                    System.out.printf(menu_opcionD,2020);
                    try {
                        String año="2021";
                        String fecha_elegida=año;
                        DateTimeFormatter formatter =DateTimeFormatter.ofPattern("yyyy");
                        DateTimeFormatter formatterMonth = DateTimeFormatter.ofPattern("       MMMM");
                        int [] meses_cantidad =new int [12];
                        System.out.println();int can=0;
                        for (VALORES iterador : DATA_CARGADA) {
                            if(iterador.getFECHA_UTC().format(formatter).equals(fecha_elegida)){
                                int nombreMesYAnio = iterador.getFECHA_UTC().getMonthValue();
                                can++;
                                meses_cantidad[nombreMesYAnio -1]++;
                            }
                        }
                        String [] MESES = {"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Dciembre"};
                        String [] numero_mes={"1","2","3","4","5","6","7","8","9","10","11","12"};
                        System.out.println(" Nro    Mes   Frecuencia   Porcentaje ");
                        for (int i = 0; i < meses_cantidad.length; i++) {
                            if(meses_cantidad[i]>0){
                                String MesSeleccionado =MESES[i];
                                String nro=numero_mes[i];
                                int vecesRepetidas =meses_cantidad[i];
                                double porcentaje = (double) vecesRepetidas / can * 100;
                                System.out.printf(" %-5s%-12s%8d%7.2f%% \n",nro,MesSeleccionado,vecesRepetidas,porcentaje);
                            }
                        }
                        System.out.println();
                        int opcion_expo=0;
                        do {
                            System.out.println(exportar);
                            opcion_expo = v.nextInt();v.nextLine();
                            if (opcion_expo==1){
                                System.out.println(reporte_concluido);
                                /**
                                 * @author FabricioElias
                                 */
                                String path = "./src/main/resources/REPORTE4.txt";
                                StringBuilder DATOS_EXPORTAR4 = new StringBuilder();
                                    DATOS_EXPORTAR4.append("##################################################################################################\n");
                                    DATOS_EXPORTAR4.append("                           TABLA PERSONALIZADA PARA EL USUARIO      2020\n");
                                    DATOS_EXPORTAR4.append("##################################################################################################\n");
                                    DATOS_EXPORTAR4.append(" Nro    Mes   Frecuencia   Porcentaje \n");
                                    for (int i = 0; i < meses_cantidad.length; i++) {
                                        if (meses_cantidad[i] > 0) {
                                            String MesSeleccionado = MESES[i];
                                            String nro = numero_mes[i];
                                            int vecesRepetidas = meses_cantidad[i];
                                            double porcentaje = (double) vecesRepetidas / can * 100;
                                            DATOS_EXPORTAR4.append(String.format(" %-5s%-12s%8d%7.2f%% \n", nro, MesSeleccionado, vecesRepetidas, porcentaje));
                                            opcion_expo = 2;
                                        }
                                        Metodos_Staticos.GUARDAR_ARCHIVOS(path,DATOS_EXPORTAR4.toString());
                                    }
                                System.out.println("#########################################################################");
                                System.out.println("#   Texto exportado correctamente al archivo: " + path);
                                System.out.println("#########################################################################\n");
                            }else {
                                System.out.println("Saliendo del programa");
                                opcion_expo=2;
                            }
                        }while (!(opcion_expo==2));
                    }catch (Exception es ){
                        Metodos_Staticos.AGREGAR_REGISTROS(es.getMessage());
                    }
                }
            }
            if(!(opcion==1 || opcion==2 ||opcion==3)){
                System.out.println("Intoduciste incorrectamenete vuelve a intentar :(");
            }
        }while (!(opcion==3));
        System.out.println("# Gracias por usar nuestra aplicacion :) #\n");
        String mee= """
                ⣤⣤⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⡤⠒⣢⣵⢦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠿⠛⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⠚⠉⠀⠀⣸⣿⣉⠃⠙⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⣀⣀⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⡤⠤⠤⠖⠒⠺⠛⠛⣿⠀⢀⣠⣤⣛⣛⠻⣷⡄⠈⢳⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠛⠛⠛⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⡴⠚⠉⠁⠀⠀⠀⠀⠀⠈⠀⠀⡏⠙⣿⣯⠉⠛⣫⣤⣿⣷⠃⣸⢧⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⡇⠀⣠⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣾⠟⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⠀⠈⢿⣿⣾⣷⣶⡿⠛⢠⠇⠀⠀⠙⠲⢄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠠⡠⠀⢀⣠⠄⠀⠀
                ⣷⠀⣿⣇⢀⣠⣤⣤⡀⠀⠀⠀⠀⠀⠀⠀⣿⣏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⠀⠀⠘⣿⡿⠋⠁⠀⢠⠟⠀⠀⠀⠀⠀⠀⢉⠲⢤⡀⠀⠀⠀⢠⣤⣼⡗⠊⠉⠀⠀⠀⠀
                ⣿⣄⠸⠿⠟⠛⠉⠉⠁⠀⠀⠀⠀⠀⠀⠀⠘⣽⡄⠀⠀⣀⣀⣠⣤⣤⣴⣶⣶⣤⣼⣇⠀⠀⢠⠏⠀⠀⠀⢠⡾⠀⠀⠀⠀⠀⠀⠀⠀⢙⣦⣙⢦⡀⡶⣿⣿⣏⡀⠀⠀⠀⠀⠀⠀
                ⠉⢁⣶⣤⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣻⡴⠞⠛⠋⠉⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠙⠛⠒⠶⠤⣄⣼⣇⣀⠀⠀⠀⠀⠀⠀⢸⡳⠈⣯⢳⣷⣯⢿⢹⣿⠇⠀⠀⠀⠀⠀⠀
                ⢀⣾⣿⡏⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⣴⣾⣿⣤⣤⣶⣶⣶⣶⣶⣶⣶⣤⣤⣤⣤⣤⣤⣀⣀⠀⠀⠀⠀⠈⠉⠛⠻⣶⣶⣶⢋⣷⣞⢳⡏⡿⢲⣿⣿⠹⣿⣿⠀⠀⠀⠀⠀⠀⠀
                ⠈⠻⣹⣷⣠⣤⡀⠀⠀⠀⠀⠀⢶⣿⣿⣿⣿⣿⣿⡿⠿⠿⣿⡿⠿⠿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣶⣤⣀⡀⠀⠀⠽⣥⡴⣶⣿⠰⢤⠀⢹⣿⠟⢾⣁⠁⠀⠀⠀⠀⠀⠀⠀
                ⡿⠿⠛⣿⡋⠉⠀⢶⣦⠀⠀⠀⠀⠙⠿⣿⣿⣻⡏⠀⠀⢰⠟⠀⠀⣠⠟⣏⡇⠀⠀⠀⠈⢉⠙⠛⠛⠻⠿⢿⣿⣿⣿⣶⣤⣀⠙⠛⠛⢿⡙⣛⣿⢿⣄⣯⠽⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⢸⡧⠀⢠⣬⣿⣿⢿⣿⠆⠀⠀⣸⣷⣿⠁⠀⢀⡏⠀⣨⢾⣅⠀⢸⡇⠀⠀⠀⠀⠘⣧⠀⠀⠰⣆⠀⠈⢻⠛⢿⣿⣿⣿⣶⣤⡈⣿⣧⣴⡆⢿⣷⠒⠶⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⣿⡄⠈⠉⠈⢿⣦⠀⠀⢀⣴⣿⣿⡿⠀⠀⣼⡵⠊⠁⠀⠈⠉⢻⡟⠁⠀⠀⠀⢸⡏⣧⣀⣠⣼⣶⠖⠈⡇⠈⣿⠈⠉⠻⣿⣿⣿⣷⣌⣻⣘⢿⣇⡀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⢻⣷⡀⠀⠀⠀⠁⢀⡴⠛⠋⢸⢿⡇⠀⢠⣿⣦⣤⣤⣤⣤⡀⠈⣧⠀⠰⡄⠀⢸⡇⠈⢯⣹⠀⠘⣧⠀⢹⡄⢸⡀⠀⠀⣿⡏⢿⣿⣿⣿⣟⣿⣝⠻⣦⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⣿⣧⠠⣀⣀⠴⣫⠀⣀⣠⣾⢸⡇⠀⢻⢿⡁⠀⣼⣿⡿⣿⡷⣾⡄⠀⢹⣄⢸⡇⠤⣬⣿⣄⣀⡈⢳⡀⢷⣸⠀⠀⢠⣿⡇⢸⡿⠿⠿⠿⠛⢿⣿⠿⠃⠀⠀⠀⠀⠀
                ⡀⠀⣀⣠⣤⠘⣛⣤⠀⠉⠉⣩⣿⡿⢻⡿⢸⡇⠀⢸⠀⢁⡀⢿⣷⣀⣾⡇⠀⢻⡀⠸⡛⢾⡇⠛⣿⣿⣿⣿⡿⢿⣿⣿⣿⠀⠀⣾⣸⠙⠘⣧⠀⠀⠀⠀⠸⣿⣷⣄⡀⠀⠀⠀⠀
                ⠇⠀⠹⣿⣿⡆⢹⣿⣤⣴⢞⣽⡿⠁⣼⣿⠀⣿⠀⢸⠀⠀⠀⠈⠛⠛⠛⠁⠀⠀⠻⣦⣧⠈⠓⠀⣿⣤⣬⣿⡇⠀⡽⢟⡿⠀⢠⠇⣿⢷⣄⠘⣦⡀⠀⠀⠀⠘⣿⣍⢻⣶⣤⡀⠀
                ⠀⠀⠀⢹⣿⣿⡦⠟⢉⣡⣿⡟⡁⣼⡟⢸⣦⠟⣧⡈⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠻⠄⠀⠀⠙⠿⠿⠟⠀⠄⢀⣼⠁⢀⡾⢠⣿⣔⣯⠹⣍⣉⠀⠀⠀⠀⠸⣿⣷⣿⣿⣿⣦
                ⠀⠀⠀⠀⠉⠀⠀⣠⣿⣿⠟⠄⣸⣿⡇⠈⣿⠀⡟⣷⣿⡀⠀⠀⠀⠀⠀⠀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣾⡏⣠⣟⣁⣿⠁⢠⡙⣷⠀⠉⣧⠀⠀⠀⠀⠘⣿⣿⣿⣿⣿
                ⡄⠀⠀⠀⠀⠀⣰⣽⢻⠋⠀⢸⣿⣿⠃⠀⡟⠀⣧⣿⣿⣷⡀⠀⠀⠀⠀⢸⠋⠉⠒⠒⠒⡆⠀⠀⠀⠀⠀⠀⢠⣿⣿⣾⠏⡋⢹⡻⣤⣦⣧⠙⣶⣶⠻⡄⣠⣄⠀⠀⠙⣿⡿⠟⠋
                ⣿⠀⠀⠀⠀⣼⣿⠃⠀⣀⣰⣿⣿⣿⠀⠀⡇⠀⢹⣿⣿⢹⣿⣦⣄⠀⠀⠈⠷⣄⣀⣀⠼⠁⠀⠀⠀⢀⣠⠖⠉⠉⣿⣿⣼⣧⣶⣿⡘⠿⠓⠀⠿⣏⣠⠏⠀⣸⠀⠀⠀⠀⠀⠀⠀
                ⠟⠀⠀⠀⡼⣽⡟⠉⢻⣿⠀⠀⢻⣿⠀⠀⡇⠀⢸⣿⠃⠘⣿⣿⣯⢹⣦⣄⠀⠀⠀⠀⢀⣀⣠⡴⠚⠋⠀⠀⠀⠀⠸⣿⠁⢸⣽⣿⡅⠀⠀⠀⠀⢠⣿⣧⣞⡛⡇⠀⠀⠀⠀⠀⠀
                ⣄⣴⢤⡞⠛⣏⢸⡀⠀⢿⡇⣶⣾⠋⠙⢾⡇⠀⢸⡏⠀⢀⣿⢿⣿⡇⠈⠻⣝⠒⠖⠚⣉⡡⠴⠟⣆⡀⠀⠀⠀⠀⠀⢻⣤⠞⣿⢏⣷⠢⢍⡉⠉⠃⠉⠀⠀⠀⡇⠀⠀⠀⠀⠀⠀
                ⣿⡿⡞⢣⠀⡸⡆⢷⣿⡿⠛⠿⢿⠀⠀⢸⡇⠀⠸⣯⣭⣭⣾⣿⡏⠁⠀⠀⠘⣆⡴⠋⠁⠀⠀⠀⣿⣽⣲⠤⢀⣀⠀⠘⡿⣆⠠⠊⠙⡆⠀⠉⠀⠀⠀⠀⠀⠀⡇⠀⠀⠀⠀⠀⠀
                ⡟⢠⡇⠸⢿⣿⣇⠈⠉⠁⠀⠀⢸⣶⣶⡟⣧⠀⠀⣿⣿⣿⣿⣿⣿⡀⠀⠠⠤⣿⣄⣤⠀⠀⠀⣴⣿⣿⣿⣿⣶⣾⢽⣖⡗⢉⠛⢦⣄⡀⠀⠀⠀⠀⠀⣀⣀⡤⢿⣿⣭⣽⣿⣿⣿
                ⣶⣿⠀⠀⠀⠉⠁⠀⠀⠀⠀⢀⢸⣿⣿⣦⣿⠀⠀⢸⣿⣿⣿⣿⣿⣷⡀⣀⡼⢻⠈⠉⢁⣤⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⢸⠀⠀⠀⠉⣿⣿⡍⠀⠀⠀⠀⠀⠈⢻⣿⣿⣿⡿⢋
                ⣿⡏⠀⠀⠀⠀⠀⠀⣤⠖⠋⠁⢸⡀⠿⠏⣿⠄⠀⠀⣿⣿⣿⣿⣿⣿⣿⡄⠀⠈⣷⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⡇⢸⠀⠀⣠⣾⣿⣿⣿⡄⠀⠀⠀⢀⣀⣤⣿⣋⣥⣾⣿
                ⣿⠆⠀⠀⠀⠀⠀⠈⠁⠀⠀⠀⢀⣹⡶⠖⠋⠘⣆⠀⠹⣿⣿⣿⣿⣿⣿⣿⣶⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⠁⣧⢸⠀⠀⣿⣿⣿⣿⣿⣷⣴⣶⣿⣿⣿⣿⣿⣿⣿⣿⣿
                ⠁⠀⠐⠲⠶⠒⠚⣷⣶⣶⢶⡞⠋⢹⡄⠀⠀⠀⠸⣷⡀⠹⣿⡿⠟⢋⣵⠋⠹⡝⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠟⠀⠀⣿⢸⠀⠀⠹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
                ⠀⠀⠀⠀⠀⢀⣼⣿⣿⡟⣼⠁⢀⢈⣇⠀⠀⠀⠀⢹⡿⠓⣿⡶⣺⡯⠛⠖⢶⣻⡦⣝⢿⣿⣿⣿⣿⣿⣿⣿⠻⠦⢤⣀⡿⢀⠀⠀⠀⠘⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
                ⣀⠀⠀⠀⢀⣾⢫⣿⣿⣷⠟⢀⣭⣿⣿⣠⡀⠀⠀⠀⢿⡘⠧⣭⣭⠤⣴⡆⢠⡹⠷⠎⢷⠙⢿⣿⣿⣿⠟⠁⠤⣶⣶⣿⣇⣼⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠛
                ⣒⣙⣛⣓⣿⣿⣿⣿⣿⣯⣾⢟⣻⣿⣿⣯⡳⣄⠀⠀⠈⢧⡀⠀⢸⠀⣿⡇⢸⠏⠛⠓⠚⠃⠀⠻⠿⠁⠀⣴⡿⣿⠃⠀⣿⢻⠀⠀⠀⠀⣸⡿⢟⣿⡿⢿⣿⣿⣿⡿⠿⠛⠁⠀⠀
                ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣯⡿⢿⢿⣟⣿⠈⠳⣝⡷⢦⣤⣌⣷⡀⠸⡆⠿⣡⠞⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡼⣱⠃⠀⢰⠏⣼⠀⠀⠀⢠⣯⣧⣸⡌⢿⣄⡙⢿⣿⣷⠀⠀⠀⠀⠀
                ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣏⣽⡟⠀⠀⠈⠙⠃⣠⣤⣭⣭⡀⣻⠞⠁⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⣻⠃⠀⠰⠋⠀⡇⠀⠀⢠⣾⣿⣿⣿⣿⣷⣾⣿⣦⣉⠛⠷⣄⡀⠀⠀
                ⣿⣿⣿⣟⠛⣻⣿⣿⣿⣿⣧⣿⣿⣿⡇⠀⠀⠀⠀⠸⣯⣄⣀⣨⣽⣿⠛⠛⠛⢿⣧⠀⠀⠀⠀⠀⢀⡼⣿⠃⠀⠀⠀⠀⣠⡇⢀⡴⢻⣿⣿⣿⣧⣥⣿⣿⣿⣿⣿⣿⣦⣬⣟⣂⣀                                                              #
                """;
        String hEE= """
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡜⢠⢾⢿⣸⠃⡆⢸⠀⠀⠀⡁⠀⠀⠀⠀⠀⢀⢦⢸⡑⢮⣑⢺⣽⣮⢍⣛⣧⢯⡹⣍⢯⣙⢏⠿⣎⡭⢻⣌⣷⢩⠯⡽⣙⡻⢦⢹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣾⣿⣿⣿⢻⣿⢧⣈⣻⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡸⢠⡟⡿⡆⡇⠸⠀⠸⠀⠀⠀⡁⠀⠀⣠⠆⡆⡜⣸⢼⢩⠲⡜⢢⣷⢿⣮⡱⢎⡷⣧⡙⢮⠜⣮⡙⠽⣾⡡⢿⡜⣏⠳⣍⠣⣝⢫⢧⢻⣿⣮⠱⡩⢿⣏⣿⣿⣿⡿⢿⣿⣿⢿⣿⢿⣿⣿⠚⠛⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣰⠃⡾⢹⢇⣹⠀⢂⡄⣘⠀⠀⠀⡅⢀⢠⢃⠞⣸⠰⣹⡼⣧⢛⡜⢣⢽⣾⣿⣧⣛⠼⣹⢮⡙⡞⡴⣩⠳⣍⢿⣚⣷⢭⡛⣬⠳⣌⢻⡞⣯⣿⣿⡱⣙⢬⣻⣿⢻⣿⣿⣷⣮⢽⣻⢿⡜⣿⣿⣧⠀⠘⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⡇⣸⠣⣸⢸⡏⠀⡞⠀⣇⠀⢀⡞⡇⡜⢢⢍⠺⣐⠳⡘⣗⣿⡇⢎⡱⢊⣿⣿⣿⣿⣜⡱⢎⢿⣜⡱⢣⡛⡜⣎⢿⣽⣎⠷⣡⠟⣬⢣⢿⣻⢿⣿⣷⡩⣶⣿⣿⣧⢻⣿⣿⣿⣿⣿⣞⣿⣿⣯⢿⣇⠀⣘⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⢡⡏⢰⡟⢸⡇⠀⠃⡄⣷⠀⡏⡔⣯⠘⡥⢎⢣⢃⠯⡔⣻⢼⢿⢢⡱⣉⢾⣳⠘⢿⣿⡷⣍⠞⡼⢷⡫⣜⠵⣊⡎⢿⣿⡳⢥⡛⡴⣋⠾⣟⡧⢿⣽⢷⣿⣿⡞⣷⣫⢿⣿⣿⣿⣟⢿⣿⣷⣯⣿⣿⡄⢋⣸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡏⣼⠁⢸⡇⢸⡇⡀⢸⠡⢼⡒⡱⠌⣷⣉⠖⡭⢲⡉⠖⣱⢸⡞⣿⣇⠲⢥⣊⢿⡆⡈⠙⢿⣝⡻⣜⡣⢟⣮⡝⣲⣙⢎⢿⣏⢧⡝⡲⣍⠞⣧⣿⣩⣿⣿⠿⣿⣿⣿⢧⣯⣿⣿⣞⣿⣿⣿⣿⣿⡿⣿⣷⣤⣀⣻⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣇⡏⠀⢸⡇⢸⡇⡜⢢⡙⢼⣣⠱⡉⢾⡤⢋⡔⢣⠜⡱⢌⡒⣻⢻⣷⡝⣢⠞⡸⣷⢡⠀⠀⠉⠳⣍⡟⣾⣔⡻⢧⣚⠼⣪⣿⡗⢮⠵⣎⢏⢿⣹⡦⢽⣻⣯⡽⣷⡿⣿⡿⡿⢿⣯⣞⡿⣝⣿⣞⣿⣿⣿⡌⠉⠻⣧⡀⠀⠀⠀⠀⠀⠀⠀⣀⣀⣀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡠⠊⠉⠉⠑⠀⠀⠤⢄⡀⠀⠀⢸⣻⠁⢀⣼⡇⢼⡗⣌⠣⡜⣸⣧⢣⠑⡘⣷⡡⢚⠤⣋⠴⢣⠱⣚⣿⣟⣷⡡⢞⣥⣿⣶⡤⠖⠒⠒⠚⠓⠧⣫⢟⣷⣽⣣⢇⣿⣿⡩⢞⡜⢮⣹⢿⣏⠾⣿⣿⣿⣞⣿⣿⣷⣏⢯⣿⣯⣿⠽⣾⣧⢟⣿⣿⣇⠀⠀⣿⠹⡆⠀⠀⠀⠠⣶⣻⡹⣼⡽⠃⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⡋⠀⠀⠀⢀⠠⠀⠂⢀⢠⠃⠀⠀⣿⣹⢀⡼⡰⣯⢘⣿⠤⡓⢬⠐⣿⡆⡍⢒⣻⣧⢩⠒⡥⢎⡣⡝⡤⢻⡽⣿⣿⠽⣋⠜⣳⡄⠀⠀⠀⠀⣀⣀⣈⠙⠲⢭⡻⢿⣾⣿⣱⢫⡜⢧⢺⡟⣧⡟⣿⣿⠟⠋⠀⣀⣀⡈⠙⢾⣿⣝⢿⣽⡷⣯⣿⣿⣿⠀⠀⣹⠆⢻⡄⠀⠀⠀⠀⠈⠉⠉⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠙⠒⠦⣤⣀⡐⣀⠖⠁⠀⠀⠀⣿⣼⠚⡥⡑⢿⣂⢿⡧⡙⢆⠩⢼⣷⡌⢣⠼⣿⣧⢹⠰⢣⡱⢎⡱⢣⢻⣿⣯⢷⡈⢞⡰⢳⡀⠴⢊⣉⣤⣴⣶⣶⣦⣤⣽⣛⡻⢿⣧⠳⡜⣭⢺⣏⣷⡹⣿⡇⠀⡴⠋⠀⠀⠈⠢⡀⢹⣿⣷⣝⣿⣵⣿⣿⣿⡄⠀⣼⠁⠸⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠁⠀⠀⠀⠀⢰⣿⡏⡱⢢⣹⡞⣳⢺⣷⢉⡜⢌⡚⣿⣯⠒⣌⢻⣯⣧⡙⢦⡑⢎⡱⢍⡖⠦⢿⣧⢻⡔⣌⠣⢳⣴⣿⣿⣿⣿⣿⣿⠿⢿⣿⣿⣿⣿⣿⡱⣉⠦⣹⢶⣏⢷⣿⡇⢰⢥⣀⠀⠀⠀⠀⠱⡈⣿⣞⣿⣿⣛⣿⣿⣿⡇⠀⡾⠀⠀⣿⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣟⡧⣑⣳⠃⠀⢸⡆⣿⠆⡜⢢⠜⡹⡿⣷⣸⣮⢿⣯⣷⡂⣍⠲⡘⢢⠜⢣⡍⣿⣧⠙⢦⠩⣽⣿⡿⢛⣿⣿⣿⣿⣷⣶⣿⡄⠙⣿⣿⠶⢥⠚⣼⢻⣏⢾⡻⡇⡞⠳⣌⠱⣄⠐⠀⠀⡇⢹⣾⢿⣿⣿⣯⣿⣿⡇⢠⠃⠀⠀⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⠟⢳⠀⠀⠀⠀⢸⣿⣷⢠⢿⠀⠀⠈⡧⣹⡗⣌⢣⢎⡱⢻⣿⣯⠰⡩⢧⡙⢿⣤⠣⣙⢢⠙⢦⡘⡜⢯⣷⡀⠛⠭⠖⠋⢸⣿⢿⣋⡿⠯⡟⣹⡇⢠⠋⣿⡘⠦⡙⣼⣿⡹⣾⣇⠃⣧⠤⠼⠃⠈⠢⣌⣠⠇⣼⣿⣿⣻⣿⣿⢿⣿⡷⠎⠀⠀⢰⡿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢿⠀⠈⠇⠀⠀⠀⢸⣽⢻⡌⣿⠀⠀⠀⢳⡔⢿⣌⠲⡌⣵⢏⢻⡽⣷⡠⢍⢳⡌⠻⣷⣈⠦⣙⠢⢜⣰⡭⠟⠙⠀⠀⠀⠀⠀⠻⣮⠉⠂⢀⣀⡿⠗⠙⠀⣽⢌⢣⡑⣾⣷⣽⡿⠁⠀⢿⣀⡤⣖⠒⢄⠀⠀⢠⣿⣾⣿⣿⣿⡿⠀⣿⣿⣄⠀⠀⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⡀⠀⠘⡄⠀⠀⠘⣾⠘⣷⢹⡄⠀⠀⠘⣇⠎⣿⡰⡽⢃⠎⣌⠿⣟⣷⣎⡴⡙⣦⡌⠻⢷⡦⠗⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠛⠋⠉⠀⠀⠀⠀⠀⣿⢈⢦⡵⡿⠑⠋⠀⠀⠀⠈⣏⡧⠀⠙⠘⣀⠤⠞⠷⣿⣿⣿⣿⡇⠠⢿⠘⣟⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢣⠀⠀⠰⡄⠀⠀⣿⡇⢻⣞⣧⠀⠀⠀⢹⡎⡜⢿⡡⣉⠖⣩⣾⣿⣿⣿⢿⣿⣷⡿⠷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢿⡾⠝⠋⠀⠀⠀⠀⠀⣠⠏⢉⠠⢄⡶⠋⠁⠀⠀⢀⣼⣿⢟⡿⠀⠀⡜⠀⠸⡞⣆⠀⠀⠀⠠⠷⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢆⠀⠀⠘⡄⠀⢸⡇⠜⣿⣻⡄⠀⠀⠀⢻⡰⣉⢷⣌⢲⣿⡟⣸⡏⣿⡿⣿⣿⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣧⠴⠃⡠⠊⠀⠀⠀⠀⣰⣿⣿⠏⣼⠃⠀⢠⠇⠀⠀⢻⠟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢆⠀⠀⠘⢆⠀⣷⠐⢿⣿⣷⠀⠀⠀⠈⣿⣔⣊⢻⣿⣿⣴⠞⢻⣟⢻⠜⠀⡹⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠞⠁⠀⠀⠀⢀⣾⣿⣿⠋⢠⠃⠀⠀⠊⠀⠀⣀⠬⠤⠤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢆⠀⠀⠀⠣⡈⣇⠀⢻⣿⡄⠀⠀⠀⢸⣿⣷⣆⣻⣿⠟⠢⢀⠙⢻⠶⠊⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⣿⣿⠋⠀⠀⠀⠀⢠⣾⣿⡿⠁⡰⠁⠀⠀⠀⡠⠔⠉⠀⠀⠀⠀⣸⠆⠀⠀⠀⠀⠀⠀⠀⠀⠀⡀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠣⡀⠀⠀⠑⢜⢧⡀⠻⣿⠀⠀⠀⠀⢿⣺⣻⢿⡀⠀⠀⠈⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣸⣿⣿⠃⠀⠀⠀⠀⢠⣿⣿⠏⠀⠀⠀⠀⢀⠴⠊⠀⠀⠀⠀⢀⡠⠚⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠁⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⡄⠀⠀⠈⠫⣷⣤⡹⣇⠀⠀⠀⠘⣷⢿⣾⡽⢦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⠤⡴⠖⠚⠓⠓⢦⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⣿⣿⠏⠀⠀⠀⠀⢠⣿⡿⠁⠀⠀⠀⢀⠔⠁⠀⠀⠀⠀⢀⠔⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⠤⢄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⢄⠀⠀⠀⠈⠙⢛⡋⠀⠀⠀⠀⠺⣿⣞⣿⣯⣿⣦⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⡭⠒⠉⠂⠉⠀⠁⠈⠘⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣾⣿⣿⡟⠀⠀⠀⠀⢠⠟⣹⣁⠀⠀⢀⠔⠁⠀⠀⠀⠀⢀⡔⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣆⠀⡑⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⠤⠔⠒⠈⢇⠀⠀⠀⠀⠁⠀⠀⠀⠀⠀⠀⠙⣿⣾⣽⣻⣿⠯⠗⠢⠀⠀⠀⠀⠀⠀⢰⣿⡟⠀⠀⠀⠀⠀⠀⠀⠀⢰⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⠏⣸⣿⡿⠀⠀⠀⠀⢀⠏⠀⠋⠉⠓⡴⠃⠀⠀⠀⠀⠀⡠⠋⠀⠀⠀⠀⠀⠀⠀⠀⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢧⡀⢸⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡖⠉⠀⠀⠤⡔⠒⢺⢦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⣿⣿⣻⣽⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⠦⡀⠀⠀⠀⠀⠀⠀⣠⠏⠀⠀⠀⠀⠀⠀⠀⠀⢠⠞⡡⠎⣼⣿⠁⠀⠀⠀⠀⡎⠀⠀⠀⣠⠊⠀⠀⠀⠀⠀⣠⠊⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢳⡀⠀⠀⢀⠎⠙⠛⠒⠚⠓⠒⠉⠉⠑⢤⡀⠀⠀⠀⠈⣿⣿⣿⡾⣿⣷⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠑⠢⠤⠤⠒⠋⠀⠀⠀⠀⠀⠀⠀⠀⢠⡼⢋⠴⡁⠎⢼⠇⠀⠀⠀⠐⣸⠁⠀⢀⠔⠁⠀⠀⠀⠀⢠⠞⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣦⣠⠋⠘⢄⠀⠀⠳⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠢⣀⡀⠀⢹⣻⣿⣽⣻⣿⣿⣦⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⠞⢣⠐⠥⢂⠅⣨⠏⠀⠀⠀⠀⠀⢿⠀⢠⠊⠀⠀⠀⠀⢀⣴⠁⠀⠀⠀⠀⣀⣤⡶⣞⡿⣏⣯⣝⡻⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠙⢦⡀⠈⠳⡀⠀⠀⠑⠒⠰⢢⢶⡄⠀⠀⠀⠀⠀⠀⠉⠛⠉⣷⢺⡽⣿⣧⣽⣿⣿⣷⣦⣤⣄⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⢚⠡⡘⠤⢉⢂⣡⡸⠃⠀⠀⠀⠀⠀⠐⠚⡷⠃⠀⠀⠀⠀⢠⠞⠀⠀⢀⣠⣶⠿⣏⣷⡻⣵⣻⢞⡵⢮⡱⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠢⡀⠈⠢⢤⣤⡴⣴⣯⣍⡿⡦⣀⠀⠀⠀⠀⠀⠀⠀⢹⣻⢼⡳⣿⢯⣟⣯⢟⣿⡛⠛⠉⣡⠮⣍⣹⠓⢶⡦⠤⣀⣀⡤⠖⠋⠰⠘⠒⠋⠉⠉⠉⡰⠁⠀⠀⠀⠀⠀⠀⠀⠈⢁⡤⠒⠲⡄⣰⠃⠀⡠⠒⣽⢯⣏⡿⡽⣶⢻⣳⡝⣮⡝⣮⢵⡿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⢀⣀⣀⠀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣀⣀⠈⠱⡤⠤⠤⠴⠒⠲⠚⢹⡑⡌⠳⡆⠀⠀⠀⠀⠀⢸⣽⢣⠿⣽⡿⡾⣽⢺⡜⣇⠀⢷⢧⡐⢠⠃⡍⡐⢧⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡠⣄⡜⠁⠀⠀⠀⠀⠀⠀⠀⡠⠖⠁⠀⠀⣴⠘⢧⠔⠉⠀⣸⣟⢮⣗⣻⡽⣞⢯⣓⢾⡱⣞⣥⡿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⢮⣄⠀⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⠤⠖⠚⠉⠙⠇⠀⠈⠋⠹⣇⠀⠀⠀⠀⠀⠀⠈⡇⠀⠀⠙⠀⠀⠀⠀⠀⣿⡜⣫⣟⣵⠟⠛⢳⠿⠾⣟⡷⡌⠳⣻⡏⠲⣤⠑⢺⡄⠀⠀⢀⡤⢦⠀⣠⠞⢁⣴⡫⠔⠒⠒⠢⢄⠀⢀⡤⠊⠀⠀⠀⢀⣴⠃⠀⠈⢧⡀⠀⢻⣞⢯⡞⣧⢻⡜⣮⢝⣮⢳⢧⣾⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠈⠙⠆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣸⠥⠒⠒⠋⣉⣽⡆⠀⠀⠀⠀⠹⣆⠀⠀⠀⠀⠀⠀⠹⡄⠀⠀⠀⠀⠀⠀⣸⡧⢫⡟⠉⠓⡦⢞⣫⡿⠟⠛⠓⠚⢒⡿⣅⠀⠈⢳⡈⡇⢀⠜⠁⠀⢸⡞⡡⠊⠁⠀⠀⠀⠀⠀⠀⠀⠉⠚⠤⣀⠀⠀⢀⡼⣏⢀⡤⠶⢌⣷⡀⢸⣿⢫⡞⣵⢣⣟⣼⠯⣞⡭⣾⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⠀⠠⠄⠀⠀⠠⠀⣤⠀⢀⣸⠤⠔⠂⠙⠋⠀⣇⠀⠀⠀⢨⢐⡽⠙⠢⢄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠂⣿⣷⠛⠶⣦⠟⣱⡿⠉⠀⠀⠀⣀⣀⣀⣬⠾⢆⠀⠀⢳⡿⠃⠀⠀⠀⢀⡼⠀⠀⠀⠀⠀⢀⣀⠀⠀⠀⠀⣀⠀⠀⠉⣷⣩⠖⠋⠉⠀⠀⠈⢧⣿⢻⡜⣧⣻⠼⣛⠭⣎⢻⡜⣼⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠠⠀⠀⠀⠀⢀⠤⠒⠉⠀⠀⠀⠀⠀⠀⠀⠀⢓⠊⠁⠀⠀⠀⠀⠀⠠⠁⡏⠀⠀⢀⣊⡞⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣸⣿⣏⣀⣴⢣⣾⠏⠀⠡⢉⡬⠛⠉⠀⠀⠈⠑⠺⣧⣤⠈⠁⠀⠀⢀⠴⢊⡇⠀⠀⠀⠀⠀⣀⠼⡏⠓⣲⠲⠤⣵⣲⢾⠋⡀⠀⠀⠀⣠⠆⠀⠈⢿⡷⡛⢩⠐⡍⢎⡳⡜⣣⢞⣹⣇⣀⠤⠾⢛⣿⡿⣟⣷⢲⣄⡀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡠⠊⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀⠀⠀⠀⠀⠀⠀⢀⠂⡇⠀⢐⣿⡏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⣾⣿⠃⠀⣹⢡⢯⠇⠀⣬⠖⠉⠀⠀⠀⠀⠀⢀⣠⣴⣿⣿⣿⠶⠀⣾⣿⠶⣾⠀⠀⠀⠀⠐⠞⠁⡐⢻⡀⠳⠖⠚⢩⡴⠃⡔⠸⣰⠖⠛⠳⣄⠠⠀⢨⣏⠹⣉⠟⠺⠶⣥⢳⣑⢮⡜⣻⣤⢦⡴⣾⢯⣗⢯⡞⣧⣛⢿⡀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⢠⠎⠀⠀⠀⠀⠀⠀⠀⠀⢀⡠⠔⣲⠀⠀⢸⡄⠀⠀⠀⠀⠀⡤⠀⢸⣇⡴⠋⠸⢀⣀⠠⠤⠤⠢⢤⡤⡀⣀⣤⣶⠏⠉⣩⣿⣿⣟⢻⢡⢏⣎⠴⠋⠀⠀⠀⠀⣀⣤⣴⣾⣿⣿⣿⣿⣿⣿⡆⠀⠰⡀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠁⢼⠆⠀⠀⠀⢯⣠⣼⠥⠞⢁⠀⠀⠀⠉⠃⣴⢋⠆⣓⠰⢊⡑⠦⡈⠝⣯⣾⡽⣽⢿⣶⣽⡿⡹⢎⡿⡜⢧⡹⡚⡇⠀
                ⠀⠀⠀⠀⠀⠀⠀⠰⡅⠀⠀⠀⠀⢀⡀⠤⠔⠂⠁⣠⠞⠉⡇⠀⠈⡷⠀⠀⠀⠀⡼⠁⣰⡟⠋⠀⣴⠟⠁⠀⠀⠀⢀⡀⡼⣲⠿⣛⠵⠎⢀⣴⡿⡟⠁⣈⡏⡼⡛⠁⠀⢀⣠⣴⣶⡿⠟⣫⣽⣿⣿⡏⠉⣿⡟⣿⣷⠀⠀⠉⠙⡇⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⠟⡤⢋⠜⡠⢍⢢⠑⢢⠑⡌⡐⢻⡴⣩⢖⡎⢶⣱⣙⢧⡚⣭⠲⣡⢃⢷⡠
                ⠀⠀⠀⠀⠀⣀⠤⠤⠽⢭⠉⠉⠉⠀⠀⠀⢀⣴⠞⠋⠀⠀⣇⠀⠀⢿⠀⡀⣀⣨⣵⠞⠁⡀⠠⢼⣧⡦⠶⠖⠚⣻⠉⣼⢳⢋⡞⣱⣮⣶⡿⠋⢀⣧⡵⢻⠀⣧⣧⣶⣿⠿⠛⠋⠀⣰⣾⣿⢿⣿⡟⠀⢰⣿⠇⢻⣿⡄⠀⠀⠀⢣⠀⠀⠀⠀⠀⠀⠀⠀⠈⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⠞⣡⠚⡄⢃⠎⡑⠈⠆⡉⢆⠱⢠⠑⠄⢻⡵⢎⣞⢣⡗⡺⣖⡹⢆⡻⢄⠣⢾⡱
                ⣀⠀⠠⢊⠉⠀⣀⣠⠖⢹⠆⠀⠀⢀⠠⢔⠛⠁⠀⠀⠀⠀⢻⠀⡀⢾⡈⣭⡵⢾⢥⠀⠐⠀⠀⢸⣆⣠⢴⣴⣫⣇⣼⣏⣴⡿⠿⠟⠛⠉⠀⠀⡏⠉⠉⢹⠀⠻⣿⡍⠤⠒⢀⣠⣾⣿⠏⢡⣾⠟⠀⢀⣿⡿⠀⢸⣿⣧⠀⠀⠀⢸⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣴⠞⡡⢩⠄⠣⠘⠠⠈⠄⠁⠂⠈⠄⠊⠤⢉⡐⠂⣿⡹⣬⠳⡞⡵⣭⣹⠹⡜⡭⢊⡽⠀
                """;
        System.out.println(hEE);
        String deespedida = """
                  #####    ####    ##  ##    ####    #####     ####    ######    ####
                 ##           ##   ##  ##   ##  ##   ##  ##       ##    ##  ##      ##
                  #####    #####   ##  ##   ##  ##   ##  ##    #####    ##       #####
                      ##  ##  ##    #####   ##  ##   ##  ##   ##  ##    ##      ##  ##
                 ######    #####       ##    ####    ##  ##    #####   ####      #####
                #####
                """;
        System.out.println(deespedida);
    }
}