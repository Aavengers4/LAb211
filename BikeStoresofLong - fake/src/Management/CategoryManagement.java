package Management;

import ClassData.Category;
import static Management.BrandManagement.brandList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CategoryManagement {

    static List<Category> categoryList = new ArrayList<>();

    public static boolean existCategoryId(String categoryId) {
        for (Category c : categoryList) {
            if (c.getId().equals(categoryId)) {
                return true;
            }
        }
        return false;
    }

    public void loadCategoryFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
         

            String line;
            while ((line = reader.readLine()) != null) {
                String[] str = line.split(", ");
                if (str.length == 2) {
                    String id = str[0];
                    String name = str[1];
                    Category c = new Category(id, name);
                    categoryList.add(c);
                } else {
                    System.out.println("Invalid ");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void loadCategoryToFile(String fileName) { // đưa dữ liệu lên file 
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
//            for (Category c : categoryList) {
//                writer.write(c.toString());
//                writer.newLine();
//            }
//            System.out.println("Save successfully");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

}
