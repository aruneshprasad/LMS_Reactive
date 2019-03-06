package com.lms.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractReactiveCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.config.EnableReactiveCouchbaseRepositories;


@Configuration
@EnableReactiveCouchbaseRepositories(basePackages={"com.lms"})
public class LmsCouchbaseConfig extends AbstractReactiveCouchbaseConfiguration{

    public static final List<String> NODE_LIST = Arrays.asList("localhost");
    public static final String DEFAULT_BUCKET_NAME = "student";
    public static final String DEFAULT_BUCKET_PASSWORD = "student12";

    @Override
    protected List<String> getBootstrapHosts() {
        return NODE_LIST;
    }

    @Override
    protected String getBucketName() {
        return DEFAULT_BUCKET_NAME;
    }

    @Override
    protected String getBucketPassword() {
        return DEFAULT_BUCKET_PASSWORD;
    }
}
