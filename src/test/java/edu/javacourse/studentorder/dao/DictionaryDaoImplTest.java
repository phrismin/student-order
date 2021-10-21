package edu.javacourse.studentorder.dao;

import edu.javacourse.studentorder.domain.CountryArea;
import edu.javacourse.studentorder.domain.PassportOffice;
import edu.javacourse.studentorder.domain.RegisterOffice;
import edu.javacourse.studentorder.domain.Street;
import edu.javacourse.studentorder.exception.DaoException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.time.LocalDateTime;
import java.util.List;

class DictionaryDaoImplTest {

  private static final Logger logger = LoggerFactory.getLogger(DictionaryDaoImplTest.class);

  @BeforeAll
  public static void startUp() throws Exception {
    DBInit.createTestBase();
  }

  @Test
  void findStreets() throws DaoException {
    LocalDateTime now1 = LocalDateTime.now();
    logger.info("TEST STREET {}", now1);
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