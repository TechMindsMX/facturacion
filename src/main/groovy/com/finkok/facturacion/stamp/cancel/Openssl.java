package com.finkok.facturacion.stamp.cancel;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author AKMH 
 * anakaren.monroyh@gmail.com
 * 
 * Instalar openssl y configurar las variables de entorno / ambiente segun el sistema operativo
 * Cambiar rutas 
 * 
 */

public class Openssl {

    private static String claveCsd = "12345678a";
    private static String key = "C:\\Users\\Prueba\\AAD990814BP7_1.key";
    private static String cer = "C:\\Users\\Prueba\\AAD990814BP7_1.cer";
    private static String rutaDestino = "C:\\Users\\Prueba\\";
    private static String claveFinkok = "password";

    public Openssl() {
        super();
    }

    public static String getRutaDestino() {
        return rutaDestino;
    }

    public static void creaCerPem() {
        Runtime runtime = Runtime.getRuntime();
        Process exec = null;
        try {
            exec =
                    runtime.exec("openssl x509 -inform DER -outform PEM -in " + cer + " -pubkey -out " + rutaDestino
                    + "cer.pem");
            exec.waitFor();
        } catch (IOException e) {
        } catch (InterruptedException e) {
        }
    }

    public static void creaKeyPem() {
        Runtime runtime = Runtime.getRuntime();
        Process exec = null;
        try {
            exec =
                    runtime.exec("openssl pkcs8 -inform DER -in " + key + " -passin pass:" + claveCsd + " -out "
                    + rutaDestino + "key.pem");
            exec.waitFor();
        } catch (IOException e) {
        } catch (InterruptedException e) {
        }

    }

    public static void creaKeyEncriptado() {
        Runtime runtime = Runtime.getRuntime();
        Process exec = null;
        try {
            exec =
                    runtime.exec("openssl rsa -in " + rutaDestino + "key.pem -des3 -out " + rutaDestino
                    + "key.enc -passout pass:" + claveFinkok);
            exec.waitFor();
        } catch (IOException e) {
        } catch (InterruptedException e) {
        }
    }

    public static String leeArchivo(String ruta) {
        try {
            FileInputStream fstream = new FileInputStream(ruta);
            DataInputStream entrada = new DataInputStream(fstream);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada));
            String strLinea;
            StringBuffer sB = new StringBuffer();
            while ((strLinea = buffer.readLine()) != null) {
                sB.append(strLinea).append("\n");
            }
            entrada.close();
            return sB.toString();
        } catch (Exception e) {
            System.err.println("Ocurrio un error: " + e.getMessage());
        }
        return null;
    }

    public static void deleteFiles() {
        File cerPem = new File(rutaDestino + "cer.pem");
        cerPem.delete();
        File keyPem = new File(rutaDestino + "key.pem");
        keyPem.delete();
        File keyEnc = new File(rutaDestino + "key.enc");
        keyEnc.delete();
    }
}
