package guru.bonacci._1985.tringress.trs.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = FieldsValueDifferentValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldsValueDifferent {

  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
  
  String message() default "Fields should be different!";

  String field1();
  String field2();
}