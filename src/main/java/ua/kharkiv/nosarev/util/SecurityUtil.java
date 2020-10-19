package ua.kharkiv.nosarev.util;

import ua.kharkiv.nosarev.entitie.enumeration.UserRole;

import java.util.*;

public final class SecurityUtil {

    private SecurityUtil() {
    }

    private static Set<String> parseSecurityPropertiesToSet(Properties properties, UserRole role) {
        String[] values = properties.getProperty(String.valueOf(role))
                .replace(System.lineSeparator(), "\\s").split("\\s");
        return new HashSet<>(Arrays.asList(values));
    }

    public static Map<UserRole, Set<String>> initializeUriMap(Properties properties) {
        Map<UserRole, Set<String>> map = new EnumMap<>(UserRole.class);
        map.put(UserRole.ADMIN, parseSecurityPropertiesToSet(properties, UserRole.ADMIN));
        map.put(UserRole.MASTER, parseSecurityPropertiesToSet(properties, UserRole.MASTER));
        map.put(UserRole.CUSTOMER, parseSecurityPropertiesToSet(properties, UserRole.CUSTOMER));
        return map;
    }
}
