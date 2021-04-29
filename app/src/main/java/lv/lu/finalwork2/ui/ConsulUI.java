package lv.lu.finalwork2.ui;

import lv.lu.finalwork2.model.Product;
import lv.lu.finalwork2.model.ProductCategory;
import lv.lu.finalwork2.service.ProductService;

import java.math.BigDecimal;
import java.util.Scanner;

public class ConsulUI {

    private final Scanner scanner = new Scanner(System.in);
    private ProductService service;

    public ConsulUI() {

    }

    public void startUi() {

        int userChoice = 0;
        while (true) {
            printMenu();
            userChoice = scanner.nextInt();

            callServiceByChoice(userChoice);

            if (userChoice == 0) {
                break;
            }
        }

    }

    private void callServiceByChoice(int userChoice) {
        switch (userChoice) {

            case 1:
                initProductSave();
                break;
        }
    }

    private void initProductSave() {
        Product product = new Product();

        System.out.println("Enter product name..");
        product.setName(scanner.nextLine());

        System.out.println("Enter product price..");
        product.setPrice(BigDecimal.valueOf(scanner.nextDouble()));

        System.out.printf("Enter product category from (%s)", ProductCategory.values());
        product.setCategory(ProductCategory.valueOf(scanner.nextLine()));

        service.save(product);
    }

    private void printMenu() {
        System.out.println("=== Product Accounting application ===");
        System.out.println("Choose one option\n");
        System.out.println("Save product - 1");
        System.out.println("Exit application - 0");
        System.out.println("Please enter the choice");
    }
}
