package $package$.$module1_format$.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import pe.talma.commons.pubsub.PublisherClient;
import $package$.$module1_format$.dto.UserRequest;
$if(spring_data_jpa_sqlserver_enable.truthy)$
import $package$.$module1_format$.domain.User;
import $package$.$module1_format$.repository.UserRepository;
import java.util.UUID;
$endif$

import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Slf4j
@Service
public class ResponseServiceImpl implements ResponseService {
  private final PublisherClient demoPublisherClient;
  private final String label;
  $if(spring_data_jpa_sqlserver_enable.truthy)$
  private final UserRepository userRepository;
  $endif$

  public ResponseServiceImpl(PublisherClient demoPublisherClient1,
                             PublisherClient demoPublisherClient2,
                             $if(spring_data_jpa_sqlserver_enable.truthy)$
                             UserRepository userRepository,
                             $endif$
                             @Value("\${$app$.$module1_format$.pubsub.azure.label}") String label) {
    log.info("demoPublisherClient1: {}", demoPublisherClient1);
    log.info("demoPublisherClient2: {}", demoPublisherClient2);
    this.demoPublisherClient = demoPublisherClient1;
    this.label = label;
    $if(spring_data_jpa_sqlserver_enable.truthy)$
    this.userRepository = userRepository;
    $endif$
  }

  @Override
  public UserRequest findResponse(String dni) {
    String msg = "dni: " + dni;
    demoPublisherClient.publish(msg.getBytes(StandardCharsets.UTF_8), label, Optional.empty());
    return new UserRequest(dni, new UserRequest.Account(msg));
  }

  $if(spring_data_jpa_sqlserver_enable.truthy)$
  @Override
  public void save(String body) {
    userRepository.save(new User(UUID.randomUUID(), body));
  }
  $endif$
}


