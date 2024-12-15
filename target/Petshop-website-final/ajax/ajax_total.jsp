<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.Locale" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% NumberFormat format = NumberFormat.getInstance(new Locale("vn", "VN"));%>

<%=format.format(request.getAttribute("total"))%>
