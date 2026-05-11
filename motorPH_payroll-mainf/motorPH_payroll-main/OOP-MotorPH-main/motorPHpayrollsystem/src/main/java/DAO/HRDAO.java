package DAO;

import java.io.*;
import java.util.*;
import OOP.HashUtil;

public final class HRDAO implements UserDAOInterface {
    private static final String CSV_FILE = "src/main/java/CSV/HRLogin.csv";
    private List<String[]> hrData;

    public HRDAO() {
        hrData = new ArrayList<>();
        try {
            loadData();
        } catch (Exception ex) {
            System.getLogger(HRDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    @Override
    public String[][] loadData() throws Exception {
        System.out.println("PATH: " + new File(CSV_FILE).getAbsolutePath());
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            hrData.clear();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                hrData.add(values);
            }
            return hrData.toArray(String[][]::new);
        } catch (FileNotFoundException e) {
            throw new Exception("CSV file not found: " + CSV_FILE);
        }
    }

    @Override
    public boolean validateCredentials(String email, String password) {
        for (String[] hr : hrData) {
            if (hr.length > 1 && hr[0].trim().equals(email)) {
                String storedPassword = hr[1].trim();
                if (HashUtil.checkPassword(password, storedPassword)) {
                    return true;
                }
            }
        }
        return false;
    }
}
