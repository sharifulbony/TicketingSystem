import java.util.Scanner;

public class Question01 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Eligibility checking :");
        System.out.println("How Many marks have you got in math?");
        double math = input.nextDouble();
        while (math < 0 || math > 100) {
            System.out.println("Sorry!! Invalid Mark Range for math");
            System.out.println("How Many marks have you got in math?");
             math = input.nextDouble();

        }
        System.out.println("How Many marks have you got in physics?");
        double physics = input.nextDouble();
        while (physics < 0 || physics > 100) {
            System.out.println("Sorry!! Invalid Mark Range for physics");
            System.out.println("How Many marks have you got in physics?");
            physics = input.nextDouble();
        }
        System.out.println("How Many marks have you got in chemistry?");
        double chemistry = input.nextDouble();
        while (chemistry < 0 || chemistry > 100) {
            System.out.println("Sorry!! Invalid Mark Range for chemistry");
            System.out.println("How Many marks have you got in chemistry?");
            chemistry = input.nextDouble();
        }
        double totalMarks;
        totalMarks = math + physics + chemistry;
        double sumOfMathPhysics;
        sumOfMathPhysics = math + physics;

        if (math >= 65 && physics >= 55 && chemistry >= 50 && totalMarks >= 180) {
            System.out.println("You are eligible to take admission");
        } else if (sumOfMathPhysics >= 140) {
            System.out.println("You are eligible to take admission");
        } else
            System.out.println("Sorry!! You Don't have enough mark to attend");
    }

    //================================================================================


}
