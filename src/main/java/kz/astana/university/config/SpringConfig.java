package kz.astana.university.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.sql.DataSource;

@Configuration                        //указывает, что он содержит конфигурации компонентов Spring./Указывает, что класс объявляет один или несколько методов @Bean и может обрабатываться контейнером Spring для создания определений компонентов и запросов на обслуживание для этих компонентов во время выполнения.
@ComponentScan("kz.astana.university")//Настраивает директивы сканирования компонентов для использования с классами @Configuration.
@EnableWebMvc                         //Определяет методы обратного вызова для настройки конфигурации на основе Java для Spring MVC, включенного через @EnableWebMvc.  Defines callback methods to customize the Java-based configuration for Spring MVC enabled via @EnableWebMvc.
public class SpringConfig implements WebMvcConfigurer /*interface WebMvcConfigurer*/{
    private final ApplicationContext applicationContext; //Spring container

    @Autowired                        //Помечает конструктор как автоматически подключаемые средствами внедрения зависимостей Spring.
    public SpringConfig(ApplicationContext applicationContext) { // Dependency Injection
        this.applicationContext = applicationContext;
    }
    @Bean // Информирует Spring о том, что возращаемый данным методом объект должен быть зареган как бин.
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/views/");// Задаем папку где будут лижать наши представления
        templateResolver.setSuffix(".html"); // Задаем расширение этих представлений
        return templateResolver;
    }
    @Bean
    public SpringTemplateEngine templateEngine() {// Производится конфигурация наших представлений
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        registry.viewResolver(resolver);
    }
    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/student_db");
        dataSource.setUsername("postgres");
        dataSource.setPassword("123456");

        return dataSource;
    }
    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(dataSource());
    }
}
