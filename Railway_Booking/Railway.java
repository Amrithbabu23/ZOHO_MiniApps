package Railway_Booking;

import java.util.*;

public class Railway {
    private static Scanner sc;
    private static final String t_name = "Coimbatore Express";
    private static final String t_number = "XYZ001CBE07";
    private static final String t_stops[] = { "Coimbatore", "Tiruppur", "Erode", "Salem", "Kancheepuram", "Chennai" };
    private static ArrayList<Users> user_list = new ArrayList<>();
    private static ArrayList<Wait> wait_list = new ArrayList<>();
    private static ArrayList<Wait> book_list = new ArrayList<>();
    private static int seats[][] = new int[10][6];
    static int u_id = 102, index = 0, ticket_no = 50, cancel_index = 0;

    public static void home() {
        System.out.println("--------Welocome to Railway Ticket Booking--------");
        System.out.println("1.Admin");
        System.out.println("2.User");
        System.out.println("3.Exit");
        System.out.println("Choose any of the options..");
        int ch = sc.nextInt();
        switch (ch) {
            case 1:
                admin();
                break;
            case 2:
                user();
                break;
            case 3:
                System.out.println("Thank you for visiting online Booking App");
                System.exit(0);
            default:
                home();
                break;
        }
    }

    private static void admin() {
        System.out.println("Enter Admin Username");
        String a_name = sc.next();
        System.out.println("Enter Admin Password");
        String a_pass = sc.next();
        if (a_name.equals("Amrith") && a_pass.equals("1234")) {
            System.out.println("Welcome Admin " + a_name);
            System.out.println("Train Name : " + t_name);
            System.out.println("Train Number : " + t_number);
            System.out.println();
            System.out.println("--------BOOKING LIST--------");
            System.out.printf("%-10s %-15s %-15s %-10s\n", "User_ID", "From", "To", "Ticket_no");
            for (int k = 0; k < book_list.size(); k++) {
                System.out.printf("%-10s %-15s %-15s %-10s\n", book_list.get(k).u_id,
                        t_stops[book_list.get(k).dep_index],
                        t_stops[book_list.get(k).arr_index], book_list.get(k).ticket_no);
            }
            System.out.println();
            System.out.println("--------Train travelling cities :--------");
            for (int i = 0; i < 6; i++) {
                System.out.println(t_stops[i]);
            }
            System.out.println();
            System.out.println("--------Seat Filling--------");
            for (int l = 0; l < 10; l++) {
                for (int h = 0; h < 6; h++) {
                    System.out.printf("%-7s", seats[l][h]);
                }
                System.out.println();
            }
            System.out.println();
            System.out.println("--------WAIT LIST--------");
            System.out.printf("%-10s %-15s %-15s %-10s\n", "User_ID", "From", "To", "Ticket_no");
            for (int n = 0; n < wait_list.size(); n++) {
                System.out.printf("%-10s %-15s %-15s %-10s\n", wait_list.get(n).u_id,
                        t_stops[wait_list.get(n).dep_index],
                        t_stops[wait_list.get(n).arr_index], wait_list.get(n).ticket_no);
            }
        } else {
            System.out.println("Admin Name/Password incorrect");
        }
        sc.nextLine();
        String e3 = sc.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        home();
    }

    private static void user() {
        System.out.println("\t\t\t\t--------Welcome Users--------");
        System.out.println("1.Log In");
        System.out.println("2.Sign up");
        System.out.println("3.Exit");
        System.out.print("Enter your choice:");
        int m = sc.nextInt();
        switch (m) {
            case 1:
                userLogin();
                break;
            case 2:
                userSignup();
                break;
            case 3:
                System.out.print("\033[H\033[2J");
                System.out.flush();
                home();
            default:
                user();
                break;
        }
    }

    private static boolean check(ArrayList<Users> user_list2, int l_id, String l_pass) {
        for (int i = 0; i < user_list.size(); i++) {
            if (user_list.get(i).u_id == l_id && user_list.get(i).u_password.equals(l_pass)) {
                index = i;
                return true;
            }
        }
        return false;
    }

