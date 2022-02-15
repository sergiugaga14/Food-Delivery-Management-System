package org.example.data;

import org.example.business.BaseProduct;
import org.example.business.MenuItem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Sergiu
 */
public class FileOperations {
    /**
     *
     * @return the list of items from the file
     * @throws IOException exception
     */
    public static LinkedHashSet<MenuItem> readFile() throws IOException {
        try (Stream<String> stream = Files.lines(Paths.get("D:\\PT\\PT2021_30424_Gaga_Sergiu_Assignment_4\\src\\main\\java\\org\\example\\resources\\products.csv"))) {
            return stream
                    .skip(1)
                    .map(line->line.split(","))
                    .map(data->new BaseProduct(data[0],Double.parseDouble(data[1]),Integer.parseInt(data[2]),Integer.parseInt(data[3]),Integer.parseInt(data[4]),Integer.parseInt(data[5]),Integer.parseInt(data[6])))
                    .collect(Collectors.toCollection(LinkedHashSet::new));
        }
    }

    /**
     *
     * @param s the name of the file
     *  it creates the file with the name given as parameter
     */
    public static void createFile(String s)
    {
        try {
            File myObj = new File(s);
            myObj.createNewFile();
        } catch (IOException e) {

        }
    }

    /**
     *
     * @param s the name of the file
     * @param content the content of the file
     * it writes the content into the file specified
     */
    public static void writeFile(String s,String content)
    {
        try {
            FileWriter myWriter = new FileWriter(s);
            myWriter.write(content);
            myWriter.close();
        } catch (IOException e) {
        }
    }
}
