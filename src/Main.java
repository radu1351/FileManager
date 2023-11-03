import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    private static ArrayList<MultimediaFile> multimediaFiles = new ArrayList<>();
    private static ArrayList<MultimediaFile> favouriteMultimediaFiles = new ArrayList<>();

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        loadDataFromTxt();

        int option = 0;
        while (option != 6) {
            showMenu();
            option = sc.nextInt();
            switch (option) {
                case 1: {
                    printAddedFiles();
                    break;
                }
                case 2: {
                    addFile();
                    break;
                }
                case 3: {
                    deleteFile();
                    break;
                }
                case 4: {
                    if (!multimediaFiles.isEmpty()) {
                        showStatisticsMenu();
                    }
                    else{
                        System.out.println("\n--> Nu exista fisiere adaugate.");
                    }
                    break;
                }
                case 5: {
                    showFavouriteFilesMenu();
                    break;
                }
                case 6: {
                    closeApplication();
                    break;
                }
                default: {
                    System.out.println("\n--> Optiune incorecta. Incercati din nou.");
                }
            }
        }
    }

    private static void loadDataFromTxt() {
        multimediaFiles = FileManager.readFilesFromTxt();
        multimediaFiles.forEach(multimediaFile -> {
            if (multimediaFile.isFavourite()) favouriteMultimediaFiles.add(multimediaFile);
        });
    }


    private static void showStatisticsMenu() {
        int option = 0;
        while (option != 3) {
            System.out.println("\n______________STATISTICI_______________\n" +
                    "| 1.Afisare numar fisiere pe formate. |\n" +
                    "| 2.Afisare cel mai utilizat director.|\n" +
                    "| 3.Inapoi la meniul principal.       |\n" +
                    "_______________________________________");

            System.out.print("\nIntroduceti optiunea dvs: ");
            option = sc.nextInt();
            switch (option) {
                case 1: {
                    Map<String, Long> mapFormatToNumber = multimediaFiles.stream()
                            .collect(Collectors.groupingBy(MultimediaFile::getFormat, Collectors.counting()));

                    System.out.println("\n--> Numar de fisiere din fiecare format: ");
                    mapFormatToNumber.forEach((format, number) -> {
                        System.out.println("." + format + " : " + number);
                    });
                    break;
                }
                case 2: {
                    String directory = multimediaFiles.stream()
                            .collect(Collectors.groupingBy(MultimediaFile::getDirectory, Collectors.counting()))
                            .keySet().iterator().next();
                    System.out.println("Directorul cu cele mai multe fisiere salvate: " + directory);
                    break;
                }
                case 3: {
                    break;
                }
            }
        }
    }

    private static void showFavouriteFilesMenu() {
        int option = 0;
        while (option != 4) {
            System.out.println("\n________FISIERE FAVORITE_________\n" +
                    "| 1.Afisare fisiere favorite.   |\n" +
                    "| 2.Adaugare fisier favorit.    |\n" +
                    "| 3.Eliminare fisier favorit.   |\n" +
                    "| 4.Inapoi la meniul principal. |\n" +
                    "_________________________________");

            System.out.print("\nIntroduceti optiunea dvs: ");
            option = sc.nextInt();
            switch (option) {
                case 1: {
                    if (!favouriteMultimediaFiles.isEmpty()) {
                        System.out.println("\n--> Fisierele favorite sunt: ");
                        favouriteMultimediaFiles.forEach(System.out::println);
                    } else {
                        System.out.println("\n--> Nu exista fisiere favorite. ");
                    }
                    break;
                }
                case 2: {
                    int i = 1;
                    for (MultimediaFile file : favouriteMultimediaFiles) {
                        System.out.println(i++ + ": " + file.getName());
                    }
                    System.out.print("\nSelectati fisierul de adaugat in lista de favorite: ");
                    int indexToBeAddedToFavourite = sc.nextInt();

                    if (indexToBeAddedToFavourite > 0 && indexToBeAddedToFavourite <= multimediaFiles.size()) {
                        favouriteMultimediaFiles.add(multimediaFiles.get(indexToBeAddedToFavourite - 1));
                        multimediaFiles.get(indexToBeAddedToFavourite - 1).setFavourite(true);
                        System.out.println("\n--> Fisierul a fost adaugat in lista de favorite cu succes.");
                    } else {
                        System.out.println("\n--> Index fisier eronat. ");
                    }
                    break;
                }
                case 3: {
                    int i = 1;
                    for (MultimediaFile file : favouriteMultimediaFiles) {
                        System.out.println(i++ + ": " + file.getName());
                    }
                    System.out.print("\nSelectati fisierul de sters din lista de favorite: ");
                    int indexToBeDeletedFromFavourite = sc.nextInt();

                    if (indexToBeDeletedFromFavourite > 0 && indexToBeDeletedFromFavourite <= favouriteMultimediaFiles.size()) {
                        favouriteMultimediaFiles.remove(indexToBeDeletedFromFavourite - 1);
                        multimediaFiles.get(indexToBeDeletedFromFavourite - 1).setFavourite(false);
                        System.out.println("\n--> Fisierul a fost eleminat din lista de favorite cu succes.");
                    } else {
                        System.out.println("\n--> Index fisier eronat. ");
                    }
                    break;
                }
                default: {
                    break;
                }
            }

        }
    }

    private static void closeApplication() {
        System.out.println("\n--> Salvare date...");
        FileManager.saveFilesToTxt(multimediaFiles);
    }

    public static void showMenu() {
        System.out.println("\n___________MENIU_________\n" +
                "| 1.Afisare fisiere.    |\n" +
                "| 2.Adaugare fisier.    |\n" +
                "| 3.Sterere fisier.     |\n" +
                "| 4.Statistici.         |\n" +
                "| 5.Fisiere favorite.   |\n" +
                "| 6.Parasire aplicatie. |\n" +
                "_________________________");
        System.out.print("\nIntroduceti optiunea dvs: ");
    }

    public static void printAddedFiles() {
        if (!multimediaFiles.isEmpty()) {
            System.out.println("\n--> Fisierele adaugate sunt: ");
            multimediaFiles.forEach(multimediaFile -> System.out.println(multimediaFile.toString()));
        } else {
            System.out.println("\n--> Nu exista fisiere adaugate. ");
        }
    }

    public static void addFile() {
        MultimediaFile selectedFile = FileReader.selectFile();

        boolean selectedFileExists = false;
        for (MultimediaFile multimediaFile : multimediaFiles) {
            if (multimediaFile.getPath().equals(selectedFile.getPath()))
                selectedFileExists = true;
        }
        if (!selectedFileExists) {
            multimediaFiles.add(selectedFile);
            System.out.println("\n--> Fisier adaugat cu succes. ");
        } else {
            System.out.println("\n--> Fisierul a fost deja adaugat. ");
        }
    }

    public static void deleteFile() {
        int i = 1;
        for (MultimediaFile file : multimediaFiles) {
            System.out.println(i++ + ": " + file.getName());
        }
        System.out.print("\nSelectati fisierul de sters: ");
        int option = sc.nextInt();

        if (option > 0 && option <= multimediaFiles.size()) {
            multimediaFiles.remove(option - 1);
            System.out.println("\n--> Fisierul a fost sters cu succes.");
        } else {
            System.out.println("\n--> Index fisier eronat. ");
        }
    }


}