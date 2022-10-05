package umg.edu.model;

public class Cuestionario {

    private static int correlativo=1;
    private static int correlativoCues=1;
    private String  idimprimir = "" ;
    private String Nombre;
    private int id;
    private int idpreguntas;
    private String Pregunta;
    private String Respuesta1;

    private String Respuesta2;
     String Estado;

    public Cuestionario() {
    }

    public  Cuestionario(String nombre, String pregunta, String respuesta1, String respuesta2, String estado) {
        String idess = "";
        this.id =correlativoCues;
        if (correlativoCues < 10000) {
            idess = ("00000" + id);
            if (correlativoCues > 10) {
                idess = ("0000" + id);
            }
            this.idimprimir = idess;
            this.Nombre = nombre;
            this.idpreguntas = correlativo;
            this.Pregunta = pregunta;
            this.Respuesta1 = respuesta1;
            this.Respuesta2 = respuesta2;
            correlativo = correlativo + 1;
            this.Estado= estado;

        }


    }
   /* public Cuestionario(String pregunta, String respuesta1, String respuesta2) {

        this.id = Integer.parseInt("12345"+correlativo);
        this.Pregunta = pregunta;
        this.Respuesta1 = respuesta1;
        this.Respuesta2 = respuesta2;

        correlativo = correlativo + 1;

    }*/

    public static int getCorrelativo() {
        return correlativo;
    }

    public static void setCorrelativo(int correlativo) {
        Cuestionario.correlativo = correlativo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPregunta() {
        return Pregunta;
    }

    public void setPregunta(String pregunta) {
        Pregunta = pregunta;
    }

    public String getRespuesta1() {
        return Respuesta1;
    }

    public void setRespuesta1(String repuesta1) {
        Respuesta1 = repuesta1;
    }

    public String getRespuesta2() {
        return Respuesta2;
    }

    public void setRespuesta2(String respuesta2) {
        Respuesta2 = respuesta2;
    }
    public int getIdpreguntas() {
        return idpreguntas;
    }

    public void setIdpreguntas(int idpreguntas) {
        this.idpreguntas = idpreguntas;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public static int getCorrelativoCues() {
        return correlativoCues;
    }

    public static void setCorrelativoCues(int correlativoCues) {
        Cuestionario.correlativoCues =  correlativoCues ;
    }

    public String getIdimprimir() {
        return idimprimir;
    }

    public void setIdimprimir(String idimprimir) {
        this.idimprimir = idimprimir;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }
}
