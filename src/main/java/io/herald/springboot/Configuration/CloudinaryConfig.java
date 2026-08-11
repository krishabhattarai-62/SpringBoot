package io.herald.springboot.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {
    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(
                ObjectUtils.asMap(
                        "cloud_name","zg8nxthk",
                        "api_key","362573517162342",
                        "api_secret","XBthfclfrkqLV7SJmb4f_k38FD0",
                        "secure",true
                )
        );
    }
}
