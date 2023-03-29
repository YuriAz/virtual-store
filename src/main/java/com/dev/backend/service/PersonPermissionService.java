package com.dev.backend.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.backend.model.Permission;
import com.dev.backend.model.Person;
import com.dev.backend.model.PersonPermission;
import com.dev.backend.repository.PermissionRepository;
import com.dev.backend.repository.PersonPermissionRepository;

@Service
public class PersonPermissionService {

  @Autowired
  private PersonPermissionRepository personPermissionRepository;

  @Autowired
  private PermissionRepository permissionRepository;

  public void boundPersonPermissionToClient(Person person) {
    List<Permission> listPermissions = permissionRepository.findByName("CLIENT");

    if (listPermissions.size() > 0) {
      PersonPermission personPermission = new PersonPermission();
      personPermission.setPerson(person);
      personPermission.setPermission(listPermissions.get(0));
      personPermission.setCreationDate(new Date());
      personPermissionRepository.saveAndFlush(personPermission);
    }
  }
}
