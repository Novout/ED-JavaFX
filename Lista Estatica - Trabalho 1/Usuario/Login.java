package Usuario;

public class Login {
    private String nome;

    private int login;
    private int logout;
    
    public Login() 
    {
        this.nome = "NULL";    
        this.login = 0;
        this.logout = 0;
    }
    
    public Login(String nome, int login, int logout) 
    {
        this.nome = nome;
        this.login = login;
        this.logout = logout;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    @Override
    public String toString() // Para testes
    {
        return "Nome: "+this.nome+"Login: "+this.login+":00"+" | Logout: "+this.logout+":00";
    }
    
    public int getMedia() // Total de tempo logado
    {
        return this.logout - this.login;
    }

    public int getLogin() {
        return this.login;
    }

    public int getLogout() {
        return this.logout;
    }

    public void setLogin(int login) {
        this.login = login;
    }

    public void setLogout(int logout) {
        this.logout = logout;
    }
}
