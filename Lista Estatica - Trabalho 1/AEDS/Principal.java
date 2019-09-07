package AEDS;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Principal extends Application 
{
    private static Stage fixStage;
    
    private static Scene InitialScene;
    private static Scene MainScene;
    
    @Override
    public void start(Stage stage) throws Exception 
    {
        fixStage = stage; // Trabalhar com o stage do JavaFX de forma facilitada
        Parent initial = FXMLLoader.load(getClass().getResource("/JavaFX/FXMain.fxml")); // Carregar FXML da tela inicial
        Parent main = FXMLLoader.load(getClass().getResource("/JavaFX/FXMainLista.fxml")); // Carregar FXML da tela principal
        
        InitialScene = new Scene(initial); // Cena Inicial
        MainScene = new Scene(main); // Cena Principal
        
        fixStage.setScene(InitialScene); // Cena inicial
        fixStage.setTitle("Lista Estática - AEDS"); // Título
        fixStage.setResizable(false); // Altura e Largura fixa
        
        fixStage.show(); // Habilitar cena
    }
    /**
     *
     * @param args argumentos da função
     */
    public static void main(String[] args) 
    {
        /**
         * Carregar argumentos do JavaFX
         */
        launch(args);
    }
    
    /**
     * Trocar tela inicial para principal
     * @param arg opcao de tela
     */
    public static void trocarTela(String arg) 
    {
        switch(arg) 
        {
            case "initial": 
            {
                fixStage.setScene(InitialScene);
                break;
            }
            case "lista": 
            {
                fixStage.setScene(MainScene);
                break;
            }
            default: 
            {
                System.out.println("FXML Inválido");
                break;
            }
        }
    }
}
