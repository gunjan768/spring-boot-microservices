package com.gunjan768.restful.restful_demo.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

@RestController
public class UserJPAResource {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;

	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping("/jpa/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);

		if (user.isEmpty()) {
			throw new UserNotFoundException("User with id: " + id + " not found");
		}

		// "all-users", SERVER_PATH + "/users"
		// retrieveAllUsers
		EntityModel<User> resource = EntityModel.of(user.get());	//new EntityModel<User>(user.get());

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

	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
	}

	// input - details of user
	// output - CREATED & Return the created URI

	@PostMapping("/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);

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
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrieveSpecificUserPosts(@PathVariable int id) {
		Optional<User> userOptional = userRepository.findById(id);
		
		if(userOptional.isEmpty()) {
			throw new UserNotFoundException("User with id: " + id + " not found");
		}
		
		return userOptional.get().getPosts();
	}

	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post) {
		
		Optional<User> userOptional = userRepository.findById(id);
		
		if(userOptional.isEmpty()) {
			throw new UserNotFoundException("User with id: " + id + " not found");
		}

		User user = userOptional.get();
		post.setUser(user);
		
		postRepository.save(post);

		// location will look like: http://localhost:8080/jpa/users/10001/posts/1
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(post.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}
}