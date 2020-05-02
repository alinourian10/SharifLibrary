package view;

import java.util.Scanner;

public abstract class ConsoleViewIn {
    private static final Scanner scanner = new Scanner(System.in);

    public static void start() {
        System.out.println("Hi guy, Please set the date to start...");
        while (true) {
            String command = scanner.nextLine();
            if (ConsoleCommands.SET_DATE.getMatcher(command).matches()) {//0
                SplitCommand.setDate(command);
                System.out.println("21-Date has been set!\n");
                break;
            }
            System.out.println("INVALID COMMAND! PLEASE TRY AGAIN!");
        }
        while (true) {
            String command = scanner.nextLine();
            if (ConsoleCommands.END.getMatcher(command).matches()) {//1
                break;
            } else if (ConsoleCommands.ADD_BOOK_TO_LIBRARY.getMatcher(command).matches()) {//2
                SplitCommand.addBookToLibrary(command);
                System.out.println("\n");
            } else if (ConsoleCommands.ADD_BOOK_TO_STORE.getMatcher(command).matches()) {//3
                System.out.println(3);
            } else if (ConsoleCommands.ADD_STUDENT.getMatcher(command).matches()) {//4
                System.out.println(4);
            } else if (ConsoleCommands.ADD_PROFESSOR.getMatcher(command).matches()) {//5
                System.out.println(5);
            } else if (ConsoleCommands.ADD_WORKER.getMatcher(command).matches()) {//6
                System.out.println(6);
            } else if (ConsoleCommands.CREATE_BOOK.getMatcher(command).matches()) {//7
                SplitCommand.createBook(command);
                System.out.println("\n");
            } else if (ConsoleCommands.CREATE_PROFESSOR.getMatcher(command).matches()) {//8
                SplitCommand.createProfessor(command);
                System.out.println("\n");
            } else if (ConsoleCommands.CREATE_STUDENT.getMatcher(command).matches()) {//9
                SplitCommand.createStudent(command);
                System.out.println("\n");
            } else if (ConsoleCommands.CREATE_WORKER.getMatcher(command).matches()) {//10
                SplitCommand.createWorker(command);
                System.out.println("\n");
            } else if (ConsoleCommands.DEPOSIT_PROFESSOR.getMatcher(command).matches()) {//11
                SplitCommand.depositProfessor(command);
                System.out.println("\n");
            } else if (ConsoleCommands.DEPOSIT_STUDENT.getMatcher(command).matches()) {//12
                SplitCommand.depositStudent(command);
                System.out.println("\n");
            } else if (ConsoleCommands.FIND_BOOK.getMatcher(command).matches()) {//13
                System.out.println(13);
            } else if (ConsoleCommands.GIVE_BACK_BOOK.getMatcher(command).matches()) {//14
                System.out.println(14);
            } else if (ConsoleCommands.GIVE_BACK_TO_STORE.getMatcher(command).matches()) {//15
                System.out.println(15);
            } else if (ConsoleCommands.LOAN_BOOK.getMatcher(command).matches()) {//16
                System.out.println(16);
            } else if (ConsoleCommands.SET_SCHEDULE.getMatcher(command).matches()) {//17
                System.out.println(17);
            } else if (ConsoleCommands.SET_DISCOUNT.getMatcher(command).matches()) {//18
                System.out.println(18);
            } else if (ConsoleCommands.SELL_BOOK.getMatcher(command).matches()) {//19
                System.out.println(19);
            } else if (ConsoleCommands.NEXT_DAY.getMatcher(command).matches()) {//20
                System.out.println(20);
            } else {//21
                System.err.println("INVALID COMMAND! PLEASE TRY AGAIN!");
            }
        }
        System.out.println("Program has finished! Good luck;)");
    }

}