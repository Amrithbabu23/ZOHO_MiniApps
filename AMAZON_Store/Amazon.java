package Amazon_Store;

import java.util.*;

public class Amazon {
    private static Scanner sc;
    static int index = 0, u_index = 0;
    static int id_count = 1004, approval_count = 0, product_id = 208, user_count = 3, order_count = 100;
    static ArrayList<Merchant> m_list = new ArrayList<>();
    static ArrayList<Merchant> app_list = new ArrayList<>();
    static ArrayList<Products> pro_list = new ArrayList<>();
    static ArrayList<Buyers> u_list = new ArrayList<>();
    static ArrayList<Orders> o_list = new ArrayList<>();
    static ArrayList<Cart> cart_list = new ArrayList<>();
    static ArrayList<Cart> history_list = new ArrayList<>();

    public static void home() {
        System.out.println("-------AMAZON STORE-------");
        System.out.println("1.Admin");
        System.out.println("2.Merchant");
        System.out.println("3.User");
        System.out.println("4.Exit");
        System.out.println("Please choose any of the option");
        int ch = sc.nextInt();
        switch (ch) {
            case 1:
                admin();
                break;
            case 2:
                merchant();
                break;
            case 3:
                user();
                break;
            case 4:
                System.out.println("Thank you for visiting Amazon");
                System.exit(0);
            default:
                home();
                break;

        }

    }

    private static void user() {
        System.out.println("1.New User:");
        System.out.println("2.Existent User");
        System.out.println("3.Goto Home");
        int u_ch = sc.nextInt();
        switch (u_ch) {
            case 1:
                signupUser();
                break;
            case 2:
                loginUser();
                break;
            case 3:
                String e9 = sc.nextLine();
                System.out.print("\033[H\033[2J");
                System.out.flush();
                home();
                break;
            default:
                String e19 = sc.nextLine();
                System.out.print("\033[H\033[2J");
                System.out.flush();
                user();
                break;

        }
    }

    private static void loginUser() {
        System.out.println("Enter Username");
        String l_name = sc.next();
        System.out.println("Enter Password");
        String l_pass = sc.next();
        if (checkUser(l_name, l_pass)) {
            String e21 = sc.nextLine();
            System.out.print("\033[H\033[2J");
            System.out.flush();
            orgUser();
        } else {
            System.out.println("Please enter valid credentials");
            String e22 = sc.nextLine();
            System.out.print("\033[H\033[2J");
            System.out.flush();
            user();
        }
    }

    private static void orgUser() {
        System.out.println("1.Order new items");
        System.out.println("2.View order history");
        System.out.println("3.View Cart");
        System.out.println("4.View wallet");
        System.out.println("5.Cancel Orders");
        System.out.println("6.Order Details");
        System.out.println("7.Compare Products");
        System.out.println("8.Goto home");
        int ou_ch = sc.nextInt();
        switch (ou_ch) {
            case 1:
                String e22 = sc.nextLine();
                System.out.print("\033[H\033[2J");
                System.out.flush();
                newOrder();
                break;
            case 2:
                String e23 = sc.nextLine();
                System.out.print("\033[H\033[2J");
                System.out.flush();
                orderHistory();
                break;
            case 3:
                String e25 = sc.nextLine();
                System.out.print("\033[H\033[2J");
                System.out.flush();
                viewCart();
                break;

            case 4:
                // System.out.print("\033[H\033[2J");
                // System.out.flush();
                viewWallet();
                break;
            case 5:
                String e34 = sc.nextLine();
                System.out.print("\033[H\033[2J");
                System.out.flush();
                cancelOrder();
                break;
            case 6:
                String e36 = sc.nextLine();
                System.out.print("\033[H\033[2J");
                System.out.flush();
                orderDetails();
                break;
            case 7:
                String e37 = sc.nextLine();
                System.out.print("\033[H\033[2J");
                System.out.flush();
                compareProduct();
                break;
            case 8:
                System.out.println("Thank you!");
                sc.nextLine();
                String e24 = sc.nextLine();
                System.out.print("\033[H\033[2J");
                System.out.flush();
                home();
                break;
            default:
                System.out.print("\033[H\033[2J");
                System.out.flush();
                orgUser();
                break;
        }
    }

