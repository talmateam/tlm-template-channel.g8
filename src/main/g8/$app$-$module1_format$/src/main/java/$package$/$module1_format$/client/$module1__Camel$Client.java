package $package$.$module1_format$.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import $package$.$module1_format$.dto.UserRequest;
$if(audit_enable.truthy)$import pe.talma.commons.auditclient.feign.FeignConfigAudit;
$else$import pe.talma.commons.rest.cloud.feign.FeignConfig;$endif$


@FeignClient(name = "\${service.external.name}",
             url = "\${service.external.host}",
             configuration = $if(audit_enable.truthy)$FeignConfigAudit.class $else$ FeignConfig.class $endif$)
public interface $module1;format="Camel"$Client {

  @GetMapping("/$module1_format$/{dni}/response")
  UserRequest find(@PathVariable String dni);

  @GetMapping("/account/{dni}/loop/{loop}/{max}")
  UserRequest findLoop(@PathVariable String dni,
                       @PathVariable Integer loop,
                       @PathVariable Integer max);

  @GetMapping("/account/{dni}/loop/parallel/{loop}/{max}")
  UserRequest findLoopParallel(@PathVariable String dni,
                               @PathVariable Integer loop,
                               @PathVariable Integer max);

}
