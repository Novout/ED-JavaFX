/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFX;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Usuario.*;
import java.io.File;
import java.io.FileNotFoundException;

public final class FXMainLista implements Initializable 
{
    /**
     * Os ArrayList usados são exclusivos do JavaFX, toda a lógica da lista estática não utiliza ArrayList.
     */
    @FXML
    private TableView<Login> tableViewLogins; // TableView
    
    @FXML
    private TableColumn<Login, String> ColunaNome; // Coluna do nome de cada usuario
    
    @FXML
    private TableColumn<Login, Integer> ColunaLogin; // Coluna do login de cada usuário
    
    @FXML
    private TableColumn<Login, Integer> ColunaLogout; // Coluna do logout de cada usuário
    
    @FXML
    private Button btnCarregar; // Botao Carregar
    
    @FXML
    private Button btnAdicionar; // Botao Adicionar
    
    @FXML
    private Button btnRemover; // Botao Remover pela posição
    
    @FXML
    private Button btnMostraAposHora; // Botao login após o horário do input
    
    @FXML 
    private Button btnMostraAcessoUsuario; // Botao procurar usuário baseado na string digitada no input
    
    @FXML
    private Button btnRemoverUsuario; // Remover usuários com a média de tempo menor que o input
    
    @FXML
    private Button btnOrdenarListar; // Mostra uma cópia ordenada de forma decrescente
    
    @FXML
    private TextField txtNome; // Campo do input de nome
    
    @FXML
    private TextField txtLogin; // Campo do input de login
    
    @FXML
    private TextField txtLogout; // Campo do input de logout
    
    @FXML
    private TextField txtInput; // Campo do input auxiliar dos botões
    
    /**
     * Tanto o List quanto o ArrayList serve apenas para colocar o conteúdo que está no logins no table do JavaFX, servindo APENAS como auxiliar.
     */
    private final List<Login> listLogins; 
    
    private ObservableList<Login> obsLogin;
    
    /**
     * Construção da lista
     */
    private final Logins logins;
    
    private Scanner testeTP1;
    
