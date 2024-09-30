/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Management;

import ClassData.Brand;
import ClassData.Category;
import ClassData.Product;
import static Management.BrandManagement.brandList;
import static Management.CategoryManagement.categoryList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author ASUS
 */
public class fileManagement {

    public static void readFromFileToProductList(String fileName) {
        File file = new File(fileName);
        try (FileReader fileReader = new FileReader(file); // Tạo FileReader từ File
                BufferedReader reader = new BufferedReader(fileReader)) { // readfile
            String line; // Đọc từng dòng 
            while ((line = reader.readLine()) != null) {
                String[] str = line.split(", ");
                if (str.length == 6) {
                    String id = str[0];
                    String name = str[1];
                    Brand brand = null;
                    for (Brand b : brandList) {
                        if (b.getId().equals(str[2]));
                        brand = b;
                        break;
                    }
                    Category category = null;
                    for (Category c : categoryList) {
                        if (c.getId().equals(str[3]));
                        category = c;
                        break;
                    }
                    int modelYear = Integer.parseInt(str[4]);
                    double price = Double.parseDouble(str[5]);
                    // Kiểm tra thằng brand khác null, category khác null hay k và id có duy nhất k 
                    if (brand != null && category != null && ProductManagement.checkUniqueId(id)) {
                        Product p = new Product(id, name, brand, category, modelYear, price);
                        ProductManagement.listProduct.add(p);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
