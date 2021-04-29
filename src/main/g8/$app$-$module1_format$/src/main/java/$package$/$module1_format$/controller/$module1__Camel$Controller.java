package $package$.$module1_format$.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import $package$.$module1_format$.facade.$module1;format="Camel"$Facade;
import $package$.$module1_format$.dto.UserRequest;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
@RestController
@RequestMapping("/$module1_format$")
public class $module1;format="Camel"$Controller {

  private final $module1;format="Camel"$Facade $module1;format="camel"$Facade;

  public $module1;format="Camel"$Controller($module1;format="Camel"$Facade $module1;format="camel"$Facade) {
    this.$module1;format ="camel"$Facade = $module1;format ="camel"$Facade;
  }

  // ========================================================================================================
  @GetMapping("/{dni}")
  public List<UserRequest> find(@PathVariable String dni,
                                @RequestParam(required = false, defaultValue = "10") Integer loop) {
    return $module1;format="camel"$Facade.find(dni, loop);
  }

  @GetMapping("/{dni}/parallel")
  public List<UserRequest> findParallel(@PathVariable String dni)
      throws ExecutionException, InterruptedException {
    CompletableFuture<List<UserRequest>> userRequests;
    userRequests = $module1;format="camel"$Facade.findParallel(dni);
    return userRequests.get();
  }

  // ========================================================================================================
  $if(cipher_enable.truthy)$
  @GetMapping("/{dni}/encrypt")
  public List<UserRequest> findCipher(@PathVariable String dni) {
    return $module1;format="camel"$Facade.findCipher(dni);
  }
  $endif$

  // ========================================================================================================
  @GetMapping("/{dni}/response")
  public UserRequest findResponse(@PathVariable String dni) {
    log.info("dni: {}", dni.replaceAll("[\r\n]", ""));
    UserRequest userRequest = $module1;format ="camel"$Facade.findResponse(dni);
    return userRequest;
  }

  @PostMapping("/{dni}/parallel/response")
  public UserRequest findParallelResponse(@PathVariable String dni) {
    log.info("dni: {}", dni.replaceAll("[\r\n]", ""));
    UserRequest userRequest = $module1;format ="camel"$Facade.findResponse(dni);
    return userRequest;
  }
// ========================================================================================================


  // ========================================================================================================
  @GetMapping("/{dni}/loop/{loop}/{max}")
  public UserRequest findLoop(@PathVariable String dni,
                              @PathVariable int loop,
                              @PathVariable int max)
      throws ExecutionException, InterruptedException {
    log.info("loop: {}, max: {}", loop, max);
    return $module1;format="camel"$Facade.findLoop(dni, loop, max);
  }

  @GetMapping("/{dni}/loop/parallel/{loop}/{max}")
  public UserRequest findLoopParallel(@PathVariable String dni,
                                      @PathVariable int loop,
                                      @PathVariable int max)
      throws ExecutionException, InterruptedException {
    log.info("loop: {}, max: {}", loop, max);
    return $module1;format = "camel" $Facade.findLoopParallel(dni, loop, max).get();
  }
  // ========================================================================================================
}
