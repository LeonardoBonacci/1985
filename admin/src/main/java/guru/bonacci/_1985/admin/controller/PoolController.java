package guru.bonacci._1985.admin.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import guru.bonacci._1985.admin.domain.AdminUser;
import guru.bonacci._1985.admin.domain.Pool;
import guru.bonacci._1985.admin.dto.Mappers;
import guru.bonacci._1985.admin.dto.PoolDto;
import guru.bonacci._1985.admin.service.AdminService;
import guru.bonacci._1985.admin.service.PoolService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/pools")
@RequiredArgsConstructor
public class PoolController {
  
  private final PoolService poolService;
  private final AdminService adminService;


  @PostMapping("/admins/{adminId}")
  public Pool create( @PathVariable("adminId") Long adminId,
                      @Valid @RequestBody 
                      PoolDto dto) {
    var pool = Mappers.mapPool(dto);
    return poolService.createPool(adminId, pool); 
  }

  @GetMapping("/{poolId}")
  public Optional<Pool> retrieve(@PathVariable("poolId") Long poolId) {
    return poolService.getPool(poolId);
  }

  @GetMapping("/{poolId}/size")
  public Integer size(@PathVariable("poolId") Long poolId) {
    return poolService.getPoolSize(poolId);
  }

  @GetMapping("/{poolId}/allnames")
  public List<String> allPoolNames(@PathVariable("poolId") Long poolId) {
    return poolService.allPoolNames();
  }

  @GetMapping("/{poolId}/admin")
  public Optional<AdminUser> retrieveAdmin(@PathVariable("poolId") Long poolId) {
    return adminService.getAdminByPoolId(poolId); 
  }
  
  @DeleteMapping("/{poolId}")
  public void delete(@PathVariable("poolId") Long poolId) {
    poolService.deactivate(poolId);
  }
}
