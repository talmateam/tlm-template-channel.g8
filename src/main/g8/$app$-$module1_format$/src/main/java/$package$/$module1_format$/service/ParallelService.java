package $package$.$module1_format$.service;

import $package$.$module1_format$.dto.UserRequest;

import java.util.concurrent.CompletableFuture;

interface ParallelService {

  CompletableFuture<UserRequest> findParallel(String dni);

}
