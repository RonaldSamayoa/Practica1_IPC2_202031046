package com.mycompany.eventoshyrule.parser;
import com.mycompany.eventoshyrule.controlador.ActividadControlador;
import com.mycompany.eventoshyrule.controlador.AsistenciaControlador;
import com.mycompany.eventoshyrule.controlador.CertificadoControlador;
import com.mycompany.eventoshyrule.controlador.EventoControlador;
import com.mycompany.eventoshyrule.controlador.InscripcionControlador;
import com.mycompany.eventoshyrule.controlador.PagoControlador;
import com.mycompany.eventoshyrule.controlador.ParticipanteControlador;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author ronald
 */
public class Parser {
    private ParticipanteControlador participanteCtrl;
    private EventoControlador eventoCtrl;
    private InscripcionControlador inscripcionCtrl;
    private PagoControlador pagoCtrl;
    private ActividadControlador actividadCtrl;
    private AsistenciaControlador asistenciaCtrl;
    private CertificadoControlador certificadoCtrl;
    
    public Parser(){
        this.participanteCtrl = new ParticipanteControlador();
        this.eventoCtrl = new EventoControlador();
        this.inscripcionCtrl =new InscripcionControlador();
        this.pagoCtrl = new PagoControlador();
        this.actividadCtrl = new ActividadControlador();
        this.asistenciaCtrl = new AsistenciaControlador();
        this.certificadoCtrl = new CertificadoControlador();
    }
    
    // metodo para leer el archivo y procesar linea por linea
    public void parseFile(String rutaArchivo){
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))){
            String linea;
            boolean enBloqueComentario = false;
            
            while ((linea = br.readLine())!= null){
                linea = linea.trim();
                
                //inicia un comentario de bloque
                if (linea.startsWith("/*")){
                    enBloqueComentario = true;
                }
                
                // si ya estamos dentro del comentario de bloque
                if (enBloqueComentario) {
                    if (linea.endsWith("*/")) {
                        enBloqueComentario = false; //cierra el bloque
                    }
                    continue; // ignorar linea
                }
                
                //comentarios de linea (-- o //)
                if (linea.isEmpty() || linea.startsWith("--") || linea.startsWith("//")) {
                    continue;
                }
                procesarLinea(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    //procesar sentencia
    private void procesarLinea(String linea){
        try {
            int inicio = linea.indexOf("(");
            int fin = linea.lastIndexOf(")");
            if (inicio == -1 || fin == -1) {
                System.out.println("Formato invalido: " + linea);
                return;
            }
            
            String comando = linea.substring(0, inicio).trim();
            String argumentos = linea.substring(inicio + 1, fin).trim();
            String[] args = argumentos.split("\",\"");
            
            //limpiar comillas
            for (int i = 0; i < args.length; i++) {
                args[i] = args[i].replace("\"", "").trim();
            }
            ejecutarComando(comando, args);
        } catch (Exception e) {
            System.out.println("Error procesando linea: " + linea);
            System.out.println("Detalle: " +e.getMessage());
        }
    }
    
    //ejecutar la sentencia segun el comando ingresado
    private void ejecutarComando(String comando, String[] args) throws ParseException{
        switch (comando.toUpperCase()) {
            case "PARTICIPANTE":
                if (args.length == 4) {
                    String institucion = parseNullable(args[3]);
                    participanteCtrl.crearParticipante(args[0], args[1], args[2], institucion);
                } else{
                    System.out.println("Error: Participante requiere de 4 argumentos");
                }
                break;
                
            case "EVENTO":
                if (args.length == 7) {
                    try {
                        int cupo = Integer.parseInt(args[5]);
                        if (cupo <= 0) {
                            System.out.println("Error: el cupo maximo debe ser mayor a cero");
                            break;
                        }
                        BigDecimal costo = new BigDecimal(args[6]);
                        if (costo.compareTo(BigDecimal.ZERO)<0) {
                            System.out.println("Error: el costo no puede ser menor a cero");
                            break;
                        }
                        String ubicacion = parseNullable(args[4]);
                        Date fecha = parseFecha(args[1]); // fecha por dd//mm//yyyy
                        eventoCtrl.crearEvento(args[0], fecha, args[2], args[3], ubicacion, cupo, costo);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: cupo y costo deben ser numericos");
                    } catch (ParseException e){
                        System.out.println("Error: la fecha debe ser dia/mes/anio");
                    }
                } else{
                    System.out.println("Error: EVENTO requiere de 7 argumentos");
                }
                break;
                
            case "INSCRIPCION":
                if (args.length == 3) {
                    inscripcionCtrl.crearInscripcion(args[0], args[1], args[2]);
                } else{
                    System.out.println("Error: INSCRIPCION requiere de 3 argumentos");
                }
                break;
                
            case "PAGO":
                if (args.length == 4) {
                    try {
                        BigDecimal monto = new BigDecimal(args[3]);
                        if (monto.compareTo(BigDecimal.ZERO) < 0) {
                            System.out.println("Error: el monto no puede ser negativo");
                            break;
                        }
                        pagoCtrl.registrarPago(args[0], args[1], args[2], monto);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: el monto debe ser numerico");
                    }
                } else{
                    System.out.println("Error: PAGO requiere 4 argumentos");
                }
                break;
                
            case "VALIDAR_INSCRIPCION":
                if ( args.length == 2) {
                    inscripcionCtrl.validarInscripcion(args[0], args[1]);
                } else{
                    System.out.println("Error: VALIDAR_INSCRIPCION requiere de 2 argumentos");
                } break;
                
            case "ACTIVIDAD":
                if (args.length == 8) {
                    try {
                        int cupoAct = Integer.parseInt(args[7]);
                        if (cupoAct <= 0) {
                            System.out.println("Error: el cupo maximo debe ser mayor a cero");
                            break;
                        }
                        //horas a HH:mm
                        Time inicio = parseHora(args[5]);
                        Time fin = parseHora(args[6]);
                        if (!fin.after(inicio)) {
                            System.out.println("Error: la hora del fin debe ser posterior a la hora de inicio");
                            break;
                        }
                        actividadCtrl.registrarActividad(args[0], args[1], args[2], args[3], args[4], inicio, fin, cupoAct);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: el cupo debe ser numerico");
                    } catch(ParseException e){
                        System.out.println("Error: las horas deben ir en formato HH:mm (hora:minutos)");
                    }
                } else{
                    System.out.println("Error: ACTIVIDAD requiere de 8 argumentos");
                }
                break;
                
            case "ASISTENCIA":
                if (args.length == 2) {
                    asistenciaCtrl.registrarAsistencia(args[0], args[1]);
                } else {
                    System.out.println("Error: ASISTENCIA requiere de 2 argumentos");
                } break;
            
            case "CERTIFICADO":
                if (args.length == 2) {
                    certificadoCtrl.registrarCertificado(args[0], args[1]);
                } else{
                    System.out.println("Error: CERTIFICADO requiere de 2 argumentos");
                } break;
                
            default:
                System.out.println("Comando desconocido: " + comando);
        }
    }
    
    //manejo de atributos opcionales (null = "")
    private String parseNullable(String valor){
        return (valor == null || valor.isEmpty()) ? null : valor;
    }
    
    //convertir fecja dd/mm/yyyy a java.sql.Date
    private Date parseFecha(String valor) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        java.util.Date fecha = sdf.parse(valor);
        return new Date(fecha.getTime());
    }
    
    // convertir la hora HH:mm a java.sql.Time
    private Time parseHora(String valor) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        sdf.setLenient(false);
        java.util.Date hora = sdf.parse(valor);
        return new Time(hora.getTime());
    }
}
