/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.openjfx.emojibuilder;

import TDA.CircularDoublyLL;
import java.io.File;
import java.net.URL;
import java.util.ListIterator;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

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

    CircularDoublyLL<Image> listaFaces = agregarImagenesLista("src/main/resources/imagenes/faces");
    CircularDoublyLL<Image> listaEyes = agregarImagenesLista("src/main/resources/imagenes/eyes");
    CircularDoublyLL<Image> listaMouths = agregarImagenesLista("src/main/resources/imagenes/mouth");

    ListIterator<Image> itFace = listaFaces.listIterator();
    ListIterator<Image> itEye = listaEyes.listIterator();
    ListIterator<Image> itMouth = listaMouths.listIterator();

    private ImageView ImageViewBotonActual;
    int cantArchivosFaces = CantidadDeArchivos("src/main/resources/imagenes/faces");
    int cantArchivosEyes = CantidadDeArchivos("src/main/resources/imagenes/eyes");
    ;
    int cantArchivosMouths = CantidadDeArchivos("src/main/resources/imagenes/mouth");
    ;
    
    int cont1 = 0;
    int cont2 = 1;
    int cont3 = 2;

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

            ImageViewPreview1.setImage(listaFaces.getAnterior(actual));
            ImageViewPreview2.setImage(actual);
            ImageViewPreview3.setImage(listaFaces.getSiguiente(actual));

            /*
            ImageViewFace.setImage(listaFaces.get(cont2));
            CambiarPreviews(cantArchivosFaces, listaFaces);*/
        } else if (ImageViewBotonActual == ImageViewEye) {
            Image actual = itEye.next();
            ImageViewEye.setImage(actual);

            ImageViewPreview1.setImage(listaEyes.getAnterior(actual));
            ImageViewPreview2.setImage(actual);
            ImageViewPreview3.setImage(listaEyes.getSiguiente(actual));
            /*
            ImageViewEyes.setImage(listaEyes.get(cont2));
            CambiarPreviews(cantArchivosEyes, listaEyes);*/
        } else if (ImageViewBotonActual == ImageViewMouth) {
            Image actual = itMouth.next();
            ImageViewMouth.setImage(actual);

            ImageViewPreview1.setImage(listaMouths.getAnterior(actual));
            ImageViewPreview2.setImage(actual);
            ImageViewPreview3.setImage(listaMouths.getSiguiente(actual));

            /*ImageViewMouth.setImage(listaMouths.get(cont2));
            CambiarPreviews(cantArchivosMouths, listaMouths);*/
        }
        /*
        cont1++;
        cont2++;
        cont3++;*/
    }

    @FXML
    void anterior(ActionEvent event) {
        if (ImageViewBotonActual == ImageViewFace) {
            Image actual = itFace.previous();
            ImageViewFace.setImage(actual);

            ImageViewPreview1.setImage(listaFaces.getAnterior(actual));
            ImageViewPreview2.setImage(actual);
            ImageViewPreview3.setImage(listaFaces.getSiguiente(actual));
        } else if (ImageViewBotonActual == ImageViewEye) {
            Image actual = itEye.previous();
            ImageViewEye.setImage(actual);

            ImageViewPreview1.setImage(listaEyes.getAnterior(actual));
            ImageViewPreview2.setImage(actual);
            ImageViewPreview3.setImage(listaEyes.getSiguiente(actual));
        } else if (ImageViewBotonActual == ImageViewMouth) {
            Image actual = itMouth.previous();
            ImageViewMouth.setImage(actual);

            ImageViewPreview1.setImage(listaMouths.getAnterior(actual));
            ImageViewPreview2.setImage(actual);
            ImageViewPreview3.setImage(listaMouths.getSiguiente(actual));
        }
    }

    @FXML
    void showEyes(ActionEvent event) {
        ImageViewPreview1.setImage(listaEyes.get(0));
        ImageViewPreview2.setImage(listaEyes.get(1));
        ImageViewPreview3.setImage(listaEyes.get(2));
        ImageViewBotonActual = ImageViewEye;
    }

    @FXML
    void showFaces(ActionEvent event) {
        ImageViewPreview1.setImage(listaFaces.get(0));
        ImageViewPreview2.setImage(listaFaces.get(1));
        ImageViewPreview3.setImage(listaFaces.get(2));
        ImageViewBotonActual = ImageViewFace;

    }

    @FXML
    void showMouth(ActionEvent event) {
        ImageViewPreview1.setImage(listaMouths.get(0));
        ImageViewPreview2.setImage(listaMouths.get(1));
        ImageViewPreview3.setImage(listaMouths.get(2));
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

    public int CantidadDeArchivos(String RutaCarpeta) {
        File archivo = new File(RutaCarpeta);
        File[] lista = archivo.listFiles();
        return lista.length;
    }

    public void CambiarPreviews(int CantArchivos, CircularDoublyLL<Image> lista) {
        if (cont3 >= CantArchivos) {
            cont3 = 0;
        } else if (cont2 >= CantArchivos) {
            cont2 = 0;
        } else if (cont1 >= CantArchivos) {
            cont1 = 0;
        }
        ImageViewPreview1.setImage(lista.get(cont1));
        ImageViewPreview2.setImage(lista.get(cont2));
        ImageViewPreview3.setImage(lista.get(cont3));
    }
}
