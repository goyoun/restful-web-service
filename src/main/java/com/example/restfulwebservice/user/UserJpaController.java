package com.example.restfulwebservice.user;

import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jpa")
public class UserJpaController {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    // http://localhost:8088/jpa/users
    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);

        if(!user.isPresent()) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        EntityModel<User> userModel = new EntityModel<>(user.get());
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        userModel.add(linkTo.withRel("all-users"));

        return userModel;
    }

    //사용자 삭제
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
    }

    // 사용자 추가
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
//        상태 코드값 반환위해 responseEntity 사용 <User> 엔드포인트 유효성검사 Valid , json타입 RequestBody

        User savedUser = userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();

    }
    // jpa/users/90001/posts 호출
    @GetMapping("/users/{id}/posts")
    public List<Post> retrieveAllPostsByUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);

        if(!user.isPresent()) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }
        return user.get().getPosts();
    }

    //Post생성
    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Post> createPost(@PathVariable int id, @RequestBody Post post) {
//        상태 코드값 반환위해 responseEntity 사용 <User> 엔드포인트 유효성검사 Valid , json타입 RequestBody
        Optional<User> user = userRepository.findById(id);

        if(!user.isPresent()) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }
        post.setUser(user.get());
        Post savedPost = postRepository.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();

        return ResponseEntity.created(location).build();

    }

}
