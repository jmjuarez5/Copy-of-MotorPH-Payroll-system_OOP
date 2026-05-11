package DAO;

import OOP.SystemIT;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;

public class LeaveDAO implements LeaveDAOInterface {
    
    @Override
    public boolean submitLeaveRequest(String employeeNumber, String leaveType, String startDate, String endDate, String status) {
        try (FileWriter fw = new FileWriter(SystemIT.LEAVES_CSV, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter pw = new PrintWriter(bw)) {

            String[] leaveData = {employeeNumber, leaveType, startDate, endDate, status};
            pw.println(String.join(",", leaveData));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean updateLeaveStatus(String employeeNumber, String leaveType, String startDate, String endDate, String newStatus) {
        List<String[]> updatedData = new ArrayList<>();
        String csvFile = SystemIT.LEAVES_CSV;
        boolean foundAndUpdated = false;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String header = br.readLine(); // Read header
            if (header != null) {
                updatedData.add(new String[]{header});
            }

            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                if (data.length >= 5) {
                    String empNum = data[0].trim();
                    String type = data[1].trim();
                    String start = data[2].trim();
                    String end = data[3].trim();
                    String status = data[4].trim();

                    // Check if this is the request to update
                    if (empNum.equals(employeeNumber) && 
                        type.equals(leaveType) && 
                        start.equals(startDate) && 
                        end.equals(endDate) && 
                        status.equals("Pending")) {
                        
                        data[4] = newStatus; // Update status
                        foundAndUpdated = true;
                    }
                }
                updatedData.add(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        
        // Only write if we found and updated a request
        if (!foundAndUpdated) {
            return false;
        }

        // Write updated data back to file using temporary file approach for safety
        File tempFile = null;
        try {
            tempFile = File.createTempFile("leaves_temp", ".csv");
            try (FileWriter fw = new FileWriter(tempFile);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter pw = new PrintWriter(bw)) {

                for (String[] row : updatedData) {
                    pw.println(String.join(",", row));
                }
            }

            // Replace original file with temp file
            File originalFile = new File(csvFile);
            if (originalFile.exists()) {
                originalFile.delete();
            }
            tempFile.renameTo(originalFile);
            return true;
            
        } catch (IOException e) {
            e.printStackTrace();
            // Clean up temp file if something went wrong
            if (tempFile != null && tempFile.exists()) {
                tempFile.delete();
            }
            return false;
        }
    }
    
    @Override
    public List<String[]> getEmployeeLeaveRequests(String employeeNumber) {
        List<String[]> leaveRequests = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(SystemIT.LEAVES_CSV))) {
            br.readLine(); // Skip header
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                if (data.length >= 5 && data[0].trim().equals(employeeNumber)) {
                    leaveRequests.add(new String[]{data[1].trim(), data[2].trim(), data[3].trim(), data[4].trim()});
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return leaveRequests;
    }
    
    @Override
    public List<String[]> getAllLeaveRequests() {
        List<String[]> leaveRequests = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(SystemIT.LEAVES_CSV))) {
            br.readLine(); // Skip header
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                if (data.length >= 5) {
                    leaveRequests.add(new String[]{data[0].trim(), data[1].trim(), data[2].trim(), data[3].trim(), data[4].trim()});
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return leaveRequests;
    }
}
