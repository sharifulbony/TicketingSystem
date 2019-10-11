package com.sharifulbony.ticketing;

import com.sharifulbony.ticketing.JWT.JwtRequest;
import com.sharifulbony.ticketing.JWT.JwtResponse;
import com.sharifulbony.ticketing.JWT.JwtTokenUtil;
import com.sharifulbony.ticketing.ticket.TicketEntity;
import com.sharifulbony.ticketing.ticket.TicketRepository;
import com.sharifulbony.ticketing.ticket.TicketService;
import com.sharifulbony.ticketing.user.UserService;
import com.sharifulbony.ticketing.user.UserDTO;
import com.sharifulbony.ticketing.user.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 *
 */
@RestController
@RequestMapping(value = "")
@Api(value = "Product Inventory System", description = "Operations related to Product Inventory Management System")

public class RESTController {


    @Autowired
    UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserService userDetailsService;

    @Autowired
    private TicketService ticketService;

    @Autowired TicketRepository ticketRepository;





    @RequestMapping(value = "/all-ticket", method = RequestMethod.GET)
    @ApiOperation(value = DocumentationStaticContext.allCategoryDescription, response = Iterable.class)
    public List<TicketEntity> getAllTicket() {

        return ticketRepository.findByStatus((short) 0);
    }

    @RequestMapping(value = "/save-ticket", method = RequestMethod.POST)
    @ApiOperation(value = DocumentationStaticContext.allCategoryDescription, response = Iterable.class)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void saveTicket(@RequestBody TicketEntity ticketEntity) {

        ticketService.saveData(ticketEntity);


    }


    @RequestMapping(value = "/close-ticket", method = RequestMethod.POST)
    @ApiOperation(value = DocumentationStaticContext.allCategoryDescription, response = Iterable.class)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void closeTicket(@RequestBody TicketEntity ticketEntity) {
        ticketEntity.setStatus((short) 1);
        ticketRepository.save(ticketEntity);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ApiOperation(value = DocumentationStaticContext.userRegisterDescription, response = Iterable.class)
    public ResponseEntity<?> saveUser(@ApiParam(value = DocumentationStaticContext.userRegisterParam) @RequestBody UserDTO user) throws Exception {
        return ResponseEntity.ok(userDetailsService.save(user));
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    @ApiOperation(value = DocumentationStaticContext.userAuthenticationDescription, response = Iterable.class)

    public ResponseEntity<?> createAuthenticationToken(@ApiParam(value = DocumentationStaticContext.userAuthenticationParam) @RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }


    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }




}
