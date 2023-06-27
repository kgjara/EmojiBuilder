/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.openjfx.emojibuilder;

import TDA.CircularDoublyLL;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ListIterator;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Kenny
 */
public class FXMLController implements Initializable {

    @FXML
    private Button ButtonAdd;

    @FXML
    private Button ButtonBoca;

    @FXML
    private Button ButtonNext;

    @FXML
    private Button ButtonOjos;

    @FXML
    private Button ButtonExport;

    @FXML
    private Button ButtonPrevious;

    @FXML
    private Button ButtonRemove;

    @FXML
    private Button ButtonRostro;

    @FXML
    private ImageView ImageViewFace;

    @FXML
    private ImageView ImageViewMouth;

    @FXML
    private ImageView ImageViewEye;

    @FXML
    private ImageView ImageViewPreview1;

    @FXML
    private ImageView ImageViewPreview2;

    @FXML
    private ImageView ImageViewPreview3;

    @FXML
    private Button ButtonLogout;

    CircularDoublyLL<Image> listaFaces = agregarImagenesLista("src/main/resources/imagenes/faces");
    CircularDoublyLL<Image> listaEyes = agregarImagenesLista("src/main/resources/imagenes/eyes");
    CircularDoublyLL<Image> listaMouths = agregarImagenesLista("src/main/resources/imagenes/mouth");

    ListIterator<Image> itFace = listaFaces.listIterator();
    ListIterator<Image> itEye = listaEyes.listIterator();
    ListIterator<Image> itMouth = listaMouths.listIterator();

    private ImageView ImageViewBotonActual;

    private double x = 0;
    private double y = 0;

    public void initialize(URL url, ResourceBundle rb) {
        /*ImageFace.setImage(listaFaces.get(0));
        ImageMouth.setImage(listaMouth.get(0));
        ImageEyes.setImage(listaEyes.get(0));*/
    }

    public CircularDoublyLL<Image> agregarImagenesLista(String RutaCarpeta) {
        File archivo = new File(RutaCarpeta);
        CircularDoublyLL<Image> listaImagenes = new CircularDoublyLL<>();

        File[] archivos = archivo.listFiles();

        for (File file : archivos) {
            listaImagenes.addLast(new Image(file.toURI().toString()));
        }
        return listaImagenes;
    }

    @FXML
    void siguiente(ActionEvent event) {
        if (ImageViewBotonActual == ImageViewFace) {
            Image actual = itFace.next();
            ImageViewFace.setImage(actual);

            CambiarPreviews(actual, listaFaces);

        } else if (ImageViewBotonActual == ImageViewEye) {
            Image actual = itEye.next();
            ImageViewEye.setImage(actual);

            CambiarPreviews(actual, listaEyes);

        } else if (ImageViewBotonActual == ImageViewMouth) {
            Image actual = itMouth.next();
            ImageViewMouth.setImage(actual);

            CambiarPreviews(actual, listaMouths);

        }
    }

    @FXML
    void anterior(ActionEvent event) {
        if (ImageViewBotonActual == ImageViewFace) {
            Image actual = itFace.previous();
            ImageViewFace.setImage(actual);

            CambiarPreviews(actual, listaFaces);

        } else if (ImageViewBotonActual == ImageViewEye) {
            Image actual = itEye.previous();
            ImageViewEye.setImage(actual);

            CambiarPreviews(actual, listaEyes);

        } else if (ImageViewBotonActual == ImageViewMouth) {
            Image actual = itMouth.previous();
            ImageViewMouth.setImage(actual);

            CambiarPreviews(actual, listaMouths);

        }
    }

    @FXML
    void showEyes(ActionEvent event) {
        ImageViewPreview1.setImage(listaEyes.get(listaEyes.size() - 1));
        ImageViewPreview2.setImage(listaEyes.get(0));
        ImageViewPreview3.setImage(listaEyes.get(1));

        ButtonAdd.setText("Add new Eye");
        ButtonRemove.setText("Remove Eye");

        ImageViewBotonActual = ImageViewEye;
    }

    @FXML
    void showFaces(ActionEvent event) {
        ImageViewPreview1.setImage(listaFaces.get(listaFaces.size() - 1));
        ImageViewPreview2.setImage(listaFaces.get(0));
        ImageViewPreview3.setImage(listaFaces.get(1));

        ButtonAdd.setText("Add new Face");
        ButtonRemove.setText("Remove Face");

        ImageViewBotonActual = ImageViewFace;

    }

    @FXML
    void showMouth(ActionEvent event) {
        ImageViewPreview1.setImage(listaMouths.get(listaMouths.size() - 1));
        ImageViewPreview2.setImage(listaMouths.get(0));
        ImageViewPreview3.setImage(listaMouths.get(1));

        ButtonAdd.setText("Add new Mouth");
        ButtonRemove.setText("Remove Mouth");

        ImageViewBotonActual = ImageViewMouth;
    }

