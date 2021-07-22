package com.gunjan768.restful.restful_demo.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

// LiveReload doesn't work (dev tools): https://stackoverflow.com/questions/33349456/how-to-make-auto-reload-with-spring-boot-on-idea-intellij
@RestController
public class UserResource {

	@Autowired
	private UserDaoService service;

	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return service.findAll();
	}

	@GetMapping("/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable(value = "id") int id) {
		User user = service.findOne(id);
		
		if(user == null) {
			throw new UserNotFoundException("id-" + id);
		}
		
		// "all-users", SERVER_PATH + "/users"
		// retrieveAllUsers
		EntityModel<User> resource = EntityModel.of(user);

		// We are implementing HATEOAS. When someone requests for the specific user, then along with the response to that request, user will
		// also receive some additional link, a link to all users. spring-boot-hateoas-starter dependency enables us to easily add links using
		// the methods. linkTo(methodOn(this.getClass()).retrieveAllUsers()) : it will get the link associated with method retrieveAllUsers().
		// If you try to get the link of that method which is not a RestController handler (not associated with any HTTP method annotation) then
		// an error will be thrown.
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());

		//System.out.println(linkTo);

		resource.add(linkTo.withRel("all-users"));
		
		return resource;
	}

	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) throws Exception {
		User user = service.deleteById(id);
		
		if(user == null) {
			//throw new UserNotFoundException("User with id: " + id + " not found");
			throw new Exception("Something went wrong baby");
		}
	}

	// input - details of user
	// output - CREATED & Return the created URI
	
	// HATEOAS: Hypermedia As The Engine Of Application State
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {

		User savedUser = service.save(user);
		
		// We don't want to hardcode the path "/users" so we have used path builder class "ServletUriComponentsBuilder". Resultant uri will look
		// like this: http://localhost:8080/users/6
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()		// It will pick the current-request-location i.e. here "/users"
			.path("/{id}")		// append dynamic value of "id" to current request location
			.buildAndExpand(savedUser.getId())		// Pass the value of the path variable "id"
			.toUri();		
		
		// We want to send the status of 201 which signifies "created" so we have used ResponseEntity class as we can add HttpStatus code. We
		// pass the location (an instance named "location" of URI) of the resource that was created. To see the location, see the Headers
		// section as Location is one of the response headers.
		return ResponseEntity.created(location).build();
	}
}
