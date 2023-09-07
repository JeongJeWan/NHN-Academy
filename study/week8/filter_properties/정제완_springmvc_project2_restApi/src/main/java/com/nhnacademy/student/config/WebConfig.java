package com.nhnacademy.student.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.student.controller.ControllerBase;
import com.nhnacademy.student.domain.User;
import com.nhnacademy.student.filter.LoginCheckFilter;
import com.nhnacademy.student.interceptor.LoginCheckInterceptor;
import com.nhnacademy.student.thymeleaf.CustomTagDialect;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import java.util.List;
import java.util.Locale;

@EnableWebMvc
@Configuration
@ComponentScan(basePackageClasses = ControllerBase.class)
public class WebConfig implements WebMvcConfigurer, ApplicationContextAware, MessageSourceAware {

    private ApplicationContext applicationContext;
    private MessageSource messageSource;

    private final ObjectMapper objectMapper;

    public WebConfig(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    //  thymeleafViewResolver bean 등록
    @Bean
    public ThymeleafViewResolver thymeleafViewResolver(){
        ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
        thymeleafViewResolver.setTemplateEngine(springTemplateEngine());
        thymeleafViewResolver.setCharacterEncoding("UTF-8");
        thymeleafViewResolver.setOrder(1);
        thymeleafViewResolver.setViewNames(new String[]{"*"});
        return thymeleafViewResolver;
    }

    //  thymeleafViewResolver 필요한 SpringTemplateEngine 생성
    //  messageSource 가 필요, implement MessageSourceAware, 즉 의존성 주입을 위한 setter 구현
    public SpringTemplateEngine springTemplateEngine(){
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(springResourceTemplateResolver());
        templateEngine.setTemplateEngineMessageSource(messageSource);
        //todo#16 custom Dialet 추가 .. SpringTemplateEngine 에다가 직접 추가해줘도 되고 직접 CustomTagDialet @component 붙여 놓으면 .. dialet으로 등록해줌.
        templateEngine.addDialect(new CustomTagDialect());
        return templateEngine;
    }

    //  SpringTemplateEngine 생성때 필요한 SpringResourceTemplateResolver 생성
    //  applicationContext가 필요한. implement ApplicationContextAware
    //  xxxxAware 붙은 녀석들을 setter 구혀하게 됨. 즉, 의존성 주입을 위한 setter 구현
    public SpringResourceTemplateResolver springResourceTemplateResolver(){
        SpringResourceTemplateResolver springResourceTemplateResolver = new SpringResourceTemplateResolver();
        springResourceTemplateResolver.setApplicationContext(applicationContext);
        springResourceTemplateResolver.setCharacterEncoding("UTF-8");
        springResourceTemplateResolver.setPrefix("/WEB-INF/views/");
        springResourceTemplateResolver.setSuffix(".html");
        springResourceTemplateResolver.setTemplateMode(TemplateMode.HTML);
        return springResourceTemplateResolver;
    }

    //  컨텐츠 현상 전략 설정
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorParameter(true)
                .parameterName("format")
                .ignoreAcceptHeader(true)
                .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("json", MediaType.APPLICATION_JSON)
                .mediaType("xml", MediaType.APPLICATION_XML);
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 기본으로 등록되는 MappingJackson2HttpMessageConverter 제거함
        converters.removeIf(o->o instanceof MappingJackson2HttpMessageConverter);
        // bean으로 등록해놓은 objectMapper를 주입하여MappingJackson2HttpMessageConverter 생성
        HttpMessageConverter converter =  new MappingJackson2HttpMessageConverter(objectMapper);
        //추가
        converters.add(converter);
    }

    //   thymeleafViewResolver 등록
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        // registry.jsp("/WEB-INF/views/",".jsp");
        registry.viewResolver(thymeleafViewResolver());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/","/student/list.do");
        registry.addRedirectViewController("/favicon.ico","/resources/favicon.ico");
    }

    @Bean
    public User user() {
        return new User("admin", "관리자", "1234");
    }

    // cookie 기반의 localResolver bean 등록
    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setDefaultLocale(Locale.KOREAN);
        return cookieLocaleResolver;
    }

    //  LocaleChangeInterceptor 등록 ?locale=ko or ?locale=en
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor(){
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("locale");
        return localeChangeInterceptor;
    }

    // LocaleChangeInterceptor interceptor 등록, DispatcherServlet 이 Controller 를 호출하기 전후에 요청 및 응답을 참조, 가공할 수 있는 일종의 필터 역
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebMvcConfigurer.super.addInterceptors(registry);
        registry.addInterceptor(localeChangeInterceptor());
//        registry.addInterceptor(new LoginCheckInterceptor());
    }
}