    @FXML
    void ClickImagen(MouseEvent event) {
        Image imagenP = ImageViewPreview1.getImage();
        if (ImageViewBotonActual == ImageViewFace) {
            ImageViewFace.setImage(imagenP);
        } else if (ImageViewBotonActual == ImageViewEye) {
            ImageViewEye.setImage(imagenP);
        } else if (ImageViewBotonActual == ImageViewMouth) {
            ImageViewMouth.setImage(imagenP);
        }
    }

    @FXML
    void ClickImagen2(MouseEvent event) {
        Image imagenP = ImageViewPreview2.getImage();
        if (ImageViewBotonActual == ImageViewFace) {
            ImageViewFace.setImage(imagenP);
        } else if (ImageViewBotonActual == ImageViewEye) {
            ImageViewEye.setImage(imagenP);
        } else if (ImageViewBotonActual == ImageViewMouth) {
            ImageViewMouth.setImage(imagenP);
        }
    }

    @FXML
    void ClickImagen3(MouseEvent event) {
        Image imagenP = ImageViewPreview3.getImage();
        if (ImageViewBotonActual == ImageViewFace) {
            ImageViewFace.setImage(imagenP);
        } else if (ImageViewBotonActual == ImageViewEye) {
            ImageViewEye.setImage(imagenP);
        } else if (ImageViewBotonActual == ImageViewMouth) {
            ImageViewMouth.setImage(imagenP);
        }
    }

    /*
    public int CantidadDeArchivos(String RutaCarpeta) {
        File archivo = new File(RutaCarpeta);
        File[] lista = archivo.listFiles();
        return lista.length;
    }
     */
    public void CambiarPreviews(Image img, CircularDoublyLL<Image> lista) {
        ImageViewPreview1.setImage(lista.getAnterior(img));
        ImageViewPreview2.setImage(img);
        ImageViewPreview3.setImage(lista.getSiguiente(img));
    }

    @FXML
    void AgregarNuevaImagen(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Selecciona nueva imagen");
        fc.getExtensionFilters().addAll(new ExtensionFilter("PNG File", "*.png"));
        File archivo = fc.showOpenDialog(null);
        if (archivo != null) {
            if (ImageViewBotonActual == ImageViewFace) {
                listaFaces.addLast(new Image(archivo.toURI().toString()));
            } else if (ImageViewBotonActual == ImageViewEye) {
                listaEyes.addLast(new Image(archivo.toURI().toString()));
            } else if (ImageViewBotonActual == ImageViewMouth) {
                listaMouths.addLast(new Image(archivo.toURI().toString()));
            }
        }
    }

    @FXML
    void RemoverImagen(ActionEvent event) {
        Image remover;

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("¡AVISO!");
        alert.setContentText("Solo puede eliminar la imagen del centro");
        alert.setHeaderText(null);

        if (ImageViewBotonActual == ImageViewFace) {
            remover = ImageViewFace.getImage();
            if (remover != ImageViewPreview3.getImage() && remover != ImageViewPreview1.getImage()) {
                listaFaces.remove(remover);
            } else {
                alert.show();
            }
        } else if (ImageViewBotonActual == ImageViewEye) {
            remover = ImageViewEye.getImage();
            if (remover != ImageViewPreview3.getImage() && remover != ImageViewPreview1.getImage()) {
                listaEyes.remove(remover);
            } else {
                alert.show();
            }
        } else if (ImageViewBotonActual == ImageViewMouth) {
            remover = ImageViewMouth.getImage();
            if (remover != ImageViewPreview3.getImage() && remover != ImageViewPreview1.getImage()) {
                listaMouths.remove(remover);
            } else {
                alert.show();
            }
        }
    }

    @FXML
    void ExportPNG(ActionEvent event) {
        Alert alert;

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar imagen");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("PNG File", "*.png"), new ExtensionFilter("JPEG File", "*.jpeg"));
        File archivo = fileChooser.showSaveDialog(null);

        Canvas canvas = new Canvas();

        canvas.setHeight(640);
        canvas.setWidth(640);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.drawImage(ImageViewFace.getImage(), 0, 0, 640, 640);
        gc.drawImage(ImageViewEye.getImage(), 100, 0, 450, 450);
        gc.drawImage(ImageViewMouth.getImage(), 100, 250, 450, 450);

        SnapshotParameters params = new SnapshotParameters();
        params.setFill(javafx.scene.paint.Color.TRANSPARENT);
        WritableImage image = canvas.snapshot(params, null);

        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "PNG", archivo);
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Imagen guardada con éxito!");
            alert.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void logout() {

        try {
            ButtonLogout.getScene().getWindow().hide();
            
            Parent root = FXMLLoader.load(getClass().getResource("LoginFXML.fxml"));
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
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
