package Controller;

import Management.ProductManagement;

public class Menu {

     ProductManagement pm = new ProductManagement();

    public void showMenu() {
        System.out.println("========== PRODUCT MANAGEMENT SYSTEM ==========");
        System.out.println("1. Create a Product");
        System.out.println("2. Search Products by name");
        System.out.println("3. Update Product information");
        System.out.println("4. Delete a Product by ID ");
        System.out.println("5. Save Product List to file");
        System.out.println("6. Display all Products");
        System.out.println("0. Exit");
        System.out.println("Please choose an option: ");
    }

    public void menuLoop() {

        int choice;
        boolean exit = false;

        
        do {
               showMenu();
            choice = Inputter.inputInt();
         

            switch (choice) {
                case 1:
                    System.out.println("1. Create a Product: ");
                    pm.createProduct();
                    System.out.println("Create Sucsess!!");
                    break;
                case 2:
                    System.out.println("2. Search Products by name");
                    pm.searchProductByName();
                    System.out.println("Suscess!!");
                    break;
                case 3:
                    System.out.println("3. Update Product information");
                    pm.updateProduct();
        
                    break;
                case 4:
                    System.out.println("4. Delete a Product by ID ");
                    if (pm.deleteProduct()){
                        System.out.println("Delete successfully!");
                    } else {
                        System.out.println("Fail to delete");
                    }

                    break;
                case 5:
                    System.out.println("5. Save Product List to file");
                    pm.saveToFile("Product.txt");

                    break;

                case 6:
                    System.out.println("6. Display all Products");
                    pm.printList();

                    break;
                case 0:
                    System.out.println("Exit successfully!");  
                    exit = true;
                    
                    break;
                default:
                    System.out.println("Invalid option. Try again!");
                    menuLoop();
                    break;
            }
            if (!exit) {
                System.out.println("Do you wanna to go back menu:(yes or no) ????");
                if (!Inputter.inputBoolean()) {
                    System.out.println("Exit Sucsessfully !");
                    exit = true;
                }
            }

        } while (!exit);
    }
}
