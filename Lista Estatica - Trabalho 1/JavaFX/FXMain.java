package JavaFX;

import AEDS.Principal;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class FXMain implements Initializable 
{
    
    @FXML
    private Label label; // Label tela inicial
    
    @Override
    public void initialize(URL url, ResourceBundle rb) // Função padrão do JavaFX
    {
        // TODO
    }    
    
    public void iniciarListaEstatica() // Ação do botao de iniciar
    {
        Principal.trocarTela("lista");
    }
}
