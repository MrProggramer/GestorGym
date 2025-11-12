package gui.controller;

import enums.TipoGrupoMuscular;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import models.database.ControlData;
import models.rutinas.Ejercicio;
import models.rutinas.Rutina;
import models.users.Cliente;
import models.users.User;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ProfesorController extends BaseUserController implements Initializable {
    //tab ASIGNAR RUTINAS
    @FXML private ComboBox<User> cb_clientes;
    @FXML private ListView<Rutina> lv_rutinas_disponibles;
    @FXML private Button btn_asignar;
    @FXML private Button btn_borrar;
    @FXML private Label lb_nombre;
    @FXML private Label lb_status_tab1;

    //tabCREAR RUTINAS
    @FXML private ListView<Ejercicio> lv_ejercicios_disponibles;
    @FXML private ListView<Ejercicio> lv_ejercicios_seleccionados;
    @FXML private Button btn_agregar_ejercicio;
    @FXML private Button btn_quitar_ejercicio;
    @FXML private Button btn_guardar_rutina;
    @FXML private TextField tf_nombre_rutina;
    @FXML private TextArea tf_desc;
    @FXML private TextField tf_cant_dias;
    @FXML private Label lb_status_tab2;

    //tab ADMINISTRAR CLIENTES
    @FXML private ListView<Cliente> lv_clientes;
    @FXML private TextField tf_dias_restantes;
    @FXML private CheckBox cb_cuota_al_dia;
    @FXML private Button btn_actualizar_cliente;
    @FXML private Label lb_status_tab3;

    //tab ADMINISTRAR RUTINAS
    @FXML private ListView<Rutina> lv_rutinas_disponibles_admin;
    @FXML private ListView<Ejercicio> lv_ejercicios_disponibles_admin;
    @FXML private Button btn_admin_borrar;
    @FXML private Button btn_admin_actualizar;
    @FXML private Button btn_admin_agregar;
    @FXML private Label lb_status_tab4;
    @FXML private TextField tf_admin_nombre;
    @FXML private TextArea tf_admin_desc;
    @FXML private TextField tf_admin_series;
    @FXML private TextField tf_admin_reps;
    @FXML private ChoiceBox<TipoGrupoMuscular> cb_grupo;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { }

    @Override
    protected void inicializarVista() {
        lb_nombre.setText("Profesor: " + user_actual.getNombre());
        cargarAlumnos();
        cargarRutinas();
        cargarEjercicios();
        cargarClientes();
        cargarRutinasAdmin();
    }

    //TAB ASIGNAR
    private void cargarAlumnos() {
        cb_clientes.getItems().clear(); //limpiar visor
        for(User u : usuarios.getInventario()) {
            if(u instanceof Cliente) {
                cb_clientes.getItems().add(u);
            }
        }

        //cell factory (mostrar solo el nombre)
        cb_clientes.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(User user, boolean empty) {
                super.updateItem(user, empty);
                setText(empty || user == null ? null : user.getNombre());
            }
        });
        cb_clientes.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(User user, boolean empty) {
                super.updateItem(user, empty);
                setText(empty || user == null ? null : user.getNombre());
            }
        });

    }

    private void cargarRutinas() {
        lv_rutinas_disponibles.getItems().setAll(rutinas.getInventario());

        //mostrar solo nombre
        lv_rutinas_disponibles.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Rutina rutina, boolean empty) {
                super.updateItem(rutina, empty);
                setText(empty || rutina == null ? null : rutina.getNombre());
            }
        });
    }

    @FXML
    private void asignarRutina() {
        User user_selecc = cb_clientes.getValue();
        Rutina rutina_selecc = lv_rutinas_disponibles.getSelectionModel().getSelectedItem();

        if(user_selecc instanceof Cliente c && rutina_selecc == null) {
            c.setRutina(rutina_selecc);
            ControlData.guardarData(usuarios, "users");
            usuarios.actualizarGestor("users");
            mostrarMensaje("Rutina asignada a " + c.getNombre() + " (" + c.getUser() + ")", Color.GREEN, lb_status_tab1);
        }
    }

    @FXML
    private void borrarRutina() {
        User user_selecc = cb_clientes.getValue();

        if(user_selecc instanceof Cliente c) {
            c.setRutina(null);
            ControlData.guardarData(usuarios, "users");
            usuarios.actualizarGestor("users");
            mostrarMensaje("Rutina eliminada de " + c.getNombre() + " (" + c.getUser() + ")", Color.ORANGE, lb_status_tab1);
        }
    }

    //TAB CREAR
    private void cargarEjercicios() {
        lv_ejercicios_disponibles.getItems().setAll(ejercicios.getInventario());

        lv_ejercicios_disponibles.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Ejercicio e, boolean empty) {
                super.updateItem(e, empty);
                if (empty || e == null) {
                    setText(null);
                } else {
                    setText(e.getNombre() + " — " + e.getSeries() + "x" + e.getRepeticiones());
                }
            }
        });
    }

    @FXML
    private void agregarEjercicio() {
        Ejercicio e = lv_ejercicios_disponibles.getSelectionModel().getSelectedItem();
        if (e != null && !lv_ejercicios_seleccionados.getItems().contains(e)) {
            lv_ejercicios_seleccionados.getItems().add(e);
        }

        lv_ejercicios_seleccionados.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Ejercicio e, boolean empty) {
                super.updateItem(e, empty);
                if (empty || e == null) {
                    setText(null);
                } else {
                    setText(e.getNombre() + " — " + e.getSeries() + "x" + e.getRepeticiones());
                }
            }
        });
    }

    @FXML
    private void quitarEjercicio() {
        Ejercicio e = lv_ejercicios_disponibles.getSelectionModel().getSelectedItem();
        if (e != null) {
            lv_ejercicios_seleccionados.getItems().remove(e);
        }
    }

    @FXML
    private void guardarRutina() {
        String nombre = tf_nombre_rutina.getText();
        String desc = tf_desc.getText();
        int cant_dias = Integer.getInteger(tf_cant_dias.getText());

        if(nombre.isBlank() || desc.isBlank() || cant_dias == 0) {
            mostrarMensaje("Uno de los campos está vacío", Color.RED, lb_status_tab1);
            return;
        }

        List<Ejercicio> lista = new ArrayList<>(lv_ejercicios_seleccionados.getItems());
        Rutina nueva = new Rutina(cant_dias, desc, nombre); //int cant_dias, string desc, string nombre
        rutinas.altaItem(nueva);
        ControlData.guardarData(rutinas, "rutinas");
        rutinas.actualizarGestor("rutinas");
        cargarRutinas();
        mostrarMensaje("Rutina creada exitosamente", Color.GREEN, lb_status_tab2);

        tf_nombre_rutina.clear();
        tf_desc.clear();
        tf_cant_dias.clear();
        lv_ejercicios_seleccionados.getItems().clear();
    }


    //TAB ADMIN CLIENTES
    private void cargarClientes() {
        lv_clientes.getItems().clear();
        for (User u : usuarios.getInventario()) {
            if(u instanceof Cliente c) {
                lv_clientes.getItems().add(c);
            }
        }

        lv_clientes.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Cliente c, boolean empty) {
                super.updateItem(c, empty);
                setText(empty || c == null ? null : c.getNombre());
            }
        });

        lv_clientes.getSelectionModel().selectedItemProperty().addListener((obs, old, sel) -> {
            if (sel != null) {
                tf_dias_restantes.setText(String.valueOf(sel.getDias()));
                cb_cuota_al_dia.setSelected(sel.isCuotaAlDia());
            }
        });
    }

    @FXML
    private void actualizarCliente() {
        Cliente c = lv_clientes.getSelectionModel().getSelectedItem();
        if(c == null) return;

        try {
            int dias = Integer.parseInt(tf_dias_restantes.getText());
            c.setDias(dias);
            c.setCuotaAlDia(cb_cuota_al_dia.isSelected());
            ControlData.guardarData(usuarios, "users");
            usuarios.actualizarGestor("users");
            mostrarMensaje("Cliente actualizado correctamente", Color.GREEN, lb_status_tab3);
        } catch (NumberFormatException e) {
            mostrarMensaje("Ingrese un número válido para días restantes", Color.RED, lb_status_tab3);
        }
    }

    //TAB ADMIN RUTINAS
    private void cargarRutinasAdmin() {
        cb_grupo.getItems().setAll(TipoGrupoMuscular.values());

        lv_rutinas_disponibles_admin.getItems().setAll(rutinas.getInventario());
        lv_rutinas_disponibles_admin.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Rutina r, boolean empty) {
                super.updateItem(r, empty);
                setText(empty || r == null ? null : r.getNombre());
            }
        });

        //listener
        lv_rutinas_disponibles_admin.getSelectionModel().selectedItemProperty().addListener((obs, old, selected) -> {
            if (selected != null) {
                mostrarEjerciciosDeRutina(selected);
            } else {
                lv_ejercicios_disponibles_admin.getItems().clear();
                tf_admin_nombre.clear();
                tf_admin_desc.clear();
                tf_admin_series.clear();
                tf_admin_reps.clear();
                cb_grupo.setValue(null);
            }
        });
    }

    private void mostrarEjerciciosDeRutina(Rutina rutina) {
        lv_ejercicios_disponibles_admin.getItems().clear();
        lv_ejercicios_disponibles_admin.getItems().addAll(rutina.getListaEjercicios());

        //cell factory
        lv_ejercicios_disponibles_admin.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Ejercicio e, boolean empty) {
                super.updateItem(e, empty);
                if (empty || e == null) {
                    setText(null);
                } else {
                    setText(e.getNombre() + " — " + e.getSeries() + "x" + e.getRepeticiones());
                }
            }
        });

        lv_ejercicios_disponibles_admin.getSelectionModel().selectedItemProperty().addListener((o, old, ej) -> {
            if (ej != null) {
                tf_admin_nombre.setText(ej.getNombre());
                tf_admin_desc.setText(ej.getDescripcionEjericio());
                tf_admin_series.setText(String.valueOf(ej.getSeries()));
                tf_admin_reps.setText(String.valueOf(ej.getRepeticiones()));
                cb_grupo.setValue(ej.getTipoGrupoMuscular());
            }
        });
    }

    @FXML
    private void adminBorrarRutina() {
        Rutina rutina_selecc = lv_rutinas_disponibles_admin.getSelectionModel().getSelectedItem();
        Ejercicio ejer_selecc = lv_ejercicios_disponibles_admin.getSelectionModel().getSelectedItem();

        if (rutina_selecc == null) {
            mostrarMensaje("Seleccione una rutina primero", Color.RED, lb_status_tab4);
            return;
        }

        if (ejer_selecc == null) {
            mostrarMensaje("Seleccione un ejercicio para eliminar", Color.RED, lb_status_tab4);
            return;
        }

        rutina_selecc.getListaEjercicios().remove(ejer_selecc);
        ControlData.guardarData(rutinas, "rutinas");
        rutinas.actualizarGestor("rutinas");

        mostrarEjerciciosDeRutina(rutina_selecc);
        mostrarMensaje("Ejercicio eliminado exitosamente", Color.GREEN, lb_status_tab4);
    }

    @FXML
    private void adminActualizarRutina() {
        Rutina rutina_selecc = lv_rutinas_disponibles_admin.getSelectionModel().getSelectedItem();
        Ejercicio ejerc_selecc = lv_ejercicios_disponibles_admin.getSelectionModel().getSelectedItem();

        if (rutina_selecc == null || ejerc_selecc == null) {
            mostrarMensaje("Seleccione una rutina y un ejercicio primero", Color.RED, lb_status_tab4);
        }

        String nombre = tf_admin_nombre.getText().trim();
        String desc = tf_admin_desc.getText().trim();
        String series_txt = tf_admin_series.getText().trim();
        String reps_txt = tf_admin_reps.getText().trim();
        ejerc_selecc.setTipoGrupoMuscular(cb_grupo.getValue());

        if(nombre.isBlank() || desc.isBlank() || series_txt.isBlank() || reps_txt.isBlank()) {
            mostrarMensaje("Uno de los campos está vacío", Color.RED, lb_status_tab1);
            return;
        }

        int series, reps;
        try {
            series = Integer.parseInt(series_txt);
            reps = Integer.parseInt(reps_txt);
        } catch (NumberFormatException e) {
            mostrarMensaje("Series y repeticiones deben ser numeros", Color.RED, lb_status_tab4);
            return;
        }

        ejerc_selecc.setNombre(nombre);
        ejerc_selecc.setDescripcionEjericio(desc);
        ejerc_selecc.setSeries(series);
        ejerc_selecc.setRepeticiones(reps);
        cb_grupo.setValue(ejerc_selecc.getTipoGrupoMuscular());

        ControlData.guardarData(rutinas, "rutinas");
        rutinas.actualizarGestor("rutinas");

        lv_ejercicios_disponibles_admin.refresh();

        mostrarMensaje("Ejercicio actualizado correctamente", Color.GREEN, lb_status_tab4);
    }

    @FXML
    private void adminAgregarEjercicio() {
        Rutina rutina_selecc = lv_rutinas_disponibles_admin.getSelectionModel().getSelectedItem();
        if (rutina_selecc == null) {
            mostrarMensaje("Seleccione una rutina primero", Color.RED, lb_status_tab4);
            return;
        }

        String nombre = tf_admin_nombre.getText().trim();
        String desc = tf_admin_desc.getText().trim();
        String series_txt = tf_admin_series.getText().trim();
        String reps_txt = tf_admin_reps.getText().trim();
        TipoGrupoMuscular tipo = cb_grupo.getValue();

        if (nombre.isEmpty() || desc.isEmpty() || series_txt.isEmpty() || reps_txt.isEmpty() || tipo == null) {
            mostrarMensaje("Complete todos los campos para agregar un ejercicio", Color.RED, lb_status_tab4);
            return;
        }

        int series, reps;
        try {
            series = Integer.parseInt(series_txt);
            reps = Integer.parseInt(reps_txt);
        } catch (NumberFormatException e) {
            mostrarMensaje("Series y repeticiones deben ser números", Color.RED, lb_status_tab4);
            return;
        }

        Ejercicio nuevoEjercicio = new Ejercicio(nombre, tipo, desc, series, reps);
        rutina_selecc.getListaEjercicios().add(nuevoEjercicio);

        lv_ejercicios_disponibles_admin.getItems().add(nuevoEjercicio);
        lv_ejercicios_disponibles_admin.refresh();

        ControlData.guardarData(rutinas, "rutinas");
        rutinas.actualizarGestor("rutinas");

        mostrarMensaje("Ejercicio agregado correctamente", Color.GREEN, lb_status_tab4);
        tf_admin_nombre.clear();
        tf_admin_desc.clear();
        tf_admin_series.clear();
        tf_admin_reps.clear();
        cb_grupo.setValue(null);
    }


    private void mostrarMensaje(String msg, javafx.scene.paint.Color color, Label lb_status) {
        lb_status.setTextFill(color);
        lb_status.setText(msg);

        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event -> lb_status.setText(""));
        pause.play();
    }

}
