package game.farming.validators;

import game.farming.domain.Item;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ItemValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Item.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Item item = (Item) target;
        if(item.getName() == null || item.getName().isEmpty()) {
            errors.rejectValue("name", "required");
        }
        if(item.getDescription() == null || item.getDescription().isEmpty()) {
            errors.rejectValue("description", "required");
        }
        if(item.getPrice() <= 0 ) {
            errors.rejectValue("price", "min");
        }
        if(item.getPrice() > 1000000){
            errors.rejectValue("price", "max",new Object[]{"1,000,000"},null);
        }


    }
}
