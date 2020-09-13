package com.assurity.demo2.service;

import com.assurity.demo2.service.dao.EmployeeDAO;
import com.assurity.demo2.service.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController
{
    @Autowired
    private EmployeeDAO employeeDao;

    EmployeeController()
    {
        employeeDao = new EmployeeDAO();
        this.addInit();
    }

    @GetMapping(path="/getuser/", produces = "application/json")
    public ResponseEntity<Employees> getEmployees()
    {
        this.addInit();
        return new ResponseEntity<Employees>(employeeDao.getAllEmployees(), HttpStatus.OK);
    }

    @PostMapping(path= "/adduser/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> addEmployee(@RequestBody Employee employee)
    {
        Integer id = employeeDao.getAllEmployees().getEmployeeList().size() + 1;
        employee.setId(id);

        employeeDao.addEmployee(employee);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(employee.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
    private void addInit()
    {
        Employee employee = new Employee();
        employee.setId(1233);
        employee.setEmail("dkasunw@gmail1.com");
        employee.setFirstName("user1");
        employee.setLastName("demo1");
        employeeDao.addEmployee(employee);
    }
}