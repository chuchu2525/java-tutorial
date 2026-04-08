package JavaSample.lesson20_file_io_basics;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Lesson20FileIoBasics {
    public static void main(String[] args) {
        Path path = Path.of("JavaSample/lesson20_file_io_basics/sample.txt");

        try {
            Files.writeString(path, "Java lesson sample\n");
            String content = Files.readString(path);

            System.out.println("ファイル内容:");
            System.out.println(content);
        } catch (IOException e) {
            System.out.println("ファイル操作に失敗しました: " + e.getMessage());
        }
    }
}