    private static void compareProduct() {
        System.out.println("Enter product Type to be Compared");
        String type_com = sc.next();
        int com_flag = 0;
        System.out.printf("%-10s || %-15s || %-20s ||%-25s || %-15s|| %-15s || %-10s\n", "ProductID",
                "Product Type",
                "Product Brand", "Product Details", "Product Price", "Product Discount", "MerchantID");
        for (int i = 0; i < pro_list.size(); i++) {
            if (pro_list.get(i).pro_type.equals(type_com)) {
                System.out.printf("%-10s||%-15s||%-20s||%-25s||%-15s||%-15s||%-10s\n", pro_list.get(i).pro_id,
                        pro_list.get(i).pro_type, pro_list.get(i).pro_brand, pro_list.get(i).pro_des,
                        pro_list.get(i).pro_price, pro_list.get(i).pro_dis, pro_list.get(i).mer_id);
                com_flag++;
            }
        }
        if (com_flag == 0) {
            System.out.println("Product of this type does'nt exist");
        }
        System.out.println("Press Enter to go back");
        sc.nextLine();
        String e38 = sc.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        orgUser();
    }

    private static void orderDetails() {
        System.out.printf("%-9s||%-9s||%-9s\n", "Ord_ID", "U-ID", "pro_ID");
        for (int i = 0; i < o_list.size(); i++) {
            if (o_list.get(i).u_id == u_list.get(u_index).u_id) {
                System.out.printf("%-9s||%-9s||%-9s\n", o_list.get(i).order_id, o_list.get(i).u_id,
                        o_list.get(i).pro_id);
            }
        }
        System.out.println("Press Enter to go back");
        sc.nextLine();
        String e24 = sc.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        orgUser();
    }

    private static void cancelOrder() {
        System.out.println("Enter the Product_ID to be cancelled");
        int can_prod = sc.nextInt();
        int can_flag = 0;
        for (int i = 0; i < history_list.size(); i++) {
            if (history_list.get(i).u_id == u_list.get(u_index).u_id) {
                if (history_list.get(i).pro_id == can_prod) {
                    history_list.remove(i);
                    can_flag++;
                }
            }
        }
        for (int i = 0; i < o_list.size(); i++) {
            if (o_list.get(i).u_id == o_list.get(u_index).u_id) {
                if (o_list.get(i).pro_id == can_prod) {
                    o_list.remove(i);
                }
            }
        }
        if (can_flag == 0) {
            System.out.println("No items will such product_ID is ordered");
            sc.nextLine();
            String e24 = sc.nextLine();
            System.out.print("\033[H\033[2J");
            System.out.flush();
            orgUser();
        } else {
            System.out.println("Order Cancelled Successfully");
            sc.nextLine();
            String e24 = sc.nextLine();
            System.out.print("\033[H\033[2J");
            System.out.flush();
            orgUser();
        }
    }

    private static void orderHistory() {
        int his_cnt = 0;
        for (int i = 0; i < history_list.size(); i++) {
            if (history_list.get(i).u_id == u_list.get(u_index).u_id) {
                System.out.printf("%-10s||%-15s||%-20s||%-25s||%-15s||%-15s||%-10s\n", history_list.get(i).pro_id,
                        history_list.get(i).pro_type, history_list.get(i).pro_brand, history_list.get(i).pro_des,
                        history_list.get(i).pro_price, history_list.get(i).pro_dis, history_list.get(i).mer_id);
                his_cnt++;
            }
        }
        if (his_cnt == 0) {
            System.out.println("No items purchased so far...");
        }
        String e24 = sc.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        orgUser();
    }

