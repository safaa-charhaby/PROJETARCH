package Entities;

public class credentialsC {

    private int idcredential;
    private String login, password;

    public credentialsC(int idcredential, String login, String password) {
        super();
        this.idcredential = idcredential;
        this.password=password;
        this.login = login;

    }

    public credentialsC() {
        super();
    }

    public int getIdcredential() {
        return idcredential;
    }

    public void setIdcredential(int idcredential) {
        this.idcredential = idcredential;
    }




    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "credentialsC [idcredential=" + idcredential + "login=" + login + ", password="
                + password + "]";
    }





}
