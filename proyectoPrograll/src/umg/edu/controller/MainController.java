package umg.edu.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import umg.edu.model.Cuestionario;
import umg.edu.model.Respuestas;
import umg.edu.utils.Basedatos;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class MainController {

    @FXML
    RadioButton  Rcontestada1;
    @FXML
    RadioButton  Rcontestada2;
    @FXML
    MenuButton MenuButon;
    @FXML
    MenuItem menuitem1;
    @FXML
    MenuItem menuitem2;
    @FXML
    MenuItem menuitem3;
    @FXML
    MenuItem menuitem4;
    @FXML
    Button btSiguiente;

    @FXML
     MenuItem menuitem5;
    @FXML
    ComboBox SelecionarCues;
    @FXML
    TextField MostrarR2;
    @FXML
    TextField MostraR1;
    @FXML
    TextArea Pregunta;
    @FXML
    Button btingresar;
    @FXML
    RadioButton  opc1;
    @FXML
    RadioButton  opc2;
    @FXML
    Button bteditar;
    @FXML
    Button btduplicar;
    @FXML
    Button btrealizar;
    @FXML
    Button bttop3;

    @FXML
    Pane paneltop3;
    @FXML
    Pane panelduplicar;
    @FXML
    Pane panelrealizar;
    @FXML
    Pane Paneleditar;
    @FXML
    Pane panelIngresar;

    @FXML
    TextField txtIngresarpregunta;
    @FXML
    TextField NombreCuest;
    @FXML
    TextField txtingresarrespuesta;
    @FXML
    TextField txtingresarrespuesta2;
    @FXML
    TableView tablaCuestio;

    @FXML
    TableColumn tcIdcUESTIONARIO;
    @FXML
    TableColumn tcNpregunta;
    @FXML
    TableColumn tcPregunta;
    @FXML
    TableColumn tcRespuesta;
    @FXML
    TableColumn tcRespuesta2;
    @FXML
    TableColumn tcEstado;



    public MainController() {
    }

    public void initialize(){
        tcNpregunta.setCellValueFactory( new PropertyValueFactory<Cuestionario, Integer>("idpreguntas"));
        tcIdcUESTIONARIO.setCellValueFactory( new PropertyValueFactory<Cuestionario, String>("idimprimir"));
        tcPregunta.setCellValueFactory( new PropertyValueFactory<Cuestionario, String>("Pregunta"));
       tcRespuesta.setCellValueFactory( new PropertyValueFactory<Cuestionario, String>("Respuesta1"));
        tcRespuesta2.setCellValueFactory( new PropertyValueFactory<Cuestionario, String>("Respuesta2"));
        tcEstado.setCellValueFactory( new PropertyValueFactory<Cuestionario, String>("Estado"));


        /*ObservableList<Cuestionario> data = FXCollections.observableArrayList(Basedatos.listaCustionarios);
      tablaCuestio.setItems( data );*/

    }

    public void onClickMenu(ActionEvent actionEvent){

        if( actionEvent.getSource() == btingresar){
            panelIngresar.toFront();
            opc1.requestFocus();
        }

        if( actionEvent.getSource() == bteditar){
            Paneleditar.toFront();
        }

        if( actionEvent.getSource() == btduplicar){
            panelduplicar.toFront();
        }
        if( actionEvent.getSource() == btrealizar){
            panelrealizar.toFront();
            MostraR1.setEditable(false);
            MostrarR2.setEditable(false);
            Pregunta.setEditable(false);
            ArrayList<String> lista = new ArrayList<>();
            for (Cuestionario C : Basedatos.listaCustionarios){
                lista.add(C.getIdimprimir());
            }
            menuitem1.setText(lista.get(0));
            menuitem2.setText(lista.get(1));
            menuitem3.setText(lista.get(2));
            menuitem4.setText(lista.get(3));
            menuitem5.setText(lista.get(4));


        }
        if( actionEvent.getSource() == bttop3){
            paneltop3.toFront();
        }

    }

    public void onClickAgregarpreGuntayrespuestas(ActionEvent actionEvent){


        if (opc1.isSelected()||opc2.isSelected()) {

            //cancelar cambio de nombre hasta que termine cuestionario
            NombreCuest.setEditable(false);
            // 1. obtener datos ingresados
            String NombreCues = NombreCuest.getText();
            String Pregunta = txtIngresarpregunta.getText();
            String respuesta = txtingresarrespuesta.getText();
            String respuesta2 = txtingresarrespuesta2.getText();
            String estado = "Registrado";

            //  crear la instancia del modelO CUESTIONARIO
            Cuestionario cuestionario = new Cuestionario(NombreCues, Pregunta, respuesta, respuesta2, estado);

            //  guardarlo en el arreglo
            Basedatos.listaCustionarios.add(cuestionario);

            //  limpiar los campos
            txtingresarrespuesta.setText("");
            txtingresarrespuesta2.setText("");
            txtIngresarpregunta.setText("");

            ObservableList<Cuestionario> data = FXCollections.observableArrayList(Basedatos.listaCustionarios);
            tablaCuestio.setItems(data);
            //Guardar Respuesta Correcta
            if (opc1.isSelected()){
                int op1 = 1;
                Respuestas respuestas = new Respuestas(cuestionario.getId(),cuestionario.getIdpreguntas(),op1);
                Basedatos.listaRespuestas.add(respuestas);
            }
            if (opc2.isSelected()){
                int op2 = 2;
                Respuestas respuestas = new Respuestas(cuestionario.getId(),cuestionario.getIdpreguntas(),op2);
                Basedatos.listaRespuestas.add(respuestas);
            }



        }else {
            JOptionPane.showMessageDialog(null, "Seleccione Respuesta correcta" );
        }

    }
    public void onClicterminarCuestionario(ActionEvent actionEvent){
        int c1= Cuestionario.getCorrelativoCues();

            for (Cuestionario C : Basedatos.listaCustionarios) {
                if (c1 == Cuestionario.getCorrelativoCues()) {
                    C.setEstado("Activo");
                }


            }

        NombreCuest.setEditable(true);
        c1= c1+1;
        Cuestionario.setCorrelativoCues(c1);
        // 4. limpiar los campos
        txtingresarrespuesta.setText("");
        txtingresarrespuesta2.setText("");
        txtIngresarpregunta.setText("");
        NombreCuest.setText("");
        Cuestionario.setCorrelativo(1);

    }

    public void onSelececionarCuestionario1(ActionEvent actionEvent){


        for (Cuestionario C : Basedatos.listaCustionarios){


            if (1 == C.getId()) {
                Basedatos.listaC.add(C);
                Pregunta.setText(C.getPregunta());
                MostraR1.setText(C.getRespuesta1());
                MostrarR2.setText(C.getRespuesta2());
                JOptionPane.showMessageDialog(null, C.getPregunta() );
                int i =0;
                i++;

            }

        }


    }
    public void onSelececionarCuestionario2(ActionEvent actionEvent){


        for (Cuestionario C : Basedatos.listaCustionarios){


            if (2 == C.getId()) {
                Basedatos.listaC.add(C);
                Pregunta.setText(C.getPregunta());
                MostraR1.setText(C.getRespuesta1());
                MostrarR2.setText(C.getRespuesta2());
                JOptionPane.showMessageDialog(null, C.getPregunta() );
                int i =0;
                i++;

            }

        }


    }
    public void onSelececionarCuestionario3(ActionEvent actionEvent){


        for (Cuestionario C : Basedatos.listaCustionarios){


            if (3 == C.getId()) {
                Basedatos.listaC.add(C);
                Pregunta.setText(C.getPregunta());
                MostraR1.setText(C.getRespuesta1());
                MostrarR2.setText(C.getRespuesta2());
                JOptionPane.showMessageDialog(null, C.getPregunta() );
                int i =0;
                i++;

            }

        }


    }
    public void onSelececionarCuestionario4(ActionEvent actionEvent){


        for (Cuestionario C : Basedatos.listaCustionarios){


            if (4 == C.getId()) {
                Basedatos.listaC.add(C);
                Pregunta.setText(C.getPregunta());
                MostraR1.setText(C.getRespuesta1());
                MostrarR2.setText(C.getRespuesta2());
                JOptionPane.showMessageDialog(null, C.getPregunta() );
                int i =0;
                i++;

            }

        }


    }
    public void onSelececionarCuestionario5(ActionEvent actionEvent){


        for (Cuestionario C : Basedatos.listaCustionarios){
            if (5 == C.getId()) {
                Basedatos.listaC.add(C);
                Pregunta.setText(C.getPregunta());
                MostraR1.setText(C.getRespuesta1());
                MostrarR2.setText(C.getRespuesta2());
                JOptionPane.showMessageDialog(null, C.getPregunta() );
                int i =0;
                i++;

            }

        }


    }



    public void onSelececionarCuestionarios(ActionEvent actionEvent){
        ArrayList<String> lista = new ArrayList<>();
        int c1= Cuestionario.getCorrelativoCues();
        for (Cuestionario C : Basedatos.listaCustionarios){

            lista.add(C.getIdimprimir());
        }
        menuitem1.setText(lista.get(0));
        menuitem2.setText(lista.get(1));
        menuitem3.setText(lista.get(2));
        menuitem4.setText(lista.get(3));
        menuitem5.setText(lista.get(4));


    }
    public void siguiente(ActionEvent actionEvent){

        for (Respuestas R:Basedatos.listaRespuestas){


        }



    }
    public void unaRespuesta() {

        if (Rcontestada1.isSelected()){
            Rcontestada2.setSelected(false);

        }

    }

    public void unRespuesta() {

        if (Rcontestada2.isSelected()){
            Rcontestada1.setSelected(false);

        }

    }




    public void unBotonn() {

        if (opc2.isSelected()){
            opc1.setSelected(false);

        }

    }
   public void dosBotonn() {

        if (opc1.isSelected()){
            opc2.setSelected(false);

        }

    }


}
