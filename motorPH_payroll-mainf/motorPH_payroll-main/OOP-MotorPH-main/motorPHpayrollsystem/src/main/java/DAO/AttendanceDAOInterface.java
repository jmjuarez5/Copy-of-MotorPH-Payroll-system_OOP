package DAO;

public interface AttendanceDAOInterface {
    String[][] loadAttendance() throws Exception;
    String[][] findAttendanceByEmployee(String employeeId);

    void logAttendance(String employeeId, String date, String timeIn, String timeOut) throws Exception;
    void saveToCSV() throws Exception;
}