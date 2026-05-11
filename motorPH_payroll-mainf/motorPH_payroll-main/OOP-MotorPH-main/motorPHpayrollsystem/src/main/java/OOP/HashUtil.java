package OOP;

/**
 * @deprecated Use Utils.HashUtil instead. This class is kept for backward compatibility.
 */
public class HashUtil {
    
    /**
     * @deprecated Use Utils.HashUtil.hashPassword() instead
     */
    @Deprecated
    public static String hashPassword(String plainPassword) {
        return Utils.HashUtil.hashPassword(plainPassword);
    }
    
    /**
     * @deprecated Use Utils.HashUtil.checkPassword() instead
     */
    @Deprecated
    public static boolean checkPassword(String plainPassword, String storedPassword) {
        return Utils.HashUtil.checkPassword(plainPassword, storedPassword);
    }
    
    /**
     * @deprecated Use Utils.HashUtil.isHashed() instead
     */
    @Deprecated
    public static boolean isHashed(String password) {
        return Utils.HashUtil.isHashed(password);
    }
}
