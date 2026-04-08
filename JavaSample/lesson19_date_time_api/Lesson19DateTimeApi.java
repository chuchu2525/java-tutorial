package JavaSample.lesson19_date_time_api;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Lesson19DateTimeApi {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        LocalDate nextWeek = today.plusDays(7);

        LocalDateTime meeting = LocalDateTime.of(2026, 4, 10, 14, 30);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

        Duration studyTime = Duration.ofMinutes(90);

        System.out.println("今日: " + today);
        System.out.println("1週間後: " + nextWeek);
        System.out.println("会議日時: " + meeting.format(formatter));
        System.out.println("学習時間(分): " + studyTime.toMinutes());
    }
}
