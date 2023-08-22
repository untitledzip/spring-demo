package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
// ㄴ SpringSecurityFilterChain이 자동으로 포함됨. WebSecurityConfigureAdapter를 상속받아서 메소드 오버라이딩을 통해 보안 설정 커스터마이징 가능
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception{

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        /*
            BCryptPasswordEncoder의 해시 함수를 이용하여 비밀번호를 암호화하여 저장
             -> 비밀번호를 데이터베이스에 그대로 저장했을 경우, 데이터베이스가 해킹당하면 고객의 회원 정보가 그대로 노출되기 때문    
         */
        return new BCryptPasswordEncoder(); 
    }
}
