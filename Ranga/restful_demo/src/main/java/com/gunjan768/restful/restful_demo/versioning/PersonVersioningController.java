package com.gunjan768.restful.restful_demo.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// Versioning is the hard to handle. There are various ways to handle versions. Some of them are discussed below. Some companies versioning types:
// Github: Media type, Microsoft: Headers, Twitter: URI, Amazon: Request parameter. Media and Header Versioning types cannot be executed in the
// browser as we can't set the headers using browser.
@RestController
public class PersonVersioningController {

	// First way of versioning is called URI versioning.

	// Simplest way of doing versioning is providing different URI for different versions. Here we provided "v1/person" for version1 where
	// name field is not split and "v2/person" for version2 where name is being split into 2 more fields: firstName and lastName.
	@GetMapping("v1/person")
	public PersonV1 personV1() {
		return new PersonV1("Bob Charlie");
	}

	@GetMapping("v2/person")
	public PersonV2 personV2() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}

	// Second way of doing versioning is by using query params (Request parameter versioning). URL will be like:
	// http://localhost:port/person/param?version=x where x is the version number

	// params = "version=1": means if query param variable "version" is set to 1 then this method will execute
	@GetMapping(value = "/person/param", params = "version=1")
	public PersonV1 paramV1() {
		return new PersonV1("Bob Charlie");
	}

	@GetMapping(value = "/person/param", params = "version=2")
	public PersonV2 paramV2() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}

	// Third way of doing versioning is by using headers.

	// headers: List of name of the headers. "X-API-VERSION": you can give any name to header. This method will execute when url matches
	// plus header "X-API-VERSION" is set to 1
	@GetMapping(value = "/person/header", headers = "X-API-VERSION=1")
	public PersonV1 headerV1() {
		return new PersonV1("Bob Charlie");
	}

	@GetMapping(value = "/person/header", headers = "X-API-VERSION=2")
	public PersonV2 headerV2() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}

	// Fourth way of handling version is using produces (aka Accept Header or Mime Type or Media Type or Content Negotiation).

	// produces says what is the output of the specific service. produces is also a header whose key = "Accept" and value is equal to the value
	// of produces property. Like this: Accept = application/vnd.company.app-v1+json.

	// If you change the header name (from Accept) to something else, then first handler (method) which matches URL will get executed i.e. here
	// this method will get execute as it is the first method with URL = "/person/produces". Below method has same URL but will not run as
	// it comes after this method.
	@GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v1+json")
	public PersonV1 producesV1() {
		return new PersonV1("Bob Charlie");
	}

	@GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v2+json")
	public PersonV2 producesV2() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}
}