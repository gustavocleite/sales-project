package com.project.sales.utilitis.utils;

import org.apache.commons.lang3.StringUtils;

import static java.lang.String.format;

public class Utils {

    public static String removeMask(String value) {
        return StringUtils.isNotEmpty(value) ? value.replaceAll("[^\\dA-Za-z ]", "").replaceAll("\\s+", "") : value;
    }

    public static String addMaskCpf(String cpf) {
        if (StringUtils.isNotEmpty(cpf) && cpf.length() == 11) {
            return cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
        }
        return cpf;
    }

    public static String formatPhoneNumber(String phone) {
        if (StringUtils.isNotEmpty(phone)) {
            phone = removeMask(phone);
            return switch (phone.length()) {
                case 8 -> format(phone, "####-####");
                case 9 -> format(phone, "#####-####");
                case 10 -> format(phone, "(##) ####-####");
                case 11 -> format(phone, "(##) #####-####");
                default -> throw new IllegalArgumentException("{utils.validation.phone}" + phone);
            };
        }
        return phone;
    }
}
