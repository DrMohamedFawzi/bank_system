import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    public static final int EXIT = 0;

    static final Scanner scanner = new Scanner(System.in);
    static final ArrayList<User> users = new ArrayList<>();

    public static void main(String[] args) {
        displayMainMenu();
    }

    public static boolean checkLogin(String username, String password) {
        return username.equals("admin") && password.equals("123456");
    }

    public static void displayMainMenu() {
        int v;
        do {

            System.out.println("1. دخول كأدمن");
            System.out.println("2. دخول كمستخدم");
            System.out.println("0. خروج");
            v = handleMainSelection(scanner.nextInt());

        } while (v != EXIT);
    }

    public static int handleMainSelection(int selection) {
        switch (selection) {
            case 1:
                handleAdministratorLogin();
                break;
            case 2:
                handleUserLogin();
                break;
            case EXIT:
                return 0;
            default:
                System.out.println("قيمة غير صحيحة");
                break;
        }
        return selection;
    }

    private static void handleAdministratorLogin() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("اسم المستخدم: ");
        String username = scanner.nextLine();
        System.out.print("كلمة المرور: ");
        String password = scanner.nextLine();

        if (checkLogin(username, password)) {
            displayAdminMenu();


        } else {
            System.out.println("اسم المستخدم او كلمة المرور غير صحيحة");
            System.out.println("1. حاول مرة أخرة");
            System.out.println("2. عودة للقائمة الرئيسية");
            int selection = scanner.nextInt();
            scanner.nextLine();

            if (selection == 1) {
                handleAdministratorLogin();
            } else {
                displayMainMenu();
            }
        }
    }

    private static void handleUserLogin() {
        System.out.print("أدخل رقم الحساب: ");
        int accountNumber = scanner.nextInt();
        scanner.nextLine();

        User user = null;
        for (User u : users) {
            if (u.getAccountNumber() == accountNumber) {
                user = u;
                break;
            }

        }
        if (user != null) {
            System.out.println("مرحبا :" + user.getName());
            displayUserMenu(user);
        } else {
            System.out.println("الحساب غير موجود");
            System.out.println("1. حاول مرة اخرى");
            System.out.println("2. عودة للقائمة الرئيسية");
            int selection = scanner.nextInt();
            scanner.nextLine();

            if (selection == 1) {
                handleUserLogin();
            } else {
                displayMainMenu();
            }
        }
    }

    public static void displayAdminMenu() {
        System.out.println("1. اضافة مستخدم");
        System.out.println("2.عرض كل المستخدمين");
        System.out.println("3. البحث عن مستخدم");
        System.out.println("4. تعديل مستخدم");
        System.out.println("5. حذف مستخدم");
        System.out.println("0. خروج للقائمة الرئيسية");

        handleAdminSelection(scanner.nextInt());
    }

    public static void addUser() {
        scanner.nextLine();
        System.out.print("أدخل الإسم: ");
        String name = scanner.nextLine();
        System.out.print("الهوية: ");
        String id = scanner.nextLine();
        System.out.print("العنوان: ");
        String address = scanner.nextLine();
        System.out.print("المبلغ المالي: ");
        double money = scanner.nextDouble();
        if (money < 0) money = 0;
        System.out.print("رقم الحساب: ");
        int accountNumber = scanner.nextInt();
        scanner.nextLine();

        User user = new User(name, id, address, money, accountNumber);
        users.add(user);
        System.out.println("تمت الإضافة بنجاح");

        adminRepeatMenu();
        int i = scanner.nextInt();
        if (i == 1) {
            addUser();
        } else if (i == 2) {
            displayAdminMenu();
        } else {
            System.out.println("عملية غير صحيحة");
            adminRepeatMenu();
        }


    }


    public static void handleAdminSelection(int selection) {
        switch (selection) {
            case 1:
                addUser();
                break;
            case 2:
                showAllUsers();
                break;
            case 3:
                findByAccountNumber();
                break;
            case 4:
                updateUserAccount();
                break;
            case 5:
                deleteUserAccount();
                break;
            case EXIT:
                displayMainMenu();
                break;
            default:
                System.out.println("Invalid selection. Please try again.");
                break;
        }
    }

    private static void showAllUsers() {
        if (users.size() > 0) for (User user : users) {
            System.out.println(user);
        }
        else {
            System.out.println("لم يتم اضافة اي مستخدمين");
        }
        displayAdminMenu();

    }

    private static void findByAccountNumber() {
        System.out.println("أدخل رقم الحساب للعرض");
        int accountNumber = scanner.nextInt();
        if (users.size() > 0) for (User user : users) {
            if (user.getAccountNumber() == accountNumber) {
                System.out.println(user);
                System.out.println();
                adminRepeatMenu();
                scanner.nextLine();
                int i = scanner.nextInt();
                if (i == 1) {
                    findByAccountNumber();
                } else if (i == 2) {
                    displayAdminMenu();
                } else {
                    System.out.println("قيمة غير صحيحة");
                }
                return;
            }
        }
        else System.out.println("لا يوجد اي حسابات");
        System.out.println("لا يوجد اي حساب بهذا الرقم");
        displayAdminMenu();


    }

    private static void updateUserAccount() {
        System.out.println("أدخل رقم الحساب لتحديث البيانات");
        int accountNumber = scanner.nextInt();
        if (users.size() > 0) for (User user : users) {
            if (user.getAccountNumber() == accountNumber) {
                scanner.nextLine();
                System.out.print("أدخل الإسم: ");
                user.setName(scanner.nextLine());
                System.out.print("الهوية: ");
                user.setId(scanner.nextLine());
                System.out.print("العنوان: ");
                user.setAddress(scanner.nextLine());
                System.out.print("المبلغ المالي: ");
                user.setMoney(scanner.nextDouble());
                System.out.println("تم تحديث البيانات");

                adminRepeatMenu();
                scanner.nextLine();
                int i = scanner.nextInt();
                if (i == 1) {
                    updateUserAccount();
                } else if (i == 2) {
                    displayAdminMenu();
                } else {
                    System.out.println("قيمة غير صحيحة");
                }
                return;
            }
        }
        else {
            System.out.println("لا يوجد اي حسابات");
        }
        System.out.println("لا يوجد اي حساب بهذا الرقم");

        displayAdminMenu();

    }

    private static void deleteUserAccount() {
        System.out.println("أدخل رقم الحساب للحذف");
        int accountNumber = scanner.nextInt();
        if (users.size() > 0) for (User user : users) {
            if (user.getAccountNumber() == accountNumber) {
                users.remove(user);
                adminRepeatMenu();
                scanner.nextLine();
                int i = scanner.nextInt();
                if (i == 1) {
                    deleteUserAccount();
                } else if (i == 2) {
                    displayAdminMenu();
                } else {
                    System.out.println("قيمة غير صحيحة");
                }
                return;
            }
        }
        else System.out.println("لا يوجد اي حسابات");
        System.out.println("لا يوجد اي حساب بهذا الرقم");

        displayAdminMenu();

    }

    private static void adminRepeatMenu() {
        System.out.println("1 . تكرار العملية");
        System.out.println("2 . العودة لقائمة المسؤول");
    }

    private static void userRepeatMenu() {
        System.out.println("1 . تكرار العملية");
        System.out.println("2 . العودة لقائمة المستخدم");
    }

    public static void displayUserMenu(User user) {
        int selected;
        do {
            System.out.println("1. عرض بيانات المستخدم");
            System.out.println("2. سحب أموال");
            System.out.println("3. إيداع أموال ");
            System.out.println("0. خروج");

            selected = scanner.nextInt();
            handleUserSelection(selected, user);

        } while (selected != 0);

    }

    public static void handleUserSelection(int selection, User user) {
        switch (selection) {
            case 1:
                System.out.println(user);

                break;
            case 2:
                withdraw(user);
                break;

            case 3:
                deposit(user);
                break;
            case EXIT:
                System.out.println("تم الخروج من الحساب");
                displayMainMenu();
                break;
            default:
                System.out.println("قيمة غير صحيحة");
                break;
        }
    }

    private static void deposit(User user) {
        System.out.println("الرصيد: " + user.getMoney());
        System.out.println("أدخل الكمية المراد إيداعها");
        int money = scanner.nextInt();
        if (money > 0) {
            user.setMoney(user.getMoney() - money);
            System.out.println("تم إيداع مبلغ " + money);
            System.out.println("الرصيد الحالي : " + user.getMoney());
        } else {
            System.out.println("فشلت عملية الإيداع");
            System.out.println("الرصيد المدخل بالسالب ");
            System.out.println("الرصيد: " + user.getMoney());
        }
        userRepeatMenu();
        scanner.nextLine();
        int i = scanner.nextInt();
        if (i == 1) {
            deposit(user);
        } else if (i == 2) {
            displayUserMenu(user);
        } else {
            System.out.println("قيمة غير صحيحة");
        }
    }


    private static void withdraw(User user) {
        System.out.println("الرصيد: " + user.getMoney());
        System.out.println("أدخل الكمية المراد سحبها");
        int money = scanner.nextInt();
        if (user.getMoney() >= money) {
            user.setMoney(user.getMoney() - money);
            System.out.println("تم سحب مبلغ " + money);
            System.out.println("الرصيد المتبقي : " + user.getMoney());
        } else {
            System.out.println("فشلت عملية السحب");

            System.out.println("الرصيد غير كافي ");
            System.out.println("الرصيد: " + user.getMoney());
        }
        userRepeatMenu();
        scanner.nextLine();
        int i = scanner.nextInt();
        if (i == 1) {
            withdraw(user);
        } else if (i == 2) {
            displayUserMenu(user);
        } else {
            System.out.println("قيمة غير صحيحة");
        }
    }

}


