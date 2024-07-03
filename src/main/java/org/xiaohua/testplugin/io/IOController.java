package org.xiaohua.testplugin.io;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IOController {
    private static final String directoryName = "data";
    private static final String whitelistedFileName = "WhitelistedPlayers.txt";
    private static final String adminsFileName = "admins.txt";

    public static List<String> getWhitelistedPlayers() {
        createWhitelistedFileIfNotExist(directoryName, whitelistedFileName);
        return getUsernamesFromFile(directoryName, whitelistedFileName);
    }

    public static Map<String, Integer> getAdminList() {
        createAdminsFileIfNotExist(directoryName, adminsFileName);
        return getAdminsFromFile(directoryName, adminsFileName);
    }

    public static boolean addWhitelistedPlayer(String playerName) {
        addUsernameToWhitelist(directoryName, whitelistedFileName, playerName);
        return true;
    }

    private static void addUsernameToWhitelist(String directoryName, String fileName, String username) {
        File file = new File(directoryName + File.separator + fileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(username + "\n");
            System.out.println("Added " + username + " to whitelist.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createWhitelistedFileIfNotExist(String directoryName, String fileName) {
        try {
            // 创建目录
            File dir = new File(directoryName);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // 创建文件
            File file = new File(directoryName + File.separator + fileName);
            if (!file.exists()) {
                file.createNewFile();
                // 可选择性地添加初始内容
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    writer.write("用户名1\n用户名2\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createAdminsFileIfNotExist(String directoryName, String fileName) {
        try {
            // 创建目录
            File dir = new File(directoryName);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // 创建文件
            File file = new File(directoryName + File.separator + fileName);
            if (!file.exists()) {
                file.createNewFile();
                // 可选择性地添加初始内容
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    writer.write("Tom 99\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String> getUsernamesFromFile(String directoryName, String fileName) {
        List<String> usernames = new ArrayList<>();
        File file = new File(directoryName + File.separator + fileName);

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                usernames.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return usernames;
    }

    private static Map<String, Integer> getAdminsFromFile(String directoryName, String fileName) {
        Map<String, Integer> admins = new HashMap<>();
        File file = new File(directoryName + File.separator + fileName);

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 2) {
                    String username = parts[0];
                    int permission;
                    try {
                        permission = Integer.parseInt(parts[1]);
                        admins.put(username, permission);
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid permission format for user: " + username);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return admins;
    }
}
