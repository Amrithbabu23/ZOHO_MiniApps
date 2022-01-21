package Vehicle_RentalSystem;

import java.util.*;

public class VehicleApp {
    private static Scanner sc;
    private static ArrayList<Vehicle> v_list = new ArrayList<Vehicle>();
    private static ArrayList<Users> u_list = new ArrayList<Users>();
    private static ArrayList<Vehicle> s_list = new ArrayList<Vehicle>();
    private static ArrayList<History> h_list = new ArrayList<History>();
    static int user_id = 4, index = -1, veh_index = -1;

    private static void home() {
        System.out.println("-------Welcome to Vehicle Rental App-------");
        System.out.println("1.Admin");
        System.out.println("2.User");
        System.out.println("3.Exit");
        System.out.println("Select an option");
        int ch = sc.nextInt();
        switch (ch) {
            case 1:
                clear();
                admin();
                break;
            case 2:
                clear();
                user();
                break;
            case 3:
                System.out.println("Thank you for visiting us!");
                clear();
                System.exit(0);
                break;
            default:
                clear();
                home();
                break;
        }
    }

    // -------------User-------------------
    private static void user() {
        System.out.println("\t------------Welcome Rental Vehicle Users-----------");
        System.out.println("1.New User");
        System.out.println("2.Existing User");
        System.out.println("3.Home");
        System.out.print("Enter your choice :");
        int ch = sc.nextInt();
        switch (ch) {
            case 1:
                clear();
                newUser();
                break;
            case 2:
                clear();
                existUser();
                break;
            case 3:
                clear();
                home();
                break;
            default:
                System.out.println("Enter valid Input");
                clear();
                user();
                break;
        }

    }

    private static void newUser() {
        System.out.print("Enter User Name:");
        String u_name = sc.next();
        System.out.print("Set User Password");
        String u_pass = sc.next();
        System.out.print("Set Initial Security Deposit Amount:");
        double u_amt = sc.nextDouble();
        u_list.add(new Users(user_id, u_name, u_pass, u_amt));
        System.out.println("Your User Id is " + user_id + " Please Remember this for Login purposes");
        user_id++;
        System.out.println("User Created Successfully...Try to log In");
        clear();
        user();
    }

    private static void existUser() {
        System.out.print("Enter User ID: ");
        int u_id = sc.nextInt();
        System.out.print("User Password: ");
        String u_pass = sc.next();
        if (check(u_list, u_id, u_pass)) {
            orgUser();
        } else {
            System.out.println("Invalid login Credentials Try again...");
            System.out.println("Press Enter to continue");
            sc.nextLine();
            String s = sc.nextLine();
            System.out.println("\033[H\033[2J");
            System.out.flush();
        }
    }

    private static boolean check(ArrayList<Users> u_list2, int u_id, String u_pass) {
        for (int i = 0; i < u_list.size(); i++) {
            if (u_list.get(i).u_id == u_id && u_list.get(i).u_password.equals(u_pass)) {
                index = i;
                return true;
            }
        }
        return false;
    }