    /**
     * Construtor que inclue todo o conteúdo do testeTP1.txt na lista
     */
    public FXMainLista() 
    {
        int aux = 0; // Auxiliador para identificar tipo de entrada, em 0 (nome) / 1 (login) / 2 (logout)
        
        /* Auxiliadores do input */
        String nome = "NULL";
        int login = 0;
        int logout = 0;
        
        /* Construindo */
        this.listLogins = new ArrayList<>(); // Array auxiliar para mostrar conteúdos no tableView
        this.logins = new Logins(); // lista estática
       
        try 
        {
            this.testeTP1 = new Scanner(new File("C:\\dev\\java\\AEDS\\src\\testeTP1.txt")); // Carregar diretório do .txt
        }catch(FileNotFoundException e) 
        {
            System.out.println("Arquivo não foi encontrado: "+e.getMessage());
            return;
        }
        
        /* Carregar todo o conteúdo do .txt */
        while(this.testeTP1.hasNextLine()) 
        {
            switch (aux) 
            {
                case 0:
                    nome = this.testeTP1.nextLine();
                    aux++;
                    break;
                case 1:
                    login = this.testeTP1.nextInt();
                    aux++;
                    break;
                case 2:
                    logout = this.testeTP1.nextInt();
                    this.adicionar(nome, login, logout); // Cria um novo objeto com os parâmetros recebidos
                    aux = 0;
                    break;
                default:           
                    break;
            }
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        this.carregarGeral(); /* Recarrega a lista mostrando os dados iniciais */
        
        this.fixInitialInput(); /* Arruma o problema de espacamento dos nomes inicias */
    }    
    
    public void fixInitialInput() 
    {
        for(int i = 0; i < this.logins.getTotalDeUsuarios();i++) 
        {
            this.logins.buscar(i).setNome(this.logins.buscar(i).getNome().replace(" ", ""));
        }
    }
    
    /**
     * Remove da lista a posicao colocada no input
     */
    public void removerDaLista() 
    {
        this.limpar();
        
        try 
        {
            /* Verifica se a posicao do input e valida */
            if(this.logins.getTotalDeUsuarios() > Integer.parseInt(this.txtInput.getText()))
                this.logins.remover(Integer.parseInt(this.txtInput.getText()));
        }catch(Exception e) 
        {
            System.out.println("removerDaLista: "+e.getMessage());
        }

        this.carregar();       
    }
    
    /**
     * Adiciona o conteudo de testeTP1.txt
     * @param nome nome recebido do .txt
     * @param login login recebido do .txt
     * @param logout logout recebido do .txt
     */
    public void adicionar(String nome, int login, int logout) 
    {
        try 
        {
            /* Verifica se os logins e logouts estão entre 0 e 23*/
            /* Verifica se o tempo de logout é maior que o de login*/
            if((login >= 0 && login <=23) && (logout >= 0 && logout <= 23) && (login < logout)) 
            {
                Login index = new Login(nome, login, logout); // Novo objeto
                
                this.logins.adicionar(index); // Adiciona a nova instância do objeto na lista

            }else {
                System.out.println("Input inválido");
            }
            
            
        }catch(Exception e) 
        {
            System.out.println("adicionar: "+e.getMessage());
        }
    }
    
    /**
     * Adiciona ao final da lista
     */
    public void adicionar() 
    {
        try 
        {
            this.limpar(); // Da clear na Table
            
            int loginAux = Integer.parseInt(this.txtLogin.getText()); // Converte o input para inteiro
            int logoutAux = Integer.parseInt(this.txtLogout.getText()); // Converte o input para inteiro
            
            if((loginAux >= 0 && loginAux <=23) && (logoutAux >= 0 && logoutAux <= 23) && (loginAux < logoutAux)) 
            {
                Login index = new Login(this.txtNome.getText(), loginAux, logoutAux); // Novo objeto
                
                this.logins.adicionar(index); // Adicionando o objeto ao fim da lista
            
                this.carregar(); // Carrega o novo Login no Table

                // clear nos input's
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
    
    /**
     * Carrega todo o conteúdo do Logins no listLogins, para mostrar na Table
     */
    public void carregar() 
    { 
        try 
        {
            /* Table View */
            ColunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
            ColunaLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
            ColunaLogout.setCellValueFactory(new PropertyValueFactory<>("logout"));
            
            for(int i = 0;i < this.logins.getTotalDeUsuarios();i++) 
            {     
                this.listLogins.add(this.logins.buscar(i)); // Adiciona todos os itens do Logins no ArrayList
            }
        
            this.obsLogin = FXCollections.observableArrayList(this.listLogins);

            this.tableViewLogins.setItems(this.obsLogin); // Seta o ArrayList na table
            
        }catch(Exception e) 
        {
            System.out.println("carregar: "+e.getMessage());
        }
    }
    
    /**
     * Carrega todo o conteúdo do Logins no listLogins, para mostrar na Table
     * @param index logins auxiliar para a lista decrescente, nao mexendo com o logins principal
     */
    public void carregar(Logins index) 
    {
       try 
        {
            for(int i = 0;i < index.getTotalDeUsuarios();i++) 
            {     
                this.listLogins.add(index.buscar(i)); //Adiciona todo o conteudo da lista no ArrayList
            }
        
            this.obsLogin = FXCollections.observableArrayList(this.listLogins); // Seta o ArrayList

            this.tableViewLogins.setItems(this.obsLogin); //Exibe a table
            
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
    
    /* Mostra todos os usuários após uma determinada inserida no input*/
    public void mostraAposHora() 
    {
        try 
        {   
            int index = Integer.parseInt(this.txtInput.getText());
        
            this.limpar();
            
            if(this.logins.getTotalDeUsuarios() != 0)
                for(int i = 0;i < this.logins.getTotalDeUsuarios();i++) 
                    if(this.logins.buscar(i).getLogin() > index) // Se a hora de login for maior que a do input
                        this.listLogins.add(this.logins.buscar(i)); // Adiciona o objeto na ArrayList
        
            this.obsLogin = FXCollections.observableArrayList(this.listLogins);

            this.tableViewLogins.setItems(this.obsLogin); 
            
        }catch(Exception e) 
        {
            System.out.println("mostraAposHora: "+e.getMessage());
        }
    }
    
    /* Procura o usuário inserido no array*/
    public void mostraAcessoUsuario() 
    { 
        String index = this.txtInput.getText();
        this.limpar();
        
        try 
        {   
            if(this.logins.getTotalDeUsuarios() != 0)
                for(int i = 0;i < this.logins.getTotalDeUsuarios();i++) 
                    if(this.logins.buscar(i).getNome().equals(index)) // Se o nome do usuario for igual ao do index
                        this.listLogins.add(this.logins.buscar(i)); // Adiciona no ArrayList
        
            this.obsLogin = FXCollections.observableArrayList(this.listLogins);

            this.tableViewLogins.setItems(this.obsLogin); 
            
        }catch(Exception e) 
        {
            System.out.println("mostraAcessoUsuario: "+e.getMessage());
        }
    }
    
    public void removerUsuario() 
    {
        int index = Integer.parseInt(this.txtInput.getText());
        
        this.limpar();
        
        /* Três loops para garantir que removeu todos os itens das condições, não é nada eficiente mas funciona.*/
        if(this.logins.getTotalDeUsuarios() != 0)
            for(int i = 0;i < this.logins.getTotalDeUsuarios();i++) 
                for(int j = 0;j < this.logins.getTotalDeUsuarios();j++) 
                    for(int k = 0;k < this.logins.getTotalDeUsuarios();k++) 
                        if(this.logins.buscar(k).getMedia() < index)
                            this.logins.remover(k);
        
        this.carregar();
    }
    
    /**
     * Ordenar em ordem decrescente um logins auxiliar e não o principal
     * @return index retorno o logins em ordem decrescente
     */
    public Logins OrdenarUsuarios() 
    {
        Logins index = new Logins();
        
        /* Copia o conteúdo do logins para o index */
        for(int i = 0;i < this.logins.getTotalDeUsuarios();i++) 
        {
            index.adicionar(this.logins.buscar(i));
        }
        
        this.limpar();
            
        /**
          * Sort básico para conseguir ordenar em ordem decrescente
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
        
        return index; // Retorno do logins auxiliar
    }
    
    /* Ação do botao para ordenar em ordem decrescente */
    public void OrdenarUsuariosAction() 
    {
        this.carregar(this.OrdenarUsuarios());
    }
    
    /* Limpa a lista auxiliar */
    public void limpar() 
    {
        try 
        {
            if(this.logins.getTotalDeUsuarios() != 0) 
                this.listLogins.clear(); 
            
        }catch(Exception e) 
        {
            System.out.println("limpar: "+e.getMessage());
        }
    }
}
