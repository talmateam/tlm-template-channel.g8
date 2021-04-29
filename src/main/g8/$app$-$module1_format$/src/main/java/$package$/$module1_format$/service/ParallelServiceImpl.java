package $package$.$module1_format$.service;

import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Async;

import $package$.$module1_format$.client.$module1;format="Camel"$Client;
import $package$.$module1_format$.dto.UserRequest;

import java.util.concurrent.CompletableFuture;

@Service
public class ParallelServiceImpl implements ParallelService {

  private final $module1;format="Camel"$Client $module1;format="camel"$Client;

  public ParallelServiceImpl($module1;format="Camel"$Client $module1;format="camel"$Client) {
    this.$module1;format="camel"$Client = $module1;format="camel"$Client;
  }

  @Override
  @Async
  public CompletableFuture<UserRequest> findParallel(String dni) {
    UserRequest userRequest = $module1;format="camel"$Client.find(dni);
    return CompletableFuture.completedFuture(userRequest);
  }

}