    private static void viewCart() {
        int cart_flag = 0;
        for (int i = 0; i < cart_list.size(); i++) {
            if (cart_list.get(i).u_id == u_list.get(u_index).u_id) {
                System.out.printf("%-10s||%-15s||%-20s||%-25s||%-15s||%-15s||%-10s\n", cart_list.get(i).pro_id,
                        cart_list.get(i).pro_type, cart_list.get(i).pro_brand, cart_list.get(i).pro_des,
                        cart_list.get(i).pro_price, cart_list.get(i).pro_dis, cart_list.get(i).mer_id);
                cart_flag++;
            }
        }
        if (cart_flag == 0) {
            System.out.println("No Items in Cart");
            sc.nextLine();
        } else {
            System.out.println("Press (1) to Buy items in cart....(2) to remove items from cart");
            String buy = sc.next();
            double amount = 0, dis = 0;
            if (buy.equals("1")) {
                for (int i = 0; i < cart_list.size(); i++) {
                    if (cart_list.get(i).u_id == u_list.get(u_index).u_id) {
                        dis = ((cart_list.get(i).pro_dis) / 100) * cart_list.get(i).pro_price;
                        amount += (cart_list.get(i).pro_price) - dis;
                    }
                }
                System.out.println("Total Amount:" + amount);
                System.out.println("1.Pay through cash");
                System.out.println("2.Pay through wallet");
                int cash_ch = sc.nextInt();
                switch (cash_ch) {
                    case 1:
                        u_list.get(u_index).u_orders++;
                        int size = cart_list.size();
                        for (int i = 0; i < size; i++) {
                            if (cart_list.get(0).u_id == u_list.get(u_index).u_id) {
                                history_list.add(cart_list.get(0));
                                o_list.add(new Orders(order_count, u_list.get(u_index).u_id, cart_list.get(0).pro_id));
                                order_count++;
                                cart_list.remove(0);
                            }
                        }
                        System.out.println("Purchase successfull");
                        orgUser();
                        String e28 = sc.nextLine();
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        break;
                    case 2:
                        if (amount > u_list.get(u_index).u_wallet) {
                            System.out.println("Not enough balance in wallet>>Pay through cash instead");
                            viewCart();
                            String e29 = sc.nextLine();
                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                        } else {
                            u_list.get(u_index).u_wallet -= amount;
                            u_list.get(u_index).u_orders++;
                            int size1 = cart_list.size();
                            for (int i = 0; i < size1; i++) {
                                if (cart_list.get(0).u_id == u_list.get(u_index).u_id) {
                                    history_list.add(cart_list.get(0));
                                    o_list.add(
                                            new Orders(order_count, u_list.get(u_index).u_id, cart_list.get(0).pro_id));
                                    order_count++;
                                    cart_list.remove(0);
                                }
                            }
                            System.out.println("Purchase Successfull");
                            orgUser();
                            String e30 = sc.nextLine();
                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                        }
                        break;
                    default:
                        viewCart();
                        break;

                }
            } else if (buy.equals("2")) {
                System.out.println("Enter Product_ID of the item to be removed: ");
                int rem_prod = sc.nextInt();
                int rem_flag = 0;
                for (int i = 0; i < cart_list.size(); i++) {
                    if (cart_list.get(i).u_id == u_list.get(u_index).u_id) {
                        if (cart_list.get(i).pro_id == rem_prod) {
                            cart_list.remove(i);
                            rem_flag++;
                        }
                    }
                }
                if (rem_flag == 0) {
                    System.out.println("No tems match with this product_ID");
                } else {
                    System.out.println("Item removed from Cart Successfully");
                }
                sc.nextLine();
                String e27 = sc.nextLine();
                System.out.print("\033[H\033[2J");
                System.out.flush();
                orgUser();
            } else {
                orgUser();
                // String e27 = sc.nextLine();
                // System.out.print("\033[H\033[2J");
                // System.out.flush();
            }
        }
        orgUser();
    }

