package $package$.$module1_format$.dto;

import org.apache.commons.lang3.StringUtils;
import lombok.Value;
import lombok.Builder;
import com.fasterxml.jackson.annotation.JsonCreator;

import static  $package$.exceptions.$application;format="Camel"$CatalogErrors.$application;format="upper,word"$_E500_001;

@Builder(toBuilder = true)
@Value
public class UserRequest {

  private String dni;
  private Account account;

  @JsonCreator
  public UserRequest(String dni, Account account) {
    this.dni = dni;
    if (StringUtils.isBlank(dni) || dni.length() != 8) {
      throw $application;format="upper,word"$_E500_001.getException();
    }
    this.account = account;
  }
  @Builder(toBuilder = true)
  @Value
  public static class Account {
    private String id;

    @JsonCreator
    public Account(String id) {
      this.id = id;
    }
  }

}
