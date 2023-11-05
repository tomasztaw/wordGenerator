/**
 * Created by tomasz_taw
 * Date: 05.11.2023
 * Time: 09:08
 * Project Name: wordGenerator
 * Description:
 */
package pl.taw.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "tomasz_taw",
                        email = "this.dev.test@gmail.com",
                        url = "https://jakiasstrona.pl"),
                description = "OpenApi documentation for Word Generator App",
                title = "OpenApi specification - tomasz_taw", version = "1.0",
                license = @License(name = "Licence name", url = "https://someurl.com"),
                termsOfService = "Terms of service"),
        servers = {
                @Server(description = "Prod ENV", url = "https://wordgenerator-production.up.railway.app/"),
                @Server(description = "Local ENV", url = "http://localhost:8080") },
        security = { @SecurityRequirement(name = "bearerAuth") })
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT auth description",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER)
public class OpenApiConfig {
}
