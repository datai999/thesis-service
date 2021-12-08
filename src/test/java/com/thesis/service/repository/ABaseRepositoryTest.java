package com.thesis.service.repository;

import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.List;
import javax.sql.DataSource;
import com.thesis.service.AnnotationsTest.Passed;
import com.thesis.service.DisplayNameCamelCase;
import com.thesis.service.model.BaseTable;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ActiveProfiles;

/**
 * Provider context environment for repository testing
 *
 * @param <ID> id type of entity
 * @param <E> model entity
 * @param <R> repository for entity
 */
@ActiveProfiles("test")
@DataJpaTest(showSql = false) // use logging hibernate BasicBinder in application.yml
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayNameGeneration(DisplayNameCamelCase.class)
public abstract class ABaseRepositoryTest<ID, E extends BaseTable, R extends JpaRepository<E, ID>> {

  @Autowired
  protected R repository;

  @Autowired
  private DataSource dataSource;

  protected abstract List<String> scriptPaths();

  @BeforeAll
  void initDatabase() {
    System.out.println(">>>>>INIT DATA TEST");
    var resourceDB = new ResourceDatabasePopulator();
    this.scriptPaths().forEach(path -> {
      System.out.printf(">>>>>DATA SOURCE PATH: %s\n", path);
      resourceDB.addScript(new ClassPathResource(path));
    });
    resourceDB.execute(dataSource);
  }

  @BeforeEach
  protected void beforeEach(TestInfo testInfo) {
    System.out.printf(">>>>>START TEST : %s\n", testInfo.getDisplayName());
  }

  @AfterEach
  protected void afterEach(TestInfo testInfo) {
    System.out.printf(">>>>>>>END TEST : %s\n", testInfo.getDisplayName());
  }

  @Passed
  @Test
  @DisplayName("Find by id is null")
  void findByNullIdTest() {
    assertThrows(InvalidDataAccessApiUsageException.class, () -> repository.findById(null));
  }

}
