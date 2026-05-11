package OOP;

/**
 * Utility class for employee-related operations.
 * Contains helper methods that don't belong in specific layers.
 */
public class EmployeeUtils {
    
    /**
     * Safely parses a string to double value.
     * Handles null, empty strings, and formatting issues.
     * 
     * @param value The string value to parse
     * @return The parsed double value, or 0.0 if parsing fails
     */
    public static double parseDoubleSafe(String value) {
        if (value == null || value.trim().isEmpty()) return 0.0;
        try {
            // Remove commas and any whitespace, then parse
            String cleanValue = value.replace(",", "").trim();
            return Double.parseDouble(cleanValue);
        } catch (NumberFormatException e) {
            // Log the error for debugging but return 0.0 to prevent crash
            System.err.println("Failed to parse double value: '" + value + "'");
            return 0.0;
        }
    }
    
    /**
     * Formats a monetary amount as currency with proper separators.
     * 
     * @param amount The amount to format
     * @return Formatted currency string with peso symbol and comma separators
     */
    public static String formatCurrency(double amount) {
        return String.format("₱%,.2f", amount);
    }
    
    /**
     * Validates if a string is not null or empty after trimming.
     * 
     * @param value The string to validate
     * @return true if the string has content, false otherwise
     */
    public static boolean isValidString(String value) {
        return value != null && !value.trim().isEmpty();
    }
    
    /**
     * Trims and normalizes a string value.
     * 
     * @param value The string to normalize
     * @return The trimmed string, or empty string if null
     */
    public static String normalizeString(String value) {
        return (value != null) ? value.trim() : "";
    }
}