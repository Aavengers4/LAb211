/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manager;

import entities.Item;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.YearMonth;
import java.util.ArrayList;
import utils.Inputter;
import utils.Menu;

/**
 *
 * @author ryliz
 */
public class ItemList extends ArrayList<Item> implements I_ItemList {

    private String generateCode(String type) {
        boolean isExist;
        String prefix = "RAM";
        String code;
        int index = 1;
        do {
            code = prefix + type + "_" + index;
            isExist = false;
            //sử dụng foreach => cú pháp là: for(DataType name : List)
            for (Item item : this) {
                if (item.getCode().equalsIgnoreCase(code)) {
                    index++;
                    isExist = true;
                    break;
                }

            }
        } while (isExist);
        return code;
        //ngoai ra xài shortcut là alt + shift + f;
    }

    @Override
    public void addItem() {
        boolean confirm;
        String code, type, brand;
        int bus, quantity;
        YearMonth productionDate;
        do {
            type = Inputter.inputNonBlankStr("Enter RAM's type: ");
            type = type.toUpperCase(); //ddr5 -> DDR5
            bus = Inputter.inputIntLimit("Enter RAM's bus speed (in MHz): ", 0, 10000);
            brand = Inputter.inputNonBlankStr("Enter RAM's brand: ");
            quantity = Inputter.inputIntLimit("Enter product's quantity: ", 0, Integer.MAX_VALUE);
            productionDate = Inputter.inputDate("Enter production date: ");

            confirm = Inputter.confirmation("Do you want to add this RAM item?");
            if (confirm) {
                Item item = new Item(generateCode(type), type, brand, bus, quantity, productionDate, confirm);
                this.add(item);
                System.out.println("RAM item added successfully.");

            } else {
                System.out.println("Action discarded.");
            }
            confirm = Inputter.confirmation("Do you want to add another product?");
            if (!confirm) {
                System.out.println("Returning to menu...");
            }
        } while (confirm);
    }

    //ram, type ddr5, cái t3 trong list => code = RAMDDR5_3
    @Override
    public void searchItem() {
        if (this.isEmpty()) {
            System.out.println("RAM list is currently empty. Returning to main menu...");
            return;
        }
        String keyword;
        int value;
        boolean confirm, isExist;
        do {
            isExist = false;
            for (Item item : this) {
                if (item.isActive()) {
                    isExist = true;
                }
            }
            if (!isExist) {
                System.out.println("RAM list is currently empty. Returning to main menu...");
                return;
            }
            String[] options = {"Search by Type", "Search by Bus", "Search by Brand", "Return"};
            int choice;
            do {
                choice = Menu.getChoice(options);
                switch (choice) {
                    case 1:
                        //case 1: sẽ ktra bằng item.getType
                        keyword = Inputter.inputNonBlankStr("Enter RAM's type to search: ");
                        for (Item item : this) {
                            if (item.getType().equalsIgnoreCase(keyword)) {
                                System.out.println(item);
                            }
                        }
                        break;
                    case 2:
                        //case 2: sẽ ktra bằng item.getBus
                        value = Inputter.inputIntLimit("Enter RAM's bus speed (in MHz): ", 0, 10000);
                        for (Item item : this) {
                            if (item.getBus() == value) {
                                System.out.println(item);
                            }
                        }
                        break;
                    case 3:
                        //case 3: sẽ ktra bằng item.getBrand
                        keyword = Inputter.inputNonBlankStr("Enter RAM's brand to search: ");
                        for (Item item : this) {
                            if (item.getBrand().equalsIgnoreCase(keyword)) {
                                System.out.println(item);
                            }
                        }
                        break;
                    case 4:
                        System.out.println("Returning...");
                        break;
                }
            } while (choice != 4);
            confirm = Inputter.confirmation("Do you want to perform another search?");
            if (!confirm) {
                System.out.println("Returning to main menu...");
            }
        } while (confirm);

    }

