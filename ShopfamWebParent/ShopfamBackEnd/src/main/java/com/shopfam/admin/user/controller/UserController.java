package com.shopfam.admin.user.controller;

import java.io.IOException;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shopfam.admin.FileUploadUtil;
import com.shopfam.admin.paging.PagingAndSortingHelper;
import com.shopfam.admin.paging.PagingAndSortingParam;
import com.shopfam.admin.user.UserNotFoundException;
import com.shopfam.admin.user.UserService;
import com.shopfam.admin.user.export.UserCsvExporter;
import com.shopfam.admin.user.export.UserPdfExporter;
import com.shopfam.common.entity.*;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class UserController {
		@Autowired
		private UserService service;
		
		@GetMapping("/users")
		public String listFirstPage() {
			return "redirect:/users/page/1?sortField=firstname&sortDir=asc";
		}
		
		@GetMapping("/users/page/{pageNum}")
		public String listByPage(
				@PagingAndSortingParam(listName = "listUsers" , moduleURL = "/users") PagingAndSortingHelper helper,
				@PathVariable(name="pageNum") int pageNum 
				) {
			service.listByPage(pageNum ,helper);
			return "users";
		}
		
		
		@GetMapping("/users/new")
		public String newUser(Model model) {
			List<Role> listRoles =service.listRoles();
			
			User user = new User();
			user.setEnabled(true);
			
			model.addAttribute("user" , user);
			model.addAttribute("listRoles" , listRoles);
			model.addAttribute("pageTitle" , "Create New User");

			return "user_form";
		}
		
		@PostMapping("/users/save")
		public String saveUser(User user , RedirectAttributes redirectAttributes , 
				@RequestParam("image") MultipartFile multipartFile) throws IOException {
				if (!multipartFile.isEmpty()) {
					String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename()); 
					user.setPhotos(fileName);
					
					User savedUser = service.save(user);
					String uploadDir = "user-photos/" + savedUser.getId();
					FileUploadUtil.cleanDir(uploadDir);

					FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);


				}else {
					if (user.getPhotos().isEmpty()) user.setPhotos(null);
					service.save(user);

				}
				
			
			
			redirectAttributes.addFlashAttribute("message" , "The user has been saved successfully.");

			return getRedirectedURLtoAffectedUser(user);
		}
		private String getRedirectedURLtoAffectedUser(User user) {
			String firstPartOfEmail = user.getEmail().split("@")[0];
			return "redirect:/users/page/1?sortField=id&sortDir=asc&keyword=" + firstPartOfEmail;
		}
		
		@GetMapping("/users/edit/{id}")
		public String editUser(@PathVariable(name ="id") Integer id , RedirectAttributes redirectAttributes,
				Model model) {
			try {
				User user = service.get(id);
				List<Role> listRoles =service.listRoles();

				model.addAttribute("user" , user);
				model.addAttribute("pageTitle" , "Edit User (ID: " + id + ")");
				model.addAttribute("listRoles" , listRoles);

			
				return "user_form";
			}catch (UserNotFoundException ex) {
				redirectAttributes.addFlashAttribute("message" , ex.getMessage());
				return "redirect:/users";
			}
		}	
		@GetMapping("/users/delete/{id}")
		public String deleteUser(@PathVariable(name ="id") Integer id , RedirectAttributes redirectAttributes,
					Model model) {
			try {
				service.delete(id);;
				redirectAttributes.addFlashAttribute("message" , "The user ID " + id + " has been deleted successfully");
			}catch (UserNotFoundException ex) {
				redirectAttributes.addFlashAttribute("message" , ex.getMessage());
			
			}
			return "redirect:/users";
		
		}
		@GetMapping("/users/{id}/enabled/{status}")
		public String updateUserEnabledStatus(@PathVariable("id") Integer id ,
				@PathVariable("status") boolean enabled , RedirectAttributes redirectAttributes) {
			service.updateUserEnabledStatus(id,enabled);
			String status =enabled ? "enabled" : "disabled";
			String message ="The user ID " + id + " has been " + status;
			redirectAttributes.addFlashAttribute("message" , message);
			
			return "redirect:/users";
		}
		@GetMapping("/users/export/csv")
		public void exportToCSV(HttpServletResponse response) throws IOException {
			List<User> listUsers = service.listAll();
			UserCsvExporter exporter = new UserCsvExporter();
			exporter.export(listUsers, response);
		}
		@GetMapping("/users/export/pdf")
		public void exportToPDF(HttpServletResponse response) throws IOException {
			List<User> listUsers = service.listAll();
			
			UserPdfExporter exporter = new UserPdfExporter();
			exporter.export(listUsers, response);
		}
		
}
