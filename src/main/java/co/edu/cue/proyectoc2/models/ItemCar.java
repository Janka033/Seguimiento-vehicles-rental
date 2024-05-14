package co.edu.cue.proyectoc2.models;

import co.edu.cue.proyectoc2.mapping.dto.ProductDto;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ItemCar {
    private int cantidad;
    private ProductDto product;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass())
            return false;
        ItemCar itemCar = (ItemCar) object;
        return Objects.equals(product.id(), itemCar.product.id()) &&
                Objects.equals(product.model(), itemCar.product.model());
    }
    public int getTotal(){
        return cantidad * product.price();
    }

}
