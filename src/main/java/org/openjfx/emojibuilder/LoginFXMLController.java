/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.openjfx.emojibuilder;

import TDA.LinkedList;
import TDA.User;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Kenny
 */
public class LoginFXMLController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button ButtonLogin;
    @FXML
    private ImageView imageView1;
    @FXML
    private ImageView imageView2;

    File file = new File("src/main/resources/Users/Username&Password.txt");

    private double x = 0;
    private double y = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        LinkedList<User> listaUsuarios = CargarListaUsuarios(); //Opcional, solo para ver los usuarios mas facil,
        System.out.println(listaUsuarios);                      //sin tener que abrir el .txt

    }

    public LinkedList<User> CargarListaUsuarios() {
        LinkedList<User> listaUsuarios = new LinkedList<>();
        file = new File("src/main/resources/Users/Username&Password.txt");
        Scanner scanner;
        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                Scanner sl = new Scanner(linea);
                sl.useDelimiter(":");

                User user = new User();
                user.setUsername(sl.next());
                user.setPassword(sl.next());

                listaUsuarios.addLast(user);

                /*System.out.println(sl.next());
                System.out.println(sl.next());*/
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return listaUsuarios;
    }

    public void login() {

        Alert alert;
        try {
            if (checkLogin(username.getText(), password.getText()) == true) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("Succesfully Login");
                alert.showAndWait();

                ButtonLogin.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent event) -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event) -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);

                    stage.setOpacity(.8);
                });

                root.setOnMouseReleased((MouseEvent event) -> {
                    stage.setOpacity(1);
                });

                stage.setScene(scene);
                stage.show();
            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Wrong Username/Password");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkLogin(String username, String password) {
        LinkedList<User> listaUsuarios = CargarListaUsuarios();
        boolean correcto = false;
        for (int i = 0; i < listaUsuarios.size(); i++) {
            if (listaUsuarios.get(i).getUsername().equals(username)
                    && listaUsuarios.get(i).getPassword().equals(password)) {
                correcto = true;
            }
        }
        return correcto;
    }

    public boolean checkSignup(String username) {
        LinkedList<User> listaUsuarios = CargarListaUsuarios();
        boolean correcto = false;
        for (int i = 0; i < listaUsuarios.size(); i++) {
            if (listaUsuarios.get(i).getUsername().equals(username)) {
                correcto = true;
            }
        }
        return correcto;
    }

    public void signup() {
        Alert alert;
        try {
            if (checkSignup(username.getText()) == true) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("Usuario ya existente");
                alert.show();
                
            } else if (username.getText().equals("") || password.getText().equals("")) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("Por favor, llene todas las celdas");
                alert.show();
                
            } else {
                FileWriter fileWriter = new FileWriter(file, true);
                fileWriter.write(username.getText() + ":" + password.getText() + '\n');
                fileWriter.close();

                username.clear();
                password.clear();

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("Usuario agregado con éxito");
                alert.show();

            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
