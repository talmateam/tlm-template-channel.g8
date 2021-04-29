package $package$.$module1_format$.acceptancetest;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import $package$.$module1_format$.AbstractTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
public class ControllerTest extends AbstractTest {

  @Test
  public void indexTest() throws Exception {
    mvc.perform(get("/")).andDo(mvcResult -> log.info(mvcResult.getResponse().getContentAsString()));
  }

  @Test
  public void getTest() throws Exception {
    mvc
      .perform(get("/$module1_format$/40202020"))
      .andDo(mvcResult -> log.info(mvcResult.getResponse().getContentAsString()))
      .andExpect(status().isOk())
    ;
  }

  $if(cipher_enable.truthy)$
  @Test
  public void getEncrypt() throws Exception {
    mvc
      .perform(get("/$module1_format$/40404040/encrypt"))
      .andDo(mvcResult -> log.info(mvcResult.getResponse().getContentAsString()))
      .andExpect(status().isOk())
    ;
  }
  $endif$

  @Test
  public void getTestExceptionEmpty() throws Exception {
    mvc
      .perform(get("/$module1_format$/  /response"))
      .andDo(mvcResult -> log.info(mvcResult.getResponse().getContentAsString()))
      .andExpect(status().is5xxServerError())
      .andExpect(content().json("{'errorCode': '$application;format="upper,word"$_E500_001'}"));
  }

  @Test
  public void getTestExceptionMinor8() throws Exception {
    mvc
      .perform(get("/$module1_format$/1234567/response"))
      .andDo(mvcResult -> log.info(mvcResult.getResponse().getContentAsString()))
      .andExpect(status().is5xxServerError())
      .andExpect(content().json("{'errorCode': '$application;format="upper,word"$_E500_001'}"));
  }

  @Test
  public void postTest() throws Exception {
    mvc.perform(get("/$module1_format$/40202020/parallel")).andDo(mvcResult -> log.info(mvcResult.getResponse().getContentAsString()));
  }

  @Test
  public void findLoopParallel() throws Exception {
    mvc.perform(get("/$module1_format$/55005500/loop/parallel/0/3")).andDo(mvcResult -> log.info(mvcResult.getResponse().getContentAsString()));
  }

  @Test
  public void findLoop() throws Exception {
    mvc.perform(get("/$module1_format$/55005500/loop/0/3")).andDo(mvcResult -> log.info(mvcResult.getResponse().getContentAsString()));
  }

  @Test
  public void findResponse() throws Exception {
    mvc.perform(get("/$module1_format$/55005500/response")).andDo(mvcResult -> log.info(mvcResult.getResponse().getContentAsString()));
  }

  @Test
  public void findParallelResponse() throws Exception {
    mvc.perform(post("/$module1_format$/55005500/parallel/response")).andDo(mvcResult -> log.info(mvcResult.getResponse().getContentAsString()));
  }

}