    private static void newOrder() {
        System.out.printf("%-10s || %-15s || %-20s ||%-25s || %-15s|| %-15s || %-10s\n", "Product_ID",
                "Product Type",
                "Product Brand", "Product Details", "Product Price", "Product Discount", "Merchant_ID");
        for (int i = 0; i < pro_list.size(); i++) {
            System.out.printf("%-10s||%-15s||%-20s||%-25s||%-15s||%-15s||%-10s\n", pro_list.get(i).pro_id,
                    pro_list.get(i).pro_type, pro_list.get(i).pro_brand, pro_list.get(i).pro_des,
                    pro_list.get(i).pro_price, pro_list.get(i).pro_dis, pro_list.get(i).mer_id);
        }
        System.out.println("Enter the Product_ID of the product you wish to purchase:");
        int or_ch = sc.nextInt();
        int or_check = 0;
        for (int j = 0; j < pro_list.size(); j++) {
            if (pro_list.get(j).pro_id == or_ch) {
                cart_list.add(new Cart(u_list.get(u_index).u_id, pro_list.get(j).pro_id,
                        pro_list.get(j).pro_type, pro_list.get(j).pro_brand, pro_list.get(j).pro_des,
                        pro_list.get(j).pro_price, pro_list.get(j).pro_dis, pro_list.get(j).mer_id));
                or_check = j;
            }
        }
        if (or_check == 0) {
            System.out.println("Enter valid Product_ID");
            String e26 = sc.nextLine();
            System.out.print("\033[H\033[2J");
            System.out.flush();
            newOrder();
        } else {
            System.out.println("Item added to cart Successfully");
            // String e27 = sc.nextLine();
            // System.out.print("\033[H\033[2J");
            // System.out.flush();
            orgUser();
        }
    }

    private static void viewWallet() {
        System.out.println("Balance Amount in wallet: ");
        System.out.println(u_list.get(u_index).u_wallet);
        sc.nextLine();
        String e31 = sc.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        orgUser();
    }

    private static boolean checkUser(String l_name, String l_pass) {
        for (int i = 0; i < u_list.size(); i++) {
            if (u_list.get(i).u_name.equals(l_name) && u_list.get(i).u_password.equals(l_pass)) {
                u_index = i;
                return true;
            }
        }
        return false;
    }

    private static void signupUser() {
        System.out.println("Enter Username: ");
        String s_user = sc.next();
        System.out.println("Enter Password: ");
        String s_pass = sc.next();
        System.out.println("Enter Wallet amount: ");
        double s_wallet = sc.nextDouble();
        u_list.add(new Buyers(user_count, s_user, s_pass, s_wallet, 0));
        user_count++;
        System.out.println("Account created Successfully");
        String e20 = sc.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        user();
    }

    private static void merchant() {
        System.out.println("1.Existing Merchant");
        System.out.println("2.New Merchant");
        System.out.println("3.Exit");
        System.out.println("Enter your choice:");
        int mer = sc.nextInt();
        switch (mer) {
            case 1:
                loginMechant();
                break;
            case 2:
                signupMerchant();
                break;
            case 3:
                home();
                break;

        }
    }

    private static void signupMerchant() {
        System.out.println("Enter Merchant Name : ");
        String s_name = sc.next();
        System.out.println("Enter Merchant Password : ");
        String s_pass = sc.next();
        approval_count++;
        Merchant obj = new Merchant(id_count, s_name, s_pass, "not approved");
        app_list.add(obj);
        id_count++;
        System.out.println(app_list.get(0));
        System.out.println("Waiting for admin approval");
        System.out.println("Press Enter to continue");
        String e9 = sc.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        merchant();
    }

