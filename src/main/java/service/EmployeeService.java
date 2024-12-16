package service;

import java.util.List;

import model.Employee;

public interface EmployeeService {
	public List<Employee> getAll();
	public void add(Employee employee);
	public void delete(int code);
	public Employee search(int code);
	public Employee update(Employee employee);
}
