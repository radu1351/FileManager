import java.io.*;
import java.util.ArrayList;
import java.util.Date;
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
        createTxtFile();
        ArrayList<MultimediaFile> multimediaFiles = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream("multimediaFiles.txt");
            Scanner sc = new Scanner(fis);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split(" \\| ");

                if (parts.length == 5) {
                    String name = parts[0];
                    String format = parts[1];
                    String path = parts[2];
                    Float size = Float.parseFloat(parts[3].substring(0, parts[3].indexOf("MB")));
                    Date lastModified = new Date(parts[4]);

                    MultimediaFile multimediaFile = new MultimediaFile(name, format, path, size, lastModified);
                    if (fileExistsOnDisk(multimediaFile)) {
                        multimediaFiles.add(multimediaFile);
                    }
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

    public static boolean fileExistsOnDisk(MultimediaFile multimediaFile) {
        return new File(multimediaFile.getPath()).exists();
    }

    private static void createTxtFile() {
        try {
            File file = new File("multimediaFiles.txt");
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
