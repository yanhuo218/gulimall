package com.yanhuo.common.validator.constraints;


import com.yanhuo.common.validator.vailds.ListValue;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

public class ListValueConstraintValidator implements ConstraintValidator<ListValue, Integer> {

    private Set<Integer> values = new HashSet<>();

    @Override
    public void initialize(ListValue constraintAnnotation) {
        for (Integer value : constraintAnnotation.values()) {
            if (null != value) {
                values.add(value);
            }
        }
    }

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        return values.contains(integer);
    }
}
