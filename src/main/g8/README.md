# $app;format="upper"$

Iniciar el servicio en local:
```sh
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Dspring.profiles.active=local"

mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Dspring.profiles.active=dev"



$if(distributedtrace_enable.truthy)$
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-XshowSettings:vm \
                                                    -javaagent:$app$-app/target/elastic-apm-agent-1.7.0.jar \ 
                                                    -Dspring.profiles.active=localdev \
                                                    -Delastic.apm.environment=localdev \
                                                    -Delastic.apm.service_name=$app$ \
                                                    -Delastic.apm.server_urls=http://104.209.255.47:8080 \
                                                    -Delastic.apm.enable_log_correlation=true \
                                                    -Delastic.apm.log_level=DEBUG  \
                                                    -Delastic.apm.instrument=false \
                                                    -Delastic.apm.service_version=1.0.0" 
$endif$

```

Variables para el pipeline de Bitbucket
```
Bitbucket Variables gennerales
    \$BITBUCKET_VARIABLE_ARTIFACT_USER
    \$BITBUCKET_VARIABLE_ARTIFACT_PASSWORD
    \$BITBUCKET_VARIABLE_GIT_PASS
    \$BITBUCKET_VARIABLE_GIT_USER
Bitbucket Variables por deployment
    \$BITBUCKET_VARIABLE_DEPLOYMENT_REPLICAS
    \$BITBUCKET_VARIABLE_LOGSTASH_URL
    \$BITBUCKET_VARIABLE_LOGSTASH_IP
    \$BITBUCKET_VARIABLE_LOGSTASH_DOMAIN
    \$BITBUCKET_VARIABLE_APM_SERVER_URL
    \$BITBUCKET_VARIABLE_APM_SERVER_IP
    \$BITBUCKET_VARIABLE_APM_SERVER_DOMAIN
    \$BITBUCKET_VARIABLE_AZURE_DOCKER_USER
    \$BITBUCKET_VARIABLE_AZURE_DOCKER_PASS
    \$BITBUCKET_VARIABLE_AZURE_DOCKER_HOST
    \$BITBUCKET_VARIABLE_KUBECONFIG
    \$BITBUCKET_VARIABLE_PVC
Bitbucket (Variables propias de bitbucket)
    \$BITBUCKET_DEPLOYMENT_ENVIRONMENT
bitbucket-pipeline (Variables autogeneradas en el pipeline)
    \$PIPELINE_DOCKER_TAG
```


$if(cipher_enable.truthy)$
#### IMPORTANTE!
```
Para usar el enpoint de Cifrado es necesario colocar valores en las claves 
commons.cypher.aes.password= valor1
commons.cypher.aes.salt= valor2

Esto se encuentra en application.properties dentro del módulo de configuraciones 
```
$endif$

Esta estructurado en los siguientes submodulos

```
$app;format="upper"$
 │
 ├── $app$-app
 │   Contiene el SpringApplication que es el inicializa el spring-boot y 
 │   tiene como dependencia a todos los demas otros modulos.
 │
 ├── $app$-core
 │   Contiene el codigo comun entre los proyectos.
 │
 ├── $app$-$module1_format$
 │   Contiene un sudmodulo con logica de solo este dominio.
 │
 ├── $app$-$module2_format$
 │   Contiene un sudmodulo con logica de solo este dominio.
 │
 ├── $app$-$module3_format$
 │   Contiene un sudmodulo con logica de solo este dominio.
 │
 └── $app$-config
     Contiene la configuracion de todos los sudbmodulos.
```


[](http://spring-config:8888/$app$/qa/gateway)
[](http://spring-config:8888/$app$/dev/gateway)

<https://bitbucket.org/tlmteam/tlm-commons-service-configserver-repository>

/config
+  gateway/
   + application.yml
   + application-errors.yml
   + application-dev.yml
   + application-uat.yml
+  bff-app/
   + application.yml
   + application-dev.yml
   + application-uat.yml
+  backend-transaction
   + application.yml
   + application-dev.yml
   + application-uat.yml
+  backend-support
   + application.yml
   + application-dev.yml
   + application-uat.yml


