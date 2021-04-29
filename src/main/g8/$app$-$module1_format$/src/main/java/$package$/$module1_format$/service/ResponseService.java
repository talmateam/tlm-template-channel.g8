package $package$.$module1_format$.service;

import $package$.$module1_format$.dto.UserRequest;

public interface ResponseService {

  UserRequest findResponse(String dni);
  $if(spring_data_jpa_sqlserver_enable.truthy)$
  void save(String body);
  $endif$

}
