package DAO;

public interface EmployeeDAOInterface {
    String[][] loadEmployees() throws Exception;
    String[] findEmployeeById(String employeeId);

    void addEmployee(String[] employeeData) throws Exception;
    void updateEmployee(String employeeId, String[] newData) throws Exception;
    void deleteEmployee(String employeeId) throws Exception;
    void saveToCSV() throws Exception;
}