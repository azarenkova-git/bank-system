package com.example.bank.utils.others;

import java.lang.reflect.ParameterizedType;

public final class SnakeCase {
    private SnakeCase() {
    }

    // https://stackoverflow.com/questions/18707582/get-actual-type-of-generic-type-argument-on-abstract-superclass
    public static Class findTypeArgument(Object obj) {
        ParameterizedType genericSuperclass = (ParameterizedType) obj.getClass().getGenericSuperclass();
        return (Class) genericSuperclass.getActualTypeArguments()[0];
    }

    public static String getSnakeCaseClassName(Class clazz) {
        String simpleName = clazz.getSimpleName();
        StringBuilder snakeCaseName = new StringBuilder();

        for (int i = 0; i < simpleName.length(); i++) {
            char currentChar = simpleName.charAt(i);

            if (Character.isUpperCase(currentChar) && i > 0) {
                snakeCaseName.append('_');
            }

            snakeCaseName.append(Character.toLowerCase(currentChar));
        }

        return snakeCaseName.toString();
    }
}
