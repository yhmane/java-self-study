package chapter2.item9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Item9App {

    public static void main(String[] args) {

    }

    public String getTryFinallyRead(String path) throws IOException {
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(path));
            return br.readLine();
        } catch (Exception e) {
            e.getMessage();
        } finally {
            br.close();
        }

        return path;
    }

    public String getTryWithResourcesRead(String path) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.readLine();
        }
    }
}
