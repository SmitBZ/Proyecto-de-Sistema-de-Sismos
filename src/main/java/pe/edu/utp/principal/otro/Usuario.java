package pe.edu.utp.principal.otro;

/**
 * @author Jesus
 * Esta es una clase que sirve de validador de usuario
 */
public class Usuario {

    private String username;
    private String password;


    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
