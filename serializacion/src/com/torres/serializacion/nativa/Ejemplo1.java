/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.torres.serializacion.nativa;

import com.torres.serializacion.modelo.Product;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.System.in;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Ejemplo1 {

    public static void main(String[] args) {

        Product prod = new Product(1, "iPhone X", 300.75);
        System.out.println(prod.toString());
        System.out.println("Vamos a serializar el producto anterior");
        serializar(prod);

    }

    static void serializar(Product p) {
        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream(".\\Product.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(p);
            System.out.println("El fichero ha sido serializado en Product.ser");
        } catch (FileNotFoundException ex) {
            System.out.println("No se encuentra la ruta del fichero. " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Ha surgido un error serializado. " + ex.getMessage());
        } finally {
            try {
                fileOut.close();
            } catch (IOException ex) {
                System.out.println("Ha surgido un error cerrando el fichero. " + ex.getMessage());
            }
            try {
                FileInputStream fileIn = new FileInputStream(".\\Product.ser");
                ObjectInputStream in = new ObjectInputStream(fileIn);
                p = (Product) in.readObject();
                in.close();
                fileIn.close();
                System.out.println("El fichero ha sido desserializado en Product.ser");
            } catch (IOException i) {
                i.printStackTrace();
                return;
            } catch (ClassNotFoundException c) {
                System.out.println("Product class not found");
                c.printStackTrace();
                return;
            }

            System.out.println("ID: " + p.getId());
            System.out.println("Nombre: " + p.getName());
            System.out.println("Precio: " + p.getPrice());
        }
    }
}
