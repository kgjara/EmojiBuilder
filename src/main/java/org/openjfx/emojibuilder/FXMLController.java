/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.openjfx.emojibuilder;

import TDA.CircularDoublyLL;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ListIterator;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.Slider;
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

    @FXML
    private Slider sliderSize;

    @FXML
    private Slider sliderPositionX;

    @FXML
    private Slider sliderPositionY;

    @FXML
    private Button ButtonSave;

    @FXML
    private Button ButtonLoad;

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

        sliderSize.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                if (ImageViewBotonActual == ImageViewFace) {
                    ImageViewFace.setFitHeight((Double) t1);
                } else if (ImageViewBotonActual == ImageViewEye) {
                    ImageViewEye.setFitHeight((Double) t1);
                } else if (ImageViewBotonActual == ImageViewMouth) {
                    ImageViewMouth.setFitHeight((Double) t1);
                }
            }

        });

        sliderPositionX.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                if (ImageViewBotonActual == ImageViewFace) {
                    ImageViewFace.setX((Double) t1);
                } else if (ImageViewBotonActual == ImageViewEye) {
                    ImageViewEye.setX((Double) t1);
                } else if (ImageViewBotonActual == ImageViewMouth) {
                    ImageViewMouth.setX((Double) t1);
                }
            }
        });

        sliderPositionY.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                if (ImageViewBotonActual == ImageViewFace) {
                    ImageViewFace.setY((Double) t1);
                } else if (ImageViewBotonActual == ImageViewEye) {
                    ImageViewEye.setY((Double) t1);
                } else if (ImageViewBotonActual == ImageViewMouth) {
                    ImageViewMouth.setY((Double) t1);
                }
            }
        });
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

    @FXML
    void save(ActionEvent event) {
        Alert alert;
        //Emoji emoji = new Emoji(ImageViewFace.getImage(), ImageViewEye.getImage(), ImageViewMouth.getImage());

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar Proyecto");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("dat File", "*.dat"));
        fileChooser.setInitialDirectory(new File("src/main/resources"));
        File archivo = fileChooser.showSaveDialog(null);

        try {

            FileOutputStream fileOutPutStrem = new FileOutputStream(archivo);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutPutStrem);

            byte[] faceBytes = convertirImagenABytes(ImageViewFace.getImage());
            byte[] eyeBytes = convertirImagenABytes(ImageViewEye.getImage());
            byte[] mouthBytes = convertirImagenABytes(ImageViewMouth.getImage());

            objectOutputStream.writeObject(faceBytes);
            objectOutputStream.writeObject(eyeBytes);
            objectOutputStream.writeObject(mouthBytes);

            objectOutputStream.close();

            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Proyecto emoji guardado con éxito!");
            alert.show();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void load(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Cargar Proyecto");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("dat File", "*.dat"));
        fileChooser.setInitialDirectory(new File("src/main/resources"));
        File archivo = fileChooser.showOpenDialog(null);

        try {
            FileInputStream fileInputStream = new FileInputStream(archivo);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            byte[] faceBytes = (byte[]) objectInputStream.readObject();
            byte[] eyeBytes = (byte[]) objectInputStream.readObject();
            byte[] mouthBytes = (byte[]) objectInputStream.readObject();

            Image face = convertirBytesAImagen(faceBytes);
            Image eye = convertirBytesAImagen(eyeBytes);
            Image mouth = convertirBytesAImagen(mouthBytes);

            ImageViewFace.setImage(face);
            ImageViewEye.setImage(eye);
            ImageViewMouth.setImage(mouth);

            objectInputStream.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public byte[] convertirImagenABytes(Image image) throws IOException {
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", baos);
        return baos.toByteArray();
    }

    private Image convertirBytesAImagen(byte[] bytes) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        BufferedImage bufferedImage = ImageIO.read(bis);
        return SwingFXUtils.toFXImage(bufferedImage, null);
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
