Make Entity class -> User,Role
Then Repository class to fetch user and role
then Service class 1.list all methods
                   2.implement all methods

In CustomAuthenticationFilter override "successfulAuthentication"
to do stuff after successfully

We could have used "UnSuccessfulAuthentication" as to if someone not loggined
in certain time we can stop their request

Any new API is created must be handled in customAuthorization and Security config file


## RABBITMQ AREA
docker>docker run --rm -it -p 15672:15672 -p 5672:5672 rabbitmq:3.10.5-management
         first port is rabbitmq spring from where spring boot will be connected
         second port is rabbitmq broker client/website

##KAFKA AREA
without springboot we need to configure a lot manually like
>ConsumerFactoryBean
>KafkaTemplate
>ProducerFactory