    private static void loginMechant() {
        System.out.println("Enter Merchant_ID : ");
        int m_id = sc.nextInt();
        System.out.println("Enter Merchant Password : ");
        String m_pass = sc.next();
        if (checkMer(m_id, m_pass)) {
            orgMer();
        } else if (waitMer(m_id, m_pass)) {
            System.out.println("Waiting for approval...");
            System.out.println("Press Enter to exit");
            sc.nextLine();
            String e5 = sc.nextLine();
            System.out.print("\033[H\033[2J");
            System.out.flush();
            merchant();
        } else {
            System.out.println("Please recheck Merchant_Id and pasword");
            System.out.println("Press Enter to exit");
            sc.nextLine();
            String e6 = sc.nextLine();
            System.out.print("\033[H\033[2J");
            System.out.flush();
            merchant();
        }
    }

    private static boolean waitMer(int m_id, String m_pass) {
        for (int i = 0; i < app_list.size(); i++) {
            if (app_list.get(i).mer_id == m_id && app_list.get(i).mer_password.equals(m_pass)) {
                return true;
            }
        }
        return false;
    }

    private static void orgMer() {
        System.out.println("WELCOME MERCHANT" + m_list.get(index).mer_name);
        System.out.println("1.Add Product");
        System.out.println("2.Modify Product");
        System.out.println("3.Remove Product");
        System.out.println("4.View Product");
        System.out.println("5.Exit");
        System.out.println("Please choose any of the option");
        int ch3 = sc.nextInt();
        switch (ch3) {
            case 1:
                addProduct();
                break;
            case 2:
                modifyProduct();
                break;
            case 3:
                removeProduct();
                break;
            case 4:
                viewProduct();
                break;
            case 5:
                System.out.println("Thank you " + m_list.get(index).mer_name);
                merchant();
                break;
            default:
                orgMer();
                break;

        }
    }

    public static void productList() {
        pro_list.add(new Products(200, "Mobiles", "OPPO", "RAM-16GB ROM-256GB", 1001, 32000, 5));
        pro_list.add(new Products(201, "Tablets", "Samsung", "RAM-32GB ROM-512GB", 1002, 45999, 7));
        pro_list.add(new Products(202, "Mobiles", "Vivo", "RAM-32GB ROM-256GB", 1001, 29999, 2));
        pro_list.add(new Products(203, "Book", "Robin Sharma", "Motivational - Life story", 1003, 2999, 10));
        pro_list.add(new Products(204, "Laptop", "HP", "SSD - 128GB HDD-1TB", 1004, 72000, 3));
        pro_list.add(new Products(205, "Ear pods", "Sony", "Mulit-3D-surround quality", 1005, 2000, 0));
        pro_list.add(new Products(206, "Laptop", "Mac Book", "SSD - 256GB HDD- 1TB", 1002, 98000, 3));
        pro_list.add(new Products(207, "Book", "MArk Twain", "Greatest Humarised world", 1003, 5000, 0));
    }

    private static void addProduct() {
        System.out.println("Enter product type: ");
        String p_type = sc.next();
        System.out.println("Enter product brand: ");
        String p_brand = sc.next();
        System.out.println("Enter product description: ");
        String p_des = sc.next();
        sc.nextLine();
        System.out.println("Enter product price: ");
        double p_price = sc.nextDouble();
        System.out.println("Enter product discount: ");
        double p_dis = sc.nextDouble();
        pro_list.add(new Products(product_id, p_type, p_brand, p_des, m_list.get(index).mer_id, p_price, p_dis));
        product_id++;
        System.out.println("Product added Successfully");
        System.out.println("Press Enter to exit");
        sc.nextLine();
        String e11 = sc.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        orgMer();

    }

    private static void modifyProduct() {
        System.out.println("Enter the product_Id to be modified");
        int modify_id = sc.nextInt();
        int mod_check = 0;
        for (int i = 0; i < pro_list.size(); i++) {
            if (pro_list.get(i).pro_id == modify_id && pro_list.get(i).mer_id == m_list.get(index).mer_id) {
                mod_check = 1;
                crtModify(modify_id);
            }
        }
        if (mod_check == 0) {
            System.out.println("Enter valid Product_ID");
            System.out.println("Press Enter to exit");
            sc.nextLine();
            String e14 = sc.nextLine();
            System.out.print("\033[H\033[2J");
            System.out.flush();
            orgMer();

        }
    }

