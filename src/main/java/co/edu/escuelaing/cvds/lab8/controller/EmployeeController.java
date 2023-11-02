package co.edu.escuelaing.cvds.lab8.controller;

import co.edu.escuelaing.cvds.lab8.model.Employee;
import co.edu.escuelaing.cvds.lab8.service.EmployeeService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/empleados")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Mostrar el formulario para agregar un empleado
    @GetMapping("/agregar")
    public String mostrarFormularioAgregarEmpleado(Model model) {
        model.addAttribute("empleado", new Employee());
        return "CREATE";
    }
    // Procesar el formulario y agregar el empleado
    @PostMapping("/agregar")
    public String agregarEmpleado(Employee empleado) {
        employeeService.createEmployee(empleado);
        return "redirect:/empleados/lista"; // Redirigir a la lista de empleados
    }

    // Mostrar la lista de empleados
    @GetMapping("/lista")
    public String mostrarListaEmpleados(Model model) {
        model.addAttribute("empleados", employeeService.getAllEmployees());
        return "READ";
    }


    @GetMapping("/editar/{employeeId}")
    public String loadUpdateEmployeeForm(@PathVariable Long employeeId, Model model) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        model.addAttribute("employee", employee);
        return "UPDATE";
    }

    @PostMapping("/editar/{employeeId}")
    public String updateEmployee(@PathVariable Long employeeId, @ModelAttribute Employee updatedEmployee) {
        Employee existingEmployee = employeeService.getEmployeeById(employeeId);
        if (existingEmployee == null) {
            return "redirect:/empleados/lista"; 
        }

        employeeService.updateUser(updatedEmployee);
          return "redirect:/empleados/lista"; 
    }

    @PostMapping("/eliminar/{employeeId}")
    public String deleteEmployee(@PathVariable long employeeId) {
        employeeService.deleteUser(employeeId);
        return "redirect:/empleados/lista";
    }

    @GetMapping("/histogramaSalarios")

    public String mostrarHistogramaSalarios(Model model) {
        List<Employee> empleados = employeeService.getAllEmployees();

       List<Double> salarios = new ArrayList<>();
        // salarioo de los empleados 
        for (Employee empleado : empleados) {
            double salario = empleado.getSalary();
            salarios.add(salario);
        }

        // ordenar lista 
        Collections.sort(salarios);

        // Variables importantes
        double salarioMinimo = salarios.get(0); // Obtener el salario mínimo
        double salarioMaximo = salarios.get(salarios.size() - 1); // Obtener el salario máximo
        double rango = salarioMaximo - salarioMinimo;

        //numero de clases, cuantas barras entre 5 y 10 lo mas recomendable 
        int numeroDeClases =10;

        double anchoDeClase= rango/numeroDeClases;
        List<Double> Marcasdeclase = new ArrayList<>();
        int[] frecuenciaAbsoluta = new int[numeroDeClases];
        double limiteInferior= salarioMinimo;
        double limiteSuperior= 0;
     

        for (int i = 0; i < numeroDeClases; i++) {
             
             limiteSuperior = limiteInferior + anchoDeClase;
             double marcadeclase = (limiteInferior + limiteSuperior)/2;
             Marcasdeclase.add(marcadeclase);
             int frecuencia = 0;
             for (double salario : salarios) {
                
                if (salario >= limiteInferior && salario < limiteSuperior) {
                    frecuencia++;
                }
            }
           frecuenciaAbsoluta[i] = frecuencia;
           limiteInferior=limiteSuperior;
           limiteSuperior= limiteInferior+anchoDeClase;
        }
        // como la ultima marca de clase si deberia tomar el limite superior sumamos 1 al final
        frecuenciaAbsoluta[frecuenciaAbsoluta.length - 1] += 1;
        //Como lo vamos a llamar en el html 
        model.addAttribute("marcasDeClase", Marcasdeclase);
        model.addAttribute("frecuenciaAbsoluta", frecuenciaAbsoluta);
        return "histogramaSalarios";
    }


    @GetMapping("/PieEmpleados")
    public String mostrarGrafico(Model model) {

        List<Employee> empleados = employeeService.getAllEmployees();
        
        Map<String, Integer> empresaEmpleadoCount = new HashMap<>();

        // Itera sobre la lista de empleados y cuenta el número de empleados por empresa
        for (Employee empleado : empleados) {
            String sex = empleado.getSex();
            empresaEmpleadoCount.put(sex, empresaEmpleadoCount.getOrDefault(sex, 0) + 1);
        }

        model.addAttribute("empresaEmpleadoCount", empresaEmpleadoCount);

        return "PieEmpleados"; // Nombre de la vista Thymeleaf
    }

    


    @GetMapping("menu")
    public String menu() {
        return "Menu";
    }

}
