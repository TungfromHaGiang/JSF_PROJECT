package service.impl;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import dao.EmployeeDAO;
import model.Employee;
import service.EmployeeService;

@ApplicationScoped
public class EmployeeServiceImpl implements EmployeeService {
	@Inject
	private EmployeeDAO employeeDAO;
	@Override
	public List<Employee> getAll() {
		return employeeDAO.getAllEmployees();
	}
	@Override
	public void add(Employee employee) {
		employeeDAO.addEmployee(employee);	
	}
	@Override
	public void delete(int code) {
		employeeDAO.delete(code);
	}
	@Override
	public Employee search(int code) {
		return employeeDAO.search(code);
		
	}
	@Override
	public Employee update(Employee employee) {
		 return employeeDAO.update(employee);
	}
}
