/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
import java.time.YearMonth;

/**
 *
 * @author ryliz
 */
public class Item implements Serializable{
    private String code, type, brand;
    private int bus, quantity;
    private YearMonth productionDate;
    private boolean active;

    public Item() {
    }
    
    
    
    //phím tắt tạo code: alt + insert
    
    //mot hai ba => motHaiBa
    //hằng số => từ khoá final (ie: private final int ...);
    //quy tắc viết tên hằng số là viết hoa tất cả, các từ phân cách bằng _
    //vd: mot hai ba => MOT_HAI_BA

    
    //Item item = new Item();
    
    //Item.txt
    //mỗi object, mỗi phần tử phải được lưu dưới dạng
    
    //RAMDDR5_1, DDR5, ROG, 5600, 100, 12/2023, true 
    
    //ngoài ra thì có cách xài file nhị phần
    
    //file nhị phân được viết, được tạo ra dưới đuôi .dat
    //01010101011100001
    
    public Item(String code, String type, String brand, int bus, int quantity, YearMonth productionDate, boolean active) {
        this.code = code;
        this.type = type;
        this.brand = brand;
        this.bus = bus;
        this.quantity = quantity;
        this.productionDate = productionDate;
        this.active = active;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getBus() {
        return bus;
    }

    public void setBus(int bus) {
        this.bus = bus;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public YearMonth getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(YearMonth productionDate) {
        this.productionDate = productionDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Item ID: "+code + " | Type: "+type + " | Bus speed: " + bus +"MHz | Brand: "+brand + " | Quantity: "+quantity
                + " | Release date: "+productionDate+ " |";
    }
    
    //khi mình 1 vật thể (item) này ra, mình sẽ viêt là System.out.print(item.toString());
    
    
}
