import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class FileManager {

    /**
     * Saves a list of files given as an argument to the specific text file.
     *
     * @param multimediaFiles The files to be saved in the txt file
     */
    public static void saveFilesToTxt(ArrayList<MultimediaFile> multimediaFiles) {
        createTxtFile();
        try {
            FileWriter fileWriter = new FileWriter("multimediaFiles.txt");
            for (MultimediaFile multimediaFile : multimediaFiles) {
                fileWriter.write(multimediaFile.toString() + " | " +
                        (multimediaFile.isFavourite() ? "1" : "0") + "\n");
            }
            fileWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Retrieves a list of MultimediaFiles from the specific text file.
     *
     * @return The file loaded from the text file.
     */
    public static ArrayList<MultimediaFile> readFilesFromTxt() {
        createTxtFile();
        ArrayList<MultimediaFile> multimediaFiles = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream("multimediaFiles.txt");
            Scanner sc = new Scanner(fis);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split(" \\| ");

                if (parts.length == 6) {
                    String name = parts[0];
                    String format = parts[1];
                    String path = parts[2];
                    Float size = Float.parseFloat(parts[3].substring(0, parts[3].indexOf("MB")));
                    Date lastModified = new Date(parts[4]);
                    MultimediaFile multimediaFile = new MultimediaFile(name, format, path, size, lastModified);
                    multimediaFile.setFavourite(parts[5].equals("1"));
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

    /**
     * Checks if a MultimediaFile given as a argument exists on disk
     *
     * @param multimediaFile The file to be checked if it exists on disk
     * @return A boolean, meaning if the file exists on disk or not
     */
    public static boolean fileExistsOnDisk(MultimediaFile multimediaFile) {
        return new File(multimediaFile.getPath()).exists();
    }

    /**
     * Creates a blank text file with the specific name.
     */
    private static void createTxtFile() {
        try {
            File file = new File("multimediaFiles.txt");
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
