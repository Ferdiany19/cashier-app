import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import dao.MenuDao;
import dao.OrderDao;
import models.Menu;
import models.Order;
import services.MenuService;
import services.MenuServiceImp;
import services.order.OrderService;
import services.order.OrderServiceImp;

public class App {
    static MenuDao menuDao = new MenuDao();
    static MenuService menuService = new MenuServiceImp(menuDao);

    static OrderDao orderDao = new OrderDao();
    static OrderService orderService = new OrderServiceImp(orderDao);

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        List<Order> orders = new ArrayList<>();

        boolean lanjutOrder = true;
        try {
            while (lanjutOrder) {
                System.out.println("========== RESTORAN AYAM GEPREK ==========");
                System.out.println("1. Lihat Daftar Menu");
                System.out.println("2. Input Pemesanan");
                System.out.println("3. Pembayaran");
                System.out.print("Pilihan: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        daftarMenu("Makanan");
                        daftarMenu("Minuman");
                        daftarMenu("Paket");
                        break;
                    case 2:
                        inputPesan();
                        break;
                    default:
                        break;
                }

                System.out.print("Ingin melakukan pemesanan / pembayaran? (y/t)");
                String lanjutPilih = scanner.next();
                if (!lanjutPilih.equalsIgnoreCase("y")) {
                    lanjutOrder = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static void inputPesan() {
        Integer choice;

        do {
            System.out.print("""

                    ====== MANAJEMEN PESANAN ======
                    1. Tambah Pesanan
                    2. Ubah Pesanan
                    3. Hapus Pesanan
                    4. Batalkan Semua Pesanan
                    Pilihan :  """);
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    tambahPesanan();
                    break;
                case 2:
                    ubahPesanan();
                    break;
                case 3:
                    hapusPesanan();
                    break;
                case 4:
                    batalkanSemuaPesanan();
                    break;
                default:
                    System.out.println("Pilihan tidak tersedia...");
            }

        } while (choice > 4 || choice < 1);
    }

    private static void ubahPesanan() {
        if (orderService.getOrderNumber() < 0) {
            System.out.println("Belum ada pesanan");
        } else {
            cetakPesanan();
            System.out.println("Masukkan pesanan yang ingin diubah; ");
            Integer id = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Daftar Menu: ");
            for (int i = 1; i <= menuService.getMenuNumber(); i++) {
                System.out.println((i) + ". " + menuService.getMenuById(i));
            }

            System.out.print("\nMasukan id menu : ");
            id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Masukan jumlah pesanan : ");
            Integer jumlahPesanan = scanner.nextInt();
            scanner.nextLine();

            Order order = new Order(menuService.getMenuById(id), jumlahPesanan);
            orderService.updateOrder(id, order);

            cetakPesanan();
        }
    }

    private static void hapusPesanan() {

        if (orderService.getOrderNumber() > 0) {
            cetakPesanan();
            System.out.print("Masukan id pesanan yang ingin dihapus : ");
            Integer id = scanner.nextInt();
            scanner.nextLine();

            orderService.deleteOrder(id);

            cetakPesanan();

        } else {
            System.out.println("Tidak bisa hapus pesanan karena belum ada data...");
        }
    }

    private static void batalkanSemuaPesanan() {
        orderService.deleteAllOrders();
    }

    private static Double ppn() {
        Double nilaiPpn = 0.;

        for (int i = 1; i <= orderService.getOrderNumber(); i++) {
            nilaiPpn += orderService.getOrderById(i).getTax();
        }
        return nilaiPpn;
    }

    private static void cetakPesanan() {
        if (orderService.getOrderNumber() > 0) {
            System.out.println("=======LIST PESANAN========");
            for (int i = 1; i <= orderService.getOrderNumber(); i++) {
                System.out.println(i + ". " + orderService.getOrderById(i));
            }

            System.out.println("Total harga : Rp " + getPpriceBeforeTax());
            System.out.println("Total harga setelah PPN (11%) : Rp " + (getPpriceBeforeTax() + ppn()));
        } else {
            System.out.println("Belum ada pesanan");
        }
    }

    private static Double getPpriceBeforeTax() {
        Double priceBeforeTax = 0.;

        for (int i = 1; i <= orderService.getOrderNumber(); i++) {
            priceBeforeTax += orderService.getOrderById(i).getPriceBeforeTax();
        }

        return priceBeforeTax;
    }

    }

    private static void tambahPesanan() {
        Integer choice;

        do {
            System.out.print("""

                    ====== PESAN MENU  ======
                    1. Menu Makanan
                    2. Menu Minuman
                    3. Menu Paket
                    Pilihan :  """);
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    orderMenu("Makanan");
                    break;
                case 2:
                    orderMenu("Minuman");
                    break;
                case 3:
                    orderMenu("Paket");
                    break;
                default:
                    System.out.println("Pilihan tidak tersedia...");
            }

        } while (choice > 3 || choice < 1);
    }

    private static void orderMenu(String kategori) {
        daftarMenu(kategori);

        System.out.println("Input nomornya untuk " + kategori + "");
        Integer id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Masukan jumlah yang dipesan : ");
        Integer jumlahPesanan = scanner.nextInt();
        scanner.nextLine();

        List<Menu> filteredMenu = filteredMenu(kategori);

        Order order = new Order(filteredMenu.get(id), jumlahPesanan);
        orderService.createOrder(order);

    }

    private static void daftarMenu(String kategori) {
        initAllMenu();
        System.out.println("\n====== MENU " + kategori + " ======");

        List<Menu> filteredMenu = filteredMenu(kategori);

        for (int i = 0; i < filteredMenu.size(); i++) {
            System.out.println((i + 1) + ". " + filteredMenu.get(i));
        }

    }

    private static List<Menu> filteredMenu(String kategori) {
        // List<Menu> filterdMenuPaket = listMenu.stream()
        // .filter(menu -> Arrays.asList("Paket").contains(menu.getKategori()))
        // .collect(Collectors.toList());

        List<Menu> listMenu = menuService.getAllMenus();
        List<Menu> filteredMenu;
        List<String> menuKategori = Arrays.asList(kategori);

        filteredMenu = listMenu.stream().filter(menu -> menuKategori.contains(menu.getKategori()))
                .collect(Collectors.toList());

        return filteredMenu;
    }

    private static void initAllMenu() {
        Menu[] menus = {
                new Menu("Bakso Biasa", 13000, "Makanan"),
                new Menu("Bakso Urat", 15000, "Makanan"),
                new Menu("Bakso Telor", 18000, "Makanan"),
                new Menu("Mie Ayam Bakso", 18000, "Makanan"),
                new Menu("Bakso Super", 22000, "Makanan"),
                // minuman
                new Menu("Es Cincau", 10000, "Minuman"),
                new Menu("Es Teh Tawar", 5000, "Minuman"),
                new Menu("Es Campur", 15000, "Minuman"),
                new Menu("Es Buah", 13000, "Minuman"),
                new Menu("Es Kelapa Muda", 12000, "Minuman"),
                // paket
                new Menu("Paket Hemat: Bakso biasa + tahu + pangsit + es teh tawar", 22000, "Paket"),
                new Menu("Paket Lengkap: Mie Ayam +  Bakso + Es Campur", 30000, "Paket"),
        };

        for (Menu menu : menus) {
            menuService.createMenu(menu);
        }
    }
}
