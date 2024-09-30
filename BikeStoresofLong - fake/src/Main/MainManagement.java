package Main;

import Management.BrandManagement;
import Management.CategoryManagement;
import Management.ProductManagement;
import Controller.Menu;

public class MainManagement {

    public static void main(String[] args) {
        BrandManagement brM = new BrandManagement();
        brM.loadBrandFromFile("Brand.txt");

        CategoryManagement caM = new CategoryManagement();
        caM.loadCategoryFromFile("Category.txt");

//        ProductManagement prM = new ProductManagement(); in danh sách ở trươc khi menu 
//        prM.printList();

        Menu menu = new Menu();
        menu.menuLoop();
    }
}

