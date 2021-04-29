package $package$.$module1_format$.service;

import java.util.concurrent.CompletableFuture;
import java.util.List;

import $package$.$module1_format$.dto.UserRequest;

public interface $module1;format="Camel"$Service{

  List<UserRequest> find(String dni, Integer loop);
  CompletableFuture<List<UserRequest>>findParallel(String dni);
  UserRequest findLoop(String dni,Integer loop,Integer max);
  CompletableFuture<UserRequest> findLoopParallel(String dni,Integer loop,Integer max);
  $if(cipher_enable.truthy)$
  List<UserRequest> findCipher(String dni);
  $endif$

}
