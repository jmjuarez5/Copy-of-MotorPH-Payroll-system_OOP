package Utils;

import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mindrot.jbcrypt.BCrypt;

public final class HashUtil {
    private static final Logger LOGGER = Logger.getLogger(HashUtil.class.getName());

    private HashUtil() {
        // utility class
    }

    public static String hashPassword(String plainPassword) {
        if (plainPassword == null) {
            throw new IllegalArgumentException("Password cannot be null");
        }
        
        try {
            // Use BCrypt for password hashing
            return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
        } catch (Exception e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    public static boolean isHashed(String storedPassword) {
        if (storedPassword == null) {
            return false;
        }
        // Check for BCrypt format (starts with $2a$, $2b$, or $2y$)
        if (storedPassword.startsWith("$2a$") || 
            storedPassword.startsWith("$2b$") || 
            storedPassword.startsWith("$2y$")) {
            return true;
        }
        return false;
    }

    public static boolean checkPassword(String plainPassword, String storedPassword) {
        System.out.println("[LOGIN STAGE 5] Password verification details:");
        System.out.println("   Input password: '" + plainPassword + "'");
        System.out.println("   Stored password: '" + storedPassword + "'");
        System.out.println("   Is stored password hashed: " + isHashed(storedPassword));

        if (plainPassword == null || storedPassword == null || storedPassword.isEmpty()) {
            System.out.println("[LOGIN STAGE 5] Null or empty password - returning false");
            return false;
        }

        if (isHashed(storedPassword)) {
            System.out.println("[LOGIN STAGE 5] Stored password is hashed - performing BCrypt verification");
            try {
                boolean result = BCrypt.checkpw(plainPassword, storedPassword);
                System.out.println("   BCrypt verification result: " + result);
                return result;
            } catch (Exception e) {
                LOGGER.log(Level.WARNING, "Error verifying hashed password", e);
                System.out.println("[LOGIN STAGE 5] Exception during hash verification: " + e.getMessage());
                return false;
            }
        }

        // Legacy plaintext fallback: use constant-time comparison
        System.out.println("[LOGIN STAGE 5] Legacy plaintext password detected - using constant-time comparison");
        LOGGER.log(Level.WARNING, "Legacy plaintext password detected; consider migrating to hashed passwords.");
        boolean result = constantTimeEquals(plainPassword.getBytes(StandardCharsets.UTF_8),
                                          storedPassword.getBytes(StandardCharsets.UTF_8));
        System.out.println("   Plaintext comparison result: " + result);
        return result;
    }

    private static boolean constantTimeEquals(byte[] a, byte[] b) {
        if (a == null || b == null) return false;
        if (a.length != b.length) {
            // still iterate to keep timing similar
            int result = 0;
            int max = Math.max(a.length, b.length);
            for (int i = 0; i < max; i++) {
                byte xa = i < a.length ? a[i] : 0;
                byte xb = i < b.length ? b[i] : 0;
                result |= xa ^ xb;
            }
            return result == 0;
        }
        int res = 0;
        for (int i = 0; i < a.length; i++) {
            res |= a[i] ^ b[i];
        }
        return res == 0;
    }
}