    private static void userLogin() {
        System.out.print("Enter User Id:");
        int l_id = sc.nextInt();
        System.out.print("Enter User Password:");
        String l_pass = sc.next();
        if (check(user_list, l_id, l_pass)) {
            orgUser();
        } else {
            System.out.println("Please Enter valid user credentials....:(");
            System.out.println("\t\t\tPress enter to continue...");
            sc.nextLine();
            String s = sc.nextLine();
            System.out.print("\033[H\033[2J");
            System.out.flush();
            userLogin();
        }

    }

    private static void orgUser() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("Welcome " + user_list.get(index).u_name);
        System.out.println("1.Book Ticket");
        System.out.println("2.View or Cancel Ticket");
        System.out.println("3.View Train Details");
        System.out.println("4.IRTC Wallet");
        System.out.println("5.Exit");
        System.out.println("Enter Your Choice");
        int opt = sc.nextInt();
        switch (opt) {
            case 1:
                bookTicket();
                break;
            case 2:
                cancelTicket();
                break;
            case 3:
                trainDetails();
                break;
            case 4:
                wallet();
                break;
            case 5:
                user();
                break;
            default:
                orgUser();
                break;
        }
    }

    private static void cancelTicket() {
        System.out.println("--------Booking Details--------");
        System.out.printf("%-10s %-15s %-15s %-10s\n", "User_ID", "From", "To", "Ticket_no");
        for (int k = 0; k < book_list.size(); k++) {
            if (book_list.get(k).u_id == user_list.get(index).u_id) {
                System.out.printf("%-10s %-15s %-15s %-10s\n", book_list.get(k).u_id,
                        t_stops[book_list.get(k).dep_index],
                        t_stops[book_list.get(k).arr_index], book_list.get(k).ticket_no);
            }
        }
        System.out.println("Press (1) cancel tickets..or any other number to go back");
        int can_t = sc.nextInt();
        int cancel_count = 0;
        if (can_t != 1) {
            orgUser();
        } else if (can_t == 1) {
            System.out.println("Enter the ticket number to be cancelled");
            int t_can = sc.nextInt();
            for (int i = 0; i < book_list.size(); i++) {
                if (book_list.get(i).u_id == user_list.get(index).u_id && book_list.get(i).ticket_no == t_can) {
                    cancel_index = i;
                    cancelProcess();
                    cancel_count++;
                }
            }
            if (cancel_count == 0) {
                System.out.println("Please Enter Valid Ticket Number");
                cancelTicket();
            }
        }
    }

    private static void cancelProcess() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 6; j++) {
                if (seats[i][j] == book_list.get(cancel_index).ticket_no) {
                    seats[i][j] = 0;
                }
            }
        }
        int rem_index = 0;
        System.out.println("Ticket cancelled Succesfully...Refund will be made in a few days");
        z: for (int w = 0; w < wait_list.size(); w++) {
            if (wait_list.get(w).dep_index >= book_list.get(cancel_index).dep_index
                    && wait_list.get(w).arr_index <= book_list.get(cancel_index).arr_index) {
                x: for (int i = 0; i < 10; i++) {
                    int sum = 0;
                    y: for (int j = wait_list.get(w).dep_index; j < wait_list.get(w).arr_index; j++) {
                        sum += seats[i][j];
                    }
                    if (sum == 0) {
                        double amount = 0;
                        int wait_index = -1;
                        book_list.add(new Wait(wait_list.get(w).u_id, wait_list.get(w).dep_index,
                                wait_list.get(w).arr_index, ++ticket_no));
                        amount = ((wait_list.get(w).arr_index) - (wait_list.get(w).dep_index)) * (double) 50;
                        for (int m = 0; m < user_list.size(); m++) {
                            if (user_list.get(m).u_id == wait_list.get(w).u_id) {
                                wait_index = m;
                            }
                        }
                        user_list.get(wait_index).u_wallet -= amount;
                        for (int k = wait_list.get(w).dep_index; k < wait_list.get(w).arr_index; k++) {
                            seats[i][k] = ticket_no;
                        }
                        rem_index = w;
                        book_list.remove(cancel_index);
                        wait_list.remove(rem_index);
                        break z;
                    }
                }
            }
        }
        sc.nextLine();
        String s12 = sc.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        orgUser();
    }

    private static void trainDetails() {
        System.out.println("Train Name : " + t_name);
        System.out.println("Train Number : " + t_number);
        System.out.println("Train travelling cities :");
        for (int i = 0; i < 6; i++) {
            System.out.println(t_stops[i]);
        }
        sc.nextLine();
        String s13 = sc.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        orgUser();
    }

    private static void wallet() {
        System.out.println("Amount in wallet : " + user_list.get(index).u_wallet);
        sc.nextLine();
        String s = sc.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        orgUser();
    }

    private static void bookTicket() {
        System.out.println("Enter number of booking");
        int n = sc.nextInt();
        for (int s = 0; s < n; s++) {
            int s_yes = 0, d_index = -1, a_index = -1;
            System.out.println("Enter Departure station :");
            String d_st = sc.next();
            System.out.println("Enter Dropping station :");
            String a_st = sc.next();
            for (int i = 0; i < t_stops.length; i++) {
                if (t_stops[i].equals(d_st)) {
                    d_index = i;
                    s_yes++;
                }
                if (t_stops[i].equals(a_st)) {
                    a_index = i;
                    s_yes++;
                }
            }
            if (s_yes == 2) {
                checkSeat(d_index, a_index);
            } else {
                System.out.println("Enter correct station names for CBE Express");
                bookTicket();
            }
        }
        orgUser();
    }

    private static void checkSeat(int d_index, int a_index) {
        int confirm = 0;
        double amount = 0;
        x: for (int i = 0; i < 10; i++) {
            int sum = 0;
            y: for (int j = d_index; j < a_index; j++) {
                sum += seats[i][j];
            }
            if (sum == 0) {
                System.out.println("Seat allocated for user_ID " + user_list.get(index).u_id + " Seat Number: " + i);
                confirm++;
                book_list.add(new Wait(user_list.get(index).u_id, d_index, a_index, ++ticket_no));
                amount = (a_index - d_index) * (double) 50;
                user_list.get(index).u_wallet -= amount;
                System.out.println("Amount " + amount + " deducted from wallet...Thank You!");
                for (int k = d_index; k < a_index; k++) {
                    seats[i][k] += ticket_no;
                }
                break x;
            }
        }
        if (confirm == 0) {
            if (wait_list.size() > 5) {
                System.out.println("Sorry...Tickets not available");
            } else {
                System.out.println("Tickets NA..In waiting List");
                wait_list.add(new Wait(user_list.get(index).u_id, d_index, a_index, 0));
            }
        }
        System.out.println();
        System.out.println("--------Seat Fillings-------");
        for (int l = 0; l < 10; l++) {
            for (int h = 0; h < 6; h++) {
                System.out.printf("%-7s", seats[l][h]);
            }
            System.out.println();
        }
        sc.nextLine();
        String e1 = sc.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void userSignup() {
        System.out.print("Enter User Name:");
        String s_name = sc.next();
        System.out.print("Enter User Password:");
        String s_pass = sc.next();
        System.out.print("Enter User Contact Number:");
        String s_mobile = sc.next();
        System.out.print("Enter user inital Wallet Amount:");
        double s_wallet = sc.nextDouble();
        user_list.add(new Users(u_id, s_name, s_pass, s_mobile, s_wallet));
        System.out.println("Your user Id is " + u_id);
        System.out.println("Remember for log in credentials!!!");
        u_id++;
        System.out.println("User Created Successully....:)");
        System.out.println("\t\t\tPress enter to continue...");
        sc.nextLine();
        String s = sc.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        user();
    }

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        userList();
        home();
    }

    private static void userList() {
        user_list.add(new Users(101, "Niveth", "1234", "8086031563", 10000));
    }
}

class Users extends Railway {
    int u_id;
    String u_mobile, u_name, u_password;
    double u_wallet;

    Users(int u_id, String u_name, String u_password, String u_mobile, double u_wallet) {
        this.u_id = u_id;
        this.u_name = u_name;
        this.u_password = u_password;
        this.u_mobile = u_mobile;
        this.u_wallet = u_wallet;
    }
}

class Wait extends Railway {
    int u_id;
    int dep_index, arr_index, ticket_no;

    Wait(int u_id, int dep_index, int arr_index, int ticket_no) {
        this.u_id = u_id;
        this.dep_index = dep_index;
        this.arr_index = arr_index;
        this.ticket_no = ticket_no;
    }

}