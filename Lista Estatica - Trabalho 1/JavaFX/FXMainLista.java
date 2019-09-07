/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFX;

import AEDS.Principal;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.collections.ObservableList;
import Usuario.*;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class FXMainLista implements Initializable 
{
    @FXML
    private TableView<Login> tableViewLogins;
    
    @FXML
    private TableColumn<Login, String> ColunaNome;
    
    @FXML
    private TableColumn<Login, Integer> ColunaLogin;
    
    @FXML
    private TableColumn<Login, Integer> ColunaLogout;
    
    @FXML
    private Button btnCarregar;
    
    @FXML
    private Button btnAdicionar;
    
    @FXML
    private Button btnRemover;
    
    @FXML
    private Button btnMostraAposHora;
    
    @FXML 
    private Button btnMostraAcessoUsuario;
    
    @FXML
    private Button btnRemoverUsuario;
    
    @FXML
    private Button btnOrdenarListar;
    
    @FXML
    private TextField txtNome;
    
    @FXML
    private TextField txtLogin;
    
    @FXML
    private TextField txtLogout;
    
    @FXML
    private TextField txtInput;
    
    private final List<Login> listUsuarios = new ArrayList<>();
    
    private ObservableList<Login> obsUsuario;
    
    private final Logins logins = new Logins();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
    }    
    
    /**
     * Remove da lista a posicao escolhida
     */
    public void removerDaLista() 
    {
        this.limpar();
        
        try 
        {
            if(this.logins.getTotalDeUsuarios() > Integer.parseInt(this.txtInput.getText()))
                this.logins.remover(Integer.parseInt(this.txtInput.getText()));
        }catch(Exception e) 
        {
            System.out.println("removerDaLista: "+e.getMessage());
        }

        this.carregar();       
    }
    
    public void adicionar() 
    {
        try 
        {
            this.limpar();
            
            int loginAux = Integer.parseInt(this.txtLogin.getText());
            int logoutAux = Integer.parseInt(this.txtLogout.getText());
            
            if((loginAux >= 0 && loginAux <=23) && (logoutAux >= 0 && logoutAux <= 23) && (loginAux < logoutAux)) 
            {
                Login index = new Login(this.txtNome.getText(), loginAux, logoutAux);
                
                this.logins.adicionar(index);
            
                this.carregar();

                this.txtNome.clear();
                this.txtLogin.clear();
                this.txtLogout.clear();
            }else {
                System.out.println("Input inválido");
            }
            
            
        }catch(Exception e) 
        {
            System.out.println("adicionar: "+e.getMessage());
        }
    }
    
    public void carregar() 
    { 
        try 
        {
            ColunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
            ColunaLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
            ColunaLogout.setCellValueFactory(new PropertyValueFactory<>("logout"));
            
            for(int i = 0;i < this.logins.getTotalDeUsuarios();i++) 
            {     
                this.listUsuarios.add(this.logins.buscar(i));   
            }
        
            this.obsUsuario = FXCollections.observableArrayList(this.listUsuarios);

            this.tableViewLogins.setItems(this.obsUsuario); 
            
        }catch(Exception e) 
        {
            System.out.println("carregar: "+e.getMessage());
        }
    }
    
    public void carregar(Logins index) 
    {
       try 
        {
            for(int i = 0;i < index.getTotalDeUsuarios();i++) 
            {     
                this.listUsuarios.add(index.buscar(i));   
            }
        
            this.obsUsuario = FXCollections.observableArrayList(this.listUsuarios);

            this.tableViewLogins.setItems(this.obsUsuario); 
            
        }catch(Exception e) 
        {
            System.out.println("carregar: "+e.getMessage());
        } 
    }
    
    public void carregarGeral() 
    {
        this.limpar();
        
        this.carregar();
    }
    
    public void mostraAposHora() 
    {
        try 
        {   
            int index = Integer.parseInt(this.txtInput.getText());
        
            this.limpar();
            
            if(this.logins.getTotalDeUsuarios() != 0)
                for(int i = 0;i < this.logins.getTotalDeUsuarios();i++) 
                    if(this.logins.buscar(i).getLogin() > index)
                        this.listUsuarios.add(this.logins.buscar(i));   
        
            this.obsUsuario = FXCollections.observableArrayList(this.listUsuarios);

            this.tableViewLogins.setItems(this.obsUsuario); 
            
        }catch(Exception e) 
        {
            System.out.println("mostraAposHora: "+e.getMessage());
        }
    }
    
    public void mostraAcessoUsuario() 
    { 
        String index = this.txtInput.getText();
        this.limpar();
        
        try 
        {   
            if(this.logins.getTotalDeUsuarios() != 0)
                for(int i = 0;i < this.logins.getTotalDeUsuarios();i++) 
                    if(this.logins.buscar(i).getNome() == null ? index == null : this.logins.buscar(i).getNome().equals(index))
                        this.listUsuarios.add(this.logins.buscar(i));   
        
            this.obsUsuario = FXCollections.observableArrayList(this.listUsuarios);

            this.tableViewLogins.setItems(this.obsUsuario); 
            
        }catch(Exception e) 
        {
            System.out.println("mostraAcessoUsuario: "+e.getMessage());
        }
    }
    
    public void removerUsuario() 
    {
        int index = Integer.parseInt(this.txtInput.getText());
        
        this.limpar();
        
        if(this.logins.getTotalDeUsuarios() != 0)
            for(int i = 0;i < this.logins.getTotalDeUsuarios();i++) 
                if(this.logins.buscar(i).getMedia() < index)
                    this.logins.remover(i);
        
        this.carregar();
    }
    
    public Logins OrdenarUsuarios() 
    {
        Logins index = new Logins();
        for(int i = 0;i < this.logins.getTotalDeUsuarios();i++) 
        {
            index.adicionar(this.logins.buscar(i));
        }
        
        this.limpar();
            
        /**
          * Conseguir ordenar em ordem decrescente
          */ 
        if(index.getTotalDeUsuarios() != 0)
            for(int i = 0;i < index.getTotalDeUsuarios() - 1;i++) 
                for(int j = 0;j < index.getTotalDeUsuarios() - 1;j++)
                    if(index.buscar(j).getMedia() < index.buscar(j + 1).getMedia()) 
                    {
                        Login aux = index.buscar(j);
                        index.setLogin(index.buscar(j + 1), j);
                        index.setLogin(aux, j + 1);
                    }
        
        return index;
    }
    
    public void OrdenarUsuariosAction() 
    {
        this.carregar(this.OrdenarUsuarios());
    }
    
    public void limpar() 
    {
        try 
        {
            if(this.logins.getTotalDeUsuarios() != 0) 
                this.listUsuarios.clear(); 
            
        }catch(Exception e) 
        {
            System.out.println("limpar: "+e.getMessage());
        }
    }
}
