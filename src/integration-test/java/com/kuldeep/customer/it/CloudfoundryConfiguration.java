package com.kuldeep.customer.it;

import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 * This needs to have all the bean exposed which {@link org.cloudfoundry.operations.CloudFoundryOperations} object need to push our app
 * to cloudfoundry public service and run so that our integration tests can pass.
 *
 * For more info please check {@link CustomerItApplicationTests}
 */
@Configuration
public class CloudfoundryConfiguration {

}
