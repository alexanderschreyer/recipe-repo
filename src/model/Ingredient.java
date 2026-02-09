package src.model;

public class Ingredient {
    private String id;
    private String name;
    private String unit;

    private float quantity;

    public Ingredient(String id, String name, String unit, float quantity) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public float getQuantity() {
        return quantity;
    }
}
