package CTRL_SRVS;

import DAO.*;
import java.util.HashMap;
import java.util.Map;

public class LoginService {
    private static final Map<String, UserDAOInterface> userDAOs = new HashMap<>();
    
    static {
        userDAOs.put("Admin", new AdminDAO());
        userDAOs.put("HR", new HRDAO());
        userDAOs.put("Finance", new FinanceDAO());
        userDAOs.put("Employee", new EmployeeDAO());
    }

    public static boolean checkCredentials(String role, String email, String password) {
        try {
            UserDAOInterface userDAO = userDAOs.get(role);
            if (userDAO != null) {
                return userDAO.validateCredentials(email, password);
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean checkEmployeeCredentials(String email, String password) {
        try {
            EmployeeDAO employeeDAO = (EmployeeDAO) userDAOs.get("Employee");
            String[][] allEmployees = employeeDAO.loadEmployees();

            for (String[] employeeData : allEmployees) {
                if (employeeData.length >= 21 &&
                    employeeData[19] != null &&
                    employeeData[20] != null &&
                    employeeData[19].trim().equalsIgnoreCase(email.trim())) {

                    String storedPassword = employeeData[20].trim();
                    return OOP.HashUtil.checkPassword(password, storedPassword);
                }
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public static boolean validateLogin(String email, String password) {
        return checkCredentials("Admin", email, password) ||
               checkCredentials("HR", email, password) ||
               checkCredentials("Finance", email, password) ||
               checkEmployeeCredentials(email, password);
    }
}
