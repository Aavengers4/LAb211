/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package manager;

/**
 *
 * @author ryliz
 */
public interface I_ItemList {
    public void addItem();
    public void updateItem();
    public void searchItem();
    public void deleteItem();
    public void saveFile(String filename);
    public ItemList loadFile(String filename);
    public void printAll();
}
