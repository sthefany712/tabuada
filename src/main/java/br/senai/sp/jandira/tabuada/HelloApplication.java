package br.senai.sp.jandira.tabuada;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        // Definir o tamanho da tela (stage)
        stage.setWidth(500);
        stage.setHeight(500);

        // Componente principal da tela (raiz)
        VBox root = new VBox(); //root: Vbox
        Scene scene = new Scene(root); //componente raiz. Cena é o espaço de dentro da tela

        // Cabeçalho da tela (1 cria o header depois as coisas q vão dentro dele)
        VBox header = new VBox();
        header.setStyle("-fx-padding: 10;-fx-background-color: #24a58a"); //padding: espaço em volta do componente


        // Adicionar um label ao header (2)
        Label labelTitulo = new Label("Tabuada"); // (javafx)
        labelTitulo.setStyle("-fx-text-fill: white;-fx-font-size: 30; -fx-font-weight: bold;"); //cor,tamanho, negrito do texto
        Label labelSubtitulo = new Label("Construa tabuadas sem limites!"); //coloco dentro do header
        labelSubtitulo.setStyle("-fx-text-fill: white;-fx-font-size: 14");

        header.getChildren().add(labelTitulo);
        header.getChildren().add(labelSubtitulo);

        // Colocar o header no root
        root.getChildren().addAll(header);

        // Criar o multiplicando
        HBox multiplicandoBox = new HBox(); // "caixa multiplicando"
        multiplicandoBox.setStyle("-fx-padding: 10;");
        Label labelMultiplicando = new Label("Multiplicando");
        TextField textFieldMultiplicando = new TextField();

       multiplicandoBox.getChildren().add(labelMultiplicando); // coloquei dentro do multi. box
       multiplicandoBox.getChildren().add(textFieldMultiplicando); // coloquei dentro do multi. box

       // Colocamos o multiplicandoBox no root
        root.getChildren().add(multiplicandoBox); //coloquei dentro do root (raiz)



        stage.setScene(scene); //stage é a tela
        stage.setTitle("Tabuada"); //título da tela
        stage.show();

    }
}
