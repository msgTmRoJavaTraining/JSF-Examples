package group.msg.jsf_beans.day12;

import group.msg.entities.Employee;
import lombok.Data;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
@Data
public class EjbBackingBean implements Serializable {
    private static boolean dummyValuesGenerated = false;
    private List<Employee> employeeList;
    private Employee selectedEmployee;
    private String inputName, inputCNP, inputRole;
    private int index = 0;
    private StreamedContent file;
    private enum InfoIcon { INFO, ERROR; }

    @Inject
    private EjbBean ejbBean;

    @PostConstruct
    public void init() {
        employeeList = new ArrayList<>();

        if (!dummyValuesGenerated) {
            employeeList.add(new Employee("Astanei Andrei", "Software engineer", "1970926022996"));
            employeeList.add(new Employee("Leucuta Larisa", "Software engineer", "2970821023883"));
            employeeList.add(new Employee("Maran Alex", "Software engineer", "1980712033781"));
            dummyValuesGenerated = true;
        }
    }

    public boolean isDataValid(String data) {
        return data.length() > 0 && data.length() < 60;
    }

    public void onRowSelect(SelectEvent event) {
        Employee currentEmployee = (Employee) event.getObject();
        String searchCNP = currentEmployee.getCnp();

        for(int i = 0; i < employeeList.size(); i++) {
            if(employeeList.get(i).getCnp().equals(searchCNP))  {
                index = i;
                break;
            }
        }
    }

    public void addEmployee() {
        if(!inputCNP.isEmpty() && isDataValid(inputCNP) && inputCNP.length() == 13) {
            if(!inputName.isEmpty() &&isDataValid(inputName)) {
                if(!inputRole.isEmpty() &&isDataValid(inputRole)) {
                    employeeList.add(new Employee(inputName, inputRole, inputCNP));
                    inputName = ""; inputCNP = ""; inputRole = "";
                    sendUserNotification(InfoIcon.INFO,"Operation Status", "New Employee added successfully!");
                } else {
                    sendUserNotification(InfoIcon.ERROR,"Input Problem", "There is an issue with the selected role!");
                }
            } else {
                sendUserNotification(InfoIcon.ERROR,"Input Problem", "The name must have between 1 and 60 characters!");
            }
        } else {
            sendUserNotification(InfoIcon.ERROR,"Input Problem", "The CNP must have a length of 13 characters!");
        }
    }

    public void deleteEmployee() {
        employeeList.remove(index);
        sendUserNotification(InfoIcon.INFO,"Operation Status", "Employee deleted!");
    }

    public void exportToPDF() {
        String fileName = "My PDF File.pdf";
        file = new DefaultStreamedContent(
                ejbBean.exportToPDF(employeeList), FacesContext.getCurrentInstance().getExternalContext().getMimeType(fileName), fileName
        );
    }

    public StreamedContent getFile() {
        return file;
    }

    public void exportToExcel() {
        String fileName = "My Excel File.xlsx";
        file = new DefaultStreamedContent(
                ejbBean.exportToEXCEL(employeeList), FacesContext.getCurrentInstance().getExternalContext().getMimeType(fileName), fileName
        );
    }

    public void sendUserNotification(InfoIcon iconSetting, String summary, String detail) {
        FacesContext context = FacesContext.getCurrentInstance();

        switch (iconSetting) {
            case INFO:
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail));
                break;

            case ERROR:
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
                break;
        }
    }
}
