import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {

    public static void saveFilesToTxt(ArrayList<MultimediaFile> multimediaFiles) {
        createTxtFile();
        try {
            FileWriter fileWriter = new FileWriter("multimediaFiles.txt");
            for (MultimediaFile multimediaFile : multimediaFiles) {
                fileWriter.write(multimediaFile.toString() + "\n");
            }
            fileWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static ArrayList<MultimediaFile> readFilesFromTxt() {
        ArrayList<MultimediaFile> multimediaFiles = new ArrayList<>();

        try {
            FileInputStream fis = new FileInputStream("multimediaFiles.txt");
            Scanner sc = new Scanner(fis);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split(" ");

                if (parts.length == 3) {
                    String name = parts[0];
                    String format = parts[1];
                    String path = parts[2];

                    MultimediaFile multimediaFile = new MultimediaFile(name, format, path);
                    multimediaFiles.add(multimediaFile);
                } else {
                    System.err.println("Invalid line format: " + line);
                }
            }

            sc.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return multimediaFiles;
    }

    private static void createTxtFile() {
        try {
            File file = new File("multimediaFiles.txt");
            if (file.delete())
                file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
