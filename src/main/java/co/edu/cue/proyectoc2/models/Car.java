package co.edu.cue.proyectoc2.models;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@SessionScoped
@Named
public class Car implements Serializable {
    private List<ItemCar> items;

    public Car() {
        this.items = new ArrayList<>();
    }

    public void addItemCarro(ItemCar itemCar) {
        if (items.contains(itemCar)) {
            Optional<ItemCar> optionalItemCarro = items.stream()
                    .filter(i -> i.equals(itemCar))
                    .findAny();
            if (optionalItemCarro.isPresent()) {
                ItemCar i = optionalItemCarro.get();
                i.setCantidad(i.getCantidad()+1);
            }
        } else {
            this.items.add(itemCar);
        }
    }
    public List<ItemCar> getItems() {

        return items;
    }

    public int getTotal() {
        return items.stream().mapToInt(ItemCar::getTotal).sum();
    }
    public void removeProductos(List<String> productIds) {
        if (productIds != null) {
            productIds.forEach(this::removeProducto);
        }
    }

    public void removeProducto(String productId) {
        Optional<ItemCar> product = findProduct(productId);
        product.ifPresent(itemCar -> items.remove(itemCar));
    }

    public void updateCantidad(String productId, int cantidad) {
        Optional<ItemCar> product = findProduct(productId);
        product.ifPresent(itemCar -> itemCar.setCantidad(cantidad));
    }

    private Optional<ItemCar> findProduct(String productId) {
        return  items.stream()
                .filter(itemCar -> productId.equals(Long.toString(itemCar.getProduct().id())))
                .findAny();
    }
}
