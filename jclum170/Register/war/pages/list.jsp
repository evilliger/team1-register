<%@ page import="java.util.List" %>
<%@ page import="com.google.appengine.api.datastore.*" %>
<%@ page import="com.olympics.register.models.AddressEntry" %>

<html>
<head>
<style type="text/css"><!--
	.link:hover {cursor: pointer; cursor: hand;}
</style>
</head>
<body>
	<h1>ADDRESS ENTRY MANAGER</h1>
 
	Function : <a href="/addressentry/add">Add Address Entry</a>
	<hr />
 
	<h2>All Address Entries</h2>
	<table border="1">
		<thead>
			<tr>
				<td>Name</td>
				<td>Email</td>
				<td>Created Date</td>
				<td>Action</td>
			</tr>
		</thead>
		<%
 
		    List<AddressEntry> entries = (List<AddressEntry>)request.getAttribute("aeList");
		    for(AddressEntry e : entries){
 
		%>
			<tr class="link" onclick="document.location = '/addressentry/list/<%=KeyFactory.keyToString(e.getKey())%>'">
			  <td><%=e.getName() %></td>
			  <td><%=e.getEmail() %></td>
			  <td><%=e.getDate()%></td>
			  <td><a href="/addressentry/update/<%=KeyFactory.keyToString(e.getKey())%>">Update</a> 
                             | <a href="/addressentry/delete/<%=KeyFactory.keyToString(e.getKey())%>">Delete</a></td>
			</tr>
		<%
			}
		%>
	</table>
	<%	
		AddressEntry e = new AddressEntry();
	
		if(request.getAttribute("updateEntity")!=null){
		
			e = (AddressEntry)request.getAttribute("updateEntity");
	%>
	<div>
		<h2>Update AddressEntry</h2>
		<form method="post" action="/addressentry/update" >
			<input type="hidden" name="key" id="key" 
				value="<%=KeyFactory.keyToString(e.getKey()) %>" /> 
			<table>
				<tr>
					<td>
						UserName :
					</td>
					<td>
						<input type="text" style="width: 185px;" maxlength="30" name="name" id="name" 
							value="<%=e.getName() %>" />
					</td>
				</tr>
				<tr>
					<td>
						Email :
					</td>
					<td>
						<input type="text" style="width: 185px;" maxlength="30" name="email" id="email" 
							value="<%=e.getEmail() %>" />
					</td>
				</tr>
			</table>
			<input type="submit" class="update" title="Update" value="Update" />
		</form>
	</div>
 	<%
		}
	%>
</body>
</html>