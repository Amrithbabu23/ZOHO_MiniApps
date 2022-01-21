package ATM;

import java.util.*;

class AtmApp {
    private static Scanner sc;
    static int arr[] = { 0, 0, 0, 0 };
    static Users u[] = new Users[5];
    static int res = 0, index = 0, lock = 0;

    public static void createUser() {
        u[0] = new Users("Niveth", "1234", 10000, "1010", "10203001", "", "Canara", 0);
        u[1] = new Users("Aswin", "2345", 12000, "1111", "10203002", "", "Canara", 0);
        u[2] = new Users("Lal", "3456", 14000, "1221", "10203003", "", "SBI", 0);
        u[3] = new Users("Sanjay", "4567", 16000, "1144", "10203004", "", "SBI", 0);
        u[4] = new Users("Bala", "5678", 18000, "1212", "10203005", "", "Axis", 0);
    }

    public static void showamount() {
        System.out.println("2000 => " + arr[0] + " ");
        System.out.println("500 => " + arr[1] + " ");
        System.out.println("200 => " + arr[2] + " ");
        System.out.println("100 => " + arr[3] + " ");
        System.out.println("Total amount present : " + res);
    }

    public static void add() {
        System.out.println("2000 => ");
        arr[0] += sc.nextInt();
        System.out.println("500 => ");
        arr[1] += sc.nextInt();
        System.out.println("200 => ");
        arr[2] += sc.nextInt();
        System.out.println("100 => ");
        arr[3] += sc.nextInt();
        System.out
                .println("Total Amount:" + (res += (arr[0] * 2000) + (arr[1] * 500) + (arr[2] * 200) + (arr[3] * 100)));
        System.out.println("Amount added Successfully");
    }

    private static boolean check(Users[] u2, String id, String password) {
        for (int i = 0; i < 5; i++) {
            if (u2[i].name.equals(id) && u2[i].password.equals(password)) {
                index = i;
                return true;
            }
        }
        return false;
    }

    private static void deposit() {
        int dep;
        System.out.println("2000 => ");
        int tt = sc.nextInt();
        arr[0] += tt;
        System.out.println("500 => ");
        int fh = sc.nextInt();
        arr[1] += fh;
        System.out.println("200 => ");
        int th = sc.nextInt();
        arr[2] += th;
        System.out.println("100 => ");
        int hh = sc.nextInt();
        arr[3] += hh;
        System.out.println("Total Amount:" + (dep = (tt * 2000) + (fh * 500) + (th * 200) + (hh * 100)));
        res += dep;
        u[index].balance += dep;
        u[index].statement += "Credited\t" + dep + "\t" + u[index].balance + "\t\t" + "deposited" + "\n";
        System.out.println("Amount deposited successfully");

    }

    public static boolean passcheck() {
        if (lock <= 3 && u[index].count < 3) {
            return true;
        }
        return false;
    }

    private static void withdraw() {
        System.out.println("Enter the amount to be withdrawn:");
        int wd = sc.nextInt();
        if (wd > u[index].balance)
            System.out.println("Amount exceeds balance");
        else if (wd > res)
            System.out.println("Sorry for the inconvenience..Try again later");
        else {
            if (!u[index].bank.equals("SBI")) {
                u[index].balance -= 20;
                System.out.println("Tax Amount deducted");
            }
            u[index].balance -= wd;
            res -= wd;
            int t = wd, temp = 0;
            temp = t / 2000;
            if (arr[0] < temp) {
                t = t % 2000;
                t += (temp * 2000) - (arr[0] * 2000);
                temp = arr[0];
                arr[0] = 0;
            } else if (arr[0] >= temp) {
                t = t % 2000;
                arr[0] -= temp;
            }
            System.out.print(temp + " ");
            temp = t / 500;
            if (arr[1] < temp) {
                t = t % 500;
                t += (temp * 500) - (arr[1] * 500);
                temp = arr[1];
                arr[1] = 0;
            } else if (arr[1] >= temp) {
                t = t % 500;
                arr[1] -= temp;
            }
            System.out.print(temp + " ");
            temp = t / 200;
            if (arr[2] < temp) {
                t = t % 200;
                t += (temp * 200) - (arr[2] * 200);
                temp = arr[2];
                arr[2] = 0;
            } else if (arr[2] >= temp) {
                t = t % 200;
                arr[2] -= temp;
            }
            System.out.print(temp + " ");
            temp = t / 100;
            if (arr[3] < temp) {
                t = t % 100;
                t += (temp * 100) - (arr[3] * 100);
                temp = arr[3];
                arr[3] = 0;
            } else if (arr[3] >= temp) {
                t = t % 100;
                arr[3] -= temp;
            }
            System.out.print(temp + " ");
            u[index].balance += t;
            res += t;
            u[index].statement += "Debited\t\t" + wd + "\t" + u[index].balance + "\t\t" + "Withdrawn" + "\n";
            System.out.println("Amount withdrawn successfully");
        }
    }

    public static void transfer() {
        int flag = 0;
        System.out.println("Enter the amount:");
        double t_amt = sc.nextDouble();
        if (t_amt < u[index].balance) {
            System.out.println("Enter account number:");
            String t_acc = sc.next();
            for (int i = 0; i < 5; i++) {
                flag++;
                if (t_acc.equals(u[i].acc_no)) {
                    u[index].balance -= t_amt;
                    u[i].balance += t_amt;
                    System.out.println("Account transfer successfull");
                    u[i].statement += "Credited\t\t" + t_amt + "\t" + u[i].balance + "\t\tFrom : " + u[index].acc_no
                            + "\n";
                    u[index].statement += "Debited\t\t" + t_amt + "\t" + u[index].balance + "\t\tTo : " + t_acc + "\n";
                    break;
                } else if (flag >= 5)
                    System.out.println("Wrong Account number");
            }
        } else {
            System.out.println("Not enough balance");
        }
    }

