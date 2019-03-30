package nu.ist.iosf.core.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Profile("dev")
@Configuration
@EnableTransactionManagement
@PropertySource("classpath:db/db-dev.properties")
@ComponentScan("nu.ist.iosf.entityRepo")
public class DevDatabaseConfig extends DbConfig{

//

}

