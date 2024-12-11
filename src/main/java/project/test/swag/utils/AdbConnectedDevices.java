package project.test.swag.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AdbConnectedDevices {
    public List<String> getAdbConnectedDevices() {
        List<String> devices = new ArrayList<>();
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("adb", "devices");
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("List of devices attached")) {
                    continue;
                }

                if (line.trim().length() > 0) {
                    String[] parts = line.split("\\s+");
                    if (parts.length > 1 && "device".equals(parts[1])) {
                        devices.add(parts[0]);
                    }
                }
            }

            process.waitFor();
        } catch (Exception e) {
            System.err.println("Error while checking ADB devices: " + e.getMessage());
            e.printStackTrace();
        }
        return devices;
    }
}
