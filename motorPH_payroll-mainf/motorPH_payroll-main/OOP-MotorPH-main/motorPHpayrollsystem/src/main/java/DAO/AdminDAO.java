package DAO;

import java.io.*;
import java.util.*;
import OOP.HashUtil;

public final class AdminDAO implements UserDAOInterface {
    private static final String CSV_FILE = "src/main/java/CSV/AdminLogin.csv";
    private List<String[]> adminData;

    public AdminDAO() {
        adminData = new ArrayList<>();
        try {
            loadData();
        } catch (Exception ex) {
            System.getLogger(AdminDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    @Override
    public String[][] loadData() throws Exception {
        System.out.println("PATH: " + new File(CSV_FILE).getAbsolutePath());

        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            adminData.clear();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                adminData.add(values);
            }
            return adminData.toArray(String[][]::new);
        } catch (FileNotFoundException e) {
            throw new Exception("CSV file not found: " + CSV_FILE);
        }
    }

    @Override
    public boolean validateCredentials(String email, String password) {
        for (String[] admin : adminData) {
            if (admin.length > 1 && admin[0].trim().equals(email)) {
                String storedPassword = admin[1].trim();
                if (HashUtil.checkPassword(password, storedPassword)) {
                    return true;
                }
            }
        }
        return false;
    }
}
