package main

import (
	"net/http"

	"github.com/aws/aws-sdk-go/aws"
	"github.com/aws/aws-sdk-go/aws/endpoints"
)

// If you are using the AWS APIs from applications that need to delegate
// TLS, after constructing your AWS config, wrap it with DelegatedTLS()

func delegatedEpResolver(
	service, region string,
	optFns ...func(*endpoints.Options),
) (endpoints.ResolvedEndpoint, error) {
	ep, err := endpoints.DefaultResolver().EndpointFor(service, region, optFns...)
	if err != nil {
		return ep, err
	}
	ep.URL = ep.URL + ":443"
	return ep, nil
}

func DelegatedTls(cfg *aws.Config) *aws.Config {
	return cfg.
		WithDisableSSL(true).
		WithEndpointResolver(endpoints.ResolverFunc(delegatedEpResolver))
}
