package com.challenge.desafio;

import com.challenge.annotation.Somar;
import com.challenge.annotation.Subtrair;
import com.challenge.interfaces.Calculavel;

import java.lang.annotation.Annotation;
import java.math.BigDecimal;
import java.util.Arrays;

public class CalculadorDeClasses implements Calculavel {

    @Override
    public BigDecimal somar(Object object) {
        return calcular(object, Somar.class);
    }

    @Override
    public BigDecimal subtrair(Object object) {
        return calcular(object, Subtrair.class);
    }

    @Override
    public BigDecimal totalizar(Object object) {
        return somar(object).subtract(subtrair(object));
    }

    private BigDecimal calcular(Object object, Class<? extends Annotation> annotation) {
        return Arrays.stream(object.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(annotation))
                .map(field -> {
                    try {
                        field.setAccessible(true);
                        return (BigDecimal) field.get(object);
                    } catch (IllegalAccessException e) {
                        return BigDecimal.ZERO;
                    }
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }
}
