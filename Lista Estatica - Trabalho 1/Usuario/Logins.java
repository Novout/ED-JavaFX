package Usuario;

/**
 *
 * @author User
 */
public class Logins {
    private Login[] logins;
    private int totalDeLogins;
    
    /**
     * Construtor de Logins
     */
    public Logins() 
    {
        this.logins = new Login[10];
        this.totalDeLogins = 0;
    }
    
    /**
     * Adiciona ao fim da lista e verifica se é possível adicionar
     * @param index 
     */
    public void adicionar(Login index) 
    {
        if(this.logins.length == this.totalDeLogins)
            this.expandirLista();
        
        this.logins[this.totalDeLogins] = index;
        this.totalDeLogins++;
    }
    
    /**
     * Remover um dos login na lista baseado em sua posição
     * @param pos Posicao de um Login na Lista
     * 
     */
    
    public void remover(int pos) 
    {
        for(int i = pos;i < this.totalDeLogins - 1;i++) 
            this.logins[i] = this.logins[i + 1];
        
        this.totalDeLogins--;
    }
    
    /**
     * 
     * @param pos Posicao de um Login na Lista
     * @return Login
     */
    public Login buscar(int pos) 
    {
        return this.logins[pos];
    }
    
    public void setLogin(Login login, int pos) 
    {
        this.logins[pos] = login;
    }
    
    /**
     * Irá ser chamada quando tiver a necessidade de expandir a lista, adicionando 10 posições
     */
    public void expandirLista() 
    {
        Login[] index = new Login[this.logins.length + 10];
        
        /*  for(int i = 0;i < this.totalDeLogins;i++)
                index[i] = this.logins[i];
        */
        
        System.arraycopy(this.logins, 0, index, 0, this.totalDeLogins);
        
        this.logins = index;
    }
    
    public void setLogins(Login[] logins) 
    {
        this.logins = logins;
    }

    public void setTotalDeUsuarios(int totalDeUsuarios) 
    {
        this.totalDeLogins += totalDeUsuarios;
    }

    public Object[] getUsuarios() 
    {
        return logins;
    }

    public int getTotalDeUsuarios() 
    {
        return totalDeLogins;
    }
}
