package umg.edu.model;

public class Respuestas {
    int idCuestionario;
    int idPregunta;
    int RespuestaCorrecta;

    public Respuestas(int idCuestionario, int idPregunta, int respuestaCorrecta) {
        this.idCuestionario = idCuestionario;
        this.idPregunta = idPregunta;
        RespuestaCorrecta = respuestaCorrecta;
    }

    public int getRespuestaCorrecta() {
        return RespuestaCorrecta;
    }

    public void setRespuestaCorrecta(int respuestaCorrecta) {
        RespuestaCorrecta = respuestaCorrecta;
    }

    public int getIdCuestionario() {
        return idCuestionario;
    }

    public void setIdCuestionario(int idCuestionario) {
        this.idCuestionario = idCuestionario;
    }

    public int getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }
}
