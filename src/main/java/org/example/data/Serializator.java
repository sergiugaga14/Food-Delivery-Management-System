package org.example.data;

import org.example.business.DeliveryService;

import java.io.*;
import java.util.ArrayList;

/**
 * @author Sergiu
 */
public class Serializator  {
    /**
     *
     * @param deliveryService the object to be serialized
     * @throws IOException
     * @throws ClassNotFoundException
     *
     */
    public static void serialize(DeliveryService deliveryService) throws IOException, ClassNotFoundException {

        FileOutputStream file = new FileOutputStream("file.txt");
        ObjectOutputStream out = new ObjectOutputStream(file);
        out.writeObject(deliveryService); // Method for serialization of object
        out.close();
        file.close();
    }

    /**
     *
     * @return the object that was deserialized
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static DeliveryService deserialize() throws IOException, ClassNotFoundException {

        FileInputStream file = new FileInputStream("file.txt");
        ObjectInputStream in = new ObjectInputStream(file);
        DeliveryService list = (DeliveryService) in.readObject(); // Method for deserialization of object
        in.close();
        file.close();
        return list;
    }
}
