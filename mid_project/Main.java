package ap.mid_project;

public class Main {
    public static void main(String[] args) {

        Storage storage = StorageFactory.createStorage();

        if (storage == null) {
            System.out.println("Invalid storage type in config.txt");
            return;
        }

        Library library;
        try {
            library = storage.load();
        } catch (Exception e) {
            System.out.println("Creating new library...");
            library = new Library();
        }

        Menu menu = new Menu(library);
        menu.showMenu();

        try {
            storage.save(library);
        } catch (Exception e) {
            System.out.println("Error saving library.");
        }
    }
}