    private static void crtModify(int modify_id) {
        System.out.println("1.Modify Price: ");
        System.out.println("2.Modify discount: ");
        System.out.println("3.Modify product details");
        System.out.println("4.Go to Merchant page");
        System.out.println("Please choose any of the option");
        int mod_ch = sc.nextInt();
        switch (mod_ch) {
            case 1:
                modPrice(modify_id);
                break;
            case 2:
                modDis(modify_id);
                break;
            case 3:
                modDes(modify_id);
                break;
            case 4:
                orgMer();
                break;
            default:
                crtModify(modify_id);
                sc.nextLine();
                String e15 = sc.nextLine();
                System.out.print("\033[H\033[2J");
                System.out.flush();
                break;
        }
    }

    private static void modDes(int modify_id) {
        String cur_des = "";
        int des_index = 0;
        for (int i = 0; i < pro_list.size(); i++) {
            if (pro_list.get(i).pro_id == modify_id) {
                cur_des = pro_list.get(i).pro_des;
                des_index = i;
            }
        }
        System.out.println("Enter the new details for the PRODUCT " + pro_list.get(des_index).pro_brand
                + " with current description " + cur_des);
        sc.nextLine();
        String mod_des = sc.nextLine();
        pro_list.get(des_index).pro_des = mod_des;
        System.out.println("Description modified successfully");
        String e17 = sc.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        orgMer();
    }

    private static void modDis(int modify_id) {
        double curr_dis = 0;
        int dis_index = 0;
        for (int i = 0; i < pro_list.size(); i++) {
            if (pro_list.get(i).pro_id == modify_id) {
                curr_dis = pro_list.get(i).pro_dis;
                dis_index = i;
            }
        }
        System.out.println("Current discount for the product: " + curr_dis);
        System.out.println("Enter new discount:");
        double dis_modify = sc.nextDouble();
        pro_list.get(dis_index).pro_dis = dis_modify;
        System.out.println("Old discount " + curr_dis + " modified to " + pro_list.get(dis_index).pro_dis);
        sc.nextLine();
        String e16 = sc.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        orgMer();
    }

    private static void modPrice(int modify_id) {
        System.out.println("1.Increase price");
        System.out.println("2.Decrease price");
        System.out.println("3.Go back");
        int pr_ch = sc.nextInt();
        switch (pr_ch) {
            case 1:
                System.out.println("Enter amount to be increased:");
                double i_amt = sc.nextDouble();
                for (int i = 0; i < pro_list.size(); i++) {
                    if (pro_list.get(i).pro_id == modify_id) {
                        pro_list.get(i).pro_id += i_amt;
                    }
                }
                System.out.println("Updated Successfully");
                sc.nextLine();
                String e15 = sc.nextLine();
                System.out.print("\033[H\033[2J");
                System.out.flush();
                orgMer();
                break;
            case 2:
                System.out.println("Enter amount to be decreased");
                double d_amt = sc.nextDouble();
                for (int i = 0; i < pro_list.size(); i++) {
                    if (pro_list.get(i).pro_id == modify_id) {
                        pro_list.get(i).pro_id -= d_amt;
                    }
                }
                System.out.println("Updated Successfully");
                sc.nextLine();
                String e18 = sc.nextLine();
                System.out.print("\033[H\033[2J");
                System.out.flush();
                orgMer();
                break;
            case 3:
                crtModify(modify_id);
                break;
            default:
                modPrice(modify_id);
                break;
        }

    }

