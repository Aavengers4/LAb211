package Controller;


import java.util.Date;
import java.util.Scanner;

public class Inputter {

    public static  Scanner sc = new Scanner(System.in);// sc là một thể hiện của lớp Scanner mà có thể được truy cập
    //từ bất kỳ đâu trong lớp mà không cần khởi tạo một đối tượng mới.

    public static int inputInt() { // nhập số nguyên hợp lệ 
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number, try again");
            return inputInt(); // yêu cầu nhập lại 
        }
    }

    public static double inputDouble() { // nhập số double hợp lệ 
        try {
            return Double.parseDouble(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number, try again");
            return inputDouble();
        }
    }
    public static String inputString() { // Nhập string đầu vào hợp lệ 
        try {
            return sc.nextLine();
        } catch (Exception e) {
            System.out.println("Invalid string, try again");
            return inputString();
        }
    }

    public static String inputNonEmptyString() { // nhập chuỗi không rỗng 
        try {
            String str = inputString();
            if (str.isEmpty()) {
                throw new EmptyStringException();
            }
            return str;
        } catch (EmptyStringException e) {
            System.out.println("String is empty, try again");
            return inputNonEmptyString();
        }
    }

    public static boolean inputBoolean() { // Kiểm tra true false của 
        String input = inputNonEmptyString();
        if (!input.equalsIgnoreCase("true") && !input.equalsIgnoreCase("false")
            && !input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no")) {
            System.out.println("Please enter true/false or yes/no");
            return inputBoolean(); // nếu không phải trưe false or yes no thì nhập lại 
        } else {
            return input.equalsIgnoreCase("yes")||input.equalsIgnoreCase("true");
        }
        // Khi nhập false thì nó sẽ không thuộc cả if else luôn nên sẽc nhập lại 
    }



    

    
}




























































class NumberOutOfRangeException extends Exception {

    public NumberOutOfRangeException() {
    }
}

class EmptyStringException extends Exception {

    public EmptyStringException() {
    }
}
