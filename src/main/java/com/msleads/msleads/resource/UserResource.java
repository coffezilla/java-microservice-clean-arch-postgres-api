package com.msleads.msleads.resource;

import com.msleads.msleads.model.User;
import com.msleads.msleads.service.UserService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/users")
public class UserResource {
    private final UserService userService;

    // constructor
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        List<User> users = userService.getAllUsers();
        return Response.ok(users).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(User user, @QueryParam("password") String password) {
        user.setCreatedAt(LocalDateTime.now());
        userService.createUser(user, password);
        return Response.status(Response.Status.CREATED).entity(user).build();
    }

    @DELETE
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("userId") Long userId) {
        userService.deleteUser(userId);
        Map<String, String> response = new HashMap<>();
        response.put("message", "User deleted successfully");
        return Response.ok(response).build();
    }

    @GET
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@PathParam("userId") Long userId) {
        User user  = userService.findUserById(userId);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(user).build();
    }

    @PUT
    @Path("/{userId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("userId") Long userId, User updatedUser) {
        User user = userService.findUserById(userId);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        // user.setCreatedAt(updatedUser.getCreatedAt());
        userService.updateUser(user);
        return Response.ok(user).build();
    }

}
