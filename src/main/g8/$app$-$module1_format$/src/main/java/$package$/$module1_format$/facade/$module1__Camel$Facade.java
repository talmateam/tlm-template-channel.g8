package $package$.$module1_format$.facade;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Component;
import $package$.$module1_format$.service.$module1;format="Camel"$Service;
import $package$.$module1_format$.dto.UserRequest;
import $package$.$module1_format$.service.ResponseService;

@Component
public class $module1;format="Camel"$Facade{

private final $module1;format="Camel"$Service $module1;format="camel"$Service;
private final ResponseService responseService;

  public $module1;format="Camel"$Facade($module1;format="Camel"$Service $module1;format="camel"$Service,
    ResponseService responseService){
    this.$module1;format="camel"$Service=$module1;format="camel"$Service;
    this.responseService=responseService;
  }

  public List<UserRequest> find(String dni, Integer loop) {
    return $module1;format="camel"$Service.find(dni, loop);
  }

  /*Cipher*/
  $if(cipher_enable.truthy)$
  public List<UserRequest> findCipher(String dni){
    return $module1;format="camel"$Service.findCipher(dni);
  }
  $endif$
/*Cipher*/

  public CompletableFuture<List<UserRequest>>findParallel(String dni){
    return $module1;format="camel"$Service.findParallel(dni);
  }

  public UserRequest findLoop(String dni,Integer loop,Integer max){
    return $module1;format="camel"$Service.findLoop(dni,loop,max);
  }

  public CompletableFuture<UserRequest> findLoopParallel(String dni,Integer loop,Integer max){
    return $module1;format="camel"$Service.findLoopParallel(dni,loop,max);
  }

  public UserRequest findResponse(String dni){
    return responseService.findResponse(dni);
  }

  public void save(String body){
    $if(spring_data_jpa_sqlserver_enable.truthy)$
    responseService.save(body);
    $endif$
  }

}