    @Override
    public void updateItem() {
        if (this.isEmpty()) {
            System.out.println("RAM list is currently empty. Returning to main menu...");
            return;
        }
        boolean confirm, isFound;
        Item selectedItem = new Item();
        String code, type, brand;
        int bus, quantity;
        YearMonth productionDate;
        final String UPDATE_PREFIX = " (leave blank to retain old data): ";
        do {
            isFound = false;
            for (Item item : this) {
                if (item.isActive()) {
                    isFound = true;
                }
            }
            if (!isFound) {
                System.out.println("RAM list is currently empty. Returning to main menu...");
                return;
            }
            printAll();
            code = Inputter.inputNonBlankStr("Enter RAM code to update: ");
            for (Item item : this) {
                if (item.getCode().equalsIgnoreCase(code) && item.isActive()) {
                    selectedItem = item;
                    isFound = true;
                    break;
                }
            }
            if (!isFound) {
                System.out.println("Item code does not exist.");
            } else {
                //phần function update chính thức sẽ nằm trong này
                //NẾU NHẬP DATA MỚI THÌ LẤY DATA MỚI, KHÔNG NHẬP GÌ (ĐỂ TRỐNG) THÌ GIỮ DATA CŨ

                type = Inputter.inputStr("Enter new RAM's type" + UPDATE_PREFIX);
                type = type.toUpperCase();
                if (type.isEmpty()) {
                    type = selectedItem.getType();
                }

                brand = Inputter.inputStr("Enter new RAM's brand: " + UPDATE_PREFIX);
                if (brand.isEmpty()) {
                    brand = selectedItem.getBrand();
                }

                bus = Inputter.inputIntOrBlank("Enter new RAM's bus speed: " + UPDATE_PREFIX, 0, 10000);
                if (bus == -1) {
                    bus = selectedItem.getBus();
                }
                quantity = Inputter.inputIntOrBlank("Enter new RAM's quantity: " + UPDATE_PREFIX, 0, Integer.MAX_VALUE);
                if (quantity == -1) {
                    quantity = selectedItem.getQuantity();
                }
                productionDate = Inputter.inputDate("Enter new RAM's production date: " + UPDATE_PREFIX);
                if (productionDate == null) {
                    productionDate = selectedItem.getProductionDate();
                }

                confirm = Inputter.confirmation("Do you want to update this item?");
                if (confirm) {
                    selectedItem.setType(type);
                    selectedItem.setBrand(brand);
                    selectedItem.setBus(bus);
                    selectedItem.setQuantity(quantity);
                    selectedItem.setProductionDate(productionDate);
                    System.out.println("Item updated successfully.");
                } else {
                    System.out.println("Action discarded.");
                }

            }
            confirm = Inputter.confirmation("Do you want to update another item?");
            if (!confirm) {
                System.out.println("Returning to main menu...");
            }
        } while (confirm);
    }

    @Override
    public void deleteItem() {
        if (this.isEmpty()) {
            System.out.println("RAM list is currently empty. Returning to main menu...");
            return;
        }
        boolean confirm, isExist;
        String code;
        do {
            isExist = false;
            for (Item item : this) {
                if (item.isActive()) {
                    isExist = true;
                }
            }
            if (!isExist) {
                System.out.println("RAM list is currently empty. Returning to main menu...");
                return;
            }
            printAll();
            isExist = false;
            code = Inputter.inputNonBlankStr("Enter RAM's code to delete: ");
            for (Item item : this) {
                if (item.getCode().equalsIgnoreCase(code) && item.isActive()) {
                    isExist = true;
                    confirm = Inputter.confirmation("Do you want to delete this item?");
                    if (confirm) {
                        item.setActive(false);
                        System.out.println("Item deleted successfully");
                        break;
                    } else {
                        System.out.println("Action discarded.");
                        break;
                    }
                }
            }
            if (!isExist) {
                System.out.println("This item code does not exist.");
            }
            confirm = Inputter.confirmation("Do you want to delete another item?");
            if (!confirm) {
                System.out.println("Returning to main menu...");
            }
        } while (confirm);
    }
    //thao tác với file:
    //trong bài này sẽ sử dụng kiểu đọc ghi file là nhị phân (binary)
    //khác với đọc file text, kiểu đọc file này không cần verify quá nhiều

    //những thư viện cần sử dụng là: File Input/Output Stream
    @Override
    public void saveFile(String filename) {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            //try with resource
            oos.writeObject(this);
            System.out.println("Save file successfully.");

        } catch (Exception e) {
            System.out.println("saveFile Error: " + e.toString());
        }
    }

    @Override
    public ItemList loadFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            ItemList iList = (ItemList) ois.readObject();
            System.out.println("Load file successfully.");
            return iList;
        } catch (Exception e) {
            System.out.println("loadFile Error: " + e.toString());
            return null;
        }
    }

    @Override
    public void printAll() {
        if (this.isEmpty()) {
            System.out.println("RAM list is currently empty. Returning to main menu...");
            return;
        }
        int count = 0;
        boolean isFound = false;
        for (Item item : this) {
            if (item.isActive()) {
                isFound = true;
                count++;
                System.out.println(item);
            }

        }
        if (!isFound) {
            System.out.println("RAM list is currently empty. Returning to main menu...");
        } else {
            System.out.println("Total: " + count + " item(s).");
        }

    }

}
