package app.primary;

import java.io.BufferedReader;
import java.io.IOException;

public class CLI {
    private final BufferedReader reader;

    public CLI(BufferedReader reader) {
        this.reader = reader;
    }

    public void display(String content) {
        System.out.println(content);
    }

    public void printWhiteSpace() {
        display("\n");
    }

    public String read() throws IOException {
        return reader.readLine();
    }
}
