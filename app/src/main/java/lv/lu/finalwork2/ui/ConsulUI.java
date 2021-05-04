package lv.lu.finalwork2.ui;

import lv.lu.finalwork2.model.repository.ProductCategory;
import lv.lu.finalwork2.model.ui.ProductInputData;
import lv.lu.finalwork2.service.ProductService;

import java.util.Arrays;
import java.util.Scanner;

public class ConsulUI {

    private final Scanner scanner = new Scanner(System.in);
    private ProductService service;

    public ConsulUI() {

    }

    public void startUi() {

        int userChoice;
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
        ProductInputData product = new ProductInputData();

        System.out.println("Enter product name..");
        product.setName(scanner.next());

        System.out.println("Enter product price..");
        product.setPrice(scanner.nextDouble());

        System.out.printf("Enter product category from (%s)",
                Arrays.asList(ProductCategory.values()));
        product.setCategory(scanner.next());

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
