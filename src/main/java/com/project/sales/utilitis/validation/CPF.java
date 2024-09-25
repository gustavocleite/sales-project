package com.project.sales.utilitis.validation;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CPF.Validator.class)
@Target({ ElementType.FIELD, ElementType.METHOD , ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface CPF {

    String message() default "{validation.client.cpf}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class Validator implements ConstraintValidator<CPF, String> {
        @Override
        public void initialize(CPF constraintAnnotation) {
        }

        @Override
        public boolean isValid(String cpf, ConstraintValidatorContext context) {
            if (cpf == null || cpf.isEmpty()) {
                return false;
            }

            cpf = cpf.replaceAll("\\D", "");
            if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
                return false;
            }

            int[] pesos = {10, 9, 8, 7, 6, 5, 4, 3, 2};
            int firstCheckDigit = calculateDigit(cpf.substring(0, 9), pesos);
            int secondCheckDigit = calculateDigit(cpf.substring(0, 9) + firstCheckDigit, new int[]{11, 10, 9, 8, 7, 6, 5, 4, 3, 2});

            return cpf.equals(cpf.substring(0, 9) + firstCheckDigit + secondCheckDigit);
        }

        private int calculateDigit(String str, int[] weights) {
            int sum = 0;

            for (int i = 0; i < str.length(); i++) {
                sum += Character.getNumericValue(str.charAt(i)) * weights[i];
            }

            int rest = sum % 11;

            return (rest < 2) ? 0 : 11 - rest;
        }
    }
}
