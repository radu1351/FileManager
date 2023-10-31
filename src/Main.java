import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    private static ArrayList<MultimediaFile> multimediaFiles = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        multimediaFiles = FileManager.readFilesFromTxt();

        int option = 0;
        while (option != 5) {
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
                    showStatisticsMenu();
                    break;
                }
                case 5: {
                    closeApplication();
                    break;
                }
                default: {
                    System.out.println("\n--> Optiune incorecta. Incercati din nou.");
                }
            }
        }
    }

    private static void showStatisticsMenu() {
        int option = 0;
        while (option != 3) {
            System.out.println("\n______________STATISTICI_______________\n" +
                    "| 1.Afisare numar fisiere pe formate. |\n" +
                    "| 2.Afisare cel mai utilizat director. |\n" +
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
                "| 5.Parasire aplicatie. |\n" +
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
        multimediaFiles.add(selectedFile);
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