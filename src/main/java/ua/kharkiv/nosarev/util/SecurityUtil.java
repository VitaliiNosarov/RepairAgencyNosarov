package ua.kharkiv.nosarev.util;

import ua.kharkiv.nosarev.entitie.enumeration.UserRole;

import java.util.*;

public class SecurityUtil {

    private static Set<String> parseSecurityPropertiesToSet(Properties properties, UserRole role) {
        String[] values = properties.getProperty(String.valueOf(role))
                .replace(System.lineSeparator(),"\\s").split("\\s");
        Set<String> set = new HashSet<>(Arrays.asList(values));
        return set;
    }

    public static Map<UserRole, Set<String>> initializeUriMap(Properties properties) {
        Map<UserRole, Set<String>> map = new HashMap();
        map.put(UserRole.ADMIN, parseSecurityPropertiesToSet(properties, UserRole.ADMIN));
        map.put(UserRole.MASTER, parseSecurityPropertiesToSet(properties, UserRole.MASTER));
        map.put(UserRole.CUSTOMER, parseSecurityPropertiesToSet(properties, UserRole.CUSTOMER));
        return map;
    }
}
