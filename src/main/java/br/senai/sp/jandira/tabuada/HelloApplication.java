package br.senai.sp.jandira.tabuada;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class HelloApplication extends Application {
    TextField textFieldMultiplicando; //Declaramos essa variável tornando-a global, para que eu a acesse de todo lugar.
    TextField textFieldMenorMultiplicador;
    TextField textFieldMaiorMultiplicador;
    ListView listaTabuada;

    @Override
    public void start(Stage stage) throws IOException {

        // Definir o tamanho da tela (stage)
        //stage.setWidth(500); //comentando se em caixa no tamanho do que tem na tela
        stage.setHeight(600);

        // Controlando o fechamento ao clicar no fechar da janela (X)
        stage.setOnCloseRequest(e -> {
            fechar();
            e.consume();
        }); //esse E guarda o evento que esta sendo disparado. Ele é uma variavel e eu poderia usar qualquer nome



        //Bloquear o redirecionamento da janela
        stage.setResizable(false); //no padrão ele é true, deixado false o úsario não consegue mexer a tela

        // Componente principal da tela (raiz)
        VBox root = new VBox(); //root: Vbox
        Scene scene = new Scene(root); //componente raiz. Cena é o espaço de dentro da tela

        stage.setScene(scene); //stage é a tela
        stage.setTitle("Tabuada"); //título da tela

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

        // Criar o multiplicando
        GridPane gridFormulario = new GridPane();
        gridFormulario.setPadding(new Insets(20)); //MARGEM
        gridFormulario.setVgap(10); //ESPAÇO VERTICAL, por isso, Vgap
        gridFormulario.setHgap(10); //ESPAÇO HORIZONTAL, por isso, Horizontal
        Label labelMultiplicando = new Label("Multiplicando");
        textFieldMultiplicando = new TextField(); //inicializando a variavel

        Label labelMenorMultiplicador = new Label("Menor Multiplicador"); //tirar a declaração dele depois que torna-lo global
        textFieldMenorMultiplicador = new TextField();

        Label labelMaiorMultiplicador = new Label("Maior Multiplicador");
        textFieldMaiorMultiplicador= new TextField();

        gridFormulario.add(labelMultiplicando, 0, 0);
        gridFormulario.add(textFieldMultiplicando, 1, 0);
        gridFormulario.add(labelMenorMultiplicador, 0, 1);
        gridFormulario.add(textFieldMenorMultiplicador, 1, 1);
        gridFormulario.add(labelMaiorMultiplicador, 0, 2);
        gridFormulario.add(textFieldMaiorMultiplicador, 1, 2);

        // Criar a caixa de botões
        HBox boxBotoes = new HBox();
        boxBotoes.setAlignment(Pos.CENTER_RIGHT);
        boxBotoes.setPadding(new Insets(0,20,20,20));
        boxBotoes.setSpacing(10); //COLOCAR BOXBOTOES
        Button btnCalcular = new Button("Calcular"); //além de mostrar o texto tem que receber oq for digitado
        btnCalcular.setPrefHeight(90);
        btnCalcular.setOnAction(event -> {});
        btnCalcular.setOnAction(e -> {
           calcularTabuada();
        });

        Button btnLimpar = new Button("Limpar");
        btnLimpar.setPrefHeight(70);
        btnLimpar.setOnAction(event -> {});
        btnLimpar.setOnAction(e -> {
            limparFormulario();
        });

        Button btnSair = new Button("Sair");
        btnSair.setPrefHeight(70);
        btnSair.setOnAction(event -> {});
        btnSair.setOnAction(e -> {
            fechar();
            e.consume(); //abandone/esquece oq eu coloquei
        });

        // Adicionar os botões na boxBotões
        boxBotoes.getChildren().addAll(btnCalcular, btnLimpar, btnSair);

        // Adicionar um componente ListView
        VBox boxResultado = new VBox();
        boxResultado.setPadding(new Insets(0,20,20,20));
        Label labelResultado= new Label("Resultados");
        labelResultado.setStyle("-fx-text-fill: blue;-fx-font-size: 18; -fx-font-weight: bold;");

        // Adicionar o ListView
         listaTabuada = new ListView();

        //Adicionar o label ao boxResultado
        boxResultado.getChildren().add(labelResultado);
        boxResultado.getChildren().add(listaTabuada);

       // Adicionar componente ao root
        root.getChildren().addAll(header);
        root.getChildren().add(gridFormulario);
        root.getChildren().add(boxBotoes);
        root.getChildren().addAll(boxResultado);

        stage.show();
    }

    public void limparFormulario(){
        textFieldMultiplicando.setText("");
        textFieldMenorMultiplicador.setText("");
        textFieldMaiorMultiplicador.setText("");
        listaTabuada.getItems().clear();
        textFieldMultiplicando.requestFocus();
    }

    public void fechar(){

        Alert alertFechar = new Alert
                (Alert.AlertType.CONFIRMATION,
                        "Confirma a saída do sistema?",
                        ButtonType.YES,
                        ButtonType.NO
                ); //Alert: tela //Confirmation: sim/não
               Optional<ButtonType> resposta = alertFechar.showAndWait(); //objeto Option

                if (resposta.isPresent() && resposta.get() == ButtonType.YES) {
                    Platform.exit();
                }


    }

    public void calcularTabuada(){

        int multiplicando = Integer.parseInt(textFieldMultiplicando.getText()); //text..recebe o texto e o parseInt pega uma String e transf em Int
        int menorMultiplicador = Integer.parseInt(textFieldMenorMultiplicador.getText());
        int maiorMultiplicador = Integer.parseInt(textFieldMaiorMultiplicador.getText());

        // Pensando no possível erro do usuário. No lugar de maior ele colocou um número menor...
        if (menorMultiplicador > maiorMultiplicador){
            int auxiliar = menorMultiplicador;
            menorMultiplicador = maiorMultiplicador;
            maiorMultiplicador = auxiliar;
        }
        int tamanho = maiorMultiplicador - menorMultiplicador + 1; //+1 pois inicia c/ 0
        String[] tabuada = new String[tamanho];

        int contador = 0;
        while (contador < tamanho) {
            double produto = multiplicando * menorMultiplicador;
            tabuada[contador] = multiplicando + " X " + menorMultiplicador + " = " + produto;
            contador++;
            menorMultiplicador++;
        }

        listaTabuada.getItems().clear();
        listaTabuada.getItems().addAll(tabuada);
    }
}
