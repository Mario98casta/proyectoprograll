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
import umg.edu.model.TOP3;
import umg.edu.utils.Basedatos;

import javax.swing.*;
import java.util.ArrayList;

public class MainController {

    String Apro ;
    ArrayList<TOP3> top31 = new ArrayList<TOP3>();
    ArrayList<Cuestionario> listaC2 = new ArrayList<Cuestionario>();
    ArrayList<Respuestas> listaR1 = new ArrayList<Respuestas>();
    int menu1= 1;
    int mb = 0;

    int in =0;
    int punteo;

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
    Button btnAgregar;

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
    Button btterminar;

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
            btterminar.setDisable(true);
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
            int i = Cuestionario.getCorrelativoCues();
            if (i >= 5){
                menuitem5.setText(String.valueOf(5));
                i--;
            }if (i == 4){
                menuitem4.setText(String.valueOf(i));
                i--;
            }if (i == 3){
                menuitem3.setText(String.valueOf(i));
                i--;
            }if (i == 2){
                menuitem2.setText(String.valueOf(i));
                i--;
            }if (i == 1){
                menuitem1.setText(String.valueOf(i));
            }

        }
        if( actionEvent.getSource() == bttop3){
            paneltop3.toFront();
        }

    }

    public void onClickAgregarpreGuntayrespuestas(ActionEvent actionEvent){


        if (opc1.isSelected()||opc2.isSelected()) {
            if (Cuestionario.getCorrelativo()==5){
                btnAgregar.setDisable(true);
                btterminar.setDisable(false);
            }
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
            //datos para la tabla
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
                //JOptionPane.showMessageDialog(null, "Seleccione Respuesta correcta"+respuestas.toString() );
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
        btnAgregar.setDisable(false);
        btterminar.setDisable(true);

    }

    public void onSelececionarCuestionario1(ActionEvent actionEvent){
        punteo=0;
        //al momento de seleccionar cuestionario el botton siguiente se activa
        btSiguiente.setDisable(false);
        //depende de accion tomada la variable mb toma un dato
         mb = 0;
        if (actionEvent.getSource()==menuitem1){
            mb= 1;
        }if (actionEvent.getSource()==menuitem2){
            mb= 2;
        }if (actionEvent.getSource()==menuitem3){
            mb= 3;
        }if (actionEvent.getSource()==menuitem4){
            mb= 4;
        }if (actionEvent.getSource()==menuitem5){
            mb= 5;
        }
        // ArrayList<Cuestionario> listaC2 = new ArrayList<Cuestionario>();
        //recorremos listaCustionario para tomar los datos validados e ingresarlos a un nuevo array list
        for (Cuestionario C : Basedatos.listaCustionarios){
            if (mb == C.getId()) {
                listaC2.add(C);
                Apro=C.getEstado();
            }

        }//fin de for
        //recorremos listaRespuestas para tomar los datos validados e ingresarlos a un nuevo array list
        for (Respuestas R : Basedatos.listaRespuestas){
            if (mb == R.getIdCuestionario()) {
                listaR1.add(R);
            }
        }
        int i =0;
        in=0;
        //ingresamos ala pantalla el primer dato obtenido en la pocicion 0

        Cuestionario C2 = listaC2.get(i);
        Pregunta.setText(C2.getPregunta());
        MostraR1.setText(C2.getRespuesta1());
        MostrarR2.setText(C2.getRespuesta2());



    }

  /* public void onSelececionarCuestionarios(ActionEvent actionEvent){
        ArrayList<String> lista = new ArrayList<>();
            for (Cuestionario C : Basedatos.listaCustionarios){
                lista.add(C.getIdimprimir());
            }
            int i = Cuestionario.getCorrelativoCues();
            if (i == 5){
                menuitem5.setText(String.valueOf(i));
                i--;
            }if (i == 4){
                menuitem4.setText(String.valueOf(i));
                i--;
            }if (i == 3){
                menuitem3.setText(String.valueOf(i));
                i--;
            }if (i == 2){
                menuitem2.setText(String.valueOf(i));
                i--;
            }if (i == 1){
                menuitem1.setText(String.valueOf(i));
            }


    }*/
  public void top3(ActionEvent actionEvent){
      int v1=0;
      int v2=0;
      int v3=0;

          for (TOP3 t3: Basedatos.top3){
              if (t3.getCalificaion() > v1)
              {
                  v1= t3.getCalificaion();
              }
              if (t3.getCalificaion() < v1)
              {
                  v2=t3.getCalificaion();
              }
              if (t3.getCalificaion()<v1)
              {
                  v3=t3.getCalificaion();
              }
          }


      int contador=0;
      for (TOP3 t4: Basedatos.top3){
          if (contador==0) {
              if (t4.getCalificaion() == v1) {
                  top31.add(t4);
                  contador++;
              }
          }
          if (contador==1) {
              if (t4.getCalificaion() == v2) {
                  top31.add(t4);
                  contador++;
              }
          }
          if (contador==2) {
              if (t4.getCalificaion() == v3) {
                  top31.add(t4);
                  contador++;
              }
          }

      }

      JOptionPane.showMessageDialog(null, top31.toString());
  }


    public void siguiente(ActionEvent actionEvent){
        //colocamos button sin acion hasta que se completen las 5 interaciones
        MenuButon.setDisable(true);
        //validamos que se selecione alguna respuesta
        if (Apro=="Activo") {
             if (in==0){
                JOptionPane.showMessageDialog(null, "Cuestionario Activo ");
             }
            if (Rcontestada1.isSelected() || Rcontestada2.isSelected()) {
                    if (in == 0) {
                        Respuestas RR = listaR1.get(in);
                        if (Rcontestada1.isSelected()) {
                            int RC1 = 1;
                            if (RC1 == RR.getRespuestaCorrecta()) {
                                JOptionPane.showMessageDialog(null, "RESPUESTA 1PUNTEO+2");
                                punteo = punteo + 2;
                            } else {
                                JOptionPane.showMessageDialog(null, "RESPUESTA incorrecta");
                            }
                        }//fin de if rcntestada1
                        if (Rcontestada2.isSelected()) {
                            int RC2 = 2;
                            if (RC2 == RR.getRespuestaCorrecta()) {
                                JOptionPane.showMessageDialog(null, "RESPUESTA 2 PUNTEO+2");
                                punteo = punteo + 2;
                            } else {
                                JOptionPane.showMessageDialog(null, "RESPUESTA incorrecta");
                            }
                        }//fin de if rcntestada2
                        in++;
                    }//FIN DE IF DE I=0
                    //al llegar al 5 terminamos cuestionario de lo contrario seguimos en interaccion
                    if (menu1 == 5) {
                        //activamos menubutton
                        MenuButon.setDisable(false);
                        //desactivamos button siguiente
                        btSiguiente.setDisable(true);
                        // agregamos las respuestas de la lista para validar si son correctas
                        Respuestas RR = listaR1.get(menu1 - 1);
                        if (menu1 > 1) {
                            if (Rcontestada1.isSelected()) {
                                int RC1 = 1;
                                if (RC1 == RR.getRespuestaCorrecta()) {
                                    JOptionPane.showMessageDialog(null, "RESPUESTA 1PUNTEO+2");
                                    punteo = punteo + 2;
                                } else {
                                    JOptionPane.showMessageDialog(null, "RESPUESTA incorrecta");
                                }

                            }//fin de if respuesta1

                            if (Rcontestada2.isSelected()) {
                                int RC2 = 2;
                                if (RC2 == RR.getRespuestaCorrecta()) {

                                    JOptionPane.showMessageDialog(null, "RESPUESTA 2 PUNTEO+2");
                                    punteo = punteo + 2;
                                } else {
                                    JOptionPane.showMessageDialog(null, "RESPUESTA incorrecta");
                                }
                            }//fin de if respuesta2
                        }//fin de menu>1
                        JOptionPane.showMessageDialog(null, "su punteo es: " + punteo);
                      //  JOptionPane.showMessageDialog(null, Basedatos.listaRespuestas.toString());

                        //limpiamos datos para poder volver a ejecutar
                        JOptionPane.showMessageDialog(null, "borrando datos");
                        listaC2.clear();
                        listaR1.clear();
                        menu1 = 1;

                    }/*fin de menu==5*/ else {
                        //hacemos el mismo procedimiento
                        JOptionPane.showMessageDialog(null, "ingreso" + menu1);
                        Cuestionario C3 = listaC2.get(menu1);
                        Pregunta.setText(C3.getPregunta());
                        MostraR1.setText(C3.getRespuesta1());
                        MostrarR2.setText(C3.getRespuesta2());
                        Respuestas RR = listaR1.get(menu1 - 1);
                        if (menu1 > 1) {
                            if (Rcontestada1.isSelected()) {
                                int RC1 = 1;
                                if (RC1 == RR.getRespuestaCorrecta()) {
                                    JOptionPane.showMessageDialog(null, "RESPUESTA 1PUNTEO+2");
                                    punteo = punteo + 2;
                                } else {
                                    JOptionPane.showMessageDialog(null, "RESPUESTA incorrecta");
                                }//fin de else
                            }//fin de if respuesta1
                            if (Rcontestada2.isSelected()) {
                                int RC2 = 2;
                                if (RC2 == RR.getRespuestaCorrecta()) {
                                    JOptionPane.showMessageDialog(null, "RESPUESTA 2 PUNTEO+2");
                                    punteo = punteo + 2;
                                } else {
                                    JOptionPane.showMessageDialog(null, "RESPUESTA incorrecta");
                                }//fin de else



                            }//fin de if respuesta2
                        }//fin de menu>1
                        menu1++;
                    }//fin de else
               TOP3 tp3 = new TOP3(mb,punteo);
                   Basedatos.top3.add(tp3);

            }/*fin de validacion de selecion de respuesta*/ else {
                JOptionPane.showMessageDialog(null, "Selecione alguna respuesta");
            }//fin de else
        }
    }//fin de acion siguiente
    public void unaRespuesta() {
        if (Rcontestada1.isSelected()){
            Rcontestada2.setSelected(false);
        }
    }//fin de unaRespuesta
    public void unRespuesta() {
        if (Rcontestada2.isSelected()){
            Rcontestada1.setSelected(false);
        }
    }//fin de unRespuesta
    public void unBotonn() {
        if (opc2.isSelected()){
            opc1.setSelected(false);
        }
    }//fin de unbotonn
   public void dosBotonn() {
        if (opc1.isSelected()){
            opc2.setSelected(false);
        }
    }//fin de dosbotonn
}
