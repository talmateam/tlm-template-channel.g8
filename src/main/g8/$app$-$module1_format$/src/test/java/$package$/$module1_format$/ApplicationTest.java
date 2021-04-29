package $package$.$module1_format$;


import com.microsoft.azure.servicebus.SubscriptionClient;
import lombok.extern.slf4j.Slf4j;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.bind.annotation.RestController;
$if(pubsub_enable.truthy)$
import pe.talma.commons.pubsub.impl.config.AzurePublisherFactoryConfig;
$endif$

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

$if(pubsub_enable.truthy)$
$endif$

@SpringBootApplication(scanBasePackages = {"$package_organization$"})
@ActiveProfiles("local")
@EnableScheduling
@RestController
@Slf4j
public class ApplicationTest {

  $if(pubsub_enable.truthy)$
  private final AzurePublisherFactoryConfigTest azurePublisherFactoryConfigTest;

  public ApplicationTest(AzurePublisherFactoryConfigTest azurePublisherFactoryConfigTest) {
    this.azurePublisherFactoryConfigTest = azurePublisherFactoryConfigTest;
  }

  @Bean
  public AzurePublisherFactoryConfig azurePublisherFactoryConfig() {
    AzurePublisherFactoryConfig azurePublisherFactoryConfigMock = azurePublisherFactoryConfigTest;
    return azurePublisherFactoryConfigMock;
  }
  $endif$

}
