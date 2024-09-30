package Management;

import ClassData.Brand;
import ClassData.Category;
import ClassData.Product;
import Controller.Inputter;
import static Management.BrandManagement.brandList;
import static Management.CategoryManagement.categoryList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProductManagement {

    static List<Product> listProduct = new ArrayList<>();

    public static boolean checkUniqueId(String id) {
        for (Product p : listProduct) {
            if (p.getId().equals(id)) {
                return false;
            }
        }
        return true;
    }

    public void createProduct() {
        System.out.println("Input ID (MUST be unique): ");
        String id = Inputter.inputNonEmptyString().trim();
        while (!checkUniqueId(id)) {
            System.out.println(id + " is used, ID MUST be unique, try again! ");
            id = Inputter.inputNonEmptyString();
        }

        System.out.println("Input Name:");
        String name = Inputter.inputNonEmptyString().trim();

        // Fetch the selected Brand object
        System.out.println("Here is Brand List: ");
        for (Brand b : BrandManagement.brandList) {
            System.out.println(b.toString());
        }
        // nhap brandid
        System.out.println("Input Brand id (in Brand.txt):");
        String brandId = Inputter.inputNonEmptyString().trim();
        Brand selectedBrand = null;
        while (!BrandManagement.existBrandId(brandId)) {
            System.out.println("Must be in Brand.txt, try again! ");
            brandId = Inputter.inputNonEmptyString();
        }
        for (Brand b : brandList) {
            if (b.getId().equals(brandId)) {
                selectedBrand = b;
                break;
            }
        }

        // Fetch the selected Category object
        System.out.println("Here is Category List: ");
        for (Category c : CategoryManagement.categoryList) {
            System.out.println(c.toString());
        }
        System.out.println("Input Category id:");
        String categoryId = Inputter.inputNonEmptyString().trim();
        Category selectedCategory = null;
        while (!CategoryManagement.existCategoryId(categoryId)) {
            System.out.println("Must be in Category.txt, try again! ");
            categoryId = Inputter.inputNonEmptyString();
        }
        for (Category c : categoryList) {
            if (c.getId().equals(categoryId));
            selectedCategory = c;
            break;
        }

        // Nhập Model Year
        int modelYear;
        do {
            try {
                System.out.print("Enter Model Year: ");
                modelYear = Inputter.inputInt();
                if (modelYear < 1900 || modelYear > 2024) {
                    System.out.println("Model Year must be between 1900 - 2024, please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input for Model Year, please enter a valid number.");
                modelYear = -1;
            }
        } while (modelYear < 1900 || modelYear > 2024);

        // Nhập List Price
        double listPrice;
        do {
            try {
                System.out.print("Enter List Price: ");
                listPrice = Inputter.inputDouble();
                if (listPrice < 0) {
                    System.out.println("List Price must be a positive number, please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input for List Price, please enter a valid number.");
                listPrice = -1;
            }
        } while (listPrice < 0);

        Product newProduct = new Product(id, name, selectedBrand, selectedCategory, modelYear, listPrice);
        listProduct.add(newProduct);
        System.out.println("Create Success!!");
    }

    // Function2: SEARCH BY NAME
    public void searchProductByName() {
        System.out.println("Input Product Name to search");
        String name = Inputter.inputNonEmptyString();
        List<Product> result = new ArrayList<>();
        for (Product p : listProduct) {
            if (p.getName().contains(name)) {
                result.add(p);
            }
        }
        if (result.size() == 0) {
            System.out.println("Have no any product");
        } else {
            result.sort(Comparator.comparingInt(Product::getModelYear)); // Sort by model year
            for (Product product : result) {
                System.out.println(product.toString());
            }
        }
    }

    // Function 3: UPDATE BY ID
    public void updateProduct() {
        System.out.println("Input Product ID you want to update:");
        String id = Inputter.inputNonEmptyString();

        Product productToUpdate = null;

        for (Product p : listProduct) {
            if (p.getId().equalsIgnoreCase(id)) {
                productToUpdate = p;
                break;
            }
        }

        if (productToUpdate == null) {
            System.out.println("Product with ID " + id + " does not exist.");
            return;
        }
        // Update Name
        System.out.println("Update Name");
        String newName = Inputter.inputNonEmptyString();

        productToUpdate.setName(newName);

        // Update Brand
        System.out.println("Update Brand ID:");
        String newBrandId = Inputter.inputString();
        if (!newBrandId.isEmpty()) { //nhập ID k rỗng thì triển tiếp 
            Brand newBrand = null; // tạo 1 cái new brand
            for (Brand b : brandList) {
                if (b.getId().equals(newBrandId)) {
                    newBrand = b;
                }
            }
            if (newBrand != null) {
                productToUpdate.setBrand(newBrand);
            } else {
                System.out.println("Invalid Brand ID, update failed.");
            }
        } else {
            System.out.println("Not update Brand ID");
        }

        // Update Category
        System.out.println("Update Category ID:");
        String newCategoryId = Inputter.inputString();
        if (!newCategoryId.isEmpty()) {
            Category newCate = null;

            for (Category c : categoryList) {
                if (c.getId().equals(newCategoryId));
                newCate = c;
            }
            if (newCate != null) {
                productToUpdate.setCategory(newCate);
            } else {
                System.out.println("Invalid Category ID, update failed.");
            }
        } else {
            System.out.println("Not update Category ID");
        }

        // Update Model Year
        System.out.println("Update Model Year:");
        int newModelYear = Inputter.inputInt();
        while (newModelYear < 1990 || newModelYear > 2024) {
            System.out.println("Invalid year! Please enter a model year between 1990 and 2024:");
            newModelYear = Inputter.inputInt();
        }
        productToUpdate.setModelYear(newModelYear);

        // Update Price
        System.out.println("Update Price:");
        double newPrice = Inputter.inputDouble();
        while (newPrice <= 0) {
            System.out.println("Invalid price, please enter a price >0");
            newPrice = Inputter.inputDouble();
        }
        productToUpdate.setPrice(newPrice);

        System.out.println("Update Success!!");
    }

    // Function 4: DELETE BY ID
    public boolean deleteProduct() {
        System.out.println("Input Product ID you want to delete:");
        String id = Inputter.inputNonEmptyString();
        boolean removed = listProduct.removeIf(p -> p.getId().equals(id));
        if (removed) {
            System.out.println("Product with ID " + id + " has been deleted.");
            return true;
        } else {
            System.out.println("Product with ID " + id + " does not exist.");
        }
        return false;
    }

    // Function 5: SAVE PRODUCT LIST TO FILE
    public void saveToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Product p : listProduct) {
                writer.write(String.format("%s, %s, %s, %s, %d, %.2f%n",
                        p.getId(), p.getName(), p.getBrand().getId(),
                        p.getCategory().getId(), p.getModelYear(), p.getPrice()));
            }
            System.out.println("Product list saved to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Function 6: DISPLAY ALL PRODUCTS
    public void printList() {
        fileManagement.readFromFileToProductList("Product.txt");
        if (listProduct.isEmpty()) {
            System.out.println("List is Empty");
        } else {
            System.out.println("List of Products:");
            for (Product p : listProduct) {
                System.out.println(p.toString());
            }
        }
    }
}
