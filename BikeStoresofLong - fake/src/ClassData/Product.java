package ClassData;

public class Product {
    private String id;
    private String name;
    private Brand brand;  // Đối tượng Brand
    private Category category;  // Đối tượng Category
    private int modelYear;
    private double price;

    public Product(String id, String name, Brand brand, Category category, int modelYear, double price) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.category = category;
        this.modelYear = modelYear;
        this.price = price;
    }

    // Getters và Setters cho Brand và Category
    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public int getModelYear() {
        return modelYear;
    }

    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {//Kiểm tra thằng brand có null hay không , k null thì nó lấy ID còn null thì in ra No brand 
        String brandId = (brand != null) ? brand.getId() : "No Brand"; 
        String categoryId = (category != null) ? category.getId() : "No Category";
        return String.format("ID: %s, Name: %s, Brand ID: %s, Category ID: %s, Model Year: %d, Price: %.2f",
                             id, name, brandId, categoryId, modelYear, price);
    }
}
