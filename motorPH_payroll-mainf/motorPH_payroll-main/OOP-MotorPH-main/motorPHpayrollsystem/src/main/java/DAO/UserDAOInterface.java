package DAO;

public interface UserDAOInterface {
    String[][] loadData() throws Exception;
    boolean validateCredentials(String email, String password) throws Exception;
}