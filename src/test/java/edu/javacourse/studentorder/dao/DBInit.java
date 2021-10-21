package edu.javacourse.studentorder.dao;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class DBInit {
  public static void createTestBase() throws Exception {
    URL urlCreateBase = DictionaryDaoImplTest.class.getClassLoader().getResource("student_project.sql");
    URL urlInsetData = DictionaryDaoImplTest.class.getClassLoader().getResource("student_data.sql");
    List<String> stringList1 = Files.readAllLines(Path.of(urlCreateBase.toURI()));
    List<String> stringList2 = Files.readAllLines(Path.of(urlInsetData.toURI()));
    String sqlCreateBase = String.join("", stringList1);
    String sqlInsetData = String.join("", stringList2);

    try (var connection = ConnectionBuilder.getConnection();
         var statement = connection.createStatement()) {
      statement.executeUpdate(sqlCreateBase);
      statement.executeUpdate(sqlInsetData);
    }
  }
}
