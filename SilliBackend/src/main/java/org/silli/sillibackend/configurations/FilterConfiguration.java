package org.silli.sillibackend.configurations;

import org.silli.sillibackend.filters.JwtCookieFilter;
import org.silli.sillibackend.filters.JwtDecoderFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {
    private final JwtCookieFilter jwtCookieFilter;
    private final JwtDecoderFilter jwtDecoderFilter;

    public FilterConfiguration(JwtCookieFilter jwtCookieFilter, JwtDecoderFilter jwtDecoderFilter)
    {
        this.jwtCookieFilter = jwtCookieFilter;
        this.jwtDecoderFilter = jwtDecoderFilter;
    }

    @Bean
    public FilterRegistrationBean<JwtCookieFilter> cookieFilter() {
        FilterRegistrationBean<JwtCookieFilter> registrationBean =
                new FilterRegistrationBean<>();
        registrationBean.setFilter(jwtCookieFilter);
        registrationBean.addUrlPatterns("/users/login"); // Only apply to /test endpoints
        registrationBean.setOrder(1); // Execution order
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<JwtDecoderFilter> decoderFilter() {
        FilterRegistrationBean<JwtDecoderFilter> registrationBean =
                new FilterRegistrationBean<>();
        registrationBean.setFilter(jwtDecoderFilter);
        registrationBean.addUrlPatterns("/trigger");
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