    private static void orgUser() {
        System.out.println("Welcome " + u_list.get(index).u_name + " to Car Rentals.....:)");
        System.out.println("1.Rent a Vehicle");
        System.out.println("2.Rent History");
        System.out.println("3.Return Vehicle");
        System.out.println("4.Deposit Balance");
        System.out.println("5.Exit");
        System.out.print("Enter your choice :");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                clear();
                rentVehicle();
                break;
            case 2:
                clear();
                rentHistory();
                break;
            case 3:
                clear();
                vehicleReturn();
                break;
            case 4:
                clear();
                depositBalance();
                break;
            case 5:
                System.out.println("Press Enter to continue");
                clear();
                user();
                break;
            default:
                System.out.println("Enter valid Input.....");
                clear();
                orgUser();
                break;
        }
    }

    // ------------------Renting Vehicle---------------
    private static void rentVehicle() {
        System.out.println("AVAILABLE VEHICLES");
        System.out.println();
        int avail = 0;
        System.out.printf("%-18s||%-7s||%-20s||%-7s||%-10s||%-7s||%-7s\n", "Vehicle Number", "V_Type", "V_Name", "V_KM",
                "V_Service", "V_Rent", "V_Avail");
        for (int i = 0; i < v_list.size(); i++) {
            if (v_list.get(i).v_available.equals("in")) {
                avail++;
                System.out.printf("%-18s||%-7s||%-20s||%-7s||%-10s||%-7s||%-7s\n", v_list.get(i).v_no,
                        v_list.get(i).v_type,
                        v_list.get(i).v_name, v_list.get(i).v_km, v_list.get(i).v_service, v_list.get(i).v_rent,
                        v_list.get(i).v_available);
            }
        }
        if (avail == 0) {
            System.out.println("No vehicles available");
            clear();
            orgUser();
        }
        System.out.println();
        System.out.println("Enter the vehicle number you want to Rent");
        String r_no = sc.next();
        int flag = 0;
        for (int j = 0; j < v_list.size(); j++) {
            if (v_list.get(j).v_no.equals(r_no) && v_list.get(j).v_available.equals("in")) {
                flag++;
                if (v_list.get(j).v_type.equals("Car")) {
                    if (u_list.get(index).u_balance > 10000) {
                        h_list.add(new History(u_list.get(index).u_id, v_list.get(j).v_no, v_list.get(j).v_name, "no"));
                        v_list.get(j).v_available = "out";
                        System.out.println("Vehicle Rented Successfully");
                        clear();
                        orgUser();
                        break;
                    } else {
                        System.out.println("Min Balance should be > 10000 to rent a Car");
                        clear();
                        orgUser();
                        break;
                    }
                } else if (v_list.get(j).v_type.equals("Bike")) {
                    if (u_list.get(index).u_balance > 3000) {
                        h_list.add(new History(u_list.get(index).u_id, v_list.get(j).v_no, v_list.get(j).v_name, "no"));
                        v_list.get(j).v_available = "out";
                        System.out.println("Vehicle Rented Successfully");
                        clear();
                        orgUser();
                        break;
                    } else {
                        System.out.println("Min Balance should be > 3000 to rent a Bike");
                        clear();
                        orgUser();
                        break;
                    }
                }
            }
        }
        if (flag == 0) {
            System.out.println("Enter valid vehicle number");
            clear();
            orgUser();
        }

    }

    // -----------------Rent History-----------------
    private static void rentHistory() {
        System.out.println("VEHICLE RENT HISTORY");
        System.out.println();
        int his = 0;
        System.out.printf("%-18s||%-20s||%-10s\n", "Vehicle Number", "V_Name", "Returned");
        for (int i = 0; i < h_list.size(); i++) {
            if (u_list.get(index).u_id == h_list.get(i).u_id) {
                his++;
                System.out.printf("%-18s||%-20s||%-10s\n", h_list.get(i).v_no,
                        h_list.get(i).v_name, h_list.get(i).v_return);
            }
        }
        if (his == 0) {
            System.out.println("No vehicle rented so far");
        }
        clear();
        orgUser();
    }

    // ---------------Admin----------------
    private static void admin() {
        System.out.println("Enter Admin Username");
        String a_name = sc.next();
        System.out.println("Enter Admin password");
        String a_pass = sc.next();
        if (a_name.equals("Amrith") && a_pass.equals("1234")) {
            clear();
            orgAdmin();
        } else {
            System.out.println("Admin Username/Password Invalid");
            System.out.println("Press Enter to go home");
            clear();
            home();
        }
    }

    // -------------Vehicle Return-------------
    private static void vehicleReturn() {
        System.out.println("Enter vehicle number to return");
        String re_no = sc.next();
        int flag = 0;
        for (int i = 0; i < h_list.size(); i++) {
            if (u_list.get(index).u_id == h_list.get(i).u_id && h_list.get(i).v_no.equals(re_no)
                    && h_list.get(i).v_return.equals("no")) {
                flag++;
                for (int j = 0; j < v_list.size(); j++) {
                    if (v_list.get(j).v_no.equals(re_no)) {
                        veh_index = j;
                    }
                }
                System.out.println("Enter KM driven : ");
                int km = sc.nextInt();
                v_list.get(veh_index).v_km += km;
                if (km > 500) {
                    u_list.get(index).u_balance -= 0.15 * v_list.get(veh_index).v_rent;
                }
                System.out.println("Enter Damage level (L)(M)(H)");
                String dam = sc.next();
                if (dam.equals("L") || dam.equals("l")) {
                    u_list.get(index).u_balance -= 0.20 * u_list.get(index).u_balance;
                    System.out.println("damage amount deducted from security savings");
                } else if (dam.equals("M") | dam.equals("m")) {
                    u_list.get(index).u_balance -= 0.50 * u_list.get(index).u_balance;
                    System.out.println("damage amount deducted from security savings");
                } else if (dam.equals("H") || dam.equals("h")) {
                    u_list.get(index).u_balance -= 0.75 * u_list.get(index).u_balance;
                    System.out.println("damage amount deducted from security savings");
                }
                System.out.println("Vehicle returned Successfully");
                h_list.get(i).v_return = "yes";
            }
        }
        if (flag == 0) {
            System.out.println("This vehicle is not rented by you!");
        }
        if (flag != 0) {
            if (v_list.get(veh_index).v_km > 3000) {
                s_list.add(
                        new Vehicle(v_list.get(veh_index).v_no, v_list.get(veh_index).v_type,
                                v_list.get(veh_index).v_name,
                                v_list.get(veh_index).v_km, "no", v_list.get(veh_index).v_rent, "no"));
                v_list.remove(veh_index);
            }
        }
        System.out.println("Press Enter to continue..");
        clear();
        orgUser();
    }

    // ------------Deposit Balance-------------
    private static void depositBalance() {
        System.out.println("Security Deposit Balance :" + u_list.get(index).u_balance);
        System.out.println("Press (1) to add Amount or any other number to go back");
        int ch = sc.nextInt();
        if (ch == 1) {
            System.out.println("Enter Amount to be added : ");
            double d_amt = sc.nextDouble();
            u_list.get(index).u_balance += d_amt;
            System.out.println("Amount added Successfully");
            clear();
            orgUser();
        } else {
            clear();
            orgUser();
        }
    }

    private static void orgAdmin() {
        System.out.println("--------Welcome Admin--------");
        System.out.println("1.Add vehicle");
        System.out.println("2.View vehicle");
        System.out.println("3.Modify vehicle details");
        System.out.println("4.Search vehicle");
        System.out.println("5.Delete vehicle");
        System.out.println("6.Service list");
        System.out.println("7.Return to home");
        System.out.println("Enter a valid choice");
        int a_ch = sc.nextInt();
        switch (a_ch) {
            case 1:
                clear();
                addVehicle();
                break;
            case 2:
                clear();
                viewVehicle();
                break;
            case 3:
                clear();
                modifyVehicle();
                break;
            case 4:
                clear();
                searchVehicle();
                break;
            case 5:
                clear();
                deleteVehicle();
                break;
            case 6:
                clear();
                service();
                break;
            case 7:
                clear();
                home();
                break;
            default:
                clear();
                orgAdmin();
                break;
        }
    }

    private static void service() {
        System.out.println("VEHICLES FOR SERVICE");
        System.out.println();
        System.out.printf("%-18s||%-7s||%-20s||%-7s||%-10s||%-7s||%-7s\n", "Vehicle Number", "V_Type", "V_Name", "V_KM",
                "V_Service", "V_Rent", "V_Avail");
        for (int i = 0; i < s_list.size(); i++) {
            System.out.printf("%-18s||%-7s||%-20s||%-7s||%-10s||%-7s||%-7s\n", s_list.get(i).v_no, s_list.get(i).v_type,
                    s_list.get(i).v_name, s_list.get(i).v_km, s_list.get(i).v_service, s_list.get(i).v_rent,
                    s_list.get(i).v_available);
        }
        System.out.println();
        System.out.println("Press(1) to service Vehicle or any other key to exit");
        String s_ch = sc.next();
        if (s_ch.equals("1")) {
            System.out.println("Enter Vehicle Number to Service");
            String s_veh = sc.next();
            int flag = 0;
            int s_index = -1;
            for (int j = 0; j < s_list.size(); j++) {
                if (s_list.get(j).v_no.equals(s_veh)) {
                    flag++;
                    s_index = j;
                }
            }
            if (flag == 0) {
                System.out.println("Vehicle Number not in Service List");
                clear();
                orgAdmin();
            } else if (flag != 0) {
                v_list.add(new Vehicle(s_list.get(s_index).v_no, s_list.get(s_index).v_type, s_list.get(s_index).v_name,
                        0, "yes", s_list.get(s_index).v_rent, "in"));
                s_list.remove(s_index);
                System.out.println("Vehicle Service successful");
                clear();
                orgAdmin();
            }
        } else {
            clear();
            orgAdmin();
        }
    }

    private static void viewVehicle() {
        System.out.println("AVAILABLE VEHICLES");
        System.out.println();
        System.out.printf("%-15s||%-7s||%-17s||%-7s||%-10s||%-7s||%-7s\n", "Vehicle Number", "V_Type", "V_Name", "V_KM",
                "V_Service", "V_Rent", "V_Avail");
        for (int i = 0; i < v_list.size(); i++) {
            System.out.printf("%-15s||%-7s||%-17s||%-7s||%-10s||%-7s||%-7s\n", v_list.get(i).v_no, v_list.get(i).v_type,
                    v_list.get(i).v_name, v_list.get(i).v_km, v_list.get(i).v_service, v_list.get(i).v_rent,
                    v_list.get(i).v_available);
        }
        System.out.println();
        System.out.println("Press Enter to continue..");
        clear();
        orgAdmin();
    }

    // -----------------Search Vehicle--------------
    private static void searchVehicle() {
        System.out.println("1.Search by vehicle type ");
        System.out.println("2.Search by vehicle number");
        int ch = sc.nextInt();
        if (ch == 1) {
            System.out.println("Press (1) for Cars and (2) for bikes");
            int s_type = sc.nextInt();
            if (s_type == 1) {
                System.out.printf("%-18s||%-7s||%-20s||%-7s||%-10s||%-7s||%-7s\n", "Vehicle Number", "V_Type", "V_Name",
                        "V_KM",
                        "V_Service", "V_Rent", "V_Avail");
                for (int i = 0; i < v_list.size(); i++) {
                    if (v_list.get(i).v_type.equals("Car")) {
                        System.out.printf("%-18s||%-7s||%-20s||%-7s||%-10s||%-7s||%-7s\n", v_list.get(i).v_no,
                                v_list.get(i).v_type,
                                v_list.get(i).v_name, v_list.get(i).v_km, v_list.get(i).v_service, v_list.get(i).v_rent,
                                v_list.get(i).v_available);
                    }
                }
            } else if (s_type == 2) {
                System.out.printf("%-18s||%-7s||%-20s||%-7s||%-10s||%-7s||%-7s\n", "Vehicle Number", "V_Type", "V_Name",
                        "V_KM",
                        "V_Service", "V_Rent", "V_Avail");
                for (int i = 0; i < v_list.size(); i++) {
                    if (v_list.get(i).v_type.equals("Bike")) {
                        System.out.printf("%-18s||%-7s||%-20s||%-7s||%-10s||%-7s||%-7s\n", v_list.get(i).v_no,
                                v_list.get(i).v_type,
                                v_list.get(i).v_name, v_list.get(i).v_km, v_list.get(i).v_service, v_list.get(i).v_rent,
                                v_list.get(i).v_available);
                    }
                }
            } else {
                System.out.println("Enter correct choice");
                clear();
                searchVehicle();
            }
        } else if (ch == 2) {
            System.out.println("Enter vehicle number to search :");
            String s_veh = sc.next();
            int flag = 0;
            System.out.printf("%-18s||%-7s||%-20s||%-7s||%-10s||%-7s||%-7s\n", "Vehicle Number", "V_Type", "V_Name",
                    "V_KM",
                    "V_Service", "V_Rent", "V_Avail");
            for (int i = 0; i < v_list.size(); i++) {
                if (v_list.get(i).v_no.equals(s_veh)) {
                    flag++;
                    System.out.printf("%-18s||%-7s||%-20s||%-7s||%-10s||%-7s||%-7s\n", v_list.get(i).v_no,
                            v_list.get(i).v_type,
                            v_list.get(i).v_name, v_list.get(i).v_km, v_list.get(i).v_service, v_list.get(i).v_rent,
                            v_list.get(i).v_available);
                }
            }
            if (flag == 0) {
                System.out.println("No such vehicle exist..Check number again");
                clear();
                searchVehicle();
            }
        } else {
            System.out.println("Enter correct choice");
            clear();
            searchVehicle();
        }
        System.out.println();
        System.out.println("Press Enter to go back..");
        clear();
        orgAdmin();

    }

    // -----------------Delete Vehicle---------------
    private static void deleteVehicle() {
        System.out.println("Enter vehicle Number to be deleted :");
        String del_no = sc.next();
        int flag = 0;
        for (int i = 0; i < v_list.size(); i++) {
            if (v_list.get(i).v_no.equals(del_no)) {
                System.out.println("Vehicle  " + v_list.get(i).v_name + " Deleted Successfully...");
                v_list.remove(i);
                flag = 1;
            }
        }
        if (flag == 0) {
            System.out.println("No Vehicle is present in the given vehicle number");
        }
        System.out.println("Press Enter to continue");
        clear();
        orgAdmin();
    }

    // -----------------Vehicle Modify---------------
    private static void modifyVehicle() {
        System.out.println("AVAILABLE VEHICLES");
        System.out.println();
        System.out.printf("%-18s||%-7s||%-20s||%-7s||%-10s||%-7s||%-7s\n", "Vehicle Number", "V_Type", "V_Name", "V_KM",
                "V_Service", "V_Rent", "V_Avail");
        for (int i = 0; i < v_list.size(); i++) {
            System.out.printf("%-18s||%-7s||%-20s||%-7s||%-10s||%-7s||%-7s\n", v_list.get(i).v_no, v_list.get(i).v_type,
                    v_list.get(i).v_name, v_list.get(i).v_km, v_list.get(i).v_service, v_list.get(i).v_rent,
                    v_list.get(i).v_available);
        }
        System.out.println();
        System.out.print("Enter the Vehicle number to modify :");
        int mod_count = 0;
        String mv_no = sc.next();
        for (int i = 0; i < v_list.size(); i++) {
            if (v_list.get(i).v_no.equals(mv_no)) {
                mod_count++;
                System.out.println("1.Modify Vehicle Name");
                System.out.println("2.Modify Vehicle Service Status");
                System.out.println("3.Modify Vehicle Number");
                System.out.println("4.Modify Vehicle KM");
                System.out.println("5.Modify Vehicle Rental charge");
                int mod_veh = sc.nextInt();
                if (mod_veh == 1) {
                    mod_name(i);
                }
                if (mod_veh == 2) {
                    mod_service(i);
                }
                if (mod_veh == 3) {
                    mod_no(i);
                }
                if (mod_veh == 4) {
                    mod_km(i);
                }
                if (mod_veh == 5) {
                    mod_rent(i);
                }
            }
        }
        if (mod_count == 0) {
            System.out.println("Incorrect Vehicle number or Vehicle not Found....");
        }
        System.out.println("Press Enter to continue");
        clear();
        orgAdmin();
    }

    private static void mod_name(int i) {
        System.out.print("Enter new Vehicle Name :");
        String veh_name = sc.next();
        v_list.get(i).v_name = veh_name;
        System.out.println("Vehicle name Changed Successfully....");
    }

    private static void mod_service(int i) {
        System.out.print("Enter new Service Status :");
        String veh_service = sc.next();
        v_list.get(i).v_service = veh_service;
        System.out.println("Vehicle Service Status Changed Successfully....");
    }

    private static void mod_no(int i) {
        System.out.print("Enter new Vehicle Number :");
        String veh_no = sc.next();
        v_list.get(i).v_no = veh_no;
        System.out.println("Vehicle Number Changed Successfully....");
    }

    private static void mod_km(int i) {
        System.out.print("Enter new Vehicle KM :");
        int veh_km = sc.nextInt();
        v_list.get(i).v_km = veh_km;
        System.out.println("Vehicle KM Changed Successfully....");
    }

    private static void mod_rent(int i) {
        System.out.print("Enter new book Title :");
        double veh_rent = sc.nextDouble();
        v_list.get(i).v_rent = veh_rent;
        System.out.println("Vehicle Rent Changed Successfully....");
    }

    // --------------------Add Vehicle--------------------------
    private static void addVehicle() {
        System.out.println("-------Add Vehicle-------");
        System.out.println("Enter Vehicle number : ");
        String av_no = sc.next();
        System.out.println("Enter Vehicle type : ");
        String av_type = sc.next();
        sc.nextLine();
        System.out.print("Enter Vehicle name : ");
        String av_name = sc.nextLine();
        System.out.println("Enter Vehicle KM : ");
        int av_km = sc.nextInt();
        System.out.println("Enter Vehicle Service Status : ");
        String av_service = sc.next();
        System.out.println("Enter Vehicle Rental charge : ");
        double av_rent = sc.nextDouble();
        v_list.add(new Vehicle(av_no, av_type, av_name, av_km, av_service, av_rent, "in"));
        System.out.println("Vehicle added Successfully");
        System.out.println("Press Enter to go back");
        clear();
        orgAdmin();
    }

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        userList();
        vehicleList();
        home();
    }

    private static void userList() {
        u_list.add(new Users(1, "Niveth", "1234", 50000));
        u_list.add(new Users(2, "Lal", "2345", 40000));
        u_list.add(new Users(3, "Sanjay", "3456", 45000));
    }

    private static void vehicleList() {
        v_list.add(new Vehicle("TN37AB5757", "Car", "Hyundai Creta", 0, "yes", 3000, "in"));
        v_list.add(new Vehicle("TN37AD1234", "Car", "Ford Ecosport", 100, "yes", 3000, "in"));
        v_list.add(new Vehicle("TN66AB1789", "Car", "Skoda Rapid", 200, "yes", 3000, "in"));
        v_list.add(new Vehicle("TN37CL1923", "Bike", "Bajaj Pulsar", 0, "yes", 1000, "in"));
        v_list.add(new Vehicle("TN66DE1756", "Bike", "Yamaha MT-i", 0, "yes", 1000, "in"));
        v_list.add(new Vehicle("TN66FR6654", "Bike", "Royal Enfield", 0, "yes", 1000, "in"));
    }

    private static void clear() {
        sc.nextLine();
        String f = sc.nextLine();
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }

}

class Vehicle {
    int v_km;
    String v_no, v_name, v_type, v_service, v_available;
    double v_rent;

    Vehicle(String v_no, String v_type, String v_name, int v_km, String v_service, double v_rent, String v_available) {
        this.v_km = v_km;
        this.v_no = v_no;
        this.v_name = v_name;
        this.v_type = v_type;
        this.v_service = v_service;
        this.v_rent = v_rent;
        this.v_available = v_available;
    }
}

class Users {
    int u_id;
    String u_name, u_password;
    double u_balance;

    Users(int u_id, String u_name, String u_password, double u_balance) {
        this.u_id = u_id;
        this.u_name = u_name;
        this.u_password = u_password;
        this.u_balance = u_balance;
    }
}

class History {
    int u_id;
    String v_no, v_name, v_return;

    History(int u_id, String v_no, String v_name, String v_return) {
        this.u_id = u_id;
        this.v_no = v_no;
        this.v_name = v_name;
        this.v_return = v_return;
    }
}