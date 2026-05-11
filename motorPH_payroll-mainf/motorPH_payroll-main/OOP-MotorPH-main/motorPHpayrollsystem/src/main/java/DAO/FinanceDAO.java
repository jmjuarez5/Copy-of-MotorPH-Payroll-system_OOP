package DAO;

import java.io.*;
import java.util.*;
import OOP.HashUtil;

public final class FinanceDAO implements UserDAOInterface {
    private static final String CSV_FILE = "src/main/java/CSV/FinanceLogin.csv";
    private List<String[]> financeData;

    public FinanceDAO() {
        financeData = new ArrayList<>();
        try {
            loadData();
        } catch (Exception ex) {
            System.getLogger(FinanceDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    @Override
    public String[][] loadData() throws Exception {
        System.out.println("PATH: " + new File(CSV_FILE).getAbsolutePath());
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            financeData.clear();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                financeData.add(values);
            }
            return financeData.toArray(String[][]::new);
        } catch (FileNotFoundException e) {
            throw new Exception("CSV file not found: " + CSV_FILE);
        }
    }

    @Override
    public boolean validateCredentials(String email, String password) {
        for (String[] finance : financeData) {
            if (finance.length > 1 && finance[0].trim().equals(email)) {
                String storedPassword = finance[1].trim();
                if (HashUtil.checkPassword(password, storedPassword)) {
                    return true;
                }
            }
        }
        return false;
    }
}
