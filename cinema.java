package cinema;
import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        // Write your code here
        Scanner scanner = new Scanner(System.in);

        // Ask for rows and cols
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int cols = scanner.nextInt();

        // Create array
        String[][] array = new String[rows][cols];
        
        for (int i = 0; i < rows; i ++){
            for (int j = 0; j < cols; j++) {
                array[i][j] = "S";
            }
        }

        //Show menu
        int temp = 1;
        do {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            temp = scanner.nextInt(); 
            switch (temp) {
                case 1:
                    printSeats(array, rows, cols);
                    break;
                case 2:
                    buyTickets(array, rows, cols);
                    break;
                case 3:
                    showStats(array, rows, cols);
                    break;
                case 0:
                    break;
                default:
            }
        } while (temp != 0);
        
    }

    public static void showStats(String[][] array, int rows, int cols) {
        
        int seats = rows * cols;
        int half_1 = rows / 2;
        int n_tickets = 0;
        int current_income = 0;
        int income;

        // Number of purchased tickets
        for (int i = 0; i < rows; i ++){
            for (int j = 0; j < cols; j++) {
                if (array[i][j]=="B") {
                    n_tickets += 1;
                }
            }
        }

        // Percentage of purchased tickets as string
        float percentage = (float)n_tickets / (float)seats * 100;
        //String s_percentage = String.format("%.2f%",percentage);

        // Current income
        for (int i = 0; i < rows; i ++){
            for (int j = 0; j < cols; j++) {
                if (array[i][j]=="B" && i < half_1 && seats > 60) {
                    current_income += 10;
                } else if (array[i][j]=="B" && i >= half_1 && seats > 60) {
                    current_income += 8;
                } else if (array[i][j]=="B" && seats <= 60) {
                    current_income += 10;
                }
            }
        }

        // Compute total income
        if (seats <= 60) {
            income = 10 * seats;
        } else {
            income = 10 * half_1 * cols + 8 * (rows - half_1) * cols;
        } 

        // print
        System.out.println("Number of purchased tickets: "+n_tickets);
        System.out.printf("Percentage: %.2f%%", percentage);
        System.out.println();
        System.out.println("Current income: $"+current_income);
        System.out.println("Total income: $"+income);
        
    }

    public static void printSeats(String[][] array, int rows, int cols) {
        System.out.println("Cinema:");
        for (int i = 0; i < rows + 1; i ++) {
            for (int j = 0; j < cols + 1; j ++) {
                if (i == 0 & j == 0) {
                    System.out.print("  ");
                } else if (i == 0 & j > 0) {
                    System.out.print(j + " ");
                } else if (j == 0 && i > 0) {
                    System.out.print(i + " ");
                } else {
                    System.out.print(array[i-1][j-1]+" ");
                }
            }
            System.out.println();
        }
    }

    public static void buyTickets(String[][] array, int rows, int cols) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a row number:");
        int row = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int col = scanner.nextInt();
        int seats = rows * cols;
        int price = 0;
        int half_1 = rows / 2;
        
        if ((row < 1 || row > rows)||(col < 1 || col > cols)) {
            System.out.println("Wrong input!");
            buyTickets(array, rows, cols);
        } else if (array[row-1][col-1]=="B") {
            System.out.println("That ticket has already been purchased!");
            buyTickets(array, rows, cols);
        } else {
            if (seats <= 60) {
                price = 10;
            } else if (row <= half_1){
                price = 10;
            } else {
                price = 8;
            }
            array[row-1][col-1] = "B";  
            System.out.println("Ticket price: $"+price);
        }
    }
}
    