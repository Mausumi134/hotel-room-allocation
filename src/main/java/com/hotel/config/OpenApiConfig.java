package com.hotel.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI hotelRoomAllocationOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Hotel Room Allocation API")
                        .description("Room allocation optimization tool for hotels. " +
                                "Automatically allocates premium and economy rooms to guests " +
                                "based on their willingness to pay, with smart upgrade logic.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Hotel Management System")
                                .email("support@hotel.com")));
    }
}
