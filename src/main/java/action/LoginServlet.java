package action;

import model.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String userId = req.getParameter("userid");
        String password = req.getParameter("password");

        res.setContentType("text/html");

        req.setAttribute("userId", userId);
        req.setAttribute("students", getStudents());
        req.setAttribute("department", getDepartments());
        req.setAttribute("passPercentages", getPassPercentages(getStudents()));
        req.getRequestDispatcher("dashboard.jsp").forward(req, res);
    }

    private List<String> getDepartments() {
        List<String> department = new ArrayList<>();
        List<Student> studentList = getStudents();

        for (Student student : studentList) {
            if (!department.contains(student.getDepartment())) {
                department.add(student.getDepartment());
            }
        }

        return department;
    }

    private List<Student> getStudents() {
        List<Student> studentList = new ArrayList<>();

        studentList.add(new Student("S1", "Mochammad", "Dep 1", 35));
        studentList.add(new Student("S2", "Fajar", "Dep 1", 70));
        studentList.add(new Student("S3", "Darussalam", "Dep 1", 60));
        studentList.add(new Student("S4", "Rajaf", "Dep 1", 90));
        studentList.add(new Student("S5", "Joko", "Dep 2", 30));
        studentList.add(new Student("S6", "Dani", "Dep 3", 32));
        studentList.add(new Student("S7", "Boys", "Dep 3", 70));
        studentList.add(new Student("S8", "Guys", "Dep 3", 20));

        return studentList;
    }

    private Map<String, Double> getPassPercentages(List<Student> students) {
        Map<String, Integer> passCounts = new HashMap<>();
        Map<String, Integer> totalCounts = new HashMap<>();

        for (Student student : students) {
            String dept = student.getDepartment();
            totalCounts.put(dept, totalCounts.getOrDefault(dept, 0) + 1);
            if (student.getMark() > 50) {
                passCounts.put(dept, passCounts.getOrDefault(dept, 0) + 1);
            }
        }

        Map<String, Double> passPercentages = new HashMap<>();
        for (String dept : totalCounts.keySet()) {
            int passCount = passCounts.getOrDefault(dept, 0);
            int totalCount = totalCounts.get(dept);
            passPercentages.put(dept, (double) passCount / totalCount * 100);
        }

        return passPercentages;
    }
}
