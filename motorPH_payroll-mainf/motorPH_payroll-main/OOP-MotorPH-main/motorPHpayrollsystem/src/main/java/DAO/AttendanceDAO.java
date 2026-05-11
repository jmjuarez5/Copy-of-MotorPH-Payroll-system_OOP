package DAO;

import java.io.*;
import java.util.*;
import java.util.Arrays;

public final class AttendanceDAO implements AttendanceDAOInterface {
    private static final String CSV_FILE = "src/main/java/CSV/AttendanceRecords.csv";
    private List<String[]> attendanceData;

    public AttendanceDAO() {
        attendanceData = new ArrayList<>();
        try {
            loadAttendance();
        } catch (Exception ex) {
            System.getLogger(AttendanceDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    @Override
    public String[][] loadAttendance() throws Exception {
        System.out.println("PATH: " + new File(CSV_FILE).getAbsolutePath());
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            attendanceData.clear();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                // Ensures that row has four columns
                if (values.length < 4) {
                    values = Arrays.copyOf(values, 4);
                }
                attendanceData.add(values);
            }
            return attendanceData.toArray(new String[0][]);
        } catch (FileNotFoundException e) {
            throw new Exception("CSV file not found: " + CSV_FILE);
        }
    }

    @Override
    public String[][] findAttendanceByEmployee(String employeeId) {
        List<String[]> result = new ArrayList<>();
        for (String[] record : attendanceData) {
            if (record[0].equals(employeeId)) {
                result.add(record);
            }
        }
        return result.toArray(String[][]::new);
    }

    @Override
    public void logAttendance(String employeeId, String date, String timeIn, String timeOut) throws Exception {
        // Validate inputs
        if (employeeId == null || employeeId.trim().isEmpty()) {
            throw new IllegalArgumentException("Employee ID cannot be null or empty");
        }
        if (date == null || date.trim().isEmpty()) {
            throw new IllegalArgumentException("Date cannot be null or empty");
        }
        
        if (!timeIn.isEmpty()) {
            // Time-In: Create new row
            String[] newRecord = new String[4];
            newRecord[0] = employeeId.trim();
            newRecord[1] = date.trim();
            newRecord[2] = timeIn.trim();
            newRecord[3] = "";
            attendanceData.add(newRecord);
            System.out.println("Time-In logged: " + employeeId + " at " + timeIn + " on " + date);
        } else if (!timeOut.isEmpty()) {
            // Time-Out: Find and update latest row for this employee/date with empty Time-Out
            boolean found = false;
            for (int i = attendanceData.size() - 1; i >= 0; i--) {
                String[] record = attendanceData.get(i);
                // Ensure record has 4 columns
                if (record.length < 4) {
                    record = Arrays.copyOf(record, 4);
                }
                if (record[0] != null && record[0].equals(employeeId) && 
                    record[1] != null && record[1].equals(date) && 
                    (record[3] == null || record[3].trim().isEmpty())) {
                    record[3] = timeOut.trim();
                    found = true;
                    System.out.println("Time-Out logged: " + employeeId + " at " + timeOut + " on " + date);
                    break;
                }
            }
            if (!found) {
                System.err.println("Warning: No matching Time-In found for Time-Out: " + employeeId + " on " + date);
            }
        } else {
            System.err.println("Warning: Neither Time-In nor Time-Out provided for employee: " + employeeId);
        }
        saveToCSV();
    }

    @Override
    public void saveToCSV() throws Exception {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CSV_FILE))) {
            for (String[] record : attendanceData) {
                bw.write(String.join(",", record));
                bw.newLine();
            }
        } catch (IOException e) {
            throw new Exception("Error saving to CSV: " + e.getMessage());
        }
    }
    
    /**
     * Loads attendance data from CSV file into a 2D array.
     * This method is used by the service layer to provide data to the GUI.
     * 
     * @param csvFilePath The path to the CSV file
     * @return 2D array containing attendance data
     * @throws Exception if file cannot be read
     */
    public String[][] loadAttendanceDataFromCSV(String csvFilePath) throws Exception {
        List<String[]> data = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            boolean isFirstRow = true;

            while ((line = br.readLine()) != null) {
                if (isFirstRow) {
                    isFirstRow = false; // Skip the header row
                    continue;
                }
                
                String[] rowData = line.split(",");
                // Ensure that row has four columns
                if (rowData.length < 4) {
                    rowData = Arrays.copyOf(rowData, 4);
                }
                data.add(rowData);
            }
        } catch (IOException e) {
            throw new Exception("Failed to load attendance data from CSV: " + e.getMessage());
        }
        
        return data.toArray(String[][]::new);
    }
}
