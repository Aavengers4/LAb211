
package Management;
import ClassData.Brand;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class BrandManagement{
    static List<Brand> brandList = new ArrayList<>();
    
    public static boolean existBrandId(String brandId){
        for (Brand b : brandList) {
            if(b.getId().equals(brandId))
                return true;
        }
        return false;
    }

    public void loadBrandFromFile(String fileName) { // đưa dữ liệu từ file về 
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
       
            while ((line = reader.readLine()) != null) { // khi nào mà hết dòng thì thôi 
                String str[] = line.split(", "); // tạo mảng string để tách kí tự 
                if (str.length == 3) {
                    String id = str[0];
                    String name = str[1];
                    String country = str[2];
                    Brand b = new Brand(id, name, country);
                    brandList.add(b);
                } else {
                    System.out.println("Invalid format in line: " + line);
                }
            }  
        } catch (IOException e) { // file k tồn tại or lỗi 
            e.printStackTrace(); // in ra chi tiết lỗi đó 
    }
    }
    
 
    
    
    
    
    
    
    
    
    
//    public void loadBrandToFile(String fileName) {
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
//            for (Brand b : brandList) {
//                writer.write(b.toString());
//                writer.newLine();
//            }
//            System.out.println("Save successfully");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

}
