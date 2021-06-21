package cinema;

import java.util.Scanner;

public class Cinema {
    private final boolean[][] seats;
    private final int rows;
    private final int seatsPerRow;
    private static final Scanner scanner = new Scanner(System.in);
    private int soldTickets = 0;
    private int totalIncome = 0;
    private int income = 0;

    public Cinema(int rows, int seatsPerRow) {
        this.seats = new boolean[rows][];
        this.rows = rows;
        this.seatsPerRow = seatsPerRow;
        for (int i = 0; i < rows; i++) {
            seats[i] = new boolean[seatsPerRow];
        }
    }

    public void initTotalIncome() {
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= seatsPerRow; j++) {
                totalIncome += getPrice(i, j);
            }
        }
    }

    public void printSeats() {
        System.out.println("Cinema:");
        System.out.print(" ");
        for (int j = 1; j <= seatsPerRow; j++) {
            System.out.print(" " + j);
        }
        System.out.println();

        for (int i = 0; i < rows; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < seatsPerRow; j++) {
                System.out.print(" " + (seats[i][j] ? "B" : "S"));
            }
            System.out.println();
        }
        System.out.println();
    }

    public void book() {
        boolean success;
        int row;
        int col;
        do {
            System.out.println("Enter a row number:");
            row = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            col = scanner.nextInt();
            success = isValid(row, col);
        } while (!success);

        seats[row - 1][col - 1] = true;
        soldTickets++;

        int price = getPrice(row, col);
        income += price;
        System.out.println("Ticket price: $" + price);
        System.out.println();
    }

    private boolean isValid(int row, int col) {
        if (row < 1 || row > rows ||
                col < 1 || col > seatsPerRow) {

            System.out.println("Wrong input!");
            return false;
        }
        if (seats[row - 1][col - 1]) {
            System.out.println("That ticket has already been purchased!");
            return false;
        }
        return true;
    }

    private int getPrice(int row, int col) {
        if (rows * seatsPerRow <= 60 || row <= rows / 2) {
            return 10;
        }
        return 8;
    }

    private void printStatistic() {
        System.out.println("Number of purchased tickets: " + soldTickets);
        System.out.printf("Percentage: %.2f%%%n", (float) soldTickets * 100 / (rows * seatsPerRow));
        System.out.println("Current income: $" + income);
        System.out.println("Total income: $" + totalIncome);
        System.out.println();
    }

    public static void main(String[] args) {
        // Write your code here
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();
        Cinema cinema = new Cinema(rows, seats);
        cinema.initTotalIncome();

        int choice;
        do {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    cinema.printSeats();
                    break;
                case 2:
                    cinema.book();
                    break;
                case 3:
                    cinema.printStatistic();
            }
        } while (choice != 0);
    }
}
