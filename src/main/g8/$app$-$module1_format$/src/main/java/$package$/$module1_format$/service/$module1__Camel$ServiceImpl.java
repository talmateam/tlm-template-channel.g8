package $package$.$module1_format$.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Async;
import $package$.$module1_format$.client.$module1;format="Camel"$Client;
import $package$.$module1_format$.dto.UserRequest;
$if(cipher_enable.truthy)$
import org.springframework.beans.factory.annotation.Autowired;
import io.opentracing.Scope;
import io.opentracing.Tracer;
import pe.talma.commons.cipher.service.CryptoAesService;

import java.util.Base64;
import java.nio.charset.Charset;
$endif$
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Slf4j
@Service
public class $module1;format="Camel"$ServiceImpl implements $module1;format="Camel"$Service{

  $if(cipher_enable.truthy)$
  @Autowired
  private Tracer tracer;
  $endif$
  private final $module1;format="Camel"$Client $module1;format="camel"$Client;
  private final ParallelService parallelService;
  $if(cipher_enable.truthy)$
  private final CryptoAesService cryptoAesService;
  $endif$

  public $module1;format="Camel"$ServiceImpl($module1;format="Camel"$Client $module1;format="camel"$Client,
                                $if(cipher_enable.truthy)$ CryptoAesService cryptoAesService, $endif$
                                ParallelService parallelService) {
    this.$module1;format="camel"$Client=$module1;format="camel"$Client;
    this.parallelService=parallelService;
    $if(cipher_enable.truthy)$
    this.cryptoAesService=cryptoAesService;
    $endif$
  }

  @Override
  public List<UserRequest> find(String dni, Integer loop){
    List<UserRequest> dtos = new ArrayList<>();
    int length = dni.length() - 3;
    for (int i = 0; i < loop; i++) {
      dtos.add($module1;format="camel"$Client.find(dni.substring(0, length) + String.format("%03d", i)));
    }
    return dtos;
  }

  @Override
  @Async
  public CompletableFuture<List<UserRequest>>findParallel(String dni){
    int length=dni.length()-3;
    Stream<CompletableFuture<UserRequest>>futures=Stream.of(
      parallelService.findParallel(dni.substring(0,length)+"001"),
      parallelService.findParallel(dni.substring(0,length)+"002"),
      parallelService.findParallel(dni.substring(0,length)+"003"),
      parallelService.findParallel(dni.substring(0,length)+"004"),
      parallelService.findParallel(dni.substring(0,length)+"005"),
      parallelService.findParallel(dni.substring(0,length)+"006"),
      parallelService.findParallel(dni.substring(0,length)+"007"),
      parallelService.findParallel(dni.substring(0,length)+"008"),
      parallelService.findParallel(dni.substring(0,length)+"009"),
      parallelService.findParallel(dni.substring(0,length)+"010")
    );

    List<UserRequest> dtos=futures
    .map(completableFuture->completableFuture.join())
    .collect(Collectors.toList());

    return CompletableFuture.completedFuture(dtos);
  }

  @Override
  public UserRequest findLoop(String dni,Integer loop,Integer max){
    final UserRequest userRequest;
    if(loop<max){
      userRequest=$module1;format="camel"$Client.findLoop(dni,loop+1,max);
    } else {
      userRequest=new UserRequest(dni,new UserRequest.Account(dni));
    }
    return userRequest;
  }


  @Override
  @Async
  public CompletableFuture<UserRequest> findLoopParallel(String dni,Integer loop,Integer max) {
    final UserRequest userRequest;
    if(loop<max) {
      userRequest = $module1;format="camel"$Client.findLoopParallel(dni, loop+1, max);
    } else {
      userRequest=new UserRequest(dni,new UserRequest.Account(dni));
    }
    return CompletableFuture.completedFuture(userRequest);
  }

  /*Cipher*/
  $if(cipher_enable.truthy)$
  @Override
  public List<UserRequest> findCipher(String dni){
    Stream<UserRequest> dto = find(dni, 15).stream();
    return cipherId(dto);
  }

  private List<UserRequest> cipherId(Stream<UserRequest> dto) {
    try(Scope scope=tracer.buildSpan("Encript").startActive(true)) {
      log.debug("scope: {}",scope);

      return dto.map(userRequest-> {
        byte[]idCypher=cryptoAesService.encrypt(userRequest.getAccount().getId().getBytes(Charset.defaultCharset()));   //
        String encodedcryp=Base64.getEncoder().encodeToString(idCypher.clone());
        UserRequest.Account account=userRequest.getAccount().toBuilder().id(encodedcryp).build();
        return userRequest.toBuilder().account(account).build();
      }).collect(Collectors.toList());
    }
  }
  $endif$
/*Cipher*/

  }
