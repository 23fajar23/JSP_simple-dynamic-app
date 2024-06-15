<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Student" %>
<%@ page import="java.util.Map" %>
<html>
<head>
    <title>Dashboard</title>
    <link rel="stylesheet" type="text/css" href="assets/css/style.css">
</head>
<body>
    <h4>Welcome <%= request.getAttribute("userId") %> </h4>

    <table class="table-data" border="1" cellspacing="0" cellpadding="0">
        <thead>
            <td>Department</td>
            <td>Student ID</td>
            <td>Marks</td>
            <td>Pass %</td>
        </thead>
        <tbody>
            <%
                List<String> departments = (List<String>) request.getAttribute("department");
                List<Student> students = (List<Student>) request.getAttribute("students");
                Map<String, Double> passPercentages = (Map<String, Double>) request.getAttribute("passPercentages");

                for (String department : departments) {
                    boolean firstRow = true;
                    int rowCount = 0;
                    for (Student student : students) {
                        if (student.getDepartment().equals(department)) {
                            rowCount++;
                        }
                    }
                    for (Student student : students) {
                        if (student.getDepartment().equals(department)) {
            %>
            <tr>
                <% if (firstRow) { %>
                    <td align="center" rowspan="<%= rowCount %>"><%= department %></td>
                <% } %>
                <td><a href="javascript:void(0);" onclick="alert('<%= student.getName() %>');"> <%= student.getId() %></a></td>
                <td align="right"><%= student.getMark() %></td>
                <% if (firstRow) { %>
                    <td align="center" rowspan="<%= rowCount %>"><%= String.format("%.2f", passPercentages.get(department)) %></td>
                <% } %>
            </tr>
            <%
                            firstRow = false;
                        }
                    }
                }
            %>
        </tbody>
    </table>
</body>
</html>
