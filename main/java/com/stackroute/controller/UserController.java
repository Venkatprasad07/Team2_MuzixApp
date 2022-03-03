package com.stackroute.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.stackroute.model.User;
import com.stackroute.repository.UserRepository;
import com.stackroute.service.UserService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;

	@PostMapping(value = "/api/v1/user/adduser")
	public ResponseEntity<?> addUser(@RequestBody User user) {
		System.out.println(userService.validateemailid(user.getEmail()));
		if (userService.validateemailid(user.getEmail())){
			userService.addUser(user);
			return new ResponseEntity<>("New User added", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("Registraion with this emailID already exits", HttpStatus.CONFLICT);
		}
	}

	@PutMapping("/api/v1/user/login")
	public ResponseEntity<?> login(@RequestBody User user) {
		if (userService.validate(user.getUsername(), user.getPassword())) {
			user.setStatus(true);
			// userService.addUser(user);
			return new ResponseEntity<>("User logged in", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("User not authorized", HttpStatus.UNAUTHORIZED);
		}
	}

	@PutMapping("/api/v1/user/logout")
	public ResponseEntity<?> logout(@RequestBody User user) {
		if (userService.isloggedin(user.getUsername())) {
			user.setStatus(false);
			// userService.addUser(user);
			return new ResponseEntity<>("User logged out ", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("User not logged out", HttpStatus.CONFLICT);
		}
	}
	@PostMapping("api/v1/user/adduser/upload")
	public ResponseEntity<?> addUser(@RequestBody User user,@RequestParam("imageFile") MultipartFile file){

		System.out.println("Original Image Byte Size - " + file.getBytes().length);
		User user  = new User(compressBytes(file.getBytes()));
		userRepository.save(user);
		return ResponseEntity.status(HttpStatus.OK);
	}

	@GetMapping(path = { "/get/{imageName}" })
	public User getImage(@PathVariable("imageName") String imageName) throws IOException {

		final Optional<User> retrievedImage = userRepository.findByUsername(imageName);
		User user = new User(retrievedImage.get().getUsername(), retrievedImage.get(),
				decompressBytes(retrievedImage.get().getProfileimage()));
		return user;
	}

	// compress the image bytes before storing it in the database
	public static byte[] compressBytes(byte[] data) {
		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		while (!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		try {
			outputStream.close();
		} catch (IOException e) {
		}
		System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

		return outputStream.toByteArray();
	}

	// uncompress the image bytes before returning it to the angular application
	public static byte[] decompressBytes(byte[] data) {
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		try {
			while (!inflater.finished()) {
				int count = inflater.inflate(buffer);
				outputStream.write(buffer, 0, count);
			}
			outputStream.close();
		} catch (IOException ioe) {
		} catch (DataFormatException e) {
		}
		return outputStream.toByteArray();
	}
}


