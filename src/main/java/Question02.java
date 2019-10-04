import java.util.Scanner;

public class Question02 {

    public static boolean isEven(int num) {
        if (num % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("Please Input an Integer number");
        int num =input.nextInt();

        if(isEven(num)){
            System.out.println("The Number is Even");
        }else {
            System.out.println("The Number is Odd");
        }


    }
}
