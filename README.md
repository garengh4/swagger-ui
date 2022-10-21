[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
![alt text](https://img.shields.io/github/languages/code-size/abyanjksatu/spring-to-heroku "Code Size")
![alt text](https://img.shields.io/github/languages/top/abyanjksatu/spring-to-heroku "Top Language")
[![Quality Gate Status](http://localhost:9000/api/project_badges/measure?project=spring-to-heroku&metric=alert_status)](http://localhost:9000/dashboard?id=spring-to-heroku)

# spring-to-heroku
spring to heroku in 5 minutes

There are 5 Step to accomplish this project:
1. Setup Project (Spring Initializr, Dependency POM.xml, Project Structure)
2. Setup Database (application.properties, check database with h2 console)
3. Create Object & Component (Entity, Repository, Pojo, Service & Controller)
4. Setup Swagger Config (Customizing Config)
5. Setup Heroku (Create App, Deploy App)

## Setup Project

Initialize your project with Spring Initializr
A [Spring Initializr](https://start.spring.io/ "Spring Initializr").

Add Dependency through Spring Initializr:
- Spring Web
- Spring Data JPA 

After Download the project, Add this dependency to POM.xml:
```xml
<depenencies>
  <dependency> 
  <groupId>com.h2database</groupId> 
  <artifactId>h2</artifactId> 
  <scope>runtime</scope> 
  </dependency>
  
  <dependency>
      <groupId>io.springfox</groupId>
      <artifactId>springfox-swagger2</artifactId>
      <version>2.9.2</version>
  </dependency>
  
  <dependency>
      <groupId>io.springfox</groupId>
      <artifactId>springfox-swagger-ui</artifactId>
      <version>2.9.2</version>
  </dependency>
</depenencies>
```

Setup folder directories:
```
└── hello
    ├── config
    ├── controller
    ├── entity
    │       ├── dao
    │       └── pojo
    ├── repository
    └── service
```

## Setup Database

Add this config to application.properties
```properties
## Server Port
server.port=9090
## H2 Console Dashboard
spring.h2.console.enabled=true
## H2 DataSource
spring.datasource.url=jdbc:h2:mem:db
spring.datasource.username=sa
spring.datasource.password=
spring.datasource.driver-class-name=org.h2.Driver
## JPA/Hibernate
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update
```

Start application & Check Database via [H2 Console](localhost:9090/h2-console "H2 Console").

## Setup Object & Component

Create User Entity (in folder entity/dao)
```java
@Entity
public class User {

 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 private Integer id;
 private String name;
 private Double salary;

 //Getter Setter
}
```

Create User Repository (in folder repository)
```java
public interface UserRepository extends JpaRepository<User, Integer> {
}
```

Create user Param Pojo (in folder entity/pojo)
```java
public class UserParam {

 private String name;
 private Double salary;

 //Getter Setter
}
```

Create User Service (in folder service)
```java
@Service
public class UserService {

 @Autowired
 private UserRepository userRepository;

 public List<User> getUserList() {
   return userRepository.findAll();
 }

 public User addUser(UserParam param) {
   User user = new User();
   user.setName(param.getName());
   user.setSalary(param.getSalary());
   return userRepository.save(user);
 }
}
```

Create User Controller (in folder controller)
```java
@RestController
@RequestMapping("/api/user")
public class UserController {

 @Autowired
 private UserService userService;

 @GetMapping
 public List<User> getUserList() {
   return userService.getUserList();
 }

 @PostMapping
 public User addUser(
     @RequestBody UserParam user
 ) {
   return userService.addUser(user);
 }
}
```

## Setup Swagger Config

Create Swagger Config (in folder config)
```java
@EnableSwagger2
@Configuration
public class SwaggerConfig {
       @Bean
        public Docket api() { 
            return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.world.hello.controller"))
                .paths(regex("/api.*"))
                .build();
        }
}
```

Create Swagger Config Customize (Advanced)
```java
@EnableSwagger2
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select() 
          .apis(RequestHandlerSelectors.basePackage("com.world.hello.controller"))
          .paths(PathSelectors.ant("/api.*"))  
          .build()
          .apiInfo(metaData());
    }

    private ApiInfo metaData() {
        return new ApiInfo(
            "MS Posts : Spring Boot REST API",
            "Spring Boot REST API for Posts management",
            "1.0",
            "Terms of service",
            new Contact("Azhwani", "https://devwithus.com", "azhwani@devwithus.com"),
            "Apache License Version 2.0",
            "https://www.apache.org/licenses/LICENSE-2.0", new ArrayList<VendorExtension>());
    }
}
```

## Setup Heroku

We use heroku to Deploy to server. 

First, we open [Heroku Dashboard](https://dashboard.heroku.com/ "Heroku Dashboard").

Then, create new app and follow this step

### Install the Heroku CLI
Download and install the Heroku CLI.
If you haven't already, log in to your Heroku account and follow the prompts to create a new SSH public key.
```shell script
$ heroku login
```

### Create a new Git repository
Initialize a git repository in a new or existing directory

```shell script
$ cd my-project/
$ git init
$ heroku git:remote -a spring-to-heroku
```

### Deploy your application
Commit your code to the repository and deploy it to Heroku using Git.
```shell script
$ git add .
$ git commit -am "make it better"
$ git push heroku master
Push from other branch
$ git push heroku <branch>:master
```

### Existing Git repository
For existing repositories, simply add the heroku remote
```shell script
$ heroku git:remote -a spring-to-heroku
```