    private static void removeProduct() {
        int rem_count = 0;
        System.out.println("Enter the product name");
        String r_brand = sc.next();
        for (int i = 0; i < pro_list.size(); i++) {
            if (pro_list.get(i).pro_brand.equals(r_brand) && pro_list.get(i).mer_id == m_list.get(index).mer_id) {
                pro_list.remove(i);
                rem_count = 0;
                System.out.println("Item removed Successfully");
            }
        }
        if (rem_count == 0) {
            System.out.println("Product not found");
        }
        sc.nextLine();
        String e13 = sc.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        orgMer();
    }

    private static void viewProduct() {
        int curr_mer = m_list.get(index).mer_id;
        ArrayList<Products> view_list = new ArrayList<>();
        for (int i = 0; i < pro_list.size(); i++) {
            if (pro_list.get(i).mer_id == curr_mer) {
                view_list.add(pro_list.get(i));
            }
        }
        display(view_list);
    }

    private static void display(ArrayList<Products> view_list) {
        if (view_list.size() > 0) {
            System.out.printf("%-10s || %-15s || %-20s ||%-25s || %-15s|| %-15s || %-10s\n", "ProductID",
                    "Product Type",
                    "Product Brand", "Product Details", "Product Price", "Product Discount", "MerchantID");
            for (int i = 0; i < view_list.size(); i++) {
                System.out.printf("%-10s||%-15s||%-20s||%-25s||%-15s||%-15s||%-10s\n", view_list.get(i).pro_id,
                        view_list.get(i).pro_type, view_list.get(i).pro_brand, view_list.get(i).pro_des,
                        view_list.get(i).pro_price, view_list.get(i).pro_dis, view_list.get(i).mer_id);
            }
        } else {
            System.out.println("No products available");
        }
        sc.nextLine();
        String e14 = sc.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        orgMer();
    }

    private static boolean checkMer(int m_id, String m_pass) {
        for (int i = 0; i < m_list.size(); i++) {
            if (m_list.get(i).mer_id == m_id && m_list.get(i).mer_password.equals(m_pass)) {
                index = i;
                return true;
            }
        }
        return false;
    }

    private static void admin() {
        System.out.println("Enter Admin_ID : ");
        String a_id = sc.next();
        System.out.println("Enter Admin Password : ");
        String a_pass = sc.next();
        if (a_id.equals("Amrith") && a_pass.equals("1234")) {
            amazonAdmin();
        } else {
            System.out.println("Enter valid Admin credentials");
            admin();
        }
        sc.nextLine();
        String e3 = sc.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void amazonAdmin() {
        System.out.println("-------Welcome Admin Ji-------");
        System.out.println("1.Merchant Approval");
        System.out.println("2.Add Merchant");
        System.out.println("3.Remove Merchant");
        System.out.println("4.Exit");
        System.out.println("Enter your choice:");
        int opt = sc.nextInt();
        sc.nextLine();
        String e4 = sc.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        switch (opt) {
            case 1:
                approval();
                break;
            case 2:
                addMerchant();
                break;
            case 3:
                remMerchant();
                break;
            case 4:
                System.out.println("Thank You!");
                home();
                break;
            default:
                amazonAdmin();
                break;
        }
    }

    private static void approval() {
        if (approval_count > 0) {
            System.out.println("Approval pending..Press (1) to check");
            String ch1 = sc.next();
            if (ch1 == "1") {
                for (int i = 0; i < approval_count; i++) {
                    System.out.println(app_list.get(i));
                    System.out.println("Press (1) to approve (0) to decline");
                    String ch2 = sc.next();
                    if (ch2 == "1") {
                        System.out.println("New Merchant " + app_list.get(i).mer_name + " added");
                        app_list.get(i).mer_approval = "approved";
                        m_list.add(app_list.get(i));
                        app_list.remove(i);
                    } else if (ch2 == "0") {
                        System.out.println("New Merchant Suspended");
                        app_list.remove(i);
                    } else {
                        System.out.println("Please provide valid input");
                        approval();
                    }
                }
            }
        } else {
            System.out.println("No Approvals pending");
        }
        System.out.println("Press Enter to exit");
        String e1 = sc.nextLine();
        sc.nextLine();
        String e2 = sc.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        amazonAdmin();
    }

    private static void addMerchant() {
        System.out.println("Enter Merchant name:");
        String a_mer = sc.next();
        System.out.println("Enter Merchant password:");
        String a_pass = sc.next();
        Merchant addmer = new Merchant(id_count, a_mer, a_pass, "approved");
        id_count++;
        m_list.add(addmer);
        System.out.println("Merchant added successfully");
        sc.nextLine();
        System.out.println("Press Enter to continue");
        String e7 = sc.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        amazonAdmin();

    }

    private static void remMerchant() {
        int flag = 1;
        System.out.println("Enter Merchant_ID:");
        int r_id = sc.nextInt();
        for (int i = 0; i < m_list.size(); i++) {
            if (m_list.get(i).mer_id == r_id) {
                System.out.println("Merchant " + m_list.get(i).mer_name + " removed successfully");
                m_list.remove(i);
                flag = 0;
            }
        }
        if (flag == 1) {
            System.out.println("No such Merchant_ID found");
        }
        System.out.println("Press Enter to continue");
        String e8 = sc.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        amazonAdmin();
    }

    public static void main(String[] args) {
        merchantList();
        productList();
        userList();
        sc = new Scanner(System.in);
        home();

    }

    private static void merchantList() {
        m_list.add(new Merchant(1001, "Niveth", "1234", "approved"));
        m_list.add(new Merchant(1002, "Aswin", "2345", "approved"));
        m_list.add(new Merchant(1003, "Lal", "3456", "approved"));
    }

    private static void userList() {
        u_list.add(new Buyers(1, "Sanjay", "1234", 40000, 0));
        u_list.add(new Buyers(2, "Kumar", "2345", 3000, 0));
    }
}

class Buyers {
    int u_id, u_orders;
    String u_name, u_password;
    double u_wallet;

