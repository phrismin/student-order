package edu.javacourse.studentorder.dao;

import edu.javacourse.studentorder.domain.CountryArea;
import edu.javacourse.studentorder.domain.PassportOffice;
import edu.javacourse.studentorder.domain.RegisterOffice;
import edu.javacourse.studentorder.domain.Street;
import edu.javacourse.studentorder.exception.DaoException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

class DictionaryDaoImplTest {

  @BeforeAll
  public static void startUp() throws Exception {
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

  @Test
  void findStreets() throws DaoException {
    List<Street> actual = new DictionaryDaoImpl().findStreets("улица");
    Assertions.assertEquals(3, actual.size());
  }

  @Test
  void findPassportOffices() throws DaoException {
    List<PassportOffice> actual = new DictionaryDaoImpl().findPassportOffices("0102000");
    Assertions.assertEquals(2, actual.size());
  }

  @Test
  void findRegisterOffices() throws DaoException {
    List<RegisterOffice> actual = new DictionaryDaoImpl().findRegisterOffices("0101000");
    Assertions.assertEquals(2, actual.size());
  }

  @Test
  void findAreas() throws DaoException {
    List<CountryArea> actual1 = new DictionaryDaoImpl().findAreas("");
    Assertions.assertEquals(3, actual1.size());
    List<CountryArea> actual2 = new DictionaryDaoImpl().findAreas("0100000");
    Assertions.assertEquals(5, actual2.size());
    List<CountryArea> actual3 = new DictionaryDaoImpl().findAreas("0202000");
    Assertions.assertEquals(5, actual3.size());
  }
}