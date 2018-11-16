package com.niit.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.UserDAO;
import com.niit.models.ErrorClazz;
import com.niit.models.User;

@RestController
public class UserController {
@Autowired
private UserDAO userDAO;

@RequestMapping(value="/register", method=RequestMethod.POST)
public ResponseEntity<?> registration(@RequestBody User user)
{
	try
	{
		if(!userDAO.isEmailUnique(user.getEmail()))
		{
			ErrorClazz errorClazz=new ErrorClazz(2,"Email is already exists..");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		userDAO.registration(user);
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}catch (Exception e)
	{
		ErrorClazz errorClazz= new ErrorClazz(1,"Something went wrong"+e.getMessage());
		return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
@RequestMapping(value="/login",method=RequestMethod.PUT)
public ResponseEntity<?> login(@RequestBody User user,HttpSession session){
	User validUser= userDAO.login(user);
	if(validUser==null) {
		ErrorClazz errorClazz=new ErrorClazz(4,"Invalid email/password...");
		return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
	}
	else
	{
		validUser.setOnline(true);
		userDAO.updateUser(validUser);
		session.setAttribute("loggedInUser", validUser.getEmail());
		return new ResponseEntity<User>(validUser,HttpStatus.OK);
		
	}
	
}
@RequestMapping(value="/logout",method=RequestMethod.PUT)
public ResponseEntity<?> logout(HttpSession session){
	String email=(String) session.getAttribute("loggedInUser");
	System.out.println("logout method "+email +" email for logged in user" );
	if(email==null) {
		ErrorClazz errorClazz=new ErrorClazz(4,"Uauthorized access.. please login.....");
		return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
	}
	User user=userDAO.getUser(email);
	user.setOnline(false);
	userDAO.updateUser(user);
	session.removeAttribute("loggedInUser");
	session.invalidate();
	return new ResponseEntity<Void>(HttpStatus.OK);
}
}
