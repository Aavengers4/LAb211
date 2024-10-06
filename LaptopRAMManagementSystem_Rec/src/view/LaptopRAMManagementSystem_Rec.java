/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package view;

import manager.ItemList;
import utils.Inputter;
import utils.Menu;

/**
 *
 * @author ryliz
 */
public class LaptopRAMManagementSystem_Rec {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int choice;
        boolean confirm;
        ///khai báo thằng item list
        ItemList itemList = new ItemList();
        itemList = itemList.loadFile("RAMModules.dat");
        String[] options = {"Add RAM item", "Search RAM item", "Update RAM Information", "Delete RAM item", "Show all RAM item",
            "Save to file", "Exit"};
        do {
            System.out.println("LAPTOP RAM MANAGEMENT SYSTEM\n");
            choice = Menu.getChoice(options);
            switch (choice) {
                case 1:
                    itemList.addItem();
                    break;
                case 2:
                    itemList.searchItem();
                    break;
                case 3:
                    itemList.updateItem();
                    break;
                case 4:
                    itemList.deleteItem();
                    break;
                case 5:
                    itemList.printAll();
                    break;
                case 6:
                    itemList.saveFile("RAMModules.dat");
                    break;  
                case 7:
                    confirm = Inputter.confirmation("Do you want to exit this system?");
                    if(confirm){
                        itemList.saveFile("RAMModules.dat");
                        break;
                    } else {
                        choice = -1;
                    }
                    break;
                default:
                    System.out.println("Invalid option, try again.");
            }

        } while (choice != 7);

    }

}

/*
GIẢI THÍCH 
Mô hình được sử dụng trong LAB211 là mô hình MVC:
Model - View - Controller
Model: là các Object class, là nơi chứa các vật thể.
VD: Product.java, Item.java, Bike.java...
Mỗi model sẽ có những thuộc tính riêng, ví dụ: ID, name, price, brand...
Controller:
Controller là bộ điều khiển một tập hợp các model
Controller sẽ có những tính năng tương tác với item:
Add, Update, Delete, PrintAll, Save + Load File, Search...
View:
View là những gì người dùng (end user) nhìn thấy. Nó là menu, là lựa chọn options, là nhập dữ liệu từ bàn phím.....

Data -> Model -> Controller -> View -> User
 */
