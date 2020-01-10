package com.kuldeep.customer.it;

import com.kuldeep.customer.client.Customer;
import com.kuldeep.customer.client.CustomerClient;
import com.kuldeep.customer.client.CustomerClientConfiguration;
import org.assertj.core.api.BDDAssertions;
import org.cloudfoundry.operations.CloudFoundryOperations;
import org.cloudfoundry.operations.applications.ApplicationManifestUtils;
import org.cloudfoundry.operations.applications.PushApplicationManifestRequest;
import org.cloudfoundry.operations.applications.PushApplicationRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes ={CustomerItTestDrivenApplication.class,
        CustomerClientConfiguration.class,
        CloudfoundryConfiguration.class
})
public class CustomerItApplicationTests {

    //@Autowired
    private CloudFoundryOperations cf;

    @Autowired
    private CustomerClient client;

    private File manifestFile = new File("../manifest.yml");

    //@Test
    public void clientShouldTalkToService(){
        this.cf.applications().pushManifest(PushApplicationManifestRequest
                .builder()
                .manifest(ApplicationManifestUtils.read(this.manifestFile.toPath()).get(0))
                .build());
        Customer customer = client.getCustomerById(1L);
        BDDAssertions.then(customer.getId()).isNotNull();
        BDDAssertions.then(customer.getFirst()).isEqualTo("a");
        BDDAssertions.then(customer.getEmail()).isEqualTo("a@email.com");
    }
}
