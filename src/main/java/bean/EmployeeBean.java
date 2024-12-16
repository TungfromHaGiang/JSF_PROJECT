package bean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import model.Employee;
import service.EmployeeService;

@Named
@SessionScoped
public class EmployeeBean  implements Serializable {
    private static final long serialVersionUID = 1L;
    @Inject
    private EmployeeService employeeService;
    private Employee newEmployee = new Employee();
    public EmployeeBean() {
   
    }
    public List<Employee> getAll(){
    	return employeeService.getAll();    			
    }
    public String add() {
    	employeeService.add(newEmployee);
    	newEmployee = new Employee();
    	return "index";
		
	}
    public String delete(int x) {
    	employeeService.delete(x);
    	return "index";
	}
    public String showUpdate(Employee employee) {
    	newEmployee.setCode(employee.getCode());
    	newEmployee.setName(employee.getName());
    	newEmployee.setAge(employee.getAge());
    	newEmployee.setDob(employee.getDob());
    	return "update";
	}
    public String search(int x) {
    	newEmployee =  employeeService.search(x);	
    	return "detail";
	}
    public String update(Employee employee) {
    	employeeService.update(newEmployee);
    	return "detail";
	}
    
	public Employee getNewEmployee() {
		return newEmployee;
	}
	
	public void setNewEmployee(Employee newEmployee) {
		this.newEmployee = newEmployee;
	}
    
}
