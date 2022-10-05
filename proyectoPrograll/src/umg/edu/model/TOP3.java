package umg.edu.model;

public class TOP3 {
    int IdCUestionario;
    int Calificaion;

    public TOP3(int idCUestionario, int calificaion) {
        IdCUestionario = idCUestionario;
        Calificaion = calificaion;
    }

    public int getIdCUestionario() {
        return IdCUestionario;
    }

    public void setIdCUestionario(int idCUestionario) {
        IdCUestionario = idCUestionario;
    }

    public int getCalificaion() {
        return Calificaion;
    }

    public void setCalificaion(int calificaion) {
        Calificaion = calificaion;
    }

    @Override
    public String toString() {
        return "TOP3{" +
                "IdCUestionario=" + IdCUestionario +
                ", Calificaion=" + Calificaion +
                '}';
    }
}
