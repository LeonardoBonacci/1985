package guru.bonacci._1985.admin.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import guru.bonacci._1985.admin.domain.AdminUser;
import guru.bonacci._1985.admin.service.AdminService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admins")
@RequiredArgsConstructor
public class AdminController {

  private final AdminService adminService;

   
  @PostMapping("/{userId}")
  public Optional<AdminUser> register(@PathVariable("userId") Long userId) {
    return adminService.createAdmin(userId, "foo bank details"); 
  }

  @DeleteMapping("/{adminId}")
  public void unregister(@PathVariable(value = "adminId") Long adminId) { 
    adminService.delete(adminId);
  }
}
