package com.tcg.healthpointtrackerpc.utils.managers;

import org.json.JSONObject;

import java.io.*;

/**
 * Created by JoseR on 6/20/2017.
 */
public class FileManager {

    public static InputStream getInternalFile(String name) {
        return ClassLoader.getSystemClassLoader().getResourceAsStream(name);
    }

    public static String getDeivceId() {
        String id = "";
        if(deviceIdExists()) {
            File deviceIdFile = getDeviceIdFile();
            try {
                FileInputStream file = new FileInputStream(deviceIdFile);
                ObjectInputStream filein = new ObjectInputStream(file);
                id = (String) filein.readObject();
                filein.close();
                file.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return id;
    }

    public static void saveDeviceId(String deviceId) {
        if(!applicationFolderExists()) createApplicationFolder();
        File deviceIdFile = FileManager.getDeviceIdFile();
        try {
            FileOutputStream fileOut = new FileOutputStream(deviceIdFile);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(deviceId);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean deviceIdExists() {
        File deviceId = new File(localAppDataFolder("Tiny Country Games Launcher") + File.separator + "deviceId.dat");
        return deviceId.exists();
    }

    public static File getDeviceIdFile() {
        return new File(localAppDataFolder("Tiny Country Games Launcher") + File.separator + "deviceId.dat");
    }

    public static void createApplicationFolder() {
        if(!applicationFolderExists()) {
            File folder = new File(localAppDataFolder());
            folder.mkdirs();
        }
    }

    public static boolean applicationFolderExists() {
        File applicationFolder = new File(localAppDataFolder());
        return applicationFolder.exists();
    }

    public static String localAppDataFolder() {
        return localAppDataFolder("Health Point Tracker PC");
    }

    public static String localAppDataFolder(String subfolder) {
        return System.getProperty("user.home") + File.separator + "AppData" + File.separator + "Local" + File.separator + "Tiny Country Games" + File.separator + subfolder;
    }

    public static void saveJSONFile(File file, JSONObject jsonObject) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(jsonObject.toString());
        bufferedWriter.close();
        fileWriter.close();
    }

    public static JSONObject loadJSONFile(File file) throws IOException {
        if(!file.exists()) return new JSONObject();
        JSONObject jsonObject = null;
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        try {
            jsonObject = new JSONObject(bufferedReader.readLine());
        } catch (Exception e) {
            e.printStackTrace();
        }
        bufferedReader.close();
        fileReader.close();
        if(jsonObject != null) {
            return jsonObject;
        } else {
            return new JSONObject();
        }
    }

    public static String fileExtension(String string) {
        return string.substring(string.lastIndexOf(".") + 1);
    }

    public static String fileSafeName(String string) {
        return string.replaceAll("[:\\\\/*?|<>]", "_");
    }

}