    public static void history() {
        String[] s = u[index].statement.split("\n");
        // System.out.println("Action" + "\t\t" + "Amount" + "\t" + "Balance" + "\t\t" +
        // "Via");
        if (s.length > 6) {
            int len = s.length - 6;
            for (int i = len; i < s.length; i++) {
                System.out.println(s[i]);
            }
        } else {
            System.out.println(u[index].statement);
        }
    }

    public static void changePass() {
        System.out.println("Enter old pin: ");
        String oldpass = sc.next();
        if (oldpass.equals(u[index].password)) {
            System.out.println("Enter New Password : ");
            String newpass = sc.next();
            u[index].password = newpass;
        } else {
            System.out.println("Incorrect old password");
        }
    }

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        int ch = 0;
        createUser();
        do {
            System.out.println("SBI ATM Machine");
            System.out.println("1.Admin Login");
            System.out.println("2.User Login");
            System.out.println("3.Exit");
            System.out.println("Enter your Choice");
            ch = sc.nextInt();
            System.out.print("\033[H\033[2J");
            System.out.flush();
            // File.flush();
            switch (ch) {
                case 1:
                    System.out.println("Enter the Admin ID: ");
                    String adminId = sc.next();
                    System.out.println("Enter the Admin password");
                    String pswd = sc.next();
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    if (adminId.equals("Amrith") && pswd.equals("1234")) {
                        int n = 0;
                        do {
                            System.out.println("------Welcome Admin-----");
                            System.out.println("1.Show Amount");
                            System.out.println("2.Add Amount");
                            System.out.println("3.Exit");
                            System.out.println("Enter Your Choice:");
                            n = sc.nextInt();
                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                            switch (n) {
                                case 1:
                                    showamount();
                                    System.out.println("Press Enter to Exit");
                                    sc.nextLine();
                                    String e1 = sc.nextLine();
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    break;
                                case 2:
                                    add();
                                    sc.nextLine();
                                    String e2 = sc.nextLine();
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    break;
                                case 3:
                                    System.out.println("Thank you admin");
                                    break;
                                default:
                                    System.out.println("Invalid input");
                                    break;
                            }

                        } while (n != 3);

                    } else {
                        System.out.println("Invalid Admin");
                    }
                    break;

                case 2:
                    System.out.println("Enter the User ID");
                    String id = sc.next();
                    System.out.println("Enter the User Password");
                    String password = sc.next();
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    if (check(u, id, password) && passcheck()) {
                        u[index].count = 0;
                        int opt = 0;
                        do {
                            System.out.println("-----Welcome " + id + "-----");
                            System.out.println("1.Balance");
                            System.out.println("2.Withdraw Amount");
                            System.out.println("3.Deposit Amount");
                            System.out.println("4.Change Password");
                            System.out.println("5.Cash Transfer");
                            System.out.println("6.Transaction History");
                            System.out.println("7.Exit");
                            System.out.println("Enter Your Choice:");
                            opt = sc.nextInt();
                            switch (opt) {
                                case 1:
                                    System.out.println("Balance amount of " + id + " : " + u[index].balance);
                                    sc.nextLine();
                                    String e9 = sc.nextLine();
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    break;
                                case 2: // withdraw
                                    System.out.println("Enter round values only");
                                    withdraw();
                                    sc.nextLine();
                                    String e1 = sc.nextLine();
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    break;
                                case 3: // deposit
                                    deposit();
                                    sc.nextLine();
                                    String e3 = sc.nextLine();
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    break;
                                case 4:
                                    changePass();
                                    sc.nextLine();
                                    String e6 = sc.nextLine();
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    break;
                                case 5:
                                    transfer();
                                    sc.nextLine();
                                    String e13 = sc.nextLine();
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    break;
                                case 6:
                                    history();
                                    sc.nextLine();
                                    String e14 = sc.nextLine();
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    break;

                                case 7:
                                    System.out.println("Thank you " + u[index].name);
                                    sc.nextLine();
                                    String e11 = sc.nextLine();
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    break;
                                default:
                                    System.out.println("invaid input");
                                    break;
                            }
                        } while (opt != 7);
                    } else {
                        // lock++;
                        u[index].count++;
                        if (u[index].count >= 3) {
                            System.out.println("Too many attemps to login..Please contact nearest branch");
                            lock = 0;
                        } else {
                            System.out.println("Invalid user Id or Password");
                            // System.out.println("Press enter to continue");
                        }
                    }
                    sc.nextLine();
                    String empty = sc.nextLine();
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
        } while (ch != 3);

    }
}

class Users {
    String name, password, ifsc, acc_no, statement, bank;
    int count;
    double balance;

    Users(String name, String password, double balance, String ifsc, String acc_no, String statement, String bank,
            int count) {
        this.name = name;
        this.password = password;
        this.balance = balance;
        this.ifsc = ifsc;
        this.acc_no = acc_no;
        this.statement = statement;
        this.bank = bank;
        this.count = count;
    }
}
