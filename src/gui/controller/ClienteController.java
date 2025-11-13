package gui.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import models.rutinas.Ejercicio;
import models.rutinas.Rutina;
import models.users.Cliente;


import java.net.URL;
import java.util.ResourceBundle;

public class ClienteController extends BaseUserController implements Initializable {
    @FXML private ListView<Ejercicio> lv_ejercicios;
    @FXML private Label lb_nombre;
    @FXML private Label lb_tipo;
    @FXML private TextArea ta_desc;
    @FXML private Label lb_series;
    @FXML private Label lb_reps;
    @FXML private Label lb_dias_restantes;
    @FXML private Label lb_cuota;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cellFactory();
        listener();
    }

    @Override
    protected void inicializarVista() {
        cargarRutinas();
        cargarEstado();
    }

    private void cargarRutinas() {
        Cliente cliente = (Cliente) this.user_actual;

        lv_ejercicios.getItems().clear(); //limpiar el visor
        if (cliente.getRutina() != null) {
            lv_ejercicios.getItems().setAll(cliente.getRutina().getListaEjercicios());

        }
    }

    private void cargarEstado() {
        Cliente cliente = (Cliente) this.user_actual;

        lb_dias_restantes.setText(cliente.getDias() + " días");
        lb_cuota.setText(cliente.isCuotaAlDia() ? "Al día" : "Vencida");
    }

    private void listener() {
        lv_ejercicios.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, selected) -> {
            if (selected != null) {
                mostrarDatosEjercicio(selected);
            }
        });
    }

    private void cellFactory() {
        lv_ejercicios.setCellFactory(lv -> new ListCell<>() {

            private final Label nombre = new Label();
            private final Label reps = new Label();
            private final HBox layout = new HBox(nombre, reps);

            {
                layout.setSpacing(10);
                HBox.setHgrow(nombre, Priority.ALWAYS);
                nombre.setMaxWidth(Double.MAX_VALUE);
            }

            @Override
            protected void updateItem(Ejercicio e, boolean empty) {
                super.updateItem(e, empty);

                if (empty || e == null) {
                    setGraphic(null);
                } else {
                    nombre.setText(e.getNombre());
                    reps.setText(e.getSeries() + " x " + e.getRepeticiones());
                    setGraphic(layout);
                }
            }
        });
    }

    private void mostrarDatosEjercicio(Ejercicio e) {
        lb_nombre.setText(e.getNombre());
        lb_tipo.setText(e.getTipoGrupoMuscular().name());
        ta_desc.setText(e.getDescripcionEjericio());
        lb_series.setText(String.valueOf(e.getSeries()));
        lb_reps.setText(String.valueOf(e.getRepeticiones()));
    }
}
