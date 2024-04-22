import java.io.*;

public class Main {
    public static void main(String[] args) {
        switch (args[0]) {
            case "-c" -> System.out.println(readBytes(args) + " " + args[1]);
            case "-l" -> System.out.println(readLines(args) + " " + args[1]);
            case "-w" -> System.out.println(readWordCount(args) + " " + args[1]);
            default -> System.out.println("Unsupported CLI command.");
        }
    }
    private static int readWordCount(String[] args) {
        int wordCount = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(args[1]))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    if (!word.isEmpty()) {
                        wordCount++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordCount;
    }

    private static int readLines(String[] args) {
        int lineCount = 0;
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(args[1])))) {
            while (br.readLine() != null) {
                lineCount++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lineCount;
    }

    // read and print each line into a String array
    public static int readBytes(String[] args) {
        int byteCount = 0;
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(args[1])))){
            int nextByte;
            while((nextByte = br.read()) != -1) {
                byteCount++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return byteCount;
    }
}
