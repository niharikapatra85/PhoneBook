package com.example.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.constants.AppConstants;
import com.example.entity.Contact;
import com.example.props.AppProporties;
import com.example.service.ContactService;

@RestController
public class ContactRestController {
	
	@Autowired
	private ContactService service;
	
	@Autowired
	private AppProporties appProps;
	
	@PostMapping("/contact")
	public String saveContact(@RequestBody Contact contact) {
		
		boolean status = service.saveContact(contact);
		
		Map<String, String> messages = appProps.getMessages();
		
		if(status) {
			return messages.get(AppConstants.CONTACT_SAVE_SUCC);
		}
		else {
			return  messages.get(AppConstants.CONTACT_SAVE_FAIL) ;
		}
	}
	
	@GetMapping("/contacts")
	public List<Contact> getAllContact(){
		return service.getAllContacts();
	}
	
	@GetMapping("/contact/{cid}")
	public Contact editContact(@PathVariable("cid") Integer contactId) {
		return service.getContactById(contactId);
	}
	
	@DeleteMapping("/contact/{cid}")
	public String deleteContact(@PathVariable("cid") Integer contactId) {
		boolean status = service.deleteContactById(contactId);
		
		Map<String, String> messages = appProps.getMessages();
		
		if(status) {
			return messages.get(AppConstants.CONTACT_DEL_SUCC);
		}
		else {
			return messages.get(AppConstants.CONTACT_DEL_FAIL);
		}
	}

}
