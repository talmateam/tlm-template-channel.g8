package $package$.$module1_format$;

import $package$.$module1_format$.client.$module1;format="Camel"$Client;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@Slf4j
@SpringBootTest(classes = ApplicationTest.class)
@AutoConfigureMockMvc
@ActiveProfiles({"test"})
public abstract class AbstractTest {

  @Autowired
  protected MockMvc mvc;

  @MockBean
  protected $module1;format="Camel"$Client $module1;format="camel"$Client;

}
