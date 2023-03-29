package com.dev.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.backend.model.Permission;
import com.dev.backend.service.PermissionService;

@RestController
@RequestMapping("/api/permission")
public class PermissionController {

  @Autowired
  private PermissionService permissionService;

  @GetMapping
  public List<Permission> getPermissions() {
    return permissionService.listPermissions();
  }

  @PostMapping
  public Permission createNewPermission(@RequestBody Permission permission) {
    return permissionService.insert(permission);
  }

  @PutMapping
  public Permission updatePermission(@RequestBody Permission permission) {
    return permissionService.update(permission);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> deletePermission(@PathVariable("id") Long id) {
    permissionService.delete(id);
    return ResponseEntity.ok().build();
  }
}