    Buyers(int u_id, String u_name, String u_password, double u_wallet, int u_orders) {
        this.u_id = u_id;
        this.u_name = u_name;
        this.u_password = u_password;
        this.u_wallet = u_wallet;
        this.u_orders = u_orders;
    }

}

class Orders {
    int order_id, u_id, pro_id;

    Orders(int order_id, int u_id, int pro_id) {
        this.order_id = order_id;
        this.u_id = u_id;
        this.pro_id = pro_id;
    }
}

class Merchant extends Amazon {
    int mer_id;
    String mer_name, mer_password, mer_approval;

    Merchant(int mer_id, String mer_name, String mer_password, String mer_approval) {
        this.mer_id = mer_id;
        this.mer_name = mer_name;
        this.mer_password = mer_password;
        this.mer_approval = mer_approval;
    }

    public String toString() {
        return mer_id + " " + mer_name + " " + mer_approval;
    }

}

class Products extends Amazon {
    int pro_id, mer_id;
    String pro_type, pro_brand, pro_des;
    double pro_dis, pro_price;

    Products(int pro_id, String pro_type, String pro_brand, String pro_des, int mer_id, double pro_price,
            double pro_dis) {
        this.pro_id = pro_id;
        this.pro_type = pro_type;
        this.pro_brand = pro_brand;
        this.pro_des = pro_des;
        this.pro_dis = pro_dis;
        this.pro_price = pro_price;
        this.mer_id = mer_id;
    }

}

class Cart extends Amazon {
    int pro_id, mer_id, u_id;
    String pro_type, pro_brand, pro_des;
    double pro_dis, pro_price;

    Cart(int u_id, int pro_id, String pro_type, String pro_brand, String pro_des, double pro_price,
            double pro_dis, int mer_id) {
        this.u_id = u_id;
        this.pro_id = pro_id;
        this.pro_type = pro_type;
        this.pro_brand = pro_brand;
        this.pro_des = pro_des;
        this.pro_dis = pro_dis;
        this.pro_price = pro_price;
        this.mer_id = mer_id;
    }

}
