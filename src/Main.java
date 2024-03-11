import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
class Home {
    public int squareFeet;
    public String address;
    public String city;
    public String state;
    public int zipcode;
    public String modelName;
    public static String filename;
    public static String file;

    public void addNewHome(Scanner scanner) {
        System.out.print("Enter square feet: ");
        squareFeet = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter address: ");
        address = scanner.nextLine();
        System.out.print("Enter city: ");
        city = scanner.nextLine();
        System.out.print("Enter state: ");
        state = scanner.nextLine();
        System.out.print("Enter zipcode: ");
        zipcode = scanner.nextInt();
        System.out.print("Enter model name: ");
        modelName = scanner.nextLine();
        System.out.print("Enter sale status (sold, available, under contract): ");
        String salesStatus = scanner.nextLine();
    }

    public void updateHome(Scanner scanner) {
        addNewHome(scanner);
    }

    public String getAddress() {
        return address;
    }

    public int getSquareFeet() {
        return squareFeet;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public int getZipcode() {
        return zipcode;
    }

    public String getModelName() {
        return modelName;
    }

    public class homeInventory {
        public static void main(String[] args) {
            List<Home> homes = new ArrayList<>();
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("1. Create new home");
                System.out.println("2. Update home");
                System.out.println("3. Remove home");
                System.out.println("4. Save to file");
                System.out.println("5. Exit");
                System.out.print("Enter your response [1, 2, 3, 4, 5]: ");
                int option = scanner.nextInt();
                scanner.nextLine();
                switch (option) {
                    case 1:
                        Home temp = new Home();
                        temp.addNewHome(scanner);
                        homes.add(temp);
                        System.out.println("Home successfully added.");
                        System.out.println("Would you like to print home to a file? [Y or N]");
                        String inpt = scanner.nextLine();
                        if (inpt.equals("Y")) {
                            System.out.println("Home details have been printed to file" + filename);
                        } else if (inpt.equals("N")) {
                            System.out.println("File will not be printed.");
                        }
                        break;
                    case 2:
                        System.out.print("Enter address to update: ");
                        String addressToUpdate = scanner.nextLine();
                        boolean addressFound = false;
                        for (Home home : homes) {
                            if (home.getAddress().equals(addressToUpdate)) {
                                home.updateHome(scanner);
                                addressFound = true;
                                System.out.println("Home updated successfully.");
                                break;
                            }
                        }
                        if (!addressFound) {
                            System.out.println("Address not found");
                        }
                        break;
                    case 3:
                        System.out.print("Enter address to remove: ");
                        String addressToRemove = scanner.nextLine();
                        for (Home home : homes) {
                            if (home.getAddress().equals(addressToRemove)) {
                                home.removeHome();
                                homes.remove(home);
                                System.out.println("Home successfully removed.");
                                break;
                            }
                        }
                        break;
                    case 4:
                        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Home.txt"))) {
                            for (Home home : homes) {
                                writer.write(home.getAddress() + "\n");
                                writer.write(home.getSquareFeet() + "\n");
                                writer.write(home.getCity() + "\n");
                                writer.write(home.getState() + "\n");
                                writer.write(home.getZipcode() + "\n");
                                writer.write(home.getModelName() + "\n");
                                writer.write(home.getSalesStatus() + "\n");
                            }
                            System.out.println("Homes saved to file.");
                        } catch (IOException e) {
                            System.out.println("An error occurred while writing to the file: " + e.getMessage());
                        }
                        break;
                    case 5:
                        scanner.close();
                        System.out.println("Exiting...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid option!");
                }
            }
        }
    }

    public void removeHome() {
    }

    public int getSalesStatus() {
        return 0;
    }
}

